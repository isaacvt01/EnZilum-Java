package edu.craptocraft;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class TokenContract {

    private String name;
    private String symbol;

    private double stock;

    private double tokenPrice;

    private final PublicKey owner;

    private Map<PublicKey, Double> balances = new HashMap<>();

    public TokenContract(Address owner) {
        this.owner = owner.getPK();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String name() {
        return name;
    }

    public String symbol() {
        return symbol;
    }

    public double totalSupply() {
        return stock;
    }

    public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    @Override
    public String toString() {
        return "TokenContract: " +
                "name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", stock=" + stock;
    }


    public void setTotalSupply(int stock) {
        this.stock = stock;
    }

    public void addOwner(PublicKey publicKey, Double stock) {
        balances.putIfAbsent(publicKey, stock);
    }

    public int numOwners() {
        return balances.size();
    }

    public Double balanceOf(PublicKey pk) {
        return balances.getOrDefault(pk, 0d);
    }

    public void transfer(PublicKey pk, double tokens) {
        try {
            // Llamada al método require
            require(tokens);
            this.stock -= tokens;
            if (balances.containsKey(pk)) {
                balances.put(pk, balances.get(pk) + tokens);
            } else {
                balances.putIfAbsent(pk, tokens);
            }
        } catch (Exception e) {
            // Manejar la excepción
            System.out.println("No hay tokens suficientes :(");
            return; // O realizar cualquier otra acción necesaria
        }

// Continuar con el resto del código si se cumplió la condició

        balances.put(owner, this.stock);
    }

    public void transfer(PublicKey vendedor, PublicKey comprador, double tokens) {
        if (balances.get(vendedor) >= tokens) {
            if (balances.containsKey(comprador)) {
                balances.put(comprador, balances.get(comprador) + tokens);
            } else {
                balances.putIfAbsent(comprador, tokens);
            }
            balances.put(vendedor, balances.get(vendedor) - tokens);
        }
    }

    private boolean require(double tokens) throws Exception {
        if (tokens <= this.stock) {
            return true;
        } else {
            throw new Exception("No quedan tokens suficientes :(");
        }
    }

    public Double totalTokensSold() {
        Optional<Double> numAsistentes = balances.entrySet().stream()
                .filter(entry -> entry.getKey() != owner)
                .map(entry -> entry.getValue())
                .reduce(Double::sum);

        return numAsistentes.isPresent() ? numAsistentes.get() : 0d;
    }

    public void owners() {
        balances.entrySet().stream()
                .filter(entry -> entry.getKey() != owner)
                .forEach(entry -> System.out.println(entry.getKey().hashCode()));
    }



    public void payable(Address receptor, Double ezi) {
        try {
            if (ezi >= this.tokenPrice) {
                Double unitsNumber = Math.floor(ezi / tokenPrice);
                transfer(receptor.getPK(), unitsNumber);
            }
        } catch (Exception e) {

        }
    }
}