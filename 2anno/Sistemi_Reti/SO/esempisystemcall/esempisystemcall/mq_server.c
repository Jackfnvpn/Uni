/*
 * server.c: Server 
 * Esempio IPC con le code di messaggi POSIX          
 * Sistemi operativi e reti
 * Pietro Frasca, 30/10/2017
 */

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <fcntl.h>
#include <mqueue.h>
//#include <sys/types.h>
//#include <sys/stat.h>

#define SERVER_QUEUE_NAME "/mq_server"
#define QUEUE_PERMISSIONS 0660
#define MAX_MESSAGES 10

struct message {
	int pid;
	char text[64];
} msg_send, msg_rcv;

int main (int argc, char argv[])
{
	mqd_t qd_server, qd_client; // descrittori code
  long serial_number = 1; // numero seriale da inviare al client
	char client_queue_name[16]; // nome della coda del client
	struct mq_attr attr; // attributi della coda
	attr.mq_flags = 0;
  attr.mq_maxmsg = MAX_MESSAGES; // numero massimo di messaggi della coda
  attr.mq_msgsize = sizeof(msg_rcv);
  attr.mq_curmsgs = 0;
	
	printf ("Server: Benvenuto!\n");
  if ((qd_server = mq_open (SERVER_QUEUE_NAME, O_RDONLY | O_CREAT, QUEUE_PERMISSIONS,&attr)) == -1) {
		perror ("Server: mq_open ");
    exit (1);
  }
	//msg_send.pid=getpid();
  while (1) {
		// preleva dalla coda il messaggio più vecchio con piu' alta  priorita'
    if (mq_receive (qd_server, (char *)&msg_rcv, sizeof(msg_rcv), NULL) == -1) {
			perror ("Server: mq_receive");
      exit (1);
    }
		printf ("Server: messaggio ricevuto dal client %d, %s.\n",msg_rcv.pid,msg_rcv.text);
		sprintf (client_queue_name, "mq_%d", msg_rcv.pid);	
    // invia il messaggio di risposta al client
		if ((qd_client = mq_open (client_queue_name, O_WRONLY)) == 1) {
			perror ("Server: impossibile aprire la coda client");
			continue;
    }
		sprintf (msg_send.text, "Benvenuto client %d, il tuo numero e' %ld", msg_rcv.pid, serial_number);
		if (mq_send (qd_client, (const char *)&msg_send, sizeof(msg_send), 0) == -1){
			perror ("Server: impossibile inviare il messaggio al client");
      continue;
    }
		printf ("Server: risposta inviata al client.\n");
    serial_number++;
	}
}
