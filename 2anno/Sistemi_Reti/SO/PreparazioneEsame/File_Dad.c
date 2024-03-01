/*
 * Si richiede di implementare un programma in linguaggio C che utilizzi il metodo delle fork e le pipe per la comunicazione tra processi.
 * Il programma dovrà creare un file di testo e poi creare due processi figli. Uno dei processi figli dovrà scrivere una sequenza di numeri interi pari
 * nel file, mentre l'altro processo figlio dovrà scrivere una sequenza di numeri interi dispari nello stesso file.
 * Il processo padre dovrà leggere i dati dal file e stamparli a video
 *
 */

#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    int p1, p2;
    srand(time(NULL));
    int fd[2];

    if (pipe(fd) == -1) {
        perror("ERRORE nella creazione della pipe");
    }

    int in_fd = open("./file_dad.txt", O_RDWR | O_TRUNC | O_CREAT);

    if (in_fd < 0) {
        perror("ERRORE nella creazione del file");
        exit(1);
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella creazione del primo processo");
        exit(0);
    }

    if (p1 == 0) {
        while (1) {
            int n = 1;

            while (n % 2 == 1) {
                n = rand() % 100;
            }

            int c_wr = write()
        }
    }

    return EXIT_SUCCESS;
}
