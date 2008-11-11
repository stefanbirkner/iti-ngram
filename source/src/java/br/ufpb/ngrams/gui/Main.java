package br.ufpb.ngrams.gui;

import br.ufpb.ngrams.MainProperties;

public class Main
{
	public static void main(String[] args)
	{
		java.awt.EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				String title = String.format("%s - %s", MainProperties.MAINWINDOW_TITLE, MainProperties.VERSION);
				int width  = Integer.parseInt(MainProperties.MAINWINDOW_WIDTH);
				int height = Integer.parseInt(MainProperties.MAINWINDOW_HEIGHT);
				new MainFrame(title, width, height).setVisible(true);
			}
		});
	}
}
