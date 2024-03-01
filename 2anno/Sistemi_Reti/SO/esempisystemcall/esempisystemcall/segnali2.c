/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio con signal, kill, getpid e getppid.
	Un processo crea un processo figlio ed entra in un ciclo vuoto determinato dalla variabile globale cont. Il figlio invia per 2 volte il segnale SIGUSR1 al padre, tramite kill.
*/  
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
int  stato, cont=0;
void  azione(int numsig){ 
  printf("Processo con pid=%d ha ricevuto il segnale %d \n", getpid(),numsig);
	cont++;
}
void main (){  
  int pidPadre, pidFiglio;
  pidPadre = getpid(); // pid del padre
	printf("Processo con pid=%d\n",pidPadre);
  signal (SIGUSR1, azione);
  if ((pidFiglio=fork()) < 0) {
    perror("errore"); 
    exit(1);
  } 
  if (pidFiglio == 0) {
    /* figlio
       Il figlio invia il segnale SIGUSR1 al padre */ 
    kill(getppid(), SIGUSR1); // invio del segnale SIGUSR1 al padre
    sleep(2); // attesa di 2 sec
    kill(pidPadre, SIGUSR1); // invio del segnale SIGUSR1 al padre
    exit(1); 
  } else {
    // padre
    // cont nel ciclo deve essere pari al numero di kill inviati 
    while (cont != 2){} // ciclo vuoto che termina quando cont=2
    wait(&stato);
    printf("numero segnali ricevuti=%d, stato del figlio=%d \n", cont, stato>>8);
    exit(0);
  }
}
