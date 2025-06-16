# Quantificare l'incertezza
## Agire in condizioni di incertezza
Nel mondo reale gli agenti si trovano in situazioni d'**incertezza**, dovute a osservabilità parziali, non determinismo o presenza di avversari.  
Un agente potrebbe non sapere mai con sicurezza in quale stato si trovi ora o dove si troverà dopo una sequenza di azioni.
Gli agenti , come visto sino ad ora, gestiscono l'incertezza tenendo traccia di uno **stato-credenza** e generando un piano condizionale che gestisca ogni eventualità.

Questo approccio funziona per problemi semplici ma presenta alcuni svantaggi:
+ l'agente deve considerare ogni possibile spiegazione per le osservazioni dei suoi sensori, anche quelle più improbabili. Così si genera uno stato credenza enorme e pieno di possibilità poco realistiche.
+ un piano condizionale corretto che gestisca ogni eventualità può crescere senza limiti e deve considerare contingenze del tutto improbabili.
+ a volte non esiste un piano che dia garanzia di raggiungere l'obiettivo, ma l'agente deve agire.

Ad esempio supponiamo che un taxi automatizzato abbia l'obiettivo di portare un passegero all'areoporto in tempo per prendere il volo. Il taxi costruisce un piano $A_{90}$ che prevede di partire da casa 90 minuti prima del volo e guidare ad una velocità ragionevole.
Anche se l'aereoporto dista pochi chilometri, l'agente non sarà in grado di concludere con assoluta certezza che il piano porterà all'obiettivo ma arriverà ad una conclusione più debole ovvero: "il piano $A_{90}$ ci farà arrivare all'obiettivo a patto che la macchina non abbia un guasto o finisca la benzina, o ci colpisca un meteorite,...".
Non è possibile dedurre con certezza nessuna di queste condizioni, perciò non possiamo inferire il successo del piano. Questo problema prende il nome di **problema della qualificazione**, ancora non risolto.

Nondimeno $A_{90}$ è effettivamente la cosa giusta da fare, pocihé massimizza la misura di prestazione attesa dell'agente (arrivare in tempo per il volo, evitare noiose attese in aereoporto e evitare multe).
La conoscenza dell'agente non può garantire questi possibili esisti di $A_{90}$ ma può fornire un qualche grado di credenza sul loro raggiungimento.
*La cosa giusta da fare dipende quindi dall'importanza relativa dei vari obiettivi, sia dalla probabilità e dalla misura del loro raggiungimento*.

### Riassumere l'incertezza
Prendiamo come esempio di ragionamento incerto quello di diagniosticare il mal di denti ad un paziente. La diagnosi prevede, quasi sempre, un certo grado di incertezza. Mostriamo l'ineguatezza dell'approccio logico:
$$\text{MalDiDenti}\implies \text{Carie}$$
Il problema è che questa regola è sbagliata perché non tutti i pazienti che accusano mal di denti hanno carie, allora potremmo cambiare la regola in:
$$\text{MalDiDenti}\implies \text{Carie} \lor \text{Gengivite}\lor \text{Ascesso}\ ...$$

Sfortunatamente, per rendere valida la regola dovremmo aggiungere una lista quasi infinta di possibili problematiche. Potremmo provare a trasformarla ina una regola causale:
$$\text{Carie}\implies \text{MalDiDenti}$$

Ma neppure questa regola è corretta perché non tutte le carie causano dolore.  
L'unico modo è di correggere la regola è renderla logicamente esaustiva, arricchendo la parte sinistra in modo tale che una carie causi effettivamente dolore.  
Cercare di usare la logica per gestire un dominio come quello di una diagnosi medica fallisce per tre motivi:
+ **Pigrizia**: elencare l'insieme completo di premesse e conseguenze che rendono una regola priva di eccezioni richiede troppo lavoro, e le regole risultano difficili da utilizzare
+ **Ignoranza teorica**: la scienza medica non ha una teoria completa per il dominio
+ **Ignoranza pratica**: anche se conosciamo tutte le regole, potremmo essere incerti nei confronti di un particolare paziente perché non sono stati eseguiti tutti gli esami necessari.

La conoscenza dell'agente, nel caso migliore, può fornire solo un **grado di credenza** nelle formule. Il nostro strumento migliore per la gestione dei gradi di credenza è **la teoria della probabilità**.

>Gli **impegni ontologici** della logica e della probabilità sono identici: il mondo è composto di fatti che valgono e non valgono in ogni caso particolare.
>Gli **impegni epistemologici** sono diversi: un agente logico crede che ogni formula sia vera o falsa o non abbia opinione, mentre un agente probabilistico potrebbe avere un grado numerico di credenza tra 0 e 1.

*La teoria della probabilità fornisce un modo per riassumere l'incertezza che deriva dalla nostra pigrizia e ignoranza*.
Potremmo anche non sapere cosa affligge, con certezza, un paziente ma sappiamo che all'80% dei casi (cioè con probabilità 0.8) un paziente che ha mal di denti ha una carie. Questo significa che prendendo tutte le situazioni indinstinguibili da quella corrente per quanto riguarda la conoscenza dell'agente, il paziente avrà una carie nell'80% di esse.

