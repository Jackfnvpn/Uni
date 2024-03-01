#include <fcntl.h>
#include <pthread.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <unistd.h>

// int buffer[10] = {0};
pthread_mutex_t mutex;
pthread_t th1, th2;

void *f_th1(void *args) {

    while (1) {

        pthread_mutex_lock(&mutex);
        char buffer1[20];
        int buffer2[10] = {0};
        int in_fd = open("./data.txt", O_RDWR);

        if (in_fd < 0) {
            perror("ERRORE nell'apertura del file thread 1");
            exit(-1);
        }
        int count_read = read(in_fd, buffer1, sizeof(buffer1));

        if (count_read < 0) {
            perror("ERRORE nella lettura del file thread 1");
            exit(-1);
        }
        buffer1[count_read] = '\0';

        int i = 0;
        int n, index = 0;

        // printf("\n%s", buffer1);

        while (buffer1[i] != '\0') {
            if (buffer1[i] != ' ') {
                // printf("[%c]", buffer1[i]);
                n = (buffer1[i] - '0');
                buffer2[index] = n;
                index++;
            }
            i++;
        }

        for (int i = 0; i < 10; i++) {
            buffer2[i]++;
        }

        printf("\n [%s] ", buffer1);

        int index2;
        for (int i = 0; i < strlen(buffer1); i++) {
            if (buffer1[i] != ' ') {
                // printf(" [%c] ", buffer1[i]);
                int number = buffer2[index2];
                buffer1[i] = number + '0';
                index2++;
            }
        }

        // printf("Ciao sono il thread1: %s", buffer1);
        printf("\n");

        lseek(in_fd, 0, SEEK_SET);

        int count_write = write(in_fd, buffer1, sizeof(buffer1));
        if (count_write < 0) {
            perror("ERRORE nella scrittura del file");
            exit(-1);
        }

        close(in_fd);

        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
}

void *f_th2(void *args) {
    while (1) {

        pthread_mutex_lock(&mutex);
        char buffer1[20];
        int buffer2[10] = {0};
        int in_fd = open("./data.txt", O_RDWR);
        lseek(in_fd, 0, SEEK_SET);

        if (in_fd < 0) {
            perror("ERRORE nell'apertura del file thread 2");
            exit(-1);
        }
        int count_read = read(in_fd, buffer1, sizeof(buffer1));

        if (count_read < 0) {
            perror("ERRORE nella lettura del file thread 2");
            exit(-1);
        }
        buffer1[count_read] = '\0';

        int i = 0;
        int n, index = 0;

        while (buffer1[i] != '\0') {
            if (buffer1[i] != ' ') {
                n = (buffer1[i] - '0');
                buffer2[index] = n;
                index++;
            }
            i++;
        }

        for (int i = 0; i < 10; i++) {
            buffer2[i]--;
        }

        int index2;
        for (int i = 0; i < strlen(buffer1); i++) {
            if (buffer1[i] != ' ') {
                int number = buffer2[index2];
                buffer1[i] = number + '0';
                index2++;
            }
        }

        printf("Ciao sono il thread2: %s", buffer1);
        printf("\n");

        lseek(in_fd, 0, SEEK_SET);

        int count_write = write(in_fd, buffer1, sizeof(buffer1));
        if (count_write < 0) {
            perror("ERRORE nella scrittura del file");
            exit(-1);
        }

        close(in_fd);
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }
}

int main(int argc, char *argv[]) {
    srand(time(NULL));
    int in_fd;
    char buffer[] = "0 0 0 0 0 0 0 0 0 0";
    char buffer2[20];
    int buffer3[10];

    in_fd = creat("./data.txt", 0700);

    if (in_fd < 0) {
        perror("ERRORE nella creazione del file");
        exit(-1);
    }

    int count_write = write(in_fd, buffer, sizeof(buffer));

    if (count_write < 0) {
        perror("ERRORE nella scrittura del file");
        exit(-1);
    }

    pthread_create(&th1, NULL, f_th1, NULL);
    pthread_create(&th2, NULL, f_th2, NULL);

    pthread_mutex_init(&mutex, NULL);

    while (1) {
        // int count_pos=0, count_neg=0;
        pthread_mutex_lock(&mutex);
        printf("GO: ");
        in_fd = open("./data.txt", O_RDONLY);
        if (in_fd < 0) {
            perror("ERRORE nella apertura del file Programma");
            exit(-1);
        }
        int count_read = read(in_fd, buffer2, sizeof(buffer2));
        if (count_read < 0) {
            perror("ERRORE nella lettura Programma");
            exit(-1);
        }
        buffer2[count_read] = '\0';
        int i = 0, index = 0, n;
        // printf("%s\n", buffer2);

        while (buffer2[i] != '\0') {
            if (buffer2[i] == ' ') {
            }
            i++;
        }

        int count_pos = 0, count_neg = 0;

        for (int i = 0; i < 10; i++) {
            if (buffer3[i] > 0) {
                count_pos++;
            }
            if (buffer3[i] < 0) {
                count_neg++;
            }
        }
        if (count_pos > count_neg) {
            printf("Il numero di positivi è maggiore di quelli negativi\n");
            // pthread_kill(th1, SIGKILL);
            // pthread_kill(th2, SIGKILL);
            // exit(0);
        }
        pthread_mutex_unlock(&mutex);
        sleep(rand() % 4);
    }

    pthread_join(th1, NULL);
    pthread_join(th2, NULL);

    return EXIT_SUCCESS;
}
