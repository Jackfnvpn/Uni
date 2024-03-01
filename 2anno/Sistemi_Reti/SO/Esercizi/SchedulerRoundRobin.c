#include <stdio.h>
#include <stdlib.h>
#include <sys/wait.h>
#include <threads.h>
#include <time.h>
#include <unistd.h>
struct Process {
    int pid;
    int executing_time;
};

typedef struct Process Process;

void tempo(int id, int time);

int main(int argc, char *argv[]) {
    Process p1 = {0, 10};
    Process p2 = {0, 15};
    Process p3 = {0, 20};

    if ((p1.pid = fork()) == -1) {
        perror("ERRORE nella fork");
    }

    if (p1.pid == 0) {
        int start_time = time(NULL);
        int current_time = start_time;

        while (current_time - start_time < p1.executing_time) {
            tempo(1, current_time - start_time);
            sleep(5); // Simulate time passing
            current_time = time(NULL);
        }
        exit(0);
    }

    if ((p2.pid = fork()) == -1) {
        perror("ERRORE nella fork");
    }

    if (p2.pid == 0) {
        int start_time = time(NULL);
        int current_time = start_time;

        while (current_time - start_time < p2.executing_time) {
            tempo(2, current_time - start_time);
            sleep(5); // Simulate time passing
            current_time = time(NULL);
        }
        exit(0);
    }

    if ((p3.pid = fork()) == -1) {
        perror("ERRORE nella fork");
    }

    if (p3.pid == 0) {
        int start_time = time(NULL);
        int current_time = start_time;

        while (current_time - start_time < p3.executing_time) {
            tempo(3, current_time - start_time);
            sleep(5); // Simulate time passing
            current_time = time(NULL);
        }
        exit(0);
    }

    waitpid(p1.pid, NULL, 0);
    waitpid(p2.pid, NULL, 0);
    waitpid(p3.pid, NULL, 0);

    return EXIT_SUCCESS;
}

void tempo(int id, int time) {
    printf("Ciao sono il processo, %d e il mio tempo attuale è %d\n", id, time);
}
