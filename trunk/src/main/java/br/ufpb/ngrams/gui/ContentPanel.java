package br.ufpb.ngrams.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ContentPanel extends JPanel
{
  private static final long serialVersionUID = 1093998575730432280L;

  private final JTextArea textArea = new JTextArea();

  public ContentPanel()
  {
    setLayout(new GridLayout(1, 1));
    createScrollPane();
  }

  private void createScrollPane()
  {
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setViewportView(textArea);
    add(scrollPane);
  }

  public JTextArea getTextArea()
  {
    return textArea;
  }
}
