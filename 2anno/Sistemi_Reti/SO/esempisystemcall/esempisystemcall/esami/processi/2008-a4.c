/**
	Sistemi operativi (SO)
	Appello 4 - A.A. 2008/2009.
	@author Pietro Frasca 
	@version 1.00 16-01-2016

	Realizzare un programma in C, completo di commento, che svolga  quanto segue: un processo padre P scrive un messaggio su un file, quindi genera due processi figli P1 e P2 e attende che terminino. Il figlio P1 inizializza una variabile X al valore 13 e quindi entra nello stato di bloccato. P2 esegue un ciclo infinito nel quale genera ogni secondo un numero intero casuale compreso tra 1 e 20. Quando P2 estrae il numero X, definito da P1, invia un segnale a P1 per risvegliarlo e P2 termina. P1, riattivato dal segnale che ha ricevuto da P2 legge il file scritto dal padre e lo visualizza sullo schermo. Infine P1 fa terminare l'applicazione.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/wait.h>
#include <time.h>
#include <fcntl.h>
#define LEGGI 0
#define SCRIVI 1

int pd[2];//descrittore della pipe (comunicazione tra figlio1 e figlio2)
pid_t pid1,pid2;
char *fileMes="2008-a4.txt";
/* genera un numero casuale compreso tra min e max */
int getRandom(int min, int max) {
	return rand()%(max-min+1)+min;
}
void handler(int sig) {
	printf("figlio1: segnale ricevuto \n");
}
void figlio1 () {
	int fd;
	int x=13;
	char mes[80];
	signal(SIGUSR1,handler); // gestione del segnale SIGUSR1
	close (pd[LEGGI]);
	printf("figlio1: x=%d \n",x);
  write(pd[SCRIVI], &x, sizeof(int)); // figlio1 comunica il valore x a figlio2
	pause(); // figlio1 si blocca
	fd=open(fileMes,O_RDONLY);
	read (fd,mes,sizeof(mes));
	close(fd);
	printf("figlio1: messaggio: %s \n",mes);
	exit(0);
}

void figlio2(){
	int x=-1,y=0;
	char buf[4], mess[80];
	close (pd[SCRIVI]);
	read(pd[LEGGI], &x, sizeof(x)); //legge dalla pipe il valore x scritto da figlio1
	srand(time(NULL)); // seme per random
	while (y!=x) {
		y=getRandom(1,20);
		printf("figlio2: y=%d \n",y);
		
	}
	kill(pid1,SIGUSR1); // invia il segnale SIGUSR1 a figlio1 (per risvegliarlo)
	printf("figlio2: termina \n");
	exit(0);
}

int main() {
	int fd,stato;
	char mes[]="Saluti dal processo padre\n";
	fd=open(fileMes,O_CREAT|O_WRONLY);
	write(fd,mes,sizeof(mes));
	close(fd);
	/* crea pipe per la comunicazione tra figlio1 e figlio2*/
	if (pipe(pd)<0) {
		printf("errore pipe");
		exit(1);
	}
	pid1=fork(); // crea processo figlio1
	switch(pid1) {
	case -1:
		// errore fork
		exit(1);
	case 0:
		// codice esguito dal processo figlio1
		figlio1();
		break;
	default:
		//codice eseguito dal processo padre
		pid2=fork(); // crea processo figlio2
		switch(pid2) {
		case -1:
			// errore fork
			exit(1);
		case 0:
			// codice esguito dal processo figlio
			figlio2();
			break;
		default:
			//codice eseguito dal processo padre
			wait(&stato); //attende che un figlio termini
			wait(&stato);	//attende che un figlio termini
		}
	}
}
	

