import javax.annotation.processing.SupportedSourceVersion;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private enum AppState{
        Exit,
        MainMenu,
        BuyIPhone,
        BuyAccessory,
        ViewCart;
    }

    static private AppState appState;

    // For the cart
    static private int totalMoney;
    static  IPhoneType chosenIPhoneType;
    static private HashMap<IPhoneType, HashMap<AccessoryType, Integer>> cartOfAccessories;
    static ArrayList<IPhone> cartOfIPhones;
    static private HashMap<IPhoneType, HashMap<Color, Integer>> cartOfCases;
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

    private static void initIPhoneStock()
    {
        addIPhoneSEs();
        addIPhone12s();
        addIPhone13s();
    }

    private static void mainMenu(Scanner inputScanner){
        do{
            try{
                System.out.printf("%60s\n","IMax IPhone Retailing System");
                System.out.println("What do you want to do?");
                System.out.println("1- View All IPhone Stock");
                System.out.println("2- View All Accessories Stock");
                System.out.println("3- View All Stock");
                System.out.println("4- Buy IPhone");
                System.out.println("5- Buy Accessory");
                System.out.println("6- Access Cart");
                System.out.println("7- Exit");

                System.out.print("\nAnswer: ");
                String answer = inputScanner.nextLine();

                switch(answer)
                {
                    case "1" -> {
                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_SE);
                        System.out.print("\n\n");
                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_12);
                        System.out.print("\n\n");
                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_13);
                        System.out.print("\n\n");
                        appState = AppState.MainMenu;
                    }
                    case "2" -> {
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_SE);
                        System.out.print("\n\n");
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_12);
                        System.out.print("\n\n");
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_13);
                        System.out.print("\n\n");
                        appState = AppState.MainMenu;
                    }
                    case "3" -> {
                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_SE);
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_SE);
                        System.out.print("\n\n");

                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_12);
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_12);

                        System.out.print("\n\n");
                        IPhoneStock.Instance().printStockData(IPhoneType.IPHONE_13);
                        AccessoryStock.Instance().printStock(IPhoneType.IPHONE_13);

                        System.out.print("\n\n");

                        appState = AppState.MainMenu;
                    }
                    case "4" -> {
                        appState = AppState.BuyIPhone;
                    }
                    case "5" -> {
                        appState = AppState.BuyAccessory;
                    }
                    case "6" -> {
                        appState = AppState.ViewCart;
                    }
                    case "7", "Exit" -> {
                        appState = AppState.Exit;
                    }
                    default -> {
                        System.out.println("Invalid Input Reenter");
                    }
                }
            }
            catch( Exception e)
            {
                unHandledExceptionFunc(e);
            }
        }while(appState == AppState.MainMenu);
    }

    private static IPhoneType chooseIPhone(Scanner inputScanner) {
        IPhoneType chosenType = null;
        do {
            try {
                System.out.print("Choose IPhone Model:\n");

                for (var choice : IPhoneType.values())
                    System.out.printf("- %-20s\n", choice.toString());

                String input = inputScanner.nextLine();
                for (var name : IPhoneType.values())
                    if (input.equalsIgnoreCase(name.toString()))
                        chosenType = name;

                if (chosenType == null)
                    System.out.println("Invalid Input Reenter\n");
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input Reenter\n");
            }
            catch(Exception e) {
                unHandledExceptionFunc(e);

                System.out.println("Invalid Input Reenter\n");
            }
        }while(chosenType == null);
        return chosenType;
    }

    private static void clearCart()
    {
        for(var phoneType : IPhoneType.values())
        {
            cartOfAccessories.get(phoneType).clear();
            cartOfCases.get(phoneType).clear();
        }
        cartOfIPhones.clear();
    }
    private static void putBackCart()
    {
        for (IPhoneType phoneType : IPhoneType.values())
        {
            for(var acc : cartOfAccessories.get(phoneType).entrySet())
            {
                for (int i = 0; i < acc.getValue(); i++)
                    AccessoryStock.Instance().addToStock(phoneType, acc.getKey());
            }
            for(var caseClr : cartOfCases.get(phoneType).entrySet())
            {
                for (int i = 0; i < caseClr.getValue(); i++)
                    AccessoryStock.Instance().addToStock(phoneType, caseClr.getKey());
            }
        }

        for (IPhone phone : cartOfIPhones)
            IPhoneStock.Instance().emplaceIPhoneBack(phone);

        clearCart();
    }

    private static Storage choseStorage(Scanner inputScanner, ArrayList<Storage> storages)  {
        Storage chosenStorage = null;
        do{
            try{
                System.out.print("Available Storage\n");

                for (var s : storages)
                    System.out.printf("- %s\n", s.toString());

                System.out.print("\nAnswer: ");
                String input = inputScanner.nextLine();
                boolean found = false;
                for (var s : storages){
                    if (input.equalsIgnoreCase(s.toString())) {
                        chosenStorage = s;
                        found = true;
                    }
                }
                if (!found)
                    System.out.println("Invalid Input");
            }
            catch(Exception e)
            {
                unHandledExceptionFunc(e);
            }
        }while(chosenStorage == null);
        return chosenStorage;
    }

    private static Color chooseColor(Scanner inputScanner, ArrayList<Color> iphoneColors) {
        Color chosenColor = null;
        do{
            try{
                System.out.print("Available Colors\n");

                for (var s : iphoneColors)
                    System.out.printf("- %s\n", s.toString());

                System.out.print("\nAnswer: ");
                String input = inputScanner.nextLine();
                boolean found = false;
                for (var s : iphoneColors){
                    if (input.equalsIgnoreCase(s.toString())) {
                        chosenColor = s;
                        found = true;
                    }
                }
                if (!found)
                    System.out.println("Invalid Input");
            }
            catch(Exception e)
            {
                unHandledExceptionFunc(e);
            }
        }while(chosenColor == null);
        return chosenColor;
    }
    private static AccessoryType chooseAccessory(Scanner inputScanner) {
        AccessoryType chosenAccessoryType = null;
        do{
            try{
                System.out.print("Accessories\n");

                for (var acc : AccessoryType.values())
                    System.out.printf("- %s\n", acc);


                System.out.print("\nAnswer: ");
                String input = inputScanner.nextLine();

                boolean found = false;
                for (var s : AccessoryType.values())
                {
                    if (input.equalsIgnoreCase(s.toString())) {
                        chosenAccessoryType = s;
                        found = true;
                    }
                }
                if (!found)
                    System.out.println("Invalid Input");
            }
            catch(Exception e)
            {
                unHandledExceptionFunc(e);
            }
        }while(chosenAccessoryType == null);
        return chosenAccessoryType;
    }

    private static void buyAccessory(Scanner inputScanner)
    {
        if(chosenIPhoneType == null)
            chosenIPhoneType = chooseIPhone(inputScanner);

        AccessoryStock.Instance().printStock(chosenIPhoneType);
        AccessoryType accessoryType = chooseAccessory(inputScanner);

        if (accessoryType == AccessoryType.Case) {
            ArrayList<Color> colors = AccessoryStock.Instance().getCaseColors(chosenIPhoneType);
            Color color = chooseColor(inputScanner, colors);
            int price = AccessoryStock.Instance().buyCase(chosenIPhoneType, color);
            if (price > 0) {
                int count = cartOfCases.get(chosenIPhoneType).get(color);
                cartOfCases.get(chosenIPhoneType).put(color, count + 1);
                System.out.printf("%s case was added to the cart. Costs: %d$\n", color, price);
                totalMoney += price;
                System.out.printf("Total money %d$\n", totalMoney);
            }
            else {
                System.out.print("Out of Stock\n");
            }
        }
        else {
            int price = AccessoryStock.Instance().buyAccessory(chosenIPhoneType, accessoryType);
            if (price > 0)
            {
                System.out.printf("%s was added to the cart. Costs: %d$\n", accessoryType, price);
                int count = cartOfAccessories.get(chosenIPhoneType).get(accessoryType);
                cartOfAccessories.get(chosenIPhoneType).put(accessoryType, count + 1);
                totalMoney += price;
                System.out.printf("Total money %d$\n", totalMoney);
            }
            else {
                System.out.print("Out of Stock");
            }
        }
        chosenIPhoneType = null;
        appState = AppState.MainMenu;
    }

    private static void buyIPhone(Scanner inputScanner)
    {
        IPhoneType type = chooseIPhone(inputScanner);

        IPhoneStock.Instance().printStockData(type);

        ArrayList<Storage> iphoneStorageOptions = IPhoneStock.Instance().getIphoneStorages(type);
        Storage storage = choseStorage(inputScanner, iphoneStorageOptions);
        ArrayList<Color> iphoneColors = IPhoneStock.Instance().getIPhoneColors(type);
        Color color = chooseColor(inputScanner, iphoneColors);
        IPhone chosenIPhone = IPhoneStock.Instance().buyIPhone(type, storage, color);
        if (chosenIPhone == null){
            System.out.print("Chosen IPhone is out of stock\n");
        }
        else {
            System.out.printf("Bought %s Successfully & added to the cart. It costs: %d$\n", chosenIPhone.getModelName(), chosenIPhone.getPrice());
            totalMoney += chosenIPhone.getPrice();
            System.out.printf("Total money in cart: %d$\n", totalMoney);
        }
        cartOfIPhones.add(chosenIPhone);

        System.out.print("Do you want to buy accessories for this IPhone?\n");
        System.out.print("- Yes\n");
        System.out.print("- No\n");
        String answer = null;

        try{
            do{
                String input = inputScanner.nextLine();
                if (input.equalsIgnoreCase("yes"))
                    answer = "yes";
                else if (input.equalsIgnoreCase("no"))
                    answer = "no";
                else
                    System.out.print("Invalid Input, reenter");
            }while(answer == null);
        }
        catch (Exception e)
        {
            unHandledExceptionFunc(e);
            answer = "no";
        }
        if (answer.equalsIgnoreCase("no"))
            appState = AppState.MainMenu;
        else {
            chosenIPhoneType = chosenIPhone.getType();
            appState = AppState.BuyAccessory;
        }
    }

    private static void viewCart(Scanner inputScanner)
    {
        System.out.printf("%d IPhones in cart\n", cartOfIPhones.size());
        for (var phone : cartOfIPhones) {
            System.out.printf("- %-20s (%d$)\n", phone.toString(), phone.getPrice());
            System.out.printf("\tID: %d\n", phone.getId());
        }

        for (IPhoneType phoneType : IPhoneType.values())
        {
            System.out.printf("Chosen Accessories for %s\n\n", phoneType);
            for (var acc : cartOfAccessories.get(phoneType).entrySet())
            {
                if (acc.getValue() > 0)
                    System.out.printf("- %-20s (%d)\n", acc.getKey().toString(), acc.getValue());
            }
            for (var chosenCase : cartOfCases.get(phoneType).entrySet())
            {
                if(chosenCase.getValue() > 0)
                    System.out.printf("- %-20s (%d)\n", chosenCase.getKey().toString(), chosenCase.getValue());
            }
        }

        String choice = null;
        do {
            System.out.print("-------------------------\n");
            System.out.println("- Put back");
            System.out.println("- Buy");
            System.out.println("- Back");

            System.out.print("\nAnswer: ");
            String input = inputScanner.nextLine();
            if (input.equalsIgnoreCase("Buy"))
                choice = "Buy";
            else if (input.equalsIgnoreCase("Put Back"))
                choice = "Put back";
            else if (input.equalsIgnoreCase("Back"))
                choice = "Back";
            else
                System.out.print("Invalid Input renter\n");
        }while(choice == null);

        switch (choice)
        {
            case "Put back" -> {
                putBackCart();
                System.out.print("put back Successfully\n");
                appState = AppState.MainMenu;
            }
            case "Buy" -> {
                clearCart();
                System.out.printf("Paid %d\n", totalMoney);
                totalMoney = 0;
                appState = AppState.MainMenu;
            }
            case "Back" -> {
                appState = AppState.MainMenu;
            }
        }
    }

    private static void unHandledExceptionFunc(Exception e)
    {
        System.err.printf("Unhandled Exception Occurred: %s\n", e.getMessage());
        e.printStackTrace(System.err);
        System.err.print("Continuing Execution\n");
    }

    public static void main(String[] args)
    {
        cartOfAccessories = new HashMap<>();
        cartOfCases = new HashMap<>();
        cartOfIPhones = new ArrayList<>();
        for (var type : IPhoneType.values())
        {
            cartOfCases.put(type, new HashMap<>());
            for (var clr : Color.values())
                cartOfCases.get(type).put(clr, 0);
            cartOfAccessories.put(type, new HashMap<>());
            for (var acc : AccessoryType.values())
                cartOfAccessories.get(type).put(acc, 0);
        }

        appState = AppState.MainMenu;

        Scanner inputScanner = new Scanner(System.in);

        initIPhoneStock();

        do {
            switch (appState) {
                case MainMenu -> {
                    mainMenu(inputScanner);
                }
                case BuyIPhone -> {
                    buyIPhone(inputScanner);
                }
                case BuyAccessory -> {
                    buyAccessory(inputScanner);
                }
                case ViewCart -> {
                    viewCart(inputScanner);
                }
            }
        }
        while(appState != AppState.Exit);
        System.out.print("Thank you for choosing this app!\n");
    }
}
