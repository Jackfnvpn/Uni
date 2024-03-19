# Livello di applicazione  
## Web e HTTP  
### Interazione utente-server: i cookie  
Abbiamo visto che i server HTTP sono privi di stato. Ciò semplica la progettazione e consente di sviluppare web server ad alte prestazioni. Tuttavia, è spesso auspicabile che i web server possano autenticare gli utenti, si per limitare l'accesso da parte di questi ultimi sia per fornire contenuti in funzione della loro identità.  A questo scopo HTTP usa i **cookie**. I cookie consentono ai server di tener traccia degli utenti. La maggior parte dei siti commerciali usa i cookie.  

![Cookie](./Screen/cookie.png)  

Come mostrato in figura, la tecnologia dei cookie presenta quattro componenti:  

1. Una riga dìintestazione nel messaggio di risposta HTTP  
2. Una riga di intestazione nel messaggio di richiesta HTTP  
3. Un file mantenuto sul sistema dell'utente e gestito dal browser  
4. Un database sul sito.  

Supponiamo che Susan, che accede sempre al WEB con Internet Explorer dal proprio PC, contatti per la prima volta Amazon.com. Supponiamo inoltre che in passato abbia visistato il sito di eBay. Quando giunge la richiesta al web server di Amazon, il sito crea un identificativo unico e una voce nel proprio daabse, indicizzata dal numero identificativo.  
A questo punto il server risponde al browser di Susan, includendo nella risposta HTTP l'intestazione ```Set-cookie:``` che contiene il numero identificativo.  
```Set-cookie: 1678```  

Quando il browser di Susan riceve il messaggio di risposta HTTP, vede l'intestazione ```Set-cookie:```. Il browser allora aggiunge una riga al flie dei cookie che gestisce. Questa riga include il nome dell'host del server e il numero identificativo nell'intestazione ```Set-cookie```  . Si noti che il file di cookie contiene già una voce per eBay.  
Mentre Susan continua a navigare nel sito di Amazon, ogni volta che richiede una pagina web, il suo browser consulta il suo file dei cookie, estrae il suo numero identificativo per il sito e pone nella richiesta HTTP una riga d'intestazione del cookie che include tale numero.  
```Cookie: 1678```.  
In tal modo è possibile monitorare l'attività di Susan nel sito.  

I cookie possono essere usati per identificare gli utenti. La prima volta che visita un sito, un utente può fornire un identificazione. Successivamente il browser passa un'intestazione cookie al server durante tutte le successive visite al sito, identificando quindi l'utente al server.  I cookie possono essere usati per creare un livello di sessione utente al di sopra di HTTP che è privo di stato. Per esempio, quando un utente si identifica in un'applicazione di posta elettronica, il browser invia le informazioni del cookie al server, permettendo a questo di identificare l'utente attraverso la sessione utente dell'applicazione.  

### Web caching  

Una **web cache**, nota anche come **proxy server**, è un'entità di rete che soddisfa richieste HTTP al posto del web server effettivo.  
Il proxy ha una propria memoria sul disco (cache) in cui conserva copie di oggetti recentemente richiesti.  

![Web cache](./Screen/proxyserver.png)  

Come in figura, il browser di un utente può essere configurato in modo che tutte le richieste HTTP dell'utente vengano innanzitutto dirette al proxy server. Una volta configurato il browser, ogni richiesta di oggetto da parte del browser viene inizialmente diretta al proxy.  
Supponiamo che per esempio un browser stia richiedendo l'oggetto  
```http://www.someschool.edu/campus.gif```:  

1. Il browser stabilisce una connessione TCP con il proxy server e invia una richiesta HTTP per l'oggetto specificato  
2. Il proxy controlla la presenza di una copia dell'oggetto memorizzata localmente. Se l'oggetto viene ilevato, il proxy lo inoltra all'interno di n messaggio di risposta HTTP  
3. Altrimenti il proxy apre una connessione TCP verso il server di origine. Quindi il proxy invia una richiest HTTP per l'oggetto. Una volta ricevuta tale richiesta, il server d'origine invia al proxy l'oggetto all'interno di una risposta HTTP  
4. Quando il proxy riceve l'oggetto, ne salva una copia nella propria memoria locale e ne inoltra un'altra copia, all'interno di un messaggio di risposta HTTP al browser.  

Il web caching si è sviluppato in Internet per due ragioni. Innanzitutto, un proxy può ridure i tempi di risposta alle richieste dei client, in particolare se l'ampiezza di banda tra il client e il server d'origine è molto inferiore rispetto all'ampiezza di banda minima tra client e proxy. In secondo luogo, i proxy possono ridurre il traffico sul collegamento di accesso a Internet. Possono ridurre in modo sostanziale il traffico globale del Web in Internet, migliorando di conseguenza le prestazioni di tutte le applicazioni.  


