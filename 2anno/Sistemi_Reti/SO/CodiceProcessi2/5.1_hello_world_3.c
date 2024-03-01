#include <unistd.h>
#include <stdio.h>
#include <sys/syscall.h>
#define STDOUT 1

int main(int argc, char **argv)
{
	char msg[] = "Hello World!\n";
	int nr = SYS_write;
	printf("%d",nr);
	syscall(nr, STDOUT, msg, sizeof(msg)); // printf() -> write() -> syscall(SYS_write, descrittore del file canale, il messaggio, e la dimensione in byte del messaggio)
	return 0;
}
