package edu.craptocraft;

import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

public class Address {
    private PublicKey publicKey;
    private PrivateKey privateKey;

    private final String symbol = "EZI";

    private Double balance = 0d;



    public Address() {}

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append(publicKey.hashCode() + "\n")
                .append("EZI: " + balance);
        return sb.toString();
    }

    public void generateKeyPair(){
        KeyPair keyPair = GenSig.generateKeyPair();
        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();
    }

    public PublicKey getPK() {
        return publicKey;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void transferEZI(double ezi) {
        this.balance += ezi;
    }

    public void send(TokenContract contract, double ezi) {
        if (this.balance >= ezi){
            contract.payable(this, ezi);
            this.balance -= ezi;

        }
    }
}
