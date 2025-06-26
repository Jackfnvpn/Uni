package it.uniroma2.pjdm.esercitazioni._2_io_text_files;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class WriteToFIle {

	public static void main(String[] args) {

		try {
			String outputFilePath = "tmp_out.txt";

			String encoding = "utf8";

			PrintStream ps = new PrintStream(outputFilePath, encoding);
	
			//NOTA: anche System.out è un oggetto di tipo PrintStream
			//PrintStream ps = System.out;
			ps.println("Questo è un file di output");

			ps.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

}
