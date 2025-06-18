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

### Polimorfismo per metodi

#### Overload
Firma del metodo
+ La coppia identficatore – lista di parametri
  + Es. “public int somma(int a, int b)”
+ Overload:
  + In una classe posso dichiarare metodi con lo stesso identificatore ma firma differente
+ Tipicamente:
  + Sono metodi con la stessa funzionalità

Succede nella stessa classe (ma può anche succedere tra superclasse e sottoclasse).


#### Override
In una classe si possono dichiarare metodi con la stessa firma della superclasse. Basta utilizzare la stessa firma, altrimenti si tratta di overload. Il tipo di ritorno del metodo deve coincidere con quello del metodo che si sta riscrivendo, il metodo ridefinito non deve essere meno accessibile del metodo che ridefinisce.

OVVIAMENTE VALE SOLO PER CLASSI DIVERSE QUINDI EREDITA'

```java
protected   metodo(x,y)
private     metodo(x,y) //NO

private     metodo(x,y) 
protected   metodo(x,y) // SI

```

### Polimorfismo per attributi

Possiamo assegnare ad una reference della superclasse una istanza della sottoclasse:
```java
Punto ogg = new PuntoTridimensionale();
```

Non è lecito:
```java
ogg.setZ(5); // ogg è di tipo oggetto , non ha quel metodo
```
Il tipo del riferimento (Punto) determina quali metodi puoi chiamare a compile-time, non l’oggetto reale.

Ma, quando chiami un metodo sovrascritto, verrà eseguita la versione della sottoclasse.

#### Parametri poliformici  

Significa che puoi passare riferimenti di una superclasse, ma in realtà l’oggetto passato può essere di una sottoclasse. Questo è polimorfismo: un metodo accetta un tipo generico (ad esempio Object), ma a runtime si comporta in base all’oggetto effettivo.

Il metodo println accetta parametri della classe Object:
```java
Punto p = new Punto();
System.out.println(p);
```
Il corpo del metodo println invoca il metodo toString(), se lo ho riscritto viene chiamato quello di Punto.

#### Collezioni eterogenee
Insiemi di oggetti diversi ()

```java
// Superclasse
public class Dipendente {
    public String nome;
    public int stipendio;
    public int matricola;
    public String dataDiNascita;
    public String dataDiAssunzione;
}

// Sottoclasse: Programmatore
class Programmatore extends Dipendente {
    public String linguaggiConosciuti;
    public int anniDiEsperienza;
}

// Sottoclasse: Dirigente
class Dirigente extends Dipendente {
    public String orarioDiLavoro;
}

// Sottoclasse: AgenteDiVendita
class AgenteDiVendita extends Dipendente {
    public String[] portafoglioClienti;
    public int provvigioni;
}

// Classe di test
public class Main {
    public static void main(String[] args) {
        Dipendente[] arr = new Dipendente[180];

        arr[0] = new Dirigente();
        arr[1] = new Programmatore();
        arr[2] = new AgenteDiVendita();

        // Esempio di accesso a campi comuni
        arr[0].nome = "Mario Rossi";
        arr[1].stipendio = 2500;

        // Cast per accedere a campi specifici della sottoclasse
        ((Programmatore) arr[1]).linguaggiConosciuti = "Java, Python";
        ((AgenteDiVendita) arr[2]).provvigioni = 500;

        // Stampa per verifica
        System.out.println("Programmatore usa: " + ((Programmatore) arr[1]).linguaggiConosciuti);
        System.out.println("Agente ha provvigioni: " + ((AgenteDiVendita) arr[2]).provvigioni);
    }
}
```

