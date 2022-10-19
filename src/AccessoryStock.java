import java.util.ArrayList;
import java.util.HashMap;

// The accessory stock is just a helper class to be able to easily interact with the accessories according to what IPhone we are ordering.
public class AccessoryStock {
    private static AccessoryStock instance;

    public static AccessoryStock Instance()
    {
        if (instance == null)
            instance = new AccessoryStock();
        return instance;
    }
    // These prototypes are like the portal where we know which phones have what accessories and of what quantity.
    HashMap<IPhoneType, IPhone> phonesPrototypes;

    // Constructor that initializes the prototypes to some dummy variables.
    private AccessoryStock()
    {
        phonesPrototypes = new HashMap<>();
        phonesPrototypes.put(IPhoneType.IPHONE_SE, new IPhoneSE(Storage.GB_64, Color.Red, 1));
        phonesPrototypes.put(IPhoneType.IPHONE_12, new IPhone12(Storage.GB_64, Color.Red, 1));
        phonesPrototypes.put(IPhoneType.IPHONE_13, new IPhone13(Storage.GB_64, Color.Red, 1));
    }

    // Buying an Accessory is done by knowing which iphone type it is, and what accessory it is.
    public int buyAccessory(IPhoneType iPhoneType, AccessoryType type)
    {
        // We would be only dealing with Airpods and chargers here, because Cases need another layer of choice.
        try {
            switch (type)
            {
                case Airpods -> {
                    return phonesPrototypes.get(iPhoneType).buyAirpods();
                }
                case MagSafeCharger -> {
                    return phonesPrototypes.get(iPhoneType).buyCharger();
                }
                case Case -> {
                    return 0;
                }
            }
            return 0;
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: buyAccessory\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
            return 0;
        }
    }
    // Buying a case is by providing the phone type and the color.
    public int buyCase(IPhoneType iPhoneType, Color color)
    {
        try {
            return phonesPrototypes.get(iPhoneType).buyCase(color);
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: buyCase\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
            return 0;
        }
    }

    // Prints the stock of a specific iphone type
    public void printStock(IPhoneType iphoneType)
    {
        phonesPrototypes.get(iphoneType).printAvailableAcc();
    }

    // Adds to the stock of a specific iphone (not for cases)
    public void addToStock(IPhoneType iPhoneType, AccessoryType type) {
        try {
            switch (type)
            {
                case Airpods -> {
                    phonesPrototypes.get(iPhoneType).addAirpods();
                }
                case MagSafeCharger -> {
                    phonesPrototypes.get(iPhoneType).addCharger();
                }
                case Case -> {
                }
            }
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: addToStock\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
        }

    }

    // Adding a case is passing in the case color.
    public void addToStock(IPhoneType iPhoneType, Color caseColor) {
        try {
            phonesPrototypes.get(iPhoneType).addCase(caseColor);
        }
        catch(NullPointerException e)
        {
            System.err.printf("Null Pointer Exception occurred in function: addToStock\nMessage: %s\n", e.getMessage());
            e.printStackTrace(System.err);
        }
    }

    // Helper function to know the available colors in a phone model.
    public ArrayList<Color> getCaseColors(IPhoneType type) {

        return phonesPrototypes.get(type).getCaseColors();
    }
}
