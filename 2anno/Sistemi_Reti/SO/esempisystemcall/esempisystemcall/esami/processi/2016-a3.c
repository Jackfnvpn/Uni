/** 
	Sistemi operativi e reti (SOR)
	Appello 3 - A.A. 2015/2016
	@author Pietro Frasca 
	@version 1.00 20-09-2016
	
	Realizzate un programma multi processo in C, completo di commento, che svolga  quanto segue: un processo P genera due processi figli P1 e P2. Il figlio P1 scrive un messaggio M in un file quindi esegue un ciclo indeterminato nel quale genera casualmente una sequenza di numeri interi compresi tra 1 e 1000. Quando il numero generato assume il valore 500 P1 termina il lavoro di generazione dei numeri e invia un segnale a P2. P2, che nel frattempo non svolgeva alcun lavoro (stato di bloccato), ricevuto il segnale da P1 legge il messaggio M contenuto nel file salvato da P1 e lo visualizza sullo schermo.  (4 punti) 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <time.h>

int TARGET=500;
char mes[]="Cari saluti";
char fileMes[]="appello3.txt";
int fd,p1,p2;

void gestore(int s){
	fd=open(fileMes,O_RDONLY);
  read(fd,mes,sizeof(mes));
  printf("figlio 2 legge: %s \n",mes);
  close(fd);
}

void figlio1() {
	int x;
	fd=open(fileMes,O_CREAT|O_WRONLY);
	write(fd,mes,sizeof(mes));
	close(fd);
	srand(time(NULL));
	do{
	 	x=rand()%1000+1;
		printf("figlio1: x=%d \n",x);
	} while (x!=TARGET);
	kill(p2,SIGUSR1);
	exit(0);
}

void figlio2() {
	signal (SIGUSR1,gestore);
  pause();
	exit(0);
}

int main(){
 	int stato1, stato2;
  p2=fork();
	switch(p2) {
	case 0: 
		//figlio2
		figlio2();	
		printf ("figlio2 termina");     
		break;
	case -1:
		// errore fork
		exit(1);
	default:
		//padre
		p1=fork();
		if (p1==0){
			// figlio 1
			figlio1();
			printf ("figlio1 termina \n");    
		} else if (p1==-1) {
			// errore fork
			exit(1);
		} else {
			wait(&stato1);     
			wait(&stato2);
		}     
	}	
 }
