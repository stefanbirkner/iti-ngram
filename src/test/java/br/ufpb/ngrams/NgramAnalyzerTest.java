package br.ufpb.ngrams;

import static org.junit.Assert.assertArrayEquals;

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
	
	private void assertCountersForNgrams(List<NGramCounter> counters, String... expectedNgrams)
	{
		String[] symbols = new String[counters.size()];
		for (int i = 0; i < counters.size(); ++i)
		{
			symbols[i] = counters.get(i).getNGram();
		}

		assertArrayEquals("Wrong ngrams.", expectedNgrams, symbols);
	}
}
