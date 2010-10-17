package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import br.ufpb.ngrams.gui.ValidateTextThread;

public class ValidateTextListener implements ActionListener
{
  private final JTextArea textArea;
  
  public ValidateTextListener(JTextArea textArea)
  {
    this.textArea = textArea;
  }

	public void actionPerformed(ActionEvent event)
	{
		ValidateTextThread t = new ValidateTextThread(textArea);
		t.start();
	}
}
