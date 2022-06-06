package ex02;

public class UsersArrayList implements UsersList {
    private Integer count = 0;
    private Integer defaultSize = 10;
    private User [] array;

    public UsersArrayList() {
        this.array = new User[this.defaultSize];
    }

    public int getCurrentSize() {
        return this.defaultSize;
    }

    @Override
    public void addUser(User newUser) {
        if (count >= defaultSize) {
            this.defaultSize += defaultSize / 2;
            User [] tmp = new User[this.defaultSize];
            for (int i = 0; i < this.count; i++) {
                tmp[i] = this.array[i];
            }
            this.array = tmp;
        }
        this.array[count] = newUser;
        this.count++;
    }

    @Override
    public User findUserById(int id) {
        if (id >= 0) {
            for (int i = 0; i < this.count; i++) {
                if (this.array[i].getId() == id) {
                    return this.array[i];
                }
            }
        }
        throw new UserNotFoundException("Error: User with ID: " + id + " can not be found.");
    }

    @Override
    public User findUserByIndex(int index) {
        if (index < this.count && index >= 0) {
            return this.array[index];
        }
        throw new UserNotFoundException("Error: User with index: " + index + " can not be found.");
    }

    @Override
    public int countUsers() {
        return this.count;
    }
}
