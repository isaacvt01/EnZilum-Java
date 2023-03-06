package edu.craptocraft;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TokenContractTest {

    private Address owner;
    private TokenContract tokenContract;

    @Before
    public void setUp() {
        owner = new Address();
        owner.generateKeyPair();

        tokenContract = new TokenContract(owner);
        tokenContract.setName("EnZinlum");
        tokenContract.setSymbol("EZI");
        tokenContract.setStock(1000);
        tokenContract.setTokenPrice(10.0);
        tokenContract.addOwner(owner.getPK(), tokenContract.totalSupply());
    }

    @Test
    public void transferTest() {
        Address receptor = new Address();
        receptor.generateKeyPair();

        double balanceInicial = receptor.getBalance();

        double totalSupplyAntesDeTransfer = tokenContract.totalSupply();

        double tokensATransferir = 100.0;
        tokenContract.transfer(receptor.getPK(), tokensATransferir);

        double balanceDeReceptorEsperado = balanceInicial + tokensATransferir;
        assertEquals(balanceDeReceptorEsperado, tokenContract.balanceOf(receptor.getPK()), 0.0);

        double balanceDeOwnerEsperado = totalSupplyAntesDeTransfer - tokensATransferir;
        assertEquals(balanceDeOwnerEsperado, tokenContract.balanceOf(owner.getPK()), 0.0);
    }

    @Test
    public void testTransferInsufficientTokens() {
        Address receptor = new Address();
        receptor.generateKeyPair();

        double balanceInicial = receptor.getBalance();

        double tokensATransferir = 10000.0; // cantidad mayor a los tokens que existen
        tokenContract.transfer(owner.getPK(), receptor.getPK(), tokensATransferir);

        double balanceDeReceptorEsperado = balanceInicial;
        assertEquals(balanceDeReceptorEsperado, receptor.getBalance(), 0.0);

        double balanceDeOwnerEsperado = tokenContract.totalSupply();
        assertEquals(balanceDeOwnerEsperado, tokenContract.balanceOf(owner.getPK()), 0.0);
    }

}
