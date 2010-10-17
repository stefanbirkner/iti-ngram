package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;

import br.ufpb.ngrams.gui.ContentPanel;
import br.ufpb.ngrams.gui.CreateStatisticsThread;
import br.ufpb.ngrams.gui.OutputPanel;
import br.ufpb.ngrams.gui.StatusBar;

public class ProcessTextListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
    String text = ContentPanel.getTextArea().getText();
    JTabbedPane panel = OutputPanel.getInstance().getTabbedPane();
    CreateStatisticsThread thread = new CreateStatisticsThread(text, panel, StatusBar.getInstance());
		thread.start();
	}
}
