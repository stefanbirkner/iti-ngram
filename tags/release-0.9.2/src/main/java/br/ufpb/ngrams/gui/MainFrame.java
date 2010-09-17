package br.ufpb.ngrams.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import br.ufpb.ngrams.Attributes;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 3354879614300145036L;
	private static final String FILENAME_NGRAMS = "br/ufpb/ngrams/resources/icons/ngrams.png";

	private JSplitPane splitPane;
	private MainMenuBar mainMenu;
	private MainToolBar mainToolBar;
	
	public MainFrame(String title, int width, int height)
	{
		super(title);
		super.setIconImage(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(FILENAME_NGRAMS)));
		super.setSize(width, height);
		this.init();
		this.initSetup();
		super.setVisible(true);
	}
	
	private void init()
	{
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.initMenu();
		this.initSplitPane();
		this.initToolBar();
		this.getContentPane().add(StatusBar.getInstance(), BorderLayout.SOUTH);
		WindowUtil.centralize(this);
	}
	
	private void initSplitPane()
	{
		int splitLocation = super.getHeight() / 2;
		this.splitPane = new JSplitPane();
		this.splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.splitPane.setDividerLocation(splitLocation);
		this.splitPane.setOneTouchExpandable(true);
		this.splitPane.setTopComponent(ContentPanel.getInstance());
		this.splitPane.setBottomComponent(OutputPanel.getInstance());
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
	}
	
	private void initToolBar()
	{
		this.mainToolBar = new MainToolBar();
		this.getContentPane().add(mainToolBar, BorderLayout.NORTH);
	}
	
	private void initMenu()
	{
		this.mainMenu = new MainMenuBar();
		this.setJMenuBar(mainMenu);
	}
	
	private void initSetup()
	{
		Attributes.getInstance().setAttributes(true, false, false, true, true, false, true);
	}
}
