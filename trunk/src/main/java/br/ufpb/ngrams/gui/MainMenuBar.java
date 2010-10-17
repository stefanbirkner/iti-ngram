 package br.ufpb.ngrams.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;

import br.ufpb.ngrams.MainProperties;
import br.ufpb.ngrams.gui.listeners.AboutListener;
import br.ufpb.ngrams.gui.listeners.OpenFileListener;
import br.ufpb.ngrams.gui.listeners.SetupListener;

public class MainMenuBar extends JMenuBar
{
	private static final long serialVersionUID = -6442019048135737576L;
	
	public MainMenuBar(JTextArea textArea)
	{
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		initMenuFile(textArea);
		initMenuNgrams();
		initMenuHelp();
	}
	
	private void initMenuFile(JTextArea textArea)
	{
	  JMenu menuFile = new JMenu(MainProperties.MENU_FILE);
		this.add(menuFile);
		
		JMenuItem itemOpen = new JMenuItem(MainProperties.MENU_FILE_OPEN);
		itemOpen.addActionListener(new OpenFileListener(textArea));
		menuFile.add(itemOpen);
		menuFile.addSeparator();
		
		JMenuItem itemExit = new JMenuItem(MainProperties.MENU_FILE_EXIT);
		menuFile.add(itemExit);
	}
	
	private void initMenuNgrams()
	{
	  JMenu menuNgrams = new JMenu(MainProperties.MENU_NGRAMS);
		this.add(menuNgrams);
		
		JMenuItem itemSetup = new JMenuItem(MainProperties.MENU_NGRAMS_SETUP);
		itemSetup.addActionListener(new SetupListener());
		menuNgrams.add(itemSetup);
	}
	
	private void initMenuHelp()
	{
	  JMenu menuHelp = new JMenu(MainProperties.MENU_HELP);
		this.add(menuHelp);
		
		JMenuItem itemAbout = new JMenuItem(MainProperties.MENU_HELP_ABOUT);
		itemAbout.addActionListener(new AboutListener());
		menuHelp.add(itemAbout);
	}
}
