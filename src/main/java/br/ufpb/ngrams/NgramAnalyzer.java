package br.ufpb.ngrams;

import static java.util.Collections.emptyList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NgramAnalyzer
{
	private final String text;
	
	public NgramAnalyzer(String text) {
		this.text = text;
	}

	public List<NGramCounter> getNgramsOfLength(int n)
	{
		if (n < 1 || text == null)
		{
			return emptyList();
		}
		else
		{
			List<NGramCounter> ngrams = calculateNgramsOfLength(n);
			sortNgrams(ngrams);
			return ngrams;
		}
	}
	
	private List<NGramCounter> calculateNgramsOfLength(int n)
	{
		List<NGramCounter> nodes = new ArrayList<NGramCounter>();
		int length = text.length();
		for (int i = 0; i < length - n + 1; i++)
		{
			String ngram = text.substring(i, i + n);
			NGramCounter node = createOrFindNodeWithNgram(nodes, ngram);
			node.incrementCountByOne();
		}
		return nodes;
	}
	
	private NGramCounter createOrFindNodeWithNgram(List<NGramCounter> existingNodes, String ngram)
	{
		NGramCounter node = new NGramCounter(ngram);
		if (existingNodes.contains(node))
		{
			int index = existingNodes.indexOf(node);
			return existingNodes.get(index);
		}
		else
		{
			existingNodes.add(node);
			return node;
		}
	}

	private void sortNgrams(List<NGramCounter> ngrams)
	{
			Collections.sort(ngrams);
			Collections.reverse(ngrams);
	}
}
