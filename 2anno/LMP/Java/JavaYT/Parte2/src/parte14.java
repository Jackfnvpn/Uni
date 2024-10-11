// gli arraylist sono array ridimensionabili

import java.util.ArrayList;

public class parte14 {
    public static void main(String[] args) {
        ArrayList<String> persone = new ArrayList<String>();
        persone.add("Luca");
        persone.add("Marco");
        persone.add("Anna");

        System.out.println(persone.get(0));
        
        for(int i=0; i<persone.size();i++){
            System.out.println(persone.get(i));
        }

        persone.set(2, "Paola");
        persone.remove(0);
        persone.clear();

        ArrayList<Integer> numeri= new ArrayList<Integer>();
        numeri.add(100);
        numeri.add(200);


        ArrayList<ArrayList<String>> classi=new ArrayList();  

        ArrayList<String> classe1=new ArrayList();
        classe1.add("Giacomo");
        classe1.add("Sara");
        ArrayList<String> classe2=new ArrayList<>();

        classe2.add("Luca");
        classe2.add("Michele");

        classi.add(classe1);
        classi.add(classe2);

        for(int i=0; i < classi.size();i++){
            System.out.println();
            for(int j=0;j<classi.get(i).size();j++){
                System.out.println(classi.get(i).get(j));
            }
        }


    }
    
}
