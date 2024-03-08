#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int elem;
    struct Node *next;
} Node;

typedef struct List {
    Node *pointer;
    int n;

} List;

int main(int argc, char *argv[]) {
    List L = {.pointer = NULL, .n = 0};

    return EXIT_SUCCESS;
}