Nel mondo reale, però, non vi è incertezza al momento della diagnosi: il paziente ha una carie oppure no. Cosa significa dunque che la probabilità che si ha una carie è all'80%? Non dovrebbe essere 0 o 1? Le affermazioni probabilistiche sono fatte rispetto a uno stato di conoscenza, non al mondo reale.
Affermiamo: "La probabilità che il paziente abbia una carie, dato che ha mal di denti, è 80%". Se in seguito veniamo a sapere che il paziente ha già avuto problemi gengivali possiamo fare un'affermazione diversa: "La probabilità che il paziente abbia una carie dato che ha mal di denti e problemi gengivali è 40%". Se invece arrivano informazioni che contraddicono l'ipotesi di carie allora: "La probabilità che il paziente abbia una carie, dato che ha mal di denti, è 0%". Notate che queste affermazioni non si contraddicono tra loro, ogniuna di esse è sperata e relativa a un diverso stato di conoscenza.

### Incertezza e decisioni razionali
Dati diversi piani per il raggiungimento di un obiettivo, per compiere una scelta tra questi un agente deve avere, prima di tutto, **preferenze** sui possibili **esiti** dei vari piani. Un esito è la descrizione completa di uno stato, che specifica fattori come il fatto di arrivare in tempo per il volo e la durata dell'attesa in aereoporto. Per rappresentare le preferenze e ragionare quantitativamente con esse useremo la **teoria dell'utilità** che afferma che ogni stato (o sequenza di stati) ha un certo grado di utilità per l'agente e che quest'ultimo preferirà sempre stati con utilità maggiore. 
L'utilità di uno stato è relativa ad un agente. Per esempio l'utilità di uno stato in cui il bianco vince una partita a scacchi è ovviamente alta per l'agente che gioca con i pezzi bianchi e bassa per quello che gioca con i pezzi neri ma non è possibile considerare rigidamente i punteggi imposte dalle regole di scacchi poiché alcuni giocatori potrebbero essere contenti di vincere mentre altri no. Non è possibile valutare oggettivamente gusti o preferenze: qualcuno potrebbe preferire il gelato al cioccolato altri il gelato al gusto gomma delle automobili. Una funzione di utilità tiene conto di qualsiasi preferenza.
Le preferenze sono combinate con le probabilità nella **teoria delle decisioni**.

*Un agente è razionale se e solo se sceglie l'azione che portaa alla più alta utilità attesa,calcolata sulla media di tutti i possibili esiti dell'azione*. 

Questo è il principio della **Massima Utilità Attesa**, dove il termine "attesa" significa "media stocastica" delle utilità degli esiti, pesata dalla probabilità dell'esito.

## Notazione base della teoria della probabilità
Le asserzioni probabilistiche riguardano mondi possibili come le asserzioni logiche, la differenza sta nel fatto che le asserzioni logiche affermano quali mondi possibili sono strettamente esclusi (asserzioni false), le asserzioni probabilistiche indicano quanto sono probabili i vari mondi.
In teoria della probabilità, l'insieme di tutti i mondi possibili è chiamato **spazio campionario ($\Omega$)**. I mondi possibili sono *mutualmente esclusivi* ed *esaustivi*: due mondi possibili non possono verificarsi entrambi, e un mondo possibile deve necessariamente verificarsi. 

> $\omega$ si riferisce a elementi dello spazio campionario, cioè particolari mondi possibili

Un **modello di probabilità** completamente specificato associa una probabilità numerica $P(\omega)$ a ogni mondo possibile.

Gli assiomi di base della teoria della probabilità affermano:
$$0 \leq P(\omega)\leq 1 \ \forall \omega \ \ \text{e} \  \ \sum_{\omega \in \Omega}P(\omega)=1 \ \ (12.1)$$

Solitamente le asserzioni e le interrogazioni probabilistiche non riguardano alcuni particolari mondi possibili, ma insiemi di mondi (ad esempio potremmo chiedere la probabilità di ottenere 11 lanciando due dadi o di ottenere due numeri uguali e così via...). Nella teoria della probabilità questo insieme di mondi sono detti **eventi**, in logica un insieme di mondi corrisponde ad una **proposizione** di un linguaggio formale. 
La probabilità associata ad una proposizione è definita come la somma delle probabilità dei mondi in cui tale proposizione vale:  
$$\text{Per qualsiasi proposizione }\phi, P(\phi)=\sum_{\omega \in \phi}P(\omega)\ (12.2)$$

Per esempio, nel lancio di due dadi non truccati abbiamo: 

$$P(\text{Totale=11})=P((5,6))+P((6,5))=\frac{1}{36}+\frac{1}{36}=\frac{1}{18}$$

La teoria della probabilità non richiede la conoscenza completa delle probabilità di ogni mondo possibile.
Ad esempio, se crediamo che i dadi siano truccati per produrre lo stesso numero, potremmo asserire:  
$$P(\text{doppio})=\frac{1}{4}$$

