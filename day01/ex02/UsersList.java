package ex02;

public interface UsersList {
    void addUser(User newUser);
    User findUserById(int id);
    User findUserByIndex(int index);
    int countUsers();
}
