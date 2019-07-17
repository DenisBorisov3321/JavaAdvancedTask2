package testutil;

import enums.Person;
import model.Accaunt;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utilites.CreateAccaunts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CreateTestAccaunts {
    private static final Logger logger = LogManager.getLogger(CreateAccaunts.class);

    private static final int TEST_ACCAUNTS_MAX_AMMOUNT = 2;
    private static final String TEST_ACCAUNTS_REPOSITORY = "src/main/resources/testaccaunts/";

    private IntStream testAccauntsStream = IntStream.range(0, TEST_ACCAUNTS_MAX_AMMOUNT);
    private List<Accaunt> testAccaunts = new ArrayList<>();
    private Person tmpPerson = Person.IRONMAN;

    private Random random = new Random();

    public void createTestAccauntsFiles(){
        clearTestAccauntsRepository();
        testAccauntsStream.forEach(accauntNum -> testAccaunts.add(generateAccaunt(accauntNum)));
        testAccaunts.forEach(this::writeBinFile);
    }

    public int getAccauntsMaxAmmount(){
        return TEST_ACCAUNTS_MAX_AMMOUNT;
    }

    public String getAccauntsRepository(){
        return TEST_ACCAUNTS_REPOSITORY;
    }

    private Accaunt generateAccaunt(int accauntNum){
        return new Accaunt(accauntNum, tmpPerson.getRandomPerson(),
                random.nextInt(5000));
    }
    private void writeBinFile(Accaunt accaunt){
        File file = new File(TEST_ACCAUNTS_REPOSITORY + accaunt.getId());
        try (FileOutputStream fileOutputStream = new FileOutputStream(file);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)){
            objectOutputStream.writeObject(accaunt);
            objectOutputStream.flush();
        }catch (IOException ex){
            logger.error("Не найдено хранилище test файлов", ex);
        }
    }
    private void clearTestAccauntsRepository(){
        try{
            FileUtils.cleanDirectory(new File(TEST_ACCAUNTS_REPOSITORY));
        }catch (IOException ex){
            logger.error("ошибка чтения test файлов ...", ex);
        }
    }
}
