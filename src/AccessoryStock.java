import java.util.ArrayList;
import java.util.HashMap;

public class AccessoryStock {

    // The Accessory doesn't even need to be a class for how little of data it holds,
    // We can simply represent them with three attributes (Its type, Color in CASES, and Price),
    // If we were to use classes and inheritance, the classes would pretty much have one or two (max) as attributes.
    // The type would be by the class type, so we do not need to save it.
    // The Color will only be in the case of an accessory type of cases (not in the others).
    // the price should NOT exist with the accessory, because we have variations in the prices, and
    // they depend on another class (IPhone), so the middle man would be the stock :D.

    // If we were to embed the accessory inside the IPhone, we would have made them static functions to the IPhones
    // Defying the reason behind their existence as a physical object in the stock.
    // If we were to use them as an interface to the IPhones, it doesn't do any difference to the implementation, because
    // I would still need the existence of an object of the IPhone (which I do not have if I am only buying an accessory).

    // The chosen design is that we save the data of all the accessories inside the stock spread out in them.
    // We would have gone to the _classes_ option if the accessories had IDs, but they do not (but they should have ones).
    // If something has a stock, it should have a unique identifier.

    // The concept of a stock is naively simple. A singleton container that exists only once and keeps track of the data in and out
    // of the stock.

    // It has to be a singleton because at any point in time, I would only need to have one stock of IPhones/Accessories.

    private static AccessoryStock instance;

    public static AccessoryStock Instance()
    {
        if (instance == null)
            instance = new AccessoryStock();
        return instance;
    }
    HashMap<IPhoneType, IPhone> phonesPrototypes;

    private AccessoryStock()
    {
        phonesPrototypes = new HashMap<>();
        phonesPrototypes.put(IPhoneType.IPHONE_SE, new IPhoneSE(Storage.GB_64, Color.Red, 1));
        phonesPrototypes.put(IPhoneType.IPHONE_12, new IPhone12(Storage.GB_64, Color.Red, 1));
        phonesPrototypes.put(IPhoneType.IPHONE_13, new IPhone13(Storage.GB_64, Color.Red, 1));
    }

    public int buyAccessory(IPhoneType iPhoneType, AccessoryType type)
    {
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

    public void printStock(IPhoneType iphoneType)
    {
        phonesPrototypes.get(iphoneType).printAvailableAcc();
    }

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

    public ArrayList<Color> getCaseColors(IPhoneType type) {

        return phonesPrototypes.get(type).getCaseColors();
    }
}
