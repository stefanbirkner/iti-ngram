package br.ufpb.ngrams;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NodeTest {
	@Test
	public void shouldHaveAmountOneAfterFirstIncrementing() {
		Node node = new Node("dummy text", 0);
		node.incrementAmountByOne();
		assertEquals("Wrong amount.", 1, node.getAmount());
	}
}
