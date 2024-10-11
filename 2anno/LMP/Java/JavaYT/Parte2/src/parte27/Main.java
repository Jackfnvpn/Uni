package parte27;

public class Main {
    public static void main(String[] args) {
        Leone leone=new Leone();
        Gazzelle gazzella=new Gazzelle();

        leone.caccia();
        gazzella.scappa();

        Pesce pesce = new Pesce();
        pesce.caccia();
        pesce.scappa();
    }
    
}
