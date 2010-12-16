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
		if (attributes.ignoredCharacters.contains(ch) ||
				Character.isLetter(ch) && attributes.ignoreLetters ||
				Character.isDigit(ch) && attributes.ignoreDigits ||
				Character.isWhitespace(ch) && attributes.ignoreWhitespaces)
		{
			return false;
		}
		else if (attributes.ignoreSymbols &&
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
				if (attributes.ignoreConsecutive)
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
				if (Character.isLetter(ch) && attributes.convertDowncase) { ch = Character.toLowerCase(ch); }
				if (Character.isWhitespace(ch) && attributes.mergeWhitespaces)
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
