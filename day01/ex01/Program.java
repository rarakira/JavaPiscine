package ex01;

public class Program {
    public static void main(String[] args) {
        User [] users = new User[10];
        for (int i = 0; i < 10; i++) {
            users[i] = new User("Name" + i, 10 * i);
        }
        for (int i = 0; i < 10; i++) {
            users[i].printUserInfo();
        }
    }
}
