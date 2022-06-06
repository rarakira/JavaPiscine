package ex00;

public class Program {
    public static void main(String[] args) {
        User    mike = new User(1, "Mike", 210);
        User    bob = new User(2, "Bob", 420);
        Transaction send50 = new Transaction(mike, bob, -50);
        Transaction get99 = new Transaction(mike, bob, 99);

        System.out.println("USERS CREATED:");
        mike.printUserInfo();
        bob.printUserInfo();

        System.out.println("\nCREDIT OPERATION -50:");
        send50.printTransactionInfo();
        mike.setBalance(mike.getBalance() - 50);
        bob.setBalance(bob.getBalance() + 50);
        mike.printUserInfo();
        bob.printUserInfo();

        System.out.println("\nDEBIT OPERATION 99:");
        get99.printTransactionInfo();
        mike.setBalance(mike.getBalance() + 99);
        bob.setBalance(bob.getBalance() - 99);
        mike.printUserInfo();
        bob.printUserInfo();

        System.out.println("\nSET NEGATIVE BALANCE:");
        mike.setBalance(-100);

        System.out.println("\nSET INCOGNITO:");
        bob.setName("");
    }
}
