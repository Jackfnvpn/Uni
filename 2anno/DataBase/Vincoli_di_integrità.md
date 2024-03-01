# Vincoli di integrità
Le strutture del modello relazionale ci permettono di organizzare le informazioni per le nostre applicazioni. In molti casi, però, non è vero che qualsiasi insieme di tuple sullo schema rapprresenti informazioni corrette per l'applicazione. Ad esempio:  
![Base di dati con informazioni errate](./Screen/informazioni_errate.png)  
+ Nella prima tupla della relazione *ESAMI*  abbiamo un voto pari a 36, che nel sistema italiano, non è ammissibile.
+ Nella seconda tupla ancora della relazione *ESAMI* viene attribuita la lode ad un esame il cui voto è 28.
+ Le ultime due tuple della relazione *STUDENTI* contengono informazioni su due studenti diversi con lo stesso numero di matricola.
+ La quarta tupla nella relazione *ESAMI* presenta , per l'attributo *Studente*, un valore che non compare fra i numeri di matricola nella relazione *STUDENTI*. Analogamente la prima tupla presenta un codice di corso che non compare nella relazione *CORSI*.  

Per evitare queste situazioni è stato introdotto il concetto di *vincolo d integrità*, come proprietà che deve essere soddisfatta dalle istanze che rappresentano informazioni corrette per l'applicazione. Ogni vincolo può essere visto come un *predicato* che associa a ogni istanza il valore *vero* o *falso*. Se il predicato assume il valore *vero*, allora diciamo che l'istanza *soddisfa* il vincolo. In generale, a uno schema di base di dati associamo un insieme di vincoli e consideriamo *corrette* le istanze che soddisfano tutti i vincoli.  
E' possibile classificare i vincoli a seconda degli elementi di una base di dati che ne sono coinvolti:  

- Un vincolo è *intrarelazionale* se il suo soddisfacimento è definito rispetto a singole relazioni della base di dati; talvolta il coinvolgimento riguarda le tuple (o i valori addirittura) separatamente le une dalle altre:
  - un *vincolo di tupla* è un vincolo che può essere valutato su ciasuna tupla indipendentemente dalle altre;
  - un vincolo definito sui singoli valori (caso voti > 30) viene detto *vincoli sui valori*.
- Un vincolo *interrelazionale* se coinvolge più relazioni.
## Vincoli di tupla
Una possibile sintassi per esprimere vincoli di questo tipo è quella che permette di definire espressioni booleane (AND, OR , NOT) con atomi che confrontano (operatori di uguaglianza, disuguaglianza, ordinamento) valori di attributo o espressioni aritmetiche su valori di attributo.  
![Vincoli tupla](./Screen/vincoli_tupla.png)  
## Chiavi 
![Una relazione per le chiavi come esempio](./Screen/chiavi.png)  
I valori delle varie tuple sono diversi l'uno dall'altro. Ad *identificare univocamente* gli studenti ci pensa il numero di matricola.  
Intuitivamente una chivae è un insieme di attributi utilizzato per identificare univocamente le tuple di una relazione.  
Formalmente:  
+ Un insieme $K$ di attributi è *superchiave* di una relazione *r* se *r* non contiene due tuple distinte $t_1$ e$t_2$ con $t_1[K]=t_2[K]$;
+ $K$ è *chiave* di *r* se è una superchiave minimale di *r* (cioè non esiste un'altra superchiave $K'$ di *r* che sia contenuta in $K$ come sottoinsieme proprio).

