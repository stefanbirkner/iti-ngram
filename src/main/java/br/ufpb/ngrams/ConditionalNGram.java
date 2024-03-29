package br.ufpb.ngrams;

public class ConditionalNGram {
  private final NGramCounter counter, counterOfExtendedNGram;

  public ConditionalNGram(NGramCounter counter, NGramCounter counterOfExtendedNGram)
  {
    this.counter = counter;
    this.counterOfExtendedNGram = counterOfExtendedNGram;
  }
  
  public String getBaseNGram()
  {
    return counter.getNGram();
  }
  
  public String getAppendedCharacters()
  {
    int beginOfAppendedCharacters = getBaseNGram().length();
    return counterOfExtendedNGram.getNGram().substring(beginOfAppendedCharacters);
  }

  public float getProbability()
  {
    if (counterOfExtendedNGram == null)
    {
      return 0;
    }
    else
    {
        int prefixCount = counter.getCount();
        return (prefixCount == 0) ? 0 : (float) counterOfExtendedNGram.getCount() / prefixCount;
    }
  }
}
