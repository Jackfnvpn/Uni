/*
 Scrivere un programma in C con tre thread che operano su due array di dimensione N inizialmente a 0.

 Il primo thread scrive in un array A numeri casuali tra 1 e 150, scrivendo un numero per volta in posizioni randomiche.
 Il secondo thread scrive in un array B numeri cassuali tra 150 e 300, scrivendo un numero per volta in posizioni randomiche.
 Il terzo thread controlla se entrambi gli array sono stati inizializzati, in caso affermativo calcola il massimo in A e in B,
 calcola il minimo in A e in B. Infine determina il max{max(A), max(B)} e il min{min(A), min(B)}.

 */

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

#define N 10

pthread_mutex_t mutex1, mutex2;
pthread_t th1, th2, th3;
pthread_cond_t c_th3;
int buffer1[N];
int buffer2[N];

int max(int *, int);
int min(int *, int);

int max(int *x, int v_max) {
    for (int i = 0; i < N; i++) {
        if (x[i] > v_max) {
            v_max = x[i];
        }
    }
    return v_max;
}

int min(int *x, int v_min) {
    for (int i = 0; i < N; i++) {
        if (x[i] < v_min) {
            v_min = x[i];
        }
    }
    return v_min;
}

void *f_th1(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex1);

        int n = (rand() % 150) + 1;

        int index = rand() % N;

        buffer1[index] = n;

        printf("\nSono il thread 1 e questo è il buffer 1: \n");
        printf("\n[");
        for (int i = 0; i < N; i++) {
            printf(" %d ", buffer1[i]);
        }
        printf("]\n");
        pthread_mutex_unlock(&mutex1);
        pthread_cond_signal(&c_th3);
        sleep(1);
    }

    pthread_exit(0);
}

void *f_th2(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex2);

        int n = (rand() % 151) + 150;

        int index = rand() % N;

        buffer2[index] = n;

        printf("\nSono il thread 2 e questo è il buffer 2: \n");
        printf("\n[");
        for (int i = 0; i < N; i++) {
            printf(" %d ", buffer2[i]);
        }
        printf("]\n");
        pthread_mutex_unlock(&mutex2);
        pthread_cond_signal(&c_th3);
        sleep(1);
    }

    pthread_exit(0);
}

void *f_th3(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex1);
        for (int i = 0; i < N; i++) {
            if (buffer1[i] == 0) {
                pthread_cond_wait(&c_th3, &mutex1);
                i = 0;
            }
        }
        pthread_mutex_lock(&mutex2);
        for (int i = 0; i < N; i++) {
            if (buffer2[i] == 0) {
                pthread_cond_wait(&c_th3, &mutex2);
                i = 0;
            }
        }

        int max1, max2, min1, min2;

        max1 = max(buffer1, 0);
        max2 = max(buffer2, 0);

        min1 = min(buffer1, buffer1[0]);
        min2 = min(buffer2, buffer2[0]);

        if (max1 > max2) {
            printf("\nIl massimo più grande si trova nel primo buffer ed è: %d", max1);
        } else {
            printf("\nIl massimo più grande si trova nel secondo buffer ed è: %d", max2);
        }

        if (min1 < min2) {
            printf("\nIl minimo più piccolo si trova nel primo buffer ed è: %d", min1);
        } else {
            printf("\nIl minimo più piccolo si trova nel secondo buffer ed è: %d", min2);
        }

        pthread_mutex_unlock(&mutex1);
        pthread_mutex_unlock(&mutex2);
        sleep(1);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    pthread_mutex_init(&mutex1, NULL);
    pthread_cond_init(&c_th3, NULL);
    pthread_create(&th1, NULL, f_th1, NULL);
    pthread_create(&th2, NULL, f_th2, NULL);
    pthread_create(&th3, NULL, f_th3, NULL);

    pthread_join(th1, NULL);
    pthread_join(th2, NULL);
    pthread_join(th3, NULL);

    return EXIT_SUCCESS;
}
