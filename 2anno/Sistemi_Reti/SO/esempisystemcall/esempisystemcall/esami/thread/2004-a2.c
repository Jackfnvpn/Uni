/**
	Sistemi operativi
	Appello 2 - A.A. 2004/2005
	@author Pietro Frasca
	@version 1.00 11-01-2016

	Realizzare un programma in C che svolga  quanto segue: il tread main iniziale crei due thread di nome Th1 e Th2. I due thread condividono la variabile intera A alla quale inizialmente è assegnato il valore 10. Il thread Th1 incrementa di 1 il valore di A e attende 1 secondo, mentre Th2 decrementa di 1 il valore di A e attende 1 secondo. Entrambi i thread eseguono le operazioni suddette per 10 volte, quindi terminano. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
pthread_mutex_t M; 
int A=10;

void *codice_Th1 (void *arg){
  int i;
  for (i=0; i<10;i++){
		pthread_mutex_lock(&M); /* prologo sez. critica */
    A++;
    printf (" Thread %s: A = %d \n",(char *)arg,A);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
		sleep(1);
  }
  pthread_exit(0);
}

void *codice_Th2 (void *arg){
  int i;
  for (i=0; i<10;i++){
		pthread_mutex_lock(&M); /* prologo sez. critica */
    A--;
    printf (" Thread %s: A = %d \n",(char *)arg,A);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
		sleep(1);
  }
  pthread_exit(0);
}

int main(){
  pthread_t th1, th2;
  int ret;
	pthread_mutex_init (&M,NULL);
  // creazione e attivazione del primo thread
  if (pthread_create(&th1,NULL,codice_Th1, "th1")!=0){
    fprintf(stderr,"Errore di creazione thread 1 \n");
    exit(1);
  } 
   // creazione e attivazione del secondo thread
  if (pthread_create(&th2,NULL,codice_Th2, "th2")!=0){
    fprintf(stderr,"Errore di creazione thread 2 \n");
    exit(1);
  }
  // attesa della terminazione del primo thread
	ret=pthread_join(th1,NULL);
  
  if (ret !=0)
    fprintf(stderr,"join fallito %d \n",ret);
  else 
    printf("terminato il thread 1 \n");
  
  // attesa della terminazione del secondo thread
	ret=pthread_join(th2,NULL);
  if (ret !=0)
    fprintf(stderr,"join fallito %d \n",ret);
  else 
    printf("terminato il thread 2 \n");
  return 0;
}
