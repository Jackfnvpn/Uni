package parte29;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<String,String> capitali = new HashMap<String,String>();    
        capitali.put("Italia", "Roma");
        capitali.put("Inghilterra", "Londra");
        capitali.put("Francia", "Parigi");  

        System.out.println(capitali.get("Italia"));  

        capitali.remove("Francia");
        capitali.clear();
        System.out.println(capitali);

    }

}
