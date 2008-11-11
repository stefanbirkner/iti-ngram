package br.ufpb.ngrams.gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Window;

public class WindowUtil
{
	public static void centralize(Window window)
	{
		Dimension dim = window.getToolkit().getScreenSize();
		Rectangle abounds = window.getBounds();
		window.setLocation((dim.width - abounds.width) / 2, (dim.height - abounds.height) / 2);
	}
}