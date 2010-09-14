package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JProgressBar;

import br.ufpb.ngrams.gui.StatusBar;

public class Ngrams
{
	public static List<List<Node>> getNgrams(String content, int n)
	{
		StatusBar.getInstance().setMessage("Processing characters...");
		
		List<List<Node>> ngrams = new ArrayList<List<Node>>();
		
		if (n < 1 || content == null)
		{
			return ngrams;
		}
		
		for (int i = 1; i <= n; i++)
		{
			List<Node> nodes = new ArrayList<Node>();
			ngrams.add(nodes);
		}
		
		int length = content.length();
		
		JProgressBar progress = StatusBar.getInstance().getProgressBar();
		progress.setMaximum(length);
		
		for (int i = 0; i < length; i++)
		{
			for (int j = 0; j < n; j++)
			{
				String symbol = null;
				
				if (i + j < length)
				{
					symbol = content.substring(i, i + (j + 1));
					
					Node node = new Node(symbol, 0);
					
					List<Node> ngram = ngrams.get(j);
					int index = ngram.indexOf(node);
					
					if (index < 0)
					{
						ngram.add(node);
					}
					else
					{
						node = ngram.get(index);
					}
					node.incrementAmountByOne();
				}
			}
			progress.setValue(i + 1);
		}
		
		for (int i = 0; i < n; i++)
		{
			Collections.sort(ngrams.get(i));
			Collections.reverse(ngrams.get(i));
		}
		
		return ngrams;
	}
}
