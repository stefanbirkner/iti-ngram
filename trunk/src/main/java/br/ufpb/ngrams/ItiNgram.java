package br.ufpb.ngrams;

import br.ufpb.ngrams.gui.MainFrame;

public class ItiNgram
{
	public static void main(String[] args)
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				String title = String.format("%s - %s", MainProperties.MAINWINDOW_TITLE, MainProperties.VERSION);
				new MainFrame(title).setVisible(true);
			}
		});
	}
}
