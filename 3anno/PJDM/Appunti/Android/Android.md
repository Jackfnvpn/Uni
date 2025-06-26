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


| Metodo      | Quando viene chiamato                                                                                                            | Cosa succede                                                  |
| ----------- | -------------------------------------------------------------------------------------------------------------------------------- | ------------------------------------------------------------- |
| `onPause()` | ❗ Quando l'**Activity perde il focus** ma **è ancora visibile** (es: apre un dialogo, altro activity in primo piano trasparente) | Puoi **fermare animazioni**, **salvare dati volatili**        |
| `onStop()`  | ❗ Quando l'**Activity non è più visibile** (coperta del tutto, o l'utente va in un'altra app)                                    | Qui puoi **rilasciare risorse pesanti** (audio, sensor, ecc.) |


+ onPause()
L’utente riceve una chiamata → compare il dialer sopra → l'Activity è ancora sotto ma non interagibile

Viene chiamato prima che Android decida se distruggere o no l’Activity

+ onStop()
L’utente apre una nuova activity a tutto schermo → la tua activity non è più visibile

Viene chiamato se l’activity va in background

### 1️⃣ **Created**

🔹 Metodo chiamato: `onCreate()`

* L’Activity viene **creata in memoria**
* Qui carichi layout, inizializzi variabili, ecc.

✅ Esempi:

```java
setContentView(R.layout.activity_main);
```

---

### 2️⃣ **Started (visible)**

🔹 Metodo chiamato: `onStart()`

* L’Activity è **visibile** ma **non ancora pronta all’interazione**
* UI è **in primo piano** ma l’utente **non ci può ancora interagire**

✅ Esempio: la tua app è visibile ma sta ancora caricando i dati

---

### 3️⃣ **Resumed (visible)**

🔹 Metodo chiamato: `onResume()`

* L’Activity è **completamente attiva**
* L’utente può **interagire** (toccare bottoni, scrivere, scrollare)

✅ È lo **stato principale** di un’Activity attiva

---

### 4️⃣ **Paused (partially visible)**

🔹 Metodo chiamato: `onPause()`

* L’Activity è **parzialmente visibile** (es: un dialog sopra, una chiamata in arrivo)
* **Perde il focus**, ma è ancora lì sotto

✅ Qui puoi:

* Pausare animazioni
* Salvare dati temporanei (tipo contenuto di un form)

---

### 5️⃣ **Stopped (hidden)**

🔹 Metodo chiamato: `onStop()`

* L’Activity è **completamente nascosta** (non è più sullo schermo)
* L’utente è passato ad un’altra app, o a un’altra Activity

✅ Qui puoi:

* Rilasciare risorse pesanti (video, fotocamera, GPS…)

---

### 6️⃣ **Destroyed**

🔹 Metodo chiamato: `onDestroy()`

* L’Activity è **rimossa dalla memoria**
* Non esiste più

✅ È chiamata:

* Se l’utente chiude l’app
* Se Android decide di ucciderla per liberare RAM

---

### 🔁 Stato speciale: **Restart**

🔹 Metodo chiamato: `onRestart()`

* Viene chiamato **quando torni in Activity dopo che era in `Stopped`**
* Subito dopo parte `onStart()`

✅ Esempio: Torni all'app dopo averla minimizzata
## 🔄 Transizioni principali:

| Da → A                  | Metodo chiamato             |
| ----------------------- | --------------------------- |
| `Created` → `Started`   | `onStart()`                 |
| `Started` → `Resumed`   | `onResume()`                |
| `Resumed` → `Paused`    | `onPause()`                 |
| `Paused` → `Stopped`    | `onStop()`                  |
| `Stopped` → `Restart`   | `onRestart()` → `onStart()` |
| Qualsiasi → `Destroyed` | `onDestroy()`               |

---

## 💡 Esempio pratico:

* Apri un’app:
  `onCreate()` → `onStart()` → `onResume()`
* Premi Home:
  `onPause()` → `onStop()`
* Torni all’app:
  `onRestart()` → `onStart()` → `onResume()`
* Chiudi l’app:
  `onPause()` → `onStop()` → `onDestroy()`

---



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

## Adapter
Problema:
spesso i dati non sono disponibili sin da subito dunque devono essere gestite view dinamicamente.
Viene usato il layout AdapterView che affida ad un oggetto adapter la creazione delle view per visualizzare i dati.

