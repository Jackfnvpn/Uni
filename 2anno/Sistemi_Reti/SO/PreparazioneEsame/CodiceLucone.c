
#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

// inizializzo mutex e condition
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;
pthread_cond_t condition = PTHREAD_COND_INITIALIZER;

int buffer[11];
pthread_t threadsArray[3];

#define TRUE 1
#define FALSE 1
#define bufferSize 11

// funzione che stampa il contenuto del buffer
void *printBuffer() {

    for (int i = 0; i < bufferSize; i++) {
        printf("Position %d -> %d\n", i, buffer[i]);
    }
    printf("\n");
}

// funzione che aggiunge il numero 1 in una posizione casuale del buffer
void *aggiungiUnoPositivo() {

    // signal(SIGINT,signalHandler);

    while (TRUE) {

        pthread_mutex_lock(&mutex);

        // estraggo il numero di cella casuale
        int randomCell = random() % 12;

        // inserisco 1 nella cella
        buffer[randomCell] = 1;

        // estraggo un numero casuale di secondi da dormire
        int sleepTime = random() % 4;

        // log
        printf("Sono il thread positivo e ho aggiunto 1 in posizione %d, dormo per %d secondi\n", randomCell, sleepTime);
        printBuffer();

        // rilascio il mutex e invio un segnale
        pthread_cond_signal(&condition);
        pthread_mutex_unlock(&mutex);

        // dormo
        sleep(sleepTime);
    }
}

// funzione che aggiunge il numero -1 in una posizione casuale del buffer
void *aggiungiUnoNegativo() {

    // signal(SIGINT,signalHandler);

    while (TRUE) {

        pthread_mutex_lock(&mutex);

        // estraggo il numero di cella casuale
        int randomCell = random() % 12;

        // inserisco 1 nella cella
        buffer[randomCell] = -1;

        // estraggo un numero casuale di secondi da dormire
        int sleepTime = random() % 4;

        // log
        printf("Sono il thread negativo e ho aggiunto -1 in posizione %d, dormo per %d secondi\n", randomCell, sleepTime);
        printBuffer();

        // rilascio il mutex e invio un segnale
        pthread_cond_signal(&condition);
        pthread_mutex_unlock(&mutex);

        // dormo
        sleep(sleepTime);
    }
}

// funzioone che controlla se il buffer è stato inizializzato ovvero se tutte le celle sono diverse da 0
int checkBufferInit() {
    for (int i = 0; i < bufferSize; i++) {
        if (buffer[i] == 0) {
            return 0;
        }
    }
    return 1;
}

// funzione che controlla che il buffer sia stato inizializzato e che controlla se ci sono più 1 o -1
void checkBuffer() {

    while (TRUE) {

        pthread_mutex_lock(&mutex);

        // controllo se il mutex ha qualche 0
        while (checkBufferInit() == 0) {

            pthread_cond_wait(&condition, &mutex);
        }

        // controllo se ci sono più 1 oppure -1
        int positiveCounter = 0;
        int negativeCounter = 0;

        for (int i = 0; i < bufferSize; i++) {

            if (buffer[i] == 1) {
                positiveCounter++;
            } else {
                negativeCounter++;
            }
        }

        // stampo il risultato
        printf("Ci sono %d numeri positivi\n", positiveCounter);
        printf("Ci sono %d numeri negativi\n", negativeCounter);
        if (positiveCounter > negativeCounter) {
            printf("Le celle con un numero positivo sono maggiore di quelle con un numero negativo\n");
        }

        // Killo i threads
        pthread_kill(threadsArray[1], SIGINT);
        pthread_kill(threadsArray[2], SIGINT);

        // autokill
        pthread_exit(0);
    }
}

int main() {

    srand(time(NULL));

    pthread_create(&threadsArray[0], NULL, &checkBuffer, NULL);
    pthread_create(&threadsArray[1], NULL, &aggiungiUnoPositivo, NULL);
    pthread_create(&threadsArray[2], NULL, &aggiungiUnoNegativo, NULL);

    pthread_join(threadsArray[0], NULL);
    pthread_join(threadsArray[1], NULL);
    pthread_join(threadsArray[2], NULL);

    return 0;
}
