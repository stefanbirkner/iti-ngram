package br.ufpb.ngrams.gui.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;

public class OpenFileListener implements ActionListener
{
  private final JTextArea textArea;
  
	public OpenFileListener(JTextArea textArea)
	{
    this.textArea = textArea;
  }

  public void actionPerformed(ActionEvent e)
	{
		JFileChooser fileChooser = new JFileChooser();
		if (fileChooser.showOpenDialog(fileChooser) == JFileChooser.APPROVE_OPTION)
		{
		  File file = fileChooser.getSelectedFile();
		  readFile(file);
		}
	}
  
  private void readFile(File file)
  {
    try
    {
      FileReader reader = new FileReader(file);
      textArea.read(reader, null);
    }
    catch (IOException exception)
    {
      System.err.println(exception.getMessage());
      exception.printStackTrace();
    }
    
  }
}
