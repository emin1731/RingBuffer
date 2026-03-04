public class RingBuffer {

  private final long[] buffer; // fixed-size array to hold the data
  private final long[] sequences; // parallel array to track the sequence number of each slot in the buffer
  private final long size; // capacity of the buffer
  private volatile long writeSequence = 0; // global sequence number for the next write operation

  
 
  // Constructor initializes the buffer and sequence tracking arrays, and validates the size.
  public RingBuffer(int size) {

    // Validate that the buffer size is positive
    if (size <= 0) {
      throw new IllegalArgumentException("Buffer size must be greater than 0");
    }

    // Initialize the buffer and sequences arrays based on the specified size
    this.size = size;
    this.buffer = new long[size];
    this.sequences = new long[size];
 

    // Initialize all sequence numbers to -1 to indicate that they are empty
    for (int i = 0; i < size; i++) {
      sequences[i] = -1;
    }
  }


  // The write method adds a new value to the buffer at the position determined by the current write sequence.
  public synchronized void write(long value) {

    int index = (int) (writeSequence % size);

    buffer[index] = value;
    sequences[index] = writeSequence;

    writeSequence++;

  }

  // The read method retrieves a value from the buffer based on the provided sequence number.
  public Long read(long sequence) {
    int index = (int) (sequence % size);

    // Check if the requested sequence is still available in the buffer. 
    if (sequences[index] != sequence) {
      return null; // overwritten or not written yet
    }

    return buffer[index];

  }

  // Returns the current write sequence number, which can be used by readers to determine where to start reading from.
  public long getWriteSequence() {
    return writeSequence;
  }

  // Returns the size of the buffer.
  public long getSize() {
    return size;
  }

}