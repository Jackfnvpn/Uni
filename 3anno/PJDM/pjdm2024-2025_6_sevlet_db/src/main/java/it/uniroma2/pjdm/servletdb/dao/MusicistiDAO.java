package it.uniroma2.pjdm.servletdb.dao;

import it.uniroma2.pjdm.servletdb.entity.ListaMusicisti;
import it.uniroma2.pjdm.servletdb.entity.Musicista;

public interface MusicistiDAO {

	public Musicista loadMusicista(String name);

	public Musicista loadMusicistaByID(int inputSsn);

	public ListaMusicisti loadTuttiMusicisti();
	
	public int getNumeroMusicisti();

	public int insertMusicista(Musicista musicista);

	public boolean deleteMusicista(int ssn);

	public void closeConnection();

}
