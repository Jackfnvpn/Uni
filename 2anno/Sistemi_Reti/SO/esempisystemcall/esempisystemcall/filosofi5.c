/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Il problema dei 5 filosofi.
	Il problema dei 5 filosofi a cena è un esempio che mostra un problema di sincronizzazione tra thread (o processi) paralleli. Cinque filosofi stanno cenando in un tavolo rotondo. Ciascun filosofo ha il suo piatto di spaghetti e condivide una bacchetta a destra e un bacchetta a sinistra con i vicini di tavola. Ci sono quindi solo cinque bacchette e per mangiare ne servono 2 per persona. Immaginiamo che durante la cena, un filosofo trascorra periodi in cui mangia e in cui pensa, e che ciascun filosofo abbia bisogno di due bacchette per mangiare, e che le bacchette siano prese una per volta. Quando possiede due bacchette, il filosofo mangia per un pò, poi lascia le bacchette, una per volta, e ricomincia a pensare. Il problema consiste nel trovare un algoritmo che eviti situazioni di stallo (deadlock) e di starvation (attesa indefinita). Lo stallo può verificarsi se ciascuno dei filosofi acquisisce una bacchetta senza mai riuscire a prendere l'altra. Il filosofo F1 aspetta di prendere la bacchetta che ha in mano il filosofo F2, che aspetta la bacchetta che ha in mano il filosofo F3, e così via (condizione di attesa circolare). La situazione di starvation può verificarsi indipendentemente dal deadlock se uno dei filosofi non riesce mai a prendere entrambe le bacchette.
	La soluzione qui riportata evita il verificarsi dello stallo (evitando la condizione di attesa circolare) inponendo che i filosofi (identificati da un indice) con indice pari (considerando 0 pari) prendano prima la bacchetta alla loro destra e poi quella alla loro sinistra. Viceversa i filosofi con indice dispari prendano prima la bacchetta che si trova alla loro sinistra e poi quella alla loro destra.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <pthread.h>
#include <string.h>
#define NUMFILOSOFI 5
#define CICLI 100

typedef struct{ 
    int id; 
    pthread_t thread_id;
    char nome[20];
  } Filosofo;

/* Le bacchette sono risorse condivise, quindi ne gestiamo l'accesso mediante l'uso di mutex*/
pthread_mutex_t bacchetta[NUMFILOSOFI];

/* sospende per un intervallo di tempo random l'esecuzione del thread chiamante */
void tempoRnd(int min, int max) { 
  sleep(rand()%(max-min+1) + min);
}

void *filosofo_th(void *id){ 
  Filosofo fil=*(Filosofo *)id;
  int i;
  
  for (i=0; i<CICLI; i++){ 
    printf("Filosofo %d: %s sta pensando \n", fil.id+1,fil.nome);
    tempoRnd(3, 12);
    printf("Filosofo %d: %s ha fame\n", fil.id+1 ,fil.nome);
    /* condizione che elimina l'attesa circolare */
    if (fil.id % 2){ 
      // bacchetta destra
			pthread_mutex_lock(&bacchetta[fil.id]);
			printf("Filosofo %d: %s prende la bacchetta destra (%d)\n", fil.id+1, fil.nome,fil.id+1);
      tempoRnd(1,2);
      // bacchetta sinistra
			pthread_mutex_lock(&bacchetta[(fil.id+1) % NUMFILOSOFI]);
			printf("Filosofo %d: %s prende la bacchetta sinistra (%d)\n", fil.id+1, fil.nome,(fil.id+1)%NUMFILOSOFI+1);
		}
    else{ 
      // bacchetta sinistra
			pthread_mutex_lock(&bacchetta[(fil.id+1) % NUMFILOSOFI]);
			printf("Filosofo %d: %s prende la bacchetta sinistra (%d)\n", fil.id+1, fil.nome,(fil.id+1)%NUMFILOSOFI+1);
      tempoRnd(1,2);
      // bacchetta destra
			pthread_mutex_lock(&bacchetta[fil.id]);
			printf("Filosofo %d: %s prende la bacchetta destra (%d)\n", fil.id+1, fil.nome,fil.id+1);
    }
    printf("Filosofo %d: %s sta mangiando \n", fil.id+1,fil.nome);
    tempoRnd(3, 5);

    pthread_mutex_unlock(&bacchetta[fil.id]);
    printf("Filosofo %d: %s posa la bacchetta destra (%d)\n", fil.id+1, fil.nome,fil.id+1);
    pthread_mutex_unlock(&bacchetta[(fil.id+1) % NUMFILOSOFI]);
    printf("Filosofo %d: %s posa la bacchetta sinistra (%d)\n", fil.id+1, fil.nome, (fil.id+1)%NUMFILOSOFI+1);
  }

}

int main(int argc, char *argv[]){ 
  int i;
  char nome[][20]={"Socrate","Platone","Aristotele","Talete","Pitagora"};
  Filosofo filosofo[NUMFILOSOFI];
  srand(time(NULL));

  /* inizializza i mutex */
  for (i=0; i<NUMFILOSOFI; i++)
    pthread_mutex_init(&bacchetta[i], NULL);

  /* crea e avvia i threads */
  for (i=0; i<NUMFILOSOFI; i++){
    filosofo[i].id=i;
    strcpy(filosofo[i].nome,nome[i]);
    if (pthread_create(&filosofo[i].thread_id, NULL, filosofo_th, &filosofo[i]))
      perror("errore pthread_create");
    }

  /* il thread main attende che i thread filosofi terminino */ 
  for (i=0; i<NUMFILOSOFI; i++)
    if (pthread_join(filosofo[i].thread_id, NULL))
      perror("errore pthread_join");

  return 0;
}


