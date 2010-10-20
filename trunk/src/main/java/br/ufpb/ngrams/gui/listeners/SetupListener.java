package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.gui.SetupDialog;

public class SetupListener implements ActionListener
{
  private final Attributes configuration;

  public SetupListener(Attributes configuration)
  {
    this.configuration = configuration;
  }

  public void actionPerformed(ActionEvent arg0)
	{
		new SetupDialog(configuration);
	}
}
