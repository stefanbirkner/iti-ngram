package br.ufpb.ngrams.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContentPanel extends JPanel
{
  private static final long serialVersionUID = 1093998575730432279L;

  private static ContentPanel instance = null;
  private final JTextArea textArea = new JTextArea();

  private ContentPanel()
  {
    setLayout(new GridLayout(1, 1));
    createScrollPane();
  }

  public static ContentPanel getInstance()
  {
    if (instance == null)
    {
      instance = new ContentPanel();
    }
    return instance;
  }

  private void createScrollPane()
  {
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setViewportView(textArea);
    add(scrollPane);
  }

  public static JTextArea getTextArea()
  {
    return getInstance().textArea;
  }
}
