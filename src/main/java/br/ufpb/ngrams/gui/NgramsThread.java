package br.ufpb.ngrams.gui;

import static java.util.Arrays.sort;
import static java.util.Collections.sort;

import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.ufpb.ngrams.ConditionalNGram;
import br.ufpb.ngrams.ConditionalNGramFactory;
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
		createTablesForNGrams(analyzer);
		createTablesForConditionalNGrams(analyzer);
		
		StatusBar.getInstance().setMessage("Process complete!");
	}

  private void createTablesForConditionalNGrams(NgramAnalyzer analyzer) {
    ConditionalNGramFactory factory = new ConditionalNGramFactory(analyzer);
    createTableForConditionalNGramsOfBaseLength(1, factory);
    createTableForConditionalNGramsOfBaseLength(2, factory);
  }

  private void createTablesForNGrams(NgramAnalyzer analyzer) {
    StatusBar.getInstance().setMessage("Generating n-gram reports...");
		StatusBar.getInstance().getProgressBar().setMaximum(3);
		for (int length = 1; length < 4; length++)
		{
			createTableForNGramsOfLength(length, analyzer);
			StatusBar.getInstance().getProgressBar().setValue(length);
		}
  }

  private void createTableForNGramsOfLength(int length, NgramAnalyzer analyzer) {
    NGramCounter[] counters = analyzer.getNgramsOfLength(length);
    sort(counters, new SortNGramCountersByCountsDescending());
    
    String labelOfSecondColumn = String.format(MainProperties.TABLE_ROWB, length);
    DefaultTableModel tableModel = createTableModelWithSecondColumnLabel(labelOfSecondColumn);
    
    for (int i = 0; i < counters.length; i++)
    {
      String probability = String.valueOf(Probability.getProbability(counters, i));
    	tableModel.addRow(new String[] {String.valueOf(i), counters[i].toString(), probability});
    }
    
    String labelOfTable = String.format("%s-gram", length);
    addTableWithLabel(tableModel, labelOfTable);
  }
	
	private void createTableForConditionalNGramsOfBaseLength(int lengthOfBaseNGram, ConditionalNGramFactory factory)
	{
    String label  =  (lengthOfBaseNGram + 1) + "-gram";
		StatusBar.getInstance().setMessage("Generating conditional " + label + "s ...");
		ProgressBarProgressListener progressListener = new ProgressBarProgressListener(StatusBar.getInstance().getProgressBar());
		List<ConditionalNGram> conditionalNGrams = factory.getConditionalNGrams(lengthOfBaseNGram, progressListener);

    DefaultTableModel tableModel = createTableForConditionalNGrams(conditionalNGrams);
		StatusBar.getInstance().setMessage("Generating conditional " + label + " output ...");
		addTableWithLabel(tableModel, "Conditional " + label);
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

	private void addTableWithLabel(DefaultTableModel model, String label)
	{
		JTable table = new JTable(model);
		table.setFont(FIXED_WIDTH_FONT);

		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().add(table);
		OutputPanel.getInstance().getTabbedPane().add(label, scroll);
	}
}
