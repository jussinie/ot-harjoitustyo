import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaksukorttiTest {

    Maksukortti kortti;

    @BeforeEach
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void konstruktoriAsettaaSaldonOikein() {
        Maksukortti kortti = new Maksukortti(10);
        String vastaus = kortti.toString();
        assertEquals("Kortilla on rahaa 10.0 euroa", vastaus);
    }

    @Test
    public void syoEdullisestiVahentaaSaldoaOikein() {
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiVahentaaSaldoaOikein() {
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
    }

    @Test
    public void rahanLataaminenToimiiOikein() {
        kortti.lataaRahaa(130);
        assertEquals("Kortilla on rahaa 140.0 euroa", kortti.toString());
    }

    @Test
    public void negatiivinenSummaEiMuutaKortinSaldoa() {
        kortti.lataaRahaa(-50);
        assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
    }

    @Test
    public void voitOstaaEdullisenLounaanKunSaldoaJuuriSenVerran() {
        kortti = new Maksukortti(2.5);
        kortti.syoEdullisesti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }

    @Test
    public void voitOstaaMaukkaanLounaanKunSaldoaJuuriSenVerran() {
        kortti = new Maksukortti(4.0);
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
    }

    @Test
    public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        kortti.syoMaukkaasti();
        assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
    }
    //maukkaan lounaan syöminen ei vie saldoa negatiiviseksi, ota tähän mallia testistä syoEdullisestiEiVieSaldoaNegatiiviseksi

}