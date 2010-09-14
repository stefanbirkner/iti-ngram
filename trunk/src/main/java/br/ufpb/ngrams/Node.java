package br.ufpb.ngrams;

public class Node implements Comparable<Node>
{
	private String symbol;
	private int amount = 0;
	
	public Node(String symbol)
	{
		this.symbol = symbol;
	}

	public int getAmount()
	{
		return amount;
	}
	
	void incrementAmountByOne()
	{
		++amount;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int compareTo(Node node)
	{
		if (node.getAmount() < getAmount()) { return  1; }
		if (node.getAmount() > getAmount()) { return -1; }
		return 0;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if ((o instanceof Node) && ((Node)o).getSymbol().equals(getSymbol())) { return true; }
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return this.symbol.hashCode();
	}
	
	@Override
	public String toString()
	{
		return String.format("[%s, %s]", getSymbol(), getAmount());
	}
}
