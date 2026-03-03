public class RingBuffer {

  private final Integer[] buffer;
  private final long[] sequences;
  private final int size;
  private long writeSequence = 0;
  private String writerId;

  
 
  public RingBuffer(int size) {
    if (size <= 0) {
      throw new IllegalArgumentException("Buffer size must be greater than 0");
    }

    this.buffer = new Integer[size];
    this.sequences = new long[size];
    this.size = size;
 
    for (int i = 0; i < size; i++) {
      sequences[i] = -1;
    }
  }

  public void registerWriter(String writerName) {
    if (writerName == null || writerName.isBlank()) {
      throw new IllegalArgumentException("Writer name must not be null or blank");
    }

    if (writerId == null) {
      writerId = writerName;
      return;
    }

    throw new IllegalStateException("Only one writer is allowed per RingBuffer (owner: " + writerId + ")");
  }
 
  public void write(int value, String writerName) {
    if (writerId == null) {
      throw new IllegalStateException("Writer is not registered for this RingBuffer");
    }

    if (!writerId.equals(writerName)) {
      throw new IllegalStateException("Write rejected: this RingBuffer already has a different writer");
    }

    long sequence = writeSequence;
    int index = (int) (sequence % size);

    buffer[index] = value;
    sequences[index] = sequence;

    writeSequence++;

  }

  public Integer read(long sequence) {
    int index = (int) (sequence % size);

    if (sequences[index] != sequence) {
      return null; // overwritten or not written yet
    }

    return buffer[index];

  }

  public long getWriteSequence() {
    return writeSequence;
  }

  public int getSize() {
    return size;
  }

}