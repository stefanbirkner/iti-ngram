package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufpb.ngrams.gui.StatusBar;

public abstract class Probability
{
	public static float getProbability(List<Node> nodes, int index)
	{
		Node node = nodes.get(index);
		return (float) node.getAmount() / getSymbolsAmount(nodes);
	}
	
	public static long getSymbolsAmount(List<Node> nodes)
	{
		long amount = 0;
		
		for (Node node : nodes)
		{
			amount += node.getAmount();
		}
		
		return amount;
	}
	
	public static float getConditionalProbability(Node nodea, Node nodeb, boolean end)
	{
		int amounta = 0;
		int amountb = nodeb.getAmount();
		
		if (end) { amountb--; }
		if (amountb < 1) { return 0; }
		
		if (nodeb.getSymbol().endsWith(nodeb.getSymbol()))
		{
			amounta = amountb;
		}
		
		return amounta / amountb;
	}
	
	public static List<ConditionalNode> getConditionalUnigram(List<List<Node>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional unigram...");
		
		List<Node> ngrama = ngrams.get(0);
		List<Node> ngramb = ngrams.get(1);
		
		List<ConditionalNode> nodes = new ArrayList<ConditionalNode>();

		StatusBar.getInstance().getProgressBar().setMaximum(ngrama.size());
		
		for (int i = 0; i < ngrama.size(); i++)
		{
			Node nodeb = ngrama.get(i);
			for (Node nodea : ngrama)
			{
				Node referenceNode = new Node(nodeb.getSymbol() + nodea.getSymbol(), 0);
				int referenceIndex = ngramb.indexOf(referenceNode);
				
				float probability = 0;
				
				if (referenceIndex < 0) {
					probability = 0;
				}
				else
				{
					referenceNode = ngramb.get(referenceIndex);
					int amounta = nodea.getAmount();
					int amountb = referenceNode.getAmount();
					
					if (content.endsWith(nodea.getSymbol())) {
						amounta--;
					};
					
					if (amounta < 1) {
						probability = 0;
					}
					else {
						probability = (float) amountb / amounta;
					}
				}
				
				nodes.add(new ConditionalNode(nodea.getSymbol(), nodeb.getSymbol(), probability));
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		Collections.sort(nodes);
		Collections.reverse(nodes);
		
		return nodes;
	}
	
	public static List<ConditionalNode> getConditionalBigram(List<List<Node>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");
		
		List<Node> ngrama = ngrams.get(0);
		List<Node> ngramb = ngrams.get(1);
		List<Node> ngramc = ngrams.get(2);
		
		List<ConditionalNode> nodes = new ArrayList<ConditionalNode>();

		StatusBar.getInstance().getProgressBar().setMaximum(ngramb.size());
		
		for (int i = 0; i < ngramb.size(); i++)
		{
			Node nodeb = ngramb.get(i);
			for (Node nodea : ngrama)
			{
				Node referenceNode = new Node(nodeb.getSymbol() + nodea.getSymbol(), 0);
				int referenceIndex = ngramc.indexOf(referenceNode);
				
				float probability = 0;
				
				if (referenceIndex < 0) {
					probability = 0;
				}
				else
				{
					referenceNode = ngramc.get(referenceIndex);
					int amountb = nodeb.getAmount();
					int amountc = referenceNode.getAmount();
					
					if (content.endsWith(nodeb.getSymbol())) {
						amountb--;
					};
					
					if (amountb < 1) {
						probability = 0;
					}
					else {
						probability = (float) amountc / amountb;
					}
				}
				
				nodes.add(new ConditionalNode(nodea.getSymbol(), nodeb.getSymbol(), probability));
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		Collections.sort(nodes);
		Collections.reverse(nodes);
		
		return nodes;
	}
}
