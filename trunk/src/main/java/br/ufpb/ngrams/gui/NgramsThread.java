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
		
		String text = ContentPanel.getInstance().getTextArea().getText();
		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		
		StatusBar.getInstance().setMessage("Generating n-gram reports...");
		
		StatusBar.getInstance().getProgressBar().setMaximum(3);
		
		for (int n = 1; n < 4; n++)
		{
			List<Node> nodes = analyzer.getNgramsOfLength(n);
			
			String labelOfSecondColumn = String.format(MainProperties.TABLE_ROWB, n);
			DefaultTableModel tableModel = createTableModelWithSecondColumnLabel(labelOfSecondColumn);
			
			for (int j = 0; j < nodes.size(); j++)
			{
				Node node = nodes.get(j);
				tableModel.addRow(new String[] {String.valueOf(j), node.toString(), String.valueOf(Probability.getProbability(nodes, j))});
			}
			
			JScrollPane scroll = new JScrollPane();
			JTable table = new JTable(tableModel);
			
			scroll.getViewport().add(table);
			OutputPanel.getInstance().getTabbedPane().add(String.format("%s-gram", n), scroll);
			
			StatusBar.getInstance().getProgressBar().setValue(n);
		}
		
		conditionalUnigram(text);
		conditionalBigram(text);
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
	
	public void conditionalUnigram(String text)
	{
		StatusBar.getInstance().setMessage("Generating conditional unigram...");

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		List<Node> ngrama = analyzer.getNgramsOfLength(1);
		List<Node> ngramb = analyzer.getNgramsOfLength(2);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

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
					if (text.endsWith(nodea.getSymbol())) { amounta--; };
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
	
	public void conditionalBigram(String text)
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		List<Node> ngrama = analyzer.getNgramsOfLength(1);
		List<Node> ngramb = analyzer.getNgramsOfLength(2);
		List<Node> ngramc = analyzer.getNgramsOfLength(3);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

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
					
					if (text.endsWith(nodeb.getSymbol()))
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

	private DefaultTableModel createTableModelWithSecondColumnLabel(String label)
	{
	  DefaultTableModel model = new DefaultTableModel();
	  model.addColumn(MainProperties.TABLE_ROWA);
	  model.addColumn(label);
	  model.addColumn(MainProperties.TABLE_ROWC);
	  return model;
	}
	
	private DefaultTableModel createTableModelForConditionalNgrams()
	{
	  return createTableModelWithSecondColumnLabel("Condition");
	}
}
