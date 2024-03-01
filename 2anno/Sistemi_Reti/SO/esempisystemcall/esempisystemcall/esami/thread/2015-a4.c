/** 
	Sistemi operativi e reti (SOR)
	Appello 3 - A.A. 2015/2016
	@author Pietro Frasca
	@version 1.00 21-02-2017
	
	Realizzate un programma multi thread in C, completo di commento, che svolga quanto segue: il thread iniziale che esegue il main crea due thread TH1 e TH2. I due thread condividono due variabili intere S1 e S2 sulle quali operano in mutua esclusione. TH1 genera periodicamente, ogni secondo, un numero casuale compreso tra 1 e 50 e lo sottrae a S1 se il numero estratto è dispari, mentre lo sottrae a S2 se il numero estratto è pari. TH2 genera periodicamente, ogni due secondi, un numero casuale compreso tra 1 e 100 e lo aggiunge ad S1 ed incrementa di 1 il valore di S2. Il programma termina quando TH1 verifica che il valore della somma S1+S2 ha superato il valore 1000. Prima della terminazione del programma il thread main stampa su schermo il valore finale della somma S1+S2.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
pthread_mutex_t M; /* mutex per mutua esclusione */ 
int s1,s2; /* variabili condivise */

/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}
void *thread1(void *arg){
	int x;
	srand(time(NULL));
	
	do {
		x=getRandom(1,50); 
		pthread_mutex_lock(&M); /* prologo sez. critica */
		if (x%2)
			s1=s1-x; // x numero dispari
		else
			s2=s2-x; //numero pari
		printf("thread 1: x=%d s1=%d s2=%d\n",x,s1,s2); 
		sleep(1);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	} while ((s1+s2)<=1000);
	pthread_exit((int*)1);
 }

 void *thread2(void *arg){
	int x;
	srand(time(NULL)+1);
	while(1){
		x=getRandom(1,100);
		pthread_mutex_lock(&M); /* prologo sez. critica */
		s1=s1+x;
		s2++;
		printf("thread 2: x=%d s1=%d s2=%d\n",x,s1,s2); 
		sleep(2);
		pthread_mutex_unlock(&M); /* epilogo sez. critica */
	}
 }
  
int main () {
	int somma=0;
	pthread_t th1, th2;
  pthread_mutex_init (&M,NULL);
  if (pthread_create(&th1,NULL,thread1,NULL) !=0) {
    fprintf (stderr, "errore create thread 1 \n");
    exit(1);
  }
  if (pthread_create(&th2,NULL,thread2,NULL) != 0){
    fprintf (stderr,"errore create thread 2 \n");
    exit(1);
  }
	
	pthread_join(th1,NULL); // il thread main si sospende fino alla terminazione di th1
	somma=s1+s2;
	printf("thread main: s1=%d s2=%d somma=%d\n",s1,s2,somma); 	
}

