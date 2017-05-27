package n26.model;

public class Transaction {

    private final double amount;
    private final long timestamp;

    public Transaction(final double amount, final long timestamp) {
        this.amount = amount;
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
