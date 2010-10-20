package br.ufpb.ngrams.gui;

import static br.ufpb.ngrams.gui.ButtonFactory.createButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.gui.components.StartButton;
import br.ufpb.ngrams.gui.listeners.OpenFileListener;
import br.ufpb.ngrams.gui.listeners.ProcessTextListener;
import br.ufpb.ngrams.gui.listeners.SetupListener;
import br.ufpb.ngrams.gui.listeners.ValidateTextListener;

class MainToolBar extends JToolBar
{
	private static final long serialVersionUID = 999566304598748208L;

	MainToolBar(JTextArea textArea, Attributes configuration)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT));
		addButton(ButtonConfig.FOLDER, new OpenFileListener(textArea));
		addButton(ButtonConfig.SETUP, new SetupListener(configuration));
		addButton(ButtonConfig.VALIDATE, new ValidateTextListener(textArea, configuration));
		addStartButton(textArea);
	}
  
  private void addButton(ButtonConfig config, ActionListener listener)
  {
    JButton button = createButton(config);
    button.addActionListener(listener);
    add(button);
  }

  private void addStartButton(JTextArea textArea)
  {
    StartButton button = new StartButton();
    button.addActionListener(new ProcessTextListener(textArea));
    button.setToolTipText(ButtonConfig.START.toolTipText);
		add(button);
  }
}