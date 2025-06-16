package it.uniroma2.pjdm.db.main;

import it.uniroma2.pjdm.db.dao.MusicistiDAO;
import it.uniroma2.pjdm.db.dao.MusicistiDAOJDBCImpl;
import it.uniroma2.pjdm.db.entity.ListaMusicisti;
import it.uniroma2.pjdm.db.entity.Musicista;

public class Main {

	public static void main(String[] args) {

		String ip = "127.0.0.1";
		String port = "3306";
		String dbName = "musicisti";
		String userName = "danilo";
		String password = "danilo"; // really strong pwd!

		MusicistiDAO dao = new MusicistiDAOJDBCImpl(ip, port, dbName, userName, password);

		//dao.doStuff(); this method is specific from the class MusicistiDAOJDBCImpl
		
		String inputName = "Ron Wood";

		System.out.println("Provo a leggere " + inputName);
		Musicista loadedMusician = dao.loadMusicista(inputName);
		System.out.println(loadedMusician);

		System.out.println("La sua rappresentazione in JSON");
		System.out.println(loadedMusician.toJSON());

		System.out.println("\nCarico la lista completa dei musicisti");
		ListaMusicisti tuttiMusicisti = dao.loadTuttiMusicisti();
		System.out.println(tuttiMusicisti);

		System.out.println("\nLista dei musicisti in JSON");
		System.out.println(tuttiMusicisti.toJSONString());

		System.out.println("\nIl numero di Musicisti è " + dao.getNumeroMusicisti());
		//PERCHE' NON LA SEGUENTE ISTRUZIONE?
		//System.out.println("\nIl numero di Musicisti è " + dao.loadTuttiMusicisti().size());

		Musicista newMusicista = new Musicista("James Hatefield", 9);

		dao.insertMusicista(newMusicista);

		System.out.println("\nIl numero di Musicisti è " + dao.getNumeroMusicisti() + " dopo l'aggiunta di "
				+ newMusicista);

		System.out.println("\nOra i musicisti sono:");
		System.out.println(dao.loadTuttiMusicisti().toJSONArray());

		dao.deleteMusicista(newMusicista);
		
		System.out.println("\nAlla fine i musicisti sono tornati ad essere:");
		System.out.println(dao.loadTuttiMusicisti().toJSONArray());
		System.out.println("\nIl numero di Musicisti è " + dao.getNumeroMusicisti() + " dopo la rimozione di "
				+ newMusicista);

		
		dao.closeConnection();
		System.out.println("\nConnection Closed");
	}

}
