package br.ufpb.ngrams;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import br.ufpb.ngrams.Attributes;

public class AttributesTest extends TestCase
{
	public void testGetInstance()
	{
		Attributes attriba = Attributes.getInstance();
		Attributes attribb = Attributes.getInstance();
		assertSame(attriba, attribb);
	}
	
	public void testGetSetAllTrue()
	{
		Attributes instance = Attributes.getInstance();
		instance.setAttributes(true, true, true, true, true, true, true);
		
		assertTrue(instance.isConvertDowncase());
		assertTrue(instance.isIgnoreDigits());
		assertTrue(instance.isIgnoreLetters());
		assertTrue(instance.isIgnoreSymbols());
		assertTrue(instance.isIgnoreConsecutive());
		assertTrue(instance.isIgnoreWhitespaces());
		assertTrue(instance.isMergeWhitespaces());
	}
	
	public void testGetSetAllFalse()
	{
		Attributes instance = Attributes.getInstance();
		instance.setAttributes(false, false, false, false, false, false, false);
		
		assertFalse(instance.isConvertDowncase());
		assertFalse(instance.isIgnoreDigits());
		assertFalse(instance.isIgnoreLetters());
		assertFalse(instance.isIgnoreSymbols());
		assertFalse(instance.isIgnoreConsecutive());
		assertFalse(instance.isIgnoreWhitespaces());
		assertFalse(instance.isMergeWhitespaces());
	}

	public void testGetSetAttributesTrue()
	{
		Attributes instance = Attributes.getInstance();
		instance.setIgnoreLetters(true);
		instance.setIgnoreDigits(true);
		instance.setIgnoreSymbols(true);
		instance.setIgnoreWhitespaces(true);
		instance.setIgnoreConsecutive(true);
		instance.setMergeWhitespaces(true);
		instance.setConvertDowncase(true);
		
		assertTrue(instance.isConvertDowncase());
		assertTrue(instance.isIgnoreDigits());
		assertTrue(instance.isIgnoreLetters());
		assertTrue(instance.isIgnoreSymbols());
		assertTrue(instance.isIgnoreConsecutive());
		assertTrue(instance.isIgnoreWhitespaces());
		assertTrue(instance.isMergeWhitespaces());
	}

	public void testGetSetAttributesFalse()
	{
		Attributes attributes = Attributes.getInstance();
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
		
		Attributes instance = Attributes.getInstance();
		instance.setIgnoredCharacters(list);
		
		assertEquals(3, list.size());
		assertEquals(new Character('a'), instance.getIgnoredCharacters().get(0));
		assertEquals(new Character('b'), instance.getIgnoredCharacters().get(1));
		assertEquals(new Character('c'), instance.getIgnoredCharacters().get(2));
	}
}
