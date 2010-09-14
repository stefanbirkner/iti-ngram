package br.ufpb.ngrams.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import br.ufpb.ngrams.Attributes;
import br.ufpb.ngrams.MainProperties;

public class SetupDialog extends JDialog
{
	private static final long serialVersionUID = -8864293312009068070L;
	
	private JPanel panelSetup;
	private JPanel panelGlobal;
	
	private JLabel lIgnoreLetters;
	private JLabel lIgnoreDigits;
	private JLabel lIgnoreWhitespaces;
	private JLabel lIgnoreSymbols;
	private JLabel lIgnoreConsecutive;
	private JLabel lMergeWhitespaces;
	private JLabel lIgnoredCharacters;
	
	private JCheckBox cIgnoreLetters;
	private JCheckBox cIgnoreDigits;
	private JCheckBox cIgnoreWhitespaces;
	private JCheckBox cIgnoreSymbols;
	private JCheckBox cIgnoreConsecutive;
	private JCheckBox cMergeWhiteSpaces;
	private JTextField tIgnoredCharacters;
	
	private JButton buttonSave;
	private JButton buttonCancel;
	
	public SetupDialog()
	{
		this.initAll();
		setVisible(true);
	}
	
	public void initAll()
	{
		setTitle(new String(MainProperties.LABEL_SETUP));
		setModal(true);
		this.initComponents();
		this.initValues();
		pack();
		WindowUtil.centralize(this);
	}
	
	public void initComponents()
	{
		panelSetup = new JPanel();
		panelSetup.setLayout(new GridLayout(7, 2, 5, 5));
		panelSetup.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		panelGlobal = new JPanel();
		panelGlobal.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		
		lIgnoreLetters = new JLabel(MainProperties.LABEL_IGNORELETTERS);
		lIgnoreDigits = new JLabel(MainProperties.LABEL_IGNOREDIGITS);
		lIgnoreWhitespaces = new JLabel(MainProperties.LABEL_IGNOREWHITESPACES);
		lIgnoreConsecutive = new JLabel(MainProperties.LABEL_IGNORECONSECUTIVE);
		lIgnoreSymbols = new JLabel(MainProperties.LABEL_IGNORESYMBOLS);
		lMergeWhitespaces = new JLabel(MainProperties.LABEL_MERGEWHITESPACES);
		lIgnoredCharacters = new JLabel(MainProperties.LABEL_IGNOREDCHARACTERS);
		
		lIgnoreLetters.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lIgnoreDigits.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lIgnoreWhitespaces.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lIgnoreConsecutive.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lIgnoreSymbols.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lMergeWhitespaces.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		lIgnoredCharacters.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
		
		cIgnoreLetters = new JCheckBox();
		cIgnoreDigits = new JCheckBox();
		cIgnoreWhitespaces = new JCheckBox();
		cIgnoreConsecutive = new JCheckBox();
		cIgnoreSymbols = new JCheckBox();
		cMergeWhiteSpaces = new JCheckBox();
		tIgnoredCharacters = new JTextField();
		tIgnoredCharacters.setPreferredSize(new Dimension(50, tIgnoredCharacters.getSize().height));
		
		buttonSave = new JButton(MainProperties.BUTTON_SAVE);
		buttonSave.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Attributes.getInstance().setIgnoreLetters(cIgnoreLetters.isSelected());
				Attributes.getInstance().setIgnoreDigits(cIgnoreDigits.isSelected());
				Attributes.getInstance().setIgnoreSymbols(cIgnoreSymbols.isSelected());
				Attributes.getInstance().setIgnoreWhitespaces(cIgnoreWhitespaces.isSelected());
				Attributes.getInstance().setIgnoreConsecutive(cIgnoreConsecutive.isSelected());
				Attributes.getInstance().setMergeWhitespaces(cMergeWhiteSpaces.isSelected());
				
				Attributes.getInstance().getIgnoredCharacters().clear();
				for (char c : tIgnoredCharacters.getText().toCharArray())
				{
					Attributes.getInstance().getIgnoredCharacters().add(new Character(c));
				}
				
				close();
			}
		});
		
		buttonCancel = new JButton(MainProperties.BUTTON_CANCEL);
		buttonCancel.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				close();
			}
		});
		
		panelSetup.add(lIgnoreLetters);
		panelSetup.add(cIgnoreLetters);
		panelSetup.add(lIgnoreDigits);
		panelSetup.add(cIgnoreDigits);
		panelSetup.add(lIgnoreWhitespaces);
		panelSetup.add(cIgnoreWhitespaces);
		panelSetup.add(lIgnoreConsecutive);
		panelSetup.add(cIgnoreConsecutive);
		panelSetup.add(lIgnoreSymbols);
		panelSetup.add(cIgnoreSymbols);
		panelSetup.add(lMergeWhitespaces);
		panelSetup.add(cMergeWhiteSpaces);
		panelSetup.add(lIgnoredCharacters);
		panelSetup.add(tIgnoredCharacters);
		
		panelGlobal.add(buttonSave);
		panelGlobal.add(buttonCancel);
		
		this.add(panelSetup, BorderLayout.CENTER);
		this.add(panelGlobal, BorderLayout.SOUTH);
	}
	
	private void close()
	{
		this.dispose();
	}
	
	private void initValues()
	{
		this.cIgnoreLetters.setSelected(Attributes.getInstance().isIgnoreLetters());
		this.cIgnoreDigits.setSelected(Attributes.getInstance().isIgnoreDigits());
		this.cIgnoreWhitespaces.setSelected(Attributes.getInstance().isIgnoreWhitespaces());
		this.cIgnoreConsecutive.setSelected(Attributes.getInstance().isIgnoreConsecutive());
		this.cIgnoreSymbols.setSelected(Attributes.getInstance().isIgnoreSymbols());
		this.cMergeWhiteSpaces.setSelected(Attributes.getInstance().isMergeWhitespaces());
		
		String characters = new String();
		for (Character c : Attributes.getInstance().getIgnoredCharacters()) {
			characters += c;
		}
		this.tIgnoredCharacters.setText(characters);
	}
}