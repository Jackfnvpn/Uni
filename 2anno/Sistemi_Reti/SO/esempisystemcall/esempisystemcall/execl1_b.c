/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio di fork con execl. 
	Questo codice viene eseguito mediante chiamata execl dal processo execl1_a. Esso visualizza i parametri che ha ricevuto da exec11_a.
	NOTA: per le spiegazioni vedere l'esempio execl1_a.c
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
 int i;
 printf ("Sono il processo con pid=%d chiamato con execl\n",getpid());
 printf("Il mio processo padre ha PID %d \n",getppid());
 for (i=0;i<argc;i++)
   printf("ho ricevuto il parametro %d = %s \n",i,argv[i]);
  
}
