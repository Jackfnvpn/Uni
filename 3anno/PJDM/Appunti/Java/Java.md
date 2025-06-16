# Java
Un linguaggio robusto infatti non permette costrutti "pericolsi" come ereditarietà multipla e ha gestione della memoria.
E'orientato a oggetti, verifica gli errori in runtime e li gestisce esplicitamente.

E' portabile, astrae l'accesso alle risorse del SO (in C devi sapere come funzione il filesystem sottostante), astrae i tipi di dato (in C devi conoscere quanti byte occupa quel tipo) ed è ricco di librerie.

Permette debug dinamico , significa che puoi eseguire e analizzare un programma mentre gira, osservando:
+ i valori delle variabili,
+ l’ordine di esecuzione delle istruzioni,
+ eventuali eccezioni lanciate,
+ lo stato dello stack dei metodi.

Tutto questo grazie al fatto che il bytecode contiene informazioni di debug, come:

+ nomi di variabili,
+ numeri di riga,
+ nomi dei metodi e classi.

Il linking è il processo di collegamento tra diverse classi, metodi e librerie.
In Java il linking è dinamico, cioè avviene durante l’esecuzione (a runtime) e non tutto al momento della compilazione.
In Java: le classi vengono caricate man mano che servono, anche da file .class o .jar esterni.

### JDK
+ **bin:** Contiene i file eseguibili
  + javac: compilatore
  + java: avvia la JVM ed esegue i programmi
  + jdb: esecuzione in modalità debug
  + javadoc: genera la documentazione
+ **demo:** Contiene esempi
+ **include/lib:** codice binario usato dalla JDK
+ **jre**: la JVM
+ **docs:** docuementazione delle librerie java

### JVM
E' un programma che crea l'ambiente esecutivo per i programmi, li interfaccia con la macchina.
Avviata con "java", esegue il bytecode generato da javac.

Il programma compilato "teoricamente" è portabile: non dipende dal SO, serve solo la JVM, la JVM non è portabile, ogni SO ne ha una.
Tutte le JVM dovrebbero avere le stesse caratteristiche ma non sempre è così.

### Processo di sviluppo
1. Scrittura di un file di testo salvato con estensione .java
2. javac esegue la compilazione in bytecode, che non è codice nativo ma un linguaggio macchina della JVM
3. java esegue la JVM che esegue il bytecode.

Lo stesso bytecode pò essere eseguito su più piattaforme , la JVM isola il programma dall hardware.

### Metodi statici
Un gruppo di istruzioni con un nome.
Indica la struttura di un programma, elimina la ridondanza riusando il codice.
Inserire delle istruzioni in un metodo statico se: sono strutturalmente correlati e sono ripetuti.
Non creare metodi per istruzioni singole o istruzioni non correlate o debolmente correlate (dividere in metodi più piccoli).

## PO
### Incapsulamento
Una classe contiene dati e metodi.
Ai dati si accede solo attraverso i metodi e i metodi devono prevenire dati non corretti (getter and setters).
Così garantisco robustezza perche controllo l'accesso ai dati.
Indipendenza e riusabilità , le altre classi conscono solo l'interfaccia , non devono conoscere i dettagli interni.
Manutenzione , infatti posso riscrivere il corpo dei metodi senza cambiare l'interfaccia.

#### Incapsulamento funzionale
Dichiaro dei metodi con il modificatore private, possono essere visti solo nei metodi della stessa classe.

Se dichiaro un membro privato questo non è accessibile da altre classi (Ne dagli oggetti delle altre classi)
Due oggetti della stessa classe possono accedere ai membri privati in “modo pubblico” (È comunque preferibile non farlo, usiamo i getters).

### Ereditarietà

Partiamo da una classe “generale” e la estendiamo una o più volte particolareggiando le sue caratteristiche.

NON è permesso in Java estendere due classi!!
+ Potete estendere una sola classe
  + Garantiamo la creazione di un albero
  + Questa scelta toglie della flessibilità al programmatore ma favorisce la robustezza

#### La classe Object
Nella libreria standard di java è definito il concetto di “oggetto generico”: java.lang.Object.
Ogni Classe java estende implicitamente la classe Object.

Quando utilizzare l’ereditarietà?
Devo chiedermi se l’oggetto della candidata sottoclasse è un oggetto della candidata superclasse.

### Ereditarietà ed Incapsulamento
Se incapsulo una classe le variabili della classe non sono “visibili” della sottoclasse:
+ Le variabili private non sono ereditate.
+ Se la superclasse espone i setter ed i getter:
  + La sotto classe eredita questi metodi (se pubblici)
  + Accedo ai membri privati (della superclasse) attraverso questi nella sottoclasse

Quindi, la sottoclasse possiede degli attributi a cui può accedere attraverso dei metodi (ereditati).

#### Modificatore protected
Un membro dichiarato protected è accessibile dalle classi del package , e dalle sottoclassi (viene ereditato dalle sottoclassi).

| Modificatore | Visibile da...                             |
| ------------ | ------------------------------------------ |
| `private`    | solo all'interno della stessa classe       |
| `protected`  | stessa classe, sottoclassi, stesso package |
| `public`     | ovunque                                    |

### Polimorfismo
Il polimorfismo permette di usare una superclasse come riferimento per oggetti di sottoclassi diverse.

### Overload
Firma del metodo
+ La coppia identficatore – lista di parametri
  + Es. “public int somma(int a, int b)”
+ Overload:
  + In una classe posso dichiarare metodi con lo stesso identificatore ma firma differente
+ Tipicamente:
  + Sono metodi con la stessa funzionalità