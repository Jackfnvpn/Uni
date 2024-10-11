

public class parte15 {

    public static void main(String[] args) {
        faiPasta("sugo");

        int doppioadd=addizione(5, 5);
        int triploadd=addizione(4,4,4);
        System.out.println(triploadd);
        
    }

    static void faiPasta(String condimento){
        System.out.println("cucino pasta al "+ condimento);
    }

    static int addizione(int a, int b){
        int c=a+b;
        return c;
    }

    static int addizione(int a, int b, int d){
        int c=a+b+d;
        return c;
    }
}