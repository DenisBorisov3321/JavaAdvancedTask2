package service;

import model.Accaunt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilites.CreateAccaunts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class AccauntsService {

    private static final Logger logger = LogManager.getLogger(AccauntsService.class);

    private CreateAccaunts createAccaunts = new CreateAccaunts();

    private List<Accaunt> readedAccaunts = new ArrayList<>();
    private IntStream readedAccauntsStream = IntStream.range(0, createAccaunts.getAccauntsMaxAmmount());

    public List<Accaunt> getReadedAccaunts(){
        readedAccauntsStream.forEach(acccauntFileNum ->{
            File file = new File(createAccaunts.getAccauntsRepository() + acccauntFileNum);
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
