package br.ufpb.ngrams.gui;

import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ContentPanel extends JPanel
{
	private static final long serialVersionUID = 1093998575730432278L;
	
	private static ContentPanel instance = null;
	private TextArea textArea;
	private JScrollPane scrollPane;
	
	private ContentPanel() {
		this.init();
	}
	
	public static ContentPanel getInstance()
	{
		if (instance == null) {
			instance = new ContentPanel();
		}
		return instance;
	}
	
	private void init()
	{
		this.setLayout(new GridLayout(1,1));
		this.add(this.getScrollPane());
	}
	
	public JScrollPane getScrollPane()
	{
		if (scrollPane == null)
		{
			scrollPane = new JScrollPane();
			scrollPane.setViewportView(getTextArea());
		}
		return scrollPane;
	}
	
	public TextArea getTextArea()
	{
		if (textArea == null) {
			textArea = new TextArea();
		}
		return textArea;
	}
}
