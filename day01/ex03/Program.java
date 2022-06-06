package ex03;

import java.util.UUID;

public class Program {
    public static void main(String[] args) {
        User mike = new User("Mike", 210);
        User bob = new User("Bob", 420);
        Transaction send50 = new Transaction(mike, bob, -50);
        Transaction get99 = new Transaction(mike, bob, 99);
        Transaction send150 = new Transaction(mike, bob, -150);
        Transaction get199 = new Transaction(mike, bob, 199);

        System.out.println("\nTESTING MIKE:");
        mike.addTransaction(send50);
        mike.addTransaction(get99);
        mike.printUserInfo();
        mike.printUserTransactions();

        System.out.println("\nTESTING BOB:");
        bob.printUserInfo();
        bob.printUserTransactions();
        bob.addTransaction(send50);
        bob.addTransaction(get99);
        bob.addTransaction(send150);
        bob.addTransaction(get199);
        bob.printUserTransactions();

        System.out.println("\nREMOVE FROM BOB:");
        bob.removeTransaction(send150.getId());
        bob.printUserTransactions();

        System.out.println("\nCREATE ARRAY and LIST ALL ELEMENTS:");
        Transaction [] bobsTransactions = bob.exportTransactionsToArray();
        for (int i = 0; i < bob.getTransactionCount(); i++) {
            bobsTransactions[i].printTransactionInfo();
        }

        System.out.println("\nREMOVE ALL FROM MIKE:");
        mike.removeTransaction(send50.getId());
        mike.removeTransaction(get99.getId());
        mike.printUserTransactions();

        System.out.println("\nEXCEPTION EXAMPLES:");
        try {
            mike.removeTransaction(UUID.randomUUID());
        } catch (TransactionNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
