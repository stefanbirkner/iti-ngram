package br.ufpb.ngrams.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Toolkit;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JSplitPane;

import br.ufpb.ngrams.Attributes;

public class MainFrame extends JFrame
{
  private static final long serialVersionUID = 3354879614300145038L;
  private static final int DEFAULT_WIDTH = 800;
  private static final int DEFAULT_HEIGHT = 600;
  private static final String NAME_OF_ICON_IMAGE = "br/ufpb/ngrams/resources/icons/ngrams.png";

  private final ContentPanel contentPanel = new ContentPanel();

  public MainFrame(String title)
  {
    super(title);
    URL iconUrl = ClassLoader.getSystemResource(NAME_OF_ICON_IMAGE);
    setIconImage(Toolkit.getDefaultToolkit().createImage(iconUrl));
    setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    Attributes configuration = createConfiguration();
    addComponents(configuration);
    WindowUtil.centralize(this);
  }

  private Attributes createConfiguration()
  {
    Attributes configuration = new Attributes();
    configuration.ignoreDigits = true;
    configuration.ignoreLetters = false;
    configuration.ignoreWhitespaces = false;
    configuration.ignoreSymbols = true;
    configuration.mergeWhitespaces = true;
    configuration.ignoreConsecutive = false;
    configuration.convertDowncase = true;
    return configuration;
  }

  private void addComponents(Attributes configuration)
  {
    addMenuBar(configuration);
    initSplitPane();
    addToolBar(configuration);
    getContentPane().add(StatusBar.getInstance(), BorderLayout.SOUTH);
  }

  private void initSplitPane()
  {
    int splitLocation = super.getHeight() / 2;
    JSplitPane splitPane = new JSplitPane();
    splitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
    splitPane.setDividerLocation(splitLocation);
    splitPane.setOneTouchExpandable(true);
    splitPane.setTopComponent(contentPanel);
    splitPane.setBottomComponent(OutputPanel.getInstance());
    getContentPane().add(splitPane, BorderLayout.CENTER);
  }

  private void addToolBar(Attributes configuration)
  {
    Component toolBar = new MainToolBar(contentPanel.getTextArea(), configuration);
    getContentPane().add(toolBar, BorderLayout.NORTH);
  }

  private void addMenuBar(Attributes configuration)
  {
    JMenuBar menuBar = new MainMenuBar(contentPanel.getTextArea(), configuration);
    setJMenuBar(menuBar);
  }
}
