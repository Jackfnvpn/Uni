#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

#define N 5

sem_t semaforo;
sem_t empty;
sem_t full;

int buffer[N];
int in = 0;

void down(sem_t *sem) { sem_wait(sem); }

void up(sem_t *sem) { sem_post(sem); }
void remove_buffer() {
    int item = buffer[in - 1];
    printf("\nRimuovo: %d", item);
    in--;
}
void add_buffer() {
    int item = rand() % 100;
    printf("\nAggiungo: %d", item);
    buffer[in] = item;
    in++;
}

void print_buffer() {
    for (int i = 0; i < in; i++) {
        printf(" %d ", buffer[i]);
    }
    printf("\n");
}

void *producer(void *args) {
    while (1) {
        down(&empty);
        down(&semaforo);
        add_buffer();
        print_buffer();
        up(&semaforo);
        up(&full);
        sleep(2);
    }
}

void *consumer(void *args) {
    while (1) {
        down(&full);
        down(&semaforo);
        remove_buffer();
        print_buffer();
        up(&semaforo);
        up(&empty);
        sleep(2);
    }
}
int main(int argc, char *argv[]) {
    pthread_t prod, cons;
    sem_init(&semaforo, 0, 1);
    sem_init(&empty, 0, N);
    sem_init(&full, 0, 0);

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);

    return EXIT_SUCCESS;
}
