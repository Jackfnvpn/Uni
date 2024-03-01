/**
  Sistemi operativi
	Appello 2 - A.A. 2006/2007
  @author Pietro Frasca
  @version 1.00 11-01-2016
	
  Realizzate un programma multi thread in C, completo di commento, che svolga quanto segue: il thread iniziale che esegue il main crea due thread TH1 e TH2. I due thread condividono una variabile intera S sulla quale operano in mutua esclusione. TH1 genera un numero casuale compreso tra 1 e 100 e lo sottrae ad S solo nel caso in cui il numero estratto sia dispari. TH2 genera un numero casuale compreso tra 1 e 50 e lo aggiunge ad S. Il programma termina quando TH1 verifica che il valore di S ha superato il valore 1000. Prima della terminazione il thread main stampa su schermo il valore finale di S. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#define SOGLIA 1000
pthread_mutex_t M;
int S=0;

void *codice_th1 (void *arg){
	int id=(int)arg;
	srand(time(NULL)+id);
  int x;
  while(S<=SOGLIA){
    x=rand()%100+1;
    pthread_mutex_lock(&M);    
		if (x%2) { // x dispari
      S=S-x;
			printf("Thread %d: x=%d S=%d \n",id,x,S); 
		}
    pthread_mutex_unlock(&M);  
		usleep(1000);
  }	 
	pthread_exit((void *)0);  
}

void *codice_th2 (void *arg){
	int id=(int)arg;
	srand(time(NULL)+id);
  int x;
  while(1){
    x=rand()%50+1;
    pthread_mutex_lock(&M);     
    S=S+x;
		printf("Thread %d: x=%d S=%d \n",id,x,S); 
    pthread_mutex_unlock(&M);  
		usleep(1000);		
  }	   
}

int main(){
  pthread_t th1, th2;
  pthread_mutex_t M;
  pthread_mutex_init(&M,NULL);
  int ret,stato;
	
  // creazione e attivazione del primo thread
  if (pthread_create(&th1,NULL,codice_th1, (int *)1)!=0){
    fprintf(stderr,"Errore di creazione thread 1 \n");
    exit(1);
  } 
   // creazione e attivazione del secondo thread
  if (pthread_create(&th2,NULL,codice_th2, (int *)2)!=0){
    fprintf(stderr,"Errore di creazione thread 2 \n");
    exit(1);
  }
  // attesa della terminazione del primo thread
  ret=pthread_join(th1,(void *)&stato);
  if (ret !=0)
    fprintf(stderr,"join fallito %d \n",ret);
  else 
    printf("terminato il thread 1 con stato = %d \n",stato);
  
    printf("fine. S = %d\n",S);
	return 0;
}
