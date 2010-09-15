package br.ufpb.ngrams;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

public class NgramAnalyzerTest
{
	@Test
	public void shouldReturnUnigrams()
	{
		NgramAnalyzer analyzer = createAnalyzerForAbcd();
		List<NGramCounter> unigrams = analyzer.getNgramsOfLength(1);
		assertCountersForNgrams(unigrams, "d", "c", "b", "a");
	}

	@Test
	public void shouldReturnBigrams()
	{
		NgramAnalyzer analyzer = createAnalyzerForAbcd();
		List<NGramCounter> bigrams = analyzer.getNgramsOfLength(2);
		assertCountersForNgrams(bigrams, "cd", "bc", "ab");
	}

	@Test
	public void shouldReturnTrigrams()
	{
		NgramAnalyzer analyzer = createAnalyzerForAbcd();
		List<NGramCounter> trigrams = analyzer.getNgramsOfLength(3);
		assertCountersForNgrams(trigrams, "bcd", "abc");
	}
	
	private NgramAnalyzer createAnalyzerForAbcd() {
		return new NgramAnalyzer("abcd");
	}
	
	private void assertCountersForNgrams(List<NGramCounter> counters, String... expectedNGrams)
	{
	  assertEquals("Wrong number of counters.", expectedNGrams.length, counters.size());
		for (NGramCounter counter: counters)
		{
		  String nGram = counter.getNGram();
		  boolean nGramHasBeenExpected = false;
		  for (String nGramUnderTest : expectedNGrams)
		  {
		    if (nGramUnderTest.equals(nGram))
		    {
		      nGramHasBeenExpected = true;
		      break;
		    }
		  }
		  
		  assertTrue("N-gram '" + nGram + "' has not been expected.", nGramHasBeenExpected);
		}
	}
}