### Tipi principali di adapter

* **`ArrayAdapter<T>`** → per array/lista di oggetti
* **`CursorAdapter`** → per leggere da database (cursori SQLite)
* **`SimpleAdapter`** → per mappare chiavi di una `Map` su viste
* **`SpinnerAdapter`** → usato con `Spinner`

> Usati con `AdapterView` o `ListAdapter`
```java
public ArrayAdapter(Context context, int resource, List<T> objects)
```

| Parametro                 | Significato                                    | Dettagli                                                                                                                                |
| ------------------------- | ---------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------- |
| **C** → `Context`         | Il **contesto dell’app**                       | Serve per accedere a risorse, `LayoutInflater`, attività correnti, ecc. Di solito `this` (in Activity) o `getActivity()` (in Fragment). |
| **I** → `int resource`    | L’**ID del layout XML** da usare per ogni riga | Di solito un file tipo `R.layout.riga_elemento`, che contiene almeno una `TextView` con ID `android.R.id.text1` se usi layout standard. |
| **S** → `List<T> objects` | La **sorgente dati**                           | È la lista degli oggetti da mostrare, ad esempio `List<String>` o `List<MioOggetto>`.                                                   |


Come un AdapterView (tipo ListView) chiede a un Adapter di fornire le view e i dati?

#### Metodo fondamentale: `getView(...)`

```java
View getView(int position, View convertView, ViewGroup parent)
```

### Parametri:

* `position`: indice dell’elemento
* `convertView`: **view riutilizzabile** (se non è `null`, riusala!)
* `parent`: il `ViewGroup` che conterrà la view

È il **cuore di un `Adapter`**, chiamato da `ListView` (o simili) per **ottenere la vista di un item** in posizione `position`.

Obiettivi:
* Restituire la `View` corretta per quell’item (posizione `i`)
* Evitare di creare ogni volta nuove `View` (ottimizzazione!)

## 🗃️ Metodi di gestione dei dati (definiti in `Adapter`):

| Metodo               | Cosa fa                                |
| -------------------- | -------------------------------------- |
| `getItem(int pos)`   | Ritorna l’oggetto alla posizione `pos` |
| `getItemId(int pos)` | Ritorna l’ID univoco per l’item `pos`  |
| `getCount()`         | Numero totale di elementi              |
| `isEmpty()`          | Dice se l’adapter è vuoto              |


## 🔁 Metodo chiave del `BaseAdapter`:

```java
notifyDataSetChanged();
```

📌 Chiama questo metodo quando i dati cambiano, per dire al `ListView`/`GridView` di **aggiornarsi**.

---

## 🔧 Esempio di `getView()` in un custom `ArrayAdapter`:

```java
@Override
public View getView(int position, View convertView, ViewGroup parent) {
    if (convertView == null) {
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.my_item_layout, parent, false);
    }

    String item = getItem(position);
    TextView tv = convertView.findViewById(R.id.myTextView);
    tv.setText(item);

    return convertView;
}
```

Recycler view è una view group o layout che mostra una lista di elementi dinamici , usano gli adapter per collegare i dati e sono scrollabili.

#### Differenze con ListView

| Cosa                  | `ListView`                      | `RecyclerView` 💪                                  |
| --------------------- | ------------------------------- | -------------------------------------------------- |
| **Efficienza**        | Bassa per liste grandi          | 🔥 Altissima, usa `ViewHolder`                     |
| **Layout flessibile** | Solo lista verticale            | ✅ Puoi avere griglia, orizzontale, staggered, ecc. |
| **Animazioni**        | Manuali e limitate              | ✅ Supportate di default                            |
| **Gestione touch**    | Limitata (no swipe, drag, ecc.) | ✅ Con `ItemTouchHelper`                            |
| **Architettura**      | Antica (pre-Lollipop)           | Moderna, modulare                                  |
| **Riuso View**        | Usa `convertView`               | 🔥 Usa `ViewHolder` obbligatoriamente              |
| **Supporto Google**   | 🟡 Legacy                       | ✅ Raccomandato ufficialmente                       |

