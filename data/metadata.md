## Field data

- **ID**: et tal mellem 1 og 40

- **FieldType:** kan være “Plot”, “Brewery", “ShippingLine", “Chance”, “Prison”, “Visit”, “Parking", “Start” eller “Tax”

- **Label**: navnet på feltet som spilleren ser det
  fx. “Rådhuspladsen”

- **Cost**: prisen ved af en property eller ved betaling af skat
  Felter som ikke er forbundet med en omkostning har 0 som værdi.
    - Vi skal have en metode til Bryggerierne som gør at hvis virksomheden ejes skal der betales 100 gange så meget som øjnene viser. Hvis begge bryggerier ejes, betales der 200 gange så meget som øjnene viser.

- **Income**: det beløb der inkasseres af ejeren når nogen lander på en property.
  Ved start feltet noteres passér start beløbet.

- **Series ID**: et tal der grupperer grunde, redderier eller bryghuse.
- Serierne nummereres fortløbende: 

  -  Første serie på spillepladen er felt 2 og 4 (Hvidovrevej og Rødovrevej): giv begge felter **serieID 1**
  -  Anden serie på spillepladen er Redderierne(Shippingline). De skal have **serieID 2**
  -  Felterne i sidste serie på spillepladen har **serieID 10**

### Eksempler:

```csv
39, Tax, Ekstra Skat,2000, 0, 0`
40, Plot, Rådhuspladsen, 8000, 1000, 10 `
```


## Card data

- **message** : besked der beskriver en hændelse

- **income** : det beløb man evt. skal modtage

- **cost**: det beløb der evt. skal betales

- **event**: en hændelse skal kategorieseres som en af følgende typer: 

- **newPostion** : ofte kombineres en hændelse med en ny position på spillepladen. Værdien her _lægges til_ nuværende position hvis event er "moveSteps"

### Liste over events
**Obs!** 
Denne liste er en skitse. Analyse af chancekort påkrævet, således at alle eventtyper og deres datakrav afdækkes. 

| event name             | description                                                                                  | example |
|------------------------|----------------------------------------------------------------------------------------------|--- |
| reward                 | spilleren modtager nogle penge                                                               ||
| recieveFromPlayers     | spilleren modtager penge fra alle de øvrige spillere                                         ||
| payment                | spilleren skal betale penge til banken                                                       ||
| paymentTimesProperties | spilleren skal betale et beløb ganget med antal ejede bygninger                              ||
| takeFerry              | spilleren skal rykke til nærmeste færge og betale dobbelt leje til ejeren                    ||
| wildcard               | spilleren kan gemme kortet til at komme ud af fængsel senere                                 ||
| moveToField            | spilleren skal rykkes til et bestemt felt                                                    ||
| moveSteps              | spilleren skal rykke et antal felter frem eller tilbage (newPosition sættes til et minustal) |


### Eksempler:
```csv
Du har snydt i skat. Ryk i fængsel, 0, 0, jail, 11
Modtag legat for værdig trængende, 1000, 0, receive, 0
```


### Noter vedrørende felt data
- fielddata.csv er sepereret med ","
- Breweries skal kigges på da de har en variabel income 
- Det ene Tax felt skal kigges på da den også har en variabel cost


### Noter vedrørende chancekort data
- cardata.csv er sepereret med ";" !
- Linje 16 har en variabel udgift der skal kigges på
- Linje 18 har en variabel indtægt der skal kigges på
