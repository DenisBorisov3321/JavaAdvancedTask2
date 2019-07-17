package utilites;

import model.Accaunt;
import model.Transfer;
import service.AccauntsService;

import java.util.List;
import java.util.Random;

public class CreateTransfer {

    private Random random = new Random();
    private AccauntsService accauntsService = new AccauntsService();
    private List<Accaunt> accaunstList = accauntsService.getReadedAccaunts();

    public Transfer createTransfer(){
        return new Transfer(accaunstList.get(random.nextInt(accaunstList.size())),
                accaunstList.get(random.nextInt(accaunstList.size())), random.nextInt(1000));
    }
}
