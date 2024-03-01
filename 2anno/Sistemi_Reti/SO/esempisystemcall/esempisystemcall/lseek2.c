/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016
	
	Esempio lseek.
	Il processo scrive in un file la frase "La nebbia agli isti colli piovigginando sale...", che contiene un errore di ortografia (isti invece di irti). Successivamente posiziona, mediante lseek il puntatore alla posizione 15 (a partire dall'inizio del file) per effettuare l'operazione di correzione.
*/
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <unistd.h>

void main(){
  int fd,ret;
  char frase[]="La nebbia agli isti colli piovigginando sale...";
	char parola[]="IRTI";
  fd = creat("prova.txt",0666);
  write(fd,frase,sizeof(frase)); // scrive il contenuto di buffer nel file
  printf("fd=%d\n", fd); // valore di fd
  lseek(fd,15,SEEK_SET); // posiziona il puntatore del file a partire dal carattere in posizione 15.
	printf("lunghezza frase = %d, parola = %d \n",sizeof(frase), sizeof(parola));
  write (fd,parola,sizeof(parola)-1);
  close(fd);
  
  fd = open("prova.txt",O_RDONLY);
  while(ret = read(fd,frase,sizeof(frase))){
  printf("ret = %d, letto: %s \n",ret,frase);
  }
  close(fd);
}
