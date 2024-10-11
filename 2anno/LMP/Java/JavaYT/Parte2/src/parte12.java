public class parte12 {
    public static void main(String[] args) {
        String nome="leonardo";
        boolean risultato=nome.equals("Leonardo");  // case sensitive  
        boolean risultato2=nome.equalsIgnoreCase("Leonardo");  // no case sensitive  
        int risultato3=nome.length();  
        char risultato4=nome.charAt(7);  
        int risultato5=nome.indexOf('l');  
        boolean risultato6=nome.isEmpty();
        String risultato7=nome.toUpperCase();
        String risultato8=nome.toLowerCase();
        String risultato9=nome.trim(); // elimina gli spazi
        String risultato10=nome.replace('o', 'w');

        System.out.println(risultato10);
    }

    
}