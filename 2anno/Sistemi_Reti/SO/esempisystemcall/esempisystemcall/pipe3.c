/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio di comunicazione mediante pipe, tra due processi che sono in relazione di parentela. Il processo figlio legge una stringa da tastiera e la comunica al padre tramite una pipe. Il padre legge la stringa dalla pipe e la visualizza sullo schermo.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
#define LEGGI 0
#define SCRIVI 1
#define DIM 256
int main(){
  int fd[2];
  int pid,i,stato;
  char buffer[DIM];
  if(pipe(fd)<0){ 
    printf("Errore pipe");
    exit(1);
  }

  if((pid=fork())<0){
     printf("Errore fork");
     exit(1);
  } else if (pid==0){
    // figlio
    close(fd[LEGGI]); //chiude l'estremo del canale che non usa
    do {
			printf("SOR# "); //prompt
      fgets(buffer,DIM,stdin);
      write(fd[SCRIVI],buffer,DIM);// scrive nella pipe
    } while (strcmp(buffer,".\n")); // fine del ciclo quando si digita il punto e si va a capo.
    exit(0);
  } else {
    //padre
    close(fd[SCRIVI]);//chiude l'estremo del canale che non usa
    do {
      read(fd[LEGGI],buffer,DIM);// legge dalla pipe
      printf("padre: %s",buffer);
    } while (strcmp(buffer,".\n"));
    wait (&stato);
  }
}
 

  
