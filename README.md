
# Ohjelmistotekniikka - Tuntikirjanpitosofta

Sovelluksen avulla käyttäjät voivat kirjata ylös viikon aikana tehdyt työtuntinsa. Jokaisella käyttäjällä on oma käyttäjätunnuksensa ja jokainen käyttäjä voi näin hallinnoida omia tuntejaan. Toistaiseksi kaikki toiminnallisuudet eivät vielä toimi ja sovellus on osin puutteellinen, mutta tämä korjaantuu tulevien viikkojen kuluessa. Lisätietoja löytyy vaatimusmäärittelystä.

### Dokumentaatio 

[Vaatimusmäärittely](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/vaatimusmaarittely.md)

[Tuntikirjanpito](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/tuntikirjanpito.md)

[Käyttöohje](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/kayttoohje.md)

[Arkkitehtuuri](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/dokumentaatio/arkkitehtuuri.md)

[Release](https://github.com/jussinie/ot-harjoitustyo/releases/tag/viikko5)

## Komentorivitoiminnot

### Ohjelman suorittaminen
Ohjelmasta voi luoda Jar-paketin komennolla. Huomaa, että kaikki mvn-komennot tulee antaa kansiossa, jossa on pom.xml-tiedosto (eli kansiossa Tuntikirjanpitosofta). 
> mvn package

Jar-paketti ajetaan komennolla
> java -jar Tuntikirjanpitosofta-1.0-SNAPSHOT.jar

### Testaus
Testit voidaan suorittaa komennolla
> mvn test

Testikattavuusraportin luominen onnistuu komennolla 
> mvn test jacoco:report 

tai lyhyemmin 
> mvn jacoco:report

jos testit on jo valmiiksi ajettu. Testikattavuusraportti löytyy polusta target/site/jacoco/. Index.html-tiedoston voi avata haluamallaan selaimella. 
Checkstylen voi suorittaa komennolla 
> mvn jxr:jxr checkstyle:checkstyle

Checkstyle-määrittelyt löytyvät tiedostosta [checkstyle.xml](https://github.com/jussinie/ot-harjoitustyo/blob/master/Tuntikirjanpitosofta/checkstyle.xml)

### Dokumentaatio
JavaDoc-dokumentaation voi luoda komennolla
> mvn javadoc:javadoc

Komento luo polkuun target/site/apidocs tiedoston index.html, jonka voi avata haluamallaan selaimella.
