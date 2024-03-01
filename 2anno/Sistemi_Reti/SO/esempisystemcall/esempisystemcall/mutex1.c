/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0.1 11-11-2018
	
	Esempio mutex.
	Due thread condividono la variabile cont, alla quale accedono
	in mutua esclusione mediante un mutex.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define MAX 10
pthread_mutex_t M; /* mutex condiviso tra threads */ 
int cont=0; /* variabile condivisa */
void *thread1(void *arg){
  int k=1;
  while(k){
    pthread_mutex_lock(&M); /* prologo sez. critica */
    cont++;
    printf("%s: cont=%d \n",arg,cont); 
    k=(cont >= MAX)?0:1;
    pthread_mutex_unlock(&M); /* epilogo sez. critica */
    sleep(1);    
  }
  pthread_exit(0);
}

void * thread2 (void * arg){
  int k=1 ;
  while (k){
    pthread_mutex_lock(&M); /*prologo sez. critica */
    cont++;
    printf("%s: cont=%d \n",arg,cont); 
    k=(cont >= MAX)?0:1;
    pthread_mutex_unlock(&M); /*epilogo sez. critica*/
    sleep(1);
  }
  pthread_exit(0);
}
  
int main () {
  pthread_t th1, th2;
  /* il mutex è inizialmente libero */ 
  pthread_mutex_init (&M,NULL);
  if (pthread_create(&th1,NULL,thread1,"Lino") !=0) {
    fprintf (stderr, "errore create thread 1 \n");
    exit(1);
  }
  if (pthread_create(&th2,NULL,thread2,"Eva") != 0){
    fprintf (stderr,"errore create thread 2 \n");
    exit(1);
  }
  pthread_join (th1,NULL); 
  pthread_join (th2,NULL);
  printf("FINE");
}
