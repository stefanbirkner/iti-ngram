package br.ufpb.ngrams.gui;

import javax.swing.JTextArea;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.Text;

public class ValidateTextThread extends Thread
{
  private final JTextArea textArea;
  private final Attributes configuration;
  
	public ValidateTextThread(JTextArea textArea, Attributes configuration)
  {
    this.textArea = textArea;
    this.configuration = configuration;
  }

  @Override
	public void run()
	{
		StatusBar.getInstance().setMessage("Processing...");
		
		String input = textArea.getText();
		Text text = new Text(input, configuration);
		textArea.setText(text.process());
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
}
