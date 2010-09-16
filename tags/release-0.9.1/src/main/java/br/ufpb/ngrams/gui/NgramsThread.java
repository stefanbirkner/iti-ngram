package br.ufpb.ngrams.gui;

import static java.util.Arrays.sort;
import static java.util.Collections.sort;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufpb.ngrams.ConditionalNGram;
import br.ufpb.ngrams.MainProperties;
import br.ufpb.ngrams.NGramCounter;
import br.ufpb.ngrams.NgramAnalyzer;
import br.ufpb.ngrams.Probability;
import br.ufpb.ngrams.SortConditionalNGramsByProbabilityDescending;
import br.ufpb.ngrams.SortNGramCountersByCountsDescending;

public class NgramsThread extends Thread
{
  private static final Font FIXED_WIDTH_FONT = new Font( "Monospaced", Font.PLAIN, 12 );
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

		StatusBar.getInstance().getProgressBar().setMaximum(unigramCounters.length);

    List<ConditionalNGram> conditionalNGrams = new ArrayList<ConditionalNGram>();
    for (int i = 0; i < unigramCounters.length; i++)
    {
      String unigram = unigramCounters[i].getNGram();
      for (NGramCounter unigramCounter : unigramCounters)
      {
        String bigram = unigram + unigramCounter.getNGram();
        NGramCounter bigramCounter = getCounterWithNGram(bigramCounters, bigram);
        if (bigramCounter != null)
        {
          ConditionalNGram conditionalNGram = new ConditionalNGram(unigramCounter, bigramCounter);
          conditionalNGrams.add(conditionalNGram);
        }
      }
      StatusBar.getInstance().getProgressBar().setValue(i + 1);
    }

    DefaultTableModel tableModel = createTableForConditionalNGrams(conditionalNGrams);
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

		StatusBar.getInstance().getProgressBar().setMaximum(bigramCounters.length);
		
		List<ConditionalNGram> conditionalNGrams = new ArrayList<ConditionalNGram>();
		for (int i = 0; i < bigramCounters.length; i++)
		{
			for (NGramCounter unigramCounter : unigramCounters)
			{
			  String unigram = unigramCounter.getNGram();
				String trigram = bigramCounters[i].getNGram() + unigram;
				NGramCounter trigramCounter = getCounterWithNGram(trigramCounters, trigram);
				if (trigramCounter != null)
				{
				  ConditionalNGram conditionalNGram = new ConditionalNGram(bigramCounters[i], trigramCounter);
				  conditionalNGrams.add(conditionalNGram);
				}
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}

    DefaultTableModel tableModel = createTableForConditionalNGrams(conditionalNGrams);
		StatusBar.getInstance().setMessage("Generating conditional bigram output...");
		addTableWithLabel(tableModel, "Conditional Bigram");
	}

	private DefaultTableModel createTableForConditionalNGrams(List<ConditionalNGram> conditionalNGrams)
	{
    DefaultTableModel tableModel = createTableModelForConditionalNgrams();
	  sort(conditionalNGrams, new SortConditionalNGramsByProbabilityDescending());
	  int position = 1;
	  for (ConditionalNGram conditionalNGram : conditionalNGrams)
	  {
	    String text = conditionalNGram.getAppendedCharacters() + " after " + conditionalNGram.getBaseNGram();
	    Object[] rowData = { position, text, conditionalNGram.getProbability() };
	    tableModel.addRow(rowData);
	    ++position;
	  }
	  return tableModel;
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

	private void addTableWithLabel(DefaultTableModel model, String label)
	{
		JTable table = new JTable(model);
		table.setFont(FIXED_WIDTH_FONT);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(label, scroll);
	}
}
