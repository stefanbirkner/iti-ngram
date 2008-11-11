package br.ufpb.ngrams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil
{
	public static String readTextFile(String path) throws IOException
	{
		return readTextFile(new File(path));
	}
	
	public static String readTextFile(File file) throws IOException
	{
		return readTextFile(new FileReader(file));
	}
	
	public static String readTextFile(FileReader reader) throws IOException
	{
		StringBuffer content = new StringBuffer();
		BufferedReader input = null;

		try
		{
			input = new BufferedReader(reader);
			String line = null;
			while ((line = input.readLine()) != null)
			{
				content.append(line);
				content.append(System.getProperty("line.separator"));
			}
		}
		finally
		{
			if (input != null)
			{
				input.close();
			}
		}

		return content.toString();
	}
	
	public static void writeTextFile(String path, String content) throws IOException
	{
		writeTextFile(new File(path), content);
	}
	
	public static void writeTextFile(File file, String content) throws IOException
	{
		FileWriter output = null;
		
		try
		{
			output = new FileWriter(file);
			output.write(content);
			output.flush();
		}
		finally
		{
			if (output != null)
			{
				output.close();
			}
		}
	}
}
