/** 
	Sistemi operativi e reti (SOR)
	Appello 2 - A.A. 2014/2015
	@author Pietro Frasca 
	@version 1.00 16-01-2016
	
	Realizzate un programma multi thread in C, completo di commento, che simuli il comportamento di automobili che entrano in un parcheggio, in base alle seguenti specifiche:
	- Le auto arrivano al parcheggio in istanti di tempo random;
	- al parcheggio si può accedere da due ingressi: ingresso SUD e ingresso NORD;
	- dal parcheggio si esce da una sola uscita;
	- nel parcheggio possono essere presenti al massimo 20 auto allo stesso tempo;
	- un auto deve attendere in coda (SUD o NORD) se il parcheggio è pieno;
	- quando entra nel parcheggio un'auto si ferma per un tempo random;
	- dimensionare la simulazione per 100 auto. 
	L'applicazione, durante l'esecuzione, deve visualizzare i seguenti messaggi, in accordo con gli stati in cui si può trovare un'auto:
	- auto j arriva al parcheggio da SUD (NORD);
	- auto j entra nel parcheggio da SUD (NORD);
	- auto j esce dal parcheggio;
	- sono presenti k auto nel parcheggio;
	- auto j è in coda SUD (NORD);
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#define randTime(min, max) (rand()%(max-min+1)+min)

pthread_mutex_t M; /* mutex per mutua esclusione */
pthread_cond_t C[2]; /* variabile condizione */
typedef enum direzione{NORD, SUD} Direzione;
int numCarMax=20;// numero massimo di auto presenti nel parcheggio
int numCarIn=0; // numero di auto presenti nel parcheggio
char dirStr[2][10]={"NORD","SUD"};

typedef struct {
	int arrivo1;
	int arrivo2;
	int stop1;
	int stop2;
} Tempo;
Tempo tempo={1,2,14,16};	

void *car(void *arg){
	int id=(int)arg; // identificatore thread
	srand(time(NULL));
	Direzione dir=rand()%2; 
	pthread_mutex_lock(&M);
	while (numCarIn==numCarMax) {
		printf("auto %i in coda %s\n",id,dirStr[dir]);
			pthread_cond_wait(&C[dir],&M);	
		}	
		numCarIn++;
		printf("auto %i entra da %s. Ci sono %d auto nel parcheggio\n",id,dirStr[dir],numCarIn);
		pthread_mutex_unlock(&M);
		sleep(randTime(tempo.stop1,tempo.stop2));
		pthread_mutex_lock(&M);
		numCarIn--;
		printf("auto %i esce dal parcheggio. Ci sono %d auto nel parcheggio \n",id,numCarIn);
		pthread_cond_signal(&C[dir]);
		pthread_mutex_unlock(&M);	
}

int main () {
	int N=100, i=0;
	pthread_t th[N];
  pthread_mutex_init (&M,NULL);
	pthread_cond_init(&C[NORD],NULL);
	pthread_cond_init(&C[SUD],NULL);
  srand(time(NULL));
	while (i<N) {
		if (pthread_create(&th[i],NULL,car,(void *)i) !=0) {
			fprintf (stderr, "errore create thread  \n");
			exit(1);
		}
		i++;
		sleep(randTime(tempo.arrivo1,tempo.arrivo2));	
	}				
}

