package it.uniroma2.pjdm.esercitazioni._2_io_text_files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ReadFromFIle {

	public static void main(String[] args) {

		try {

			if (args.length != 1) {
				System.err.println("Il path del file è obbligatorio");
				return;
			}

			String inputFilePath = args[0];

			// Notare File.separator: permette di passare da windows a unix gestendo
			// opportunamente "/" e "\" autoamticamente
			// String inputFilePath = "data" + File.separator + "docenti.txt";

			FileReader fileReader = new FileReader(inputFilePath);
			BufferedReader br = new BufferedReader(fileReader);

			String line;

			while ((line = br.readLine()) != null) {

				String[] split = line.split(",");

				System.out.println(line);
				System.out.println(split[0]);
				System.out.println(split[1]);
				System.out.println(split[2]);
				System.out.println("************");
			}

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
