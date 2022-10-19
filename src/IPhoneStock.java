import java.util.*;

// The stock of IPhones acts as an inventory manager.
public class IPhoneStock {
    // All the stock of each iphone variation
    private static HashMap<Storage, HashMap<Color, Stack<IPhone>>> iphone12Stock;
    private static HashMap<Storage, HashMap<Color, Stack<IPhone>>> iphone13Stock;
    private static HashMap<Storage, HashMap<Color, Stack<IPhone>>> iphoneSEStock;

    // It is a singleton because we will only have one inventory per system.
    private static IPhoneStock instance;

    public static IPhoneStock Instance() {
        if (instance == null)
            instance = new IPhoneStock();
        return instance;
    }

    // Initializes the stock, it is private because we do not want it to be created.
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

    // Adds to the stock the correct IPhone.
    public void addToStock(IPhoneType type, Storage storage, Color color, int price)
    {
        switch(type)
        {
            case IPHONE_13 -> iphone13Stock.get(storage).get(color).add(new IPhone13(storage, color, price));
            case IPHONE_12 -> iphone12Stock.get(storage).get(color).add(new IPhone12(storage, color, price));
            case IPHONE_SE -> iphoneSEStock.get(storage).get(color).add(new IPhoneSE(storage, color, price));
        }
    }

    // Adds to the stock an IPhone more than once
    public void addToStock(IPhoneType type, Storage storage, Color color, int price, int count)
    {
        for (int i = 0; i < count; i++)
            addToStock(type, storage, color, price);
    }

    // Buys an IPhone if exists in the stock.
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
            // This is an exception that means that the function took wrong parameters.
            // A very lazy way to "error check" the parameters.

            //  System.err.printf("Null Pointer Exception occurred in function: buyIphone\nMessage: %s\n", exception.getMessage());
            //  exception.printStackTrace(System.err);
            //  System.err.print("Returning NULL\n");

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

    // Returning the IPhone to its own stock (preserving the ID it was given from the beginning).
    public void emplaceIPhoneBack(IPhone phone)
    {
        switch (phone.getType())
        {
            case IPHONE_SE -> {
                iphoneSEStock.get(phone.getModelCapacity()).get(phone.getModelColor()).push(phone);
            }
            case IPHONE_13 -> {
                iphone13Stock.get(phone.getModelCapacity()).get(phone.getModelColor()).push(phone);
            }
            case IPHONE_12 -> {
                iphone12Stock.get(phone.getModelCapacity()).get(phone.getModelColor()).push(phone);
            }
        }
    }

    // Helper function that prints the entire stock data of a specific IPhone
    private void printStockData(HashMap<Storage, HashMap<Color, Stack<IPhone>>> stock)
    {
        for (var storageEntry : stock.entrySet())
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

                System.out.print("\n");
            }
        }
    }

    // General function that takes the iphone type and prints its stock
    public void printStockData(IPhoneType type)
    {
        System.out.printf("Available Stock of %s:\n", type);
        switch (type)
        {
            case IPHONE_SE -> {
                printStockData(iphoneSEStock);
            }
            case IPHONE_12 -> {
                printStockData(iphone12Stock);
            }
            case IPHONE_13 -> {
                printStockData(iphone13Stock);
            }
        }
        System.out.println();
    }

    // Getting the iphone colors available. unlike the accessories, the iphone stock is the one
    // that knows about the iphone colors not the actual iphone. Since the iphone is indifferent about it.
    public ArrayList<Color> getIPhoneColors(IPhoneType type) {
        ArrayList<Color> colors = new ArrayList<>();
        switch(type){
            case IPHONE_12 -> {
                // We only need one example from the stock, that is why we break.
                for (var v : iphone12Stock.values())
                {
                    colors.addAll(v.keySet());
                    break;
                }
            }
            case IPHONE_13 -> {
                for (var v : iphone13Stock.values())
                {
                    colors.addAll(v.keySet());
                    break;
                }
            }
            case IPHONE_SE -> {
                for (var v : iphoneSEStock.values())
                {
                    colors.addAll(v.keySet());
                    break;
                }
            }
        }
        return colors;
    }

    // Gets the storages of the iphone.
    public ArrayList<Storage> getIphoneStorages(IPhoneType type) {
        ArrayList<Storage> storages = new ArrayList<>();
        switch(type){
            case IPHONE_12 -> {
                storages.addAll(iphone12Stock.keySet());
            }
            case IPHONE_13 -> {
                storages.addAll(iphone13Stock.keySet());
            }
            case IPHONE_SE -> {
                storages.addAll(iphoneSEStock.keySet());
            }
        }
        return storages;
    }
}
