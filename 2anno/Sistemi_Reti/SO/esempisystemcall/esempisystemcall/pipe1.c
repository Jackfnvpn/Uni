/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio uso di pipe.
	Un processo crea tramite fork() un processo figlio. I due processi, padre e figlio, comunicano attraverso pipe, usando le write e read. Il canale 0 è per lettura, il canale 1 per la scrittura.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#define DIM 256
#define LEGGI 0
#define SCRIVI 1

int main() {
  int	n, pd[2];
  int  pid;
  char line[DIM];
  
  if (pipe(pd) < 0) {
	printf ("errore pipe");
      exit(1);
  }  
  if ( (pid = fork()) < 0) {
    printf("errore fork");
    exit(1);
  } else if (pid > 0) {		
    /* padre */
    close(pd[LEGGI]); // chiude il canale che non usa
    write(pd[SCRIVI], "Ciao, figlio", DIM);
  } else {				
    /* figlio */
    close(pd[SCRIVI]);// chiude il canale che non usa
    n = read(pd[LEGGI], line, DIM);
    printf("%s",line);
  }
}
