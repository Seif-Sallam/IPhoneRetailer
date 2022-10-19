import java.util.ArrayList;
import java.util.HashMap;

// Same procedure as IPhone12
public class IPhoneSE extends IPhone {

    static HashMap<Color, Integer> casesStock;
    static final int casePrice = 30;

    IPhoneSE(Storage modelCapacity, Color color, int price)
    {
        super(IPhoneType.IPHONE_SE, modelCapacity, color, price);
        if (casesStock == null )
        {
            casesStock = new HashMap<>();
            casesStock.put(Color.Red, 2);
            casesStock.put(Color.Black, 1);
        }
    }

    @Override
    public int buyCase(Color clr) {
        if (casesStock.containsKey(clr))
        {
            if (casesStock.get(clr) > 0)
            {
                int count = casesStock.get(clr);
                casesStock.put(clr, count - 1);
                return casePrice;
            }
            return 0;
        }
        return 0;
    }

    @Override
    public int buyAirpods() {
        return 0;
    }

    @Override
    public int buyCharger() {
        return 0;
    }

    @Override
    public void printAvailableAcc() {
        System.out.println("Available Accessories for *IPhone SE*");
        System.out.printf("Cases: %d$ each\n", casePrice);
        for (var cases : casesStock.entrySet())
            System.out.printf("- %-20s (%s)\n", cases.getKey(), cases.getValue() == 0? "Out of Stock" : cases.getValue());
    }

    @Override
    public void addCase(Color clr) {
        if (casesStock.containsKey(clr))
        {
            int count = casesStock.get(clr);
            casesStock.put(clr, count + 1);
        }
        else {
            casesStock.put(clr, 1);
        }
    }

    @Override
    public void addAirpods() {}

    @Override
    public void addCharger() {}

    @Override
    public ArrayList<Color> getCaseColors() {
        return new ArrayList<>(casesStock.keySet());
    }
}
