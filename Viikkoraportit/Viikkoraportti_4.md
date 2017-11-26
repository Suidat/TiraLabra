Tällä viikolla käytin turhauttavan paljon aikaa testien luomiseen ja muokkaamiseen. Tähän paloi paljon aikaa, sillä tarvitsi luoda karttoja joilla testat, ja myös nopeasti tutustua suhteellisten tiedostoreittien luomiseen. Tällaisten karttojen luominen käsin on aikaa vievää.
A* koki myös joitain muutoksia, sillä siinä oli muutama ongelma kun sitä käytettiin yhdessä kokonasiten GameState objektien kanssa (muiden pelaajien yli voitiin kävellä jne.) Perjaatteessa tällä ohjelmalla voisi pelata Vindiniumia, mutta tällä hetkellä botti vain seisoo paikallaan.
Botin logiikkaa olen jo tutkinut, mutta sattuneista syistä en ole ehtinyt lisätä niitä ohjelmaan. Saan botille jonkinlaisen päätöksenteko järjestelmän pystyyn ensiviikon aikana.
Ohjelmassa on src/test/resources/ kansiossa nyt tiedostoja, joita muuttamalla voi luoda erillaisia karttoja, joilla testata toiminnallisuutta. Tällä hetkellä on vain 2 karttaa, testGame ja longTestGame. Toisella testataan ohjelmaa kun etäisyys kahden pisteen välillä on suuri, ja toisella voidaan testata normaalimpaa käytöstä. Olen myös aloittanut tutustumisen muihin tietorakenteisiin.

Aikaa mennyt noin 15h.
