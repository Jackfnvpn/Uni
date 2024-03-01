/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio d'uso di fork.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
int main() {
  pid_t pid;
  pid=fork(); 
  /*  fork ritorna il pid del figlio al padre, il valore 0 al figlio e -1 in caso di errore. In base a questo è possibile separare il codice del padre da quello del figlio. */ 
  if (pid==-1) {
	  printf("Errore nella fork \n");
	  exit(0);
  } else if (pid==0) {
	  // codice del figlio
	  printf("figlio con pid=%d \n",getpid());
  } else {
	  // codice del padre
    printf("padre con pid=%d \n",getpid());
  }
  // codice eseguito dal padre e dal figlio
  printf("Questa istruzione printf e' eseguita dal processo con pid=%d \n", getpid());
}