Quando scorri una lista molto lunga, ListView crea e distrugge le View (le righe) ogni volta. Questo è lento, pesante per la memoria, e laggoso.
RV ricicla (riutilizza) le View che escono dallo schermo invece di crearle di nuovo.
Scorri la lista in basso → righe in alto escono dallo schermo_
+ Quelle View non vengono eliminate, ma messe in un “pool”
+ Nuove righe riusano quelle stesse View, cambiandone solo i dati
+ Tipo: "non butto via la riga, la riscrivo con i dati nuovi"

Per farlo, usa:
ViewHolder → classe che mantiene i riferimenti agli elementi della riga (TextView, ImageView, ecc.)
Questo evita di rifare findViewById() ogni volta.

Ha bisogno di un LayoutManager → per sapere come disporre le righe.
Un LayoutManager è la parte della RecyclerView che decide come disporre le righe (o elementi) sullo schermo.

| Tipo                         | Descrizione                           | Esempio                   |
| ---------------------------- | ------------------------------------- | ------------------------- |
| `LinearLayoutManager`        | Lista **verticale** o **orizzontale** | Messaggi WhatsApp         |
| `GridLayoutManager`          | Griglia a colonne                     | Galleria foto             |
| `StaggeredGridLayoutManager` | Griglia con altezze variabili         | Pinterest, Instagram feed |



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
### Ciclo di vita
Una `Activity` e un `Fragment` hanno cicli di vita **collegati**, ma non identici.
Un `Fragment` **vive dentro un'`Activity`**, quindi segue la sua esecuzione.

### 👈 **Activity**:

1. `onCreate()`
2. `onAttachFragment()`
3. `onStart()`
4. `onResume()`

### 👉 **Fragment**:

2. `onAttach()`
3. `onCreate()`
4. `onCreateView()`
5. `onActivityCreated()`
6. `onStart()`
7. `onResume()`

# 🧠 Spiegazione dei metodi importanti

### 🔹 `onAttach(Context)`

→ Il Fragment è collegato all’Activity → puoi usare `getActivity()`

> È il **primo punto di contatto** con l’Activity.

---

### 🔹 `onCreate(Bundle)`

→ Inizializza il Fragment (senza creare ancora la UI).

---

### 🔹 `onCreateView(...)`

→ Crea e ritorna la **view** del fragment (tipo il suo layout XML).

> Questo è dove usi `LayoutInflater`.

---

### 🔹 `onActivityCreated(Bundle)`

→ Chiamato quando l’Activity **ha finito il suo `onCreate()`**.

> Qui puoi iniziare a modificare elementi della UI.

---

### 🔹 `onStart()` / `onResume()`

→ Il fragment diventa **visibile** e poi **interattivo**.


| Ordine | Metodo                  | Cosa fa                                                         |
| ------ | ----------------------- | --------------------------------------------------------------- |
| 2      | `onPause()`             | L'Activity non è più in primo piano (ma visibile in background) |
| 4      | `onSaveInstanceState()` | Salva dati temporanei nel Bundle                                |
| 6      | `onStop()`              | L'Activity non è più visibile                                   |
| 10     | `onDestroy()`           | L’Activity viene distrutta per sempre                           |

| Ordine | Metodo                  | Cosa fa                                                              |
| ------ | ----------------------- | -------------------------------------------------------------------- |
| 1      | `onPause()`             | Il fragment non è più attivo                                         |
| 3      | `onSaveInstanceState()` | Salva lo stato del fragment (dati, posizione, ecc.) Serve per salvare dati prima che l’Activity o il Fragment venga distrutto temporaneamente                  |
| 5      | `onStop()`              | Il fragment non è più visibile                                       |
| 7      | `onDestroyView()`       | 🧱 Distrugge solo la **view** (UI), ma l'oggetto fragment resta vivo |
| 8      | `onDestroy()`           | Distrugge l’oggetto fragment vero e proprio                          |
| 9      | `onDetach()`            | Rimuove il fragment dall’activity                                    |

### **Navigation Component**: cos’è?

È il sistema moderno e ufficiale di Android per **gestire la navigazione tra schermate (fragment)**

####  È composto da 3 pezzi principali:

#### 1. **Navigation Graph (`nav_graph.xml`)**

un file di risorse che contiene tutte le
destinazioni e le azioni:
+ Destinazioni: Fragment da visualizzare
+ Azioni: connessioni logiche tra le destinazioni

Il grafo rappresenta tutti i percorsi di navigazione disponibili

È un **file XML** dove **disegni la mappa della tua app**:
cioè **quali fragment esistono** e **come si passa da uno all’altro**.

