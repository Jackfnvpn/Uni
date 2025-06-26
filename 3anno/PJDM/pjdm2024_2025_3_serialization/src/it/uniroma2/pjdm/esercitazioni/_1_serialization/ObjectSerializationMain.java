package it.uniroma2.pjdm.esercitazioni._1_serialization;

import java.io.IOException;

import it.uniroma2.pjdm.esercitazioni.entity.Punto;

public class ObjectSerializationMain {

	public static void main(String[] args) {

		Punto p = new Punto(4, 6);
		
		String filePath = "myPunto.obj";

		try {
			p.saveOnFile(filePath);
		} catch (IOException e) {			
			e.printStackTrace();
			System.err.println("Error while writing in " + filePath);
			// better to stop...
			return;
		}

		Punto p2 = null;
		
		try {
			p2 = Punto.loadFromFile(filePath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Error deserializing the Punto object");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error while reading " + filePath);
		}

		/*
		 * PAY ATTENTION: if something fails when loading the file, here p2 is null!!!
		 */
		System.out.println("original object:\t"+ p);
		System.out.println("reloaded object:\t"+ p2);
		System.out.println(p == p2);
		System.out.println(p.equals(p2));

	}

}
