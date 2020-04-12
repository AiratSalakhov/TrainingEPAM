package salakhov.lesson;

public class Account {
    private volatile int accout;

    public Account(int accout) {
        this.accout = accout;
    }

    public int getAccout() {
        return accout;
    }

    public void setAccout(int accout) {
        this.accout = accout;
    }

    @Override
    public String toString() {
        return String.valueOf(this.accout);
    }
}
