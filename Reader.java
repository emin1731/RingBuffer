public class Reader {

    private RingBuffer ringBuffer; // Reference to the shared RingBuffer instance
    private String name; // Name of the reader for identification in logs
    private long readSequence; // Sequence number for the next read operation, initialized to the current write sequence of the ring buffer


    // Constructor initializes the reader with a name, reference to the ring buffer, and sets the initial read sequence to the current write sequence of the ring buffer.
    public Reader(String name, RingBuffer ringBuffer) {
        this.name = name;
        this.ringBuffer = ringBuffer;
        this.readSequence = ringBuffer.getWriteSequence(); // Start reading from the current write sequence
    }

    public synchronized Long read() {
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
        
        Long value = this.ringBuffer.read(this.readSequence);
        
        // case: reader is trying to read data that has been overwritten
        if(value == null) {
            return null;
        }
        
        readSequence++;
        
        return value;
    }

    // Getter for the reader's name, used for logging purposes.
    public String getName() {
        return name;
    }
}
