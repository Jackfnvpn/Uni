/**
	Sistemi operativi e reti (SOR)
	Appello 3 - A.A. 2011/2012
	@author Pietro Frasca 
	@version 1.00 16-01-2016

	Realizzate un programma multi-processo in C, completo di commento, che svolge quanto segue: Il processo padre P crea una matrice di numeri interi di dimensione NxM assegnando a ciascun elemento della matrice un valore casuale compreso tra 0 e 1024. Dopo aver creato la matrice, il processo padre crea 2 processi figli P1 e P2. P1 ha il compito di eseguire la somma di tutte le righe pari della matrice (considerate 0 pari) mentre P2 ha il compito di eseguire la somma di tutte le righe dispari.  Ciascun processo figlio comunica la somma che ha calcolato al processo padre e quindi passa nello stato di bloccato. Il processo padre, ottenute le somme dai processi figli, le confronta con il valore di una variabile SOGLIA. Se almeno uno dei valori calcolati dai figli, supera il valore di SOGLIA, il processo padre invia un segnale a ciascuno dei processi figli che ne provoca la riattivazione. I figli, così riattivati,  visualizzano  sullo schermo la somma che hanno calcolato e quindi terminano. Nel caso in cui le somme calcolate dai processi figli non superino il valore di SOGLIA, il padre semplicemente fa terminare l’applicazione multi-processo.
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
	int i,j;
	long somma=0;
	signal(SIGUSR1,handler);
	close (pd[LEGGI]);
	for (i=0;i<N;i+=2)
		for (j=0;j<M;j++)
			somma+=a[i][j];
	
	write(pd[SCRIVI], &somma, sizeof(long));
	pause();	
	printf("figlio1: somma=%ld \n",somma);
}

void figlio2 (int pd[]) {
	int i,j;
	long somma=0;
	signal(SIGUSR1,handler);
	close (pd[LEGGI]);
	for (i=1;i<N;i+=2)
		for (j=0;j<M;j++)
			somma+=a[i][j];
	   
	write(pd[SCRIVI], &somma, sizeof(long));
	pause();
	printf("figlio2: somma=%ld \n",somma);
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
			if (somma1>SOGLIA || somma2>SOGLIA){
				printf("padre invia SIGUSR1 (somma1=%ld, somma2=%ld, soglia=%d)\n",somma1,somma2,SOGLIA);
				kill (pid1,SIGUSR1);
				kill (pid2,SIGUSR1);
			}	else{
				printf("padre invia SIGKILL \n");
				kill(0,SIGKILL);
			}	
		}
	}
}

	

