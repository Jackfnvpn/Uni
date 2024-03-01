/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio uso di 2 pipe.
	Il processo padre crea due figli pid1 e pid2, con i quali comunica attraverso le due pipe pd1 e pd2 rispettivamnete. Il canale 0 è per lettura, il canale 1 per la scrittura.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
#define DIM 256
#define LEGGI 0
#define SCRIVI 1

int main() {
  int	pd1[2],pd2[2],n;
  int  pid1,pid2; // pid figlio1 e pid figlio2
  int s1,s2; // stato figlio1 e stato figlio2 (wait)
  char messag[DIM]; // messaggio 
  if (pipe(pd1) <0) {
		printf ("errore pipe");
    exit(1);
  } 
 
  if ( (pid1 = fork()) < 0) {
    printf("errore fork");
    exit(1);
  } else if (pid1 > 0) {		
    /* Processo padre. 
		Crea una nuova pipe per comunicare con il secondo figlio. */ 
    if (pipe(pd2) <0) {
			printf ("errore pipe");
      exit(1);
    }  
		if ((pid2=fork())<0) {
			printf ("errore pipe");
			exit(1);
		} else if (pid2==0) {
			/* figlio2.
         Chiude la pipe pd2 in scrittura. */
      close (pd2[SCRIVI]);
      n=read(pd2[LEGGI],messag,DIM);
      printf("figlio2 riceve: %s \n",messag);
    } else {
      /* padre.
         chiude le pipe pd1 e pd2 in lettura. */   
        close(pd2[LEGGI]);
        write(pd2[SCRIVI],"Ciao, secondo figlio", DIM);   
        close(pd1[LEGGI]);
        write(pd1[SCRIVI],"Ciao, primo figlio", DIM);
        wait(&s1);
        wait(&s2);
    }
  } else {				
    /* figlio1.
       chiude la pipe pd1 in scrittura. */  
    close(pd1[SCRIVI]);
    n=read(pd1[LEGGI], messag, DIM);
    printf("figlio1 riceve: %s \n",messag);
  }
  
}
