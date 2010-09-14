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
		List<Node> unigrams = analyzer.getNgramsOfLength(1);
		assertNgramsAre(unigrams, "d", "c", "b", "a");
	}

	@Test
	public void shouldReturnBigrams()
	{
		NgramAnalyzer analyzer = createAnalyzerForAbcd();
		List<Node> bigrams = analyzer.getNgramsOfLength(2);
		assertNgramsAre(bigrams, "cd", "bc", "ab");
	}

	@Test
	public void shouldReturnTrigrams()
	{
		NgramAnalyzer analyzer = createAnalyzerForAbcd();
		List<Node> trigrams = analyzer.getNgramsOfLength(3);
		assertNgramsAre(trigrams, "bcd", "abc");
	}
	
	private NgramAnalyzer createAnalyzerForAbcd() {
		return new NgramAnalyzer("abcd");
	}
	
	private void assertNgramsAre(List<Node> ngrams, String... expectedNgrams)
	{
		String[] symbols = new String[ngrams.size()];
		for (int i = 0; i < ngrams.size(); ++i)
		{
			symbols[i] = ngrams.get(i).getSymbol();
		}

		assertArrayEquals("Wrong ngrams.", expectedNgrams, symbols);
	}
}
