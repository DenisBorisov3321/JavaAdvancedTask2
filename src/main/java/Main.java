import count.AtomicCounter;
import model.Accaunt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.AccauntsService;
import threads.TransferThread;
import utilites.CreateAccaunts;
import utilites.CreateTransfer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final int THREADS_COUNT = 20;
    private static final int TRANSACTIONS_COUNT = 1000;

    public static void main(String[] args) {
        CreateAccaunts createAccaunts = new CreateAccaunts();
        CreateTransfer createTransfer = new CreateTransfer();
        AccauntsService accauntsService = new AccauntsService();

        createAccaunts.createAccauntsFiles();
        List<Accaunt> accaunts = accauntsService.getReadedAccaunts();

        logger.info(accaunts);
        logger.info("Изначальный общий баланс: " + accauntsService.getTotalBalance(accaunts) + '\n');

        ExecutorService executorService = Executors.newFixedThreadPool(THREADS_COUNT);

        IntStream.range(0, TRANSACTIONS_COUNT).forEach(thread -> executorService.submit(
                new TransferThread(createTransfer.createTransfer())
        ));

        executorService.shutdown();

        try{
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        }catch (InterruptedException ex){
            logger.error("Ошибка инициализации потока транзакции " + ex);
        }

        logger.info('\n' + "Всего транзакций: " + AtomicCounter.overallCounter.get());
        logger.info("Успешных транзакций: " + AtomicCounter.processedCounter.get());
        logger.info("Отклоненных транзакций: " + AtomicCounter.rejectedCounter.get() + '\n');
        logger.info(accaunts);
        logger.info("Конечный общий баланс: " + accauntsService.getTotalBalance(accaunts) + '\n');
    }
}
