package br.ufpb.ngrams.gui;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.Text;

public class ValidateTextThread extends Thread
{
	@Override
	public void run()
	{
		super.run();
		
		StatusBar.getInstance().setMessage("Processing...");
		
		String input = ContentPanel.getInstance().getTextArea().getText();
		String output = Text.process(input, Attributes.getInstance());
		ContentPanel.getInstance().getTextArea().setText(output);
		
		StatusBar.getInstance().setMessage("Process complete!");
	}
}
