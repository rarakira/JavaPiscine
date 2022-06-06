package ex03;

import java.util.UUID;

public class User {
    private Integer                     id;
    private String                      name;
    private Integer                     balance = 0;
    private TransactionsLinkedList      transactions = new TransactionsLinkedList();

    public User(String name, int balance) {
        if (!this.setBalance(balance) || !this.setName(name)) {
            System.exit(-1);
        }
        this.id = UserIdsGenerator.getInstance().generateId();
    }

    @Override
    public String toString() {
        return "[" + this.id + "] " + this.name + " has " + this.balance + " on their balance.";
    }

    public void printUserInfo() {
        System.out.println(this.toString());
    }

    public void printUserTransactions() {
        this.transactions.printTransactionsList();
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.addTransaction(transaction);
    }

    public void removeTransaction(UUID id) {
        this.transactions.removeTransactionById(id);
    }

    public Transaction [] exportTransactionsToArray() {
        return this.transactions.toArray();
    }

    public int getTransactionCount() {
        return this.transactions.getLength();
    }

    private boolean setBalance(int balance) {
        if (balance < 0) {
            System.err.println(">> The balance can not be negative!");
            return false;
        }
        this.balance = balance;
        return true;
    }

    private void setId(int id) {
        this.id = id;
    }

    private boolean setName(String name) {
        if (name.equals("")) {
            System.err.println(">> The Name can not be empty!");
            return false;
        }
        this.name = name;
        return true;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getBalance() {
        return balance;
    }
}
