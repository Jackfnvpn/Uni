public class parte11 {  
    public static void main(String[] args) {
        String[][] scuola=new String[3][3];

        String[][] classi={
            {"Rosa","Maria","Carmela"},
            {"Mario","Sara","Paolo"},
            {"Franco","Michele","Giuseppe"}
        };    
        
        for(int classe=0;classe<classi.length;classe++){
            System.out.println();
            for(int alunni=0;alunni<classi[classe].length;alunni++){
                System.out.println(classi[classe][alunni]);
            }

        }


        /*for(String[] classe:classi){
            for(String alunno:classe){
                System.out.println(alunno);
            }
        }*/
    }  

   
    
}
