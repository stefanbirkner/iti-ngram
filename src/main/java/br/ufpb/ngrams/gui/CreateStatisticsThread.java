package br.ufpb.ngrams.gui;

import static java.util.Arrays.sort;
import static java.util.Collections.sort;

import java.awt.Font;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
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

public class CreateStatisticsThread extends Thread
{
  private static final Font FIXED_WIDTH_FONT = new Font( "Monospaced", Font.PLAIN, 12 );
  private final String text;
  private final JTabbedPane panel;
  private final StatusBar statusBar;
  
  public CreateStatisticsThread(String text, JTabbedPane panel, StatusBar statusBar)
  {
    this.text = text;
    this.panel = panel;
    this.statusBar = statusBar;
  }
  
	@Override
	public void run()
	{
		panel.removeAll();

		NgramAnalyzer analyzer = new NgramAnalyzer(text);
		createTablesForNGrams(analyzer);
		createTablesForConditionalNGrams(analyzer);
		
		setStatusBarMessage("Process complete!");
	}

  private void createTablesForNGrams(NgramAnalyzer analyzer) {
    setStatusBarMessage("Generating n-gram reports...");
    statusBar.startForNumberOfSteps(3);
    for (int length = 1; length < 4; length++)
    {
      createTableForNGramsOfLength(length, analyzer);
      statusBar.nextStep();
    }
  }

  private void createTablesForConditionalNGrams(NgramAnalyzer analyzer) {
    ConditionalNGramFactory factory = new ConditionalNGramFactory(analyzer);
    createTableForConditionalNGramsOfBaseLength(1, factory);
    createTableForConditionalNGramsOfBaseLength(2, factory);
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
    
    String labelOfTable = getLabelForNGramOfLength(length);
    addTableWithLabel(tableModel, labelOfTable);
  }
	
	private void createTableForConditionalNGramsOfBaseLength(int lengthOfBaseNGram, ConditionalNGramFactory factory)
	{
    String label = getLabelForNGramOfLength(lengthOfBaseNGram + 1);
    setStatusBarMessage("Generating conditional " + label + "s ...");
		List<ConditionalNGram> conditionalNGrams = factory.getConditionalNGrams(lengthOfBaseNGram, statusBar);

    DefaultTableModel tableModel = createTableForConditionalNGrams(conditionalNGrams);
    setStatusBarMessage("Generating conditional " + label + " output ...");
		addTableWithLabel(tableModel, "Conditional " + label);
	}

	private String getLabelForNGramOfLength(int length)
	{
	  return length + "-gram";
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
		panel.add(label, scroll);
	}

  private void setStatusBarMessage(String message) {
    statusBar.setMessage(message);
  }
}
