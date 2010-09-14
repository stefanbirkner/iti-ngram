package br.ufpb.ngrams.gui;

import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufpb.ngrams.MainProperties;
import br.ufpb.ngrams.NgramAnalyzer;
import br.ufpb.ngrams.Node;
import br.ufpb.ngrams.Probability;

public class NgramsThread extends Thread
{
	@Override
	public void run()
	{
		super.run();
		
		OutputPanel.getInstance().getTabbedPane().removeAll();
		
		String content = ContentPanel.getInstance().getTextArea().getText();
		List<List<Node>> ngrams = NgramAnalyzer.getNgrams(content, 3);
		
		StatusBar.getInstance().setMessage("Generating n-gram reports...");
		
		StatusBar.getInstance().getProgressBar().setMaximum(ngrams.size());
		
		for (int i = 0; i < ngrams.size(); i++)
		{
			List<Node> nodes = ngrams.get(i);
			
			DefaultTableModel tableModel = new DefaultTableModel();
			tableModel.addColumn(MainProperties.TABLE_ROWA);
			tableModel.addColumn(String.format(MainProperties.TABLE_ROWB, i + 1));
			tableModel.addColumn(MainProperties.TABLE_ROWC);
			
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				tableModel.addRow(new String[] {String.valueOf(j), node.toString(), String.valueOf(Probability.getProbability(nodes, j))});
			}
			
			JScrollPane scroll = new JScrollPane();
			JTable table = new JTable(tableModel);
			
			scroll.getViewport().add(table);
			OutputPanel.getInstance().getTabbedPane().add(String.format("%s-gram", (i + 1)), scroll);
			
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		this.conditionalUnigram(ngrams, content);
		this.conditionalBigram(ngrams, content);
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
	
	public void conditionalUnigram(List<List<Node>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional unigram...");
		
		List<Node> ngrama = ngrams.get(0);
		List<Node> ngramb = ngrams.get(1);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn(MainProperties.TABLE_ROWA);
		tableModel.addColumn("Condition");
		tableModel.addColumn(MainProperties.TABLE_ROWC);

		StatusBar.getInstance().getProgressBar().setMaximum(ngrama.size());
		
		int count = 0;
		for (int i = 0; i < ngrama.size(); i++)
		{
			Node nodeb = ngrama.get(i);
			for (Node nodea : ngrama)
			{
				Node referenceNode = new Node(nodeb.getSymbol() + nodea.getSymbol());
				int referenceIndex = ngramb.indexOf(referenceNode);
				
				float probability = 0;
				
				if (referenceIndex < 0)
				{
					probability = 0;
				}
				else
				{
					referenceNode = ngramb.get(referenceIndex);
					int amounta = nodea.getAmount();
					int amountb = referenceNode.getAmount();
					if (content.endsWith(nodea.getSymbol())) { amounta--; };
					if (amounta < 1) { probability = 0; }
					else { probability = (float) amountb / amounta; }
				}
				
				tableModel.addRow(new String[] {
					String.valueOf(count++),
					String.format("P(%s|%s)", nodea.getSymbol(), nodeb.getSymbol()),
					String.valueOf(probability)});
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}

		StatusBar.getInstance().setMessage("Generating conditional unigram output...");
		
		JScrollPane scroll = new JScrollPane();
		JTable table = new JTable(tableModel);
		
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(String.format("Conditional Unigram"), scroll);
	}
	
	public void conditionalBigram(List<List<Node>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");
		
		List<Node> ngrama = ngrams.get(0);
		List<Node> ngramb = ngrams.get(1);
		List<Node> ngramc = ngrams.get(2);

		DefaultTableModel tableModel = new DefaultTableModel();
		tableModel.addColumn(MainProperties.TABLE_ROWA);
		tableModel.addColumn("Condition");
		tableModel.addColumn(MainProperties.TABLE_ROWC);

		StatusBar.getInstance().getProgressBar().setMaximum(ngramb.size());
		
		int count = 0;
		for (int i = 0; i < ngramb.size(); i++)
		{
			Node nodeb = ngramb.get(i);
			for (Node nodea : ngrama)
			{
				Node referenceNode = new Node(nodeb.getSymbol() + nodea.getSymbol());
				int referenceIndex = ngramc.indexOf(referenceNode);
				
				float probability = 0;
				
				if (referenceIndex < 0)
				{
					probability = 0;
				}
				else
				{
					referenceNode = ngramc.get(referenceIndex);
					int amountb = nodeb.getAmount();
					int amountc = referenceNode.getAmount();
					
					if (content.endsWith(nodeb.getSymbol()))
					{
						amountb--;
					};
					
					if (amountb < 1)
					{
						probability = 0;
					}
					else
					{
						probability = (float) amountc / amountb;
					}
				}
				
				tableModel.addRow(new String[]
	                             {
									String.valueOf(count++),
									String.format("P(%s|%s)", nodea.getSymbol(), nodeb.getSymbol()),
									String.valueOf(probability)
								 });
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		StatusBar.getInstance().setMessage("Generating conditional bigram output...");
		
		JScrollPane scroll = new JScrollPane();
		JTable table = new JTable(tableModel);
		
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(String.format("Conditional Bigram"), scroll);
	}
}
