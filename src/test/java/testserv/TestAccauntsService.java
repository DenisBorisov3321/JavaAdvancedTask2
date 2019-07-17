package testserv;

import model.Accaunt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import testutil.CreateTestAccaunts;
import utilites.CreateAccaunts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TestAccauntsService {

    private static final Logger logger = LogManager.getLogger(TestAccauntsService.class);

    private CreateTestAccaunts createTestAccaunts = new CreateTestAccaunts();

    private List<Accaunt> readedAccaunts = new ArrayList<>();
    private IntStream readedAccauntsStream = IntStream.range(0, createTestAccaunts.getAccauntsMaxAmmount());

    public List<Accaunt> getReadedTestAccaunts(){
        readedAccauntsStream.forEach(acccauntFileNum ->{
            File file = new File(createTestAccaunts.getAccauntsRepository() + acccauntFileNum);
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                Accaunt accaunt = (Accaunt) objectInputStream.readObject();
                readedAccaunts.add(accaunt);
            }catch (IOException | ClassNotFoundException ex){
                logger.error("Ошибка чтения файлов", ex);
            }
        });
        return readedAccaunts;
    }

    public long getTotalBalance(List<Accaunt> accauntsList){
        return accauntsList.stream().mapToLong(Accaunt::getBalance).sum();
    }
}
