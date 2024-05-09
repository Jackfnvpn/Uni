# Livello di rete  
Trasporta i segmenti dall'host mittente all'host destinatario:
+ **Mittente**: incapsula i segmenti dentro ai datagrammi che passa al livello di collegamento  
+ **Destinatario**: consegna i segmenti a livello di trasporto  

I protocolli a livello di rete sono implementati da tutti i dispositivi in Internet: host, router.  

> [!NOTE]  
> Un router esamina i campi dell'intestazione di tutti i datagrammi IP che lo attraversano.  
> Sposta i datagrammi dalle porte di ingresso alla porta di uscita per trasferire il datagramma lungo il percorso dall'host di origine a quello di destinazione  

## Inoltro e instradamento
Il ruolo principale del livello di rete è quello di trasferire pacchetti da un host ad un altro. Per fare questo è importante identificare due importanti funzioni:
+ **Inoltro**: trasferisce i pacchetti da un collegamento di ingresso di un router al collegamento di uscita appropriato del router  
+ **Instradamento**: determina il percorso seguito dai pacchetti dall'origine alla destinazione, attraverso **algoritmi di instradamento**.  

Per inoltrare i pacchetti, i router estraggono da uno o più campi nell'intestazione i loro valori che utilizzano come indice nella **tabella di inoltro** , un elemento chiave di qualsiasi router.  

### Piano di controllo: l'approccio tradizionale  













