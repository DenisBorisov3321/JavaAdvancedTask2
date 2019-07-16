package model;

import enums.Person;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

public class Accaunt implements Serializable {

    private long id;
    private Person client;
    private long balance;

    private ReentrantLock lock = new ReentrantLock();

    public Accaunt(long id, Person client, long balance) {
        this.id = id;
        this.client = client;
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "id: " + id +
               ", client: " + client.getPerson() +
               ", balance: " + balance;
    }
}
