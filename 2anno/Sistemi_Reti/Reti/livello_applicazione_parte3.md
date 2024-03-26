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

+ **Mail server aliasing** 
