#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>

pthread_mutex_t mutex;

void *count(void *arg) {

    for (int i = 0; i < 10; i++) {
        pthread_mutex_lock(&mutex);
        printf("\n Ciao sono il thread: %d, è stampo il numero: %d", arg, i);
        pthread_mutex_unlock(&mutex);
    }

    exit(0);
}

int main(int argc, char *argv[]) {

    pthread_t thread1, thread2;

    pthread_create(&thread1, NULL, count, (void *)1);
    pthread_create(&thread2, NULL, count, (void *)2);

    pthread_join(thread1, NULL);
    pthread_join(thread2, NULL);

    pthread_mutex_destroy(&mutex);
}
