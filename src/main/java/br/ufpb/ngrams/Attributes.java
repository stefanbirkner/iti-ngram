package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

public class Attributes
{
  public List<Character> ignoredCharacters = new ArrayList<Character>();
  public boolean ignoreDigits;
  public boolean ignoreLetters;
  public boolean ignoreWhitespaces;
  public boolean ignoreSymbols;
  public boolean mergeWhitespaces;
  public boolean ignoreConsecutive;
  public boolean convertDowncase;

  public void setAttributes(boolean ignoreDigits, boolean ignoreLetters, boolean ignoreWhitespaces,
      boolean ignoreSymbols, boolean mergeWhiteSpaces, boolean mergeConsecutive,
      boolean convertDownCase)
  {
    this.ignoreDigits = ignoreDigits;
    this.ignoreLetters = ignoreLetters;
    this.ignoreWhitespaces = ignoreWhitespaces;
    this.ignoreSymbols = ignoreSymbols;
    this.mergeWhitespaces = mergeWhiteSpaces;
    this.ignoreConsecutive = mergeConsecutive;
    this.convertDowncase = convertDownCase;
  }
}
