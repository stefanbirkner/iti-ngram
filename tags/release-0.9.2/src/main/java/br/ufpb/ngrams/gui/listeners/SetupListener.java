package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufpb.ngrams.gui.SetupDialog;

public class SetupListener implements ActionListener
{
	public void actionPerformed(ActionEvent arg0)
	{
		new SetupDialog();
	}
}
