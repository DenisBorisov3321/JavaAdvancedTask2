package service;

import model.Accaunt;
import model.Transfer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MoneyTransfer {

    private static final Logger logger = LogManager.getLogger(MoneyTransfer.class);

    public void moneyTransfer(Transfer transfer){

        Accaunt moneySender = transfer.getSender();
        Accaunt moneyRecepient = transfer.getRecepient();
        long moneyTransferAmount = transfer.getTransferAmount();
    }
}
