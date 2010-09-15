package br.ufpb.ngrams.gui;

import static java.util.Arrays.sort;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufpb.ngrams.MainProperties;
import br.ufpb.ngrams.NGramCounter;
import br.ufpb.ngrams.NgramAnalyzer;
import br.ufpb.ngrams.Probability;
import br.ufpb.ngrams.SortNGramCountersByCountsDescending;

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
			NGramCounter[] counters = analyzer.getNgramsOfLength(n);
			sort(counters, new SortNGramCountersByCountsDescending());
			
			String labelOfSecondColumn = String.format(MainProperties.TABLE_ROWB, n);
			DefaultTableModel tableModel = createTableModelWithSecondColumnLabel(labelOfSecondColumn);
			
			for (int j = 0; j < counters.length; j++)
			{
			  String probability = String.valueOf(Probability.getProbability(counters, j));
				tableModel.addRow(new String[] {String.valueOf(j), counters[j].toString(), probability});
			}
			
			String labelOfTable = String.format("%s-gram", n);
			addTableWithLabel(tableModel, labelOfTable);
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
		NGramCounter[] ngrama = analyzer.getNgramsOfLength(1);
		NGramCounter[] ngramb = analyzer.getNgramsOfLength(2);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

		StatusBar.getInstance().getProgressBar().setMaximum(ngrama.length);
		
		int count = 0;
		for (int i = 0; i < ngrama.length; i++)
		{
			NGramCounter nodeb = ngrama[i];
			for (NGramCounter nodea : ngrama)
			{
        String nGram = nodeb.getNGram() + nodea.getNGram();
        NGramCounter referenceCounter = getCounterWithNGram(ngramb, nGram);
				
				float probability = 0;
				
				if (referenceCounter != null)
				{
					int amounta = nodea.getCount();
					int amountb = referenceCounter.getCount();
					if (text.endsWith(nodea.getNGram())) { amounta--; };
					if (amounta < 1) { probability = 0; }
					else { probability = (float) amountb / amounta; }
				}
				
				tableModel.addRow(new String[] {
					String.valueOf(count++),
					String.format("P(%s|%s)", nodea.getNGram(), nodeb.getNGram()),
					String.valueOf(probability)});
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}

		StatusBar.getInstance().setMessage("Generating conditional unigram output...");
		addTableWithLabel(tableModel, "Conditional Unigram");
	}
	
	public void conditionalBigram(String text)
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		NGramCounter[] ngrama = analyzer.getNgramsOfLength(1);
		NGramCounter[] ngramb = analyzer.getNgramsOfLength(2);
		NGramCounter[] ngramc = analyzer.getNgramsOfLength(3);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

		StatusBar.getInstance().getProgressBar().setMaximum(ngramb.length);
		
		int count = 0;
		for (int i = 0; i < ngramb.length; i++)
		{
			NGramCounter nodeb = ngramb[i];
			for (NGramCounter nodea : ngrama)
			{
				String nGram = nodeb.getNGram() + nodea.getNGram();
				NGramCounter referenceCounter = getCounterWithNGram(ngramc, nGram);
				
				float probability = 0;
				
				if (referenceCounter != null)
				{
					int amountb = nodeb.getCount();
					int amountc = referenceCounter.getCount();
					
					if (text.endsWith(nodeb.getNGram()))
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
									String.format("P(%s|%s)", nodea.getNGram(), nodeb.getNGram()),
									String.valueOf(probability)
								 });
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		StatusBar.getInstance().setMessage("Generating conditional bigram output...");
		addTableWithLabel(tableModel, "Conditional Bigram");
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
	
	private void addTableWithLabel(DefaultTableModel model, String label)
	{
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(label, scroll);
	}
	
	private NGramCounter getCounterWithNGram(NGramCounter[] counters, String nGram)
	{
	  for (int i = 0; i < counters.length; ++i)
	  {
	    if (counters[i].getNGram().equals(nGram))
	    {
	      return counters[i];
	    }
	  }
	  return null;
	}
}
