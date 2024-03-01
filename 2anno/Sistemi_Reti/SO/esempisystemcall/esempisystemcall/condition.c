/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Variabili condition.
	Esempio di sincronizzazione per l'accesso a una risorsa R che può essere usata contemporaneamente da, al massimo, MAX thread.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#define MAX 5
// variabili globali
int N_in=0; // numero thread che usano la risorsa 
pthread_cond_t PIENO; // variabile condition
pthread_mutex_t M; // mutex associato alla variabile condition PIENO 
void *codice_thread (void *arg){ 
	int id=(int)arg;
	int t=0;
	
  pthread_mutex_lock(&M);
  if (N_in==MAX) {
    printf ("thread %d sospeso\n",id); 
    pthread_cond_wait(&PIENO, &M); 
    printf ("thread %d riattivato\n",id);
  }
	pthread_mutex_lock(&M);
  N_in++;
	printf("thread %d inizia a usare la risorsa. N_in=%d \n",id,N_in);
  pthread_mutex_unlock(&M) ;
	srand(time(NULL)+id);
  t=rand()%10+1;
	sleep(t);
  
  pthread_mutex_lock(&M);
  N_in--;
	printf("thread %d ha usato la risorsa per %d sec. N_in=%d \n",id,t,N_in);
  pthread_cond_signal(&PIENO) ; 
  pthread_mutex_unlock(&M) ;
}

int main(){
  int N=30;
  pthread_t th[N];
  int i;
	
  pthread_mutex_init(&M,NULL); //inizializza il mutex
  pthread_cond_init(&PIENO,NULL);//inizializza la variabile condition PIENO
  
  for (i=0;i<N;i++){
    printf ("creato thread %d \n",i);
    pthread_create(&th[i],NULL,codice_thread,(int *)i);
    sleep(rand()%2); 
  }

  for (i=0;i<N;i++){
    pthread_join(th[i],NULL);
    printf ("terminato thread %d \n",i);
  }
    
}

