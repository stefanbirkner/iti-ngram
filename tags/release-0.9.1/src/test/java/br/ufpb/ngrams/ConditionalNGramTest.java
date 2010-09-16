package br.ufpb.ngrams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ConditionalNGramTest {
  @Test
  public void shouldCalculateProbability()
  {
    NGramCounter count2Counter = createCounterWithCount(2);
    NGramCounter count4Counter = createCounterWithCount(4);
    ConditionalNGram conditionalNGram = new ConditionalNGram(count4Counter, count2Counter);
    assertProbability(conditionalNGram, 0.5f);
  }

  @Test
  public void shouldCalculateProbabilityZeroIfCounterHasCountZero()
  {
    NGramCounter count2Counter = createCounterWithCount(2);
    NGramCounter count0Counter = createCounterWithCount(0);
    ConditionalNGram conditionalNGram = new ConditionalNGram(count0Counter, count2Counter);
    assertProbability(conditionalNGram, 0.0f);
  }

  @Test
  public void shouldCalculateProbabilityWhenCountIsIncrementedAfterwards()
  {
    NGramCounter count2Counter = createCounterWithCount(2);
    NGramCounter count4Counter = createCounterWithCount(4);
    ConditionalNGram conditionalNGram = new ConditionalNGram(count4Counter, count2Counter);
    count2Counter.incrementCountByOne();
    assertProbability(conditionalNGram, 0.75f);
  }

  private void assertProbability(ConditionalNGram conditionalNGram, float expectedProbability) {
    assertEquals("Wrong probability.", expectedProbability, conditionalNGram.getProbability(), 0.01);
  }
  
  private static NGramCounter createCounterWithCount(int count)
  {
    NGramCounter counter  = new NGramCounter("");
    for (int i = 0; i < count; ++i)
    {
      counter.incrementCountByOne();
    }
    
    return counter;
  }
}
