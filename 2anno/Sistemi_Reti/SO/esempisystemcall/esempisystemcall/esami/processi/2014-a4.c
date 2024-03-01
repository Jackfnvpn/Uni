/**
	Sistemi operativi e reti (SOR)
	Appello 4 - A.A. 2014/2015
	@author Pietro Frasca 
	@version 1.00 29-11-2017
	
	Realizzare un programma multiprocesso in C, completo di commento, che svolga  quanto segue: un processo P genera due processi figli P1 e P2. I due figli P1 e P2 eseguono un ciclo indeterminato nel quale generano, ogni secondo, un numero intero casuale compreso tra 0 e 100.  Ad ogni estrazione, i numeri generati dai figli sono inviati al processo padre P che provvede a sommarli, a stamparli sullo schermo e a memorizzarli in un file. Il  processo P1 deve gestire  il segnale di interruzione SIGINT. In particolare, all’arrivo di tale segnale P1 deve visualizzare il messaggio di avviso “interruzione disabilitata!”. Il programma viene terminato dal processo padre P quando verifica che la somma dei numeri, che ha ricevuto dai processi figli, assume il valore 100. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <time.h>
#include <string.h>
#include <stdbool.h>
int fdp1[2];
int fdp2[2];
char *fileName="2014-a4.txt";
char buffer[32];
pid_t pid1, pid2;
void handler(){
	printf("interruzione disabilitata!\n"); 
}

void figlio1(){
	srand(time(NULL));
	signal(SIGINT,handler);
	close (fdp1[0]);
	while(true){
		int x=rand()%101;
		write (fdp1[1],&x,sizeof(int));
		// sleep(1);
  }
}

void figlio2(){
	srand(time(NULL)+1);
	close (fdp2[0]);
	while(true){
		int x=rand()%101;
		write (fdp2[1],&x,sizeof(int));
		// sleep(1);
  }
}

void padre() {
	int somma=0,x1,x2;
	int fd=open(fileName,O_CREAT|O_WRONLY,0755);
	close(fdp1[1]);
	close(fdp2[1]);
	do {
		read(fdp1[0],&x1,sizeof(int));
		read(fdp2[0],&x2,sizeof(int));
		somma=x1+x2;
		printf ("x1=%d x2=%d somma=%d\n",x1,x2,somma);
		int n=sprintf (buffer,"x1=%d x2=%d somma=%d\n",x1,x2,somma);
		write (fd,buffer,n);
	} while (somma !=100);
	close (fd);
	kill (0,SIGKILL);
}

int main(){
	pipe(fdp1);
	pipe(fdp2);
	pid1=fork();
	switch (pid1) {
	case -1:
		printf("errore fork \n");
		exit(1);
	case 0:
		// figlio
		figlio1();
		break;
	default:
		// padre
		pid2=fork();
		switch (pid2) {
		case -1:
			printf("errore fork \n");
			exit(1);
		case 0:
			// figlio
			figlio2();
			break;
		default:
			// padre
			padre();
		}	
	}	
}