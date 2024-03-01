#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define PIPE_RD 0
#define PIPE_WR 1
#define STDOUT 1
#define STDIN 0

int main(int argc, char *argv[]) {
    int f_pipe[2];
    int p1;
    srand(time(NULL));

    if (pipe(f_pipe) == -1) {
        perror("ERRORE nella creazione della pipe");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("ERRORE nella fork");
        exit(-1);
    }

    if (p1 != 0) {

        close(STDOUT);
        close(f_pipe[PIPE_RD]);
        dup(f_pipe[PIPE_WR]);
        int n = rand() % 9;
        int count_wr = write(f_pipe[PIPE_WR], &n, sizeof(int));
        // printf("%d", n);
        if (count_wr == -1) {
            perror("ERRORE nella write");
            exit(-1);
        }

        close(f_pipe[PIPE_WR]);
    }

    if (p1 == 0) {
        close(STDIN);
        close(f_pipe[PIPE_WR]);
        dup(f_pipe[PIPE_RD]);
        int random_number;
        int count_read = read(f_pipe[PIPE_RD], &random_number, sizeof(int));
        if (count_read == -1) {
            perror("ERRORE nella read");
            exit(-1);
        }
        close(f_pipe[PIPE_RD]);

        float operation_number = sqrt(random_number);
        printf("%f\n", operation_number);
    }

    waitpid(p1, NULL, 0);

    return EXIT_SUCCESS;
}
