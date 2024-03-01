/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Semplice esempio che mostra l'uso della libreria pthread. Vengono creati due thread che eseguono lo stesso codice (codice_T).
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
char msg[]="Ciao";

void *codice_T (void *arg){
  int i;
  for (i=0; i<5;i++){
    printf("Thread %s: %s \n", (char *)arg, msg);
    sleep(1);
  }
  pthread_exit(0);
}
int main(){
  pthread_t th1, th2;
  int ret;
  // creazione e attivazione del primo thread
  if (pthread_create(&th1,NULL,codice_T, "1")!=0){
    fprintf(stderr,"Errore di creazione thread 1 \n");
    exit(1);
  } 
   // creazione e attivazione del secondo thread
  if (pthread_create(&th2,NULL,codice_T, "2")!=0){
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




     