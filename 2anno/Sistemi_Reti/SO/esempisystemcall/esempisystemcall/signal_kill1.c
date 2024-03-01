#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h> 
void gestore (int signum) {
	static int cont=0;
  	printf ("Processo con pid %d; ricevuti n. %d segnali. Segnale %d 	    \n",  getpid(), ++cont, signum);
}
int  main () {
  pid_t pid;
  signal(SIGUSR1, gestore); 
  pid = fork ();
  if (pid==0) /* figlio */
    for (; ;) pause();
  else /* padre */
    for ( ; ;) { 
      kill (pid, SIGUSR1);
      sleep(1);
	  }
}
