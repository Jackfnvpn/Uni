package it.uniroma2.pjdm.esercitazioni._3_json;

import org.json.JSONException;
import org.json.JSONObject;

import it.uniroma2.pjdm.esercitazioni.entity.Punto;

public class JsonExampleWithArray {

	public static void main(String[] args) throws JSONException {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("my_integer_value", 4);
		jsonObject.put("my_float_value", 3f);
		jsonObject.put("my_string_value", "ciao");
		jsonObject.put("my_punto", new Punto(2,4));
		
		System.out.println(jsonObject);
		
		JSONObject jsonObject2 = new JSONObject();
		jsonObject2.put("my_integer_value", 4);
		jsonObject2.put("my_float_value", 3f);
		jsonObject2.put("my_string_value", "ciao");
		jsonObject2.put("my_punto", new Punto(2,4).toJsonObject());
		
		System.out.println(jsonObject2);
		
//		JSONObject puntoJsonObject = jsonObject.getJSONObject("my_punto");
		JSONObject puntoJsonObject2 = jsonObject2.getJSONObject("my_punto");
		Punto p = Punto.parse(puntoJsonObject2);
		
		System.out.println(p);

	}
	
}
