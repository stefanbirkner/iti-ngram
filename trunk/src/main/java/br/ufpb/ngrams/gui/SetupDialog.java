package br.ufpb.ngrams.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

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

  private final Attributes configuration;

  public SetupDialog(Attributes configuration)
  {
    this.configuration = configuration;
    setTitle(new String(MainProperties.LABEL_SETUP));
    initComponents();
    initValues();
    pack();
    WindowUtil.centralize(this);
    setVisible(true);
  }

  private void initComponents()
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
        configuration.ignoreLetters = cIgnoreLetters.isSelected();
        configuration.ignoreDigits = cIgnoreDigits.isSelected();
        configuration.ignoreSymbols = cIgnoreSymbols.isSelected();
        configuration.ignoreWhitespaces = cIgnoreWhitespaces.isSelected();
        configuration.ignoreConsecutive = cIgnoreConsecutive.isSelected();
        configuration.mergeWhitespaces = cMergeWhiteSpaces.isSelected();
        configuration.ignoredCharacters = getIgnoredCharacters();
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
    dispose();
  }

  private void initValues()
  {
    this.cIgnoreLetters.setSelected(configuration.ignoreLetters);
    this.cIgnoreDigits.setSelected(configuration.ignoreDigits);
    this.cIgnoreWhitespaces.setSelected(configuration.ignoreWhitespaces);
    this.cIgnoreConsecutive.setSelected(configuration.ignoreConsecutive);
    this.cIgnoreSymbols.setSelected(configuration.ignoreSymbols);
    this.cMergeWhiteSpaces.setSelected(configuration.mergeWhitespaces);

    String characters = new String();
    for (Character c : configuration.ignoredCharacters)
    {
      characters += c;
    }
    this.tIgnoredCharacters.setText(characters);
  }

  private List<Character> getIgnoredCharacters()
  {
    List<Character> ignoredCharacters = new ArrayList<Character>();
    for (char c : tIgnoredCharacters.getText().toCharArray())
    {
      ignoredCharacters.add(c);
    }
    return ignoredCharacters;
  }
}