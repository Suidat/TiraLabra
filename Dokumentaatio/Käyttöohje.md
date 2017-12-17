Ennen ohjelman käynnistystä täytyy rekisteröityä Vindiniumin sivuilla: Vindinium.org/register. Sivulla olevaan tekstikenttään kirjoitetaan haluttu nimi omalle botille. Tämän jälkeisellä sivulla ota talteen annettu koodi. ÄLÄ KADOTA SITÄ! Jos se katoaa, joudut luomaan uuden käyttäjän sivulle, sillä ei ole mitään tapaa saada uutta koodia botille.

Tämän jälkeen ohjelma voidaan käynnistää jar tiedostosta, joka löytyy ohjelman juuresta. Eli kun koko repositorion lataa, kansiosta TiraBot löytyy tarvittava jar tiedosto, jonka nimi on TiraBot.jar. Ohjelma käynnistetään kuten jar tiedostot yleensä.

Kun sen käynnistää, ohjelma kysyy koodia jonka sait kun rekisteröidyit vindiniumiin. Kun se on syötetty ilmoitetaan halutaanko botti laittaa harjoitus otteluun, missä vastustajien liikkeet ovat satunnaisia, vai halutaanko se laittaa areenaan missä vastassa on muiden ihmisten tekemiä botteja. Harjoituksen saa kun syöttää 1, ja areena ottelun saa syötteellä 2. Tämän jälkeen syötetään vielä tieto siitä, millaista bottia halutaan käyttää. Tällä hetkellä on vain yksi mahdollinen botti, ja sen saa syötteellä "basic".

Kun nämä tiedot on syötetty, alkaa botti pelaamaan peliä. Jos areena valittiin, voi joutua odottamaan pitkäänkin, sillä peli voidaan aloittaa vain kun on 4 pelajaa valmiina yhtä aikaa. Pelin alettua sen url-osoite ilmoitetaan käyttöliittymässä. Käyttöliittymästä voi myös tarkitstaa botin päätökset jokaisella vuorolla sekä sen paikan kartalla.

Pelin alkupään tiedot tallennetaan tiedostoon src/main/resources/game ja pelin lopun tiedot läytyvät tiedostosta src/main/resources/game. Täältä voi tarkistaa miten botti pärjäsi pelissä. Tämä tieto näkyy myös ohjelman antamassa osoitteessa, kun peli on saatu päätökseen. Mitään graaffista dataa ohjelma ei anna, sillä sen saa valmiiksi vindiniumin puolelta.

Jos haluat voit laittaa useamman saman botin yhtä aikaa pelamaan, ja jos onnistut käynnistämään ne hyvin nopeasti peräkkäin, voit saada jopa 4 yhteen peliin, ja tarkistaa miten botti pärjää itseään vastaan.
