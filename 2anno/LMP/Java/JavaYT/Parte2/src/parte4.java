import java.util.Scanner;
public class parte4 {
    public static void main(String[] args) {
        Scanner scanner  = new Scanner(System.in);
        
        System.out.println("Qual'è il tuo nome?");
        String nome=scanner.nextLine();  

        System.out.println("Mi chiamo "+nome);

        System.out.println("Qual'è il tuo cognome?");
        String cognome=scanner.nextLine();  
        System.out.println("Il mio cognome è "+cognome);


        System.out.println("Qual'è la tua età?");
        String eta=scanner.nextLine();  
        System.out.println("Ho "+eta+" anni");


        System.out.println("Qual'è la tua città?");
        String citta=scanner.nextLine();  
        System.out.println("Vivo ad "+citta);  

        int x=10;
        x+=1;

        System.out.println(x);




    }
}
