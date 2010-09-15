package br.ufpb.ngrams;

import static java.util.Collections.sort;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;


public class SortNGramCountersByCountsDescendingTest {
  private static final NGramCounter COUNTER_WITH_COUNT_2 = createCounterWithCount(2);
  private static final NGramCounter COUNTER_WITH_COUNT_5 = createCounterWithCount(5);

  @Test
  public void shouldSortCount5BeforeCount2()
  {
    List<NGramCounter> counters = new ArrayList<NGramCounter>();
    counters.add(COUNTER_WITH_COUNT_2);
    counters.add(COUNTER_WITH_COUNT_5);
    
    sort(counters, new SortNGramCountersByCountsDescending());

    assertEquals("Wrong element at position 0.", COUNTER_WITH_COUNT_5, counters.get(0));
    assertEquals("Wrong element at position 1.", COUNTER_WITH_COUNT_2, counters.get(1));
  }
  
  private static NGramCounter createCounterWithCount(int count)
  {
    NGramCounter counter  = new NGramCounter("");
    for (int i = 0; i < 2; ++i)
    {
      counter.incrementCountByOne();
    }
    
    return counter;
  }
}
