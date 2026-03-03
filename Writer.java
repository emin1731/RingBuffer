public class Writer {
    private final RingBuffer ringBuffer;
    private final String name;

    public Writer(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
    }

    public void write(int value) {
        ringBuffer.write(value);
        System.out.println(name + " wrote: " + value);
    }
}
