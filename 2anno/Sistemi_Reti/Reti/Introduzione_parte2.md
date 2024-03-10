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
