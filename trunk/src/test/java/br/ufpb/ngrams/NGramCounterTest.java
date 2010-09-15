package br.ufpb.ngrams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NGramCounterTest
{
  @Test
  public void shouldHaveAmountOneAfterFirstIncrementing()
  {
    NGramCounter counter = new NGramCounter("dummy text");
    counter.incrementCountByOne();
    assertEquals("Wrong amount.", 1, counter.getCount());
  }
  
  @Test
  public void shouldConcatenateNgramAndFrequency() {
    NGramCounter counter = new NGramCounter("dummy text ");
    assertEquals("Wrong toString() value.", "'dummy text ' (0 times)", counter.toString());
  }
}
