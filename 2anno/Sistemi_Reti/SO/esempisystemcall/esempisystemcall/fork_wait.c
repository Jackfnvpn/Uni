/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio di fork con wait.
	Il processo genera un figlio e si mette in attesa che questo termini il suo lavoro. Il figlio scrive semplicemente per N volte la stringa "Sto aspettando da ...". La wait è bloccante per cui il padre attende fino a quando il figlio termina"
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#define N 4

int main() {
  int ret, stato, i, pid_figlio;
  ret=fork(); 
  /*  fork ritorna il pid del figlio al padre, il valore 0 al figlio e -1 in caso di errore. In base a questo è possibile separare il codice del padre da quello del figlio. */ 
  if (ret==-1) {
		printf("Errore nella fork \n");
		exit(0);
  } else if (ret==0) {
		// codice del figlio
		printf("Sono il figlio con pid=%d \n",getpid());
    sleep(1);
    for (i=0; i<N; i++) {
			printf("Sto aspettando... da %d cicli \n",i);
			sleep(1);
    }	
  } else {
		// codice del padre
    // il padre attende che il figlio termini il suo lavoro
    printf("Sono il padre con pid=%d \n",getpid());
    pid_figlio=wait(&stato);
    printf("Il figlio con pid=%d e' terminato con stato=%d\n",pid_figlio,stato>>8); 
  }
  // codice eseguito dal padre e dal figlio
  printf("Questa istruzione printf e' eseguita da: pid=%d \n", getpid());
}
