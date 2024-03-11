# Reti di Calcolatori e Internet 
## Il nucleo della rete  

![nucleo della rete](./Screen/nucleorete.png)  
*Nella figura il "nucleo della rete" è evidenziato con linee più spesse e ombreggiate.* 

### Commutazione del pacchetto  
Le applicazioni distribuite scambiano **messaggi** che possono contenere qualsiasi cosa il progettista del protocollo desideri.  
Possono svolgere una funzione di controllo o contenere dati come in un messaggio di posta elettronica, un'immagine JPEG o un file audio MP4.  

La sorgente suddivide i messaggi lunghi in parti più piccole note come **pacchetti**. Tra la sorgente e la destinazione, questi pacchetti viaggiano attraverso collegamenti e **commutatori di pacchetto** (**router** e **commutatori a livello di collegamento**). I pacchetti vengono trasmessi su ciascun collegamento a una velocità pari alla velocità totale di trasmissione del collegamento stesso. Quindi se un sistema periferico o un commutatore invia un pacchetto di $L$ bit su un canale con velocità di $R$ bps, il tempo di trasmissione risulta pari a $L/R$ secondi.  
**Trasimssione store-and-forward**  

La maggior parte dei commutatori di pacchetto utilizza la **trasmissione store-and-forward**. Ciò significa che il commutatore deve ricevere l'intero pacchetto prima di poter cominciare a trasmettere sul collegamento in uscita il primo bit. Per capire meglio questo tipo di trasmissione si consideri la seguente rete:  

![store-and-forward](./Screen/storeandf.png)  

vi sono due host collegati attraverso un unico router. Un router è usualmente dotato di molti collegamenti; infatti il suo compito è quello di instradare un pacchetto in entrata su un collegamento in uscita.  
Iil destinatario ha ricevuto i primi due pacchetti e il router ha ricevuto il terzo. Infine al tempo $5L/R$ il destinatario ha ricevuto i tre pacchetti.  

In generale, su un percorso consistente di $N$ collegamil destinatario ha ricevuto i primi due pacchetti e il router ha ricevuto il terzo. Infine al tempo $5L/R$ il destinatario ha ricevuto i tre pacchetti.  

In generale, su un percorso consistente di $N$ collegamn questo esempio la sorgente deve inviare alla destinazione tre pacchetti, ogniuno di $L$ bit. Nella figura, la sorgente ha già trasmesso parte del pacchetto 2 in cui i primi bit sono già arrivati al router.  Il router non può trasmettere i bit che ha ricevuto in questo momento perché adotta la modalità store-and-forward; al contrario deve poter prima imagazzinare nel buffer i bit del pacchetto. Solo dopo aver ricevuto tutti i bit del pacchetto il router può iniziare a trasmettere il pacchetto sul collegamento di uscità.  
Calcoliamo ora il tempo che intercorre da quando la sorgente inizia a inviare il pacchetto a quando il destinatario lo ha completamente ricevuto. Stiamo tyrascurando il ritardo di propagazione, ossia il tempo che i bit impiegano a percorrere il collegamento a una velocità che si avvicina alla velocità della luce.  
La sorgente inizia la trasmissione al tempo 1; all'istante $L/R$ secondi ha trasmesso l'intero pacchetto e quest'ultimo è stato ricevuto e memorizzato nel router, in quando si sono trascurati i ritardi di propagazione. In tale istante, il router comincia a trasmetytere il apcchetto verso il canale di uscità verso il destinatario; all'istante $2L/R$ secondi l'intero pacchetto è stato trasmesso dal router e ricevuto dal destinatario. Quindi il ritardo totale è $2L/R$.  

Calcoliamo ora l'intervallo di tempo intercorso da quando la sorgente inizia a inviare il primo pacchetto a quando il destinatario li ha ricevuti tutti e tre.  
Al tempo $L/R$ il router inizia a inoltrare il primo pacchetto. Ma sempre al tempo $L/R$ inizia a inviare anche il secondo , oerché ha appena completato l'invio del primo pacchetto. Quindi al tempo $3L/R$ il destinatario ha ricevuto il primo pacchetto e il router ha ricevuto il secondo. Allo stesso molto al tempo $3L/R$ il destinatario ha ricevuto i primi due pacchetti e il router ha ricevuto il terzo. Infine al tempo $4L/R$ il destinatario ha ricevuto i tre pacchetti.  

In generale, su un percorso consistente di $N$ collegamenti ogniuno con velocità di trasmissione $R$. Applicando lo stesso ragioanemtno di trova che il ritardo da un capo all'altro (end-to-end).  
$d_{end-to-end} = N \frac{L}{R}$.  

**Ritardi di accodamento e perdita di pacchetti**  

