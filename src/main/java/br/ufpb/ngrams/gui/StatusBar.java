package br.ufpb.ngrams.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class StatusBar extends JPanel
{
	private static final long serialVersionUID = -3459030279106440133L;

	private static StatusBar instance = null;
	private JLabel labelMessage;
	private JProgressBar progressBar;
	
	private StatusBar()
	{
		this.init();
		this.setMessage(new String());
	}
	
	public static StatusBar getInstance()
	{
		if (instance == null) {
			instance = new StatusBar();
		}
		return instance;
	}
	
	private void init()
	{
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.labelMessage = new JLabel();
		this.progressBar = new JProgressBar();
		this.add(this.progressBar);
		this.add(this.labelMessage);
	}
	
	public void setMessage(String message)
	{
		if (new String().equals(message)){ labelMessage.setText(new String(" ")); }
		else { labelMessage.setText(message); }
	}
	
	public JProgressBar getProgressBar()
	{
		return this.progressBar;
	}
}
