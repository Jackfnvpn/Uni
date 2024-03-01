/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.1 30-10-2018

	Esempio di exec.
	Legge ciclicamente un comando digitato da tastiera (tramite scanf). Se il comando non è exit, viene esiguita la fork che crea un processo figlio il cui codice viene sostituito dal comando eseguito tramite la execpl. */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/wait.h>
int main (){
  pid_t pid;
	int stato;
  char comando[1000];
  while(1){
    printf("SOR# "); //visualizza il prompt >
    scanf("%s", comando); // memorizza nella stringa comando i caratteri letti dalla tastiera
		if(!strcmp(comando,"exit")) // esce dal ciclo while se il comado digitato è exit
      break;
    pid=fork(); // crea un processo figlio
    if(pid==0){
      /* codice del processo figlio il codice del processo figlio viene sostituito dal codice del processo creato dall'avvio del programma lanciato da execpl */    
      execlp(comando,comando,NULL);
			perror("errore exec"); // visualizza l'eventuale errore generato da execpl
			exit(1);
    } else 
		  wait(& stato); // il padre attende la terminazione del processo creato da execlp
  }
}

