package ex02;

public class User {
    private Integer      id;
    private String       name;
    private Integer      balance = 0;

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
