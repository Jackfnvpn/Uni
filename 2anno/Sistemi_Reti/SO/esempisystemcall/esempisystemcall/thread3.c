/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
  
	Tormentone sincronizzato
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>

int im;
int turno=0;
int cicli=20;
pthread_mutex_t M;
pthread_cond_t C;
char msg[][20]={"dove vai?","al cinema","a vedere cosa","quo vadis","cosa vuol dire?"};

void *Lino (void *arg){
  int i;
  for (i=0; i<cicli;i++){
    pthread_mutex_lock(&M);
		if (turno!=0) {
			pthread_cond_wait(&C, &M);
		} 
			printf("Thread %s: %s \n", (char *)arg, msg[im]);
			im=(im+1)%5;
			turno=1;
			usleep(1);
    
		pthread_cond_signal(&C);
		pthread_mutex_unlock(&M);	 
		
   }
  pthread_exit(0);
}

void *Eva (void *arg){
  int i;
  for (i=0; i<cicli;i++){
    pthread_mutex_lock(&M);
		if (turno!=1) {
		 pthread_cond_wait(&C, &M);
		} 
		printf("Thread %s: %s \n", (char *)arg, msg[im]);
		im=(im+1)%5;
		turno=0;
		usleep(1);
		pthread_cond_signal(&C);
		pthread_mutex_unlock(&M);	  
	} 
			
    //sleep(1);
	  pthread_exit(0);
}

int main(){
  pthread_t th1, th2;
 
  pthread_mutex_init(&M,NULL);
  	
  // creazione e attivazione del primo thread
  if (pthread_create(&th1,NULL,Lino, "Lino")!=0){
    fprintf(stderr,"Errore di creazione thread 1 \n");
    exit(1);
  } 
   // creazione e attivazione del secondo thread
  if (pthread_create(&th2,NULL,Eva, "Eva")!=0){
    fprintf(stderr,"Errore di creazione thread 2 \n");
    exit(1);
  }
  // attesa della terminazione del primo thread
  if (pthread_join(th1,NULL) !=0)
    fprintf(stderr,"join fallito %d \n");
  else 
    printf("terminato il thread 1 \n");
  
  // attesa della terminazione del secondo thread
  
  if (pthread_join(th2,NULL) !=0)
    fprintf(stderr,"join fallito %d \n");
  else 
    printf("terminato il thread 2 \n");
  return 0;
}







     