Ogni commutatore di pacchetto connette più collegamenti. Per ciascuno di questi, il commutatore mantiene un **buffer di output** (detto anche **coda di output**) per conservare i pacchetti che sta per inviare su quel collegamento. I buffer di output rivestono un ruolo chiave nella commutazione di pacchetto. Un pacchetto in arrivo che debba essere inviato attraverso un collegamento occupato dalla trasmissione di un altro, deve attendere nella doda di output. Di conseguenza oltre ai ritardi di store-and-forward, i pacchetti subiscono anche **ritardi di accodamento** nei buffer di output. Dato che la dimensione del buffer è finita , un pacchetto può trovare il buffer completamento pieno di altri pacchetti che attendono la trasmissione. IN tal caso si verificherà una **perdita del pacchetto**: verrà eliminato o il pacchetto o uno di quelli in coda.  

![commutazione pacchetto](./Screen/commutazionepacchetto.png)

Nella figura i pacchetti vengono rappresentati da piccole lastre tridimensionali. Il loro spessore rappresenta il numero di bit del pacchetto.In questa figura tutti i pacchetti hanno la stessa lunghezza.  
Si supponga che gli host A e B stiano inviando pacchetti a E. Dapprima inviano i loro pacchetti lungo i collegamenti Ethernet a 100 Mbps verso il primo commutatore di pacchetto. Quest'ultimo dirige i pacchetti al collegamento da 15 Mbps. Se per un breve lasso di tempo la velocità di arrivo dei pacchetti al commutatore supera la velocità di inolto sul collegamento in uscita si verifica una congestione, in quanto i pacchetti rimangono in cosa nel buffer di output prima di venire trasmessi sul collegamento.  

**Tabelle di inoltro e protocolli di instradamento**  

Come fa il router a determinare su quale collegamento il pacchetto dovrebbe essere inoltrato? Ciò viene fatto in diversi modi a seconda del tipo di rete.  
In Internet ogni host ha un indirizzo chiamato indirizzo IP.  
Ogni pacchetto che percorre la rete contiene nella propria intestazione l'indirizzo della sua destinazione. Quando un pacchetto giunge a un router della rete, questo esamina una parte dell'indirizzo di destinazione e lo inoltra a un router adiacente. Ogni router ha una **tabella di inoltro** che mette in relazione gli indirizzi di destinazione con i collegamenti di uscità. Quando un pacchetto giunge a un router, questo esamina l'indirizzo e consulta la propria tabella per determinare un collegamento uscente appropriato. Il router dirige il pacchetto verso quel collegamento di uscità.  
Abbiamo appena appreso che un router usa l'indirizzo di destinazione del pacchetto per consultare una tabella di inoltro e determinare il collegamento di uscita corretto. Per impostare le tabelle di inoltro, Internet ha parecchi **protocolli di instradamento**. Questo può ad esempio determinare il percorso più corto da ciascun router verso ciascuna destinazione e usare questo risultato per configurare le tabelle.  

### Commutazione di circuito
Per spostare i dati in una rete di collegamenti e commutatori esistono due approcci fondamentali: la **commutazione di circuito** e la **commutazione di pacchetto**. Esaminiamo ora le prime.  
Nelle reti a commutazione di circuito le risorse richieste lungo un percorso, per consentire la comunicazione tra host, sono riservate per l'intera durata della sessione di comunicazione. Nelle reti a commutazione di pacchetto, tali risorse non sono riservate; i messaggi di una sessione utilizzano le risorse e di conseguenza potrebbero dover attendere per accedere a un collegamento.  
Le reti telefoniche sono esempi di reti a commutazione di circuito. Quando una persona vuole inviare informazioni ad un altro, prima che possa inizare l'invio, la rete deve stabilire una connessione tra mittente e destinatario. Questa connessione nel gergo della telefonia è detta **circuito**. Quando la rete stabilisce un circuito riserva una velocità di trasmissione costante nei collegamenti per la durata della comunicazione.  

![Commutazione Circuito](./Screen/commutazionecircuito.png)  

La figura mostra una rete a commutazione di circuito in cui i quattro commutatori sono interconessi tramite quattro collegamenti. Ciascuno di questi ultimi dispone di quattro circuiti, in modo che ogni collegamento possa supportare quattro connessioni simultanee. Gli host sono tutti direttamente connessi a uno dei commutatori. Quando due host desiderano comunicare la rete stabilisce una **connessione end-to-end** (**punto a punto**) dedicata a loro.  
Affinché A invii messaggi a B, la rete deve prima riservare un circuitosu ciascuno dei due collegamenti. Nell'esempio la connessione punto a punto usa iil secondo circuito del primo collegamento e il quarto circuito del secondo. Poiché ogni collegamento ospita quattro circuiti, per ogni collegamento utilizzato dalla connessione punto a punto la connessione ottiene un quarto della capacità trasmissiva totale del collegamento per la durata della connessione stessa.  

**Multiplexing nelle reti a commutazione di circuito**  

