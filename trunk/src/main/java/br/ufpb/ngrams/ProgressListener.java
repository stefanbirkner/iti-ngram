package br.ufpb.ngrams;

public interface ProgressListener {
  void startForNumberOfSteps(int n);
  void nextStep();
}
