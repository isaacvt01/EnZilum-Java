package edu.craptocraft;

public class Address {
    private String publicKey;
    private String privateKey;

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    private GenSig gensig = new GenSig();

    public Address() {}

    public void generateKeyPair(){
        publicKey = gensig.generateKey();
        privateKey = gensig.generateKey();
    }
}
