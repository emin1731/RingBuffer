public class Reader {

    private RingBuffer ringBuffer;
    private String name;
    private long readSequence;

    public Reader(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
        this.readSequence = ringBuffer.getWriteSequence(); // Start reading from the current write 
    }

    public synchronized Integer read() {
        Integer value = this.ringBuffer.read(this.readSequence);
        long writeSequence = ringBuffer.getWriteSequence();
        long oldestAvailableIndex = Math.max(0, writeSequence - ringBuffer.getSize());


        // case: reader is too slow and has fallen behind the oldest available index
        if(readSequence < oldestAvailableIndex) {
            readSequence = oldestAvailableIndex;
        }

        // case: reader is trying to read data that has not been written yet
        if(readSequence >= writeSequence) {
            return null;
        }
        
        // case: reader is trying to read data that has been overwritten
        if(value == null) {
            return null;
        }
        
        readSequence++;
        
        return value;
    }

    public String getName() {
        return name;
    }
}
