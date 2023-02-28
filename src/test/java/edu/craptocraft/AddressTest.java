package edu.craptocraft;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.security.PublicKey;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AddressTest {

    static Address address;
    @Before
    public void setUp() {
        address = new Address();
        address.generateKeyPair();
    }

    @Test
    public void testPublicKey() {
        PublicKey publicKey = address.getPK();
        assertNotNull(publicKey);
    }

    @Test
    public void testBalance() {
        double initialBalance = address.getBalance();
        assertEquals(0d, initialBalance, 0.001);

        address.setBalance(10d);
        double updatedBalance = address.getBalance();
        assertEquals(10d, updatedBalance, 0.001);

        address.transferEZI(5d);
        double newBalance = address.getBalance();
        assertEquals(15d, newBalance, 0.001);
    }
}
