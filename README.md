# Quintor Android handson sessie

## Dependencies en eerste start-up
1. Download en installeer de volgende onderdelen:
	* [Git](https://git-scm.com/)
	* [Android studio](https://developer.android.com/studio/index.html)
2. Check deze repository uit met git dmv: `git clone https://github.com/xanvierb/android_handson.git`. (maak er eventueel een fork van in je eigen repository als je ook wilt kunnen pushen naar remote.)
3. Zorg dat je de juiste branch hebt geopend door `git checkout part-1` uit te voeren
4. Open het project in Android Studio
5. Klik op de groene play knop bovenin. Je krijgt nu een scherm te zien 'Select Deployment Target'.
6. Creeer een nieuwe Pixel 2 telefoon met een API level 26 (Android 8 Oreo)
7. Selecteer het toestel dat je zojuist hebt aangemaakt en klik op `OK`
8. Als alles goed is gegaan start je nu de applicatie op een virtuele Pixel 2 telefoon op.

## Android applicatie structuur, User interface en Intents 
1. Je kunt verder gaan met de applicatie die je zojuist hebt opgestart.
2. Maak een nieuwe activity en maak deze op dmv XML code.
3. Schrijf de code zodat je met de knop in het beeld door kan klikken van de bestaande activity naar de nieuwe activity.
4. Maak een fragment met een TextView welke je toevoegd aan de door jou gemaakte activity.
5. Commit al je wijzigingen met Git met het commando `git commit -m "part 2 klaar"`

## Advanced User Interface
1. Haal nu de branch part-3 binnen. `git checkout part-3`
2. Er zit 1 scherm in met wat tekst en een knop, zorg dat de tekst afhankelijk van de ingestelde locale van de telefoon in het Nederlands of het Engels wordt weergegeven.
3. Voeg een lege activity toe (file > new > empty activity)
4. Voeg een nieuwe List Fragment toe (file > new > Fragment > Fragment (List))
5. Zorg dat deze nieuwe fragment wordt geladen op de Activity die je net gemaakt hebt. (Via de layout XML)
6. De kun je de originele acitivity zo configureren dat deze de nieuwe Activity laadt als je op de knop drukt.
7. Laat in de list fragment 15 ingredienten zien.
8. Commit al je wijzigingen in Git met `git commit -m "part 3 klaar"`

## AsyncTask en REST calls
1. Haal nu de branch part-4 binnen. `git checkout part-4`
2. Maak een account aan op https://newsapi.org/ en genereer een API key.
3. Zet de API key in het juiste resource bestand.
4. Er is een klasse `NewsRestTask`, deze is al helemaal geschreven. Er zijn 2 regels die afgemaakt moeten worden, maak het af zodat er een call wordt gedaan naar de nieuws API.
5. Test of er news wordt geladen en dit wordt weergegeven in het scherm.
6. Er is een methode `getAgeFromDate()` Dit retourneerd niet iets dat je graag op het scherm hebt, zorg dat dit mooi wordt opgemaakt.



