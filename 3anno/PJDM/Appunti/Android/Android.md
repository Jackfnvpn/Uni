# Android Studio
Android usa l'Android RunTime (ART)
Questa esegue bytecode compilato, si compilano i .class al momento dell'installazione dell'app.

## Componenti App
+ **Activity:** Interfaccia utente
    + Rappresenta una schermata dell'applicazione
    + Quando avviamo una nuova attività, la corrente è posta in background. Infatti si usa uno stack di activity.
    + La vita di una activity è controllata mediante delle chiamate
+ **Servizi:** Operazioni in background
  + Eseguono operazioni in background, non hanno GUI.
  + Servono per operazioni come suonare la musica, di rete o leggere la posizione.
+ **Content Provider:** Condivisione dati tra app
  + Sono usati per memorizzare e leggere dati rendendoli accessibili alle applicazioni
  + Espongono una URI pubblica per individuare univocamente i propri dati
  + I dati sono esposti in forma di una singola tabella di database
+ **Broadcast Receiver:** 
  + Sono componenti che rispondono ad annunci inviati a tutti i componenti di sistema: il telefono si spegne, batteria scarica,chiamata in arrivo
  + Anche le app possono inviare annunci in broadcast

### Manifest File
Presenta l'applicazione al SO, descrive i componenti dell'applicazione, dichiara i permessi necessari e le API necessarie.

## XML
XML è un meta linguaggio basato sui marcatori (tag).
E' pensato per strutturare, organizzare, trasportare informazioni e non visualizzarle.
Tutto ciò che è racchiuso tra due tag, compresi questi ultimi, sono detti **elementi**.
XML è case sensitive. Ci deve essere un solo elemento root.

I nomi dei tag hanno delle regole:
+ Possono contenere numeri e lettere
+ Non possono iniziare con un numero
+ Non possono iniziare con un nome xml
+ Non possono avere spazi
+ Non ci sono parole riservate

Gli attributi forniscono informazioni che non sono parte dei dati e vanno fra apici.
Un documento è valido se rispetta le regole formali e si dice corretto se rispetta le regole di un DTD o di un XML schema.

I namespace servono ad evitare conflitti di nomi.

## Risorse

Le risorse sono contenute nella cartella **res**.
Per fornire risorse alternative

## Activity
Una activity è realizzata con una classe java che esetende android.add.Activity. Rappresenta una singola schermata grafica della nostra app. Si attiva in primo piano.

Ogni activity ha il compito di memorizzare e ripristinare lo stato delle sue variabili d'istanza.

### Intent
Un messaggio che consente di comunicare tra componenti dell'app o app diverse.
Contiene informazioni su cosa fare e con quali dati.
Usato principalmente per avviare activity , service , un broadcast.
Abbiamo intent espliciti , specificano esattamente che componente avviare , usati per lanciare activity o service nell'app.
E impliciti, descrivono un azione da compiere.

Esempio intent implicito:

```java
Intent intent = new Intent(Intent.ACTION_SEND);
intent.setType("plain/text");
intent.putExtra(Intent.EXTRA_TEXT,"Testo della mia nota");
startActivity(Intent.createChooser(intent,"Condividi nota"));
```
Chiede al sistema di mostrare le app in grado di condividere testo (es Gmail, Whatsapp).


### Costanti della classe Intent

Queste costanti rappresentano **azioni** che puoi associare a un `Intent`, per dire al sistema Android cosa vuoi fare.

+  `ACTION_VIEW`

     + Mostra un dato all’utente. Può essere una pagina web, un contatto, un numero di telefono, ecc.
     + Esempi:

       + `content://contacts/people/1`: mostra il contatto con ID 1
       + `tel:123`: mostra il numero `123` nell'app Telefono

* `ACTION_DIAL`

  * Apre il dialer (app Telefono) **con un numero precompilato**, ma senza effettuare la chiamata.
  * Esempi:

    * `content://contacts/people/1`: apre il dialer con il numero del contatto
    * `tel:123`: apre il dialer con il numero 123

* `ACTION_EDIT`

  * Permette di modificare un contenuto, ad esempio un contatto.
  * Esempio:

    * `content://contacts/people/1`: modifica il contatto con ID 1

---

### 💡 **Esempio pratico**

```java
Intent intent = new Intent();
intent.setAction(Intent.ACTION_DIAL);
startActivity(intent);
```

🔍 **Spiegazione:**

1. Crea un nuovo oggetto `Intent`.
2. Imposta l’azione su `ACTION_DIAL`, cioè aprire il dialer.
3. Avvia l’`Intent` con `startActivity(intent);`.

