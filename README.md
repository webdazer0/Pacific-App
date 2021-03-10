# Pacific-App
Pacific App: Gioco delle Orche e Leoni Marini


![](https://i.imgur.com/vY2rinr.jpeg)


Nell'Oceano Pacifico ci sono orche e leoni marini. Ogni mattina le amabili orche danno la caccia ai simpatici leoni marini, che cercano di salvarsi la vita. Sia le orche che i leoni marini possono muoversi liberamente, ogni mattina. 

Sviluppare in Android un programma che simuli il comportamento di orche e leoni marini secondo le seguenti regole per ogni turno di movimento:
1.	L'oceano è un mondo infinito, composto da una griglia di 20 x 20, per un totale di 400 caselle. Dalla casella [0,0], muovendosi di -1 sia su righe che colonne, ci si ritroverebbe nella casella [19,19]
2.	Ad ogni turno gli animali nelle caselle vengono mossi partendo da [0,0] e arrivando a [19,19], procedendo per colonne su ogni riga. Questo significa che un animale in [1,1] si muoverà prima di un animale in [1,2], che a sua volta si muoverà prima di un animale in [2,1]
3.	Ciascun animale si muove saltando da una casella all'altra (teletrasporto), senza tener conto del percorso intermedio che conduce alla casella successiva
4.	Ogni animale ha un movimento specifico. Se per esempio il movimento è codificato come [3,4] e l'animale si trova in [1,1], vuol dire che la prossima posizione dell'animale sarà [4,5]. Il movimento è codificato come variazione (delta) su righe e colonne.
5.	Sia le orche che i leoni marini possono atterrare sulle caselle vuote
6.	Se un'orca si muove e la casella successiva è occupata da un leone marino, allora l'orca mangia il leone marino, che esce quindi dal gioco
7.	In tutti gli altri casi diversi dai punti 5 e 6 l'animale rimane fermo

Per ottenere le coordinate di orche e leoni marini, utilizzare i dati in JSON dalle seguenti coordinate:


>  [https://torregatti.com/auth.php?animali=1](https://torregatti.com/auth.php?animali=1) 



I dati possono essere inseriti anche a mano sotto forma di stringa, utilizzando il file in allegato.
Nel file JSON i dati sono codificati alla seguente maniera:

|  |  |
| ------ | ------ |
| **riga** | la posizione sulle righe dell'animale |
| **colonna** | la posizione sulle colonne dell'animale |
| **tipo** | il tipo di animale, 0 sono le orche, 1 sono i leoni marini |
| **delta_riga** | spostamento sulle righe (un numero intero) |
| **delta_colonna** | spostamento sulle colonne (un numero intero) |


Il programma su Android deve disporre di una visualizzazione a griglia, che mostri graficamente all'utente la posizione degli animali ad ogni epoca (o turno). Il programma deve prevedere un pulsante per far cambiare il turno ogni volta che l'utente lo preme, aggiornando a video la posizione di tutti gli animali. La app deve inoltre consentire di inserire degli ostacoli sulla griglia (la mappa del mondo), nella maniera seguente:
1.	se l'utente clicca su una cella senza ostacoli e senza alcun animali, nella cella viene inserito un ostacolo
2.	se l'utente clicca su una cella con un ostacolo, l'ostacolo viene rimosso
3.	se l'utente clicca su una cella con un animale, non viene inserito alcun ostacolo
4.	gli animali non possono mai saltare sugli ostacoli, quindi se durante il suo movimento un animale dovesse trovarsi su un ostacolo, l'animale resterebbe fermo e non si muoverebbe

Il gioco parte all'epoca 0, quando i dati sono caricati dal file JSON. All'epoca 1 ciascun animale ha effettuato un singolo movimento, e così via per le epoche successive.
