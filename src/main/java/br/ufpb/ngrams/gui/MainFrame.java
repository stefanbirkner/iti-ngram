package br.ufpb.ngrams.gui;

import java.awt.BorderLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JSplitPane;

import br.ufpb.ngrams.Attributes;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 3354879614300145037L;
	private static final String FILENAME_NGRAMS = "br/ufpb/ngrams/resources/icons/ngrams.png";

	private JSplitPane splitPane;
	private MainMenuBar mainMenu;
	private MainToolBar mainToolBar;
	private final ContentPanel contentPanel = new ContentPanel();
	
	public MainFrame(String title, int width, int height)
	{
		super(title);
    Attributes configuration = new Attributes();
		setIconImage(Toolkit.getDefaultToolkit().createImage(ClassLoader.getSystemResource(FILENAME_NGRAMS)));
		setSize(width, height);
		init(configuration);
		initSetup(configuration);
		setVisible(true);
	}
	
	private void init(Attributes configuration)
	{
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.initMenu(configuration);
		this.initSplitPane();
		this.initToolBar(configuration);
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
		this.splitPane.setTopComponent(contentPanel);
		this.splitPane.setBottomComponent(OutputPanel.getInstance());
		this.getContentPane().add(splitPane, BorderLayout.CENTER);
	}
	
	private void initToolBar(Attributes configuration)
	{
		this.mainToolBar = new MainToolBar(contentPanel.getTextArea(), configuration);
		this.getContentPane().add(mainToolBar, BorderLayout.NORTH);
	}
	
	private void initMenu(Attributes configuration)
	{
		this.mainMenu = new MainMenuBar(contentPanel.getTextArea(), configuration);
		this.setJMenuBar(mainMenu);
	}
	
	private void initSetup(Attributes configuration)
	{
	  configuration.setAttributes(true, false, false, true, true, false, true);
	}
}
