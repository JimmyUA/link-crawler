package crawler;

public class WalkerException extends RuntimeException {
    private int amount;
    public WalkerException(Throwable cause, int amount) {
        super(cause);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
