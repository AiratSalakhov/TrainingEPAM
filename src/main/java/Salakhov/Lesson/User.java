package Salakhov.Lesson;

public class User {
    private String fio;

    public enum Roles {ADMIN, USER, MODERATOR;}

    private Roles role;

    public String getFio() {
        return this.fio;
    }

    public Roles getRole() {
        return this.role;
    }

    public User(String fio, Roles role) {
        this.fio = fio;
        this.role = role;
    }

    public void greeting() {
        System.out.println("Приветствуем " + fio + " с ролью " + role);
    }
}

