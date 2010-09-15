package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.ufpb.ngrams.gui.StatusBar;

public abstract class Probability
{
	public static float getProbability(List<NGramCounter> NGramCounters, int index)
	{
		NGramCounter NGramCounter = NGramCounters.get(index);
		return (float) NGramCounter.getCount() / getSymbolsAmount(NGramCounters);
	}
	
	public static long getSymbolsAmount(List<NGramCounter> NGramCounters)
	{
		long amount = 0;
		
		for (NGramCounter NGramCounter : NGramCounters)
		{
			amount += NGramCounter.getCount();
		}
		
		return amount;
	}
	
	public static float getConditionalProbability(NGramCounter NGramCountera, NGramCounter NGramCounterb, boolean end)
	{
		int amounta = 0;
		int amountb = NGramCounterb.getCount();
		
		if (end) { amountb--; }
		if (amountb < 1) { return 0; }
		
		if (NGramCounterb.getNGram().endsWith(NGramCounterb.getNGram()))
		{
			amounta = amountb;
		}
		
		return amounta / amountb;
	}
	
	public static List<ConditionalNode> getConditionalUnigram(List<List<NGramCounter>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional unigram...");
		
		List<NGramCounter> ngrama = ngrams.get(0);
		List<NGramCounter> ngramb = ngrams.get(1);
		
		List<ConditionalNode> NGramCounters = new ArrayList<ConditionalNode>();

		StatusBar.getInstance().getProgressBar().setMaximum(ngrama.size());
		
		for (int i = 0; i < ngrama.size(); i++)
		{
			NGramCounter NGramCounterb = ngrama.get(i);
			for (NGramCounter NGramCountera : ngrama)
			{
				NGramCounter referenceNGramCounter = new NGramCounter(NGramCounterb.getNGram() + NGramCountera.getNGram());
				int referenceIndex = ngramb.indexOf(referenceNGramCounter);
				
				float probability = 0;
				
				if (referenceIndex < 0) {
					probability = 0;
				}
				else
				{
					referenceNGramCounter = ngramb.get(referenceIndex);
					int amounta = NGramCountera.getCount();
					int amountb = referenceNGramCounter.getCount();
					
					if (content.endsWith(NGramCountera.getNGram())) {
						amounta--;
					};
					
					if (amounta < 1) {
						probability = 0;
					}
					else {
						probability = (float) amountb / amounta;
					}
				}
				
				NGramCounters.add(new ConditionalNode(NGramCountera.getNGram(), NGramCounterb.getNGram(), probability));
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		Collections.sort(NGramCounters);
		Collections.reverse(NGramCounters);
		
		return NGramCounters;
	}
	
	public static List<ConditionalNode> getConditionalBigram(List<List<NGramCounter>> ngrams, String content)
	{
		StatusBar.getInstance().setMessage("Generating conditional bigram...");
		
		List<NGramCounter> ngrama = ngrams.get(0);
		List<NGramCounter> ngramb = ngrams.get(1);
		List<NGramCounter> ngramc = ngrams.get(2);
		
		List<ConditionalNode> NGramCounters = new ArrayList<ConditionalNode>();

		StatusBar.getInstance().getProgressBar().setMaximum(ngramb.size());
		
		for (int i = 0; i < ngramb.size(); i++)
		{
			NGramCounter NGramCounterb = ngramb.get(i);
			for (NGramCounter NGramCountera : ngrama)
			{
				NGramCounter referenceNGramCounter = new NGramCounter(NGramCounterb.getNGram() + NGramCountera.getNGram());
				int referenceIndex = ngramc.indexOf(referenceNGramCounter);
				
				float probability = 0;
				
				if (referenceIndex < 0) {
					probability = 0;
				}
				else
				{
					referenceNGramCounter = ngramc.get(referenceIndex);
					int amountb = NGramCounterb.getCount();
					int amountc = referenceNGramCounter.getCount();
					
					if (content.endsWith(NGramCounterb.getNGram())) {
						amountb--;
					};
					
					if (amountb < 1) {
						probability = 0;
					}
					else {
						probability = (float) amountc / amountb;
					}
				}
				
				NGramCounters.add(new ConditionalNode(NGramCountera.getNGram(), NGramCounterb.getNGram(), probability));
			}
			StatusBar.getInstance().getProgressBar().setValue(i + 1);
		}
		
		Collections.sort(NGramCounters);
		Collections.reverse(NGramCounters);
		
		return NGramCounters;
	}
}
