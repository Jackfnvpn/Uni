# Livello Applicazione

Gli host Internet possono essere identificati in vari modi. Gli **hostname** quali ```www.facebook.com, www.google.com```, risultano abbastanza appropriati per le persone ma forniscono poca informazione quanto la loro locazione in Internet.  
Inoltre dato che questi nomi sono costituiti da un numero variabile di caratteri alfanumerici, sono abbastanza difficili da elaborare per il server. Per questo gli host vengono identificati con gli **indirizzi IP**.  
Questo consiste di quattro byte e presenta una rigida struttura gerarchica.  
Ha questa forma: ```127.7.106.83``` in cui ogni punto separa un byte espressi con un decimale che va da 0 a 256.  

## Servizi forniti dai DNS  
Esistono dunque due modi per identificare gli host: nomi e indirizzi IP. Al fine di coinciliare i due approcci è necessario un servizio in grado di tradurre gli hostname nei loro indirizzi IP, compito del **DNS** (*Domain name system*).  
DNS è $(1)$ un database distribuito implementato in una gerarchia **DNS server** e $(2)$ un protocollo a livello di applicazione che consente di interrogare il database.  
Il protocollo DNS usa UDP e la porta 53.  
Consideriamo che cosa succede quando un browser in esecuzione sull'host di un utente richiede l'URL ```www.someschool.edu/index.html```. L'host dell'utente, per essere in grado di inviare un messaggio di richiesta HTTP al web server ```www.someschool.edu```, deve come prima cosa ottenere il suo indirizzo IP:  

1.  La stessa macchina utente esegue il lato client dell'applicazione DNS.  
2. Il browser estrae il nome dell'host ```www.someschool.edu ```, dall'URL e lo passa al lato client dell'applicazione DNS.  
3. Il client DNS invia una interrogazione (query) contenente l'host name a un DNS server  
4. Il client DNS prima o poi riceve una risposta, che include l'indirizzo IP corrispondente all'hostname  
5. Una volta ricevuto l'indirizzo IP dal DNS, il browser può dare inizio a una connessione TCP verso il processo server HTTP collegato alla porta 80 di quell'indirizzo IP.  

DNS offre ulteriori servizi:  

+ **Host aliasing** Un host dal nome complicato (**host canonico**) può avere uno o più sinonimi (*alias*).  
Il DNS può essere invocato da un'applicazione per ottenere l'host canonico di un sinonimo.  

+ **Mail server aliasing** Gli indirizzi email sono facili da ricordare. Per esempio, se Bob ha un account Hotmail, il suo indirizzo di posta elettronica potrebbe essere semplicemente ```bob@yahoo.com``` tuttavia l'hostname potrebbe essere più complicato. Un'applicazione può invocare il DNS per ottenere il nome canonico di un sinonimo fornito come l'indirizzo IP dell'host.  

+ **Distribuzione del carico di rete** Il DNS viene anche utilizzato per distribuire il carico tra server replicati, per esempio dei web server. I siti con molto traffico vengono replicati su più server , ogniuno eseguito su un host diverso con indirizzo IP differente.  
Nel caso du web server replicati, va dunque associato a ogni hostname canonico un insieme di indirizzi IP. Il database DNS contiene questo insieme di indirizzi. Quando i clienti effettuano una query DNS per un nome associato a un insieme di indirizzi, il server risponde con l'intero insieme d'indirizzi ma ne varia l'ordinamento a ogni risposta. Dato che generalmente un client invia il suo messaggio di richiesta HTTP al primo indirizzo IP elencato nell'insieme, la rotazione DNS distribuisce il traffico su server replicati.  

## Funzionamento DNS  
Supponiamo che un'applicazione in esecuzione sull'host di un utente abbia necessità di tradurre un hostname in un indirizzo IP. L'applicazione invocherà il lato client del DNS specificando l'hostname da tradurre.  
Su molte macchine UNIX, ```gethostbyname()``` è la chiamata di funzione effettuata da un'applicazione per ottenere il servizio di traduzione.  
Il DNS sull'host invia un messaggio (*query*) sulla rete. Tutte le query DNS e i messaggi di risposta vengono inviati dentro datagrammi UDP diretti alla porta 53. Dopo un ritardo che varia dai millisecondi ai secondi , il client DNS sull'host dell'utente riceve un messaggio di risposta contenente la corrispondenza desiderata, che viene poi passata all'applicazione che ne ha fatto richiesta. Un DNS è costituito da un grande numero di DNS server distribuiti per il mondo e da un protocollo a livello di applicazione che specifica la comunicazione tra DNS server e host richiedenti.  
Un primo approccio potrebbe essere quello di usare un DNS server contenente tutte le corrispondenze. Con un sistema centralizzato. Questo schema presenta dei problemi:  

