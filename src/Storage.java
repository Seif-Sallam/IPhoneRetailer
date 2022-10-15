public enum Storage {
    GB_64("64 GB"),
    GB_128 ("128 GB"),
    GB_256 ("256 GB");

    private String name;

    Storage(String name)
    {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
