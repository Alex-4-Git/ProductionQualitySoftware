package edu.nyu.cs.jx379.pqs.ps1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * 
 * Utilities class, for file input & output.
 * @author Jing Xia
 *
 */
public class Utils {
	private final static Logger LOGGER = Logger
			.getLogger(Utils.class.getName());

	/**
	 * Suppress default constructor for noninstantiability
	 */
	private Utils() {
		throw new AssertionError();
	}

	/**
	 * This function save a string content to a file with input filename in
	 * local folder.
	 * 
	 * @param filename
	 * @param content
	 * @return If nothing wrong then return true, else return false.
	 */
	public static boolean saveFile(String filename, String content) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(filename, "UTF-8");
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
		writer.print(content);
		writer.close();
		return true;
	}

	/**
	 * Read a file from its filename in local folder and return the content of
	 * this file.
	 * 
	 * @param filename
	 * @return The content string of this file.
	 */
	public static String readFile(String filename) {
		Scanner scanner = null;
		try {
			// scanner = new Scanner(new FileReader(filename),"UTF-8");
			// Read UTF-8
			scanner = new Scanner(new FileInputStream(filename), "UTF-8");
		} catch (FileNotFoundException e) {
			LOGGER.severe("File no found.");
//			System.err.println();
			e.printStackTrace();
		}
		StringBuilder sb = new StringBuilder();
		if (scanner != null && scanner.hasNext()) {
			String sentence = scanner.nextLine();
			sb.append(sentence);
		}
		return sb.toString();
	}
}
