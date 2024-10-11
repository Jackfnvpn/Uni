public class Parte8 {
    public static void main(String[] args) {
        int i=0;
        while (i<0) {
            System.out.println(i);
            i++;
        }

        do{
            System.out.println("do: "+i);
        } while(i<0);
    }
    
}
