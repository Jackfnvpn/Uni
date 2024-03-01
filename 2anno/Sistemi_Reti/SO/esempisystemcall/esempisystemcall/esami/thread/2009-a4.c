/**
	Sistemi operativi e reti (SOR)
	Appello 4 - A.A. 2009/2010
	@author Pietro Frasca
	@version 1.00 11-01-2016

	Realizzate un programma multi thread in C, completo di commento, che implementi il seguente problema del produttore-consumatore: il thread main crea due thread Th1 (produttore) e Th2 (consumatore).  Il thread produttore esegue un ciclo indeterminato, durante il quale genera ogni secondo un numero intero casuale compreso tra 0 e 255 e lo scrive in un buffer di dimensione 1 (variabile intera). Il thread consumatore legge il valore scritto dal produttore nel buffer e lo visualizza sullo schermo. Il programma termina quando il valore del buffer assume il valore 128. Il programma deve essere realizzato in modo tale che il consumatore legga ogni valore scritto dal produttore. 
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>

int buffer=0;// buffer condiviso
pthread_mutex_t mutex;
pthread_cond_t cond_pronto;
pthread_cond_t cond_pieno;
int dato_pronto=0;
int buffer_pieno=0;

void * produttore(void *arg){
	int i=0;
	srand(time(NULL));
	while(1){
		pthread_mutex_lock(&mutex);
		if (buffer_pieno) pthread_cond_wait(&cond_pieno,&mutex);
		buffer=rand()%256;
		buffer_pieno=1;
		dato_pronto=1;
		printf("Produttore ->%d\n",buffer);
		pthread_cond_signal(&cond_pronto);
		pthread_mutex_unlock(&mutex);
		if (buffer==128) break;
		sleep (1);
	}
	pthread_exit(0);
}

void * consumatore(void *arg){
	
	while(1){
		pthread_mutex_lock(&mutex);
		if (!dato_pronto) pthread_cond_wait(&cond_pronto,&mutex);
		printf("consumatore <--%d\n\n",buffer);
		if (buffer==128) break;
		buffer_pieno=0;
		dato_pronto=0;
		pthread_cond_signal(&cond_pieno);
		pthread_mutex_unlock(&mutex);	
		sleep(1);
	}
	pthread_exit(0);
}

int main(){
	pthread_t prod,cons;
	pthread_mutex_init(&mutex,NULL);
	pthread_cond_init(&cond_pronto,NULL);
	pthread_cond_init(&cond_pieno,NULL);
	pthread_create(&prod,NULL,produttore,NULL);
	pthread_create(&cons,NULL,consumatore,NULL);
	pthread_join(prod,NULL);
	pthread_join(cons,NULL);
}
