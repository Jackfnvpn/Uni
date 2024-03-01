#include <stdio.h>
#include <stdlib.h>

#define N 100
int count=0;


void producer(void){
    int item;

    while (1) {
        item=produce_item();   //Genera elemento successivo al buffer
        if (count == N) sleep(); //Se il buffer è pieno, vai a dormire
        insert_item();            //Inserisce l'item nel buffer
        count++;
        if(count==1) wakeup(consumer) // Se il buffer era vuoto, e ora c'è un elemento sveglia il consumer     
    }
}


void consumer(void){
    int item;
    while (1) {
        if (count == 0) sleep(); // Se il buffer è vuoto, dormi!
        item_remove();  // Rimuove elemento nel buffer
        count--;
        if (count == N-1) wakeup(producer) // Se il buffer era pieno, è ora è stato tolto un elemento, sveglia il producer
        consume_item(item)
    }
}
