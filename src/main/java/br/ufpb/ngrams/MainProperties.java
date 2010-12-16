package br.ufpb.ngrams;

import java.io.IOException;
import java.util.Properties;

public class MainProperties
{
	public final static String PROPERTIES_PATH = "br/ufpb/ngrams/resources/ngrams.properties";
	
	public static final String VERSION = getProperty("ngrams.version");
	
	public static final String MAINWINDOW_TITLE = getProperty("ngrams.mainwindow.title");
	
	public static final String MENU_FILE = getProperty("ngrams.menu.file");
	public static final String MENU_FILE_OPEN = getProperty("ngrams.menu.file.open");
	public static final String MENU_FILE_EXIT = getProperty("ngrams.menu.file.exit");
	public static final String MENU_NGRAMS = getProperty("ngrams.menu.ngrams");
	public static final String MENU_NGRAMS_SETUP = getProperty("ngrams.menu.ngrams.setup");
	public static final String MENU_HELP = getProperty("ngrams.menu.help");
	public static final String MENU_HELP_ABOUT = getProperty("ngrams.menu.help.about");
	
	public static final String LABEL_IGNORELETTERS = getProperty("ngrams.labels.ignoreletters");
	public static final String LABEL_IGNOREDIGITS = getProperty("ngrams.labels.ignoredigits");
	public static final String LABEL_IGNOREWHITESPACES = getProperty("ngrams.labels.ignorewhitespaces");
	public static final String LABEL_IGNORESYMBOLS = getProperty("ngrams.labels.ignoresymbols");
	public static final String LABEL_MERGEWHITESPACES = getProperty("ngrams.labels.mergewhitespaces");
	public static final String LABEL_IGNOREDCHARACTERS = getProperty("ngrams.labels.ignoredcharacters");
	public static final String LABEL_IGNORECONSECUTIVE = getProperty("ngrams.labels.ignoreconsecutive");
	public static final String LABEL_SETUP = getProperty("ngrams.labels.setup");
	
	public static final String BUTTON_SAVE = getProperty("ngrams.buttons.save");
	public static final String BUTTON_CANCEL = getProperty("ngrams.buttons.cancel");
	
	public static final String TABLE_ROWA = getProperty("ngrams.table.rowa");
	public static final String TABLE_ROWB = getProperty("ngrams.table.rowb");
	public static final String TABLE_ROWC = getProperty("ngrams.table.rowc");
	
	private static Properties properties = null;
	
	private MainProperties()
	{
	}
	
	public static String getToolTipTextForButton(String name)
	{
    return getProperty("ngrams.button." + name + ".toolTipText");
	}
	
	private static String getProperty(String name)
	{
    return getProperties().getProperty(name);
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
