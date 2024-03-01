#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <unistd.h>

#define STDOUT 1
#define STDIN 0
#define PIPE_RD 0
#define PIPE_WR 1

int main(int argc, char *argv[]) {
    int f_pipe[2];
    int p1;

    if (pipe(f_pipe) == -1) {
        perror("ERRORE nella creazione della pipe");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        // fprintf(stderr, "ERRORE nella creazione del processo figlio");
        perror("ERRORE nella fork");
        exit(-1);
    }

    if (p1 != 0) {
        close(STDOUT);
        close(f_pipe[PIPE_RD]);
        dup(f_pipe[PIPE_WR]);
        char buffer[] = "Ciao, sono papa'\n";
        int write_count = write(f_pipe[PIPE_WR], buffer, sizeof(buffer));
        if (write_count == -1) {
            perror("ERRORE nella scrittura nella pipe");
            exit(-1);
        }
        close(f_pipe[PIPE_WR]);
    }

    else {

        close(STDIN);
        close(f_pipe[PIPE_WR]);
        dup(f_pipe[PIPE_RD]);
        char buffer[256];
        int buffer_count = read(f_pipe[PIPE_RD], buffer, sizeof(buffer));
        if (buffer_count == -1) {
            perror("ERRORE nella lettura nella pipe");
            exit(-1);
        }
        close(f_pipe[PIPE_RD]);
        printf("%s", buffer);
    }

    waitpid(p1, NULL, 0);
    return EXIT_SUCCESS;
}