```xml
<fragment
    android:id="@+id/firstFragment"
    android:name="com.example.FirstFragment"
    android:label="First"
    tools:layout="@layout/fragment_first">
    <action
        android:id="@+id/action_toSecond"
        app:destination="@id/secondFragment" />
</fragment>
```

---

#### 2. **NavHost / NavHostFragment**

È un **contenitore vuoto** che **ospita i fragment**
e mostra **quello attivo** in base alla navigazione.

Nel layout XML:

```xml
<androidx.navigation.fragment.NavHostFragment
    android:id="@+id/nav_host_fragment"
    android:name="androidx.navigation.fragment.NavHostFragment"
    app:navGraph="@navigation/nav_graph"
    app:defaultNavHost="true"
    android:layout_width="match_parent"
    android:layout_height="match_parent" />
```

---

#### 3. **NavController**

È l’**oggetto che controlla la navigazione**:


I navigation host devono:
+ Gestire il salvataggio e il ripristino dello stato del proprio
controller
+ Chiamare Navigation.setViewNavController sulla loro vista
radice
+ Inoltrare gli eventi del pulsante "Indietro" del sistema al
NavController,
  + NavController.popBackStack


* sposta da un fragment all’altro
* gestisce il back
* legge le azioni dal grafo

```java
NavController navController = Navigation.findNavController(view);
navController.navigate(R.id.action_toSecond);
```


## Handler e Looper

Solo il thread main può interagire con la UI (gestisce eventi e oggetti grafici).
NON BLOCCARE IL THREAD MAIN , NON ACCEDERE AGLI OGGETTI GRAFICI CHE NON SONO MAIN THREAD.

### 1 Soluzione
Inviare il codice con l'aggiornamento al mainThread, il codice è contenuto nel metodo run di una Runnable:
+ Activity.runOnUIThread(Runnable)
+ View.post(Runnable)
+ View.postDelayed(Runnable,long)

### 2 Soluzione
Inviare un messaggio al main thread , i messaggi sono gestiti mediante una coda, ogni messaggio viene gestito nel mainThread che possiede la coda.

Gli oggetti che gestiscono questi messaggi si chiamano Handler, alcuni contengono una runnable.

Un handler è una classe che permette a due o più thread di scambiarsi informazioni di tipo Message/Runnable, è associato solo al thread in cui è creato.
La coda utilizzata di default all'handler per accodare i messaggi è quella del thread che lo ha creato, se ne può impostare una diversa passandola come argomento al costruttore. Si occupa di inserire i messaggi in coda e di gestire quelli che gli vengono recapitati sulla coda.

#### Metodi per spedire messaggi
1. handler.sentMessage(message)
2. handler.sentMessageAtFrontOfQueue(Message)
3. handler.sentMessageAtTime(Message,long)
4. handler.sendMessageDelayed(Message,long)

#### Metodi per inviare Runnable
1. handler.post(Runnable)
2. handler.postDelayed(Runnable, long)
3. handler.postAtTime(Runnable, long)


#### Metodi per gestire la ricezione
1. dispatchMessage(Message)
2. handleMessage(Message)

Per passare runnable attraverso messaggi:

```java
private final Message getPostMessage(Runnable r){
  Message m = Message.obtain();
  m.callback=r;
  return m;
}
```

Il pipeline Thread possiede una coda di task da eseguire.
Altri thread inseriscono task nella coda, la coda gestisce l'accesso.

Il pipeline Thread esegue i task uno dopo l'altro e attende nuovi task se la coda è vuota.
La classe che trasforma un thread in una pipeline di thread è il Looper, il gestore della coda.

### Looper

Il Looper è la classe che gestisce la MessageQueue associata ad un Thread.
Un Looper è associato solo ad un singolo Thread, ma può essere legato a più Handle.
Il Looper gestisce dei messaggi in modo asincrono, schedulandoli nel tempo ed inviandoli all’handler che li ha creati.
Il legame tra Thread e Looper si instaura a seguito della
chiamata statica Looper.prepare() (nel caso del main Thread con la chiamata Looper.prepareMainLooper())

Terminare un loop: quit()

```java
public void quit()
{
// NOTE: By enqueueing directly into the message queue, the
// message is left with a null target. This is how we know it is
// a quit message.
Message msg = Message.obtain();
mQueue.enqueueMessage(msg, 0);
}
```

