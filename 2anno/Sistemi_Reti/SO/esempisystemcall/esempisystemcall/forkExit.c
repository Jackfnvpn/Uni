#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>

int main(){
	pid_t ret, pid; 
  int stato;
	ret=fork();
	if (ret==0){
		// codice del figlio
		printf("sono il figlio pid: %d \n",getpid());
		sleep(20); // sospensione per 20 secondi
    exit(2);//valore che il padre leggerà in stato
	} else if (ret > 0){
		//codice del padre
    pid=wait(&stato);
		printf("processo figlio pid: %d terminato\n", pid);
      if (stato<256)
         printf("terminazione forzata: segnale n = %d\n", stato);
      else 
		  printf("terminazione volontaria: stato = %d \n",
			  stato>>8);
  } else
		printf("fork fallita\n");
}
