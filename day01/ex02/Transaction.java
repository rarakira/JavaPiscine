package ex02;

import java.util.UUID;

public class Transaction {
    private UUID         id;
    private User         recipient;
    private User         sender;
    private Category     category;
    private Integer      amount;

    public Transaction(User sender, User recipient, int amount) {
        if (amount >= 0) {
            category = Category.DEBITS;
            this.amount = amount;
        } else if (amount < 0) {
            category = Category.CREDITS;
            this.amount = amount * -1;
        }
        this.sender = sender;
        this.recipient = recipient;
        this.id = UUID.randomUUID();
    }

    @Override
    public String toString() {
        if (this.category == Category.DEBITS) {
            return "[" + this.id + "] " + this.sender.getName() + " got " + this.amount + " from " + this.recipient.getName();
        } else {
            return "[" + this.id + "] " + this.sender.getName() + " sent " + this.amount + " to " + this.recipient.getName();
        }
    }

    public void printTransactionInfo() {
        System.out.println(this.toString());
    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public enum Category {
        DEBITS,
        CREDITS
    }
}
