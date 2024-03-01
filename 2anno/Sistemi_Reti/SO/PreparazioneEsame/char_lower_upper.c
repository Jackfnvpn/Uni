#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

pthread_t Threads[8];
sem_t count_threads;

char string[] = "ciaocome";
int in = 0;

void *Upper(void *args) {

    sem_wait(&count_threads);

    string[in] = string[in] - 'a' + 'A';

    printf("\nCiao sono il thread [%d] e questa è l'attuale stringa: %c\n", args, string[in]);

    in++;

    sem_post(&count_threads);

    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    sem_init(&count_threads, 0, 1);

    for (int i = 0; i < 8; i++) {
        pthread_create(&Threads[i], NULL, Upper, (void *)i);
    }

    for (int i = 0; i < 8; i++) {
        pthread_join(Threads[i], NULL);
    }

    printf("\n%s\n", string);

    return EXIT_SUCCESS;
}
