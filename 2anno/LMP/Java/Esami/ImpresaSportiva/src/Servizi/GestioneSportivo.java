package Servizi;
import java.util.ArrayList;
import Modelli.Sportivo;
import Modelli.Tipologia;
import java.time.LocalDate;
import java.util.HashMap;

public class GestioneSportivo {
    private ArrayList<Sportivo> listaSportivi=new ArrayList<>();
    private HashMap <String,Integer> bonusPremio=new HashMap<>();  
    private ArrayList <HashMap<String,Integer>> stessoTipoMese=new ArrayList<>();  

    private final int N=10;

    public void addSportivo(String nome, String cognome, LocalDate dataAssunzione, Tipologia tipologiaSportivo, int livelloStipendiale){
        Sportivo sportivo=new Sportivo(nome, cognome, dataAssunzione, tipologiaSportivo, livelloStipendiale);
        listaSportivi.add(sportivo);
    }  


    public HashMap<String,Integer> bonusPremio(){
        for(Sportivo sportivi: listaSportivi){
            if (sportivi.getLivelloStipendiale()>=3){
                int countGol=0;
                for(int reti: sportivi.getReti().values()){
                    if (reti > N){
                        countGol++;
                    }
                }  

                bonusPremio.put(sportivi.getNome()+sportivi.getCognome(), countGol);
            }
        }
        return bonusPremio;
    }
      
    public ArrayList<HashMap<String,Integer>> stessoTipoMese(){
        HashMap <,Integer>coppieTipoMese
        for(Sportivo sportivo: listaSportivi){
            for (Sportivo altroSportivo:listaSportivi){
                String nomeSportivoCorrente=sportivo.getNome()+sportivo.getCognome();
                String nomeAltroSportivo=altroSportivo.getNome()+altroSportivo.getCognome();
                if(nomeSportivoCorrente.equals(nomeAltroSportivo)){
                    if (sportivo.getDataAssunzione().getMonth()==altroSportivo.getDataAssunzione().getMonth() && sportivo.getTipologiaSportivo()==altroSportivo.getTipologiaSportivo()){
                        stessoTipoMese.add()
                    }

                }
            }
            
        }
    }

}
