#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define MAX 10

pthread_mutex_t mutex;
pthread_cond_t c_cons, c_prod;
int buffer[MAX];
int in = 0;
int size_b = sizeof(buffer) / sizeof(int);

void add_element() {
    printf("Aggiungo elemento: %d\n", in);
    buffer[in] = in;
    in++;
}

void remove_element() {
    int item = buffer[in - 1];
    printf("Rimuovo elemento: %d\n", item);
    in--;
}

void print_buffer() {
    printf("\n[");
    for (int i = 0; i < in; i++) {
        printf(" %d ", buffer[i]);
    }
    printf("]\n");
}

void *producer(void *arg) {
    for (int i = 0; i < MAX; i++) {
        pthread_mutex_lock(&mutex);

        if (in == MAX) {
            pthread_cond_wait(&c_prod, &mutex);
        }
        add_element();
        print_buffer();

        pthread_cond_signal(&c_cons);
        pthread_mutex_unlock(&mutex);
    }
    pthread_exit(0);
}

void *consumer(void *arg) {
    for (int i = 0; i < MAX; i++) {
        pthread_mutex_lock(&mutex);

        if (in == 0) {
            pthread_cond_wait(&c_cons, &mutex);
        }

        remove_element();
        print_buffer();

        pthread_cond_signal(&c_prod);
        pthread_mutex_unlock(&mutex);
    }

    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    pthread_t prod, cons;
    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&c_cons, NULL);
    pthread_cond_init(&c_prod, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    pthread_cond_destroy(&c_cons);
    pthread_cond_destroy(&c_prod);

    return EXIT_SUCCESS;
}
