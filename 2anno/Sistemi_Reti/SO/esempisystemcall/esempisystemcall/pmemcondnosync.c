/* POSIX Shared Memory */
#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <sys/stat.h>        /* costanti mode  */
#include <fcntl.h>           /* costanti O_*  */
#include <unistd.h>
#include <sys/types.h>
#include <string.h>
#include <semaphore.h>
int N=5;
char nome[][20]={"Pietro","Antonio","Laura","Luisa","Lino"};

struct buffer_t {
	int id;
	char text[64];
} *buffer;


void produttore(){
	int i;
	for (i=0;i<N;i++) {
		usleep(100);
		buffer->id=i;	
		strcpy(buffer->text,nome[i]);
		printf ("msg scritto: %d %s\n",buffer->id, buffer->text);
		
	}
}

void consumatore(){
	int i;
	for (i=0;i<N;i++) {
		usleep(100);
		printf ("msg letto: %d %s\n",buffer->id, buffer->text);
		
		
	}
}
int main(){
	pid_t pid;
	int shm_id, SIZE;
	shm_id=shm_open("/memcond",  O_CREAT|O_RDWR,0666);
	SIZE=sizeof(struct buffer_t);
	ftruncate(shm_id,SIZE);
	
	buffer=mmap(0,SIZE,PROT_READ|PROT_WRITE,MAP_SHARED,shm_id,0);
	pid=fork();
	if (pid==0) {
		consumatore();
	
	} else {
		
		produttore();
		wait(NULL);
		shm_unlink("/memcond");
	}
}
