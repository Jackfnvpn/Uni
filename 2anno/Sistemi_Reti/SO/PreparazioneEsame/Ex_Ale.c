#include <fcntl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    int p1, p2;
    srand(time(NULL));
    int fd = open("FileAlessandro.txt", O_RDWR | O_TRUNC | O_CREAT);

    if (fd < 0) {
        perror("ERRORE nella creazione del file");
        return 1;
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella creazione del primo processo");
        return 1;
    }

    if (p1 == 0) {

        int n;

        int fd = open("FileAlessandro.txt", O_WRONLY);

        if (fd < 0) {
            perror("ERRORE nell'apertura del file (p1)");
            return 1;
        }

        n = rand() % 9;

        while (n % 2 == 1) {
            n = rand() % 9;
        }

        int c_write = write(fd, &n, sizeof(int));

        if (c_write < 0) {
            perror("ERRORE nella scrittura del numero pari");
            return 1;
        }

        close(fd);
    }

    if (p1 != 0) {
        if ((p2 = fork()) == -1) {
            perror("ERRORE nella creazione del secondo processo");
            return 1;
        }

        if (p2 == 0) {
            int n;

            int fd = open("./FileAlessandro.txt", O_RDWR);

            if (fd < 0) {
                perror("ERRORE nell'apertura del file (p1)");
                return 1;
            }

            n = rand() % 9;

            while (n % 2 == 0) {
                n = rand() % 9;
            }

            lseek(fd, sizeof(int), SEEK_SET);

            int c_write = write(fd, &n, sizeof(int));

            if (c_write < 0) {
                perror("ERRORE nella scrittura del numero pari");
                return 1;
            }

            close(fd);
        }

        else {
            int buffer[100];
            waitpid(p1, NULL, 0);
            waitpid(p2, NULL, 0);

            int c_read = read(fd, buffer, sizeof(int) * 2);
            if (c_read < 0) {
                perror("ERRORE nella lettura del file");
                return 1;
            }

            for (int i = 0; i < 2; i++) {
                printf(" %d ", buffer[i]);
            }
        }
    }

    return EXIT_SUCCESS;
}
