/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Esempio con fork, alarm, pause e sleep
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <time.h>
int ns=1;
time_t rawtime;
struct tm* tempo;

void oraEsatta(int sign){
  time (&rawtime);
	tempo = localtime(&rawtime);
	printf("Ora esatta dal processo %d: %d %d %d \n",getpid(), tempo->tm_hour, tempo->tm_min, tempo->tm_sec);
	ns++;
	alarm(ns); // alarm viene riattivato
}

int main (){
	int pid,i=0;
	int tr=0;
	int ts=3;
	printf ("Processo padre con pid:%d \n",getpid()); 
  signal(SIGALRM,oraEsatta); /* gestione del segnale SIGALARM: quando il processo riceverà il segnale SIGALARM, sarà eseguita la funzione oraEsatta */
	pid=fork();
	alarm(ns); /* dopo ns secondi il processo chiamante alarm riceverà il segnale SIGALRM */
	while(i < 5) {
		printf("processo %d sospeso\n",getpid());
    tr=sleep(ts);
    printf("processo %d riattivato dopo %d sec\n",getpid(),ts-tr);
		i++;
  }
  exit(0);
}

