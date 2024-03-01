#include <fcntl.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>
#define PIPE_RD 0
#define PIPE_WR 1

typedef struct array {
    float *data;
    int len;
    int sum;
} array;

float buffer[20] = {5.5, 6.6, 7, 8, 9, 6, 2, 3, 5, 6.6, 8.8, 7.7, 6.6, 5.5, 3.3, 2.2, 98, 99, 20.3, 10};
pthread_mutex_t mutex;

void *somma(void *args) {
    array *pari = (array *)args;
    pthread_mutex_lock(&mutex);

    for (int i = 0; i < pari->len; i++) {
        pari->sum = pari->sum + pari->data[i];
    }

    pthread_mutex_unlock(&mutex);
    pthread_exit(NULL);
}

int main(int argc, char *argv[]) {
    int fd_p1[2];
    int fd_p2[2];
    int p1, p2;

    pthread_mutex_init(&mutex, NULL);

    if ((pipe(fd_p1) == -1) || (pipe(fd_p2) == -1)) {
        perror("ERRORE nella creazione della pipe");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella creazione del processo 1");
        exit(1);
    }

    if (p1 == 0) {

        int count = 0;
        int index = 0;
        for (int i = 0; i < 20; i++) {
            if ((int)buffer[i] % 2 == 0) {
                count++;
            }
        }
        float *buffer_p1 = malloc(count * sizeof(float));

        for (int i = 0; i < 20; i++) {
            if ((int)buffer[i] % 2 == 0) {
                buffer_p1[index] = buffer[i];
                index++;
            }
        }
        array pari = {
            .data = buffer_p1,
            .len = count,
            .sum = 0};
        pthread_t thread1;
        pthread_create(&thread1, NULL, somma, (void *)&pari);
        pthread_join(thread1, NULL);

        close(fd_p1[PIPE_RD]);

        int fd_1 = write(fd_p1[PIPE_WR], &(pari.sum), sizeof(int));

        if (fd_1 <= 0) {
            perror("ERRORE nella scrittura della pipe 1");
            exit(-1);
        }

        close(fd_p1[PIPE_WR]);

    } else if (p1 != 0) {
        if ((p2 = fork()) == -1) {
            perror("ERRORE nella fork");
            exit(-1);
        }

        if (p2 == 0) {
            int count = 0;
            int index = 0;
            for (int i = 0; i < 20; i++) {
                if ((int)buffer[i] % 2 == 0) {
                    count++;
                }
            }
            float *buffer_p1 = malloc(count * sizeof(float));

            for (int i = 0; i < 20; i++) {
                if ((int)buffer[i] % 2 == 1) {
                    buffer_p1[index] = buffer[i];
                    index++;
                }
            }
            array dispari = {
                .data = buffer_p1,
                .len = count,
                .sum = 0};
            pthread_t thread1;
            pthread_create(&thread1, NULL, somma, (void *)&dispari);
            pthread_join(thread1, NULL);

            // printf("%d\n", dispari.sum);
            close(fd_p2[PIPE_RD]);

            int fd_2 = write(fd_p2[PIPE_WR], &(dispari.sum), sizeof(int));

            if (fd_2 <= 0) {
                perror("ERRORE nella scrittura della pipe 2");
                exit(-1);
            }

            close(fd_p2[PIPE_WR]);
        } else if (p2 != 0) {

            printf("ciao\n");
            int n_pari, n_dispari;
            waitpid(p1, NULL, 0);
            int fd1 = read(fd_p1[PIPE_RD], &n_pari, sizeof(int));
            if (fd1 <= 0) {
                perror("ERRORE nella lettura della pipe");
                exit(-1);
            }
            waitpid(p2, NULL, 0);
            int fd2 = read(fd_p2[PIPE_RD], &n_dispari, sizeof(int));

            if (fd2 <= 0) {
                perror("ERRORE nella lettura della pipe");
                exit(-1);
            }

            printf("%d\n", n_pari + n_dispari);
        }
    }

    return EXIT_SUCCESS;
}