Senza sapere se i dadi "preferiscano" il doppio 6 al doppio 2, ad esempio.
Questa asserzione *vincola* il modello di probabilità sottostante senza determinarlo completamente.

Dunque in parole più semplici la probabilità di prendere un doppio è $\frac{1}{4}$ ma non sappiamo se è più probabile prendere due 2 invece che due 6. Così vincoliamo il modello di probabilità sottostante (la distribuzione effettiva delle probabilità per ogni coppia di numeri uguali), poiché la somma delle probabilità di ogni coppia dovrà essere $\frac{1}{4}$.

Le probabilità come $P(\text{Totale=11})$ o $P(\text{doppio})$ sono dette **non condizionate** o **a priori** si riferiscono a gradi di credenza in proposizioni *in assenza di altre informazioni*. Nella maggior parte dei casi, tuttavia abbiamo alcune informazioni, chiamate **evidenze**.
Supponiamo che il primo dado sia stato lanciato e sia uscito 5 e siamo in attesa dell'altro. In questo caso non siamo interessati alla probabilità non condizionata di ottenere un numero doppio, ma alla probabilità **condizionata** o **a posteriori** di ottenere un numero doppio *dato che il primo dado lanciato è un* 5:
$$P(\text{doppio }|\text{ Dado}_1=5)$$ 

ove $|$ si legge "dato".

Ad esempio se stiamo andando dal dentista per un controllo potremmo essere interessati alla probabilità a priori $P(\text{carie})=0.2$. Se invece andiamo dal dentista perché abbiamo mal di denti, ci interessa la probabilità condizionata $P(\text{carie }|\text{ mal di denti})=0.6$.
E' importante capire che $P(\text{carie})=0.2$ è ancora *valida* dopo l'osservazione sul mal di denti, tuttavia non è più utile. Quando prende decisioni, un agente deve tener conto di tutte le evidenze che ha osservato. E'importante differenziare anche l'implicazione logica e condizionamento. L'asserzione 

$$P(\text{Carie }|\text{ Mal di denti})=0.6$$

non significa "Ogni volta che mal di denti è vero, concludiamo che carie è vero con probabilità 0.6" ma piuttosto: "Ogni volta che mal di denti è vero e non abbiamo altre informazioni, concludiamo che carie è vero con probabilità 0.6".

Ad esempio se avessimo l'ulteriore informazione che il paziente non ha carie , la probabilità dovrebbe essere 0 e non 0.6:  

$$P(\text{Carie }| \text{Mal di denti } \land \ \lnot \text{Carie})=0$$

In termini matematici, per due proposizioni $a,b$ abbiamo:
$$P(a \ |\ b)=\frac{P(a \land b)}{P(b)} \ (12.3)$$

che vale ogni volta che $P(b)>0$ ad esempio:  
$$P(\text{Doppio }|\text{ Dado}_1=5)=\frac{P(\text{doppio }\land \text{ Dado}_1=5)}{P(\text{ Dado}_1=5)}$$

La formula può essere scritta in modo diverso, ottenendo **la regola del prodotto**:

$$P(a \land b)=P(a\ | \ b)P(b)$$  

### Il linguaggio delle proposizioni nelle asserzioni probabilistiche
Inizieremo ad usare **rappresentazioni fattorizzate**, in cui un mondo possibile è rappresentato  da un insieme di coppie variabile/valore.
Le variabili, in teoria della probabilità, sono chiamate **variabili casuali** (iniziale maiuscola). Nell'esempio dei dadi, $\text{Dado}_1$ e $\text{Totale}$ sono variabili casuali.
Ogni variabile casuale è una funzione dal dominio dei mondi possibili $\Omega$ a un certo **intervallo**, l'insieme dei valori che la variabile può assumere (lettera minuscola).  
Ad esempio l'intervallo di $\text{Totale}$ è l'insieme $\set{2,...,12}$.
Una variabile casuale booleana ha come intervallo $\set{\text{True,False}}$, ad esempio $P(\text{Doppio=True})$, quindi la probabilità di ottenere un doppio.  
Per semplicità le proposizioni della forma $\text{A = True}$ sono abbreviati in $a$ mentre $\text{A=False}$ è abbreviata in $\lnot a$.

Gli intervalli possono essere insiemi di token arbitrari. Potremmo scegliere come intervallo di CondizioniAtmosferiche l'insieme $\set{\text{sole, pioggia, coperto, neve}}$. Quando non c'è possibilità di ambiguità, si utilizza un valore per rappresentare una proposizione che una particolare variabile ha que valore, ad esempio *sole* può stare per *CondizioniAtmosferiche=sole*.
Le variabili possono avere anche intervalli infiniti,discreti o continui.
Se una variabile ha un intervallo ordinato sono possibili anche le diseguaglianze, come $\text{NumeroDiAtomi}\geq 10^{70}$.
Infine possiamo combinare queste proposizioni elementari usando i connettivi logici ad esempio:  
$$P(\text{Carie }|\ \lnot \text{MalDiDenti},\text{Adolescente})=0.1$$

