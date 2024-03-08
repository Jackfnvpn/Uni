#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
    int elem;
    struct Node *next;
} Node;

typedef struct List {
    Node *head;
    int size;
} List;

int is_Empty(List *);
void push(List *, int);
int pop(List *);
int top(List *);

int main(int argc, char *argv[]) {
    List list = {.head = NULL, .size = 0};

    int len = is_Empty(&list);

    push(&list, 5);

    printf("%d", list.head->elem);

    return EXIT_SUCCESS;
}

int is_Empty(List *L) {
    if (L->size == 0) {
        return 0;
    } else {
        return 1;
    }
}

void push(List *L, int elem) {
    Node *p = malloc(sizeof(Node));

    p->elem = elem;
    p->next = L->head;

    L->head = p;
    L->size++;
}

int pop(List *L) {
    int elem = L->head->elem;

    Node *p = L->head->next;

    L->head = L->head->next;
    L->size--;

    free(p);

    return elem;
}

int top(List *L) {
    int elem = L->head->elem;
    return elem;
}
