public class Reader {
    private RingBuffer ringBuffer;
    private String name;
    private int readSequence;

    public Reader(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
        this.readSequence = 0;
    }

    public int read(int expectedSequence) {
        int value = ringBuffer.read(expectedSequence);
        readSequence++;
        System.out.println(name + " read: " + value);
        return value;
    }
}
