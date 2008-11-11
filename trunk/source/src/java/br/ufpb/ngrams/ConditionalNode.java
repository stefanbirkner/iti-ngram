package br.ufpb.ngrams;


public class ConditionalNode implements Comparable<ConditionalNode>
{
	private String symbola;
	private String symbolb;
	private float probability;
	
	public ConditionalNode(String symbola, String symbolb, float probability)
	{
		this.symbola = symbola;
		this.symbolb = symbolb;
		this.probability = probability;
	}

	public String getSymbolb() { return symbolb; }
	public String getSymbola() { return symbola; }
	public float getProbability() { return probability; }
	
	public void setSymbola(String symbola) { this.symbola = symbola; }
	public void setSymbolb(String symbolb) { this.symbolb = symbolb; }
	public void setProbability(float probability) { this.probability = probability; }

	public int compareTo(ConditionalNode node)
	{
		if (node.getProbability() < getProbability()) { return  1; }
		if (node.getProbability() > getProbability()) { return -1; }
		return 0;
	}
	
	@Override
	public boolean equals(Object o)
	{
		if ((o instanceof ConditionalNode) &&
			((ConditionalNode)o).getSymbola().equals(getSymbola()) &&
			((ConditionalNode)o).getSymbolb().equals(getSymbolb()))
		{
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode()
	{
		return this.symbola.hashCode() + this.symbolb.hashCode();
	}
	
	@Override
	public String toString()
	{
		return String.format("[%s|%s, %s]", getSymbola(), getSymbolb(), getProbability());
	}
}
