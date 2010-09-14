package br.ufpb.ngrams.gui;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JToolBar;

import br.ufpb.ngrams.gui.listeners.OpenFileListener;
import br.ufpb.ngrams.gui.listeners.ProcessTextListener;
import br.ufpb.ngrams.gui.listeners.SetupListener;
import br.ufpb.ngrams.gui.listeners.ValidateTextListener;

public class MainToolBar extends JToolBar
{
	private static final long serialVersionUID = 999566304598748207L;

	public MainToolBar()
	{		
		super.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JButton buttonOpen = new JButton();
		buttonOpen.setIcon(Icons.getIcon(Icons.ICON_FOLDER));
		buttonOpen.setFocusable(false);
		buttonOpen.addActionListener(new OpenFileListener());
		super.add(buttonOpen);
		
		JButton buttonSetup = new JButton();
		buttonSetup.setIcon(Icons.getIcon(Icons.ICON_SETUP));
		buttonSetup.setFocusable(false);
		buttonSetup.addActionListener(new SetupListener());
		super.add(buttonSetup);
		
		JButton buttonProcessText = new JButton();
		buttonProcessText.setIcon(Icons.getIcon(Icons.ICON_VALIDATE));
		buttonProcessText.setFocusable(false);
		buttonProcessText.addActionListener(new ValidateTextListener());
		super.add(buttonProcessText);
		
		JButton buttonStart = new JButton();
		buttonStart.setIcon(Icons.getIcon(Icons.ICON_START));
		buttonStart.setFocusable(false);
		buttonStart.addActionListener(new ProcessTextListener());
		super.add(buttonStart);
	}
}