instanceof fornisce la classe di una istanza:
```java
// Superclasse
class Dipendente {
    public String nome;
    public int stipendio;
    public int matricola;
    public String dataDiNascita;
    public String dataDiAssunzione;
}

// Sottoclassi
class Programmatore extends Dipendente {
    public String linguaggiConosciuti;
    public int anniDiEsperienza;
}

class Dirigente extends Dipendente {
    public String orarioDiLavoro;
}

class AgenteDiVendita extends Dipendente {
    public String[] portafoglioClienti;
    public int provvigioni;
}

public class Main {

    // Metodo che assegna lo stipendio in base al tipo
    public static void pagaDipendente(Dipendente dip) {
        if (dip instanceof Programmatore) {
            dip.stipendio = 1200;
        } else if (dip instanceof Dirigente) {
            dip.stipendio = 3000;
        } else if (dip instanceof AgenteDiVendita) {
            dip.stipendio = 1000;
        } else {
            dip.stipendio = 800; // default
        }
    }

    public static void main(String[] args) {
        Dipendente[] arr = new Dipendente[3];

        arr[0] = new Dirigente();
        arr[1] = new Programmatore();
        arr[2] = new AgenteDiVendita();

        // Ciclo che paga tutti i dipendenti
        for (Dipendente dipendente : arr) {
            pagaDipendente(dipendente);
            System.out.println(dipendente.getClass().getSimpleName() + " ha stipendio: " + dipendente.stipendio);
        }
    }
}
```
È lecito questo costrutto??
```Java
if (dip instanceof Programmatore){
  Programmatore pro = (Programmatore) dip;
}
```
Bhe si, perchè c'è la condizione che mi assicura che il dipendente sia un programmatore altrimenti avrei un exception

```Java
Dipendente dip = new Programmatore();
dip.linguaggiConosciuti = "Java"; // ❌ ERRORE a compilazione: linguaggiConosciuti non esiste in Dipendente
```
#### Metodi virtuali
Situazione:
+ B è una sottoclasse di A
+ B ridefinisce (override) un metodo m di A
+ Creo un istanza di B
+ Invoco m con una reference di A

Penso di invocare un metodo di A ma in realtà invoco un metodo di B.

## Costruttori

### Costruttori ed ereditarietà
Quando crei un oggetto di una sottoclasse, Java:

1. chiama prima il costruttore della superclasse

2. poi esegue il costruttore della sottoclasse

```Java
public class Punto {
    private int x, y;
    public Punto() {
        System.out.println("Costruito punto bidimensionale");
    }
}

public class Punto3D extends Punto {
    private int z;
    public Punto3D() {
        System.out.println("Costruito punto tridimensionale");
    }
}
```

Nel main
```Java
new Punto3D(); //NB: non sono necessarie reference per istanziare oggetti
```

Output: 
Costruito punto bidimensionale
Costruito punto tridimensionale
### Proprietà
1. Hanno lo stesso nome della classe
2. Non hanno tipo di ritorno
3. Sono chiamati automaticamente (e solamente) ogni volta che viene istanziato un oggetto della classe cui appartengono, relativamente a quell’oggetto.
4. Sono presenti in ogni classe.
5. non sono ereditati dalle sottoclassi
6. un qualsiasi costruttore (anche quello di default), come prima istruzione, invoca sempre un costruttore della superclasse.

### Super 
Super è un riferimento implicito alla superclasse dell’oggetto corrente.

Lo usi per accedere a membri (metodi o costruttori) della superclasse.

È utile quando vuoi usare la versione della superclasse, anche se l’hai sovrascritta nella sottoclasse.

```Java
public class Persona {
    private String nome, cognome;

    public String toString() {
        return nome + " " + cognome;
    }

    // set/get per nome e cognome...
}


public class Cliente extends Persona {
    private String indirizzo, telefono;

    public String toString() {
        return super.toString() + "\n" +
               indirizzo + "\nTel:" + telefono;
    }

    // set/get per indirizzo e telefono...
}
```

La chiamata al costruttore della superclasse è operata con super().
Posso inserire esplicitamente la chiamata con super(parametri) se devo selezionare il costruttore da chiamare:
+ Deve essere la prima istruzione nel costruttore della sottoclasse
+ Se non esiste un costruttore di default sono obbligato a esplicitare la chiamata inserendo i parametri

## Classi innestata

È una classe definita dentro un'altra classe.

