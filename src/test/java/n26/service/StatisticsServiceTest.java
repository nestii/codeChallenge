package n26.service;

import n26.model.Statistic;
import n26.model.Transaction;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class StatisticsServiceTest {

    private static final long CURRENT_TIMESTAMP = Timestamp.valueOf("2017-05-27 10:10:10.0").getTime();

    @InjectMocks
    private StatisticsService statisticsService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnTrueIfTransactionOlderThanMinute() {
        // given
        final Timestamp transactionTimestamp = Timestamp.valueOf("2017-05-27 10:09:10.0");
        // when
        final boolean isTransactionOlderThanMinute = statisticsService.isTransactionOlderThanMinute(createTransaction(0.0, transactionTimestamp.getTime()), CURRENT_TIMESTAMP);
        // then
        assertTrue(isTransactionOlderThanMinute);
    }

    @Test
    public void shouldReturnFalseIfTransactionOlderThanMinute() {
        // given
        final Timestamp transactionTimestamp = Timestamp.valueOf("2017-05-27 10:10:01.0");
        // when
        final boolean isTransactionOlderThanMinute = statisticsService.isTransactionOlderThanMinute(createTransaction(0.0, transactionTimestamp.getTime()), CURRENT_TIMESTAMP);
        // then
        assertFalse(isTransactionOlderThanMinute);
    }

    private Transaction createTransaction(final double amount, final long timestamp) {
        final Transaction t = new Transaction();
        t.setAmount(amount);
        t.setTimestamp(timestamp);
        return t;
    }

}
