public class IDGenerator {
    private static int nextID = 1;

    public static int generate() {
        return nextID++;
    }

    private IDGenerator() {}
}
