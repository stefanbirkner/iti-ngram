package br.ufpb.ngrams.gui;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.Text;

public class ValidateTextThread extends Thread
{
	@Override
	public void run()
	{
		StatusBar.getInstance().setMessage("Processing...");
		
		String input = ContentPanel.getTextArea().getText();
		Text text = new Text(input, Attributes.getInstance());
		ContentPanel.getTextArea().setText(text.process());
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
}
