### 🟦 Use case

**Effettua prenotazione**

| Descrizione | Passo | Azione                                                                 |
| ----------- | ----- | ---------------------------------------------------------------------- |
|             | 1     | Il cliente accede al sistema e seleziona le date di arrivo e partenza. |
|             | 2     | Il cliente seleziona la tipologia di camera desiderata.                |
|             | 3     | Il cliente conferma i dati e completa la prenotazione.                 |

**Attori:** Cliente
**Precondizioni:** Il cliente ha effettuato l'accesso al sistema.
**Scenario principale:** Il cliente effettua una prenotazione per un soggiorno.
**Scenari alternativi:** La camera selezionata potrebbe non essere disponibile, viene suggerita un'alternativa.
**Post-condizioni:** La prenotazione è registrata nel sistema e visibile nella sezione personale del cliente.

### 🟦 Use case

**Gestione prenotazioni**

| Descrizione | Passo | Azione                                                                    |
| ----------- | ----- | ------------------------------------------------------------------------- |
|             | 1     | Il cliente visualizza la lista delle prenotazioni effettuate.             |
|             | 2     | Il cliente seleziona una prenotazione da modificare o cancellare.         |
|             | 3     | Il sistema applica le modifiche richieste oppure elimina la prenotazione. |

**Attori:** Cliente, Amministrazione
**Precondizioni:** Il cliente ha effettuato almeno una prenotazione e ha effettuato l’accesso.
**Scenario principale:** Il cliente modifica o cancella una prenotazione esistente.
**Scenari alternativi:** La prenotazione non è modificabile (es. troppo vicino alla data d’arrivo), viene mostrato un messaggio di errore.
**Post-condizioni:** La prenotazione è aggiornata o rimossa dal sistema.

---

### 🟦 Use case

**Richiesta servizi extra**

| Descrizione | Passo | Azione                                                             |
| ----------- | ----- | ------------------------------------------------------------------ |
|             | 1     | Il cliente accede alla sezione “Servizi Extra” della prenotazione. |
|             | 2     | Il cliente seleziona uno o più servizi disponibili.                |
|             | 3     | Il sistema aggiorna la prenotazione con i servizi richiesti.       |

**Attori:** Cliente
**Precondizioni:** Il cliente ha una prenotazione attiva.
**Scenario principale:** Il cliente aggiunge servizi aggiuntivi (es. colazione, spa, lavanderia).
**Scenari alternativi:** Alcuni servizi potrebbero non essere disponibili nelle date selezionate.
**Post-condizioni:** I servizi extra vengono associati alla prenotazione.

---


### 🟦 Use case

**Pagamento online**

| Descrizione | Passo | Azione                                                           |
| ----------- | ----- | ---------------------------------------------------------------- |
|             | 1     | Il cliente accede alla sezione pagamenti della prenotazione.     |
|             | 2     | Seleziona il metodo di pagamento (es. carta di credito, PayPal). |
|             | 3     | Il sistema processa il pagamento e lo conferma.                  |

**Attori:** Cliente
**Precondizioni:** Il cliente ha una prenotazione valida e accesso al sistema.
**Scenario principale:** Il cliente paga online per la prenotazione.
**Scenari alternativi:** Il pagamento può fallire (es. carta rifiutata), viene mostrato un messaggio d’errore.
**Post-condizioni:** La prenotazione risulta pagata e pronta per il check-in.


Perfetto bro, grazie della precisazione!
Se sei **un singolo hotel**, allora semplifichiamo tutto: **le recensioni non devono riferirsi a una specifica struttura** perché **ce n’è solo una**.
Quindi:
✅ Le recensioni sono **assolute**, riferite **all'hotel** stesso (unico).

---

## 🟦 Use Case

### **Scrivi recensione**

| Descrizione | Passo | Azione                                         |
| ----------- | ----- | ---------------------------------------------- |
|             | 1     | Il cliente accede alla sezione “Recensioni”.   |
|             | 2     | Verifica di aver soggiornato almeno una volta. |
|             | 3     | Inserisce un voto e un commento testuale.      |
|             | 4     | Il sistema salva la recensione.                |

**Attori:** Cliente
**Precondizioni:** Il cliente ha almeno una prenotazione **conclusa**.
**Scenario principale:** Il cliente recensisce l’hotel dopo il soggiorno.
**Scenari alternativi:**

* Il cliente prova a recensire **senza aver mai effettuato una prenotazione** → messaggio di errore.


**Post-condizioni:** La recensione viene salvata e mostrata pubblicamente.





