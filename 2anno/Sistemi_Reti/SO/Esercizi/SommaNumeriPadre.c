#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0
#define PIPE_WR 1

int main() {
    srand(time(NULL));
    int fdp1[2];
    int fdp2[2];
    int p = 0, d = 0;
    pid_t p1, p2;
    ssize_t buffer;
    int somma = 0;

    if (pipe(fdp1) == -1 || pipe(fdp2) == -1) {
        perror("Pipe creation failed");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("FORK failed");
        exit(-1);
    }

    if (p1 == 0) {
        close(STDOUT);
        close(fdp1[PIPE_RD]);
        dup(fdp1[PIPE_WR]);
        while (1) {
            int n = rand() % 101;
            if (n % 2 == 0) {
                write(fdp1[PIPE_WR], &n, sizeof(int));
            }
            // close(fdp1[PIPE_WR]);
        }
    }

    if ((p2 = fork()) == -1) {
        perror("FORK FAILED");
        exit(-1);
    }
    if (p2 == 0) {
        close(STDOUT);
        close(fdp2[PIPE_RD]);
        dup(fdp2[PIPE_WR]);
        while (1) {
            int n = rand() % 101;
            if (n % 2 != 0) {
                write(fdp2[PIPE_WR], &n, sizeof(int));
            }
            // close(fdp2[PIPE_WR]);
        }
    }
    close(fdp1[PIPE_WR]);
    close(fdp2[PIPE_WR]);

    while (1) {
        close(STDIN);
        dup(fdp1[PIPE_RD]);
        buffer = read(fdp1[PIPE_RD], &p, sizeof(int));
        if (buffer == -1) {
            perror("ERROR READ");
            exit(-1);
        }

        dup(fdp2[PIPE_RD]);
        buffer = read(fdp2[PIPE_RD], &d, sizeof(int));

        if (buffer == -1) {
            perror("ERROR READ");
            exit(-1);
        }

        somma = p + d;
        printf("la somma e' %d + %d = %d\n", p, d, somma);

        if (somma > 190) {
            kill(p1, SIGTERM);
            kill(p2, SIGTERM);
            return 0;
        }
        // close(fdp1[PIPE_RD]);
        // close(fdp2[PIPE_RD]);
    }
    waitpid(p1, NULL, 0);
    waitpid(p2, NULL, 0);
}
