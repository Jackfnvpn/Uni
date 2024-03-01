/**
	Sistemi operativi e reti (SOR)
	Appello 1 - A.A. 2016/2017
	@author Pietro Frasca 
	@version 1.00 14-06-2017

	Realizzate un programma multi-processo in C, completo di commento, che svolge quanto segue: Il processo padre P crea una matrice di numeri interi di dimensione NxM assegnando a ciascun elemento della matrice un valore casuale compreso tra 0 e 1024. Dopo aver creato la matrice, il processo padre crea 2 processi figli P1 e P2. P1 ha il compito di eseguire la somma di tutte le righe pari della matrice (considerate 0 pari) mentre P2 ha il compito di eseguire la somma di tutte le righe dispari.  Ciascun processo figlio comunica la somma che ha calcolato al processo padre e quindi passa nello stato di bloccato. Il processo padre, ottenute le somme dai processi figli, ne calcola il prodotto e verifica se questo abbia superato il valore di una variabile SOGLIA. Se il valore del prodotto  è inferiore al valore di SOGLIA, il processo padre riattiva i figli inviando ad essi un segnale. I figli, così riattivati, ripetono le operazioni precedentemente descritte. IL programma termina nel caso in cui il prodotto calcolato dal padre superi il valore di SOGLIA. (5 punti) 

*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#define LEGGI 0
#define SCRIVI 1
#define N 10
#define M 20
#define MAX 1024
int a[N][M];
long SOGLIA=M*N*MAX/4;


/* handler per il segnale SIGINT */
void handler (int sig){
	printf("segnale %d\n",sig);
}

void figlio1 (int pd[]) {
	signal(SIGUSR1,handler);
	int i,j;
	long somma=0;
	
	close (pd[LEGGI]);
	do {
		for (i=0;i<N;i+=2)
			for (j=0;j<M;j++)
				somma+=a[i][j];
	
		write(pd[SCRIVI], &somma, sizeof(long));
		pause();	
		printf("figlio1: somma=%ld \n",somma);
	while (1);
}

void figlio2 (int pd[]) {
	int i,j;
	long somma=0;
	signal(SIGUSR1,handler);
	close (pd[LEGGI]);
	do {
		for (i=1;i<N;i+=2)
			for (j=0;j<M;j++)
				somma+=a[i][j];
	   
		write(pd[SCRIVI], &somma, sizeof(long));
		pause();
		printf("figlio2: somma=%ld \n",somma);
	while (1);
}

int main() {
	pid_t pid1, pid2;
	long somma1, somma2=0;
	int i,j;
	int pd1[2],pd2[2];
	
	srand(time(NULL)); // seme per random
	for (i=0;i<N;i++)
		for (j=0;j<M;j++)
			a[i][j]=rand()%1025;                                                   
	/* crea pipe per la comunicazione tra padre e figlio1 */
	if (pipe(pd1)<0) {
		printf("errore pipe");
		exit(1);
	}
	/* crea pipe per la comunicazione tra padre e figlio2 */
	if (pipe(pd2)<0) {
		printf("errore pipe");
		exit(1);
	}
	pid1=fork(); // crea processo figlio1
	switch(pid1) {
	case -1:
		// errore fork
		exit(1);
	case 0:
		// codice esguito dal processo figlio1
		figlio1(pd1);
		break;
	default:
		//codice eseguito dal processo padre
		pid2=fork(); // crea processo figlio2
		switch(pid2) {
		case -1:
			// errore fork
			exit(1);
		case 0:
			// codice esguito dal processo figlio
			figlio2(pd2);
			break;
		default:
			//codice eseguito dal processo padre
			close(pd1[SCRIVI]); // chiude estremo della pipe che non usa
			close(pd2[SCRIVI]); // chiude estremo della pipe che non usa
			printf("padre legge pipe1 \n");
			read(pd1[LEGGI], &somma1,sizeof(long)); // legge dalla pipe figlio1
			printf("padre legge pipe2 \n");
			read(pd2[LEGGI],&somma2,sizeof(long)); // legge dalla pipe figlio2
			
			if (somma1*somma2<SOGLIA){
				printf("padre invia SIGUSR1 (somma1=%ld, somma2=%ld, soglia=%d)\n",somma1,somma2,SOGLIA);
				kill (pid1,SIGUSR1);
				kill (pid2,SIGUSR1);
			}	else{
				/* Termina applicazione */	
				printf("padre invia SIGKILL \n");
				kill(0,SIGKILL);
			}	
		}
	}
}

	

