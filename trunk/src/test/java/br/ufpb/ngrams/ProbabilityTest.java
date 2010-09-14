package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ProbabilityTest extends TestCase
{
	public void testgetProbability()
	{
		List<Node> nodes = new ArrayList<Node>();
		
		addNodeWithAmount(nodes, 1);
		addNodeWithAmount(nodes, 3);
		addNodeWithAmount(nodes, 1);
		addNodeWithAmount(nodes, 1);
		
		assertEquals(0.5f, Probability.getProbability(nodes, 1));
	}
	
	public void testGetSymbolsAmount()
	{
		List<Node> nodes = new ArrayList<Node>();
		
		addNodeWithAmount(nodes, 1);
		addNodeWithAmount(nodes, 1);
		addNodeWithAmount(nodes, 1);
		
		assertEquals(3, Probability.getSymbolsAmount(nodes));
	}
	
	private void addNodeWithAmount(List<Node> nodes, int amount) {
		Node node = new Node("");
		for (int i = 0; i < amount; ++i) {
			node.incrementAmountByOne();
		}
		
		nodes.add(node);
	}
}
