# Käyttöohje

## Sovelluksen käynnistäminen

Lataa uusin [release](https://github.com/jussinie/ot-harjoitustyo/releases/tag/loppupalautus). Jos haluat suorittaa sovelluksen tuotantotilassa, käytä komentoa
> java -jar HourReporter.jar prod

ja mikäli testitilassa, käytä komentoa

> java -jar HourReporter.jar test

Viimeisenä annettu parametri määrittelee sen, mihin tietokantaan yhteys muodostetaan. Tietokantaa voi vaihtaa muokkaamalla konfiguraatiotiedostoa [config.txt](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/config.txt), mikä tekee ohjelmasta helposti konfiguroitavan ja laajennettavan. 

### Käynnistyksen jälkeen

Sovelluksen käynnistyttyä olet aloitussivulla. Tällä sivulla voit valita kirjaudutko sisään olemassaolevalla käyttäjätunnuksella (Existing user - Log in), luotko uuden käyttäjätunnuksen (New user - Create account) vai poistutko ohjelmasta. 

![landingPage](https://user-images.githubusercontent.com/64590570/147268001-dd4a3770-08b2-467d-a5ea-c79c520d01eb.png)

Jos klikkasit Existing user - Log in sinun tulee syöttää olemassaoleva käyttäjätunnuksesi kenttään ja klikata log in -nappia. Vaihtoehtoisesti voit kirjautua sisään painamalla Enteriä. Voit myös palata aloitussivulle painamalla Go back. 

![loginPage](https://user-images.githubusercontent.com/64590570/146057685-6e894517-38a6-4b44-bd13-4e062d9d6281.png)

Mikäli käyttäjätunnusta ei ole olemassa, ohjelma kertoo siitä virheviestillä.

![errorLogin](https://user-images.githubusercontent.com/64590570/146057858-6281f951-35e9-4350-b127-25e6152301f1.png)

### Uuden käyttäjän luominen

Ennen ensimmäistä kirjautumista on luotava käyttäjätunnus. Se tapahtuu täyttämällä tiedot kenttiin ja painamalla nappia Create user and log in.

![userCreation](https://user-images.githubusercontent.com/64590570/146058196-4543339d-b9df-4a40-8e38-06cc38ee7c4f.png)

### Kirjautumisen jälkeen

Kirjautumisen jälkeen olet kotisivullasi. Vasemmasta ylänurkasta valikon File alta voit valita luotko uuden viikon vai valitsetko jonkin olemassa olevan viikon, jolle kirjaat tunteja. Huomaa, että jos valitsemaasi viikkoa ei ole olemassa, se luodaan tässä vaiheessa. 

![mainPage](https://user-images.githubusercontent.com/64590570/146062498-ffef2746-bb27-48e1-bbb6-852a3c6a2bf9.png)

Huomaa, että voit avata nämä ohjeet milloin tahansa Help-valikon alta klikkaamalla kohtaa User manual. 

![userManual](https://user-images.githubusercontent.com/64590570/146062691-958dd176-266c-476a-858e-7f23a932b965.png)

### Viikon luominen

Viikko luodaan alla olevassa näkymässä, valitsemalla viikon numero 1-52 ja painamalla nappia Create. Jos viikko on jo luotu, sitä ei ylikirjoiteta vaan se valitaan työstettäväksi seuraavaan näkymään. 

![weekCreation](https://user-images.githubusercontent.com/64590570/146063350-86f477ac-d86b-4edf-98b4-cdea9b0dbf2c.png)

Kun viikko on valittu, se näytetään seuraavassa näkymässä. 

![weekModification](https://user-images.githubusercontent.com/64590570/146063387-8c44dbea-a4a0-4e6f-9034-36d3539f7087.png)

Tässä näkymässä käyttäjä voi muokata tuntejaan ja tallentaa ne painamalla Save-näppäintä.


