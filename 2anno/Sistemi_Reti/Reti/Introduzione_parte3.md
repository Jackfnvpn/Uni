# Reti di calcolatori e Internet
## Livelli dei protocolli e loro modelli di servizio  
### Stratificazione dei protocolli  
Per dare struttura alla progettazione dei protocolli, i progettisti organizzano i protocolli e l'hardware e software che li implementano in **livelli** o **stati**.  
Ciascun protcollo appartiene a uno dei livelli. Siamo interessati ai **servizi** offerti da un livello a quello superiore: il **modello di serviio** di un livello.  
Ogni livello fornisce il suo servizio effetuando determinate azioni all'interno del livello stesso e utilizzando i servizi del livello subito inferiore.  
Per esmepio, i servizi offerti dal livello $n$ possono includere la consegna affidabile dei messaggi da un lato della rete all'altro. Ciò può essere implementato utilizzando un servizio fornito dal livello $n-1$- e aggiungendo qualcosa a livello $n$.  
Un livello di protocolli può essere implementato via software, hardware o misto.  

La stratificazione dei protocolli presenta vantaggi concettuali e strutturali. Fornisce un modo strutturto per trattare i componenti dei sistemi.  
La modularità rende più facile aggiornare le componenti.  
Un eventuale svantaggio è la possibilità che un livello duplichi le funzionalità di quello inferiore.  
Un secondo possibile svantaggio è che una funzionalità a un livello possa richiedere informazioni presenti solo in un altro livello.

Considerati assieme, i protocolli dei vari livelli sono detti **pila di protocolli**. La pila di protocolli di Internet 


