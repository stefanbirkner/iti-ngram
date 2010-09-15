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
  private final String text;
  
  public NgramsThread(String text)
  {
    this.text = text;
  }
  
	@Override
	public void run()
	{
		super.run();
		
		OutputPanel.getInstance().getTabbedPane().removeAll();
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
		
		conditionalUnigram();
		conditionalBigram();
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
	
	private void conditionalUnigram()
	{
		StatusBar.getInstance().setMessage("Generating conditional unigram...");

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		NGramCounter[] unigramCounters = analyzer.getNgramsOfLength(1);
		NGramCounter[] bigramCounters = analyzer.getNgramsOfLength(2);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

		StatusBar.getInstance().getProgressBar().setMaximum(unigramCounters.length);
		
		int count = 0;
		for (int i = 0; i < unigramCounters.length; i++)
		{
			String unigram = unigramCounters[i].getNGram();
			for (NGramCounter unigramCounter : unigramCounters)
			{
        String bigram = unigram + unigramCounter.getNGram();
        NGramCounter bigramCounter = getCounterWithNGram(bigramCounters, bigram);
        float probability = getProbabilityThatFirstNGramIsPrefixOfSecondNGram(
            unigramCounter, bigramCounter);
        tableModel.addRow(new String[] {
					String.valueOf(count++),
					String.format("P(%s|%s)", unigramCounter.getNGram(), unigram),
					String.valueOf(probability)});
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}

		StatusBar.getInstance().setMessage("Generating conditional unigram output...");
		addTableWithLabel(tableModel, "Conditional Unigram");
	}
	
	private void conditionalBigram()
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		NGramCounter[] unigramCounters = analyzer.getNgramsOfLength(1);
		NGramCounter[] bigramCounters = analyzer.getNgramsOfLength(2);
		NGramCounter[] trigramCounters = analyzer.getNgramsOfLength(3);

		DefaultTableModel tableModel = createTableModelForConditionalNgrams();

		StatusBar.getInstance().getProgressBar().setMaximum(bigramCounters.length);
		
		int count = 0;
		for (int i = 0; i < bigramCounters.length; i++)
		{
			for (NGramCounter nodea : unigramCounters)
			{
			  String unigram = nodea.getNGram();
				String trigram = bigramCounters[i].getNGram() + unigram;
				NGramCounter trigramCounter = getCounterWithNGram(trigramCounters, trigram);
				float probability = getProbabilityThatFirstNGramIsPrefixOfSecondNGram(
				    bigramCounters[i], trigramCounter);
				tableModel.addRow(new String[] {
									String.valueOf(count++),
									String.format("P(%s|%s)", unigram, bigramCounters[i].getNGram()),
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
	
	private float getProbabilityThatFirstNGramIsPrefixOfSecondNGram(NGramCounter prefixCounter, NGramCounter secondCounter)
	{
    if (secondCounter == null)
    {
      return 0;
    }
    else
    {
        int prefixCount = prefixCounter.getCount();
        if (text.endsWith(prefixCounter.getNGram()))
        {
          --prefixCount;
        }
        
        return (prefixCount == 0) ? 0 : (float) secondCounter.getCount() / prefixCount;
    }
	}

	private void addTableWithLabel(DefaultTableModel model, String label)
	{
		JTable table = new JTable(model);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(label, scroll);
	}
}
