# Testidokumentaatio #

## Testikattavuus ja testausmetodologia ##

### Yksikkötestaus ###

Testikattavuus on tällä hetkellä noin ~80% rivikattavuuden ja ~70% haarautumakattavuuden osalta. Testit löytyvät kahdesta pakkauksesta. DomainTests sisältää sovelluslogiikkaan ja tavallisiin luokkiin liittyvät testit, siinä missä daoTests-pakkaus sisältää tietokannan toimintaa kontrolloivien luokkien testit. UserService-luokan testauksessa on käytetty mock up -komponentteja FakeUserDao ja FakeWeekDao, jotka tallentavat tarvittavat tiedot muistiin tietokannan sijaan. Tämän injektoinnin myötä testaaminen helpottuu, kun tietokantaa ei tarvitse käyttää sovelluslogiikan testaamisessa. 

![testCoverage](https://user-images.githubusercontent.com/64590570/147246109-b22efae0-3d64-454e-8fc3-05094d8a70fa.png)

Testattaessa tietokannan toimintaa hallinnoivia luokkia käytössä on tuotantotietokannasta erillään oleva testitietokanta. Tietokantojen nimet on mahdollista määritellä uudestaan konfiguraatiotiedoston config.txt avulla. UserDao ja WeekDao -luokat saavat konstruktorinsa parametrinä tietokantayhteyden kuvaavan merkkijonon, joka määrittelee luodaanko yhteys testi- vai tuotantotietokantaan. Kun yhteys testitietokantaan muodostetaan ensimmäisen kerran, luodaan samalla taulut Users ja Weeks.

### Järjestelmätestaus ###

Sovelluksen koodi on ladattu Helsingin Yliopiston tarjoamalle Cubbli Linux virtuaalityöasemalle komennolla git fetch. Koodista on sen jälkeen luotu ajettava jar-paketti onnistuneesti komennolla mvn package. Myös ohjelman suoritus onnistuu oletetusti [käynnistysohjeissa]() kerrotulla tavalla joko tuotanto- tai testitilaan. 

Sovelluksen syötteitä (konstruktorit [UserCreation](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/src/main/java/hourreporter/ui/UserCreationController.java), [login](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/src/main/java/hourreporter/ui/LoginPageController.java), [weekModification](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/src/main/java/hourreporter/ui/WeekModificationPageController.java), [weekCreation](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/src/main/java/hourreporter/ui/WeekCreationPageController.java) ja [weekSelection](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/src/main/java/hourreporter/ui/WeekSelectionPageController.java) on testattu manuaalisesti eikä sovellus kaadu virheellisiin syötteisiin. 

### Testien ajaminen ja testiraportin tarkastelu ### 

Testit voi ajaa komennolla 

> mvn test report:jacoco 

ja testikattavuusraporttia voi sen jälkeen tutkia haluamallaan selaimella. Raportti on html-tiedosto, polku ./target/site/jacoco/index.html. Alla kuvakaappaus testiraportista, josta on nähtävissä tämänhetkinen testauskattavuus. 

### Checkstyle ### 

Projektissa käytetään myös Checkstyleä, jotta koodi pysyy hyvin luettavana ja oikein muotoiltuna. Checkstylestä enemmän täällä: [checkstyle](https://checkstyle.org/). Checkstyle-raportin voi tuottaa komennolla
> mvn jxr:jxr checkstyle:checkstyle

Valmis raportti checkstyle.html löytyy polusta ./target/site/. 
Alla viimeisin Checkstyle-raportti.

![checkstyle](https://user-images.githubusercontent.com/64590570/147101720-cccf2ff6-981d-4f03-a134-614b383be193.png)
