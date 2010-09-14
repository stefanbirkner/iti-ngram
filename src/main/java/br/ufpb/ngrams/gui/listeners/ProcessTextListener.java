package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufpb.ngrams.gui.NgramsThread;

public class ProcessTextListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		NgramsThread thread = new NgramsThread();
		thread.start();
	}
}
