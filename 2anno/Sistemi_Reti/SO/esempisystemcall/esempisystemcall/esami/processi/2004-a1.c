/**
	Sistemi operativi (SO)
	Appello 1 - A.A. 2004/2005
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Realizzare un programma in C che svolga  quanto segue: un processo padre genera un processo figlio. Il figlio genera una sequenza di M numeri interi compresi tra 1 e N e li comunica al padre il quale provvede a visualizzarli sullo schermo. Il valore di N è inizializzato dal figlio. Infine, sia il padre che il figlio devono disabilitare il segnale SIGINT. 
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <time.h>
#define SCRIVI 1
#define LEGGI 0
int fd[2]; // descrittore della pipe
int M=10;
/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}
/* handler per il segnale SIGINT */
void handler (int sig){
	printf("Segnale SIGINT intercettato! \n");
}

void figlio () {
	int x,i;
	int N=100;
	srand(time(NULL)); // seme per random
	// il processo figlio chiude l'estremo della pipe che non usa 
	close (fd[LEGGI]);	
	for (i=0;i<M;i++) {
		x=getRandom(1,N);
		write(fd[SCRIVI], &x, sizeof(int));
	}
}

int main() {
	int pid1;
	int N,i,x;
	signal(SIGINT,handler); // gestione segnale SIGINT
	// crea la pipe per la comunicazione tra padre e filglio 
	if (pipe(fd)<0) {
		printf("errore pipe");
		exit(1);
	}
	pid1=fork(); // crea processo figlio
	switch(pid1) {
	case -1:
		// errore fork
		exit(1);
	case 0:
		// codice eseguito dal processo figlio
		figlio();
		break;
	default:
		// codice eseguito dal processo padre
		// il processo padre chiude l'estremo della pipe che non usa 
		close (fd[SCRIVI]); 
		for (i=0;i<M;i++) {
			read(fd[LEGGI], &x, sizeof(int));
			printf("Numero = %d \n",x);
			sleep (1);
		}
	}
}