> Nella notazione della probabilità, la virgola corrisponde alla congiunzione logica

A volte vorremo parlare delle probabilità di *tutti* i valori possibili di una variabile casuale:  
$$P(\text{CondizioniAtmosferiche=sole})=0.6\\P(\text{CondizioniAtmosferiche=pioggia})=0.1\\P(\text{CondizioniAtmosferiche=coperto})=0.29\\P(\text{CondizioniAtmosferiche=neve})=0.01\\$$

con un abbreviazione:
$$\bold{P}(\text{CondizioniAtmosferiche})=(0.6,0.1,0.29,0.01)$$

dove $\bold{P}$ indica che il risultato è un vettore di numeri e ipotizziamo che vi sia un ordinamento predefinito sull'intervallo di *CondizioniAtmosferiche*.
Diciamo che l'affermazione $\bold{P}$ definisce una **distribuzione di probabilità** per la variabile casuale *CondizioniAtmosferiche*, cioè un assegnamento di una probabilità a ogni possibile valore della variabile casuale (con un intervallo finito e discreto, la distribuzione è detta **distribuzione categoriale**).
La notazione $\bold{P}$ è usata anche per le distribuzioni condizionate: $\bold{P}(X|Y)$ fornisce i valori di $P(X=x_i|Y=y_i)$ per ogni possibile coppia $i,j$.

Per le variabili continue non è possibile scrivere l'intera distribuzione come vettore, perché i possibili valori sono infiniti. Possiamo però definire la probabilità che una variabile casuale assuma un valore $x$ come funzione parametrizzata di $x$, chiamata **funzione di densità di probabilità**:
$$P(\text{TemperaturaMezzogiorno}=x)=\text{Uniform}(x;18C,26C)$$

esprime la credenza che la temperatura a mezzogiorno sia distribuita uniformemente tra i 18 e i 26 gradi Celsius.

Le funzioni di densità di probabilità (**PDF**) hanno un significato diverso dalle distribuzioni discrete
Dire che la densità di probabilità è uniforme da 18C a 26C significa che vi è una possibilità del 100% che la temperatura ricada in quell'intervallo esteso su 8C e una possibilità del 50% che ricada in un sottointervallo di 4C, e così via. 
Scriviamo la densità di probabilità per una variabile casuale continua $X$ nel valore $x$ come $P(X = x)$ o semplicemente $P(x)$; la definizione intuitiva di $P(x)$ è la probabilità che $X$ ricada in un intorno arbitrariamente piccolo di $x$, divisa per l'ampiezza dell'intorno:
$$P(x)=\lim_{\Delta x\rightarrow 0}\frac{P(x\leq X \leq x+\Delta x)}{\Delta x}$$

Per *TemperaturaMezzogiorno* abbiamo:  
$$P(\text{TemperaturaMezzogiorno}=x)=\text{Uniform}(x;18C,26C)=\begin{cases}
    \frac{1}{8C} \text{ se }18C\leq x \leq 26 \\
    0 \text{ altrimenti}
\end{cases}$$

In $P(\text{TemperaturaMezzogiorno=20.18})=\frac{1}{8C}$ notiamo che $\frac{1}{8C}$ non è una probabilità ma una densità di probabilità. La probabilità che *TemperaturaMezzogiorno* sia esattamente 20.18C è zero, perché 20.18C è un intervallo di ampiezza 0. 

Ci serve una notazione non solo per distribuzioni su singole variabili, ma anche per distribuzioni su variabili multiple. A questo scopo utilizziamo le virgole. 
Per esempio, $\bold{P}(\text{CondizioniAtmosferiche, Carie})$ denota le probabilità di tutte le combinazioni dei valori di *CondizioniAtmosferiche* e *Carie*. 
Si tratta di una tabella di probabilità $4 \times 2$ chiamata **distribuzione di probabilità congiunta** di *CondizioniAtmosferiche* e *Carie*. 

Possiamo anche mescolare variabili e valori specifici: $\bold{P}(\text{sole, Carie})$ sarebbe un vettore di due elementi che fornisce le probabilità di avere una carie con una giornata soleggiata e nessuna carie con una giornata soleggiata.

Con la notazione $\bold{P}$ possiamo applicare la regola del prodotto per tutti i valori possibili di *CondizioniAtmosferiche* e *Carie*:
$$\bold{P}(\text{CondizioniAtmosferiche,Carie})=\bold{P}(\text{CondizioniAtmosferiche }| \text{ Carie})\bold{P}(\text{Carie})$$

Al posto di avere $4 \times 2=8$ equazioni con tutte le possibilità.  

Un caso degenere è $\bold{P}(\text{sole,carie})$ che non ha variabili e dunque è uno scalare e non un vettore.  

Per completare la semantica dobbiamo dire quali sono i mondi e come determinare se una proposizione vale in un mondo. 
Riprendiamo questa parte direttamente dalla semantica della logica proposizionale, come segue: 
*un mondo possibile è definito come un assegnamento di valori a tutte le variabili casuali oggetto di considerazione*.

