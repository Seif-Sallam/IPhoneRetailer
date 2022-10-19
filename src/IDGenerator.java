// A simple class that you cannot instantiate that generates universally unique IDs or UUIDs in short.
public class IDGenerator {
    private static int nextID = 1;

    public static int generate() {
        return nextID++;
    }

    private IDGenerator() {}
}
