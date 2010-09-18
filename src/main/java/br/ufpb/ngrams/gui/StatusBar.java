package br.ufpb.ngrams.gui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import br.ufpb.ngrams.ProgressListener;

public class StatusBar extends JPanel implements ProgressListener
{
	private static final long serialVersionUID = -3459030279106440132L;

	private static StatusBar instance = null;
	private final JLabel labelMessage;
	private final JProgressBar progressBar;
	
	private StatusBar()
	{
    setLayout(new FlowLayout(FlowLayout.LEFT));
    labelMessage = new JLabel();
    progressBar = new JProgressBar();
    add(progressBar);
    add(labelMessage);

		setMessage("");
	}

	public static StatusBar getInstance()
	{
		if (instance == null) {
			instance = new StatusBar();
		}
		return instance;
	}

	public void setMessage(String message)
	{
		if ("".equals(message))
		{
		  message = " ";
		}
		
		labelMessage.setText(message);
	}

  @Override
  public void startForNumberOfSteps(int n) {
    progressBar.setValue(0);
    progressBar.setMaximum(n);
  }

  @Override
  public void nextStep() {
    int nextValue = progressBar.getValue() + 1;
    progressBar.setValue(nextValue);
  }
}