+ **Un solo point of failure** Se il DNS si guasta, soffre l'intera Internet.  

+ **Volume di traffico** Un singolo DNS server dovrebbe gestire tutte le richieste.  

+ **Database centralizzato distante** Un singolo DNS server non può essere vicino a tutti clienti. Potrebbero esserci dunque pesanti ritardi.  
+ **Manutenzione** Il singolo DNS server dovrebbe contenente record relativi tutti gli host Internet. Un tale server centralizzato non solo sarebbe vasto ma dovrebbe essere aggiornato frequentemente.  

### Un database distribuito e gerarchico  

Per trattare il problema della sequenzialità , il DNS utilizza un grande numero di server, organizzati in maniera gerarchica e distribuiti nel mondo. Esistono tre classi di DNS server: **root server, top-level domain (TLD), server autoritativi** organizzati in una gerarchia.  

![gerarchia dns](./Screen/Ger_DNS.png)  

+ **root server**. In Internet esistono più di 1000 root server, dislocati in tutto il mondo. Forniscono gli indirizzi IP dei TLD server.

+ **TLD server**. Si occupano dei domini di primo livello quali com, org, net, edu e gov e di tutti i domini di primo livello relativi ai vari paesi come it, uk, fr, ca e jp. Forniscono gli indirizzi IP dei server autoritativi.  
+ **server autoritativi**. Ogni organizzazione dotata di host pubblicamente accessibili tramite Internet deve fornire record DNS pubblicamente accessibili che associno tali host a indirizzi IP. Il DNS server autoritativo ospita questi record.  

Esiste un altro importante DNS, il **DNS server locale**, che non appartiene strettamente alla gerarchia di server, ma che è comunque centrale nell'architettura DNS. Ciascun ISP (università, ISP residenziale,...) ha un DNS server locale. Quando un host si connette a un ISP, quest'ultimo gli fornisce un indirizzo IP tratto da uno o più dei suoi DNS server locali.   
Quando un host effettua una richiesta DNS, la query viene inviata al DNS server locale, che opera da proxy e inoltra la query alla gerarchia dei DNS server.  

Vediamo un esempio: Supponiamo che l'host ```cse.nyu.edu``` voglia l'indirizzo IP di ```gaia.cs.umass.edu```. Supponiamo che inoltre il DNS server locale per ```cse.nyu.edu``` sia ```dns.nyu.edu```, mentre un server autoritativo per ```gaia.cs.umass.edu``` sia ```dns.umass.edu```.  

![Iterazione DNS](./Screen/Int_DNS.png)  
l'host ```cse.nyu.edu``` dapprima invia un messaggio di richiesta DNS al proprio server locale ```dns.nyu.edu```. Il messaggio contiene il nome da tradurre (```gaia.cs.umass.edu```). Il server locale inoltra il messaggio di richiesta a un root server. Quest'ultimo prende nota del suffisso ```edu``` e restituisce al server locale un elenco di indirizzi IP per i TLD server responsabili di ```edu```. Il server locale rinvia quindi il messaggio di richiesta a uno di questi ultimi. Il TLD server prende nota del suffisso ```umass.edu``` e risponde con l'indirizzo IP del server autoritativo per l'università del Massachusetts, ```dns.umass.edu```. Infine il server locale rimanda il messaggio di richiesta direttamente a ```dns.umass.edu```, che risponde con l'indirizzo di ```gaia.cs.umass.edu```.  
Può capitare che un TLD conosca un DNS server intermedio, il quale conosce il server autoritativo relativo all'hostname.  

![QUery ricorsive](./Screen/querric_DNS.png)  

L'esempio in figura, fa uso sia di **query ricorsive** che **iterative**. La richiesta inviata da ```cse.nyu.edu``` a ```dns.nyu.edu``` è ricorsiva, in quanto richiede a ```dns.nyu.edu``` di ottenere l'associazione per conto del richiedente. Le successive tre richieste sono iterative , dato che tutte le risposte sono restituite direttamente a ```dns.nyu.edu```  

