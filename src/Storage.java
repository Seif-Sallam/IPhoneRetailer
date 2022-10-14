public enum Storage {
    GB_64,
    GB_128,
    GB_256;

    static String toString(Storage s)
    {
        if (s == GB_64)
            return "64 GB";
        else if (s == GB_128)
            return "128 GB";
        return "256 GB";
    }
}
