package br.ufpb.ngrams;

import java.util.Comparator;

public class SortNGramCountersByCountsDescending implements Comparator<NGramCounter>
{
	@Override
	public int compare(NGramCounter counter1, NGramCounter counter2)
	{
	  int count1 = counter1.getCount();
	  int count2 = counter2.getCount();
	  if (count1 < count2)
	  {
	    return 1;
	  }
	  else if (count1 == count2)
	  {
	    return 0;
	  }
	  else
	  {
	    return -1;
	  }
	}
}
