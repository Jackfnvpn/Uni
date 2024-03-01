#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_cond_t cond_p, cond_c;

int var = 0;

void *producer(void *args) {
    int n = 0;
    while (1) {
        pthread_mutex_lock(&mutex);

        if (var != 0) {
            pthread_cond_wait(&cond_p, &mutex);
        }

        n = rand() % 101;

        printf("Mando il numero: %d\n", n);

        var = n;

        pthread_mutex_unlock(&mutex);
        pthread_cond_signal(&cond_c);
    }
}

void *consumer(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);

        if (var == 0) {
            pthread_cond_wait(&cond_c, &mutex);
        }

        printf("stampo il numero: %d\n", var);
        var = 0;

        pthread_mutex_unlock(&mutex);
        pthread_cond_signal(&cond_p);
    }
}

int main(int argc, char *argv[]) {
    srand(time(NULL));

    pthread_mutex_init(&mutex, NULL);

    pthread_cond_init(&cond_p, NULL);
    pthread_cond_init(&cond_c, NULL);

    pthread_t prod, cons;

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond_p);
    pthread_cond_destroy(&cond_c);

    return EXIT_SUCCESS;
}
