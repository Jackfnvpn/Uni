/*
Sei thread, un scrittore e 5 lettori.
Lo scrittore scrive su un buffer numeri dispari da 0 a 50 nelle posizioni pari e numeri pari da 50 a 100 nelle posizioni dispari.
I lettori leggono coppie di numeri (paro, disparo), li somma e li stampa.

*/

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <strings.h>
#include <time.h>
#include <unistd.h>
#define N 5

sem_t reader;
sem_t writer;
pthread_t wr, rd[5];
int buffer[N];
sem_t count;
int count_rd = 0;

void *f_rd(void *args) {
    int p, d, index;
    while (1) {

        sem_wait(&count);

        count_rd++;

        if (count_rd == 1) {

            sem_wait(&reader);
        }

        sem_post(&count);

        printf("\nSono il thread %d\n", args);

        index = rand() % N;

        while (buffer[index] % 2 == 0) {
            index = rand() % N;
        }

        d = buffer[index];

        while (buffer[index] % 2 == 1) {
            index = rand() % N;
        }

        p = buffer[index];

        printf("\nHo preso questi numeri interi (%d,%d) e questa è la loro somma: %d \n", p, d, d + p);

        sem_wait(&count);

        count_rd--;

        sem_post(&count);

        // sleep(1);

        if (count_rd == 0) {
            sem_post(&writer);
        }
    }
    pthread_exit(0);
}

void *f_wr(void *args) {
    int n_p, n_d;
    while (1) {
        sem_wait(&writer);

        printf("\nSono lo scrittore \n");
        for (int i = 0; i < N; i++) {

            n_p = 1;
            n_d = 0;

            while (n_d % 2 == 0) {
                n_d = rand() % 51;
            }

            while (n_p % 2 == 1) {
                n_p = (rand() % 51) + 50;
            }

            if (i % 2 == 0) {
                buffer[i] = n_d;
            } else {
                buffer[i] = n_p;
            }
        }

        printf("\n[");
        for (int i = 0; i < N; i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        sem_post(&reader);
        // sleep(1);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    sem_init(&writer, 0, 1);
    sem_init(&reader, 0, 0);
    sem_init(&count, 0, 1);

    pthread_create(&wr, NULL, f_wr, NULL);

    for (int i = 0; i < 5; i++) {
        pthread_create(&rd[i], NULL, f_rd, (void *)i);
    }

    pthread_join(wr, NULL);

    for (int i = 0; i < 5; i++) {
        pthread_join(rd[i], NULL);
    }

    return EXIT_SUCCESS;
}
