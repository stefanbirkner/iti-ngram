package br.ufpb.ngrams;


public abstract class Probability
{
	public static float getProbability(NGramCounter[] counters, int index)
	{
		return (float) counters[index].getCount() / getSymbolsAmount(counters);
	}
	
	public static long getSymbolsAmount(NGramCounter[] counters)
	{
		long amount = 0;
		for (NGramCounter counter : counters)
		{
			amount += counter.getCount();
		}
		return amount;
	}
}
