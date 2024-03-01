/**
	Sistemi operativi (SO)
	Appello 1 - A.A. 2006/2007
	@author Pietro Frasca 
	@version 1.00 16-01-2016

	Realizzare un programma in C, completo di commento, che svolga  quanto segue: un processo padre P genera due processi figli P1 e P2. I due figli P1 e P2 generano ogni secondo un numero intero casuale compreso tra 0 e 100. I numeri generati dai figli vengono inviati al processo padre che provvede a sommarli, a stamparli sullo schermo e a memorizzarli in un file. Inizialmente, i processi devono disabilitare il segnale di interruzione SIGINT, in particolare all’arrivo di tale segnale deve essere visualizzato il messaggio di avviso "interruzione disabilitata". Il programma viene terminato dal processo padre quando verifica che la somma dei numeri avuti dai processi figli assume il valore 100. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <fcntl.h>
#define LEGGI 0
#define SCRIVI 1

int fd1[2]; // descrittore pipe 1 (comunicazione tra padre e figlio1)
int fd2[2]; // descrittore pipe 2 (comunicazione tra padre e figlio2)

/* handler per il segnale SIGINT 
 (il segnale SIGINT può essere generato
 premendo contemporaneamente sulla tastiera 
 i tasti CTRL e C)
*/
void handler (int sig){
	printf("Interruzione del segnale %d disabilitata da %d\n",sig, getpid());
}

void figlio1 () {
	int x;
	close (fd1[LEGGI]);// chiude estremo della pipe che non usa
	close (fd2[LEGGI]);
	close (fd2[SCRIVI]);
	srand(time(NULL)); // seme per random
	while (1) {
		x=rand()%101; // genera un numero casuale compreso tra 0 e 100
		printf("figlio1: x1=%d \n",x);
		write(fd1[SCRIVI], &x, sizeof(int));
		sleep(1);	
	}
}

void figlio2(){
	int x;
	close (fd2[LEGGI]);
	close (fd1[LEGGI]);
	close (fd1[SCRIVI]);
	srand(time(NULL)+1); // seme per random
	while (1) {
		x=random()%101;
		printf("figlio2: x2=%d \n",x);
		write(fd2[SCRIVI], &x, sizeof(x));
		sleep(1);	
	}
}

int main() {
	int pid1, pid2;
	int x1, x2, somma;
	int fd;
	char buf[sizeof(int)];
	signal(SIGINT, handler); // gestione segnale SIGINT 
	// crea pipe per la comunicazione tra padre e figlio1 
	if (pipe(fd1)<0) {
		printf("errore pipe");
		exit(1);
	}
	// crea pipe per la comunicazione tra padre e figlio2 
	if (pipe(fd2)<0) {
		printf("errore pipe");
		exit(1);
	}
	pid1=fork(); // crea processo figlio1
	switch(pid1) {
		case -1:
			// errore fork
			exit(1);
			break;
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
					break;
				case 0:
					// codice esguito dal processo figlio2
					figlio2();
					break;
				default:
					//codice eseguito dal processo padre
					
					close(fd1[SCRIVI]); // chiude estremo della pipe che non usa
					close(fd2[SCRIVI]); // chiude estremo della pipe che non usa
					fd=open("2006-a1.txt",O_CREAT|O_WRONLY|O_TEXT,0755);
					while (somma!=100) {
						read(fd1[LEGGI], &x1,sizeof(int)); // legge dalla pipe
						write(fd, &x1,sizeof(int)); // scrive sul file
						read(fd2[LEGGI], &x2,sizeof(int));
						write(fd, &x2,sizeof(int));
					  somma=x1+x2;
						printf("padre: x1 = %d, x2 = %d, somma = %d \n",x1,x2,somma);
					}
					close (fd);
					kill (0,SIGKILL); /* invia il segnale SIGKILL a tutti i processi del gruppo: a se stesso (padre) e ai due figli */ 
					break;		
			}
			break;
	}
}
	

