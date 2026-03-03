public class Writer {
    private final RingBuffer ringBuffer;
    private final String name;

    public Writer(String name, RingBuffer ringBuffer) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Writer name must not be null or blank");
        }

        this.name = name;
        this.ringBuffer = ringBuffer;

        ringBuffer.registerWriter(name);
    }

    public void write(int value) {
        ringBuffer.write(value, name);
        System.out.println(name + " wrote: " + value);
    }
}
