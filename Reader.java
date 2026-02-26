public class Reader {

    private RingBuffer ringBuffer;
    private String name;
    private int readSequence;

    public Reader(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
        this.readSequence = ringBuffer.getWriteSequence(); // Start reading from the current write 
    }

    public Integer read() {
        Integer value = this.ringBuffer.read(this.readSequence);


        if(value == null) {
            int minAvailableIndex = ringBuffer.getWriteSequence() - ringBuffer.getSize();

            if(readSequence < minAvailableIndex) {
                readSequence = minAvailableIndex;
            }
            return null;
        }
        
        readSequence++;
        
        System.out.println(name + " read: " + value);
        return value;
    }
}
