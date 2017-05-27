package n26.model;


public class Statistic {

    private final double sum;
    private final double avg;
    private final double max;
    private final double min;
    private final long quantity;

    public Statistic(final double sum, final double max, final double min, final long quantity) {
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.quantity = quantity;
        this.avg = countAvg(sum, quantity);
    }

    public double getSum() {
        return sum;
    }

    public double getAvg() {
        return avg;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    public long getQuantity() {
        return quantity;
    }

    private double countAvg(final double sum, final long quantity) {
        return quantity == 0 ? 0 : sum / quantity;
    }
}
