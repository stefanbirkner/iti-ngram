package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.gui.ValidateTextThread;

public class ValidateTextListener implements ActionListener
{
  private final JTextArea textArea;
  private final Attributes configuration;
  
  public ValidateTextListener(JTextArea textArea, Attributes configuration)
  {
    this.textArea = textArea;
    this.configuration = configuration;
  }

	public void actionPerformed(ActionEvent event)
	{
		ValidateTextThread t = new ValidateTextThread(textArea, configuration);
		t.start();
	}
}
