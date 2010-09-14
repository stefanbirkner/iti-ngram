package br.ufpb.ngrams.gui;

import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class OutputPanel extends JPanel
{
	private static final long serialVersionUID = 8249833819427833524L;
	
	private static OutputPanel instance = null;
	private JTabbedPane tabbedPane;
	
	private OutputPanel()
	{
		this.init();
	}
	
	public static OutputPanel getInstance()
	{
		if (instance == null) {
			instance = new OutputPanel();
		}
		return instance;
	}
	
	private void init()
	{
		this.setLayout(new GridLayout(1,1));
		this.tabbedPane = new JTabbedPane();
		this.add(tabbedPane);
	}
	
	public JTabbedPane getTabbedPane()
	{
		return this.tabbedPane;
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane)
	{
		this.tabbedPane = tabbedPane;
	}
}
