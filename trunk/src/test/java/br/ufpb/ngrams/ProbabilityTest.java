package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class ProbabilityTest extends TestCase
{
	public void testgetProbability()
	{
		List<Node> nodes = new ArrayList<Node>();
		
		nodes.add(new Node(new String(), 1));
		nodes.add(new Node(new String(), 3));
		nodes.add(new Node(new String(), 1));
		nodes.add(new Node(new String(), 1));
		
		assertEquals(0.5f, Probability.getProbability(nodes, 1));
	}
	
	public void testGetSymbolsAmount()
	{
		List<Node> nodes = new ArrayList<Node>();
		
		nodes.add(new Node(new String(), 1));
		nodes.add(new Node(new String(), 1));
		nodes.add(new Node(new String(), 1));
		
		assertEquals(3, Probability.getSymbolsAmount(nodes));
	}
}
