Tällä viikolla olen toteuttanut loput tietorakenteet, kirjoittanut niille testejä ja tehnyt suorituskykytestejä rakenteille. Kaikki tietorakenteet toimivat, ja niillä on testit tarpeellisille metodeille.
A* algoritmi ja muut itse toteutetut luokat toimivat nyt vain omilla tietorakenteilla. Nopeus ei kärsinyt tästä muutoksesta juurikaan yhtään. Tietorakenteiden vertailusta näkyy, että Javan valmiit metodit suorittuvat tehtävistä samalla nopeudella kuin itse toteuteuttamani.
Botin logiikkaa en ole muuttanut tällä viikolla, sillä yrittämäni muutokset saivat sen juoksemaan seinään, joten jotta githubissa oleva versio vielä toimisi, otin pois muutokset ja jatkan niiden tutkiskelua.
Suorituskykytestit tällä hetkellä vertaavat omien ja Javan tietorakenteiden nopeuksia suurilla syötteillä. Kaikki testit syöttävät rakenteisiin 1000,2000,3000...10000 alkiota, ja sitten niiden etsimis/poistamis nopeuksia verrataan. Tämä informaatio tulee terminaaliin. 