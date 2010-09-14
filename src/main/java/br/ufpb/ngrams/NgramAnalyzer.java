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

	public List<Node> getNgramsOfLength(int n)
	{
		if (n < 1 || text == null)
		{
			return emptyList();
		}
		else
		{
			List<Node> ngrams = calculateNgramsOfLength(n);
			sortNgrams(ngrams);
			return ngrams;
		}
	}
	
	private List<Node> calculateNgramsOfLength(int n)
	{
		List<Node> nodes = new ArrayList<Node>();
		int length = text.length();
		for (int i = 0; i < length - n + 1; i++)
		{
			String ngram = text.substring(i, i + n);
			Node node = createOrFindNodeWithNgram(nodes, ngram);
			node.incrementAmountByOne();
		}
		return nodes;
	}
	
	private Node createOrFindNodeWithNgram(List<Node> existingNodes, String ngram)
	{
		Node node = new Node(ngram);
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

	private void sortNgrams(List<Node> ngrams)
	{
			Collections.sort(ngrams);
			Collections.reverse(ngrams);
	}
}
