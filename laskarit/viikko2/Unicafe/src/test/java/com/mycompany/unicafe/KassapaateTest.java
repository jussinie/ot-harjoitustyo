package com.mycompany.unicafe;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassapaate;

    @Before
    public void setUp() {
        kassapaate = new Kassapaate();
    }

    @Test
    public void kassapaatteessaOnOikeaMaaraRahaaAlussa() {
        assertEquals(kassapaate.kassassaRahaa(), 100000);
    }

    @Test
    public void kassapaatteessaOikeaMaaraMyytyjaEdullisia() {
        assertEquals(kassapaate.edullisiaLounaitaMyyty(), 0);
    }

    @Test
    public void kassapaatteessaOikeaMaaraMyytyjaMaukkaita() {
        assertEquals(kassapaate.maukkaitaLounaitaMyyty(), 0);
    }

    @Test
    public void edullisenOstoToimii() {
        assertEquals(60, kassapaate.syoEdullisesti(300));
        assertEquals(100240, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenOstoEiToimiJosEiRahaa() {
        assertEquals(200, kassapaate.syoEdullisesti(200));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaanOstoToimii() {
        assertEquals(100, kassapaate.syoMaukkaasti(500));
        assertEquals(100400, kassapaate.kassassaRahaa());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
    }

    @Test
    public void maukkaanOstoEiToimiJosEiRahaa() {
        assertEquals(200, kassapaate.syoMaukkaasti(200));
        assertEquals(100000, kassapaate.kassassaRahaa());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
    }

    @Test
    public void edullisenVoiOstaaKortillaJosRahaa() {
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassapaate.syoEdullisesti(kortti));
        assertEquals(260, kortti.saldo());
        assertEquals(1, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void edullistaEiVoiOstaaKortillaJosEiRahaa() {
        Maksukortti kortti = new Maksukortti(200);
        assertFalse(kassapaate.syoEdullisesti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassapaate.edullisiaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void maukkaanVoiOstaaKortillaJosRahaa() {
        Maksukortti kortti = new Maksukortti(500);
        assertTrue(kassapaate.syoMaukkaasti(kortti));
        assertEquals(100, kortti.saldo());
        assertEquals(1, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void maukastaEiVoiOstaaKortillaJosEiRahaa() {
        Maksukortti kortti = new Maksukortti(200);
        assertFalse(kassapaate.syoMaukkaasti(kortti));
        assertEquals(200, kortti.saldo());
        assertEquals(0, kassapaate.maukkaitaLounaitaMyyty());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortinJaKassanSaldotPaivittyvatOikeinKunPositiivinen() {
        Maksukortti maksukortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(maksukortti, 1000);
        assertEquals(2000, maksukortti.saldo());
        assertEquals(101000, kassapaate.kassassaRahaa());
    }

    @Test
    public void kortinJaKassanSaldoEiPaivityNegatiivisesta() {
        Maksukortti maksukortti = new Maksukortti(1000);
        kassapaate.lataaRahaaKortille(maksukortti, -500);
        assertEquals(1000, maksukortti.saldo());
        assertEquals(100000, kassapaate.kassassaRahaa());
    }

}
