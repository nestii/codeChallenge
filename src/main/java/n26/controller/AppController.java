package n26.controller;

import n26.model.Statistic;
import n26.model.Transaction;
import n26.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AppController {

    private final StatisticsService statisticsService;

    @Autowired
    public AppController(final StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/statistics")
    public ResponseEntity<Statistic> getStatistics() {
        return new ResponseEntity<>(statisticsService.getStatistic(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    public ResponseEntity<Void> addTransaction(@RequestBody final Transaction transaction) {
        long currentTimestamp = System.currentTimeMillis();

        if(statisticsService.isTransactionOlderThanMinute(transaction, currentTimestamp)) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204
        }
        statisticsService.addTransaction(transaction);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
