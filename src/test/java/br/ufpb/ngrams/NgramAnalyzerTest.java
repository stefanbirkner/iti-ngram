package br.ufpb.ngrams;

import java.util.List;

import junit.framework.TestCase;

public class NgramAnalyzerTest extends TestCase
{
	public void testNgrams()
	{
	}

	public void testGetNgrams()
	{
		String content = "abcd";
		
		List<List<Node>> ngrams = NgramAnalyzer.getNgrams(content, 3);
		
		List<Node> unigram = ngrams.get(0);
		List<Node> bigram  = ngrams.get(1);
		List<Node> trigram = ngrams.get(2);
		
		assertEquals("d", unigram.get(0).getSymbol());
		assertEquals("c", unigram.get(1).getSymbol());
		assertEquals("b", unigram.get(2).getSymbol());
		assertEquals("a", unigram.get(3).getSymbol());
		
		assertEquals("cd", bigram.get(0).getSymbol());
		assertEquals("bc", bigram.get(1).getSymbol());
		assertEquals("ab", bigram.get(2).getSymbol());
		
		assertEquals("bcd", trigram.get(0).getSymbol());
		assertEquals("abc", trigram.get(1).getSymbol());
	}
}
