/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio che mostra l'uso della libreria pthread.
	Il thread main crea due thread th1 e th2 (che eseguono rispettivamente il codice codice_th1 e codice_th2), quindi attende la terminazione dei due thread figli. Al termine, ciascuno dei thread chiama la pthread_exit che consente di comunicare al padre lo stato di terminazione del thread. Tale stato è recuperato dal thread padre mediante la pthread_join che dispone di due parametri: il primo specifica il thread da attendere e il secondo il suo stato di terminazione, passato mediante la pthread_exit dai thread "figli". 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
char msg[]="Ciao";
void *codice_th1 (void *arg){
  int i;
  for (i=0; i<5;i++){
    printf("Thread %s: %s \n", (char *)arg, msg);
    sleep(1);
  }
  pthread_exit((void *)1);
}
void *codice_th2 (void *arg){
  int i;
  for (i=0; i<5;i++){
    printf("Thread %s: %s \n", (char *)arg, msg);
    sleep(1);
  }
  pthread_exit((void *)2);
}

int main(){
  pthread_t th1, th2;
  int ret,stato;
  // creazione e attivazione del primo thread
  if (pthread_create(&th1,NULL,codice_th1, "1")!=0){
    fprintf(stderr,"Errore di creazione thread 1 \n");
    exit(1);
  } 
   // creazione e attivazione del secondo thread
  if (pthread_create(&th2,NULL,codice_th2, "2")!=0){
    fprintf(stderr,"Errore di creazione thread 2 \n");
    exit(1);
  }
  // attesa della terminazione del primo thread
  ret=pthread_join(th1,(void *)&stato);
  if (ret !=0)
    fprintf(stderr,"join fallito %d \n",ret);
  else 
    printf("terminato il thread 1 stato = %d\n",stato);
  
  // attesa della terminazione del secondo thread
   ret=pthread_join(th2,(void *)&stato);
  if (ret !=0)
    fprintf(stderr,"join fallito %d \n",ret);
  else 
    printf("terminato il thread 2 stato = %d\n",stato);
  return 0;
}

