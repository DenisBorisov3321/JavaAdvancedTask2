import service.AccauntsService;
import utilites.CreateAccaunts;

public class Main {
    public static void main(String[] args) {
        CreateAccaunts createAccaunts = new CreateAccaunts();
        AccauntsService accauntsService = new AccauntsService();
        createAccaunts.createAccauntsFiles();

        System.out.println(accauntsService.getReadedAccaunts());
    }
}
