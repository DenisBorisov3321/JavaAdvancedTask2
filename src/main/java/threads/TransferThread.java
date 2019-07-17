package threads;

import exception.OutOfMoneyException;
import model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.MoneyTransfer;

public class TransferThread implements Runnable{

    private static final Logger logger = LogManager.getLogger(TransferThread.class);

    private Transfer transfer;
    private MoneyTransfer moneyTransfer = new MoneyTransfer();

    public TransferThread(Transfer transfer){
        this.transfer = transfer;
    }

    @Override
    public void run(){
        try {
            moneyTransfer.moneyTransfer(transfer);
        }catch (OutOfMoneyException ex){
            logger.error("Ошибка транзакции " + ex);
        }
    }
}
