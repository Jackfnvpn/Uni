/** 
	Sistemi operativi e reti (SOR)
	Appello 4 - A.A. 2011/2012
	@author Pietro Frasca
	@version 1.00 15-01-2016
	
	Realizzate un programma multi thread in C, completo di commento, che svolga quanto segue: il thread iniziale che esegue il main crea due thread TH1 e TH2. I due thread condividono una variabile intera S. TH1, ciclicamente, genera un numero casuale compreso tra 0 e 50 e lo sottrae ad S solo nel caso in cui il numero estratto sia minore di S. TH2, ciclicamente, genera un numero casuale compreso tra 0 e 100 e lo aggiunge ad S. Un thread termina se il numero estratto ha valore 0. Il programma termina quando il thread main verifica che il valore di S ha superato il valore di soglia 1000 o i due thread figli hanno terminato la loro esecuzione. Prima della terminazione il thread main stampa sullo schermo il valore finale di S e lo salva in un file.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <fcntl.h>
pthread_mutex_t M; // mutex per mutua esclusione  
pthread_cond_t C; // variabile condizione 
int ceckup=0; // variabile condivisa.
int ntt=0; //variabile condivisa. numero thread terminati
int s=0; // variabile condivisa 

void *thread1(void *arg){
	int x;
	srand(time(NULL));
	do {
		sleep(1);
		x=rand()%51;
		pthread_mutex_lock(&M); 
		if (x<s) {
			s=s-x; 
			printf("thread1: x=%d s=%d \n",x,s);
		} 
		pthread_mutex_unlock(&M); 
	} while (x!=0);
	pthread_mutex_lock(&M); 
	ntt++;
	printf("thread1: terminato \n");
	pthread_cond_signal(&C);
	pthread_mutex_unlock(&M); 
	pthread_exit((int*)1);
}

void *thread2(void *arg){
	int x=1;
	srand(time(NULL)+1);
	do {
		x=rand()%101;
		pthread_mutex_lock(&M); 
		s=s+x;
		printf("thread2: x=%d s=%d\n",x,s);
		ceckup=1;
		pthread_cond_signal(&C);
		pthread_mutex_unlock(&M); 
	  sleep(1);
	} while(x!=0);
	pthread_mutex_lock(&M); 
	ntt++;
	pthread_cond_signal(&C);
	pthread_mutex_unlock(&M); 
	printf("thread2: terminato \n");
	pthread_exit((int*)1);
}
  
int main () {
	int fd;
	char buf[6];
	pthread_t th1, th2;
  pthread_mutex_init (&M,NULL);
	pthread_cond_init(&C,NULL);
  if (pthread_create(&th1,NULL,thread1,NULL) !=0) {
    fprintf (stderr, "errore create thread 1 \n");
    exit(1);
  }
  if (pthread_create(&th2,NULL,thread2,NULL) != 0){
    fprintf (stderr,"errore create thread 2 \n");
    exit(1);
  }
	while (1) {
		pthread_mutex_lock(&M);
		if (s>1000||ntt==2) break;
		if (ceckup==0)
			pthread_cond_wait(&C,&M);
		printf("thread main: s=%d \n",s); 	
		ceckup=0;	
		pthread_mutex_unlock(&M);
	}	
	printf("thread main: fine, s=%d \n",s); 	
	fd=open("2011-a4.txt",O_CREAT|O_WRONLY,0644);
	sprintf(buf,"%d",s); // conversione tipo da int a  char[]
	write (fd, buf,sizeof(buf));
	close(fd);	
}

