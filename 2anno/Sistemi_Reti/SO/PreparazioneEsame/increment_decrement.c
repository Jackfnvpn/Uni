// Write a C program that follows the specifications below:
// 1. Create an array (buffer) of 11 integers, initialized to zero.
// 2. Generate three threads using POSIX libraries with the following specifications:
//    - The first thread, in an infinite loop, randomly chooses a cell in the buffer and increments its value by 1.
//    - The second thread, in an infinite loop, randomly chooses a cell in the buffer and decrements its value by 1.
//    - The third thread, in an infinite loop, checks if all cells in the buffer are initialized to a non-zero value.
//      If true, it determines whether the count of cells containing +1 is greater than those with -1,
//      and if so, it terminates all three threads.
// 3. Ensure mutual exclusion: While one thread has access to the buffer (read or write), no other thread should access it.
// 4. Once a thread has accessed the buffer, it should wait for a random number of seconds between 0 and 3 before accessing it again.

#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

pthread_mutex_t mutex;
pthread_t th1, th2, th3;
int done = 0;
int buffer[11] = {0};

void *f_th1(void *args) {
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 11;
        buffer[n]++;
        printf("Ciao sono il thread [1] e questo è il buffer: \n");
        printf("[");
        for (int i = 0; i < sizeof(buffer) / sizeof(int); i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *f_th2(void *args) {
    while (done == 0) {
        pthread_mutex_lock(&mutex);
        int n = rand() % 11;
        buffer[n]--;
        printf("Ciao sono il thread [2] e questo è il buffer: \n");
        printf("[");
        for (int i = 0; i < sizeof(buffer) / sizeof(int); i++) {
            printf(" %d ", buffer[i]);
        }
        printf("]\n");
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

void *f_th3(void *args) {
    while (1) {
        pthread_mutex_lock(&mutex);
        int count_pone = 0, count_none = 0, init_zero = 0;
        printf("Ciao sono il thread [3] e questo è il buffer: \n");
        printf("[");
        for (int i = 0; i < sizeof(buffer) / sizeof(int); i++) {
            printf(" %d ", buffer[i]);
            if (buffer[i] == 1) {
                count_pone++;
            }
            if (buffer[i] == -1) {
                count_none++;
            }
            if (buffer[i] != 0) {
                init_zero++;
            }
        }
        printf("]\n");
        if (count_pone > count_none && init_zero == 11) {
            printf("\nFINITO\n");
            done = 1;
            pthread_mutex_unlock(&mutex);
            pthread_exit(0);
        }

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
    pthread_exit(0);
}

int main(int argc, char *argv[]) {
    srand(time(NULL));

    pthread_create(&th1, NULL, f_th1, NULL);
    pthread_create(&th2, NULL, f_th2, NULL);
    pthread_create(&th3, NULL, f_th3, NULL);

    pthread_mutex_init(&mutex, NULL);

    pthread_join(th1, NULL);
    pthread_join(th2, NULL);
    pthread_join(th3, NULL);

    pthread_mutex_destroy(&mutex);

    return EXIT_SUCCESS;
}
