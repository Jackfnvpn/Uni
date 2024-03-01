#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    char Path[] = "./File/file.txt";

    int pipe1[2], pipe2[2];

    struct stat file;

    int p1, p2;

    if ((pipe(pipe1)) == -1 || (pipe(pipe2)) == -1) {
        perror("ERRORE nella creazione della pipe");
        exit(-1);
    }

    if ((stat(Path, &file)) == -1) {
        perror("ERRORE nell'acquisizione delle informazioni");
        exit(-1);
    }

    if (!(file.st_mode & S_IRUSR)) {
        perror("ERRORE permessi di lettura non validi");
        exit(-1);
    }

    int in_fd = open(Path, O_RDONLY);

    if (in_fd < 0) {
        perror("ERRORE nell'apertura del file");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella fork");
        exit(-1);
    }

    long long_file = file.st_size;

    if (p1 == 0) {
        char *buffer = malloc(long_file / 2);

        int count_read = read(in_fd, buffer, long_file / 2);

        if (count_read < 0) {
            perror("ERRORE nella lettura del file ");
            exit(-1);
        }

        int count = 0;

        char *ptr = buffer;
        while ((ptr = strstr(ptr, "ti prego")) != NULL) {
            count++;
            ptr += strlen("ti prego");
        }

        close(pipe1[0]);

        int count_write = write(pipe1[1], &count, sizeof(int));
        if (count_write < 0) {
            perror("ERRORE nella scrittura della pipe");
            exit(-1);
        }

        close(pipe1[1]);

    }

    else if (p1 != 0) {
        if ((p2 = fork()) == -1) {
            perror("ERRORE nella fork");
            exit(-2);
        }

        if (p2 == 0) {
            char *buffer = malloc(long_file / 2);

            lseek(in_fd, long_file / 2, SEEK_SET);

            int count_read2 = read(in_fd, buffer, long_file / 2);

            if (count_read2 < 0) {
                perror("ERRORE nella lettura del file");
                exit(-2);
            }

            int count = 0;

            char *ptr = buffer;
            while ((ptr = strstr(ptr, "ti prego")) != NULL) {
                count++;
                ptr += strlen("ti prego");
            }
            // printf("%d", count);

            close(pipe2[0]);

            int count_write = write(pipe2[1], &count, sizeof(int));

            if (count_write < 0) {
                perror("ERRORE nella scrittura della pipe");
                exit(-2);
            }

            close(pipe2[1]);
        } else if (p2 != 0) {
            close(pipe1[1]);
            close(pipe2[1]);

            int n1, n2;

            waitpid(p1, NULL, 0);
            waitpid(p2, NULL, 0);

            int count_read1 = read(pipe1[0], &n1, sizeof(int));
            if (count_read1 < 0) {
                perror("ERRORE nella lettura della pipe");
                exit(-1);
            }
            close(pipe1[0]);
            int count_read2 = read(pipe2[0], &n2, sizeof(int));

            if (count_read2 < 0) {
                perror("ERRORE nella lettura della pipe");
                exit(-2);
            }
            close(pipe2[0]);

            int sum = n1 + n2;
            printf("%d\n", sum);
        }
    }

    return EXIT_SUCCESS;
}
