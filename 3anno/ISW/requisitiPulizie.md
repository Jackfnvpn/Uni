### 🟦 Use case

**Visualizza camere da pulire**

| Descrizione | Passo | Azione                                                                   |
| ----------- | ----- | ------------------------------------------------------------------------ |
|             | 1     | L’operatore del servizio pulizie accede alla sezione camere.             |
|             | 2     | Il sistema mostra l’elenco delle camere da pulire ordinate per priorità. |

**Attori:** Servizio pulizie
**Precondizioni:** L’operatore è autenticato nel sistema.
**Scenario principale:** L’operatore visualizza le camere che richiedono pulizia.
**Scenari alternativi:** Se non ci sono camere da pulire, viene mostrato un messaggio.
**Post-condizioni:** L’operatore ha le informazioni necessarie per iniziare la pulizia.

---

### 🟦 Use case

**Segnala completamento pulizia**

| Descrizione | Passo | Azione                                                   |
| ----------- | ----- | -------------------------------------------------------- |
|             | 1     | L’operatore seleziona la camera pulita dall’elenco.      |
|             | 2     | Il sistema aggiorna lo stato della camera come “pulita”. |

**Attori:** Servizio pulizie
**Precondizioni:** La camera deve essere presente nell’elenco delle camere da pulire.
**Scenario principale:** L’operatore aggiorna lo stato della camera dopo aver completato la pulizia.
**Scenari alternativi:** Se la camera è già segnata come pulita, viene mostrato un avviso.
**Post-condizioni:** Lo stato della camera è aggiornato e visibile ad altri ruoli (es. receptionist).
