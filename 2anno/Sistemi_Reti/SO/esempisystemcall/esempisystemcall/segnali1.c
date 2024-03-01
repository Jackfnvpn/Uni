/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Per provare questo programma, è necessario compilarlo ed eseguirlo in background. Inviare quindi i segnali SIGUSR1, SIGUSR2 o di terminazione con il comando kill. 
	Il PID del processo viene visualizzato all'inizio dell'esecuzione.
	Esempio: 
	1) aprire uno shell (terminale)
	2) compilare il programma: gcc segnali1.c -o segnali1
	3) lanciare il programma in background: ./segnali1 &             
	4) inviare il segnale SIGUSR1: kill -SIGUSR1 <PID processo>
	5) inviare il segnale SIGUSR2: kill -SIGUSR2 <PID processo>
	6) inviare il segnale SIGINT: kill -SIGINT <PID processo>
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

void handler(int signum) {
  printf("Segnale ricevuto: %d\n",signum);
}

int main(int argc, char *argv[]) {
  printf("Il mio PID e': %d \n", getpid());
  signal(SIGUSR1, handler); // segnale gestito
  signal(SIGUSR2, handler); // segnale gestito
  signal(SIGINT, SIG_IGN); // segnale ignorato
  while(1) {
		printf ("Attendo segnale...\n");
    sleep(5);
  }
}

