package br.ufpb.ngrams;

import static javax.swing.JFrame.EXIT_ON_CLOSE;
import br.ufpb.ngrams.gui.MainFrame;

public class ItiNgram
{
  public static void main(String[] args)
  {
    String title = String.format("%s - %s", MainProperties.MAINWINDOW_TITLE, MainProperties.VERSION);
    MainFrame f = new MainFrame(title);
    f.setDefaultCloseOperation(EXIT_ON_CLOSE); 
    f.setVisible(true);
  }
}