Notate che alcune variabili casuali potrebbero essere ridondanti, nel senso che i loro valori sono ottenibili in tutti i casi dai valori di altre variabili. Per esempio, la variabile Doppi nel mondo dei due dati è vera esattamente quando Dado1 = Dado2. Se si include Doppi tra le variabili casuali in aggiunta a Dado1 e Dado2, sembra che il numero dei mondi possibili aumenti da 36 a 72, ma esattamente la metà di quei 72 mondi sarà logicamente impossibile e avrà probabilità 0. 
Dalla precedente definizione di mondi possibili segue che un modello di probabilità è completamente determinato dalla distribuzione congiunta di tutte le variabili casuali, la cosiddetta **distribuzione di probabilità congiunta completa**. 
Per esempio, dati *Carie, Maldidenti e CondizioniAtmosferiche*, la distribuzione congiunta completa è $\bold{P}(\text{Carie, Maldidenti, CondizioniAtmosferiche})$. Questa distribuzione congiunta può essere rappresentata come una tabella $2 \times 2 \times 4$ con $16$ elementi. Poiché la probabilità di ogni proposizione è una somma su mondi possibili, una distribuzione congiunta completa è sufficiente, in linea di principio, per calcolare la probabilità di qualsiasi proposizione.

### Gli assiomi della teoria della probabilità e la loro ragionevolezza
$$\begin{matrix}
P(\lnot a)&=&\sum_{\omega \in \lnot a}P(\omega)\\
\\
&=&\sum_{\omega \in \lnot a}P(\omega)-\sum_{\omega \in a}P(\omega)-\sum_{\omega \in a}P(\omega) \\
\\
&=&\sum_{\omega \in  \Omega}P(\omega)-\sum_{\omega \in a}P(\omega) \text{ (ragruppando i primi due termini)}\\
\\
&=&1-P(a) \text{(per 12.1 e 12.2)}
\end{matrix}$$

Possiamo anche ricavare il **principio di inclusione-esclusione**:
$$P(a \lor b)=P(a)+P(b)-P(a \land b) \ \ (12.5)$$

$(12.5)$ e $(12.1)$ sono spesso chiamati **assiomi di Kolmogorov**

## Inferenze basate su distribuzioni congiunte complete  
Descriviamo un metodo semplice per l'**inferenza probabilistica**. Useremo la distribuzione congiunta completa come "base di conoscenza" da cui poter derivare le risposte a tutte le domande.  
Cominciamo con un esempio semplice: un dominio che consiste nelle tre sole variabili booleane *Maldidenti,Carie,Prende*. La distribuzione di probabilità congiunta completa è la seguente tabella $2\times 2 \times 2$:

<img src="/Screen/image.png" width=400>

Notate che la somma delle probabilità della distribuzione congiunta è pari a 1, come richiesto dagli assiomi. 
Notate anche che l'Equazione $(12.2)$ ci fornisce un metodo diretto per calcolare la probabilità di ogni proposizione, semplice o complessa: basta semplicemente identificare i mondi possibili in cui la proposizione è vera e sommare le loro probabilità. 

Per esempio, ci sono sei mondi possibili in cui $\text{carie} \lor \text{maldidenti}$ è verificata:

$$P(\text{carie} \lor \text{maldidenti}) = 0.108 + 0.012 + 0.072 + 0.008 + 0.016 + 0.064 = 0.28$$

Un'attività particolarmente comune è l'estrazione della distribuzione su un sottoinsieme particolare di variabili o una variabile singola. Per esempio, sommare tutti i valori della prima riga ci fornisce la **probabilità marginale** o non condizionata di *carie*:
$$P(carie)= 0.108 + 0.012 + 0.072 + 0.008=0.2$$  

Questo processo viene chiamato **marginalizzazione**, perché sommano le probabilità per ogni valore possibile delle altri variabili, rimuovendole così dall'equazione. Possiamo scrivere la seguente regola generale di marginalizzazione per qualsiasi coppia di insiemi di variabili $\bold{Y}$ e $\bold{Z}$:  
$$\bold{P(Y)}=\sum_{z}\bold{P(Y,Z=z)} \ \ (12.7)$$

Dove $\sum_z$ indica la somma su tutte le possibili combinazioni di valori dell'insieme di variabili $\bold{Z}$.
Ad esempio per le carie:  
$$\begin{matrix}
\bold{P}(\text{Carie})&=&\bold{P}(\text{Carie,maldidenti,prende})+\bold{P}(\text{Carie,maldidenti,¬prende})\\
\\
&+&\bold{P}(\text{Carie,¬maldidenti,prende})+\bold{P}(\text{Carie,¬maldidenti,¬prende})\\
\\
&=&(0.108,0.016)+(0.012,0.064)+(0.072,0.144)+(0.008,0.576)=(0.2,0.8)
\end{matrix}$$

Usando la regola del prodotto possiamo modificare $(12.7)$ ottenendo la regola del **condizionamento**:
$$\bold{P(Y)}=\sum_z \bold{P(Y|Z)}P(\bold{z})$$

