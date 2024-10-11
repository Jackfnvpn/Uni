package parte24.pacchetto1;
import parte24.pacchetto2.*;  

/*              class   package     subclass    word
 * public       si      si          si          si
 * protected    si      si          si          no
 * default      si      si          no          no  senza specificare il modificatore d'accesso
 * private      si      no          no          no
 */
public class Main {
    public static void main(String[] args) {
        A a =new A();

        System.out.println(a.nome);
        
    }

    
}
