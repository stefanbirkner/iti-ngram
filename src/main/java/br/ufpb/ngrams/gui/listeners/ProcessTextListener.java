package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

import br.ufpb.ngrams.gui.CreateStatisticsThread;
import br.ufpb.ngrams.gui.OutputPanel;
import br.ufpb.ngrams.gui.StatusBar;

public class ProcessTextListener implements ActionListener
{
  private final JTextArea textArea;
  
  public ProcessTextListener(JTextArea textArea)
  {
    this.textArea = textArea;
  }

	public void actionPerformed(ActionEvent e)
	{
    String text = textArea.getText();
    JTabbedPane panel = OutputPanel.getInstance().getTabbedPane();
    CreateStatisticsThread thread = new CreateStatisticsThread(text, panel, StatusBar.getInstance());
		thread.start();
	}
}