Un circuito all'interno di un collegamento è implementato tramite **multiplexing a divisione di frequenza** (**FDM**) o **multiplexing a divisione di tempo** (**TDM**). Con FDM, lo spettro di frequenza di un collegamento viene suddiviso tra le connessioni stabilite tramite il collegamento. Nello specifico il collegamento dedica una banda di frequenza a ciascuna connessione per la durata della connessione stessa. La larghezza della banda viene detta **ampiezza della banda**.  
Per un collegamento TDM il tempo viene suddiviso in frame di durata fissa a loro volta ripartiti in un numero fisso di slot temporali. Quando la rete stabilisce una connessione attraverso un collegamento, le deidca uno slot di tempo in ogni frame. Tali slot sono dedicati unicamente a quella connessione, con uno slot temporale disponibile in ciascun frame alla trasmissione dei dati di connessione.  

![FDM TDM](./Screen/FDMTDM.png)  

La figura mostra FDM e TDM per uno specifico collegamento di rete che supporta fino a quattro circuiti. Nel caso di FDM il dominio delle frequenze viene ripartito in quattro bande, ciascuno con ampiezza di 4kHz.  
Nel caso di TDM, il dominio del tempo viene suddiviso in frame, con quattro slot di tempo per ciascun intervallo; a ogni circuito viene assegnato lo stesso slot dedicato in tutti i frame.
Nel caso TDM la velocità di trasmissione di un circuito è uguale alla frequenza di frame moltiplicata per il numero di bit in uno slot. Per esempio se il collegamento trasmette 8000 frame al secondo e ogni slot è costituito da 8 bit, allora la velocità di trasmissione del circuito è di 64 Kbps.  

I sostenitori della commutazione di pacchetto, hanno sempre sostenuto che la commutazione di circuito fosse dispendiosa, dato che i circuiti dedicati sono inattivi durante i **periodi di silenzio**. Un esempio sta nel considerare un radiologo che usa una rete a commutazione di circuito per accedere in remoto a una serie di lastre. IL radiologo predispone una connessione, richiede un'immagine, la osserva e poi ne richiede un'altra. Per tutto il tempo in cui il radiologo guarda le lastre, le risorse di rete vengono sprecate.  
I sostenitori della commutazione di pacchetto tendono anche a sottolineare la complicazione insita nello stabilire circuiti e nel riservare la larghezza di banda punto a punto. Ciò richiede un software complesso.  
**Confronto tra commutazione di pacchetto e commutazione di circuito**  
Perché la commutazione di pacchetto risulta più efficiente? Usiamo un esempio.  Si supponga che gli utenti condividano un collegamento da 1Mbps e che ciascun utente alterni periodi di attività, in cui genera dati a una velocità costante di 100 Kbps, a momenti durante i quali non veongono generati dati. Si ipotizzi che l'utente sia attivo solo per il 10% del tempo. Con la commutazione di circuito è necessario *riservare* 100 kbps per ciascun utente in ogni istante. Per esempio nel caso TDM a commutazione di circuito, se un frame di un secondo viene diviso in 10 slot da 100 ms, ciascun utente si vedrebbe allocato uno slot per frame.  Pertanto il collegamento può supportare simultaneamente solo 10 (=1Mbps/100 kbps) utenti.  
Con la commutazione di pacchetto la probabilità che un determinato utente sia attivo è pari a 0,1. Se sono presenti 35 utenti, la probabilità di avere 11 o più utenti attivi in contemporanea è circa 0,0004. Un altro esempio. Si supponga la presenza di 10 utenti, e che un utente improvvisamente generi 1000 pacchetti da 1000 bit, mentre gli altri rimangono inattivi senza generare traffico. Con la commutazione di circuito TDM e 10 slto da 1000 bit per frame, l'utente attivo può utilizzare soltanto il proprio slot temporale per trasmettere dati, mentre i restnati nove slot del frame rimangono inutilizzati. Trascorreranno dieci secondi prima che il milione di bit dell'utente attivo sia stato completamente trasmesso.  
Nel caso di commutazione di pacchetto, l'utente può continuamente inviare i propri pacchetti alla massima velocità di collegamento, dato che nessun altro utente genera pacchetti che richiedono di essere trasmessi assieme a quelli dell'utente attivo.  

Gli esempi riportati sottolineano la curciale differenza tra le due forme di condivisione della velocità trasmissiva di un collegamento su più flussi di dati. La commutazione di circuito prealloca l'uso dek collegamento trasmissivo indipendentemente dalla richiesta, con collegamenti garantiti, na non utilizzati, che provocano il dispendio di tempo. La commutazione di pacchetto alloca l'uso di collegamenti su *richiesta*. Pacchetto per pacchetto, la capacità trasmissiva dei collegamenti sarà condivisa solo tra gli utenti che devono trasmettere.  

### Una rete di reti

La connessione degli utenti e dei fornitori di contenuti alla rete di un ISP è solo una piccola parte del puzzle daa risolvere per connettere i  miliardi di utenti. Per completare il puzzle bisogna interconnettere gli ISP attraverso una **rete di reti**.  

