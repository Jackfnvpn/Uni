/** 
	Sistemi operativi e reti (SOR)
	Appello 2 - A.A. 2010/2011
	@author Pietro Frasca 
	@version 1.00 15-01-2016

	Realizzate un programma multi thread in C, completo di commento, che svolga quanto segue: il thread main crea due thread figli T1 e T2. Entrambi i thread figli eseguono un ciclo indeterminato durante il quale, ad ogni iterazione, generano un numero intero casuale compreso tra 0 e 1000 che comunicano al padre. Il thread padre, per ogni coppia di numeri che riceve  dai thread figli ne fa la somma e la visualizza. Il programma deve terminare quando i due numeri estratti da T1 e T2 sono uguali o la somma dei due numeri supera il valore 1800. La sequenza temporale delle operazioni eseguite dai thread deve essere: 
	1) T1 estrae un numero e lo comunica al thread padre; 
	2) T2 estrae un numero e lo comunica al thread padre; 
	3) il thread padre fa la somma dei numeri ricevuti ed eventualmente termina il programma; e così via.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define MAX 1000 
#define MIN 0
pthread_mutex_t M; /* mutex condiviso tra threads */ 
pthread_cond_t cond_turno;
int turno=0; /* variabile condivisa usata per stabilire la sequenza temporale delle operazioni dei thread */
int x[2]={0,0}; // numeri estratti dai thread figli
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
	int somma=0,i;
	const int NT=2;
	pthread_t th[NT];
  pthread_mutex_init (&M,NULL);
  pthread_cond_init(&cond_turno,NULL);
	for (i=0;i<NT;i++) {
		if (pthread_create(&th[i],NULL,th_figlio,(int *)i) !=0) {
			fprintf (stderr, "errore create thread %d \n",i);
			exit(1);
		}
	}	
  
	while (1) {
	  pthread_mutex_lock(&M); /* prologo sez. critica */
		while (turno !=2) {pthread_cond_wait(&cond_turno,&M);}	
		somma=x[0]+x[1];
		printf("thread main: somma=%d \n\n",somma); 
		if (somma >1800 || x[0]==x[1]) exit(0);
		turno=0;
		pthread_cond_signal(&cond_turno);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	}
}

