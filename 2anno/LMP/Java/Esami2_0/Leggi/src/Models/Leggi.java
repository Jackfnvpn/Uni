package Models;
import java.time.LocalDate;
import java.util.ArrayList;
import Models.Articolo;
import Enum.TipologiaLegge;

public class Leggi {
	private TipologiaLegge tipologia;
	private LocalDate data;
	private String identità;
	private ArrayList<String> allegati;
	private String[] intestazione;
	private ArrayList<Articolo> articoli;
	private String[] conclusioni;
	public Leggi(TipologiaLegge tipologia, LocalDate data, ArrayList<String> allegati,
			String[] intestazione, String[] conclusioni) {
		super();
		this.tipologia = tipologia;
		this.data = data;
		this.identità = data.getYear()+"/"+data.getMonth()+"/"+data.getDayOfMonth()+" "+tipologia;
		this.allegati = allegati;
		this.intestazione = intestazione;
		this.conclusioni = conclusioni;
	}
	
	
	public TipologiaLegge getTipologia() {
		return tipologia;
	}
	public void setTipologia(TipologiaLegge tipologia) {
		this.tipologia = tipologia;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	public String getIdentità() {
		return identità;
	}
	public void setIdentità(String identità) {
		this.identità = identità;
	}
	public ArrayList<String> getAllegati() {
		return allegati;
	}
	public void setAllegati(ArrayList<String> allegati) {
		this.allegati = allegati;
	}
	public String[] getIntestazione() {
		return intestazione;
	}
	public void setIntestazione(String[] intestazione) {
		this.intestazione = intestazione;
	}
	public ArrayList<Articolo> getArticoli() {
		return articoli;
	}
	public void setArticoli(ArrayList<Articolo> articoli) {
		this.articoli = articoli;
	}
	public String[] getConclusioni() {
		return conclusioni;
	}
	public void setConclusioni(String[] conclusioni) {
		this.conclusioni = conclusioni;
	}
	
	
	

}
