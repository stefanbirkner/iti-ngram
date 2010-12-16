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
}
