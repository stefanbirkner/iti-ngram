package br.ufpb.ngrams;

import br.ufpb.ngrams.gui.StatusBar;

public class Text
{
	private final String content;
	private final Attributes attributes;
	
	public Text(String content, Attributes attributes)
	{
		this.content = content;
		this.attributes = attributes;
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
	
	public String process()
	{
		StringBuilder output = new StringBuilder();
		StatusBar.getInstance().startForNumberOfSteps(content.length());
		
		for (int i = 0; i < content.length(); i++)
		{			
			char ch = content.charAt(i);
			
			if (isValid(ch, attributes))
			{
				if (attributes.isIgnoreConsecutive())
				{
					int tempa = i + 1;
					int tempb = i + 2;
					if (i + 2 < content.length() && ch == content.charAt(tempa) && ch == content.charAt(tempb))
					{
						while (i < content.length() && ch == content.charAt(i)) { i++; }
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

			StatusBar.getInstance().nextStep();
		}
		
		return output.toString();
	}
}
