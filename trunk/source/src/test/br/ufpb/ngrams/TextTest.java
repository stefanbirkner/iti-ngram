package br.ufpb.ngrams;

import junit.framework.TestCase;
import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.Text;

public class TextTest extends TestCase
{
	public void testConstructor()
	{
		String content = "abc";
		Attributes.getInstance().setAttributes(false, false, false, false, false, false, false);
		Text text = new Text(content, Attributes.getInstance());
		assertEquals(content, text.process());
	}
	
	public void testProcessStringIgnoreDigits()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(true, false, false, false, false, false, false);
		
		assertEquals("",   Text.process("1234567890",   attributes));
		assertEquals("",   Text.process("0987654321",   attributes));
		assertEquals(" ",  Text.process(" 1234567890",  attributes));
		assertEquals(" ",  Text.process("1234567890 ",  attributes));
		assertEquals("a",  Text.process("a1234567890",  attributes));
		assertEquals("b",  Text.process("1234567890b",  attributes));
		assertEquals("  ", Text.process(" 1234567890 ", attributes));
		assertEquals("ab", Text.process("a1234567890b", attributes));
	}
	
	public void testProcessStringIgnoreLetters()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, true, false, false, false, false, false);
		
		assertEquals("",   Text.process("abcdefghijklmnopqrstuvxwz",   attributes));
		assertEquals("",   Text.process("abcdefghijklmnopqrstuvxwz",   attributes));
		assertEquals(" ",  Text.process(" abcdefghijklmnopqrstuvxwz",  attributes));
		assertEquals(" ",  Text.process("abcdefghijklmnopqrstuvxwz ",  attributes));
		assertEquals("1",  Text.process("1abcdefghijklmnopqrstuvxwz",  attributes));
		assertEquals("2",  Text.process("abcdefghijklmnopqrstuvxwz2",  attributes));
		assertEquals("  ", Text.process(" abcdefghijklmnopqrstuvxwz ", attributes));
		assertEquals("12", Text.process("1abcdefghijklmnopqrstuvxwz2", attributes));
		assertEquals("12", Text.process("abcdefghijk12lmnopqrstuvxwz", attributes));
		assertEquals("12", Text.process("abcde1fghijklmnop2qrstuvxwz", attributes));
	}
	
	public void testProcessStringIgnoreWhitespaces()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, false, true, false, false, false, false);
		
		String abc = "abc";
		assertEquals(abc, Text.process("abc",     attributes));
		assertEquals(abc, Text.process("abc ",    attributes));
		assertEquals(abc, Text.process("abc  ",   attributes));
		assertEquals(abc, Text.process(" abc",    attributes));
		assertEquals(abc, Text.process("  abc",   attributes));
		assertEquals(abc, Text.process(" abc ",   attributes));
		assertEquals(abc, Text.process("  abc  ", attributes));
		assertEquals(abc, Text.process(" a b c ", attributes));
	}
	
	public void testProcessStringIgnoreSymbols()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, false, false, true, false, false, false);
		
		String abc = "abc";
		assertEquals(abc, Text.process("!@#$%^&*()_+-={}|[]\\:',./<>?~`abc", attributes));
		assertEquals(abc, Text.process("abc!@#$%^&*()_+-={}|[]\\:',./<>?~`", attributes));
		assertEquals(abc, Text.process("!@#$%^&*()_+-={}|[]\\:',./<>?~`abc", attributes));
		assertEquals(abc, Text.process("!@#$%^a&*()_+-=b{}|[]\\:',.c/<>?~`", attributes));
		assertEquals("",  Text.process("!@#$%^&*()_+-=^^^^{}|[]\\:',./<>?~", attributes));
	}
	
	public void testProcessStringIgnoreConsecute()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, false, false, false, false, true, false);
		
		assertEquals("abc", Text.process("============abc", attributes));
		assertEquals("abc", Text.process("abc============", attributes));
		assertEquals("abc", Text.process("===a===b===c===", attributes));
		assertEquals("bc" , Text.process("=======b===c===", attributes));
	}
	
	public void testProcessStringMergeWhitespaces()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, false, false, false, true, false, false);
		
		assertEquals(" ",    Text.process(" ",      attributes));
		assertEquals(" ",    Text.process("  ",     attributes));
		assertEquals("a b",  Text.process("a b",    attributes));
		assertEquals("a b",  Text.process("a  b",   attributes));
		assertEquals(" a b", Text.process(" a b",   attributes));
		assertEquals(" a b", Text.process("   a b", attributes));
		assertEquals("a b ", Text.process("a b   ", attributes));
		assertEquals("a b ", Text.process("a   b ", attributes));
	}
	
	public void testProcessStringMergeWhitespacesIgnoreSymbols()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, false, false, true, true, false, false);

		assertEquals(" ", Text.process(" ! ",   attributes));
		assertEquals(" ", Text.process(" % ! ", attributes));
	}
	
	public void testProcessStringMergeWhitespacesIgnoreLetter()
	{
		Attributes attributes = Attributes.getInstance();
		attributes.setAttributes(false, true, false, false, true, false, false);

		assertEquals(" ", Text.process(" a ",   attributes));
		assertEquals(" ", Text.process(" a b ", attributes));
	}
}