La chiamata looper.quit() deve essere fatta da un altro
Thread ma sull’istanza di Looper che si vuole terminare.

Message.setTarget(Handler):
Se si utilizza la chiamata sendMessage(), nel campo target del messaggio viene salvato l’Handler dal quale si sta effettuando la chiamata, andando a sovrascrivere eventuali target precedenti. 

## Service e Receiver

I servizi sono componenti che avviano operazioni in background. I loro thread sopravvivono di più di quelli delle activity nascoste.

Sono specializzazioni della classe Context come le activity.
Le operazioni dei Service vengono eseguite nello stesso processo che li ha invocati.

Vanno dichiarati nel manifest.

Si hanno due tipi di servizi:
+ Started : vengono avviati e sono "indipendenti"
+ Bounded: accessibili da componenti dell'app o di altre.

+ Interfaccia Servizi started:
  + permette lo start e lo stop
+ Interfaccia Servizi bounded
  +  ottengo il riferimento al servizio e mi ci collego (binding)

### Avvio e binding

+ Servizi locali: start e stop
  + public abstract ComponentName startService (Intent service)
  + public abstract boolean stopService (Intent service)
+ Collegamento a servizi “remoti”
  + public abstract boolean bindService (Intent service,
ServiceConnection conn, int flags)
  + public abstract void unbindService (ServiceConnection conn)
### Ciclo di vita
+ public void onCreate ()
– eseguito alla creazione del servizio
+ public int onStartCommand (Intent intent, int flags, int
startId)
  + eseguito all’avvio del servizio
    + in corrispondenza di ogni startService()
  + flags
    + START_FLAG_REDELIVERY
    + START_FLAG_RETRY
+ public void onDestroy()
  + eseguito alla distruzione del servizio

| **Costanti**                      | **Descrizione**                                                                                                                                                                                                                                                                                           |
|----------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `START_STICKY`                   | Il Service viene mantenuto nello stato di started, ma l’Intent ricevuto non viene mantenuto. L’ambiente proverà successivamente a ricreare il servizio invocando nuovamente il metodo `onStartCommand()`, passando però un Intent a null, a meno che non ve ne siano di pendenti.               |
| `START_NOT_STICKY`              | Il Service, se non ci sono Intent in attesa, non viene ricreato fino a un’esplicita invocazione del metodo `startService()`. Questo garantisce che l’Intent passato sia sempre diverso da null.                                                                                                          |
| `START_REDELIVER_INTENT`        | In questo caso viene rischedulata una nuova partenza del Service, con un reinvio dello stesso Intent.                                                                                                                                                                                                    |
| `START_STICKY_COMPATIBILITY`    | Indica la non garanzia nella chiamata al metodo `onStartCommand()` nel caso di gestione con `START_STICKY`.                                                                                                                                                                                              |


### Cosa sono i Bounded Services
Un Bounded Service (servizio vincolato) è un tipo di servizio che:

+ Permette ad altri componenti (come Activity o altri servizi) di collegarsi (bind) ad esso.

+ Una volta collegati, possono inviare richieste, ricevere risposte e comunicare (anche tra processi diversi tramite IPC).

+ Diversamente dai Started Services, i Bounded Services vivono finché c'è almeno un componente collegato. Quando tutti si scollegano, il servizio si ferma automaticamente.

### Metodi principali

+ `bindService(Intent service, ServiceConnection conn, int flags)`: Serve per collegarsi al servizio.

  * `service`: l'`Intent` che specifica quale servizio.
  * `conn`: l'interfaccia che gestisce la connessione (`ServiceConnection`).
  * `flags`: opzioni (es. `Context.BIND_AUTO_CREATE` per creare il servizio se non esiste già).

+ `unbindService(ServiceConnection conn)` : Serve per scollegarsi dal servizio. Se tutte le connessioni vengono chiuse, il servizio si ferma.

### Interfaccia `ServiceConnection`

Questa interfaccia gestisce il legame tra il componente (es. Activity) e il servizio. Devi implementare due metodi:

+ `onServiceConnected(ComponentName name, IBinder service)`

  * Viene chiamato **quando la connessione al servizio avviene correttamente**.
  * Ricevi un oggetto `IBinder` che ti permette di **interagire con il servizio** (es. chiamare suoi metodi pubblici).