Le probabilità condizionate (di solito molto interessanti) possono essere ricavate usando anzitutto $(12.3)$ per ottenere un' espressione che contiene probabilità non condizionate e successivamente valutando tale espressione per messo della distribuzione congiunta completa:  
$$P(\text{carie }|\text{ maldidenti})=\frac{P(\text{carie}\land \text{maldidenti})}{P(\text{maldidenti})}=\frac{0.108+0.012}{0.108+0.012+0.016+0.064}=0.6$$

Vediamo la probabilità che dato il mal di denti non ci sia carie:  

$$P(\lnot\text{carie }|\text{ maldidenti})=\frac{P(\lnot\text{carie}\land \text{maldidenti})}{P(\text{maldidenti})}=\frac{0.0168+0.064}{0.108+0.012+0.016+0.064}=0.4$$

Il termine $P(\text{maldidenti})$ sta al denominatore in entrambi i calcoli precedenti.
Se la variabile *Carie* avesse più di due valori, tale termine sarebbe al denominatore di tutti.
In effetti, il termine può essere considerato una costante di **normalizzazione** per la distribuzione $P(\text{Carie | maldidenti})$, che assicura che la sua somma valga $1$. 
Useremo la lettera $\alpha$ per indicare simili costanti. Con questa notazione, possiamo scrivere le due precedenti equazioni come una sola:

$$\begin{matrix}
P(\text{Carie | maldidenti})&=&\alpha \bold{P}(\text{Carie,maldidenti})\\
&=&\alpha[\bold{P}(\text{Carie,maldidenti,prende})+\bold{P}(\text{Carie,maldidenti,¬prende})]\\
&=&\alpha[(0.108,0.016)+(0.012,0.064)]=\alpha(0.12,0.08)=(0.6,0.4)  
\end{matrix}$$

ove $\alpha=\frac{1}{0.2}$

In altre parole possiamo calcolare $\bold{P}(\text{Carie | maldidenti})$ senza conoscere il valore di $P(\text{maldidenti})$!
Dimentichiamo intanto il fattore $\alpha$ e sommiamo i valori per *carie* e *¬carie*, ottenendo $0.12$ e $0.08$. 
Questi valori sono in proporzioni relative corrette, ma la loro somma non è $1$, perciò li normalizziamo dividendoli entrambi per $0.12 + 0.08$, ottenendo le vere probabilità: $0.6$ e $0.4$.
La normalizzazione si rivela un'utile scorciatoia in molti calcoli relativi alle probabilità, sia per facilitarli, sia per consentirci di procedere quando non sono disponibili alcune valutazioni probabilistiche (come $P(\text{maldidenti})$). 

Da questo esempio possiamo ricavare una procedura di inferenza generale. 
Iniziamo con il caso in cui l'interrogazione riguarda una sola variabile, $X$ (nell'esempio, *Carie*). 
Sia $\bold{E}$ la lista di variabili dell'evidenza (nell'esempio, solo *MalDiDenti*), sia $\bold{e}$ la lista dei valori osservati per esse e sia $\bold{Y}$ l'insieme delle restanti variabili non osservate (nell'esempio, solo *Prende*). 
La query è $\bold{P}(X|\bold{e})$ e può essere valutata come:
$$\bold{P}(X|\bold{e})=\alpha \bold{P}(X,\bold{e})=\alpha \sum_y \bold{P}(X,\bold{e},\bold{y})\ \ (12.9)$$

dove la somma spazia su tutte le possibili $\bold{y}$ combinazioni di valori delle variabili non osservate $\bold{Y}$. 

Data la distribuzione congiunta completa su cui lavorare, l'Equazione (12.9) può rispondere a interrogazioni probabilistiche su variabili discrete. 
L'algoritmo tuttavia non scala molto bene: se il dominio è descritto da $n$ variabili booleane, le dimensioni della tabella di input devono essere $O(2^n)$, così come il tempo richiesto per elaborarla. 

Per queste ragioni, la distribuzione congiunta completa in forma tabellare è uno strumento utilizzabile soltanto raramente per costruire sistemi di ragionamento nella pratica. Dev’essere piuttosto considerata il fondamento teorico da cui partire per sviluppare approcci più efficaci.

## Indipendenza

Espandiamo la distribuzione congiunta completa aggiungendo una quarta variabile, *CondizioniAtmosferiche*. 
A questo punto la distribuzione diventa $\bold{P}(\text{MalDiDenti, Prende, Carie, CondizioniAtmosferiche})$, che ha $2 \times 2 \times 2 \times 4 = 32$ elementi. 
È naturale chiedersi quale sia la relazione di queste edizioni tra loro e con la tabella originale a tre variabili, nonché quale relazione ci sia tra $P(\text{maldidenti, prende, carie, CondizioniAtmosferiche = coperto})$ e $P(\text{maldidenti, prende, carie})$. 
Possiamo usare la regola del prodotto:
$$P(\text{maldidenti,prende,carie,coperto})=P(\text{coperto|maldidenti, prende, carie}) P(\text{maldidenti, prende, carie})$$  

