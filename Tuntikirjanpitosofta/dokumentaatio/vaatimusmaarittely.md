
# Vaatimusmäärittely*

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat kirjata ylös viikon aikana tehdyt tuntinsa. Jokaisella käyttäjällä on oma käyttäjätunnuksensa ja jokainen käyttäjä voi näin hallinnoida omia tuntejaan. 

## Käyttäjät

Sovelluksessa on tavallisia käyttäjiä, jotka kirjaavat omat tuntinsa sekä heidän esimiehiään, jotka voivat hyväksyä tai hylätä nämä kirjatut tunnit. 

## Suunnitellut toiminnallisuudet

    * Käyttäjä / esimies voi luoda itselleen käyttäjätunnuksen => tehty
    * Käyttäjä / esimies voi kirjautua sisään käyttäen tätä tunnusta => tehty
    * Käyttäjä voi luoda itselleen uuden periodin tuntien lisäämistä varten => tehty
    * Käyttäjä voi kirjata tälle periodille tekemänsä tunnit => tehty
    * Käyttäjä näkee vain omat lisäämänsä tunnit
    * Esimies voi hyväksyä käyttäjän lisäämät tunnit
    * Käyttäjä / esimies voi kirjautua ulos sovelluksesta

    => Osa toiminnallisuuksista vielä vajavaisia,kaikkia syötteitä ei tarkisteta.
    => Tietojen tallennus siirretty tiedostosta sql-tietokantaan

### Jatkokehitystä

    * Esimiehillä on omat tiiminsä, joihin käyttäjiä voi lisätä
    * Esimies näkee oman tiiminsä kirjatut tunnit
    * Esimies voi tulostaa tuntitiedot ulos järjestelmästä
    * Esimies voi luoda projektin, johon kuuluu erilaisia tehtäviä
    * Tuntikirjaukseen voi lisätä, mihin projektiin ja tehtävään se kuuluu
    * Tuntikirjaukseen voi tarvittaessa lisätä kommentin

*kyseessä alustava määrittelydokumentti, joten muutoksia (uusia featureita) voi vielä tulla
