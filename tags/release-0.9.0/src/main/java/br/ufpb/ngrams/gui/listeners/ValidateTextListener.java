package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufpb.ngrams.gui.ValidateTextThread;

public class ValidateTextListener implements ActionListener
{
	public void actionPerformed(ActionEvent arg0)
	{
		ValidateTextThread t = new ValidateTextThread();
		t.start();
	}
}
