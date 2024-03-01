#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

pthread_mutex_t mutex;
int buffer[10] = {0};
pthread_cond_t cond_check;
pthread_t prod, cons, check;

void *producer(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 10;
        buffer[n]++;
        printf("Ciao sono il Producer, incremento in posizione [%d] e questo è il buffer attuale:\n", n);
        printf("[");
        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");

        pthread_cond_signal(&cond_check);
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *consumer(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 10;
        buffer[n]--;
        printf("Ciao sono il Consumer, incremento in posizione [%d] e questo è il buffer attuale:\n", n);
        printf("[");
        for (int i = 0; i < 10; i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");

        pthread_cond_signal(&cond_check);
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}
/*int condition_buffer() {
    for (int i = 0; i < 10; i++) {
        if (buffer[i] == 0) {
            return 0;
        }
    }
    return 1;
}*/
void *checker(void *args) {
    while (1) {

        pthread_mutex_lock(&mutex);

        /*while (condition_buffer() == 0) {
            pthread_cond_wait(&cond_check, &mutex);
        }*/

        for (int i = 0; i < 10; i++) {
            if (buffer[i] == 0) {
                pthread_cond_wait(&cond_check, &mutex);
                i = 0;
            }
        }
        int count_pos = 0, count_neg = 0;

        for (int i = 0; i < 10; i++) {
            if (buffer[i] > 0) {
                count_pos++;
            }
            if (buffer[i] < 0) {
                count_neg++;
            }
        }

        if (count_pos > count_neg) {
            printf("\nIl numero di interi positivi è maggiore di quelli negativi\n");
            printf("[");
            for (int i = 0; i < 10; i++) {
                printf(" %d ", buffer[i]);
            }
            printf("]");

            // pthread_kill(prod, SIGKILL);
            // pthread_kill(cons, SIGKILL);
            exit(0);
        }

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
}

int main(int argc, char *argv[]) {

    srand(time(NULL));

    pthread_mutex_init(&mutex, NULL);

    pthread_create(&prod, NULL, producer, NULL);
    pthread_create(&cons, NULL, consumer, NULL);
    pthread_create(&check, NULL, checker, NULL);

    pthread_cond_init(&cond_check, NULL);

    pthread_join(prod, NULL);
    pthread_join(cons, NULL);
    pthread_join(check, NULL);

    pthread_mutex_destroy(&mutex);
    pthread_cond_destroy(&cond_check);

    return EXIT_SUCCESS;
}
