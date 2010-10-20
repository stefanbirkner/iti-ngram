package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class AttributesTest extends TestCase
{
	public void testGetSetAllTrue()
	{
		Attributes attributes = new Attributes();
		attributes.setAttributes(true, true, true, true, true, true, true);
		
		assertTrue(attributes.isConvertDowncase());
		assertTrue(attributes.isIgnoreDigits());
		assertTrue(attributes.isIgnoreLetters());
		assertTrue(attributes.isIgnoreSymbols());
		assertTrue(attributes.isIgnoreConsecutive());
		assertTrue(attributes.isIgnoreWhitespaces());
		assertTrue(attributes.isMergeWhitespaces());
	}

	public void testGetSetAllFalse()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, false, false, false, false);
		
		assertFalse(attributes.isConvertDowncase());
		assertFalse(attributes.isIgnoreDigits());
		assertFalse(attributes.isIgnoreLetters());
		assertFalse(attributes.isIgnoreSymbols());
		assertFalse(attributes.isIgnoreConsecutive());
		assertFalse(attributes.isIgnoreWhitespaces());
		assertFalse(attributes.isMergeWhitespaces());
	}

	public void testGetSetAttributesTrue()
	{
    Attributes attributes = new Attributes();
		attributes.setIgnoreLetters(true);
		attributes.setIgnoreDigits(true);
		attributes.setIgnoreSymbols(true);
		attributes.setIgnoreWhitespaces(true);
		attributes.setIgnoreConsecutive(true);
		attributes.setMergeWhitespaces(true);
		attributes.setConvertDowncase(true);
		
		assertTrue(attributes.isConvertDowncase());
		assertTrue(attributes.isIgnoreDigits());
		assertTrue(attributes.isIgnoreLetters());
		assertTrue(attributes.isIgnoreSymbols());
		assertTrue(attributes.isIgnoreConsecutive());
		assertTrue(attributes.isIgnoreWhitespaces());
		assertTrue(attributes.isMergeWhitespaces());
	}

	public void testGetSetAttributesFalse()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, false, false, false, false);
		
		assertFalse(attributes.isConvertDowncase());
		assertFalse(attributes.isIgnoreDigits());
		assertFalse(attributes.isIgnoreLetters());
		assertFalse(attributes.isIgnoreSymbols());
		assertFalse(attributes.isIgnoreConsecutive());
		assertFalse(attributes.isIgnoreWhitespaces());
		assertFalse(attributes.isMergeWhitespaces());
	}
	
	public void testGetSetIgnoredCharacter()
	{
		List<Character> list = new ArrayList<Character>();
		list.add(new Character('a'));
		list.add(new Character('b'));
		list.add(new Character('c'));

    Attributes attributes = new Attributes();
		attributes.setIgnoredCharacters(list);
		
		assertEquals(3, list.size());
		assertEquals(new Character('a'), attributes.getIgnoredCharacters().get(0));
		assertEquals(new Character('b'), attributes.getIgnoredCharacters().get(1));
		assertEquals(new Character('c'), attributes.getIgnoredCharacters().get(2));
	}
}
