public class Writer {
    private RingBuffer ringBuffer;
    private String name;

    public Writer(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
    }

    public void write(int value) {
        ringBuffer.write(value);
        System.out.println(name + " wrote: " + value);
    }
}
