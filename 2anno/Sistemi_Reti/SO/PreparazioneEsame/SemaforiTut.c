
// esercizio in cui viene creato un database rappresentato da un array di interi
// più threads tentato di accedere all'array oer leggere il suo contenuto

#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <unistd.h>

sem_t semaforo;
int database[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

void leggiDatabase(int threadNum) {

    sem_wait(&semaforo);
    printf("[Thread %d] - Entrato, leggo il db...\n", threadNum);
    sleep(2);
    for (int i = 0; i < 10; i++) {
        printf("[Thread %d] - numero letto %d\n", threadNum, database[i]);
    }
    printf("[Thread %d] - Finito, rilascio\n", threadNum);
    sem_post(&semaforo);
}

int main() {

    sem_init(&semaforo, 0, 1);

    pthread_t t1, t2, t3;
    pthread_create(&t1, NULL, leggiDatabase, 1);
    pthread_create(&t2, NULL, leggiDatabase, 2);
    pthread_create(&t3, NULL, leggiDatabase, 3);

    pthread_join(t1, NULL);
    pthread_join(t2, NULL);
    pthread_join(t3, NULL);

    return 0;
}
