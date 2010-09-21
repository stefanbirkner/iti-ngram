package br.ufpb.ngrams.gui.components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class StartButton extends JButton implements MouseListener
{
  private static final long serialVersionUID = -1221333622996051138L;
  private static final Color BUTTON_COLOR = new Color(32, 163, 47);
  
  private Color color = BUTTON_COLOR;

  public StartButton()
  {
    super();
    setBorder(null);
    addMouseListener(this);
  }

  @Override
  protected void paintComponent(Graphics graphics)
  {
    Graphics2D g2d = (Graphics2D) graphics;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    drawButton(g2d);
    drawTriangle(g2d);
  }

  private void drawButton(Graphics2D graphics)
  {
    Color endColor = new Color(BUTTON_COLOR.getRed() * 2 / 3, BUTTON_COLOR.getGreen() * 2 / 3, BUTTON_COLOR.getBlue() * 2 / 3);
    float[] fractions = new float[] {0.3f, 1.0f};
    Color[] colors = new Color[] {color, endColor};
    LinearGradientPaint gradient = new LinearGradientPaint(0,0,0,getHeight(),fractions, colors);
    graphics.setPaint(gradient);
    graphics.fillRoundRect(0, 0, getWidth(), getHeight(),  getWidth() / 2, getHeight() / 2);
  }

  private void drawTriangle(Graphics2D graphics)
  {
    int top = getHeight() / 4;
    int height = getHeight() - 2 * top;
    int midX = getWidth() / 2;
    int left = midX - top;
    int right = midX + top * 5 / 4;
    graphics.setPaint(Color.WHITE);
    Polygon triangle = new Polygon();
    triangle.addPoint(left, top);
    triangle.addPoint(right, top + height / 2);
    triangle.addPoint(left, top + height);
    graphics.fillPolygon(triangle);
  }

  @Override
  public Dimension getPreferredSize()
  {
    return new Dimension(30, 30);
  }

  @Override
  public void mouseClicked(MouseEvent arg0)
  {
    highlight();
  }

  @Override
  public void mouseEntered(MouseEvent arg0)
  {
    highlight();
  }

  @Override
  public void mouseExited(MouseEvent arg0)
  {
    restoreColor();
  }

  @Override
  public void mousePressed(MouseEvent arg0)
  {
    darken();
  }

  @Override
  public void mouseReleased(MouseEvent arg0)
  {
    highlight();
  }

  private void darken()
  {
    color = new Color(
        color.getRed() * 5 / 6,
        color.getGreen() * 5 / 6,
        color.getBlue() * 5 / 6);
    repaint();
  }

  private void highlight()
  {
    color = new Color(
        getBrighterRGB(color.getRed()),
        getBrighterRGB(color.getGreen()),
        getBrighterRGB(color.getBlue()));
    repaint();
  }

  private int getBrighterRGB(int value) {
    int delta = 255 - value;
    return 255 - delta * 3 / 4;
  }

  private void restoreColor()
  {
    color = BUTTON_COLOR;
    repaint();
  }
}
