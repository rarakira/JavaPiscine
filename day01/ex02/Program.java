package ex02;

public class Program {
    public static void main(String[] args) {
        UsersArrayList usersArr = new UsersArrayList();
        User [] base = new User[20];

        for (int i = 0; i < 20; i++) {
            base[i] = new User("Name" + i, 10 * i);
        }

        System.out.println("User count before loop: " + usersArr.countUsers() + "; Array size: " + usersArr.getCurrentSize());
        for (int i = 0; i < 20; i++) {
            usersArr.addUser(base[i]);
        }
        System.out.println("User count after loop: " + usersArr.countUsers() + "; Array size: " + usersArr.getCurrentSize());

        System.out.println("\nFind by Index:");
        for (int i = 0; i < 20; i++) {
            usersArr.findUserByIndex(i).printUserInfo();
        }

        System.out.println("\nFind by ID: 15");
        usersArr.findUserById(15).printUserInfo();

        System.out.println("\nEXCEPTION EXAMPLES:");
        try {
            usersArr.findUserByIndex(21).printUserInfo();
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            usersArr.findUserById(50).printUserInfo();
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }

        try {
            usersArr.findUserById(-1).printUserInfo();
        } catch (UserNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }
}
