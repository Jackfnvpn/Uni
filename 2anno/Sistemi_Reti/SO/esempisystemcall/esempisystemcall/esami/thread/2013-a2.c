/** 
	Sistemi operativi e reti (SOR)
	Appello 2 - A.A. 2013/2014
	@author Pietro Frasca
	@version 1.00 16-01-2016
	
	Realizzate un programma multi-thread in C, completo di commento, che svolga quanto segue: il thread main inizializza al valore 0 una variabile intera SOGLIA, poi, crea una matrice di numeri interi di dimensione NxM assegnando a ciascun elemento della  matrice un valore casuale compreso tra 0 e 100. Dopo aver creato la matrice, il thread main crea N thread produttori ciascuno dei quali ha il compito di eseguire la somma di una riga della matrice. Inoltre, i thread produttori devono aggiornare il valore della variabile SOGLIA assegnando ad essa il valore della somma della riga calcolata nel caso in cui tale somma risulti maggiore del valore corrente della variabile SOGLIA. Il valore aggiornato della variabile SOGLIA deve essere visualizzato su schermo dal thread main prima che l’applicazione termini. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define N 100
#define M 1024
pthread_mutex_t mut; /* mutex condiviso tra thread */ 
int a[N][M];
int soglia=0;

void *produttore(void *arg){
	int i=(int)arg;
	int j;
	int sommaRiga=0;
	for(j=0;j<M;j++)
		sommaRiga+=a[i][j];
	pthread_mutex_lock(&mut); /* prologo sez. critica */
	if (sommaRiga > soglia)
		soglia=sommaRiga;
	pthread_mutex_unlock(&mut); /* epilogo sez. critica */
	pthread_exit(0);
}

  
int main () {
	int i,j;
	pthread_t th[N];
	pthread_mutex_init (&mut,NULL);
	srand(time(NULL));
  for (i=0;i<N;i++) {
		for (j=0;j<M;j++)
			a[i][j]=rand()%101;
		
		if (pthread_create(&th[i],NULL,produttore,(int *)i) !=0) {
			fprintf (stderr, "errore create thread i \n");
			exit(1);
		}
		printf("thread = %d creato\n",i);
		pthread_join(th[i],NULL);
	}
	printf("Soglia = %d \n",soglia);
}

