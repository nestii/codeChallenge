package n26.model;


public class Statistic {

    private double sum;
    private double avg;
    private double max;
    private double min;
    private long quantity;

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

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    private double countAvg(final double sum, final long quantity) {
        return quantity == 0 ? 0 : sum / quantity;
    }
}
