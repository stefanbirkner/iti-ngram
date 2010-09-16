package br.ufpb.ngrams.gui;

import static br.ufpb.ngrams.gui.ButtonFactory.createButton;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.ufpb.ngrams.gui.listeners.OpenFileListener;
import br.ufpb.ngrams.gui.listeners.ProcessTextListener;
import br.ufpb.ngrams.gui.listeners.SetupListener;
import br.ufpb.ngrams.gui.listeners.ValidateTextListener;

class MainToolBar extends JToolBar
{
	private static final long serialVersionUID = 999566304598748207L;

	MainToolBar()
	{
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		addButton(ButtonConfig.FOLDER,new OpenFileListener());
		addButton(ButtonConfig.SETUP,new SetupListener());
		addButton(ButtonConfig.VALIDATE,new ValidateTextListener());
		addButton(ButtonConfig.START,new ProcessTextListener());
	}
	
	private void addButton(ButtonConfig config, ActionListener listener)
	{
		JButton button = createButton(config);
		button.addActionListener(listener);
		super.add(button);
	}
}