/**
	Sistemi operativi e reti (SOR)
	Appello 4 - A.A. 2010/2011
	@author Pietro Frasca 
	@version 1.00 15-01-2016
	
	Realizzate un programma multi-thread in C, completo di commento, che svolga quanto segue: il thread main crea due thread figli T1 e T2. Entrambi i thread figli eseguono un ciclo indeterminato durante il quale, ad ogni iterazione, generano un numero intero casuale compreso tra 0 e 5 che comunicano al padre. Il thread padre, per ogni coppia di numeri che riceve dai thread figli ne fa la somma e nel caso essa sia un numero pari incrementa di 1 la variabile S1, nel caso in cui la somma sia un numero dispari incrementa di 1 la variabile S2; se la somma vale 0 assegna sia ad S1 che a S2 il valore 0. Quanto il thread padre verifica che il valore di S1 o di S2 ha superato il valore 11, visualizza sullo schermo il valore delle due variabili e il programma termina. La sequenza temporale delle operazioni eseguite dai thread deve essere: 
	1) T1 estrae un numero e lo comunica al thread padre; 
	2) T2 estrae un numero e lo comunica al thread padre; 
	3) il thread padre esegue le operazioni sopra descritte; 
	e così via.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define MAX 5
#define MIN 0
pthread_mutex_t M; /* mutex condiviso tra threads */ 
pthread_cond_t cond_turno;
int turno=0; /* variabile condivisa usata per stabilire la sequenza temporale delle operazioni dei thread */
int x[2]={0,0};

/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}

void *th_figlio(void *arg){
	int id=(int)arg;
	srand(time(NULL)+id);
	while(1){
		pthread_mutex_lock(&M); /* prologo sez. critica */
		while (turno!=id) {pthread_cond_wait(&cond_turno,&M);} 
		x[id]=getRandom(MIN,MAX);
		turno++;
		printf("thread %d: x=%d \n",id,x[id]); 
		sleep(1);
		pthread_cond_signal(&cond_turno);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	}
}

int main () {
	int somma=0;
	int s1=0;
	int s2=0;
	pthread_t th1, th2;
  pthread_mutex_init (&M,NULL);
  pthread_cond_init(&cond_turno,NULL);
  if (pthread_create(&th1,NULL,th_figlio,(int *)0) !=0) {
    fprintf (stderr, "errore create thread 1 \n");
    exit(1);
  }
  if (pthread_create(&th2,NULL,th_figlio,(int *)1) != 0){
    fprintf (stderr,"errore create thread 2 \n");
    exit(1);
  }
	
	 do {
	  pthread_mutex_lock(&M); /* prologo sez. critica */
		while (turno !=2){pthread_cond_wait(&cond_turno,&M);}	
		somma=x[0]+x[1];
		if (somma==0) {
			s1=0; s2=0;
		} else if (somma%2) s2++;
		else
			s1++;
		
		printf("thread main: somma=%d s1=%d s2=%d\n\n",somma,s1,s2); 
		turno=0;
		pthread_cond_signal(&cond_turno);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	} while (s1<12 && s2<12);
}

