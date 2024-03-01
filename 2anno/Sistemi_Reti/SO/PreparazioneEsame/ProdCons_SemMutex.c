#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

sem_t full, empty;
pthread_mutex_t mutex;
pthread_t prod, cons;
int buffer[10];
int in = 0;

void *producer(void *args) {

    while (1) {
        sem_wait(&empty);
        pthread_mutex_lock(&mutex);

        buffer[in] = rand() % 10;
        in++;

        printf("\nCiao sono il producer e questo è il buffer:\n");
        printf("[");
        for (int i = 0; i < in; i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        sem_post(&full);
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *consumer(void *args) {
    while (1) {
        sem_wait(&full);
        pthread_mutex_lock(&mutex);

        // buffer[in] = rand() % 10;
        in--;

        printf("\nCiao sono il consumer e questo è il buffer:\n");
        printf("[");
        for (int i = 0; i < in; i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        sem_post(&empty);
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    sem_init(&full, 0, 0);
    // printf("%d", sizeof(buffer) / sizeof(int));
    sem_init(&empty, 0, sizeof(buffer) / sizeof(int));
    pthread_mutex_init(&mutex, NULL);

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    pthread_mutex_destroy(&mutex);
    sem_destroy(&full);
    sem_destroy(&empty);

    return EXIT_SUCCESS;
}
