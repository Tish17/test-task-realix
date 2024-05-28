package org.example;

import java.math.BigInteger;
import java.util.Random;

public class Message {

    private final static int NUM_BITS = 50;
    private final static Random RANDOM = new Random();

    private BigInteger random;

    public Message() {
        this.random = new BigInteger(NUM_BITS, RANDOM);
    }

    public BigInteger getRandom() {
        return random;
    }

    public void setRandom(BigInteger random) {
        this.random = random;
    }
}
