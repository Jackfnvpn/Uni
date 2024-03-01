#include <unistd.h>
#include <stdio.h>

#define STDOUT 1
// #define STDERR 2
// #define STDIN 0
int main()
{
	char msg[] = "Hello World!\n";
	write(STDOUT, msg, sizeof(msg));  // write scrive sul canale di output(prendendo come parametro il file_descriptor) il messaggio, specificando anche la sua dimensione in byte
	
	return 0;
}