Ma sembra molto difficile che operazioni dentistiche influenzino il meteo o viceversa dunque:  

$$P (\text{coperto|maldidenti, prende, carie}) = P(\text{coperto})$$

Da cui possiamo dedurre che:  
$$P (\text{maldidenti, prende, carie, coperto}) = P(\text{coperto})P(\text{maldidenti, prende, carie})$$  

Possiamo scrivere l'equazione generale:
$$\bold{P}(\text{MalDiDenti, Prende, Carie, CondizioniAtmosferiche}) = \bold{P}(\text{MalDiDenti, Prende, Carie})\bold{P}(\text{CondizioniAtmosferiche})$$

La proprietà che abbiamo sfruttato scrivendo l'Equazione (12.10) prende il nome di **indipendenza** (alternativamente, **indipendenza marginale** o **indipendenza assoluta**).
In particolare, in questo caso le condizioni meteo sono indipendenti dai nostri problemi dentari. 
L'indipendenza tra le proposizioni $a$ e $b$ può essere scritta come:  
$$P(a|b) = P(a)\text{ oppure } P(b|a) = P(b)\text{ oppure }P(a ∧ b) = P(a)P(b)$$  

L'indipendenza tra le variabili $X$ e $Y$ può essere scritta come segue: 

$$\bold{P}(X|Y) = \bold{P}(X)    \text{ oppure }    \bold{P}(Y|X) = \bold{P}(Y)    \text{ oppure }   \bold{P}(X, Y) = \bold{P}(X)\bold{P}(Y)$$  

## Regola di bayes  

Dalla regola del prodotto:  

$$P(a ∧ b) = P(a|b)P(b)     \text{  oppure  }     P(a ∧ b) = P(b|a)P(a)$$  

Possiamo derivare la **formula di bayes**   
$$P(b|a)=\frac{P(a|b)P(b)}{P(a)}$$  

Il caso più generale:  
$$\bold{P}(Y|X)=\frac{\bold{P}(X|Y)\bold{P}(Y)}{\bold{P}(X)}$$  

Ci capiterà di usare anche una versione condizionata su un evidenza $\bold{e}$:  
$$\bold{P}(Y|X,\bold{e})=\frac{\bold{P}(X|Y,\bold{e})\bold{P}(Y|\bold{e})}{\bold{P}(X|\bold{e})} \ \ (12.13)$$  

## Applicazioni semplici  

Spesso percepiamo come evidenza l'*effetto* di una *causa* ignota che vorremmo determinare. In tal caso la regola di bayes viene in nostro soccorso:  
$$P(\text{causa}|\text{effetto})=\frac{P(\text{effetto|causa})P(\text{causa})}{P(\text{effetto})}$$  

La probabilità condizionata $P(\text{effetto|causa})$ quantifica la relazione nella direzione causale, mentre $P(\text{causa|effetto})$ descrive la direzione diagnostica. In un'attività come la diagnosi medica, spesso abbiamo a disposizione le probabilità condizionate delle relazioni causali. Il medico conosce $P(\text{sintomi|malattia})$ e vuole ricavare una diagnosi, $P(\text{malattia|sintomi})$.  

Supponiamo  che $s$ sia la proposizione "Il paziente ha il torcicollo" e $m$ la proposizione "Il paziente ha la meningite".  
Supponiamo anche di sapere che:  
$$P(s|m)=0.7, \ P(m)=1/50000, \ P(s)=0.01$$  

allora  
$$P(m|s)=\frac{P(s|m)P(m)}{P(s)}=\frac{0.7\ \cdot \ 1/50000 }{0.01}=0.0014$$  

Abbiamo precedentemente illustrato un metodo per cui si può evitare di stimare la probabilità dell'evidenza (qui $P(s)$) calcolando invece una probabilità a posteriori per ogni variabile della query (qui $\lnot m, \ m$) e normalizzando i risultati. Lo stesso concetto vale per la regola di Bayes :  
$$\bold{P}(M|s)=\alpha (P(s|m)P(m),P(s|\lnot m)P(\lnot m))$$  

Per utilizzare questo approccio dobbiamo stimare $P(s|\lnot m)$ invece che $P(s)$ e questo passaggio non è banale.
La forma generale di Bayes con la normalizzazione è  
$$\bold{P}(Y|X)=\alpha \bold{P}(X|Y)\bold{P}(Y)$$  

dove $\alpha$ è la costante di normalizzazione necessaria affinché la somma degli elementi di $\bold{P}(Y|X)$ sia $1$.

## Bayes: combinazione di eventi  
Abbiamo visto che la regola di Bayes può essere utile per rispondere a interrogazioni probabilistiche condizionate su un singolo elemento di evidenza.
Che cosa succede quando abbiamo due o più elementi di evidenza?

