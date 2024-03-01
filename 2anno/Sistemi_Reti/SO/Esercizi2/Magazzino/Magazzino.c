#include <fcntl.h>
#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>

typedef struct Product {
    char *name;
    int quantity;
    float price;
    struct Product *next;
} Product;

typedef struct {
    int n;
    Product *head;
} List;

int main(int argc, char *argv[]) {
    struct stat Magazzino;
    char path[] = "./Magazzino.txt";

    List List_ofProduct = {0, NULL};

    if ((stat(path, &Magazzino)) == -1) {
        perror("[ERRORE]: Acquisizione informazioni");
        exit(-1);
    }

    int in_fd = open(path, O_RDONLY);

    if (in_fd < 0) {
        perror("[ERRORE]: Apertura File");
        return 1;
    }

    char buffer[4096];

    int count_read = read(in_fd, buffer, Magazzino.st_size);

    if (count_read < 0) {
        perror("[ERRORE]: Lettura File");
        return 1;
    }

    buffer[count_read] = '\0';

    // printf("%s", buffer);

    char *line = strtok(buffer, "\n");

    while (line) {

        Product *prod = malloc(sizeof(Product));

        // prod->next = List_ofProduct.head;
        // List_ofProduct.head = prod;

        char *product = strtok(line, " ");

        prod->name = strdup(product);
        product = strtok(NULL, " ");

        if (sscanf(product, "%d", &prod->quantity) == -1) {
            perror("[ERRORE]: Conversione Quantity");
            return 1;
        };

        product = strtok(NULL, " ");

        if (sscanf(product, "%f", &prod->price) == -1) {
            perror("[ERRORE]: Conversione Price");
            return 1;
        }

        prod->next = List_ofProduct.head;
        List_ofProduct.head = prod;

        List_ofProduct.n++;

        line = strtok(NULL, "\n");
    }

    Product *p = List_ofProduct.head;

    while (p != NULL) {
        printf("%s, %f, %d", p->name, p->price, p->quantity);
        p = p->next;
    }

    printf("\n%d", List_ofProduct.n);

    return EXIT_SUCCESS;
}
