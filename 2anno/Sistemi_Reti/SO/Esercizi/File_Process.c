#include <dirent.h>
#include <fcntl.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

#define STDOUT 1
#define STDIN 0
#define PIPE_RD 0
#define PIPE_WR 1

int main(int argc, char *argv[]) {
    DIR *dirp;
    struct dirent *dirent;
    int p1, p2;
    struct stat file;
    int in_fd;
    int pipe1[2], pipe2[2];
    char filePath[1024];

    if (argc != 2) {
        perror("ERRORE nella scrittura del path");
        exit(-1);
    }

    dirp = opendir(argv[1]);

    if (dirp == NULL) {
        perror("ERRORE nell'apertura della directory");
        exit(-1);
    }

    struct dirent *last_dirent;

    while ((dirent = readdir(dirp)) != NULL) {
        if (strcmp(dirent->d_name, ".") == 0 ||
            strcmp(dirent->d_name, "..") == 0) {
            continue;
        }

        last_dirent = dirent;
    }

    // closedir(dirp);

    int len = snprintf(filePath, sizeof(filePath), "%s/%s", argv[1],
                       last_dirent->d_name);

    // printf("%s", filePath);
    if (len < 0 || len >= sizeof(filePath)) {
        perror("ERRORE nella composizione del percorso");
        exit(EXIT_FAILURE);
    }

    // printf("%s", filePath);
    // printf("Current working directory: %s\n", getcwd(NULL, 0));
    // printf("Constructed file path: %s\n", filePath);

    if (stat(filePath, &file) < 0) {
        perror("ERRORE nel recupero delle informazioni del file");
        exit(EXIT_FAILURE);
    }

    printf("%s", filePath);

    if ((pipe(pipe1) == -1) || (pipe(pipe2) == -1)) {
        perror("ERRORE nella creazione della pipe");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella fork");
        exit(-1);
    }

    // printf("ciao");

    if (p1 == 0) {
        if (file.st_mode & S_IRUSR) {
            in_fd = open(filePath, O_RDONLY);
            if (in_fd < 0) {
                perror("ERRORE nell'apertura del file");
                exit(-1);
            }

            int long_file = file.st_size;

            char *buffer_p1 = malloc(sizeof(char) * (long_file / 2));

            int count_read_p1 = read(in_fd, buffer_p1, long_file / 2);

            if (count_read_p1 < 0) {
                perror("ERRORE nella lettura del file");
                exit(-1);
            }
            close(in_fd);
            // close(STDOUT);
            close(pipe1[PIPE_RD]);

            // dup(pipe1[PIPE_WR]);

            int count_write_p1 =
                write(pipe1[PIPE_WR], buffer_p1, long_file / 2);

            if (count_write_p1 < 0) {
                perror("ERRORE nella scrittura della pipe");
                exit(-1);
            }

            close(pipe1[PIPE_WR]);
        }

        else {
            perror("Il file non è in lettura");
            exit(-1);
        }
    }

    if ((p2 = fork()) == -1) {
        perror("ERRORE nella fork");
        exit(-2);
    }

    if (p2 == 0) {
        if (file.st_mode & S_IRUSR) {
            in_fd = open(filePath, O_RDONLY);
            if (in_fd < 0) {
                perror("ERRORE nell'apertura del file");
                exit(-1);
            }

            int long_file = file.st_size;

            lseek(in_fd, long_file / 2, SEEK_SET);

            char *buffer_p2 = malloc(sizeof(char) * (long_file / 2));

            int count_buffer_p2 = read(in_fd, buffer_p2, long_file / 2);

            if (count_buffer_p2 < 0) {
                perror("ERRORE nella lettura del file");
                exit(-2);
            }

            close(in_fd);
            // close(STDOUT);
            close(pipe2[PIPE_RD]);
            // dup(pipe2[PIPE_WR]);

            int count_write_p2 =
                write(pipe2[PIPE_WR], buffer_p2, long_file / 2);
            if (count_write_p2 < 0) {
                perror("ERRORE nella scrittura della pipe");
                exit(-2);
            }

            close(pipe2[PIPE_WR]);
        } else {
            perror("Il file non è in lettura");
            exit(-2);
        }
    }
    if (p1 > 0 && p2 > 0) {
        // close(STDIN);
        close(pipe1[PIPE_WR]);
        close(pipe2[PIPE_WR]);

        // dup(pipe1[PIPE_RD]);
        // dup(pipe2[PIPE_RD]);

        char buffer1[1024];
        char buffer2[1024];

        int count_read_fp1 = read(pipe1[PIPE_RD], buffer1, sizeof(buffer1));
        if (count_read_fp1 < 0) {
            perror("ERRORE nella lettura della pipe");
            exit(-1);
        }

        waitpid(p1, NULL, 0);
        int count_read_fp2 = read(pipe2[PIPE_RD], buffer2, sizeof(buffer2));
        if (count_read_fp2 < 0) {
            perror("ERRORE nella lettura della pipe");
            exit(-2);
        }
        waitpid(p2, NULL, 0);

        printf("\n%s\n%s\n", buffer1, buffer2);

        close(pipe1[PIPE_RD]);
        close(pipe2[PIPE_RD]);

        return EXIT_SUCCESS;
    }
}
