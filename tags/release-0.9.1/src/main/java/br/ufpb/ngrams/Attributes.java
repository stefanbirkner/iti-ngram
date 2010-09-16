package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

public class Attributes
{
	private static Attributes instance = null;
	
	private List<Character> ignoredCharacters;

	private boolean ignoreDigits;
	private boolean ignoreLetters;
	private boolean ignoreWhitespaces;
	private boolean ignoreSymbols;
	private boolean mergeWhitespaces;
	private boolean ignoreConsecutive;
	private boolean convertDowncase;
	
	private Attributes()
	{
		this.ignoredCharacters = new ArrayList<Character>();
	}
	
	public static Attributes getInstance()
	{
		if (instance == null)
		{
			instance = new Attributes();
		}
		return instance;
	}
	
	public void setAttributes(boolean ignoreDigits, boolean ignoreLetters, boolean ignoreWhitespaces, boolean ignoreSymbols, boolean mergeWhiteSpaces, boolean mergeConsecutive, boolean convertDownCase)
	{
		this.ignoreDigits = ignoreDigits;
		this.ignoreLetters = ignoreLetters;
		this.ignoreWhitespaces = ignoreWhitespaces;
		this.ignoreSymbols = ignoreSymbols;
		this.mergeWhitespaces = mergeWhiteSpaces;
		this.ignoreConsecutive = mergeConsecutive;
		this.convertDowncase = convertDownCase;
	}

	public List<Character> getIgnoredCharacters() { return ignoredCharacters; }
	public boolean isIgnoreLetters() { return ignoreLetters; }
	public boolean isIgnoreDigits() { return ignoreDigits; }
	public boolean isIgnoreSymbols() { return ignoreSymbols; }
	public boolean isIgnoreWhitespaces() { return ignoreWhitespaces; }
	public boolean isMergeWhitespaces() { return mergeWhitespaces; }
	public boolean isIgnoreConsecutive() { return ignoreConsecutive; }
	public boolean isConvertDowncase() { return convertDowncase; }

	public void setIgnoredCharacters(List<Character> ignoredCharacters) { this.ignoredCharacters = ignoredCharacters; }
	public void setIgnoreLetters(boolean ignoreLetters) { this.ignoreLetters = ignoreLetters; }
	public void setIgnoreDigits(boolean ignoreDigits) { this.ignoreDigits = ignoreDigits; }
	public void setIgnoreWhitespaces(boolean ignoreWhiteSpaces) { this.ignoreWhitespaces = ignoreWhiteSpaces; }
	public void setIgnoreSymbols(boolean ignoreSymbols) { this.ignoreSymbols = ignoreSymbols; }
	public void setMergeWhitespaces(boolean mergeWhitespaces) { this.mergeWhitespaces = mergeWhitespaces; }
	public void setIgnoreConsecutive(boolean ignoreConsecutive) { this.ignoreConsecutive = ignoreConsecutive; }
	public void setConvertDowncase(boolean convertDownCase) { this.convertDowncase = convertDownCase; }
}
