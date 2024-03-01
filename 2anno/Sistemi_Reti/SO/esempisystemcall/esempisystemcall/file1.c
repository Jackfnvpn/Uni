/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio di chiamate di sistema dei file.
	Questo programma esegue le seguenti operazioni: 
	- crea il file di nome file1.txt con diritti di accesso 744 (rwx|r--|r--);
	- legge da tastiera una stringa, mediante fgets e la scrive sul file. L'operazione di input dati termina digitando una riga vuota; 
	- il buffer buf viene azzerato;
	- il file viene chiuso; 
	- il file è riaperto in lettura e il testo  viene visualizzato sullo schermo.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

int main(int argc,char *argv[]){
	char fileName[]="./file1.txt";
  char buf[256];
  int ret,i;
  int fd=open(fileName,O_CREAT|O_WRONLY,0744);
  
  while (strcmp(buf,"\n")){
		printf("Input: ");
		fgets (buf,sizeof(buf),stdin);// legge dati da tastiera e li pone in buf
    write(fd,buf,sizeof(buf)); // scrive buf sul file  
    
  }
  close(fd); // chiude il file
  for (i=0;i<sizeof(buf);i++) buf[i]=0; // azzera contenuto di buf
  
	fd=open(fileName,O_RDONLY);
  printf ("lettura del file..\n");
  while(ret=read(fd,buf,sizeof(buf))){
    printf("%s",buf);
  }
}