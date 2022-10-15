import javax.annotation.processing.SupportedSourceVersion;
import java.util.ArrayList;
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
    static private IPhone chosenIPhone;
    static private ArrayList<AccessoryType> chosenAccessory;
    static private ArrayList<Color> caseColors;

    static private IPhoneType chosenIPhoneType;
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

    private static void addAirpods() {
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_13, AccessoryType.Airpods, 5);
        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_13, AccessoryType.Airpods, 150);
    }

    private static void addCases() {
        // IPhone SE
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_SE, Color.Red, 2);
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_SE, Color.Black, 1);

        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_SE, AccessoryType.Case, 30);
        // IPhone 12
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_12, Color.Black, 3);
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_12, Color.White, 4);

        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_12, AccessoryType.Case, 40);
        // IPhone 13
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_13, Color.Yellow, 1);
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_13, Color.Green, 1);

        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_13, AccessoryType.Case, 50);
    }

    private static void addMagSafeChargers() {
        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_12, AccessoryType.MagSafeCharger, 3);
        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_12, AccessoryType.MagSafeCharger, 100);

        AccessoryStock.Instance().addToStock(IPhoneType.IPHONE_13, AccessoryType.MagSafeCharger, 2);
        AccessoryStock.Instance().setPrice(IPhoneType.IPHONE_13, AccessoryType.MagSafeCharger, 100);
    }

    private static void initAccessoriesStock()
    {
        addMagSafeChargers();
        addCases();
        addAirpods();
    }

    private static void mainMenu(Scanner inputScanner){
        do{
            try{
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
                String[] choices = {"IPhoneSE", "IPhone12", "IPhone13"};

                for (var choice : choices)
                    System.out.println(choice);

                String input = inputScanner.nextLine();
                if (input.equalsIgnoreCase("IPhoneSE"))
                    chosenType = IPhoneType.IPHONE_SE;
                else if (input.equalsIgnoreCase("IPhone12"))
                    chosenType = IPhoneType.IPHONE_12;
                else if (input.equalsIgnoreCase(("IPhone13")))
                    chosenType = IPhoneType.IPHONE_13;
                else
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
        chosenAccessory.clear();
        caseColors.clear();
        chosenIPhoneType = null;
        chosenIPhone = null;
    }
    private static void putBackCart()
    {
        for (var acc : chosenAccessory)
            AccessoryStock.Instance().addToStock(chosenIPhoneType, acc);
        for (var caseClr : caseColors)
            AccessoryStock.Instance().addToStock(chosenIPhoneType, caseClr);

        if (chosenIPhone != null)
            IPhoneStock.Instance().emplaceIPhoneBack(chosenIPhone);

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
                System.out.print("Available Storage\n");

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
        if (chosenIPhoneType == null) {
            chosenIPhoneType = chooseIPhone(inputScanner);
        }
        else {
            System.out.printf("Choosing for %s\n", chosenIPhoneType);
        }
        AccessoryStock.Instance().printStock(chosenIPhoneType);
        AccessoryType accessoryType = chooseAccessory(inputScanner);

        if (accessoryType == AccessoryType.Case) {
            int price = AccessoryStock.Instance().getPrice(chosenIPhoneType, accessoryType);
            ArrayList<Color> colors = AccessoryStock.Instance().getCaseColors(chosenIPhoneType);
            Color color = chooseColor(inputScanner, colors);
            if (AccessoryStock.Instance().buyCase(chosenIPhoneType, color)) {
                caseColors.add(color);
                System.out.printf("%s case was added to the cart. Costs: %d$\n", color, price);
                totalMoney += price;
                System.out.printf("Total money %d$\n", totalMoney);
            }
            else {
                System.out.print("Out of Stock\n");
            }
        }
        else {
            if (AccessoryStock.Instance().buyAccessory(chosenIPhoneType, accessoryType))
            {
                int price = AccessoryStock.Instance().getPrice(chosenIPhoneType, accessoryType);
                System.out.printf("%s was added to the cart. Costs: %d$\n", accessoryType, price);
                chosenAccessory.add(accessoryType);
                totalMoney += price;
                System.out.printf("Total money %d$\n", totalMoney);
            }
            else {
                System.out.print("Out of Stock");
            }
        }
        appState = AppState.MainMenu;
    }

    private static void buyIPhone(Scanner inputScanner)
    {
        if (chosenIPhone != null)
        {
            System.out.print("Added an IPhone to the cart already. Remove it to buy a new iphone\n");
            appState = AppState.MainMenu;
            return;
        }
        IPhoneType type = chooseIPhone(inputScanner);

        IPhoneStock.Instance().printStockData(type);

        ArrayList<Storage> iphoneStorageOptions = IPhoneStock.Instance().getIphoneStorages(type);
        Storage storage = choseStorage(inputScanner, iphoneStorageOptions);
        ArrayList<Color> iphoneColors = IPhoneStock.Instance().getIPhoneColors(type);
        Color color = chooseColor(inputScanner, iphoneColors);
        chosenIPhone = IPhoneStock.Instance().buyIPhone(type, storage, color);
        if (chosenIPhone == null){
            System.out.print("Chosen IPhone is out of stock\n");
        }
        else {
            System.out.printf("Bought %s Successfully & added to the cart. It costs: %d$\n", chosenIPhone.getModelName(), chosenIPhone.getPrice());
            totalMoney += chosenIPhone.getPrice();
            System.out.printf("Total money in cart: %d$\n", totalMoney);
        }
        appState = AppState.MainMenu;
    }

    private static void viewCart(Scanner inputScanner)
    {
        String modelName = (chosenIPhone == null) ? "NONE" : chosenIPhone.getModelName();
        System.out.printf("Chosen IPhone: %-20s\n", modelName);

        if (chosenIPhone != null)
            System.out.printf("Phone ID: %d\n", chosenIPhone.getId());

        if (caseColors.size() > 0 || chosenAccessory.size() > 0)
            System.out.printf("Chosen Accessories for %s:\n", chosenIPhoneType);

        for (var acc : chosenAccessory)
            System.out.printf("\t- %s\n", acc);

        if (caseColors.size() > 0)
            System.out.print("Cases of Colors:\n");
        for (var clr : caseColors)
            System.out.printf("\t- %s\n", clr);

        String choice = null;
        do {
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
                System.out.print("Renter\n");
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
        appState = AppState.MainMenu;
        Scanner inputScanner = new Scanner(System.in);
        chosenIPhone = null;
        chosenIPhoneType = null;
        chosenAccessory = new ArrayList<>();
        caseColors = new ArrayList<>();

        initIPhoneStock();
        initAccessoriesStock();

        System.out.printf("%60s\n","IMax IPhone Retailing System");

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
