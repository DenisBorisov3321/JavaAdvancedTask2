import model.Accaunt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import testserv.TestAccauntsService;
import threads.TransferThread;
import testutil.CreateTestAccaunts;
import utilites.CreateTransfer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class UpLoadTestRun {

    private CreateTestAccaunts createTestAccaunts = new CreateTestAccaunts();
    private CreateTransfer createTransfer = new CreateTransfer();
    private TestAccauntsService testAccauntsService = new TestAccauntsService();

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final int TEST_THREADS_COUNT = 10;
    private static final int TEST_TRANSACTIONS_COUNT = 1000;

    @Test
    public void upLoadTestRun(){

        createTestAccaunts.createTestAccauntsFiles();

        ExecutorService executorService = Executors.newFixedThreadPool(TEST_THREADS_COUNT);
        List<Accaunt> accaunts = testAccauntsService.getReadedTestAccaunts();

        long sumBefore = testAccauntsService.getTotalBalance(accaunts);

        IntStream.range(0, TEST_TRANSACTIONS_COUNT).forEach(thread -> executorService.submit(
                new TransferThread(createTransfer.createTransfer())
        ));

        executorService.shutdown();

        try{
            executorService.awaitTermination(30, TimeUnit.SECONDS);
        }catch (InterruptedException ex){
            logger.error("Ошибка инициализации потока транзакции " + ex);
        }

        long sumAfter = testAccauntsService.getTotalBalance(accaunts);

        Assert.assertEquals(sumBefore, sumAfter);
    }
}
