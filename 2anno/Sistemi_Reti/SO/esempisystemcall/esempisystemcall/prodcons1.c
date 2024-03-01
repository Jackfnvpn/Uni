/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Soluzione produttore consumatore con buffer di capacità 1. 
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define NUM_MES 10
pthread_mutex_t spaziodisp;
pthread_mutex_t msgdisp;
int buf=0;

/*codice produttore*/
void * produttore(void *arg){	
  int i=0,mes;
	const int NUM=100;
	srand(time(NULL));
  while(i<NUM_MES){	
    i++; /* produzione di un nuovo messaggio */
    pthread_mutex_lock(&spaziodisp);
    buf=rand()%NUM;
    printf("Produttore: invia messaggio %i\n",buf);
		usleep(500000);
    pthread_mutex_unlock(&msgdisp);
  }
  pthread_exit (0);
}

/*codice consumatore*/
void * consumatore(void *arg){	
  int i=0,mes=0;
  int somma=0;
	
  while(i < NUM_MES){	
		i++; 
		pthread_mutex_lock(&msgdisp);
    mes=buf; /* prelievo dell'unico messaggio nel buffer */
    printf("Consumatore: preleva messaggio %d \n\n", mes); 
    somma+=mes; /* uso del del messaggio...*/
    sleep(1);
		pthread_mutex_unlock(&spaziodisp);
    
  }
  printf("Consumatore: la somma dei messaggi e': %d \n",somma);
  pthread_exit (0);
   
}

int main (){ 
  pthread_t prod, cons; /*identificatori dei thread produttore e consumatore */
  /* inizializzazione dei mutex: */
  pthread_mutex_init(&spaziodisp, NULL); /* spaziodisp inizialmente libero */
  pthread_mutex_init(&msgdisp, NULL); 
  pthread_mutex_lock(&msgdisp); /* pone il mutex msgdisp a occupato */
  if (pthread_create (&prod, NULL, produttore, NULL) != 0) { 
     fprintf (stderr, "errore pthread_create\n");
     exit (1);
  }
	
  if (pthread_create(&cons,NULL, consumatore, NULL) != 0) {
    fprintf (stderr, "errore pthread_create \n");
    exit (1);
  }

  pthread_join (prod, NULL);
  pthread_join (cons, NULL);

}


