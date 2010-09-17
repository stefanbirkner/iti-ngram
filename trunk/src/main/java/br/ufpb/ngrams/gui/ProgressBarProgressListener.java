package br.ufpb.ngrams.gui;

import javax.swing.JProgressBar;

import br.ufpb.ngrams.ProgressListener;


public class ProgressBarProgressListener implements ProgressListener {
  private final JProgressBar progressBar;
  
  public ProgressBarProgressListener(JProgressBar progressBar) {
    this.progressBar = progressBar;
  }

  @Override
  public void startForNumberOfSteps(int n)
  {
    progressBar.setValue(0);
    progressBar.setMaximum(n);
  }

  @Override
  public void nextStep()
  {
    int nextValues = progressBar.getValue() + 1;
    progressBar.setValue(nextValues);
  }
}
