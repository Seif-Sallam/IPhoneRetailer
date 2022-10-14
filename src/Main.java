import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static void addIPhoneSEs()
    {
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_SE, Storage.GB_64, Color.White, 300, 2);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_SE, Storage.GB_64, Color.Black, 300, 3);
    }

    private static void addIPhone12s()
    {
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_64, Color.White, 350, 1);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_64, Color.Black, 350, 4);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_64, Color.Blue, 350, 2);

        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_128, Color.White, 400, 2);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_128, Color.Black, 400, 3);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_12, Storage.GB_128, Color.Blue, 400, 4);
    }

    private static void addIPhone13s()
    {
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_128, Color.White, 450, 2);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_128, Color.Black, 450, 3);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_128, Color.Red, 450, 1);

        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_256, Color.White, 500, 3);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_256, Color.Black, 500, 1);
        IPhoneStock.Instance().addToStock(IPhoneType.IPHONE_13, Storage.GB_256, Color.Red, 500, 2);
    }


    private static void initStock()
    {
        addIPhoneSEs();
        addIPhone12s();
        addIPhone13s();
    }

    public static void main(String[] args) {
        initStock();
        IPhoneStock.Instance().printStockData();

        try{
            System.exit(0);
        }
        catch(InputMismatchException e)
        {

        }
        catch(Exception e)
        {
            System.out.println("Generic Exception Triggered");
            System.out.println("Stack Trace\n");
            e.printStackTrace();
        }
    }
}
