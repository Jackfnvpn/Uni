/**
	Sistemi operativi e reti (SOR)
	Appello 3 - A.A. 2013/2014
	@author Pietro Frasca 
	@version 1.00 29-11-2017
	
	Realizzate un programma multi processo in C, completo di commento, che svolga  quanto segue: un processo padre P scrive un messaggio su un file F con diritti di accesso rw- rw- ---, quindi genera due processi figli P1 e P2 e attende che terminino. Il figlio P1 inizializza una variabile X al valore 100 e quindi entra nello stato di bloccato. P2 esegue un ciclo infinito durante  il quale genera ogni secondo un numero intero casuale compreso tra 0 e 1023. Quando P2 estrae un numero pari  ad X, definito da P1, aggiunge la parola “fine!” al file F, quindi invia un segnale a P1 per risvegliarlo e P2 termina. P1, riattivato dal segnale che ha ricevuto da P2, legge il file F e lo visualizza sullo schermo. Infine P1 fa terminare l'applicazione.   
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
int fdp[2];
char *fileName="2013-a3.txt";
char mes[32];
pid_t pid1, pid2;
void handler(){
	int fd=open(fileName,O_RDONLY);
	while (read (fd,mes,sizeof(mes))>0) {
		printf("%s",mes); }
	close (fd);
}

void figlio1(){
	signal(SIGUSR1,handler);
	int x=10;
	write (fdp[1],&x,sizeof(int));
	pause();
  exit(0);
}

void figlio2(){
	int x=0,y=-1;
	char fine[]="fine!";
	read(fdp[0],&x,sizeof(int));
	printf("figlio1: x=%d\n",x);
	while(x!=y){
		y=rand()%20;
	}	
	int fd=open(fileName,O_WRONLY|O_APPEND);
	write (fd,fine,sizeof(fine));
	close (fd);
	kill (pid1,SIGUSR1);
	exit(0);
}

int main(){
	int stato1,stato2;
	strcpy(mes,"problema numero 5...");
	int fd=open(fileName,O_CREAT|O_WRONLY,777);
	write (fd,mes,sizeof(mes));
	close (fd);
	pipe(fdp);
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
			wait(&stato1);
			wait(&stato2);
		}	
	}	
}