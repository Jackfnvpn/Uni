public class parte10 {
    public static void main(String[] args) {
        int numeri[]=new int[3];
        numeri[0]=1;
        numeri[1]=2;
        numeri[2]=3;

        System.out.println(numeri.length);
        for(int i=0; i < numeri.length; i++){
            System.out.println(numeri[i]);
        }  
        for(int numero:numeri){
            System.out.println(numero);
        }
    }   
}