### DNS caching  
L'idea alla base del DNS caching è molto semplice. In una concatenazione di richieste , il DNS server che riceve una risposta DNS, può mettere in cache le informazioni contenute.  
Per esempio ogni volta che il server locale ```dns.nyu.edu``` riceve una risposta da qualche DNS server, può conservare in cache le informazioni contenute nella risposta. Se una coppia hostname/indirizzo IP è nella cache di un DNS server e giunge al server un'altra richiesta con lo  stesso hostname, il DNS server può fornire indirizzo IP desiderato. Gli host e le associazioni tra nome e indirizzo IP non sono in alcun modo permanenti, i DNS server invalidano le informazioni in cache dopo un certo periodo di tempo.  
Un DNS può inoltre, memorizzare in cache gli indirizzi IP dei TLD server, consentendogli di aggirare i root server nella catena di richieste.  
## Record e messaggi DNS  
I server che implementano il database distribuito DNS memorizzano i cosidetti **record di risorsa**, tra cui quelli che forniscono le corrispondenze tra nomi e indirizzi.  
Ogni messaggio di risposta DNS trasporta uno o più record di risorse.  

Un record di risorsa contiene i seguenti campi:  
 *(Name, Value, Type, TTL)*  

TTL è il time to live, ossia il tempo residuo di vita di un record e determina quando una risorsa vada rimossa dalla cache. Il significato di name e value dipende da type:  

+ *Type=A*: allora name è il nome dell'host e value il suo indirizzo IP (relay1.bar.foo.com, 145.37.93.126, A).  
+ *Type=NS*: allora name è un dominio e value è l'hostname del DNS server autoritativo che sa come ottenere gli indirizzi IP degli host nel dominio (foo.com, dns.foo.com,NS).  
+ *Type: CNAME* allora value rappresenta il nome canonico dell'host per il sinonimo name (foo.com, relay1.bar.foo.com,CNAME).  
+ *Type:MX* allora value è il nome canonico di un mail server che ha il sinonimo name. (foo.com, mail.bar.foo.com,MX). Questo tipo di record consente agli hostname dei mail server di avere sinonimi semplici.  

### Messaggi DNS   
Abbiamo fatto riferimento agli unici due tipi di messaggio DNS: le query e i messaggi di risposta che presentano lo stesso formato:  

![Formato messaggi dns](./Screen/formatomex_DNS.png)  

La semantica dei campi è la seguente:  

+ I primi 12 byte rappresentano la *sezione di intestazione*, che a sua volta contiene un certo numero di campi. Il primo è un numero di 16 bit che identifica la richiesta. Tale identificatore viene copiato nei messaggi di risposta a una richiesta, consentendo al client di far corrispondere le risposte ricevute con le query inviate.  
Troviamo poi il campo *flag*. Il primo di questi il bit di *richiesta/risposta*, indica se il messaggio è una richiesta (0) o una risposta (1)Un bit viene impostato nei messaggi di risposta quando il DNS server è autoritativo per il nome richiesto. Un ulteiore bit, chiamato **richiesta di ricorsione**, viene impostato quando un client desideri che il DNS server effettui una ricorsione quando non dispone del record.  
Si imposta un campo da 1 bit di ricorsione disponibile all'interno di una risposta se il DNS server supporta la ricorsione.  
Troviamo poi 4 ulteiori campi che indicano il numero di occorenze delle quattro sezioni di tipo dati che seguono l'intestazione.  
+ La *sezione delle domande* contiene informazioni sulle richieste che stanno per essere effettuate. Inoltre, include (1) un campo nome con il nome che sta per essere richiesto, e (2) un campo tipo che indica il tipo della domanda (A, MX).  
+ La *sezione delle risposte* contiene i record di risorsa relativi al nome originariamente richiesto. Una risposta può restituire più RR, dato che un hostname può avere più indirizzi IP (server replicati).  
+ La *sezione autoritativa* contiene i record di altri server autoritativi.  
+ La *sezione aggiuntiva* racchiude altri record: per esempio se il campo di risposta relativo a una richiesta MX contiene un record di risorsa che fornisce l'hostname canonico del server di posta, la sezione aggiuntiva contiene un record di tipo A che fornisce l'indirizzo IP relativo all'hostname canonico del server di posta.  

### Inserimento di record nel database DNS  
Supponiamo di aver creato un'azienda chiamata Network Utopia.  
La prima cosa che sicuramente desiderate è far registrare il nome di dominio ```networkutopia.com``` presso un ente di registrazione (**registrar**), un'azienda che verifica l'unicità del nome di dominio , lo inserisce nel database DNS e vi richiede una piccola somma di denaro.  
