package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntryTest {
    Entry c1;
    Entry c2;
    Entry c3;
    Entry c4;
    Entry c5;
    Entry c6;
    Entry c7;

    @BeforeEach
    public void setup() {
    c1 = new Entry("payee1", "cash", true, "GST", 100,
            "project1" );
    c2 = new Entry("payee2", "visa", true, "PST", 200,
            "project2" );
    c3 = new Entry("payee3",  "cheque", true, "BOTH", 300,
            "project3" );
    c4 = new Entry("payee4", "cash", false, "GST", 400,
            "project1" );
    c5 = new Entry("payee5",  "visa", false, "PST", 500,
            "project1" );
    c6 = new Entry("payee6",  "cheque", false, "BOTH", 600,
            "project6" );
    c7 = new Entry("payee7",  "cheque", false, "NONE", 700,
            "project7" );
}

    @Test
    public void entryTest() {
    assertEquals("payee1", c1.getPayee());
    assertEquals("payee2", c2.getPayee());
    assertEquals("cash", c4.getPaymentType());
    assertEquals("visa", c5.getPaymentType());
    assertEquals("cheque", c3.getPaymentType());
    assertTrue(c3.getTaxPaid());
    assertFalse(c7.getTaxPaid());
    assertEquals("GST", c1.getTaxType());
    assertEquals("PST", c2.getTaxType());
    assertEquals("BOTH", c3.getTaxType());
    assertEquals("NONE", c7.getTaxType());
    assertEquals(100, c1.getAmount());
    assertEquals(200,c2.getAmount());



}
    @Test
    public void testCalculateTax() {
    assertEquals(4.761, c1.calculateTax(), 1);
    assertEquals(13.084, c2.calculateTax(), 1);
    assertEquals(32.142, c3.calculateTax(), 1);
    assertEquals(20.0, c4.calculateTax());
    assertEquals(35.0, c5.calculateTax());
    assertEquals(72.0, c6.calculateTax());
    assertEquals(0, c7.calculateTax());
    }

    //These setters are only accessed by UI so not already tested by entry class
    @Test
    public void settersTest() {
        c1.setTaxType("GST");
        c1.setTaxPaid(false);
        c1.setAmount(101);
        c1.setPaymentType("visa");
        c1.setPayee("test");
        c1.setProject("test");
        c2.setTaxType("PST");
        c2.setTaxPaid(true);
        c2.setAmount(102);
        c2.setPaymentType("cheque");
        c2.setPayee("test2");
        c2.setProject("test2");

        assertEquals("GST", c1.getTaxType());
        assertFalse(c1.getTaxPaid());
        assertEquals(101,c1.getAmount());
        assertEquals("visa", c1.getPaymentType());
        assertEquals("test", c1.getPayee());
        assertEquals("test", c1.getProject());
        assertEquals("PST", c2.getTaxType());
        assertTrue(c2.getTaxPaid());
        assertEquals(102,c2.getAmount());
        assertEquals("cheque", c2.getPaymentType());
        assertEquals("test2", c2.getPayee());
        assertEquals("test2", c2.getProject());
    }
}