/** 
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 12-01-2016

	Questo esempio mostra il funzionamento delle SC wait e exit. il processo P crea un processo figlio (F). P chiama la wait per attendere la terminazione del figlio F. Il figlio F prima di eseguire la exit attende un tempo specificato nell'argomento di sleep. Se il figlio F termina volontariamente la wait ritorna nel byte più significativo della variabile stato il valore del parametro passato dal figlio nella exit (per questo motivo si divide il risultato per 256, che equivale a fare lo shift verso destra di 8 bit). Se in figlio F è fatto terminare in modo forzato, ad esempio inviando ad esso un segnale tramite il comando kill, la wait ritorna nella variabile stato il numero di segnale usato per fare terminare il figlio.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/wait.h>
void main(){
  int pid,stato,ret_pid;
	const int ATTESA=30;
  pid=fork(); // crea un processo figlio
  if (pid==0){
    // codice del figlio
    printf("Sono il processo figlio con pid: %d \n",getpid());
		printf("Attendi %d secondi...o invia un segnale..per farmi terminare.\n",ATTESA);
    //pause();
    sleep(ATTESA);
    exit(3); // il valore dell'argomento di exit viene restituito nel byte più significativo della variabile stato del padre
  } else if (pid > 0) {
    //codice del padre
    ret_pid=wait(&stato); // il valore dell'argomento di exit del figlio viene assegnato alla variabile stato
    printf("processo figlio con pid: %d terminato\n", ret_pid);
    if (stato%8==0)
      printf("terminazione volontaria con stato =  %d \n",stato>>8);
    else
      printf("terminazione forzata: ricevuto il segnale n. %d \n",stato);

  } else
    printf("fork fallita");
}
