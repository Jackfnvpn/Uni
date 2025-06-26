package it.uniroma2.pjdm.esercitazioni._4_jsonAndFile;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;

import it.uniroma2.pjdm.esercitazioni.entity.Docente;

public class LeggiStringheFile {

	public static void main(String[] args) {

		try {
			LoadingFlags loadingFlags = LoadingFlags.from_url;
			BufferedReader br = null;

			/*
			 * Reading the file
			 */
			switch (loadingFlags) {
			case from_plain_file:
				String inputFilePath = "data/docenti.txt";
				br = new BufferedReader(new FileReader(inputFilePath));
				break;
			case from_compressed_file:
				// Percorso del file compresso (.gz) da leggere
				String inputFilePathCompressed = "data/docenti_compressed.txt.gz";
				// Apre uno stream sul file compresso
				FileInputStream fis = new FileInputStream(inputFilePathCompressed);
				// Decomprime il contenuto .gz in tempo reale
				GZIPInputStream gzis = new GZIPInputStream(fis);
				// Converte i byte decompressi in caratteri
				InputStreamReader reader = new InputStreamReader(gzis);
				// Legge il testo riga per riga in modo efficiente
				br = new BufferedReader(reader);
				break;
			case from_url:
				// URI identifica la risorsa in modo generico (più sicuro e moderno di URL)
				URI uri = URI.create("http://sag.art.uniroma2.it/didattica/croce/docenti.txt");
				// URL serve per accedere fisicamente alla risorsa (es. aprire connessioni)
				URL url = uri.toURL();	           
				// Apre una connessione HTTP all'URL specificato
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("GET"); // Richiesta di tipo GET (lettura)
				// Reader per leggere la risposta del server
				br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				break;			
			//IT CANNOT HAPPEN!!! WHY?
			default:
				System.err.println("The input flag is not correct");
				// WARNING BR IS NULL!!!
				break;
			}

			String line;

			ArrayList<Docente> listOfDocenti = new ArrayList<Docente>();
			while ((line = br.readLine()) != null) {

				line = line.trim();

				System.out.println("Input Line:\t" + line);

				String[] strarr = line.split(",");
				System.out.println("Corresponding Array:\t" + Arrays.toString(strarr));
				Docente d = new Docente(strarr[0], strarr[1], Integer.parseInt(strarr[2]));
				

				// Docente d = new Docente(line);
				listOfDocenti.add(d);
				System.out.println();

			}
			System.out.println("Final Array");
			System.out.println(listOfDocenti);
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		
	}

}
