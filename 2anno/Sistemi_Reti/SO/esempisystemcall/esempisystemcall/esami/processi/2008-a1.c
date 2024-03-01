/**
	Sistemi operativi (SO)
	Appello 1 - A.A. 2008/2009
	@author Pietro Frasca 
	@version 1.00 16-01-2016

	Realizzate un programma multi processo in C, completo di commento, che svolga quanto segue: un processo padre P genera due processi figli P1 e P2. I due figli P1 e P2 eseguono un ciclo infinito durante il quale generano ogni secondo un numero intero casuale compreso tra 0 e 100. I numeri generati dai figli vengono inviati al processo padre che provvede a sommarli. Se la somma corrente supera il valore 190, il processo padre provoca la terminazione del figlio P1 e dopo 2 secondi la terminazione del figlio P2. Infine prima di terminare, il processo P salva la somma totale (di tutte le iterazioni effettuate) in un file. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
#include <fcntl.h>
int fd1[2], fd2[2];
char buf[16];
int x;

void figlio1(){
	srand(time(NULL));
  close(fd1[0]);
  while (1) {
		x=rand()%101;
    //sprintf(buf,"%d",x);      
    write(fd1[1],&x,sizeof(int));
  }   
}
void figlio2(){
	srand(time(NULL)+1);  
  close(fd2[0]); 
  while (1) {
		x=rand()%101;
    //sprintf(buf,"%d",x);
    write(fd2[1],&x,sizeof(int));
	}   
}

int main() {
  int pid1, pid2;
  int somma=0;
  int sommaTot=0;
  const int SOGLIA=190;
  if (pipe(fd1)<0) {
    printf("errore pipe1");
    exit(1);
  }
  if (pipe(fd2)<0) {
     printf("errore pipe2");
    exit(1);
  }
  pid1=fork();
  switch(pid1) {
  case -1: 
		printf("errore fork1");
    exit(1);
  case 0:
    // codice figlio1
    figlio1();
    break; 
  default:
    //codice padre
    pid2=fork();
    if (pid2==0) {
      // codice figlio2
      figlio2();
			break;
		}
		while (somma < SOGLIA) {
      somma=0; 
      read(fd1[0],&x,sizeof(int));
      printf("ricevo da figlio1 %d \n",x);    
      somma+=x;
      read(fd2[0],&x,sizeof(int));
      printf("ricevo da figlio2 %d \n",x);
      somma+=x;
      sommaTot+=somma;
      printf("somma = %d, somma tot = %d \n",somma, sommaTot);
      sleep(1);   
    }
    kill(pid1,SIGKILL);
    sleep(2);
    kill(pid2,SIGKILL);
		int fd=open ("./2008-a1.txt",O_CREAT|O_WRONLY,0755);
    sprintf(buf,"%d",sommaTot); 
    write (fd,buf,sizeof(buf));
    close(fd);
	}
}
