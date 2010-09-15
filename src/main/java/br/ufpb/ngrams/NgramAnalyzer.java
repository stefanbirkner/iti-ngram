package br.ufpb.ngrams;

import java.util.HashMap;
import java.util.Map;

public class NgramAnalyzer
{
	private final String text;
	
	public NgramAnalyzer(String text) {
		this.text = text;
	}

	public NGramCounter[] getNgramsOfLength(int n)
	{
		if (n < 1 || text == null)
		{
			return new NGramCounter[0];
		}
		else
		{
			return calculateNgramsOfLength(n);
		}
	}
	
	private NGramCounter[] calculateNgramsOfLength(int n)
	{
		Map<String, NGramCounter> countersForNGrams = new HashMap<String, NGramCounter>();
		int length = text.length();
		for (int i = 0; i < length - n + 1; i++)
		{
			String nGram = text.substring(i, i + n);
			NGramCounter counters = createOrFindNodeWithNgram(countersForNGrams, nGram);
			counters.incrementCountByOne();
		}
		return countersForNGrams.values().toArray(new NGramCounter[countersForNGrams.size()]);
	}
	
	private NGramCounter createOrFindNodeWithNgram(Map<String, NGramCounter> countersForNGrams, String nGram)
	{
	  NGramCounter counter = countersForNGrams.get(nGram);
	  if (counter == null)
	  {
	    counter = new NGramCounter(nGram);
	    countersForNGrams.put(nGram, counter);
	  }
	  return counter;
	}
}
