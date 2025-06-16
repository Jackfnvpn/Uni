package it.uniroma2.pjdm.db.dao;

import it.uniroma2.pjdm.db.entity.ListaMusicisti;
import it.uniroma2.pjdm.db.entity.Musicista;

public interface MusicistiDAO {

	public Musicista loadMusicista(String name);

	public Musicista loadMusicistaByID(int inputSsn);

	public ListaMusicisti loadTuttiMusicisti();
	
	public int getNumeroMusicisti();

	public int insertMusicista(Musicista musicista);

	public boolean deleteMusicista(Musicista musicista);

	public void closeConnection();

}
