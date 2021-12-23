# Arkkitehtuurikuvaus # 

## Rakenne ##

Alla olevaan graafiseen arkkitehtuurikuvaukseen on merkitty ohjelman luokat ja luokkien muuttujat, poikkeuksena käyttöliittymä (luokkia ei erikseen merkitty) ja tietokantatoimintoja kuvaava osuus DAO, jossa muuttujien sijaan on merkitty tietokannan käsittelyyn käytetyt metodit. Arkkitehtuuridiagrammi on nähtävissä kokonaisuudessaan täällä: [arkkitehtuuri](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/arkkitehtuuri.md)

### Graafinen käyttöliittymä ###

Sovellukseen on luotu graafinen käyttöliittymä JavaFX:ää hyödyntäen. Käyttöliittymä koostuu ReporterGraphUI-luokasta, joka huolehtii kontrollereiden luomisesta ja graafisen käyttöliittymän käynnistämisestä. Se sisältää myös metodit eri näkymien asettamiseen Scene-olioon. Tämän luokan ja kontrolleriluokkien lisäksi jokaisella näkymällä on oma fxml-määrittelytiedostonsa, jossa graafisen käyttöliittymän näkymä on kuvattu xml-muodossa. 

### Sovelluslogiikka ###

Sovelluslogiikasta vastaa ensisijaisesti UserService-luokka, yhdessä muiden alla näkyvien luokkien kanssa. Kaikki sovelluslogiikan metodit ovat UserService-luokan metodeja. Se siis huolehtii käyttäjien luomisesta ja tallentamisesta sekä viikkojen luomisesta ja tallentamisesta. UserService myös lataa tarvittavat tiedot tietokannasta käyttäen injektoituja UserDao ja WeekDao -olioita. 

![domain](https://user-images.githubusercontent.com/64590570/146805075-7011bd86-9c95-420d-95f8-93e81b825d86.png)

Ohessa myös esimerkinomaisesti käyttäjän luomista ja ensimmäisen viikon luomista kuvaava sekvenssikaavio: 

![sekvenssikaavio](https://user-images.githubusercontent.com/64590570/147269688-935b11ee-52b0-413a-9cf1-29e426ff5d03.png)

### Tietojen pysyväistallennus ###

Tämä sovellus käyttää tietojen pysyväistallennukseen sqlite-tietokantaa. Ohjelmisto luo ensimmäisellä käynnistyskerralla tiedoston hourreporter.db, joka sisältää tietojen tallennukseen käytetyn sqlite-tietokannan. Ensimmäisellä käynnistyskerralla luodaan myös taulut Users ja Weeks käyttäjien ja viikkojen tallennusta varten. 

![dao](https://user-images.githubusercontent.com/64590570/146805097-2f0435ec-fe5e-4dbd-82f9-9e1901896003.png)

Ohjelmassa on yksi tietokantakyselyitä abstrahoiva rajapinta DAO ja rajapinnan toteuttavat luokat UserDao ja WeekDao. Kumpikin luokka sisältää samat metodit **create, read, update ja list**. Huomaa, että kuviosta puuttuu luokka DatabaseSelector, jolla valitaan käyttäjän määrittelemä tietokanta config.txt-tiedostosta. 

## Tulevaisuuden kehityskohteet ##

Alun ideointi tuotti huomattavan paljon mahdollisia featureja, joista läheskään kaikkia ei ehtinyt tämän kurssin puitteissa toteuttaa. Mielessä siinti täysiverinen raportointityökalu. Sovelluslogiikan luokat heijastelevat tätä ajattelua - tehtävien ja tuntien tallennusta varten on luotu rakenne, jossa vuosi, viikko, päivä ja tehtävä ovat omia luokkiaan. Tällä hetkellä tätä rakennetta ei kuitenkaan hyödynnetä täysimääräisesti ja siitä syystä yksittäiseen käyttäjäolioon liittyy vain yksi vuosi. Jatkokehityksen jälkeen käyttäjä voi myös luoda uusia vuosia, joina raportoida tunteja. 

Sama pätee Day-luokkaan, jonka olisi tarkoitus tukea yksittäisten tehtävien tallennusta. Tällä hetkellä luokkaa on kuitenkin muokattu siten, että pitkän listan sijaan päivälle tallennetaan vain yksi "dummy"-tehtävä. 
