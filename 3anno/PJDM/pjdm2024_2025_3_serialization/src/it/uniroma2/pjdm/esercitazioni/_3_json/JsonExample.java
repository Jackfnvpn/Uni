package it.uniroma2.pjdm.esercitazioni._3_json;

import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma2.pjdm.esercitazioni.entity.Punto;

public class JsonExample {

	public static void main(String[] args) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("my_integer_value", 4);
		jsonObject.put("my_float_value", 3f);
		jsonObject.put("my_string_value", "ciao");
		int[] array = { 1, 5, 5 };
		jsonObject.put("my_array", array);
		
		
		Punto p = new Punto(4, 6);
		jsonObject.put("mio_punto", p.toJsonObject());
		
		
		JSONObject puntoinJson = (JSONObject) jsonObject.get("mio_punto");
		

		System.out.println("jsonObject:\t" + jsonObject);
		
		System.out.println("puntoinJson:\t" + puntoinJson);
		
		Punto newPunto = Punto.parse(puntoinJson);
		
		System.out.println(newPunto.getX());

		
	}
	

}
