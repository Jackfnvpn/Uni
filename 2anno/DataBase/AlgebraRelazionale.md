# ALGEBRA RELAZIONALE
L'algebra relazionale è costituita da un insieme di operatori, definiti su relazioni che producono ancora relazioni come risultati. In questi modo è possibile costruire espressioni che coinvolgono più operatori, allo scopo di formulare interrogazioni anche complesse.  
## Unione, intersezione, differenza  
Per iniziare, notiamo che le relazioni sono insiemi, e quindi ha senso definire su di esse operatori insiemistici tradizionali di unione, differenza e intersezione.  
Dobbiamo prestare attenzione al fatto che una relazione non è genericamente un insieme di tuple, ma un insieme di tuple *omogenee*, cioè definite sugli stessi attributi. Dunque non ha senso, nel modello relazionale, definirli con riferimento a relazioni su attributi diversi.  
Pertanto consideriamo ammissibili solo applicazioni degli operatori di unione, intersezione e differenza a coppie di operandi definiti sugli stessi attributi.  

![Operazioni di Unione, Intersezione, Differenza](./Screen/operazioni_insiemistiche.png)  
## Ridenominazione
![Unione sensata ma scorretta](./Screen/unione_sensata.png)  

La limitazione imposta negli operatori insiemistici, pur giustificata, risulta però particolarmente pesante. Consideriamo la figura: sarebbe sentato eseguire su di esse una sorta di unione, al fine di ottenere tutte le coppie "genitore-figlio", ma ciò non è possibile perché l'attributo intuitivamente indicato **Genitore** si chiama **Padre** in una relazione e **Madre** nell'altra.  
Per risolvere il problema, introduciamo un operatore, che ha come unico obiettivo quello di adeguare i nomi degli attributi. L'operatore è detto *ridenominazione*.  

![Una ridenominazione](./Screen/ridenominazione.png)  

Nella figura vediamo bene come, fra operando e risultato, cambi solo l'intestazione, mentre il corpo rimane invariato. Infatti la ridenominazione agisce solo sullo schema, nell'esempio cambiando il nome dell'attributo **Padre** in **Genitore** , come indicato dalla notazione
**Genitore $\leftarrow$ Padre** posta a pedice del simbolo $\rho$ che denota l'operatore di ridenominazione.  
![Unione preceduta da due ridenominazioni](./Screen/unione_due_ridenominazione.png)  

![Altra unione preceduta da due ridenominazioni](./Screen/other_due_rid.png)  
## Selezione 
Passiamo

