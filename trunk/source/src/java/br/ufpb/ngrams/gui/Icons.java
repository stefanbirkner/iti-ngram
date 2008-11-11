package br.ufpb.ngrams.gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import br.ufpb.ngrams.MainProperties;

public class Icons
{
	public static final String PATH = "br/ufpb/ngrams/resources/icons/";
	
	public static final String ICON_FOLDER = PATH + MainProperties.ICON_FOLDER;
	public static final String ICON_START = PATH + MainProperties.ICON_START;
	public static final String ICON_VALIDATE = PATH + MainProperties.ICON_VALIDATE;
	public static final String ICON_NGRAMS = PATH + MainProperties.ICON_NGRAMS;
	public static final String ICON_SETUP = PATH + MainProperties.ICON_SETUP;
	
	public static Icon getIcon(String path)
	{
		Icon icon = new ImageIcon(ClassLoader.getSystemResource(path));
		return icon;
	}
}
