/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Questo programma multithread simula il comportamente di clienti che si recano in banca per eseguire commissioni. Supponiamo che ciascun cliente esegua le seguenti operazioni in sequenza temporale:

  1. arriva in banca -> creazione del thread;
  2. preme il pulsante per entrare dalla porta girevole che consente l'ingresso o l'uscita di un cliente alla volta -> richiesta_ingresso();
  3. occupa la porta per entrare per un tempo tempoIngresso -> ingresso(tempoIngresso);	
  4. entra effettivamente in banca rilasciando la porta di ingresso/uscita -> rilascio_ingresso();
  5. esegue la commissione impiegando un tempo tempoCommissione -> commissione(tempoCommissione);
  6. preme il pulsante per uscire dalla porta girevole che consente l'ingresso o l'uscita di un cliente alla volta -> richiesta_uscita(); 
  7. occupa la porta per uscire per un tempo tempoIngresso -> ingresso(tempoIngresso);
  8. esce dalla banca rilasciando la porta di ingresso/uscita -> rilascio_uscita();

I clienti che arrivano  non possono entrare il banca se sono presenti all'interno un numero di clienti pariad M.

NOTA: per compilare il programma con Linux: gcc clientibanca.c -o clientibanca -lpthread
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <time.h>
#define N  100
#define M  10

struct Banca {
  pthread_mutex_t m;
  pthread_cond_t accesso;
  pthread_cond_t piena;
  int portaOccupata;
  int numClienti;
} banca;

void richiesta_ingresso() {
  pthread_mutex_lock(&banca.m);
  /* Fino a quando la banca è piena il cliente non può entrare */
  while (banca.numClienti >= M) pthread_cond_wait(&banca.piena, &banca.m);
  /* Fino a quando la porta è occupato il cliente non può entrare */ 
  while (banca.portaOccupata) pthread_cond_wait(&banca.accesso, &banca.m);
  banca.numClienti++;
  banca.portaOccupata = 1;
  pthread_mutex_unlock(&banca.m);
}

void ingresso(int tempoIngresso) {
  /* tempo impiegato dal cliente per entrare in banca */
  sleep(tempoIngresso);
}

void rilascio_ingresso() {
  pthread_mutex_lock(&banca.m);
  banca.portaOccupata = 0;
	/* risveglia un eventuale cliente in attesa di entrare */
  pthread_cond_signal(&banca.accesso);
	pthread_mutex_unlock(&banca.m);
}

void commissione(int tempoCommissione) {
  /* tempo impiegato da un cliente per svolgera la sua commissione */
  sleep(tempoCommissione);
}

void richiesta_uscita() {
  pthread_mutex_lock(&banca.m);
  /* fino a quando la porta è occupata il cliente non può uscire */
  while (banca.portaOccupata) pthread_cond_wait(&banca.accesso, &banca.m);
  /* il cliente occupa la porta */
  banca.portaOccupata = 1;
  pthread_mutex_unlock(&banca.m);
}

void uscita(int tempoUscita) {
 /* tempo impiegato dal cliente per uscire */	
  sleep(tempoUscita);
}

void rilascio_uscita() {
  /* il cliente esce e libera la porta. */
  pthread_mutex_lock(&banca.m);
  banca.portaOccupata = 0;
  banca.numClienti--;
  pthread_cond_signal(&banca.piena);
	pthread_cond_signal(&banca.accesso);  
  pthread_mutex_unlock(&banca.m);
}

int tempoRnd (int min, int max) {
  return rand()%(max-min+1)+min;
}

void *cliente(void *arg) {
  int id = *((int *)arg);
  /* Supponiamo costante il tempo di entrata e di uscita */
  int tempoIn=1;
  int tempoOut=1;
  /* Generalmente il tempo di una commissione è diverso per i vari clienti */
  int tempoComm=tempoRnd(40,60);  
  printf("cliente %d: richiesta d'ingresso \n",id);
  richiesta_ingresso();
  printf("cliente %d: occupa la porta in ingresso \n",id);
  ingresso(tempoIn);
  printf("cliente %d: entra e libera la porta \n",id);
  rilascio_ingresso();
  printf("cliente %d: esegue la commmissione in banca \n",id);
  commissione(tempoComm);
  printf("cliente %d: richiesta di uscita \n",id);  
  richiesta_uscita();
  printf("cliente %d: occupa la porta in uscita \n",id);
  uscita(tempoOut);
  printf("cliente %d: esce e libera la porta \n",id);
  rilascio_uscita();
}

int main() {
  int i;
  int id[N];
  pthread_t tid[N];
  pthread_mutex_init(&banca.m, NULL);
  pthread_cond_init(&banca.piena, NULL);
  pthread_cond_init(&banca.accesso, NULL);
  banca.numClienti = 0;
  banca.portaOccupata = 0;
  srand(time(NULL));
  for (i=1; i<=N; i++) {
    id[i] = i;
    printf("----> Il cliente %d arriva in banca. Ci sono %d clienti in banca. \n",i, banca.numClienti); 
    pthread_create(&tid[i], 0, cliente, &id[i]);
		sleep(tempoRnd(2,4));
  }
	
	for (i=0; i<N; i++) {
    pthread_join(tid[i], NULL);
  }
}
