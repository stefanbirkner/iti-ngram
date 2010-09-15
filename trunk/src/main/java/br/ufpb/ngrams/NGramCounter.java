package br.ufpb.ngrams;

public class NGramCounter implements Comparable<NGramCounter>
{
	private final String nGram;
	private int count = 0;
	
	public NGramCounter(String nGram)
	{
		this.nGram = nGram;
	}

	public int getCount()
	{
		return count;
	}
	
	void incrementCountByOne()
	{
		++count;
	}

	public String getNGram()
	{
		return nGram;
	}

	public int compareTo(NGramCounter node)
	{
		if (node.getCount() < getCount()) { return  1; }
		if (node.getCount() > getCount()) { return -1; }
		return 0;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if ((o instanceof NGramCounter) && ((NGramCounter)o).getNGram().equals(getNGram())) { return true; }
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return nGram.hashCode();
	}
	
	@Override
	public String toString()
	{
		return String.format("'%s' (%s times)", getNGram(), getCount());
	}
}
