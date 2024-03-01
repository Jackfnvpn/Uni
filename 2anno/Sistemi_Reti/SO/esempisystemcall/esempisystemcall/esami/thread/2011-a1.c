/**
	Sistemi operativi e reti (SOR)
	Appello 1 - A.A. 2011/2012
	@author Pietro Frasca 
	@version 1.00 15-01-2016

	Realizzate un programma multi-thread in C, completo di commento, che svolga quanto segue: il thread main crea due thread figli T1 e T2. T1 esegue un ciclo indeterminato durante il quale, ad ogni iesima iterazione, genera un numero intero casuale compreso tra 0 e 9 che memorizza nella posizione iesima  di un buffer di dimensione 64. Il thread T2, appena creato, entra nello stato di bloccato e si risveglia quando il buffer riempito da T1 è pieno. Quando T2 si risveglia, T1 si blocca. A questo punto, T2 esegue la somma di tutti gli elementi del buffer e la stampa sul video. Quindi T1 si risveglia e T2 si blocca.  Il programma segue il comportamento sopra descritto fino a quando la somma degli elementi del buffer calcolata da T2 è maggiore di 200. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define MAX 200
#define DIMBUF 64
pthread_mutex_t M; /* mutex condiviso tra threads */ 
pthread_cond_t cond_buf; // variabile condition
int buf_pieno=0; /* variabile condivisa usata per stabilire la sequenza temporale delle operazioni dei thread */
int fine=0;
int buf[DIMBUF];
/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}
void *T1(void *arg){
	int id=(int)arg;
	int i;
	srand(time(NULL));
	while(1){
		pthread_mutex_lock(&M); /* prologo sez. critica */
		if (fine) pthread_exit(0);
		if (buf_pieno) pthread_cond_wait(&cond_buf,&M); 
		for (i=0;i<DIMBUF;i++){
			buf[i]=getRandom(0,9);
			printf("thread %d: x[%d]=%d \n",id,i,buf[i]); 
		}
		sleep(1);
		buf_pieno=1;
		pthread_cond_signal(&cond_buf);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	}
	pthread_exit(0);
 }

 void *T2(void *arg){
	int id=(int)arg;
	int somma;
	int i;
	while(1){
		pthread_mutex_lock(&M); /* prologo sez. critica */
		if (! buf_pieno) pthread_cond_wait(&cond_buf,&M); 
		somma=0;
		for (i=0;i<DIMBUF;i++){
			somma+=buf[i];
			printf("thread %d: x[%d]=%d somma=%d\n",id,i,buf[i],somma); 
			if (somma > MAX){
				fine=1;
				pthread_exit(0);
			}
		}
		buf_pieno=0;
		sleep(1);
		pthread_cond_signal(&cond_buf);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */	
  }
	pthread_exit(0);
 }
  
int main () {
	int somma=0;
	pthread_t th1, th2;
  pthread_mutex_init (&M,NULL);
  pthread_cond_init(&cond_buf,NULL);
  if (pthread_create(&th1,NULL,T1,(void *)1) !=0) {
    fprintf (stderr, "errore create thread 1 \n");
    exit(1);
  }
  if (pthread_create(&th2,NULL,T2,(void *)2) != 0){
    fprintf (stderr,"errore create thread 2 \n");
    exit(1);
  }
	pthread_join(th2,NULL);
}

