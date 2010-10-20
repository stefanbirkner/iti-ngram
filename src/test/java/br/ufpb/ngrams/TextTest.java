package br.ufpb.ngrams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TextTest
{
  @Test
	public void shouldNotChangeTextWhenNoAttributeIsSelected()
	{
    Attributes attributes = new Attributes();
    attributes.setAttributes(false, false, false, false, false, false, false);
		Text text = new Text("abc", attributes);
		assertEquals("abc", text.process());
	}

  @Test
	public void testProcessStringIgnoreDigits()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(true, false, false, false, false, false, false);
		
		assertEquals("",   process("1234567890",   attributes));
		assertEquals("",   process("0987654321",   attributes));
		assertEquals(" ",  process(" 1234567890",  attributes));
		assertEquals(" ",  process("1234567890 ",  attributes));
		assertEquals("a",  process("a1234567890",  attributes));
		assertEquals("b",  process("1234567890b",  attributes));
		assertEquals("  ", process(" 1234567890 ", attributes));
		assertEquals("ab", process("a1234567890b", attributes));
	}

  @Test
	public void testProcessStringIgnoreLetters()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, true, false, false, false, false, false);
		
		assertEquals("",   process("abcdefghijklmnopqrstuvxwz",   attributes));
		assertEquals("",   process("abcdefghijklmnopqrstuvxwz",   attributes));
		assertEquals(" ",  process(" abcdefghijklmnopqrstuvxwz",  attributes));
		assertEquals(" ",  process("abcdefghijklmnopqrstuvxwz ",  attributes));
		assertEquals("1",  process("1abcdefghijklmnopqrstuvxwz",  attributes));
		assertEquals("2",  process("abcdefghijklmnopqrstuvxwz2",  attributes));
		assertEquals("  ", process(" abcdefghijklmnopqrstuvxwz ", attributes));
		assertEquals("12", process("1abcdefghijklmnopqrstuvxwz2", attributes));
		assertEquals("12", process("abcdefghijk12lmnopqrstuvxwz", attributes));
		assertEquals("12", process("abcde1fghijklmnop2qrstuvxwz", attributes));
	}

  @Test
	public void testProcessStringIgnoreWhitespaces()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, true, false, false, false, false);
		
		String abc = "abc";
		assertEquals(abc, process("abc",     attributes));
		assertEquals(abc, process("abc ",    attributes));
		assertEquals(abc, process("abc  ",   attributes));
		assertEquals(abc, process(" abc",    attributes));
		assertEquals(abc, process("  abc",   attributes));
		assertEquals(abc, process(" abc ",   attributes));
		assertEquals(abc, process("  abc  ", attributes));
		assertEquals(abc, process(" a b c ", attributes));
	}

  @Test
	public void testProcessStringIgnoreSymbols()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, true, false, false, false);
		
		String abc = "abc";
		assertEquals(abc, process("!@#$%^&*()_+-={}|[]\\:',./<>?~`abc", attributes));
		assertEquals(abc, process("abc!@#$%^&*()_+-={}|[]\\:',./<>?~`", attributes));
		assertEquals(abc, process("!@#$%^&*()_+-={}|[]\\:',./<>?~`abc", attributes));
		assertEquals(abc, process("!@#$%^a&*()_+-=b{}|[]\\:',.c/<>?~`", attributes));
		assertEquals("",  process("!@#$%^&*()_+-=^^^^{}|[]\\:',./<>?~", attributes));
	}

  @Test
	public void testProcessStringIgnoreConsecute()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, false, false, true, false);
		
		assertEquals("abc", process("============abc", attributes));
		assertEquals("abc", process("abc============", attributes));
		assertEquals("abc", process("===a===b===c===", attributes));
		assertEquals("bc" , process("=======b===c===", attributes));
	}

  @Test
	public void testProcessStringMergeWhitespaces()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, false, true, false, false);
		
		assertEquals(" ",    process(" ",      attributes));
		assertEquals(" ",    process("  ",     attributes));
		assertEquals("a b",  process("a b",    attributes));
		assertEquals("a b",  process("a  b",   attributes));
		assertEquals(" a b", process(" a b",   attributes));
		assertEquals(" a b", process("   a b", attributes));
		assertEquals("a b ", process("a b   ", attributes));
		assertEquals("a b ", process("a   b ", attributes));
	}

  @Test
	public void testProcessStringMergeWhitespacesIgnoreSymbols()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, false, false, true, true, false, false);

		assertEquals(" ", process(" ! ",   attributes));
		assertEquals(" ", process(" % ! ", attributes));
	}

  @Test
	public void testProcessStringMergeWhitespacesIgnoreLetter()
	{
    Attributes attributes = new Attributes();
		attributes.setAttributes(false, true, false, false, true, false, false);

		assertEquals(" ", process(" a ",   attributes));
		assertEquals(" ", process(" a b ", attributes));
	}
	
	private String process(String content, Attributes attributes)
	{
	  Text text = new Text(content, attributes);
	  return text.process();
	}
}
