/** 
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.1 11-10-2018
	Esempio pipe con nome.
	Un processo produttore invia messaggi ad un processo consumatore
	mediante pipe con nomne.
	
	Per sperimentare questo esempio seguire i seguenti passi:
	- aprire una shell
	- avviare una prima istanza di questo programma senza alcun parametro
	- avviare una seconda istanza di questo programma con un parametro
	*/
#include <fcntl.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
struct message_t {
	int numero;
	char text[32];
} msg;

int main(int argc,char *argv[]) {
	int fd, i=0, n=10;
	int size=sizeof(msg);
	srand(time(NULL));
	mknod("mia_pipe",S_IFIFO|0666,0);

	if (argc == 2)
		fd = open("mia_pipe",O_WRONLY);
	else
		fd = open("mia_pipe", O_RDONLY);
	
	strcpy(msg.text,"Auguri");
	for (i=0;i<n;i++)
		if (argc == 2){
			msg.numero=rand()%100+1;
			printf("produttore: %s %d \n",msg.text,msg.numero);
			write(fd, &msg, size);
			sleep(1);
		} else {
			read(fd,&msg,size);
			printf("consumatore: %s %d\n",msg.text,msg.numero);
			sleep(1);
		}
}
