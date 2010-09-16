package br.ufpb.ngrams;

import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SortConditionalNGramsByProbabilityDescendingTest {
  private static final NGramCounter COUNTER_WITH_COUNT_2 = createCounterWithCount(2);
  private static final NGramCounter COUNTER_WITH_COUNT_3 = createCounterWithCount(5);
  private static final NGramCounter COUNTER_WITH_COUNT_5 = createCounterWithCount(5);

  @Test
  public void shouldSort60PercentBefore40Percent()
  {
    List<ConditionalNGram> counters = new ArrayList<ConditionalNGram>();
    ConditionalNGram conditional40Percent = new ConditionalNGram(COUNTER_WITH_COUNT_5, COUNTER_WITH_COUNT_2);
    counters.add(conditional40Percent);
    ConditionalNGram conditional60Percent = new ConditionalNGram(COUNTER_WITH_COUNT_5, COUNTER_WITH_COUNT_3);
    counters.add(conditional60Percent);
    
    sort(counters, new SortConditionalNGramsByProbabilityDescending());

    assertEquals("Wrong element at position 0.", conditional60Percent, counters.get(0));
    assertEquals("Wrong element at position 1.", conditional40Percent, counters.get(1));
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
