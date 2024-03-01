/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Esempio di fork con execl.
	execl (arg0,arg1,arg2,...argN,0). il parametro arg0 specifica il nome del programma da eseguire. Gli altri parametri, arg1..argN, sono passati al programma specificato con arg0. Lo 0 è necessario per chiudere l'elenco degli argomenti. 
	Il processo corrente genera un processo figlio che esegue la execl. In tal modo l'immagine del figlio è sostituita dall'immagine del programma chiamato con la execl. 
	NOTA: per sperimentare questo programma seguire i seguenti passi:
	1) compilare questo programma (execl1_a.c): gcc execl1_a.c -o execl1_a
	2) compilare il programma execl1_b.c: gcc execl1_b.c -o execl1_b
	3) eseguire execl1_a
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>

int main() {
  int pid,stato;
  pid=fork(); 
  if (pid==-1) {
		printf("Errore nella fork \n");
		exit(1);
  } else if (pid==0) {
		// codice del figlio
		printf("processo figlio con pid=%d \n",getpid());
    execl("execl1_b","Cari","saluti","da Pietro",0);
		printf("Questo frase e' visualizzata solo se la execl fallisce \n");
		
  } else {
		// codice del padre
    printf("processo padre con pid=%d \n",getpid());
  }
  /* codice eseguito solo dal padre: il figlio è stato rimpiazzato dal processo execl1_b */
  wait(&stato);
  printf("Questa istruzione e' eseguita dal processo con pid=%d \n", getpid());

}
