package br.ufpb.ngrams.gui;

import javax.swing.JDialog;

public class AboutDialog extends JDialog
{
	private static final long serialVersionUID = -8360027268912593681L;

	public AboutDialog()
	{
		super.setSize(350, 250);
		super.setModal(true);
		WindowUtil.centralize(this);
		super.setVisible(true);
	}
}
