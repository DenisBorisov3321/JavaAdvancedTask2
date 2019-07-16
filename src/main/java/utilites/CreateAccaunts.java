package utilites;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class CreateAccaunts {

    private static final Logger logger = LogManager.getLogger(CreateAccaunts.class);

    private static final int ACCAUNT_MAX_AMMOUNT = 10;
    private static final String ACCAUNTS_REPOSITORY = "src/main/resources/accaunts";

    private Random random = new Random();

    public void createAccantsFiles(){

    }

    private void deleteAccauntsFiles(){
        try{
            FileUtils.cleanDirectory(new File(ACCAUNTS_REPOSITORY));
        }catch (IOException ex){
            logger.error("ошибка чтения файлов ...", ex);
        }
    }
}
