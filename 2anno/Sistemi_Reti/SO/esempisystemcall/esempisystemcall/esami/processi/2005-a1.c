/**
	Sistemi operativi e reti (SOR)
	Appello 1 - A.A. 2005/2006.
	@author Pietro Frasca 
	@version 1.00 16-01-2016

	Realizzare un programma in C, completo di commento, che svolga  quanto segue: un processo padre P genera due processi figli P1 e P2. I due figli P1 e P2 leggono ogni secondo rispettivamente record dai file F1 e F2. I due file hanno record di dimensione fissa pari a 20 byte per F1 e 40 byte per F2. I record letti dai due processi figli vengono inviati al processo padre che provvede a stamparli sullo schermo e a memorizzarli sul file F3. La stampa su schermo di ciascun record è preceduta dal valore del PID del processo proprietario del record. Infine il padre deve disabilitare il segnale di interruzione SIGINT, in particolare all’arrivo di tale segnale il padre deve visualizzare il messaggio di avviso “interruzione non consentita”.  
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
#include <fcntl.h>
#include <string.h>
#define LEGGI 0
#define SCRIVI 1
int fd1[2],fd2[2];
const int SIZE1=20;
const int SIZE2=40;
void handler_int(int signum){
  printf("interruzione non consentita \n");
}

void figlio1(){
	char buf[SIZE1]; 
  int fd,n;   
	fd=open ("2005-a1-1.txt",O_RDONLY);
  close(fd1[LEGGI]);
  while ((n=read(fd,buf,SIZE1))>0){
		write(fd1[SCRIVI],buf,SIZE1);
  }
  close(fd);
  exit(0);
}

void figlio2(){
	char buf[SIZE2]; 
  int fd,n;   
	fd=open ("2005-a1-2.txt",O_RDONLY);
  close(fd2[LEGGI]);
  while ((n=read(fd,buf,SIZE2))>0){
		write(fd2[SCRIVI],buf,SIZE2);
  }
  close(fd); 
}

int main(){
  int pid1,pid2,i,stato,n1,n2;
  if (pipe(fd1)<0){
    printf("Errore pipe");
    exit(1);
  }

  if (pipe(fd2)<0){
    printf("Errore pipe");
    exit(1);
  }
  
  pid1 = fork();
  if (pid1<0){
    printf("errore fork");
    exit(1);
  } else if (pid1==0){
    // figlio1
    figlio1();
  } else {
    // padre
    pid2 = fork();
    if (pid2<0){
      printf("errore fork");
      exit(1);
    } else if (pid2==0){
      // figlio2
      figlio2();
    } else {
      // padre 
      signal(SIGINT,handler_int);
			int f3;
			char buf[SIZE2]; 
      f3=open("2005-a1-3.txt",O_CREAT|O_WRONLY);     
      close(fd1[SCRIVI]);
      close(fd2[SCRIVI]);
      do {
        n1=read(fd1[LEGGI],buf,SIZE1);
        if (n1>0) { 
          printf ("figlio1 %d: %s \n",pid1,buf);
          write(f3,buf,SIZE1);
        }          
        n2=read(fd2[LEGGI],buf,SIZE2);
        if (n2>0) {
          printf ("figlio2 %d: %s \n",pid2,buf);
          write(f3,buf,SIZE2);
        }           
        
        if (n1<=0 && n2<=0) break;
        sleep(1);
      } while(1);
      close(f3);
    }
  }
}
