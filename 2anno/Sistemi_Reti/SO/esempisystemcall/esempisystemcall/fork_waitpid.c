/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio di fork con waitpid.
	il processo genera un figlio e si mette in attesa che questo termini il suo lavoro. Il figlio scrive semplicemente per N1 volte la stringa "Non sto facendo nulla da ...". La waitpid è usata in modo bloccante per cui il padre attende fino a quando il figlio termina. Waitpid, a differenza di wait, permette di specificare il figlio da attendere.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#define N1 5
int main() {
  int pid, stato, i;
  pid=fork(); 
  /*  fork ritorna il pid del figlio al padre, il valore 0 al figlio e -1 in caso di errore. In base a questo è possibile separare il codice del padre da quello del figlio. */ 
  if (pid==-1) {
	  printf("Errore nella fork \n");
	  exit(0);
  } else if (pid==0) {
	  // codice del figlio
	  printf("figlio con pid=%d \n",getpid());
    for (i=0; i<N1; i++) {
			printf("Non sto facendo nulla da... %d cicli\n",i);
	    sleep(1);
    }	
  } else {
	  // codice del padre
    // il padre attende che il figlio termini il suo lavoro
    printf("padre con pid=%d \n",getpid());
    waitpid(pid,&stato,0);
    printf("Il figlio con pid=%d e' terminato con stato=%d \n",pid,stato>>8); 
  }
}
