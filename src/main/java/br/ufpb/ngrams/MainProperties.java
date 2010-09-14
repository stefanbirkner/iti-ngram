package br.ufpb.ngrams;

import java.io.IOException;
import java.util.Properties;

public class MainProperties
{
	public final static String PROPERTIES_PATH = "br/ufpb/ngrams/resources/ngrams.properties";
	
	public static final String VERSION = getProperties().getProperty("ngrams.version");
	
	public static final String MAINWINDOW_TITLE = getProperties().getProperty("ngrams.mainwindow.title");
	public static final String MAINWINDOW_WIDTH = getProperties().getProperty("ngrams.mainwindow.width");
	public static final String MAINWINDOW_HEIGHT = getProperties().getProperty("ngrams.mainwindow.height");
	
	public static final String MENU_FILE = getProperties().getProperty("ngrams.menu.file");
	public static final String MENU_FILE_OPEN = getProperties().getProperty("ngrams.menu.file.open");
	public static final String MENU_FILE_EXIT = getProperties().getProperty("ngrams.menu.file.exit");
	public static final String MENU_NGRAMS = getProperties().getProperty("ngrams.menu.ngrams");
	public static final String MENU_NGRAMS_SETUP = getProperties().getProperty("ngrams.menu.ngrams.setup");
	public static final String MENU_HELP = getProperties().getProperty("ngrams.menu.help");
	public static final String MENU_HELP_ABOUT = getProperties().getProperty("ngrams.menu.help.about");
	
	public static final String ICON_FOLDER = getProperties().getProperty("ngrams.icons.folder");
	public static final String ICON_START = getProperties().getProperty("ngrams.icons.start");
	public static final String ICON_VALIDATE = getProperties().getProperty("ngrams.icons.validate");
	public static final String ICON_NGRAMS = getProperties().getProperty("ngrams.icons.ngrams");
	public static final String ICON_SETUP = getProperties().getProperty("ngrams.icons.setup");
	
	public static final String LABEL_IGNORELETTERS = getProperties().getProperty("ngrams.labels.ignoreletters");
	public static final String LABEL_IGNOREDIGITS = getProperties().getProperty("ngrams.labels.ignoredigits");
	public static final String LABEL_IGNOREWHITESPACES = getProperties().getProperty("ngrams.labels.ignorewhitespaces");
	public static final String LABEL_IGNORESYMBOLS = getProperties().getProperty("ngrams.labels.ignoresymbols");
	public static final String LABEL_MERGEWHITESPACES = getProperties().getProperty("ngrams.labels.mergewhitespaces");
	public static final String LABEL_IGNOREDCHARACTERS = getProperties().getProperty("ngrams.labels.ignoredcharacters");
	public static final String LABEL_IGNORECONSECUTIVE = getProperties().getProperty("ngrams.labels.ignoreconsecutive");
	public static final String LABEL_SETUP = getProperties().getProperty("ngrams.labels.setup");
	
	public static final String BUTTON_SAVE = getProperties().getProperty("ngrams.buttons.save");
	public static final String BUTTON_CANCEL = getProperties().getProperty("ngrams.buttons.cancel");
	
	public static final String TABLE_ROWA = getProperties().getProperty("ngrams.table.rowa");
	public static final String TABLE_ROWB = getProperties().getProperty("ngrams.table.rowb");
	public static final String TABLE_ROWC = getProperties().getProperty("ngrams.table.rowc");
	
	private static Properties properties = null;
	
	protected MainProperties()
	{
	}
	
	private static Properties getProperties()
	{
		if (properties == null)
		{
			properties = new Properties();
			
			try {
				properties.load(ClassLoader.getSystemResourceAsStream(PROPERTIES_PATH));
			}
			catch (IOException exception)
			{
				System.err.println(exception.getMessage());
				exception.printStackTrace();
			}
		}
		return properties;
	}
}
