#include <stdio.h>
#include <stdlib.h>

int is_Empty(int *, int);
void push(int *, int, int *);
int pop(int *, int *);
int top(int *, int *);

int main(int argc, char *argv[]) {
    int p[10], index;
    int len;

    int size = sizeof(p) / sizeof(int);

    len = is_Empty(p, size);

    index = size - 1;

    push(p, 5, &index);

    push(p, 10, &index);

    int elem1 = pop(p, &index);
    int elem2 = top(p, &index);

    return EXIT_SUCCESS;
}

int is_Empty(int *p, int size) {
    if (p[0] == 0) {
        return 0;
    }

    else {
        return 1;
    }
}

void push(int *p, int e, int *i) {

    p[*i] = e;
    (*i)--;
}

int pop(int *p, int *i) {
    int elem = p[*i];
    (*i)++;
    return elem;
}

int top(int *p, int *i) {
    int elem = p[*i];
    return elem;
}
