# Käyttöohje

## Sovelluksen käynnistäminen

Lataa uusin [release](https://github.com/jussinie/ot-harjoitustyo/releases/tag/loppupalautus). Jos haluat suorittaa sovelluksen tuotantotilassa, käytä komentoa
> java -jar HourReporter.jar prod

ja mikäli testitilassa, käytä komentoa

> java -jar HourReporter.jar test

Viimeisenä annettu parametri määrittelee sen, mihin tietokantaan yhteys muodostetaan. Tietokantaa voi vaihtaa muokkaamalla konfiguraatiotiedostoa [config.txt](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/config.txt), mikä tekee ohjelmasta helposti konfiguroitavan ja laajennettavan. 

### Käynnistyksen jälkeen

Sovelluksen käynnistyttyä olet aloitussivulla. Tällä sivulla voit valita kirjaudutko sisään olemassaolevalla käyttäjätunnuksella (Existing user - Log in), luotko uuden käyttäjätunnuksen (New user - Create account) vai poistutko ohjelmasta. 

![landingPage](https://user-images.githubusercontent.com/64590570/147268843-70c3ccbb-3d4a-460b-870d-34cb13f986c4.png)

Jos klikkasit Existing user - Log in sinun tulee syöttää olemassaoleva käyttäjätunnuksesi kenttään ja klikata log in -nappia. Vaihtoehtoisesti voit kirjautua sisään painamalla Enteriä. Voit myös palata aloitussivulle painamalla Go back. 

![loginPage](https://user-images.githubusercontent.com/64590570/147268859-18ee2c0c-86f2-4fd8-b31e-bfe2754947fc.png)

Mikäli käyttäjätunnusta ei ole olemassa, ohjelma kertoo siitä virheviestillä.

![errorScreen](https://user-images.githubusercontent.com/64590570/147268875-fd5e92ab-5805-4e24-bcc3-ab31d2f22875.png)

Huomaa, että voit avata nämä ohjeet milloin tahansa Help-valikon alta klikkaamalla kohtaa User manual. 

![userManual](https://user-images.githubusercontent.com/64590570/147269098-8ed6d980-50a3-4946-a3dd-831385f591a5.png)

### Uuden käyttäjän luominen

Ennen ensimmäistä kirjautumista on luotava käyttäjätunnus. Se tapahtuu täyttämällä tiedot kenttiin ja painamalla nappia Create user and log in. Etu- ja sukunimen sekä käyttäjänimen on oltava vähintään kolme merkkiä pitkiä. Muussa tapauksessa käyttäjän luominen ei onnistu ja käyttäjälle näytetään virheviesti.

![userCreationScreen](https://user-images.githubusercontent.com/64590570/147269123-1efc5169-9e46-45e0-909b-9c3c1e5c688b.png)

### Kirjautumisen jälkeen

Kirjautumisen jälkeen olet kotisivullasi. Vasemmasta ylänurkasta valikon File alta voit valita luotko uuden viikon vai valitsetko jonkin olemassa olevan viikon, jolle kirjaat tunteja. Huomaa, että jos valitsemaasi viikkoa ei ole olemassa, se luodaan tässä vaiheessa. 


### Viikon luominen

Viikko luodaan alla olevassa näkymässä, valitsemalla viikon numero 1-52 ja painamalla nappia Create. Jos viikko on jo luotu, sitä ei ylikirjoiteta vaan se 

![weekCreation](https://user-images.githubusercontent.com/64590570/147269228-e24af797-61f1-4316-b123-638411226306.png)

Kun viikko on valittu, se näytetään seuraavassa näkymässä. 

![weekModification](https://user-images.githubusercontent.com/64590570/147269180-beac89e8-127b-476a-82bd-8ecdcd886e49.png)

Tässä näkymässä käyttäjä voi muokata tuntejaan ja tallentaa ne painamalla Save-näppäintä.
