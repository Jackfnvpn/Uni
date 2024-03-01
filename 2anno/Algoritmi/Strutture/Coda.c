#include <stdio.h>
#include <stdlib.h>

#define N 10
void enqueue(int *p, int e, int *index);
int is_Empty(int *p, int *);
int dequeue(int *p, int *index);

int main(int argc, char *argv[]) {
    int p[N];
    int size = sizeof(p) / sizeof(int);
    int index = size;

    enqueue(p, 5, &index);
    enqueue(p, 5, &index);
    enqueue(p, 5, &index);
    enqueue(p, 5, &index);

    int elem = dequeue(p, &index);
    printf("[%d]", elem);

    for (int i = 0; i < size; i++) {
        printf(" %d ", p[i]);
    }

    return EXIT_SUCCESS;
}

int is_Empty(int *p, int *index) {
    if (*index == N) {
        return 0;
    } else {
        return 1;
    }
}

void enqueue(int *p, int e, int *index) {
    (*index)--;
    p[*index] = e;
}

int dequeue(int *p, int *index) {

    if (is_Empty(p, index) == 1) {
        int element = p[*index];
        p[*index] = 0;
        (*index)++;
        return element;
    } else {
        return -1;
    }
}
