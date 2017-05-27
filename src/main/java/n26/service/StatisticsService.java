package n26.service;

import n26.model.Statistic;
import n26.model.Transaction;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Service
public class StatisticsService {

    private static final long MINUTE_IN_MILLIS = TimeUnit.SECONDS.toMillis(60);

    private final ConcurrentLinkedQueue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    private volatile Statistic statistic;

    public void addTransaction(final Transaction transaction) {
        transactions.add(transaction);
    }

    public Statistic getStatistic() {
        return statistic;
    }

    public boolean isTransactionOlderThanMinute(final Transaction transaction, final long currentTime) {
        return currentTime - transaction.getTimestamp() >= MINUTE_IN_MILLIS;
    }

    @Scheduled(fixedDelay = 10)
    public void updateQueue() {
        long currentTimestamp = System.currentTimeMillis();
        double sum = 0;
        double max = 0;
        double min = transactions.isEmpty() ? 0 : Double.MAX_VALUE;
        long quantity = 0;

        for(final Transaction t: transactions) {
            if(isTransactionOlderThanMinute(t, currentTimestamp)) {
                transactions.remove(t);
            } else {
                double amount = t.getAmount();
                sum += amount;
                max = Math.max(amount, max);
                min = Math.min(amount, min);
                quantity++;
            }
        }
        statistic = new Statistic(sum, max, min, quantity);
    }

}
