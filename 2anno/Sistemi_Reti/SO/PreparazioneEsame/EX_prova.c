#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

int buffer[11] = {0};
pthread_mutex_t mutex;
pthread_cond_t cond_th3;
pthread_t th1, th2, th3;

void *f_th1(void *args) {
    int count_value;

    while (1) {
        pthread_mutex_lock(&mutex);
        if (count_value == 11) {

            pthread_cond_signal(&cond_th3);
            pthread_cond_wait(&cond_th3, &mutex);
        }
        int n = rand() % 11;
        buffer[n] = 1;
        count_value = 0;

        printf("Ciao, sono il thread 1\n");

        printf("[");

        for (int i = 0; i < 11; i++) {
            if (buffer[i] == 1 || buffer[i] == -1) {
                count_value++;
            }
            printf(" %d ", buffer[i]);
        }
        printf("]\n");

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *f_th2(void *args) {
    int count_value;

    while (1) {

        pthread_mutex_lock(&mutex);

        if (count_value == 11) {
            pthread_cond_signal(&cond_th3);
            pthread_cond_wait(&cond_th3, &mutex);
        }

        int n = rand() % 11;

        buffer[n] = -1;

        count_value = 0;

        printf("Ciao, sono il thread 2\n");
        printf("[");

        for (int i = 0; i < 11; i++) {
            if (buffer[i] == 1 || buffer[i] == -1) {
                count_value++;
            }
            printf(" %d ", buffer[i]);
        }
        printf("]\n");

        pthread_mutex_unlock(&mutex);

        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *f_th3(void *args) {

    int n_neg_one;
    int n_pos_one;
    while (1) {

        n_neg_one = 0;
        n_pos_one = 0;

        pthread_mutex_lock(&mutex);

        for (int i = 0; i < 11; i++) {

            if (buffer[i] == 1) {
                n_pos_one++;
            }

            if (buffer[i] == -1) {
                n_neg_one++;
            }

            if (buffer[i] == 0) {
                pthread_cond_wait(&cond_th3, &mutex);
            }
        }

        if (n_pos_one > n_neg_one) {
            printf("Il numero di 1 è maggiore di -1");
            pthread_kill(th1, SIGKILL);
            pthread_kill(th2, SIGKILL);
            pthread_exit(0);

        } else {
            pthread_cond_broadcast(&cond_th3);
            pthread_cond_wait(&cond_th3, &mutex);
            // sleep(rand() % 4);
        }
        pthread_mutex_unlock(&mutex);
    }
}

int main(int argc, char *argv[]) {
    srand(time(NULL));

    pthread_cond_init(&cond_th3, NULL);

    pthread_mutex_init(&mutex, NULL);

    pthread_create(&th1, NULL, f_th1, NULL);
    pthread_create(&th2, NULL, f_th2, NULL);
    pthread_create(&th3, NULL, f_th3, NULL);

    pthread_join(th1, NULL);
    pthread_join(th2, NULL);
    pthread_join(th3, NULL);

    printf("All threads terminated.\n");

    return EXIT_SUCCESS;
}