> Poiché non è stato fornito un numero (`tel:...`), il dialer si aprirà ma senza numero precompilato.


#### Esempio con numero

```java
Intent intent = new Intent(Intent.ACTION_DIAL);
intent.setData(Uri.parse("tel:123"));
startActivity(intent);
```

Questo apre il dialer con il numero **123** già inserito, pronto per essere chiamato.

La forma del dato è una URI.

### Intent customizzato 

**Receiver (chi riceve l’intent)**

Perché un componente Android (come un Activity, Service o BroadcastReceiver) possa ricevere un Intent personalizzato, bisogna dichiarare un filtro nell'AndroidManifest.xml:

```xml
<intent-filter>
    <action android:name="cap04.senderproject.intent.action.CUSTOM_ACTION"/>
    <category android:name="android.intent.category.DEFAULT"/>
</intent-filter>
```

+ ```action```: nome univoco dell’azione che il tuo componente sa gestire.

+ ```category.DEFAULT```: obbligatoria se usi startActivity() o startService() senza categoria esplicita.

**Sender (chi invia l’intent)**

Il sender crea un oggetto Intent e imposta l’azione personalizzata:

```java
Intent intent = new Intent();
intent.setAction("cap04.senderproject.intent.action.CUSTOM_ACTION");
startActivity(intent);
```

### Categorie
Una categoria fornisce informazioni aggiuntive su dove usare un intent. La più comune è:

```CATEGORY_DEFAULT```: serve per startActivity.

```java
intent.addCategory(Intent.CATEGORY_DEFAULT);
```

### Data
+ MIME type: definisce il tipo di dato:
  + text/plain, image/jpeg, text/html
+ URI:
  + ```<scheme>://<host>:<port>[<path>|<pathPrefix>|<pathPattern>]```

Metodi di Intent
+ setDataAndType (Uri data, String type)
+ setType(String)
+ setData(Uri)
+ setDataAndTypeAndNormalize(Uri, String)

### EXTRA

Gli extra sono coppie chiave/valore che puoi inserire in un Intent per passare dati da un componente a un altro (es. da un'Activity a un'altra).

| Costante             | Significato                    |
| -------------------- | ------------------------------ |
| `EXTRA_TEXT`         | Testo da inviare o condividere |
| `EXTRA_EMAIL`        | Indirizzo email                |
| `EXTRA_SUBJECT`      | Oggetto (es. email)            |
| `EXTRA_STREAM`       | File (come immagine, PDF...)   |
| `EXTRA_PHONE_NUMBER` | Numero di telefono             |
| `EXTRA_TITLE`        | Titolo per UI o notifiche      |

### FLAGS
Servono a comunicare al SO come deve gestire il lancio del componente.

**Impostarli**:
```java
intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
```

| Flag               | Effetto                                   |
| ------------------ | ----------------------------------------- |
| `FLAG_ACTIVITY_NEW_TASK`         | Avvia in un nuovo task                    |
| `FLAG_ACTIVITY_CLEAR_TOP`        | Rimuove le activity sopra quella target   |
| `FLAG_ACTIVITY_SINGLE_TOP`       | Riutilizza l’activity se è in cima        |
| `FLAG_ACTIVITY_REORDER_TO_FRONT` | Riporta un’activity esistente in cima     |
| `FLAG_ACTIVITY_NO_HISTORY`       | L’activity non viene salvata nello stack  |
| `FLAG_ACTIVITY_FORWARD_RESULT`   | Passa il risultato al chiamante originale |

```java
Intent i = new Intent(this, MainActivity.class);
i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
startActivity(i);
```

## Intent filter
Serve a dichiarare nel manifest cosa la tua activity può gestire (soprattutto per intent impliciti).
Dichiarano quali intent impliciti un componente può
ricevere.

Un intent filter può essere composto da
+ action : nome action
+ category : nome category
+ data : MIME Type e/o schema URI

```xml
<intent-filter>
  <action android:name="android.intent.action.VIEW"/>
  <category android:name="android.intent.category.DEFAULT"/>
  <data android:mimeType="text/plain"/>
</intent-filter>
```

Quando invii un intent implicito, Android cerca i componenti che:

+ Hanno la stessa action

+ Contengono tutte le category

+ Supportano MIME type e URI richiesti

### Package Manager
La classe che permette gestire le app si chiama Package Manager.

Puoi usare il PackageManager per scoprire quali app possono gestire un certo Intent.

