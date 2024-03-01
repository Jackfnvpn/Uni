#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/stat.h>
#include <sys/wait.h>
#include <unistd.h>
#include <wait.h>

sem_t semaphore;

int main(int argc, char *argv[]) {
    int pid;

    int lines = 0;

    struct stat File;

    sem_init(&semaphore, 0, 1);

    if (argc != 4) {
        perror("[ERRORE]: Troppi pochi elementi da riga di comando");
        exit(-1);
    }

    if ((stat(argv[2], &File)) == -1) {
        perror("[ERRORE]: Acquisizione informazioni file");
        exit(-1);
    }

    int in_fd = open(argv[2], O_RDONLY);

    if (in_fd < 0) {
        perror("[ERRORE]: Apertura File");
        exit(-1);
    }

    int out_fd = open(argv[3], O_RDWR);

    if (out_fd < 0) {
        perror("[ERRORE]: Apertura File");
        exit(-2);
    }

    long int len_file = File.st_size;

    int section = len_file / atoi(argv[1]);

    char *buffer = malloc(section);

    for (int i = 0; i < atoi(argv[1]); i++) {
        if ((pid = fork()) == -1) {
            perror("[ERRORE]: Creazione processo");
            exit(-1);
        }
        if (pid == 0) {

            sem_wait(&semaphore);
            lseek(in_fd, i * section, SEEK_SET);
            int read_count = read(in_fd, buffer, section);

            if (read_count < 0) {
                perror("[ERRORE]: Lettura file");
                exit(-1);
            }

            int count_write = dprintf(out_fd, "Processo [%d]: %s", i, buffer);

            if (count_write < 0) {
                perror("[ERRORE]: Scrittura File");
                exit(-1);
            }

            lines++;
            sem_post(&semaphore);

            if (lines == atoi(argv[1])) {
                exit(0);
            }
        }
    }

    for (int i = 0; i < atoi(argv[1]); i++) {
        wait(NULL);
    }

    sem_destroy(&semaphore);

    return EXIT_SUCCESS;
}
