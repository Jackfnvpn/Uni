#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int buffer[10] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
pthread_t th1, th2, th3;
pthread_mutex_t mutex;
pthread_cond_t t_cond;
int done = 0;

void *t_pari(void *args) {
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 101;
        int index = rand() % 11;

        if (index % 2 == 0) {
            buffer[index] = n;
        }

        printf("\nCiao sono il thread [1] e questo è il buffer: \n");
        printf("\n[");

        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }

        printf("]\n");

        pthread_mutex_unlock(&mutex);
        pthread_cond_signal(&t_cond);
        sleep(n % 4);
    }
    pthread_exit(0);
}

void *t_dispari(void *args) {
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 101;
        int index = rand() % 11;

        if (index % 2 != 0) {
            buffer[index] = n;
        }

        printf("\nCiao sono il thread [2] e questo è il buffer: \n");
        printf("\n[");

        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }

        printf("]\n");

        pthread_mutex_unlock(&mutex);
        pthread_cond_signal(&t_cond);

        sleep(n % 4);
    }
    pthread_exit(0);
}

void *t_mutation(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);

        for (int i = 0; i < 10; i++) {
            if (buffer[i] == -1) {
                pthread_cond_wait(&t_cond, &mutex);
                i = 0;
            }
        }

        for (int i = 1; i < 9; i++) {
            buffer[i] = buffer[i] + buffer[i + 1];
        }

        done = 1;

        printf("\n[");

        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }

        printf("]\n");

        pthread_mutex_unlock(&mutex);
        pthread_exit(0);
    }
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    pthread_mutex_init(&mutex, NULL);
    pthread_cond_init(&t_cond, NULL);

    pthread_create(&th1, NULL, t_pari, NULL);
    pthread_create(&th2, NULL, t_dispari, NULL);
    pthread_create(&th3, NULL, t_mutation, NULL);

    pthread_join(th1, NULL);
    pthread_join(th2, NULL);
    pthread_join(th3, NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&t_cond);

    return EXIT_SUCCESS;
}
