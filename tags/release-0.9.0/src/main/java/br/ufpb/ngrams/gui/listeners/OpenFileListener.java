package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFileChooser;

import br.ufpb.ngrams.FileUtil;
import br.ufpb.ngrams.gui.ContentPanel;

public class OpenFileListener implements ActionListener
{
	public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				String content = FileUtil.readTextFile(fileChooser.getSelectedFile());
				ContentPanel.getInstance().getTextArea().setText(content);
			}
			catch (IOException exception)
			{
				System.err.println(exception.getMessage());
				exception.printStackTrace();
			}
		}
	}
}
