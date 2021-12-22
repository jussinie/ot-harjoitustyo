
# Vaatimusmäärittely*

## Sovelluksen tarkoitus

Sovelluksen avulla käyttäjät voivat kirjata ylös viikon aikana tehdyt tuntinsa. Jokaisella käyttäjällä on oma käyttäjätunnuksensa ja jokainen käyttäjä voi näin hallinnoida omia tuntejaan. Käyttäjä voi luoda järjestelmään uuden viikon itselleen ja alkaa tallentaa siihen tunteja. Käyttäjä voi myös ladata aiemmin luomansa viikon ja jatkaa tuntien kirjaamista kyseiselle viikolle. Tällä hetkellä maksimiajanjakso, jolle tunteja voi tallentaa on yksi vuosi / 52 viikkoa. 

### Suunnittelufilosofia

Sovelluksessa on huomattavan paljon erilaisia luokkia ja monimutkaisia rakenteita tehtävien, tuntien, päivien, viikkojen ja vuosien hallintaan suhteessa siihen, mitä kurssin päättävässä releasessa käyttäjä voi tehdä. Tämä johtuu siitä, että sovellus on suunniteltu siten, että sitä on jatkossa helppo laajentaa, koska se sisältää kaikki rakenteet, joiden päälle voi rakentaa tuntikirjanpidon useille vuosille. Kaikkia arkkitehtuurin mahdollistavia featureja ei siis ole otettu vielä tässä vaiheessa käyttöön rajallisesta ohjelmointiajasta johtuen. 

### Käyttäjät

Sovelluksessa on tavallisia käyttäjiä, jotka kirjaavat omat tuntinsa sekä heidän esimiehiään, jotka voivat hyväksyä tai hylätä nämä kirjatut tunnit. 

### Sovelluksen toiminnallisuudet

    * Käyttäjä / esimies voi luoda itselleen käyttäjätunnuksen
    * Käyttäjä / esimies voi kirjautua sisään käyttäen tätä tunnusta
    * Käyttäjä voi luoda itselleen uuden periodin (viikon) tuntien lisäämistä varten
    * Käyttäjä voi kirjata tälle periodille tekemänsä tunnit
    * Käyttäjä näkee vain omat lisäämänsä tunnit
    * Käyttäjä / esimies voi kirjautua ulos sovelluksesta
    
### Toiminnallisuudet, jotka siirretty alkuperäisestä suunnitelmasta tuleviin sprintteihin

    * Esimies voi hyväksyä käyttäjän lisäämät tunnit
    * Oma näkymä, jos roolina team lead (ei eksplisiittinen vaatimus alussa, mutta implisiittisesti tarvittava)

### Pitkän aikavälin jatkokehitystä

    * Esimiehillä on omat tiiminsä, joihin käyttäjiä voi lisätä
    * Esimies näkee oman tiiminsä kirjatut tunnit
    * Esimies voi tulostaa tuntitiedot ulos järjestelmästä
    * Esimies voi luoda projektin, johon kuuluu erilaisia tehtäviä
    * Tuntikirjaukseen voi lisätä, mihin projektiin ja tehtävään se kuuluu
    * Tuntikirjaukseen voi tarvittaessa lisätä kommentin

