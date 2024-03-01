#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int buffer[11];
pthread_mutex_t mutex;
pthread_t adder_Pos, adder_Neg, checker;
int done = 0;

void *pos_Values(void *args) {
    int n;
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        n = rand() % 11;
        buffer[n] = 1;
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *neg_Values(void *args) {
    int n;
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        n = rand() % 11;
        buffer[n] = -1;
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *check_Values(void *args) {
    int count_pos, count_neg, count_zero;
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        count_pos = 0;
        count_neg = 0;
        count_zero = 0;
        printf("[");
        for (int i = 0; i < sizeof(buffer) / sizeof(int); i++) {
            if (buffer[i] > 0) {
                count_pos++;
            }
            if (buffer[i] < 0) {
                count_neg++;
            }

            if (buffer[i] == 0) {
                count_zero++;
            }

            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        if (count_pos > count_neg && count_zero == 0) {
            printf("Il numero di positivi è maggiore del numero di negativi\n");
            done = 1;
        }
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    srand(time(NULL));

    pthread_create(&adder_Pos, NULL, pos_Values, NULL);
    pthread_create(&adder_Neg, NULL, neg_Values, NULL);
    pthread_create(&checker, NULL, check_Values, NULL);

    pthread_mutex_init(&mutex, NULL);

    pthread_join(adder_Pos, NULL);
    pthread_join(adder_Neg, NULL);
    pthread_join(checker, NULL);

    pthread_mutex_destroy(&mutex);

    return EXIT_SUCCESS;
}
