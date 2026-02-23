public class RingBuffer {
  private int[] buffer;
  private int size;
  private int writeIndex;
  private int writeCount;
 
  public RingBuffer(int size) {
    this.buffer = new int[size];
    this.size = size;
    this.writeIndex = 0;
    this.writeCount = 0;
 
    for (int i = 0; i < size; i++) {
      buffer[i] = 0;
    }
  }
 
  public void write(int value) {
    buffer[writeIndex] = value;
    writeIndex++;

    if (writeIndex == size) {
      writeIndex = 0;
    }
 
    if (writeCount == size) {
      writeCount = size;
    } else {
      writeCount++;
    }
  }

  public int read(int index) {
    if (index < 0 || index >= writeCount) {
      throw new IndexOutOfBoundsException("Index: " + index + ", Write Count: " + writeCount);
    }
    return buffer[index];
  }
 
  public void print() {
    System.out.println("This is our buffer now: " + java.util.Arrays.toString(buffer));
    System.out.println("w: " + writeIndex);

    System.out.println("Write count: " + writeCount);
  }
 
}