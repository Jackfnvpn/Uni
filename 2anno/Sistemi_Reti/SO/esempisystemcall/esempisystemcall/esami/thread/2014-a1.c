/** 
	Sistemi operativi e reti (SOR)
	Appello 1 - A.A. 2014/2015
	@author Pietro Frasca
	@version 1.00 16-01-2016

	Realizzate un programma multi thread in C, completo di commento, che simuli il comportamento di elettori che si recano ad un seggio elettorale per votare, in base alle seguenti specifiche: 
	- gli elettori arrivano al seggio in istanti di tempo random;
	- nel seggio possono essere presenti al massimo 3 elettori allo stesso tempo;
	- un elettore deve attendere sulla porta se nel seggio sono già presenti 3 elettori;
	- quando entra nel seggio, un elettore impiega un tempo random per votare;
	- assegnate i tempi random di arrivo e di votazione in modo che si possa produrre 
	una coda di elettori sulla porta.
	- dimensionate la simulazione per 100 elettori. 
	L'applicazione, durante l'esecuzione, deve visualizzare i seguenti messaggi, in accordo con gli stati in cui si può trovare l'elettore:
	- elettore j arriva al seggio
	- elettore j entra nel seggio e vota
	- elettore j esce dal seggio 
	- elettore j attende sulla porta
*/
#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>
#include <unistd.h>
#define randTime(min, max) (rand()%(max-min+1)+min)
// parametri per la simulazione -----
#define tArrivo1 1
#define tArrivo2 2
#define tVota1 3
#define tVota2 4
#define numEletInMax 3
pthread_mutex_t M; /* mutex per mutua esclusione */ 
pthread_cond_t C; /* variabile condizione */
int numEletIn=0; /* variabile condivisa */

void *elettore(void *arg){
	int id=(int)arg; // identificatore thread
	srand(time(NULL));
	pthread_mutex_lock(&M);
	while (numEletIn >= numEletInMax) {
		printf("Elettore %i attende sulla porta \n",id);
		pthread_cond_wait(&C,&M);
	}	
	numEletIn++;
	printf("Elettore %i entra, ci sono %d elettori nel seggio \n",id, numEletIn);
	pthread_mutex_unlock(&M); 
	sleep(randTime(tVota1,tVota2));	
	pthread_mutex_lock(&M);
	numEletIn--;
	printf("Elettore %i esce, ci sono %d elettori nel seggio \n",id, numEletIn);
	pthread_cond_signal(&C);	
	pthread_mutex_unlock(&M); 
}
  
int main () {
	int N=100;
	pthread_t th[N];
  pthread_mutex_init (&M,NULL);
	pthread_cond_init(&C,NULL);
  int i=0;
	srand(time(NULL));
	while (i<N) {
		printf ("Arriva elettore %d \n",i);
		if (pthread_create(&th[i],NULL,elettore,(int *)i) !=0) {
			fprintf (stderr, "errore create thread  \n");
			exit(1);
		}
		i++;
		sleep(randTime(tArrivo1,tArrivo2));	
	}				
}

