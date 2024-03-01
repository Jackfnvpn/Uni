/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
   
	Esempio di 2 fork con waitpid.

	Il processo genera due figli e si mette in attesa che questi terminino il loro lavoro. Il figlio 1 scrive semplicemente per N1 volte la stringa "Sto aspettando da ...", mentre il figlio 2 scrive per N2 volte la stringa "Sto lavorando da...". La waitpid, in entrambi i casi, è usata nel modo bloccante(terzo argomento posto = 0) per cui il padre attende il termine dei figli. Waitpid, a differenza di wait, permette di specificare il figlio da attendere. Nell'esempio sono stati inclusi vari test point per tracciare il funzionamento del codice.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#define N1 5
#define N2 8
int main() {
  int pid1,pid2, stato1, stato2,i;
  printf ("A)-> padre con pid=%d \n",getpid());
  pid1=fork(); 
  /*  fork ritorna il pid del figlio al padre, il valore 0 al figlio e -1 in caso di errore. In base a questo è possibile separare il codice del 
	padre da quello del figlio. */ 
  if (pid1==-1) {
	  printf("Errore nella fork \n");
	  exit(0);
  } else if (pid1==0) {
	  // codice del figlio 1
	  printf("B)-> figlio1 con pid=%d \n",getpid());
    for (i=0; i<N1; i++) {
	    printf("figlio1: non faccio nulla...da %d cicli\n",i);
	    sleep(1);
    }	
  } else {
	  // codice del padre
    printf("C)-> processo con pid=%d \n",getpid());
    pid2=fork();
    if (pid2==-1) {
      printf ("Errore fork secondo figlio");
      exit(0);
    } else if (pid2==0) {
      // codice figlio 2
  	  printf("D)-> figlio2 con pid=%d \n",getpid());
      for (i=0; i<N2; i++) {
	      printf("figlio2: lavoro...da %d cicli\n",i);
	      sleep(1);
      }	
    } else {
      // codice padre
      printf("E)-> processo con pid=%d \n",getpid());
      waitpid(pid1,&stato1,0);
      printf("F)-> Il figlio1 con pid=%d e' terminato con stato=%d\n",pid1,stato1>>8); 
      waitpid(pid2,&stato2,0);
      printf("G)-> Il figlio2 con pid=%d e' terminato con stato=%d\n",pid2,stato2>>8); 
		}
  }
  // codice padre e figli
  printf("H)-> processo con pid %d  \n",getpid());
}
