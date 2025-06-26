package it.uniroma2.pjdm.esercitazioni._4_jsonAndFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import it.uniroma2.pjdm.esercitazioni.entity.Docente;

public class LeggiStringheFileJson {

	public static void main(String[] args) {

		try {
			FileReader fileReader = new FileReader("data/docenti.json");
			BufferedReader br = new BufferedReader(fileReader);

//			URL url = new URL("http://pjdm.netgroup.uniroma2.it/docenti.json");
//			InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
//			BufferedReader br = new BufferedReader(inputStreamReader);

//			URL url = new URL("http://127.0.0.1:8080/pjdm2022-2023_5_my_second_servlet/MyJSONServlet");
//			InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
//			BufferedReader br = new BufferedReader(inputStreamReader);

			String jsonString = "";
			String line;
			while ((line = br.readLine()) != null) {
				jsonString += " " + line;
			}

			// System.out.println(jsonString);

			JSONArray array = new JSONArray(jsonString);

			ArrayList<Docente> docenti = new ArrayList<>();

			for (int i = 0; i < array.length(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				Docente di = new Docente(jsonObject);
				docenti.add(di);
			}

			// PrintStream ps = System.out;
			PrintStream ps = new PrintStream("tmp_out_docenti.txt", "utf-8");

			for (Docente di : docenti) {
				ps.println(di); // it is the same of ps.println(di.toString());

				System.out.println(di);
			}

			ps.close();
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
