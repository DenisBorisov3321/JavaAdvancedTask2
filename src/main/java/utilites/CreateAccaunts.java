package utilites;

import enums.Person;
import model.Accaunt;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CreateAccaunts {

    private static final Logger logger = LogManager.getLogger(CreateAccaunts.class);

    private static final int ACCAUNTS_MAX_AMMOUNT = 10;
    private static final String ACCAUNTS_REPOSITORY = "src/main/resources/accaunts/";

    private IntStream accauntsStream = IntStream.range(0, ACCAUNTS_MAX_AMMOUNT);
    private List<Accaunt> accaunts = new ArrayList<>();
    private Person tmpPerson = Person.IRONMAN;

    private Random random = new Random();

    public void createAccantsFiles(){
        clearAccauntsRepository();
        accauntsStream.forEach(accauntNum -> accaunts.add(generateAccaunt(accauntNum)));
        accaunts.forEach(this::writeBinFile);
    }

    public int getAccauntsMaxAmmount(){
        return ACCAUNTS_MAX_AMMOUNT;
    }

    public String getAccauntsRepository(){
        return ACCAUNTS_REPOSITORY;
    }

    private Accaunt generateAccaunt(int accauntNum){
        return new Accaunt(accauntNum, tmpPerson.getRandomPerson(),
                random.nextInt(5000));
    }
    private void writeBinFile(Accaunt accaunt){
        File file = new File(ACCAUNTS_REPOSITORY + accaunt.getId());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(accaunt);
            objectOutputStream.flush();
        }catch (IOException ex){
            logger.error("Не найдено хранилище файлов", ex);
        }
    }
    private void clearAccauntsRepository(){
        try{
            FileUtils.cleanDirectory(new File(ACCAUNTS_REPOSITORY));
        }catch (IOException ex){
            logger.error("ошибка чтения файлов ...", ex);
        }
    }
}