```xml
<intent-filter>
<action android:name="android.intent.action.MAIN" />
<category android:name="android.intent.category.LAUNCHER" />
<category android:name="android.intent.category.HOME” />
<category
android:name="android.intent.category.DEFAULT"></category>
</intent-filter>
```
```java
Intent intent = new Intent(Intent.ACTION_MAIN);
intent.addCategory(Intent.CATEGORY_LAUNCHER);
PackageManager packageManager = getPackageManager();
List<ResolveInfo> infoList =
packageManager.queryIntentActivities(intent, 0);
```

### Task
Un task in Android è una pila (stack) di activity che l'utente sta eseguendo per completare una certa operazione.

+ Ogni volta che avvii un'Activity, questa viene spinta (push) nello stack del task corrente.
+ Quando premi "back", l’ultima activity viene tolta (pop).

Nel manifest:
Per dire che un’activity deve appartenere a un task diverso:

```xml
<activity
  android:name=".AltroActivity"
  android:taskAffinity="com.example.MioAltroTask" />
```

```taskAffinity``` è un nome che identifica il task. Se inizia con : crea un task solo per la tua app.

Con un FLAG nell’Intent:
Per dire di avviare una nuova activity in un nuovo task:

```java
Intent i = new Intent(this, AltroActivity.class);
i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
startActivity(i);
```

## UI

### View e ViewGroup: l’albero grafico
Tutto ciò che vedi a schermo è una View (es. Button, TextView).
Le View sono organizzate in ViewGroup (contenitori invisibili, es. LinearLayout, ConstraintLayout).
Insieme formano un albero (root → nodi → foglie), visibile anche con il Layout Inspector.

Le ViewGroup estendono View e possono contenere altre View.

I vantaggi:

+ Puoi trattare tutto come una collezione.

+ Chiamare un metodo sul contenitore lo propaga a tutte le sue View (es. draw()).

### Layout 
Un layout è una specializzazione di ViewGroup che organizza le view figlie con una regola di posizionamento.

Layout comuni:
+ LinearLayout – disposizione verticale/orizzontale.

+ RelativeLayout – posizionamento rispetto ad altre view.

+ FrameLayout – sovrapposizione.

+ TableLayout – simile a una tabella.

+ ConstraintLayout – posizionamento flessibile e ottimizzato.

I file xml genera oggetti java.
L’operazione di conversione si chiama inflating, da un layout xml genera un albero di view e viewgroup.

Avviene con:
```java
setContentView(R.layout.mio_layout);
```
Puoi accedere a ogni view con:
```java
findViewById(R.id.nomeView);
```
### Attraversamento dell'albero

Android attraversa l’albero delle view per eseguire operazioni (es. disegno).

Ordine:

+ Dal root in giù.
+ Le view più vicine alla radice vengono processate prima.
+ Tra pari livello, vale l’ordine di definizione nel layout.

### Processo di Draw (disegno)
3 fasi fondamentali, tutte nel main thread:

+ measure() – calcola le dimensioni di ogni view.
+ layout() – calcola la posizione.
+ draw() → chiama onDraw() – disegna le view a schermo.

Eseguo tutto in un singolo thread per non aver
problemi di contesa.

La misura avviene con measure(int, int), chiamato dal parent:

+ Viene eseguito in metodo onMeasure
+ Può essere chiamato più volte

Specifica:

+ UNSPECIFIED: libero
+ EXACTLY: misura precisa
+ AT_MOST: fino a un massimo
  
Usi makeMeasureSpec – metodo per fornire il tipo di misura.

Anche i layout devono calcolare la propria misura in base a
quella delle view:

Usi setMeasuredDimension() per definire la misura della view.

### Posizionamento delle view
Si attraversa l'albero una seconda volta per
determinare la posizione delle view nota la loro
dimensione

Il parent chiama layout(int, int, int, int)
– Viene eseguito in metodo onLayout
– coordinate del vertici top-left e bottom-right

Le informazioni circa il layout sono memorizzate in
istanze della classe ViewGroup.LayoutParams
– Ogni layout definisce la sua con i parametri specifici

### Disegno delle view 
Viene chiamato su tutto l'albero il metodo draw(), questo chiama il metodo onDraw


## Fragment
Un Fragment è una porzione modulare di UI che può essere riutilizzata in diverse Activity.

Caratteristiche:

+ Può essere aggiunto/rimosso dinamicamente durante l’esecuzione.

+ Gestisce i propri eventi e ha un ciclo di vita dipendente dalla Activity a cui è associato.

+ Non può esistere da solo, è sempre legato a un'Activity.

Ogni Activity ha un proprio FragmentManager, accessibile tramite 
+ getFragmentManager() per API < 28.
+ getSupportFragmentManager() con AppCompatActivity e AndroidX.

Tiene traccia di tutti i fragment associati all’Activity.

