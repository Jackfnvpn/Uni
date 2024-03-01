/** 
	Sistemi operativi e reti (SOR)
	@author Pietro Frasca
	@version 1.0.2 2-11-2018

	Esempio di alarm e pause
	Ogni ns secondi questo processo riceve un segnale di allarme (SIGALRM) che gestisce con la function azione specificata in signal. Il periodo di allarme ns viene incrementato di 1 secondo dopo ogni chiamata della function azione. La function system avvia il programma specificato nell'argomento. Nell'esempio e' eseguito il comando date che visualizza data e ora correnti.
*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>
int NMAX=10; // valore massimo dell'intervallo di allarme
int ns=1; // periodo iniziale di allarme (1 secondo)
void azione(){
  /* questa funzione e' eseguita ogni volta
     che il processo riceve il segnale SIGALRM.
  */
  printf("Segnale di allarme ricevuto...eseguo date \n");
  system("date"); // avvia il comando date
  
  /* riassegnamento del periodo di allarme che cancella il precedente periodo assegnato. ns e' incrementato di 1 ad ogni chiamata della funzione azione.
  */
  alarm(ns++);  
}

int main() {
  signal(SIGALRM,azione); /* gestione del segnale SIGALRM. Quando il processo riceverà il segnale SIGALRM sara' eseguita la function azione. */
  alarm(ns);  /* programmazione del segnale SIGALRM: il segnale sarà ricevuto 
							dal processo dopo ns secondi */
  while(ns <= NMAX) {
    printf("processo sospeso\n");
    pause(); /* il processo e' sospeso e sara' riattivato quando ricevera' il segnale SIGALRM. Quando il processo riceve il segnale, per prima e' eseguita la function azione associata al segnale e poi l'istruzione successiva a pause */
    printf("esecuzione function azione terminata \n");
	}
}
