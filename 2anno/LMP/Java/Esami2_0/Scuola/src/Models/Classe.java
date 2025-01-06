package Models;
import java.time.LocalDate;

public class Classe {

	private int annoStandard;
	private int anno;
	private String sezione;
	private String nome;
	
	public Classe(int annoStandard, int anno, String sezione) {
	
		this.annoStandard = annoStandard;
		this.setAnno(anno);
		this.sezione = sezione;
		this.nome = anno+sezione;
	}

	public int getAnnoStandard() {
		return annoStandard;
	}

	public void setAnnoStandard(int annoStandard) {
		this.annoStandard = annoStandard;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		if(anno < 1 || anno > 5) {
			throw new IllegalArgumentException("L'anno deve essere tra 1 e 5");
		}
		this.anno = anno;
	}

	public String getSezione() {
		return sezione;
	}

	public void setSezione(String sezione) {
		this.sezione = sezione;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	
	

}
