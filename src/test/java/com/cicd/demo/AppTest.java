package com.cicd.demo;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {
    
    private static final double DELTA = 0.001;
    
    // Tests d'addition
    @Test
    public void testAdditionPositifs() {
        assertEquals(10.0, App.addition(5, 5), DELTA);
    }
    
    @Test
    public void testAdditionNegatifs() {
        assertEquals(-10.0, App.addition(-5, -5), DELTA);
    }
    
    @Test
    public void testAdditionMixte() {
        assertEquals(0.0, App.addition(5, -5), DELTA);
    }
    
    // Tests de soustraction
    @Test
    public void testSoustraction() {
        assertEquals(5.0, App.soustraction(10, 5), DELTA);
    }
    
    @Test
    public void testSoustractionNegatif() {
        assertEquals(-5.0, App.soustraction(5, 10), DELTA);
    }
    
    // Tests de multiplication
    @Test
    public void testMultiplication() {
        assertEquals(20.0, App.multiplication(4, 5), DELTA);
    }
    
    @Test
    public void testMultiplicationParZero() {
        assertEquals(0.0, App.multiplication(5, 0), DELTA);
    }
    
    @Test
    public void testMultiplicationNegatifs() {
        assertEquals(25.0, App.multiplication(-5, -5), DELTA);
    }
    
    // Tests de division
    @Test
    public void testDivision() {
        assertEquals(2.0, App.division(10, 5), DELTA);
    }
    
    @Test
    public void testDivisionDecimale() {
        assertEquals(2.5, App.division(5, 2), DELTA);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testDivisionParZero() {
        App.division(10, 0);
    }
    
    // Tests de puissance
    @Test
    public void testPuissance() {
        assertEquals(8.0, App.puissance(2, 3), DELTA);
    }
    
    @Test
    public void testPuissanceZero() {
        assertEquals(1.0, App.puissance(5, 0), DELTA);
    }
    
    @Test
    public void testPuissanceNegative() {
        assertEquals(0.25, App.puissance(2, -2), DELTA);
    }
    
    // Tests de racine carrée
    @Test
    public void testRacineCarree() {
        assertEquals(5.0, App.racineCarree(25), DELTA);
    }
    
    @Test
    public void testRacineCarreeZero() {
        assertEquals(0.0, App.racineCarree(0), DELTA);
    }
    
    @Test(expected = ArithmeticException.class)
    public void testRacineCarreeNegative() {
        App.racineCarree(-1);
    }
    
    // Tests des métadonnées
    @Test
    public void testGetVersion() {
        assertEquals("1.0.0", App.getVersion());
    }
    
    @Test
    public void testGetAuteur() {
        assertEquals("Amira", App.getAuteur());
    }
    
    @Test
    public void testGetDescription() {
        assertNotNull(App.getDescription());
        assertTrue(App.getDescription().contains("Calculatrice"));
    }
}
