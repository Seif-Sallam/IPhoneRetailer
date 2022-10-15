import java.util.ArrayList;
import java.util.HashMap;

public class AccessoryStock {

    private static HashMap<IPhoneType, HashMap<AccessoryType, Integer>> accessoryStockMap;
    private static HashMap<IPhoneType, HashMap<AccessoryType, Integer>> accessoryPriceMap;
    private static HashMap<IPhoneType, HashMap<Color, Integer>> casesStockMap;

    private static AccessoryStock instance;

    public static AccessoryStock Instance()
    {
        if (instance == null)
            instance = new AccessoryStock();
        return instance;
    }

    private AccessoryStock()
    {
        // Initialization of the data
        accessoryStockMap = new HashMap<>();
        casesStockMap = new HashMap<>();
        accessoryPriceMap = new HashMap<>();

        for (var iphoneType : IPhoneType.values())
        {
            accessoryStockMap.put(iphoneType, new HashMap<>());
            accessoryPriceMap.put(iphoneType, new HashMap<>());
            for (var accType : AccessoryType.values())
            {
                accessoryStockMap.get(iphoneType).put(accType, 0);
                accessoryPriceMap.get(iphoneType).put(accType, 0);
            }
            casesStockMap.put(iphoneType, new HashMap<>());
        }

    }

    public boolean buyAccessory(IPhoneType iPhoneType, AccessoryType type)
    {
        try {
            var map = accessoryStockMap.get(iPhoneType);

            int count = map.get(type);
            if (count == 0)
                return false;

            map.put(type, count - 1);
            return true;
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: buyAccessory\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
            return false;
        }
    }

    public boolean buyCase(IPhoneType iPhoneType, Color color)
    {
        try {
            var map = accessoryStockMap.get(iPhoneType);
            var totalCasesCount = map.get(AccessoryType.Case);
            var caseMap = casesStockMap.get(iPhoneType);

            if (!caseMap.containsKey(color))
                return false;

            var colorCount = caseMap.get(color);
            if (colorCount == 0)
                return false;

            caseMap.put(color, colorCount - 1);
            map.put(AccessoryType.Case, totalCasesCount - 1);
            return true;
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: buyCase\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
        }
        return false;
    }
    public int getPrice(IPhoneType iPhoneType, AccessoryType accessoryType)
    {
        return accessoryPriceMap.get(iPhoneType).get(accessoryType);
    }

    public void printStock(IPhoneType iphoneType)
    {
        System.out.printf("Available accessories for %s\n", iphoneType);
        final var accessoryStock = accessoryStockMap.get(iphoneType);
        final var accessoryPrice = accessoryPriceMap.get(iphoneType);
        final var caseStock = casesStockMap.get(iphoneType);
        for (var accessory : accessoryStock.entrySet())
        {
            AccessoryType accessoryType = accessory.getKey();

            System.out.printf("- %s ", accessoryType);
            if (accessory.getValue() != 0)
                System.out.printf("(%d$): ", accessoryPrice.get(accessoryType));
            else
                System.out.print(": ");

            switch(accessoryType)
            {
                case Case -> {
                    System.out.println();
                    for (var caseType : caseStock.entrySet())
                    {
                        int count = caseType.getValue();
                        System.out.printf("\t- %-10s (%s)\n", caseType.getKey(), count == 0 ? "Out of Stock" : Integer.toString(count));
                    }
                }
                case Airpods, MagSafeCharger -> {
                    if (accessory.getValue() != 0) {
                        System.out.printf(" (%d)\n", accessory.getValue());
                    }
                    else
                    {
                        System.out.print("Out of Stock\n");
                    }
                }
            }
        }
    }

    public void setPrice(IPhoneType iphoneType, AccessoryType type, int price)
    {
        accessoryPriceMap.get(iphoneType).put(type, price);
    }

    public void addToStock(IPhoneType iPhoneType, AccessoryType type) {
        try {
            var map = accessoryStockMap.get(iPhoneType);

            var count = map.get(type);
            map.put(type, count + 1);
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: addToStock\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
        }

    }

    public void addToStock(IPhoneType iPhoneType, Color caseColor) {
        try {
            var map = accessoryStockMap.get(iPhoneType);
            var count = map.get(AccessoryType.Case);
            map.put(AccessoryType.Case, count + 1);

            int caseCount = 0;
            if (casesStockMap.get(iPhoneType).containsKey(caseColor))
                caseCount = casesStockMap.get(iPhoneType).get(caseColor);

            casesStockMap.get(iPhoneType).put(caseColor, caseCount + 1);
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: addToStock\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    public void addToStock(IPhoneType iPhoneType, AccessoryType type, int count)  {
        for (int i = 0; i < count; i++)
            addToStock(iPhoneType, type);
    }

    public void addToStock(IPhoneType iPhoneType, Color caseColor, int count)  {
        for (int i = 0; i < count; i++)
            addToStock(iPhoneType, caseColor);
    }

    public ArrayList<Color> getCaseColors(IPhoneType type) {
        return new ArrayList<>(casesStockMap.get(type).keySet());
    }
}
