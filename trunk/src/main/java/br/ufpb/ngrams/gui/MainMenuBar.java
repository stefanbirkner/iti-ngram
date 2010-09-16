 package br.ufpb.ngrams.gui;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import br.ufpb.ngrams.MainProperties;
import br.ufpb.ngrams.gui.listeners.AboutListener;
import br.ufpb.ngrams.gui.listeners.OpenFileListener;
import br.ufpb.ngrams.gui.listeners.SetupListener;

public class MainMenuBar extends JMenuBar
{
	private static final long serialVersionUID = -6442019048135737578L;

	private JMenu menuFile;
	private JMenu menuNgrams;
	private JMenu menuHelp;
	
	private JMenuItem itemOpen;
	private JMenuItem itemExit;
	private JMenuItem itemSetup;
	private JMenuItem itemAbout;
	
	public MainMenuBar()
	{
		JPopupMenu.setDefaultLightWeightPopupEnabled(false);
		this.init();
	}
	
	private void init()
	{
		this.initMenuFile();
		this.initMenuNgrams();
		this.initMenuHelp();
	}
	
	private void initMenuFile()
	{
		menuFile = new JMenu(MainProperties.MENU_FILE);
		this.add(menuFile);
		
		itemOpen = new JMenuItem(MainProperties.MENU_FILE_OPEN);
		itemOpen.addActionListener(new OpenFileListener(ContentPanel.getInstance().getTextArea()));
		menuFile.add(itemOpen);
		menuFile.addSeparator();
		
		itemExit = new JMenuItem(MainProperties.MENU_FILE_EXIT);
		menuFile.add(itemExit);
	}
	
	private void initMenuNgrams()
	{
		menuNgrams = new JMenu(MainProperties.MENU_NGRAMS);
		this.add(menuNgrams);
		
		itemSetup = new JMenuItem(MainProperties.MENU_NGRAMS_SETUP);
		itemSetup.addActionListener(new SetupListener());
		menuNgrams.add(itemSetup);
	}
	
	private void initMenuHelp()
	{
		menuHelp = new JMenu(MainProperties.MENU_HELP);
		this.add(menuHelp);
		
		itemAbout = new JMenuItem(MainProperties.MENU_HELP_ABOUT);
		itemAbout.addActionListener(new AboutListener());
		menuHelp.add(itemAbout);
	}
}