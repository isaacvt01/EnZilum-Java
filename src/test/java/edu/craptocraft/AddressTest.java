package edu.craptocraft;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class AddressTest {

    static Address address;
    @BeforeClass
    public static void inicializar(){
        address = new Address();
        address.generateKeyPair();
    }
    @Test
    public void generateKeyPairTest(){
        Assert.assertNotNull(address.getPrivateKey());
        Assert.assertNotNull(address.getPublicKey());
    }
}
