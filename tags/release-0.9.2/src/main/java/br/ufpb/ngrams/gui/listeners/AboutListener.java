package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufpb.ngrams.gui.AboutDialog;

public class AboutListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		new AboutDialog();
	}
}