+ `onServiceDisconnected(ComponentName name)`

  * Chiamato **se la connessione viene persa** (es. se il processo viene terminato inaspettatamente).
  * Serve a gestire cleanup o notifiche nella tua app.

1. **Implementare l’interfaccia `ServiceConnection`**

Questa interfaccia ha due metodi da implementare:

+ `onServiceConnected()`

  * Viene chiamato automaticamente **quando la connessione al servizio avviene con successo**.
  * Fornisce un oggetto `IBinder` per **interagire col servizio**.

+ `onServiceDisconnected()`

  * Viene chiamato **solo se il servizio viene terminato inaspettatamente** (es. crash o kill del processo).
  * ⚠️ **Non viene chiamato** se il client chiama `unbindService()`.

2. **Chiamare `bindService()`**

* Serve per **collegare il componente (es. Activity) al servizio**.
* Devi passargli:

  * un `Intent` per specificare il servizio
  * un’istanza di `ServiceConnection` (quella che hai implementato)
  * dei flag (tipicamente `BIND_AUTO_CREATE`)

 3. **Usare il servizio**

  * Quando il sistema chiama `onServiceConnected()`, puoi usare l’oggetto `IBinder` per **interagire col servizio**.

 4. **Disconnettersi con `unbindService()`**

  * Quando il componente non ha più bisogno del servizio, chiama `unbindService()`.

### Ciclo di vita

Ecco i metodi principali del ciclo di vita:

+ `public void onCreate()`

  * Viene chiamato **una sola volta** alla **creazione del servizio**, cioè **quando il servizio viene avviato per la prima volta (con `bindService()` o `startService()`)**.
  * Serve per fare inizializzazioni.



+  `public IBinder onBind(Intent intent)`

   * Viene chiamato **quando un componente si collega al servizio** con `bindService()`.
   * Deve restituire un oggetto `IBinder`, che permette al client di comunicare con il servizio.
   * Se **più client** si collegano, questo metodo **non viene chiamato di nuovo**.


+ `public boolean onUnbind(Intent intent)`

  * Chiamato **quando tutti i client si sono scollegati** dal servizio (tutti hanno chiamato `unbindService()`).
  * Se ritorni `true`, allora il sistema chiamerà `onRebind()` se un nuovo client si collega successivamente.
  * Se ritorni `false`, il sistema chiamerà di nuovo `onBind()` invece di `onRebind()`.

+ `public void onRebind(Intent intent)`

  * Viene chiamato **quando un nuovo client si collega dopo che `onUnbind()` è già stato chiamato** **e se `onUnbind()` ha restituito `true`**.
  * Non viene chiamato se ritorni `false` da `onUnbind()` → in quel caso il ciclo riparte da `onBind()`.


+  `public void onDestroy()`

   * Chiamato **quando il servizio viene definitivamente distrutto** (es. non ci sono più client e il sistema decide di liberare memoria).
   * Serve per fare cleanup e rilasciare risorse.

```
onCreate()
   ↓
onBind()
   ↓
(client usa il servizio)
   ↓
onUnbind() ← Tutti i client scollegati
   ↓         ↘
onRebind()   onBind() (se ritorna false)
   ↓
onDestroy() ← Servizio terminato
```

### Cos’è `IBinder`

`IBinder` è un’interfaccia fondamentale del sistema Android per:

* **Permettere al client di accedere al servizio**.
* **Fornire un canale di comunicazione** tra client e servizio.
* È ciò che viene **restituito da `onBind()`** nel servizio.
Tre modalità per implementare `IBinder`

1.  **Estendere la classe `Binder`**

+ **Uso: comunicazione nel *singolo processo*** (servizio e client nello stesso processo, tipico di app normali).

* È la **soluzione più semplice**.
* Il servizio espone un oggetto `Binder` che fornisce accesso diretto ai suoi metodi pubblici.

 **Vantaggio**: facile da implementare, nessuna complicazione con IPC.
 **Limite**: **funziona solo se servizio e client sono nello stesso processo**.

 2. **Usare un `Messenger`**

+ **Uso: comunicazione *inter-processo* (IPC)**, ma **monothread**.

* Usi un `Handler` nel servizio e lo incapsuli in un `Messenger`.
* Il client invia `Message` al servizio.
* Tutta la comunicazione è asincrona.

**Vantaggio**: semplifica IPC senza usare AIDL.
**Limite**: la gestione è su **un solo thread**.