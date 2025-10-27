# Matador ( Monopoly )

![Monopoly](./images/matador.jpg)

## Beskrivelse

Dette projekt er udviklet af studerende på baggrund af et objektorienteret design i form af et klassediagram og 
sekvensdiagrammer, der dækker de centrale use cases i Matador-spillet.
Formålet er at demonstrere principperne i objektorienteret programmering – samt analyse og design – for 
førsteårsstuderende på datamatikeruddannelsen.

---

## Niveauer og læringsmål

1. **Klient- og domæneklasse** (`Player - Account`)
   Hvordan en klasse kan indeholde og anvende en anden klasses funktionalitet.
   Eksempel: `Player` bruger en `Account` til at håndtere spillerens penge i stedet for selv at implementere logikken.

2. **Hjælpeklasser** (`util.TextUI, util.FileIO`)
   Hvordan klasser uden for domænet kan udformes som generiske moduler, der kan genbruges i forskellige projekter.

3. **Objektorienteret analyse** (`Game, Player, Board, Field`)
   Hvordan analyse af krav afslører de vigtigste entiteter og kandidater til klasser.

4. **Komposition**
   Hvordan koden opdeles i selvstændige enheder og forbindes til en helhed (`Game - Board - Field`).

5. **Abstraktion** (`Field < Property < Space`)
   Hvordan klasser kan dele implementering gennem nedarvning eller love bestemte adfærd via interfaces for at muliggøre polymorfi.

## Iteration og progression

Hvert niveau bygger videre på de foregående:

* Lektion 1: Niveau 1
* Lektion 2: Niveau 2, 1
* Lektion 3: Niveau 3, 2, 1
* Lektion 4: Niveau 4, (3), 2, 1
* Lektion 5: Niveau 5, 4, (3), 2, 1

Vi øver os på at nedbryde projektet i overskuelige dele og på at lede for fordele arbejdsopgaver mellem 
alle på holdet. [Læs evt. denne artikel](https://www.geeksforgeeks.org/software-engineering/software-development-life-cycle-sdlc).

![sdlc](./images/sdlc.jpg)
