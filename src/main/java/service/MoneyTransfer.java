package service;

import count.AtomicCounter;
import exception.OutOfMoneyException;
import model.Accaunt;
import model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.locks.ReentrantLock;

public class MoneyTransfer {

    private static final Logger logger = LogManager.getLogger(MoneyTransfer.class);

    public void moneyTransfer(Transfer transfer) throws OutOfMoneyException {

        Accaunt moneySender = transfer.getSender();
        Accaunt moneyRecepient = transfer.getRecepient();
        long moneyTransferAmount = transfer.getTransferAmount();

        ReentrantLock lockOne = moneySender.getId() < moneyRecepient.getId() ?
                moneySender.getLock() : moneyRecepient.getLock();
        ReentrantLock lockTwo = moneySender.getId() > moneyRecepient.getId() ?
                moneySender.getLock() : moneyRecepient.getLock();

        try{
            lockOne.lock();
            lockTwo.lock();

            logger.info("Инициализируется транзакция средств от " + moneySender.getClientName() +
                    " для " + moneyRecepient.getClientName() + " на сумму: " + moneyTransferAmount);
            AtomicCounter.overallCounter.getAndIncrement();
            if(moneySender.getBalance() < moneyTransferAmount){
                AtomicCounter.rejectedCounter.getAndIncrement();
                throw new OutOfMoneyException("На счету " + moneySender.getClientName() + " недостаточно средств");
            }else {
                AtomicCounter.processedCounter.getAndIncrement();
                moneySender.setBalance(moneySender.getBalance() - moneyTransferAmount);
                moneyRecepient.setBalance(moneyRecepient.getBalance() + moneyTransferAmount);
            }
        }finally {
            lockOne.unlock();
            lockTwo.unlock();
        }
    }
}
