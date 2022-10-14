import java.util.*;

public class IPhoneStock {
    private static HashMap<Storage, HashMap<Color, Stack<IPhone12>>> iphone12Stock;
    private static HashMap<Storage, HashMap<Color, Stack<IPhone13>>> iphone13Stock;
    private static HashMap<Storage, HashMap<Color, Stack<IPhoneSE>>> iphoneSEStock;

    private static IPhoneStock instance;

    public static IPhoneStock Instance() {
        if (instance == null)
            instance = new IPhoneStock();
        return instance;
    }

    private IPhoneStock()
    {
        iphone12Stock = new HashMap<>();
        iphoneSEStock = new HashMap<>();
        iphone13Stock = new HashMap<>();

        // Populating the stock with compatible storages
        // IPhone 12
        iphone12Stock.put(Storage.GB_64, new HashMap<>());
        iphone12Stock.put(Storage.GB_128, new HashMap<>());

        // IPhone SE
        iphoneSEStock.put(Storage.GB_64, new HashMap<>());

        // IPhone 13
        iphone13Stock.put(Storage.GB_128, new HashMap<>());
        iphone13Stock.put(Storage.GB_256, new HashMap<>());


        // Populating the stock with the compatible colors
        // IPhone 12
        for (final var map : iphone12Stock.values())
        {
            map.put(Color.Black, new Stack<>());
            map.put(Color.White, new Stack<>());
            map.put(Color.Blue, new Stack<>());
        }

        // IPhone 13
        for (final var map : iphone13Stock.values())
        {
            map.put(Color.Black, new Stack<>());
            map.put(Color.White, new Stack<>());
            map.put(Color.Red, new Stack<>());
        }

        // IPhone SE
        for (final var map : iphoneSEStock.values())
        {
            map.put(Color.Black, new Stack<>());
            map.put(Color.White, new Stack<>());
        }
    }

    public void addToStock(IPhoneType type, Storage storage, Color color, int price, int count)
    {
        for (int i = 0; i < count; i++)
        {
            switch(type)
            {
                case IPHONE_13 -> iphone13Stock.get(storage).get(color).add(new IPhone13(storage, color, price));
                case IPHONE_12 -> iphone12Stock.get(storage).get(color).add(new IPhone12(storage, color, price));
                case IPHONE_SE -> iphoneSEStock.get(storage).get(color).add(new IPhoneSE(storage, color, price));
            }
        }
    }

    public IPhone buyIPhone(IPhoneType type, Storage storage, Color color)
    {
        IPhone outputIPhone = null;
        try {

            switch (type) {
                case IPHONE_12 -> {
                    if (iphone12Stock.get(storage).get(color).size() > 0) {
                        outputIPhone = iphone12Stock.get(storage).get(color).firstElement();
                        iphone12Stock.get(storage).get(color).pop();
                    }
                }
                case IPHONE_13 -> {
                    if (iphone13Stock.get(storage).get(color).size() > 0) {
                        outputIPhone = iphone13Stock.get(storage).get(color).firstElement();
                        iphone13Stock.get(storage).get(color).pop();
                    }
                }
                case IPHONE_SE -> {
                    if (iphoneSEStock.get(storage).get(color).size() > 0) {
                        outputIPhone = iphoneSEStock.get(storage).get(color).firstElement();
                        iphoneSEStock.get(storage).get(color).pop();
                    }
                }
            }
        }
        catch(NullPointerException exception)
        {
            System.err.printf("Null Pointer Exception occurred in function: buyIphone\nMessage: %s\n", exception.getMessage());
            exception.printStackTrace(System.err);
            System.err.print("Returning NULL\n");

            return null;
        }
        catch(Exception exception)
        {
            System.err.printf("Generic Exception Occurred.\nMessage: %s\n", exception.getMessage());
            exception.printStackTrace(System.err);
            System.err.print("Returning NULL\n");

            return null;
        }

        return outputIPhone;
    }
    public void printStockData()
    {
        System.out.println("Available Stock of IPhones:");

        System.out.println("IPhone SE:");

        for (var storageEntry : iphoneSEStock.entrySet())
        {
            System.out.printf("\tStorage: %10s\n", storageEntry.getKey());
            for (var colorEntry : storageEntry.getValue().entrySet())
            {
                System.out.printf("\t\t-%10s (%d)", colorEntry.getKey(), colorEntry.getValue().size());
                if (colorEntry.getValue().size() > 0)
                {
                    int price  = colorEntry.getValue().get(0).getPrice();
                    System.out.printf(" (%d$)", price);
                }
                else {
                    System.out.print(" (Out of Stock)");
                }

                for (var phone : colorEntry.getValue())
                {
                    System.out.printf(" %d, ", phone.getId());
                }

                System.out.print("\n");
            }
        }
        System.out.print("\n");

        System.out.println("IPhone 12:");

        for (var storageEntry : iphone12Stock.entrySet())
        {
            System.out.printf("\tStorage: %10s\n", storageEntry.getKey());
            for (var colorEntry : storageEntry.getValue().entrySet())
            {
                System.out.printf("\t\t-%10s (%d)", colorEntry.getKey(), colorEntry.getValue().size());
                if (colorEntry.getValue().size() > 0)
                {
                    int price = colorEntry.getValue().get(0).getPrice();
                    System.out.printf(" (%d$)", price);
                }
                else {
                    System.out.print(" (Out of Stock)");
                }

                for (var phone : colorEntry.getValue())
                {
                    System.out.printf(" %d, ", phone.getId());
                }

                System.out.print("\n");
            }
        }
        System.out.print("\n");

        System.out.println("IPhone 13:");

        for (var storageEntry : iphone13Stock.entrySet())
        {
            System.out.printf("\tStorage: %10s\n", storageEntry.getKey());
            for (var colorEntry : storageEntry.getValue().entrySet())
            {
                System.out.printf("\t\t-%10s (%d)", colorEntry.getKey(), colorEntry.getValue().size());
                if (colorEntry.getValue().size() > 0)
                {
                    int price = colorEntry.getValue().get(0).getPrice();
                    System.out.printf(" (%d$)", price);
                }
                else {
                    System.out.print(" (Out of Stock)");
                }

                for (var phone : colorEntry.getValue())
                {
                    System.out.printf(" %d, ", phone.getId());
                }
                System.out.print("\n");
            }
        }
    }
}
