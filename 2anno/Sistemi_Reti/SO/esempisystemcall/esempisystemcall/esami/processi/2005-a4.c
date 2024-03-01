/** 
	Sistemi Operativi (SO)
	Appello 4 - A.A. 2005/2006
	@author Pietro Frasca 
	@version 1.00 16-01-2016
	
	Realizzare un programma in C, completo di commento, che svolga quanto segue: un processo genera due processi figli P1 e P2. Il figlio P1 esegue un ciclo indeterminato durante il quale genera casualmente numeri interi compresi tra 0 e 100. P1 comunica, ad ogni iterazione, il numero al padre solo se esso è dispari. P2 fa la stessa cosa ma comunica al padre solo i numeri pari. Il padre, per ogni coppia di numeri che riceve dai figli ne fa la somma e la visualizza. Il programma deve terminare quando la somma dei due numeri ricevuti supera il valore 190: il padre, allora, invia un segnale di terminazione a ciascuno dei figli.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <string.h>
#include <time.h>
#define DIM 6
const int LEGGI=0;
const int SCRIVI=1; 
int fd1[2];
int fd2[2];

void figlio1(){
	int x;
	clock_t t;
	close(fd1[LEGGI]);// il figlio chiude l'estremo del canale che non usa
  t = clock();
  srand(t);
  while(1) {
		x=rand()%101;
		if (x%2) {
			write(fd1[SCRIVI],&x,sizeof(int));
		}
  }
}
void figlio2() {
	int x;
	clock_t t;
	t = clock();
  srand(t+1);
  close(fd2[LEGGI]);// il figlio chiude l'estremo del canale che non usa
  while (1){
		x=rand()%101;
    if (x%2==0) {
			write(fd2[SCRIVI],&x,sizeof(int));
    }
  }
}

int main(){
  pid_t pid1,pid2;
  int somma=0; 
  if (pipe(fd1)<0){
    printf("Errore pipe");
    exit(1);
  }
 
  if (pipe(fd2)<0){
    printf("Errore pipe");
    exit(1);
  }

  pid1=fork();
	if (pid1==-1) {
		// errore fork
		exit(1);
	} else if (pid1==0) {
		//figlio 1
    figlio1(); 
  } else {
    // padre
    pid2=fork();
		if (pid2==-1) {
			// errore fork
			exit(1);
    } else if (pid2==0) {
      //figlio 2
      figlio2();
    } else {
      // padre
			int n1,n2;
			close (fd1[SCRIVI]);// il padre chiude l'estremo del canale che non usa
      close (fd2[SCRIVI]);// il padre chiude l'estremo del canale che non usa
      while(somma<190) {
        read(fd1[LEGGI],&n1,sizeof(int));
				read(fd2[LEGGI],&n2,sizeof(int));
        somma=n1+n2;
        printf ("n1=%i, n2=%i somma=%i \n",n1,n2,somma);
			}
			printf("Fine!");
      kill(pid1,SIGKILL);
      kill(pid2,SIGKILL);
    
   }         
  }
  
}
  
