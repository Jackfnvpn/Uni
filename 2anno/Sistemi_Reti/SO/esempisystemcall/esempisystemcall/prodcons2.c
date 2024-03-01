/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Soluzione al problema del Produrrore-Consumatore, con buffer di dimensione N, con messaggi di tipo intero.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h> 
#define MAX 100 
#define N 16

typedef struct {
  int msg[N]; 
  pthread_mutex_t M;
  int num;
	int leggi;
	int scrivi;
	pthread_cond_t pieno;
	pthread_cond_t vuoto;
} Buffer;

Buffer *buf; // variabile condivisa	

/* Inizializza il buffer */ 
void init (){
	buf=(Buffer *)malloc(sizeof(Buffer));
  pthread_mutex_init (&buf->M,NULL); 
  pthread_cond_init (&buf->pieno, NULL); 
  pthread_cond_init (&buf->vuoto, NULL); 
  buf->num=0;
  buf->leggi=0;
  buf->scrivi=0;
}

void *produttore (void *arg){
  int i,x;
	srand(time(NULL));
  for (i=0; i<MAX; i++){
		x=rand()%10;
    pthread_mutex_lock (&buf->M);
		if (buf->num==N) {
			/* il buffer e' pieno */ 
			pthread_cond_wait (&buf->pieno, &buf->M);
		}
		/* scrivi msg e aggiorna lo stato del buffer */ 
		printf ("Thread produttore -> %d \n", x); 
		buf->msg[buf->scrivi]=x;
		buf->num++;
		/* la gestione è circolare */
		buf->scrivi=(buf->scrivi+1)%N;
		/* risveglia un eventuale thread consum. sospeso */       pthread_cond_signal (&buf->vuoto);
		pthread_mutex_unlock (&buf->M);
		usleep(1000);
  }
	pthread_exit(0);
}

void *consumatore (void *arg){
  int i,x;
  for (i=0; i<MAX; i++){
		pthread_mutex_lock(&buf->M);
			if(buf->num==0){ 
			/* il buffer e' vuoto? */
			pthread_cond_wait (&buf->vuoto, &buf->M);
		}
		/* Leggi il messaggio e aggiorna lo stato del buffer*/ 
		x = buf->msg[buf->leggi];
		buf->num--;
		buf->leggi=(buf->leggi+1)%N;
		printf ("Thread consumatore <- %d \n", x);
		/* Risveglia un eventuale thread produttore */ 
		pthread_cond_signal(&buf->pieno); 
		pthread_mutex_unlock(&buf->M);
		usleep(60000);
	} 
	pthread_exit(0);
}

int main () {
  pthread_t prod, cons; 
  init ();
  /* Creazione thread  (1 produttore e 1 consumatore) */ 
  pthread_create(&prod, NULL, produttore, NULL); 
  pthread_create(&cons, NULL, consumatore, NULL); 
	/* Attesa teminazione threads creati: */ 
  pthread_join (prod, NULL);
  pthread_join (cons, NULL);
  return 0;
}