Se conosciamo la distribuzione congiunta completa (Tabella in figura) possiamo leggere semplicemente la risposta:
$$\bold{P}(\text{Carie}|\text{maldidenti ∧ prende}) = \alpha ⟨0,108, 0,016⟩ ≈ ⟨0,871, 0,129⟩$$

 
Tuttavia, sappiamo che un simile approccio non scala al crescere del numero delle variabili. 
Possiamo provare a riformulare il problema usando la regola di Bayes:
$$\bold{P}(\text{Carie}|\text{maldidenti ∧ prende}) = \alpha \bold{P}(\text{maldidenti ∧ prende|Carie})\bold{P}(\text{Carie}) \ \ \	(12.16)$$

 
Affinché questa riformulazione possa funzionare, occorre conoscere le probabilità condizionate della congiunzione $\text{maldidenti ∧ prende}$ per ogni valore di $\text{Carie}$. 
Ancora una volta, questo potrebbe essere fattibile con una evidenza costituita da due variabili, ma non scala. Infatti, con $n$ variabili di evidenza (radiografie, dieta, igiene orale etc.) risulteranno $O(2^n)$ combinazioni possibili di valori osservati di cui dovremmo conoscere le probabilità condizionate: a questo punto conviene usare la distribuzione congiunta completa.

Per fare progressi, dovremo trovare asserzioni aggiuntive riguardanti il dominio che ci permettano di semplificare le espressioni. 
La nozione di **indipendenza** è un buon inizio, ma dev'essere raffinata. Sarebbe bello se $\text{MalDiDenti}$ e $\text{Prende}$ fossero indipendenti, ma non lo sono: se lo strumento si incastra nel dente, è probabile che quest'ultimo abbia una carie e che questa sia la causa del dolore. Le variabili sono indipendenti, però, data la presenza o assenza della carie. 
In termini matematici questa proprietà si scrive:

$$\bold{P}\text{(maldidenti ∧ prende|Carie)} = \bold{P}\text{(maldidenti|Carie)}\bold{P}\text{(prende|Carie)} \ \ \	(12.17)$$

 
Questa equazione esprime l'**indipendenza condizionale** di maldidenti e prende data $\text{Carie}$. Possiamo sostituirla nell'Equazione (12.16) per ottenere la probabilità della carie:

$$\bold{P}\text{(Carie|maldidenti ∧ prende )} = \alpha \bold{P}\text{(maldidenti|Carie)}\bold{P}\text{(prende|Carie)} \bold{P}\text{(Carie)} \ \ \ (12.18)$$

La definizione generale di indipendenza condizionale di due variabili $X$ e $Y$, data una terza variabile $Z$, è

$$\bold{P}(X, Y|Z) = \bold{P}(X|Z)\bold{P}(Y|Z)$$

Nel dominio dentistico, per esempio, sembra ragionevole asserire l'indipendenza condizionale delle variabili $\text{MalDiDenti , Prende}$, data $\text{Carie}$:

$$\bold{P}(\text{MalDiDenti, Prende|Carie}) = \bold{P}(\text{MalDiDenti|Carie})\bold{P}(\text{Prende|Carie}) \ \ \ (12.19)$$

 
Notate che quest'asserzione è più forte dell'Equazione (12.17), che afferma l'indipendenza solo per valori specifici di MalDiDenti e Prende. 
Come nel caso dell'indipendenza assoluta dell'Equazione (12.11), si possono usare anche le forme alternative

$$\bold{P}(X|Y, Z) = \bold{P}(X|Z), \ \bold{P}(Y|X, Z) = \bold{P}(Y|Z)$$

L'indipendenza assoluta permette di scomporre la distribuzione congiunta completa in parti molto più piccole. 
Lo stesso si può dire dell'indipendenza condizionata. 
Per esempio, data l'asserzione dell'Equazione (12.19), possiamo derivare una scomposizione come segue:

$$\bold{P}(\text{MalDiDenti, Prende, Carie})=\\
	= \bold{P}(\text{MalDiDenti, Prende|Carie})\bold{P}(\text{Carie}) \ \ \ (\text{regola del prodotto}) \\
	= \bold{P}(\text{MalDiDenti|Carie})\bold{P}(\text{Prende|Carie})\bold{P}(\text{Carie})   \ \ \  (\text{applicando} (12.19))$$

 

In questo modo, la grande tabella originale è scomposta in tre tabelle più piccole. 
Quella originale aveva sette numeri indipendenti (la tabella ha $2^3 = 8$ elementi, ma la loro somma deve essere $1$, perciò $7$ sono indipendenti). 
Le tabelle più piccole contengono un totale di $2 + 2 + 1 = 5$ numeri indipendenti (per una distribuzione di probabilità condizionata come $\bold{P}(\text{MalDiDenti|Carie})$ ci sono due righe di due numeri, e la somma degli elementi di ogni riga è $1$, perciò sono due numeri indipendenti; 
per una distribuzione di probabilità a priori come $\bold{P}(\text{Carie})$ c'è un solo numero indipendente). 
Passare da $7$ a $5$ potrebbe non sembrare un gran progresso, ma si possono ottenere risultati molto più significativi con un maggior numero di sintomi.

## Modelli di Bayes ingenui  
