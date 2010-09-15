package br.ufpb.ngrams;

import junit.framework.TestCase;

public class ProbabilityTest extends TestCase
{
	public void testgetProbability()
	{
		NGramCounter[] counters = new NGramCounter[] {
		  createCounterWithCount(1),
		  createCounterWithCount(3),
		  createCounterWithCount(1),
		  createCounterWithCount(1)
		};
		
		assertEquals(0.5f, Probability.getProbability(counters, 1));
	}
	
	public void testGetSymbolsAmount()
	{
	  NGramCounter[] counters = new NGramCounter[] {
		  createCounterWithCount(1),
		  createCounterWithCount(1),
		  createCounterWithCount(1)
	  };
		
		assertEquals(3, Probability.getSymbolsAmount(counters));
	}
	
	private NGramCounter createCounterWithCount(int count)
	{
		NGramCounter counter = new NGramCounter("");
		for (int i = 0; i < count; ++i) {
			counter.incrementCountByOne();
		}
		return counter;
	}
}