```Java
public class Outer {
    // membro della Outer
    private String messaggio = "Nella classe ";

    private void stampaMessaggio() {
        System.out.println(messaggio + "Esterna");
    }

    public class Inner {
        public void metodo() {
            System.out.println(messaggio + "Interna");
        }

        public void chiamaMetodo() {
            stampaMessaggio(); // chiama metodo privato della outer
        }
    }
}
```

+ L'inner class può accedere direttamente ai membri (anche private) della classe esterna (Outer)

+ Non serve fare nulla di speciale: è come se fosse “dentro” l’oggetto esterno

+ Sono utili soprattutto per GUI, iteratori, eventi, logica associata strettamente all’oggetto esterno
```java
public class Main {
    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner(); // attenzione a questa sintassi!
        inner.metodo();         // stampa: Nella classe Interna
        inner.chiamaMetodo();   // stampa: Nella classe Esterna
    }
}
```
## Classe anonima

Una classe anonima è una classe senza nome che:

+ estende una classe esistente oppure implementa un'interfaccia

+ viene dichiarata e istanziata nello stesso punto

+ può ridefinire metodi

+ non può avere un costruttore (perché non ha nome)

```java
ClasseEsistente ca = new ClasseEsistente() {
    public void metodo() {
        System.out.println(messaggio + "Interna");
    }
};
```
ClasseEsistente è una vera e propria classe invece.

## Modificatori di accesso
| Modificatore | Classe | Attributo | Metodo | Costruttore | Significato                                  |
| ------------ | ------ | --------- | ------ | ----------- | -------------------------------------------- |
| `public`     | ✅      | ✅         | ✅      | ✅           | Visibile da **tutto il progetto**            |
| `protected`  | ❌      | ✅         | ✅      | ✅           | Visibile da **sottoclassi e stesso package** |
| *(default)*  | ✅      | ✅         | ✅      | ✅           | Visibile **solo nel package**                |
| `private`    | ❌      | ✅         | ✅      | ✅           | Visibile **solo nella stessa classe**        |

## Modificatori di comportamento


| Modificatore   | Classe | Attributo | Metodo | Costruttore | Blocco di codice | Significato                                                                                  |
| -------------- | ------ | --------- | ------ | ----------- | ---------------- | -------------------------------------------------------------------------------------------- |
| `abstract`     | ✅      | ❌         | ✅      | ❌           | ❌                | Classe/metodo da completare nelle sottoclassi                                                |
| `final`        | ✅      | ✅         | ✅      | ❌           | ❌                | Non modificabile (classe: non estendibile, metodo: non sovrascrivibile, attributo: costante) |
| `native`       | ❌      | ❌         | ✅      | ❌           | ❌                | Metodo implementato in **linguaggio nativo** (es. C/C++)                                     |
| `static`       | ❌      | ✅         | ✅      | ❌           | ✅                | Membro di **classe**, non di istanza                                                         |
| `strictfp`     | ✅      | ❌         | ✅      | ❌           | ❌                | Fissa la precisione FP (float/double) a IEEE 754                                             |
| `synchronized` | ❌      | ❌         | ✅      | ❌           | ❌                | Metodo usato per **thread-safe access**                                                      |
| `transient`    | ❌      | ✅         | ❌      | ❌           | ❌                | Campo **escluso** dalla serializzazione                                                      |

### FINAL
Caratteristiche:
+ una variabile dichiarata final diviene una costante
+ un metodo dichiarato final non può essere riscritto in una
sottoclasse (non è possibile applicare l’override)
+ una classe dichiarata final non può essere estesa
+ Posso dichiarare final anche parametri e variabili
locali di metodi 

### STATIC

Un membro static appartiene alla classe e non alle singole istanze.

È condiviso da tutti gli oggetti della classe. In pratica, è "globale a livello di classe".

+ Un attributo static è unico per tutta la classe

+ Un metodo static può essere chiamato senza creare un oggetto

+ Non può accedere direttamente ai campi non-statici (di istanza)

