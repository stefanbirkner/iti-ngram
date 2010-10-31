package br.ufpb.ngrams.gui.components;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JTextArea;

public class TextAreaWithDropSupport extends JTextArea
{
  private static final long serialVersionUID = -5061008565705911215L;

  public TextAreaWithDropSupport()
  {
    new DropTarget(this, new TextFileDropTarget(this));
  }

  private class TextFileDropTarget implements DropTargetListener
  {
    private static final String FILE_PREFIX = "file://";
    private final JTextArea textArea;

    private TextFileDropTarget(JTextArea textArea)
    {
      this.textArea = textArea;
    }

    @Override
    public void dragEnter(DropTargetDragEvent event)
    {
    }

    @Override
    public void dragExit(DropTargetEvent event)
    {
    }

    @Override
    public void dragOver(DropTargetDragEvent event)
    {
    }

    @Override
    public void dropActionChanged(DropTargetDragEvent arg0)
    {
    }

    @Override
    public void drop(DropTargetDropEvent event)
    {
      event.acceptDrop(DnDConstants.ACTION_COPY);
      try
      {
        Transferable tr = event.getTransferable();
        for (DataFlavor flavor : tr.getTransferDataFlavors())
        {
          if (flavor.equals(DataFlavor.stringFlavor))
          {
            dropText(tr, flavor);
            event.dropComplete(true);
          }
        }
      } catch (UnsupportedFlavorException e)
      {
        throw new RuntimeException(e);
      } catch (IOException e)
      {
        throw new RuntimeException(e);
      }
    }

    private void dropText(Transferable tr, DataFlavor flavor) throws UnsupportedFlavorException,
        IOException
    {
      String text = (String) tr.getTransferData(flavor);
      if (text.startsWith(FILE_PREFIX))
      {
        dropFile(text);
      } else
      {
        dropText(text);
      }
    }

    private void dropFile(String url) throws IOException
    {
      BufferedReader reader = createReaderForUrl(url);
      String text = getStringFromReader(reader);
      dropText(text);
    }

    private BufferedReader createReaderForUrl(String fileAsUrl) throws MalformedURLException,
        IOException
    {
      URL url = new URL(fileAsUrl);
      BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
      return reader;
    }

    private String getStringFromReader(BufferedReader reader) throws IOException
    {
      try
      {
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null)
        {
          sb.append(line);
          sb.append("\n");
          line = reader.readLine();
        }
        return sb.toString();
      } finally
      {
        reader.close();
      }
    }

    private void dropText(String text)
    {
      textArea.setText(text);
    }
  }
}
