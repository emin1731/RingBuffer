public class RingBuffer {

  private Integer[] buffer;
  private final long[] sequences;
  private int size;
  private int writeSequence = 0;

  
 
  public RingBuffer(int size) {
    this.buffer = new Integer[size];
    this.sequences = new long[size];
    this.size = size;
 
    for (int i = 0; i < size; i++) {
      buffer[i] = 0;
    }
  }
 
  public void write(int value) {
    long sequence = writeSequence;
        int index = (int) (sequence % size);

        buffer[index] = value;
        sequences[index] = sequence;

        writeSequence++;

  }

  public Integer read(int sequence) {
    int index = (int) (sequence % size);

    if (sequences[index] != sequence) {
      return null; // overwritten or not written yet
    }

    return (Integer) buffer[index];

  }

  public int getWriteSequence() {
    return writeSequence;
  }

  public int getSize() {
    return size;
  }
 
  public void print() {
    System.out.println("This is our buffer now: " + java.util.Arrays.toString(buffer));
    System.out.println("w: " + writeSequence);

    System.out.println("Write count: " + writeSequence);
  }
 
}