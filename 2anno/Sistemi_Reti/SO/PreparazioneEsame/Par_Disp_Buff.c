/*
Due thread, il produttore inserisce numeri pari da 0 a 100 in posizioni pari, e numeri dispari da 100 a 200 in posizioni dispari all'interno di un buffer di N elementi,
iniziliazzato a -1, il consumatore legge dal buffer un numero pari e un numero dispari,
li somma e stampa la loro somma.
*/
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

pthread_t produttore, consumatore;
pthread_mutex_t mutex;
pthread_cond_t cond;
int buffer[10] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

void *prod(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);
        int np = rand() % 101;
        int nd = rand() % 101 + 100;

        int index = rand() % 11;
        if (np % 2 == 0 && index % 2 == 0) {
            buffer[index] = np;
        } else if (nd % 2 == 1 && index % 2 == 1) {
            buffer[index] = nd;
        }

        printf("Sono il thread: ");

        printf("\n[");

        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }

        printf("]\n");
        pthread_mutex_unlock(&mutex);
        pthread_cond_signal(&cond);
        sleep(1);
    }
}

void *cons(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);
        for (int i = 0; i < sizeof(buffer) / sizeof(int); i++) {
            if (buffer[i] == -1) {
                pthread_cond_wait(&cond, &mutex);
                i = 0;
            }
        }

        int p = rand() % 11;
        int d = rand() % 11;

        if (p % 2 == 1) {
            p++;
        }

        if (d % 2 == 0) {
            d++;
        }

        if (d == 10) {
            d--;
        }

        printf("\n%d", buffer[p] + buffer[d]);

        pthread_mutex_unlock(&mutex);
        sleep(1);
    }
}

int main(int argc, char *argv[]) {
    pthread_create(&produttore, NULL, prod, NULL);
    pthread_create(&consumatore, NULL, cons, NULL);

    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&cond, NULL);

    pthread_join(produttore, NULL);
    pthread_join(consumatore, NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond);

    return EXIT_SUCCESS;
}
