#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include<sys/wait.h>
#include <string.h>
#define SIZETEXT 40
const int LEGGI=0;
const int SCRIVI=1;

int fd[2];
typedef struct  {
	int id;
	char testo[SIZETEXT];
}	Buffer;

int main(){
int pid, i, x, stato;
Buffer *buffer;
buffer=(Buffer *)malloc(sizeof(Buffer));

if (pipe(fd)<0){
	printf("Errore pipe");
	exit(1);
}
 
pid = fork();
if (pid<0){
	printf("errore fork");
	exit(1);
} else if (pid==0){
	// figlio
	close(fd[LEGGI]); // chiude l'estremo del canale che non usa
	do {
		buffer->id=rand()%100;
		fgets(buffer->testo,SIZETEXT,stdin); // legge da tastiera
		write(fd[SCRIVI],buffer,sizeof(Buffer)); // scrive sulla pipe
	} while (strcmp(buffer->testo,".\n")); 
	exit(0);
} else {
	// padre
	close(fd[SCRIVI]); //chiude l'estremo del canale che non usa
	do {
		read(fd[LEGGI],buffer,sizeof(Buffer)); // legge dalla pipe
		printf ("padre: id=%d %s",buffer->id,buffer->testo); // visualizza il messaggio letto
	} while (strcmp(buffer->testo,".\n"));
	wait(&stato); // attende la terminazione del figlio
}
}



  
