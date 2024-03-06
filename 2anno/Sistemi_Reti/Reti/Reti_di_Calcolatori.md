# Reti di calcolatori
## Che cos'è Internet?
Cos'è Internet? Esistono due modi di rispondere a questa domanda. La prima è descrivere gli "ingranaggi" di internet, ossia i componenti di  base hardware e software che la compongono. Un altro metodo consiste nel descrivere Internet in termini di infrastruttura di rete che fornisce servizi ad applicazioni distribuite.

### Gli "ingranaggi" di Internet
Internet è una rete di calcolatori che interconnette miliardi di dispositivi di calcolo in tutto il mondo. Questi dispositivi, in gergo, sono detti **host** o **sistemi periferici**.  
I sistemi periferici sono connessi tra loro tramite una **rete di collegamenti** e **commutatori di pacchetti**. Collegamenti diversi possono trasmettere dati a velocità differenti, e tale **tasso di trasmissione** viene misurata in bit/secondo (*bps*).  
![Componenti di base di Internet](./Screen/componenti_internet.png)  
Quando un Host vuole inviare dati ad un altro, suddivide i dati in sottoparti e aggiunge un'intestazione a ciascuna di esse: l'insieme delle informazioni risultanti , viene chiamato **pacchetto**.  
I pacchetti sono inviati attraverso la rete alla destinazione, dove vengono riassemblati per ottenere i dati originari. Un commutatore di pacchetto prende un pacchetto che arriva da uno dei collegamenti in ingresso e lo ritrasmette su uno di quelli in uscita. In internet i due principali sono i **router** e i **commutatori a livello di collegamento**. Entrambi instradano i pacchetti verso la loro destinazione finale. I commutatori a livello di collegamento sono solitamente usati nelle reti di accesso , mentre i router nel nucleo della rete. Dal sistema di invio a quello di ricezione, la sequenza di collegamenti e di commutatori di pacchetto attraversata dal singolo pacchetto è nota come **percorso**.  
I sistemi periferici accedono a Internet tramite i **Internet Service Provider** (**ISP**) che comprendono ISP residenziali, ISP aziendali, ISP universitari, ISP che forniscono accesso WiFi in aereoporti, hotel, bar e altri luoghi pubblici e ISP che forniscono accesso in mobilità.  
Un provider è un insieme di commutatori di pacchetto e di collegamenti.Gli ISP forniscono ai sistemi periferici svariati tipo di accesso alla rete. Inoltre rendono disponibile l'accesso a Internet ai fornitori di servizi, connettendone i server a Internet. Gli ISP devono essere interconnessi.  

Sistemi periferici, commutatori di pacchetto e altre parti di Internet fanno uso di protocolli che controllano l'invio e la ricezione di informazioni all'interno della rete. Due dei principali protocolli Internet sono: **Transmission Control Protocol** (**TCP**), e l'**Internet Protocol** (**IP**). Quest'ultimo specifica il formato dei pacchetti scambiati tra router e sistemi periferici.  
Data la loro importanza per Internet, un accordo sulle funzioni svolte da ogni protocollo risulta fondamentale, qui entrano in gioco gli standard. Gli **standard di Internet** vengono sviluppati dall'**Internet Enginnering Task Force** (**IETF**). Le pubblicazioni sugli standard di internet vengono dette **Request For Comment** (**RFC**).
### Descrizione dei servizi 
Possiamo descrivere come un'infrastruttura che fornisce servizi alle applicazioni (posta elettronica, messaggistica istantanea, navigazione WEB, ...). Queste applicazioni sono dette **applicazioni distribuite**, in quanto coinvolgono più sistemi periferici che si scambiano reciprocamente dati. L'aspetto più rilevante è che le applicazioni Internet vengono eseguite sui sistemi periferici e non sui commutatori di paccheto del nucleo della rete, questo perché essi non hanno a che fae con le applicazioni che sono sorgente e destinazione dei dati.  

Ma come fa una parte di applicazione eseguita su un sistema periferico a istruire Internet affinché recapiti dati a un'altra parte di software eseguita su un altro sistema periferico?  
I sistemi periferici collegati a Internet forniscono una **interfaccia socket**, che specifica come un programma eseguito su un sistema periferico possa chiedere  internet di recapitare dati a un programma eseguito su un altro sistema periferico.  
### Che cos'è un protocollo?
**Un'analogia**  

![protocollo Umano Protocollo di rete](./Screen/diff_prot.png)  

Come in figura, il protocollo umano impone come prima cosa un saluto per iniziare la comunicazione con qualcun'altro. La tipica risposta è un messaggio di saluto di ritorno. Implicitamente, una persona interpreta una risposta cordiale come "Ciao" come l'indicazione di poter procedere a chidere l'ora. Una risposta diversa potrebbe indicare come una scarsa propensione a comunicare o l'incapacità di farlo.  
Se le persone adottano protocolli differenti (per esempio se una persona è bene educata e l'altra no, o se una delle due comprende il concetto di tempo e l'altra no) i protocolli non interoperano e non è possibile portare a termine una transazione utile.   
Lo stesso concetto vale per le reti. Lo scambio si appoggia a due (o più) entità che comunicano utilizzando lo stesso protocollo al fine di assolvere un certo compito.  

**Protocolli di rete**  

Un protocollo di rete è simile a un protocollo umano, a eccezione del fatto che le entità che si scambiano messaggi e che intraprendono azioni sono componenti hardware o software di qualche dispositivo.  
Qualsiasi attività in Internet che coinvolga due o più entità remote in comunicazione viene governata da un protocollo.  
Come esempio di protocollo si consideri che succede quando si invia una richiesta a un web server, ossia quando si digita l'indirizzo di una pagina web in un browser.  
Come nella figura precedente (parte destra), per prima cosa il computer invierà un messaggio di richiesta di connessione al web server e si metterà in attesa di un messaggio di risposta. Il web server alla fine riceverà il messaggio di richiesta di connessione e restituirà un messaggio di risposta di connessione. Sapendo che ora è possibile richiedere un documento web, il computer invierà il nome della pagina che vuole prelevare dal server tramite un messaggio GET. Infine il web server restituirà la pagine web (file) al computer.  

*Un* ***protocollo*** *definisce il formato e l'ordine dei messaggi scambiati tra due o più entità in comunicazione, così come le azioni intraprese in fase di trasmissione e/O di ricezione di un messaggio o di un altro evento*.

