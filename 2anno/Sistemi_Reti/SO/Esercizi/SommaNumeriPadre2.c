#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>
#include <time.h>
#include <sys/wait.h>

#define STDIN 0
#define STDOUT 1
#define PIPE_RD 0
#define PIPE_WR 1

int main() {
    srand(time(NULL));  
    int fd[2];
    pid_t p1, p2;
    int p = 0, d = 0, somma = 0;

    if (pipe(fd) == -1) {
        perror("Pipe creation failed");
        exit(-1);
    }

    if ((p1 = fork()) == -1) {
        perror("FORK failed");
        exit(-1);
    }

    if (p1 == 0) {
        close(STDOUT);
        close(fd[PIPE_RD]);
        dup(fd[PIPE_WR]);
        while (1) {
            int n = rand() % 101;
            if (n % 2 == 0) {
                write(fd[PIPE_WR], &n, sizeof(int));
            }
        }
    }

    if ((p2 = fork()) == -1) {
        perror("FORK FAILED");
        exit(-1);
    }

    if (p2 == 0) {
        close(STDOUT);
        close(fd[PIPE_RD]);
        dup(fd[PIPE_WR]);
        while (1) {
            int n = rand() % 101;
            if (n % 2 != 0) {
                write(fd[PIPE_WR], &n, sizeof(int));
            }
        }
    }

    close(fd[PIPE_WR]);

    while (1) {
        close(STDIN);
        dup(fd[PIPE_RD]);
        ssize_t buffer = read(fd[PIPE_RD], &p, sizeof(int));

        if (buffer == -1) {
            perror("ERROR READ");
            exit(-1);
        }

        buffer = read(fd[PIPE_RD], &d, sizeof(int));

        if (buffer == -1) {
            perror("ERROR READ");
            exit(-1);
        }

        somma = p + d;
        printf("la somma e' %d + %d = %d\n", p, d, somma);

        if (somma > 190) {
            kill(p1, SIGTERM);
            kill(p2, SIGTERM);
            break;
        }
    }

    waitpid(p1, NULL, 0);
    waitpid(p2, NULL, 0);
    return 0;
}

