package parte27;

public class Pesce implements Preda,Predatore{

    @Override
    public void caccia() {
        System.out.println("Il pesce grosso va all'attacco");
    }

    @Override
    public void scappa() {
        System.out.println("Il pesce piccolo scappa");
    }
    
}
