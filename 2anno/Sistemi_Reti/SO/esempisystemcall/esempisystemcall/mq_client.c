/*
 * client.c
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

int main (int argc, char *argv[])
{
	char client_queue_name[16];
	mqd_t qd_server, qd_client;   // descrittori code
	// crea la coda client per ricevere i messaggi dal server
	sprintf (client_queue_name, "mq_%d", getpid ());		
  struct mq_attr attr;
	attr.mq_flags = 0;
  attr.mq_maxmsg = MAX_MESSAGES;
  attr.mq_msgsize = sizeof(msg_send);
  attr.mq_curmsgs = 0;
	
	if ((qd_client = mq_open (client_queue_name, O_CREAT|O_RDONLY,QUEUE_PERMISSIONS,&attr)) == -1) 	
	{
		perror ("Client: mq_open (client)");
    exit (1);
  }
	if ((qd_server = mq_open (SERVER_QUEUE_NAME, O_WRONLY)) == -1) {
		perror ("Client: mq_open (server)");
    exit (1);
  }
	
	printf ("Richiedi un numero (Premi <INVIO>): ");
	char in_buf [10];
	msg_send.pid=getpid();
	if (argc > 1)
		strcpy(msg_send.text,argv[1]);
	else {
		strcpy(msg_send.text,"Pietro");
	}
	while (fgets (in_buf, 2, stdin)) {
		// invia messaggio al server
		if (mq_send (qd_server, (const char *)&msg_send, sizeof (msg_send), 0) == -1) {
			perror ("Client: impossibile inviare messaggio al server");
      continue;
		}
		// riceve risposta dalserver
		if (mq_receive (qd_client, (char *)&msg_rcv, sizeof (msg_rcv), NULL) == -1) {
			perror ("Client: mq_receive");
      exit (1);
			
    }
    // visualizza messaggio ricevuto dal server
    printf ("Client: messaggio dal server: %s\n\n", msg_rcv.text);
		printf ("Richiedi un numero di serie (Premi <INVIO>): ");
	}
	
	if (mq_close (qd_client) == -1) {
		perror ("Client: mq_close");
    exit (1);
  }
	
	if (mq_unlink (client_queue_name) == -1) {
		perror ("Client: mq_unlink");
    exit (1);
  }
  printf ("Client: ciao\n");
	exit (0);
}