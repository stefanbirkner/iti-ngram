package br.ufpb.ngrams.gui;

import br.ufpb.ngrams.MainProperties;

enum ButtonConfig {
	FOLDER("folder"), SETUP("setup"), START("start"), VALIDATE("validate");
	
	final String name, toolTipText;

	private ButtonConfig(String name)
	{
		this.name = name;
		this.toolTipText = MainProperties.getToolTipTextForButton(name);
	}
}
