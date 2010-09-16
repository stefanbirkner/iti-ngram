package br.ufpb.ngrams;

import javax.swing.JProgressBar;

import br.ufpb.ngrams.gui.StatusBar;

public class Text
{
	private String content;
	private Attributes attributes;
	
	public Text(String content, Attributes attributes)
	{
		this.content = content;
		this.attributes = attributes;
	}
	
	public String process()
	{
		return Text.process(this.content, this.attributes);
	}
	
	public static boolean isValid(char ch, Attributes attributes)
	{
		if (attributes.getIgnoredCharacters().contains(new Character(ch)) ||
				Character.isLetter(ch) && attributes.isIgnoreLetters() ||
				Character.isDigit(ch) && attributes.isIgnoreDigits() ||
				Character.isWhitespace(ch) && attributes.isIgnoreWhitespaces())
		{
			return false;
		}
		else if (attributes.isIgnoreSymbols() &&
				!Character.isLetter(ch) && !Character.isDigit(ch) &&
				!Character.isWhitespace(ch))
		{
			return false;
		}
		return true;
	}
	
	public static String process(String input, Attributes attributes)
	{
		StringBuilder output = new StringBuilder();
	
		JProgressBar progress = StatusBar.getInstance().getProgressBar();
		progress.setMaximum(input.length());
		
		char ch;
		for (int i = 0; i < input.length(); i++)
		{			
			ch = input.charAt(i);
			
			if (isValid(ch, attributes))
			{
				if (attributes.isIgnoreConsecutive())
				{
					int tempa = i + 1;
					int tempb = i + 2;
					if (i + 2 < input.length() && ch == input.charAt(tempa) && ch == input.charAt(tempb))
					{
						while (i < input.length() && ch == input.charAt(i)) { i++; }
						i--;
						continue;
					}
				}
				if (Character.isLetter(ch) && attributes.isConvertDowncase()) { ch = Character.toLowerCase(ch); }
				if (Character.isWhitespace(ch) && attributes.isMergeWhitespaces())
				{
					ch = ' ';
					int temp = output.length() - 1;
					if (temp > -1 && output.charAt(temp) == ' '){ continue; }
				}
				output.append(ch);
			}

			progress.setValue(i + 1);
		}
		
		return output.toString();
	}
}
