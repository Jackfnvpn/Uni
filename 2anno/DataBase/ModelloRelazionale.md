

# Modello Relazionale 
## Modello relazionale: strutture
### Modelli logici nei sistemi di basi di dati 
 Il *modello relazionale* si basa su due concetti, *relazione* e *tabella*.  
La nozione di relazione proviene dalla matematica, in particolare dalla teoria degli insiemi, mentre il concetto di tabella è semplice e 
intuitivo. 
Il modello relazonale risponde al requisito dell'indipendenza dei dati, che prevede una distinzione dei dati, fra livello *fisico* e *logico*.
gli utenti che accedono ai dati e ai programmatori che creano applicazioni fanno riferimento solo al livello logico, non è necessario conoscere le strutture fisiche.
Il termine relazione viene utilizzato in tre accezioni differenti:
 - *relazione matematica*, secondo la definizione normalmente data nella teoria degli insiemi
- *relazione* secondo la definizione del modello relazionale che presenta alcune differenze rispetto alla teoria degli insiemi
 - *relazione* costrutto del modello concettuale (*Entity-Relationship*), utilizzato per descrivere legami nel mondo reale.  
### Relazioni e tabelle
Dati due insiemi $\mathrm{D}_1$ e $\mathrm{D}_2$ , $\mathrm{D}_1$ X $\mathrm{D}_2$ l'insieme delle coppie ordinate ($\mathcal{v}_1$, $\mathcal{v}_2$), tali che $\mathcal{v}_1$ è un elemento di $\mathrm{D}_1$ e 
$\mathcal{v}_2$ è un elemento di $\mathrm{D}_2$.  

Una *relazione matematica* sugli insiemi $\mathrm{D}_1$ e $\mathrm{D}_2$ (chiamati ***domini*** della relazione) è un sottoinsieme di $\mathrm{D}_1$ X $\mathrm{D}_2$. 
Dati gli insiemi $\mathcal{A}$={1,2,4} e $\mathcal{B}$={a,b} la cui una possibile relazione matematica. Una possibile relazione matematica su questi è {(1,a),(2,a),(4,a)}.  
Le relazioni possono essere rappresentate sotto forma tabellare.
![Rappresentazione tabellare di un prodotto cartesiano e di una relazione](./Screen/rappresentazione_tabellare.png)  
>
Le definizioni precedenti di prodotto cartesiano e relazione matematicafanno riferimento a due insiemi, ma possono essere generalizzate rispetto al numero di insiemi. Dati $\mathcal{n}$ > 0 insiemi ,$\mathcal{D}_1$ ,...,$\mathcal{D}_n$. Non necessariamente distinti, $\mathcal{D}_1$ X...X $\mathcal{D}_n$ è costituito dall'insieme delle $\mathcal{n}$-uple($\mathcal{v}_1$,...,$\mathcal{v}_n$) tale che $\mathcal{v}_\mathcal{i}$ appartiene a $\mathcal{D}_\mathcal{i}$ per 1 $\leq$ $\mathcal{i}$ $\leq$ $\mathcal{n}$.
Una *relazione matematica* sui domini $\mathcal{D}_1$,...,$\mathcal{D}_\mathcal{n}$ è un sotto insieme del prodotto cartesiano $\mathcal{D}_1$ X ... X $\mathcal{D}_\mathcal{n}$.  
Il numero delle $\mathcal{n}$ componenti del prodotto cartesiano viene detto *grado* del prodotto cartesiano e della relazione.
Il nuero di elementi della relazione viene chiamato *cardinalità* della relazione.
Di seguito una rappresentazione tabellare del prodotto cartesiano e di una relazione di grado tre sui domini $\mathcal{C}$={$\mathcal{x},\mathcal{y}$}, $\mathcal{D}$={$\mathcal{a},\mathcal{b},\mathcal{c}$}, $\mathcal{E}$={$\mathcal{3},\mathcal{5}$}. La relazione ha cardinalità pari a sei.  
![Rappresentazione tabellare di un prodotto cartesiano ternari](./Screen/seconda_rap.png)  
Le relazioni (e le corrispondenti tabelle) possono essere utilizzate per rappresentare dati di interesse per qualche applicazione. Ad esempio
la seguente relazione: 
![Relazione partite di calcio](./Screen/relazione_calcio.png)  
che contiene i dati relativi ai risultati di un insieme di partite di calcio.  
Essa è definita con riferimento a due domini *intero* e *stringa*, ogniuno dei quali compare due volte. La relazione è un sottoinsieme del prodotto cartesiano 

<p style="text-align:center"><b>Stringa x Stringa x Intero x Intero</b></p>  

### Relazioni con attributi
In base alla definizione, una relazione matematica è un *insieme* di 
$\mathcal{n}$-uple *ordinate* ($\mathcal{v}_\mathcal{1}$,...,$\mathcal{v}_\mathcal{n}$), con $\mathcal{v}_\mathcal{i}$ $\in$ $\mathcal{D}_\mathcal{i}$ con $\mathcal{i}$=$\mathcal{1},...,\mathcal{n}$