📌 Accesso:
Per usare un membro statico:
```java
NomeClasse.nomeStatico
```
Un metodo static non può accedere a variabili d’istanza direttamente, cioè:

```java
public class Esempio {
    int x = 5;

    public static void stampa() {
        // System.out.println(x); // ❌ ERRORE: x non è statico
    }
}
```
Per accedere a x, serve un oggetto:
```java
Esempio e = new Esempio();
System.out.println(e.x); // ✅ ok
```

```java
public class Contatore {
    static int totale = 0; // attributo statico

    public Contatore() {
        totale++;
    }

    public static void mostraTotale() {
        System.out.println("Totale istanze: " + totale);
        // System.out.println(this); // ❌ ERRORE: this non è permesso
    }
}
```

Un blocco static { ... } è un blocco di codice che viene eseguito una sola volta, quando la classe viene caricata in memoria (non quando crei un oggetto!).

```java
public class EsempioStatico {
    private static int a = 10;

    public EsempioStatico() {
        a += 10;
    }

    static {
        System.out.println("valore statico = " + a);
    }
}
```
```java
EsempioStatico ogg = new EsempioStatico();
```

La classe EsempioStatico viene caricata → esegue subito il blocco static, Il valore di a è 10 → stampa:

valore statico = 10

Poi viene creato un oggetto EsempioStatico → esegue il costruttore, che fa a += 10

Ma non c’è una stampa dopo, quindi l’output resta:

valore statico = 10

### ABSTRACT

#### Classi astratte
Si dichiarano con abstract
+ Non possono essere istanziate direttamente
+ Servono come base comune per altre classi

Possono contenere:

+ Metodi astratti (senza corpo)

+ Metodi concreti (con corpo)

+ Variabili e costruttori


#### Metodi astratti
Non hanno corpo, solo la firma:
```java
public abstract void dipingiQuadro();
```

+ Possono esistere solo dentro classi astratte
+ Obbligano le sottoclassi concrete a implementarli

## Interfacce

## Eccezioni, errori ed asserzioni
Le eccezioni sono delle situazioni impreviste che il flusso
di una applicazione può incontrare
+ Gestite mediante try, catch, throws, throw, finally
+ Concetto implementato mediante Exception

Gli errori sono delle situazioni impreviste che non
dipendono dal programmatore.
+ Non sono gestibili
+ Implementate mediante Error

Le asserzioni sono condizioni che devono essere verificate
perché una parte di codice sia corretta

+ Gestite dalla parola chiave assert

#### Gestire piu eccezioni
La prima che corrisponde viene eseguita, conta l’ordine in cui le scriviamo

### Finally
Viene eseguito sempre, serve per garantire il funzionamento di un blocco di codice

### Override

Quando si riscrive (override) un metodo che dichiara eccezioni, bisogna rispettare alcune regole:

Cosa è permesso:
1. Non aggiungere nuove eccezioni controllate (checked exceptions)
   + Se la ClasseBase non le ha, non puoi aggiungerle.

2. Devi includere le eccezioni dichiarate nella superclasse

   + Se la ClasseBase ha un throws, il metodo override deve rispettarlo.

3. Puoi specificare sottoclassi dell’eccezione dichiarata

   + Esempio: FileNotFoundException è una sottoclasse di IOException.


Cosa non è permesso:
+ Non puoi lanciare nuove eccezioni checked non compatibili
  + Es.: SQLException non è una sottoclasse di IOException, quindi è errore.

```java
public class ClasseBase {
    public void metodo() throws java.io.IOException { }
}

class SottoClasseCorretta1 extends ClasseBase {
    public void metodo() throws java.io.IOException {}
}

class SottoClasseCorretta2 extends ClasseBase {
    public void metodo() throws java.io.FileNotFoundException {}
}

class SottoClasseCorretta3 extends ClasseBase {
    public void metodo() {}
}

class SottoClasseScorretta extends ClasseBase {
    public void metodo() throws java.sql.SQLException {}
}
```












