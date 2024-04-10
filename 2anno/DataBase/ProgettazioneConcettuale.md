# Progettazione Concettuale  
## Rappresentazione concettuale dei dati  
### Criteri generali di rappresentazione  
Nella progettazione concettuale conviene seguire le "regole concettuali" del modello E-R.  

+ *Se un concetto ha proprietà significative e/o descrive classi di oggetti con esistenza autonoma, è opportuno rappresentarlo con una entità*.
+ *Se un concetto ha una struttura semplice e non possiede proprietà rilevanti associate, è opportuno rappresentarlo con un attributo di un altro concetto a cui si riferisce*  
+ *Se sono state individuate due (o più) entità e nei requisiti compare un concetto che le associa, questo concetto può essere rappresentato da una relazione*  
+ *Se uno o più concetti risultano essere casi particolari di un altro, è opportuno rappresentarli facendo uso di una generalizzazione*.  

## Strategie di progetto   
### Strategia top-down  
In questa stragia lo schema concettuale viene prodotto mediante una serie di raffinamenti successivi a partire da uno schema iniziale che descrive tutte le specifiche con pochi concetti molto astratti. Lo schema viene poi via via raffinato mediante trasformazioni che aumentano il dettaglio dei vari concetti presenti:  

![top-down](./Screen/top-down.png)  

In figura vengono rappresentati i diversi piani di raffinamento del processo: ogniuno di questi piani contiene uno schema che descrive le medesime informazioni a un diverso livello di dettaglio.   
Nel passaggio da un livello di raffinamento a un altro, lo schema viene modificato facendo uso di alcune trasformazioni elementari che vengono denominate *primitive di trasformazione top-down*  

Esempi di queste sono:  
+ La definizione degli attributi di una entità o di una relazione;  
+ La reificazione di un attributo o di una entità;  
+ La decomposizione di una relazione in due relazioni distinte  
+ La trasformazione di una entità in una gerarchia di generalizzazione.  

Il vantaggio della strategia top-down è che il progettista può inizialmente descrivere le specifiche dei dati trascurandone i dettagli, per poi entrare nel merito.  

### Strategia bottom-up  

In questa strategia, le specifiche iniziali sono suddivise in componenti via via sempre più piccole, fino a quando queste componenti descrivono un frammento elementare della realtà di interesse. A questo punto, le varie componenti vengono rappresentate da semplici schemi concettuali che possono consistere anche in singoli concetti. I vari schemi così ottenuti vengono poi fusi fino a giungere, attraverso una completa integrazione di tutte le componenti,allo schema concettuale finale.  

![bottom-up](./Screen/bottom_up.png)  

La fase di decomposizione delle specifiche, la successiva fase di rappresentazione delle componenti di base e la fase finale d'integrazione degli schemi elementari. A differenza della strategia top-down, con questa strategia i vari concetti presenti nello schema finale vengono via via introdotti durante le varie fasi. Anche in questo caso , lo schema finale si ottiene attraverso alcune trasformazioni elementari che vengono denominate *primitive di trasformazioni bottom-up* che introducono in uno schema nuovi concetti non presenti precedentemente e in grado di descrivere aspetti della realtà di interesse che non erano ancora stati rappresentati.  
Esempi di questi:  
+ 

