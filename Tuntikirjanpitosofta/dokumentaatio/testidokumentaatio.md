# Testidokumentaatio #

### Testikattavuus ja testausmetodologia ###

Testikattavuus on tällä hetkellä noin ~80% niin rivi- kuin haarautumakattavuuden osalta. Testit löytyvät kahdesta pakkauksesta. DomainTests sisältää sovelluslogiikkaan ja tavallisiin luokkiin liittyvät testit, siinä missä daoTests-pakkaus sisältää tietokannan toimintaa kontrolloivien luokkien testit. UserService-luokan testauksessa on käytetty mock up -komponentteja FakeUserDao ja FakeWeekDao, jotka tallentavat tarvittavat tiedot muistiin tietokannan sijaan. Tämän injektoinnin myötä testaaminen helpottuu, kun tietokantaa ei tarvitse käyttää sovelluslogiikan testaamisessa. 

Testattaessa tietokannan toimintaa hallinnoivia luokkia käytössä on testitietokanta hourreporterTest.db, joka on erillään tuotantotietokannasta hourreporter.db. UserDao ja WeekDao -luokat saavat konstruktorinsa parametrinä joko merkkijonon test tai prod, joka määrittelee luodaanko yhteys testi- vai tuotantotietokantaan. 

### Testien ajaminen ja testiraportin tarkastelu ### 

Testit voi ajaa komennolla 

> mvn test report:jacoco 

ja testikattavuusraporttia voi sen jälkeen tutkia haluamallaan selaimella. Raportti on html-tiedosto, polku ./target/site/jacoco/index.html. Alla kuvakaappaus testiraportista, josta on nähtävissä tämänhetkinen testauskattavuus. 


