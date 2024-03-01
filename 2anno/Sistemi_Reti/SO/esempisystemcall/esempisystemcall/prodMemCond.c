#include <stdio.h>
#include <stdlib.h>
#include <sys/mman.h>
#include <sys/wait.h>
#include <sys/stat.h>        /* costanti mode  */
#include <fcntl.h>           /* costanti O_*  */
#include <unistd.h>
#include <sys/types.h>
#include <string.h>

int main () {
  const int SIZE=4096; // dimensione del segmento condiv.
  const char *nome = "/MEMCOND"; // nome del segmento
  int shm_fd; // Descrittore del segmento condiviso
  void * shm_ptr; // Puntatore al segmento condiviso
  /* Accesso al segmento in lettura  */ 
  shm_fd = shm_open(nome, O_RDONLY, 0666); 
  /* memory map del segmento */ 
  shm_ptr = mmap (0, SIZE, PROT_READ, MAP_SHARED, shm_fd, 0); 
  /* Lettura dal segmento di memoria condivisa */ 
  printf ("%s", (char *) shm_ptr); 
  /* Rimozione del segmento di memoria condivisa */ 
  shm_unlink (nome); 
  return 0; 
} 
