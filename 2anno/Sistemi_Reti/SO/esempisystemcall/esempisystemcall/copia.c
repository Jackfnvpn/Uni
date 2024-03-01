/**
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0 11-01-2016

	Esempio d'uso di open, read e write. 
	Il programma esegue la copia di un file.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#define DIMBUF 1024
#define PERMESSI 0755

int main (int argc, char **argv){
  int stato,fin, fout,n;
  char buffer[DIMBUF];
  if (argc != 3){
    printf("Sintassi: copia fileDaCopiare fileCopia \n");
    exit(1);
  }
  if ((fin=open(argv[1],O_RDONLY))<0){
     printf ("errore lettura file");
     exit(1);
  }
  if ((fout=open(argv[2],O_CREAT|O_WRONLY,PERMESSI))<0){
     printf ("errore scrittura file");
     exit(1);
  }
  while ((n=read(fin,buffer,DIMBUF))>0)
    if (write(fout,buffer,n)<n){
      close(fin);
      close (fout);
      exit(1);
    }
  close(fin);
  close(fout);
  exit(0);
}
