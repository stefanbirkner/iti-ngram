package br.ufpb.ngrams;

import java.util.Comparator;

public class SortConditionalNGramsByProbabilityDescending implements Comparator<ConditionalNGram>
{

  @Override
  public int compare(ConditionalNGram conditionalNGram1, ConditionalNGram conditionalNGram2) {
    float probability1 = conditionalNGram1.getProbability();
    float probability2 = conditionalNGram2.getProbability();
    if (probability1 < probability2)
    {
      return 1;
    }
    else if (probability1 == probability2)
    {
      return 0;
    }
    else
    {
      return -1;
    }
  }
}
