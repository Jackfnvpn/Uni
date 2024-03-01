/** 
	Sistemi operativi e reti (SOR)
	Appello 2 - A.A. 2011/2012
	@author Pietro Frasca
	@version 1.00 15-01-2016

	Realizzate un programma multi-thread in C, completo di commento, che svolga quanto segue: il thread main crea una matrice di numeri interi di dimensione NxM assegnando a ciascun elemento della matrice un valore casuale compreso tra 0 e 255. Dopo aver creato la matrice, il thread main crea N thread figli ciascuno dei quali ha il compito di eseguire la somma di una riga della matrice. Ciascun thread aggiunge la somma che ha calcolato ad una variabile di nome SOMMA che al termine dell'esecuzione del programma conterrà la somma di tutti gli elemti della matrice. Il valore della variabile SOMMA deve essere stampato su video dal thread main.
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#define N 20
#define M 1024
pthread_mutex_t mut; /* mutex condiviso tra threads */ 
int a[N][M];
int somma=0;
/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}
void *thread_mat(void *arg){
	int i=(int)arg;
	int j;
	int sommaRiga=0;
	for(j=0;j<M;j++)
		sommaRiga+=a[i][j];
	pthread_mutex_lock(&mut); /* prologo sez. critica */
	somma+=sommaRiga; 
	printf("thread %d: sommaRiga=%d somma=%d\n",i,sommaRiga,somma);
	//sleep(1);
	pthread_mutex_unlock(&mut); /* epilogo sez. critica */
	pthread_exit(0);
 }

int main () {
	int i,j;
	pthread_t th[N];
  pthread_mutex_init (&mut,NULL);
	srand(time(NULL));
  for (i=0;i<N;i++)
		for (j=0;j<M;j++)
			a[i][j]=getRandom(0,255);
			
	for (i=0;i<N;i++)
		if (pthread_create(&th[i],NULL,thread_mat,(void *)i) !=0) {
			fprintf (stderr, "errore create thread 1 \n");
			exit(1);
		}
  for (i=0;i<N;i++)
		pthread_join(th[i],NULL);
	printf("thread main: somma = %d \n",somma);
	
}

