package br.ufpb.ngrams.gui;

import javax.swing.JTextArea;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.Text;

public class ValidateTextThread extends Thread
{
  private final JTextArea textArea;
  
	public ValidateTextThread(JTextArea textArea)
  {
    this.textArea = textArea;
  }

  @Override
	public void run()
	{
		StatusBar.getInstance().setMessage("Processing...");
		
		String input = textArea.getText();
		Text text = new Text(input, Attributes.getInstance());
		textArea.setText(text.process());
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
}
