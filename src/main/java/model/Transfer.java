package model;

public class Transfer {

    private Accaunt sender;
    private Accaunt recepient;
    private long transferAmount;

    public Transfer(Accaunt sender, Accaunt recepient, long transferAmount) {
        this.sender = sender;
        this.recepient = recepient;
        this.transferAmount = transferAmount;
    }

    public Accaunt getSender() {
        return sender;
    }

    public Accaunt getRecepient() {
        return recepient;
    }

    public long getTransferAmount() {
        return transferAmount;
    }
}
