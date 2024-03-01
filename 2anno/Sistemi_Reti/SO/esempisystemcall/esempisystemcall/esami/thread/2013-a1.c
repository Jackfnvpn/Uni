/**
	Sistemi operativi e reti (SOR)
	Appello 1 - A.A. 2013/2014
	@author Pietro Frasca
	@version 1.00 16-01-2016
	
	Realizzate un programma multi-thread in C, completo di commento, che svolga quanto segue: il thread main crea continuamente nuovi thread ad intervalli di tempo compresi tra 1 e 3 secondi. I thread creati possono essere di due tipi: Tipo1 e Tipo2. La scelta del tipo è casuale. Un thread di Tipo1 semplicemente aggiunge, ogni secondo, per 5 volte ad una variabile TOT condivisa (da gestire in mutua esclusione) un valore casuale compreso tra 1 e 1000 e quindi termina. Un thread di Tipo2, invece, sottrae, ogni secondo, al valore di TOT un valore casuale compreso tra 1 e 500 per 10 volte e termina. Se un thread estrae il numero 17 si blocca e può essere riattivato solo da un altro thread del suo stesso tipo. L’applicazione termina quando un thread estrae il valore 13. Prima di terminare il thread main stampa sullo schermo il valore di TOT e il numero di thread creati. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>

pthread_mutex_t M; /* mutex per mutua esclusione */ 
pthread_cond_t C1; /* variabile condizione per thread Tipo1 */
pthread_cond_t C2; /* variabile condizione per thread Tipo2 */
int TOT=0; /* variabile condivisa */
int FINE=0;
const int X1=1000;
const int X2=500;

void *tipo1(void *arg){
	int id=(int)arg; // identificatore thread
	int x,i;
	srand(time(NULL)+id);
	for (i=0; i<5;i++) {
		x=rand()%X1+1;
		pthread_mutex_lock(&M);
		if (x==13) {
			FINE=1;
			printf ("thread %d x=%d -> FINE \n",id,x);
			pthread_exit((void *)1);
		}	else if(x==17) {
			printf ("thread %d x=%d -> sospeso \n",id,x);
			pthread_cond_wait(&C1, &M);
			printf ("thread %d risvegliato \n",id);
		} else {
			TOT+=x;
			printf("thread %d x=%d, TOT=%d \n",id,x,TOT);
		}
		sleep(1);
		pthread_cond_signal(&C1);
		pthread_mutex_unlock(&M); 
		
	}
}

void *tipo2(void *arg){
	int id=(int)arg; // identificatore thread
	srand(time(NULL)+id);
	int x,i;
	
	for (i=0; i<10;i++) {
		x=rand()%X2+1;
		pthread_mutex_lock(&M);
		if (x==13) {
			FINE=1;
			printf ("thread %d x=%d -> FINE \n",id,x);
			pthread_exit((void *)1);
		}	else if(x==17) { 
			printf ("thread %d x=%d -> sospeso \n",id,x);
			pthread_cond_wait(&C2, &M);
			printf ("thread %d risvegliato \n",id);
		} else {
			TOT-=x;
			printf("thread %d x=%d, TOT=%d \n",id,x,TOT);
		}
		sleep(1);
		pthread_cond_signal(&C2);
		pthread_mutex_unlock(&M); 
	}
}
	
int main () {
	int i=0,tipo,t;
	pthread_t th;
  pthread_mutex_init (&M,NULL);
	pthread_cond_init(&C1,NULL);
	pthread_cond_init(&C2,NULL);
	srand(time(NULL));
	while (!FINE) {
		tipo=rand()%2+1;
		i++;
		printf ("nuovo thread %d, tipo %d \n",i,tipo);
		if (tipo==1){
			if (pthread_create(&th,NULL,tipo1,(int *)i) !=0) {
				fprintf (stderr, "errore create thread  \n");
				exit(1);
			}
		} else {
			if (pthread_create(&th,NULL,tipo2,(int *)i) !=0) {
				fprintf (stderr, "errore create thread  \n");
				exit(1);
			}
		}	
		t=rand()%3+1;
		sleep(t);
	}
	printf("Fine. TOT=%d, # thread=%d \n",TOT,i);	
}
