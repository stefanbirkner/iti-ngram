package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ConditionalNGramFactory {
  private final NgramAnalyzer analyzer;

  public ConditionalNGramFactory(NgramAnalyzer analyzer) {
    this.analyzer = analyzer;
  }
  
  public List<ConditionalNGram> getConditionalNGrams(int lengthOfBaseNGram, ProgressListener progressListener) {
    NGramCounter[] baseCounters = analyzer.getNgramsOfLength(lengthOfBaseNGram);
    NGramCounter[] extendedCounters = analyzer.getNgramsOfLength(lengthOfBaseNGram + 1);
    
    return createConditionalNGrams(baseCounters, extendedCounters, progressListener);
  }

  private List<ConditionalNGram> createConditionalNGrams(NGramCounter[] baseCounters,
      NGramCounter[] extendedCounters, ProgressListener progressListener) {
    progressListener.startForNumberOfSteps(baseCounters.length);
    ArrayList<ConditionalNGram> conditionalNGrams = new ArrayList<ConditionalNGram>();
    for (NGramCounter baseCounter:baseCounters)
    {
      Collection<ConditionalNGram> additionalNGrams = createConditionalNGramsForBaseNGram(baseCounter, extendedCounters);
      conditionalNGrams.addAll(additionalNGrams);
      progressListener.nextStep();
    }
    return conditionalNGrams;
  }

  private Collection<ConditionalNGram> createConditionalNGramsForBaseNGram(NGramCounter baseCounter, NGramCounter[] extendedCounters) {
    Collection<ConditionalNGram> conditionalNGrams = new ArrayList<ConditionalNGram>();
    String baseNGram = baseCounter.getNGram();
    for (NGramCounter extendedCounter : extendedCounters)
    {
      String extendedNGram = extendedCounter.getNGram();
      if (extendedNGram.startsWith(baseNGram))
      {
        ConditionalNGram conditionalNGram = new ConditionalNGram(baseCounter, extendedCounter);
        conditionalNGrams.add(conditionalNGram);
      }
    }
    return conditionalNGrams;
  }
}
