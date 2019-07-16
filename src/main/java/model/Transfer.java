package model;

public class Transfer {

    private Accaunt sender;
    private Accaunt recepient;
    private long transferAmmount;

    public Transfer(Accaunt sender, Accaunt recepient, long transferAmmount) {
        this.sender = sender;
        this.recepient = recepient;
        this.transferAmmount = transferAmmount;
    }

    public Accaunt getSender() {
        return sender;
    }

    public Accaunt getRecepient() {
        return recepient;
    }

    public long getTransferAmmount() {
        return transferAmmount;
    }
}
