#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
// #include <unistd.h>
#include <pthread.h>

sem_t semaphore_ping;
sem_t semaphore_pong;

void up(sem_t *sem) { sem_post(sem); }

void down(sem_t *sem) { sem_wait(sem); }

void *ping(void *arg) {
    for (int i = 0; i < 10; i++) {
        down(&semaphore_ping);
        printf("\nping ");
        up(&semaphore_pong);
    }
    pthread_exit(0);
}

void *pong(void *arg) {
    for (int i = 0; i < 10; i++) {
        down(&semaphore_pong);
        printf(" pong\n");
        up(&semaphore_ping);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    sem_init(&semaphore_ping, 0, 1);
    sem_init(&semaphore_pong, 0, 0);
    pthread_t ping_t, pong_t;

    pthread_create(&ping_t, NULL, ping, NULL);
    pthread_create(&pong_t, NULL, pong, NULL);

    pthread_join(ping_t, NULL);
    pthread_join(pong_t, NULL);

    sem_destroy(&semaphore_ping);
    sem_destroy(&semaphore_pong);

    return EXIT_SUCCESS;
}
