package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ProbabilityTest extends TestCase
{
	public void testgetProbability()
	{
		List<NGramCounter> counters = new ArrayList<NGramCounter>();
		
		addCounterWithAmount(counters, 1);
		addCounterWithAmount(counters, 3);
		addCounterWithAmount(counters, 1);
		addCounterWithAmount(counters, 1);
		
		assertEquals(0.5f, Probability.getProbability(counters, 1));
	}
	
	public void testGetSymbolsAmount()
	{
		List<NGramCounter> counters = new ArrayList<NGramCounter>();
		
		addCounterWithAmount(counters, 1);
		addCounterWithAmount(counters, 1);
		addCounterWithAmount(counters, 1);
		
		assertEquals(3, Probability.getSymbolsAmount(counters));
	}
	
	private void addCounterWithAmount(List<NGramCounter> counters, int amount)
	{
		NGramCounter counter = new NGramCounter("");
		for (int i = 0; i < amount; ++i) {
			counter.incrementCountByOne();
		}
		
		counters.add(counter);
	}
}
