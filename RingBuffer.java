import java.util.ArrayList;
import java.util.List;
 
public class RingBuffer {
  List<Integer> buffer;
  int w;
  int r;
  int size;
  int count;
 
  public RingBuffer(int size) {
    this.size = size;
    this.w = 0;
    this.r = 0;
    this.count = 0;
    this.buffer = new ArrayList<>(size);
 
    for (int i = 0; i < size; i++) {
      buffer.add(0);
    }
  }
 
  public void write(int x) {
    buffer.set(w, x);
    w++;
    if (w == size) {
      w = 0;
    }
 
    if (count == size) {
      r++;
      if (r == size) {
        r = 0;
      }
    } else {
      count++;
    }
  }
 
  public void read() {
    if (count == 0) {
      System.out.println("Buffer is empty, nothing to read");
      return; // case we read all the messages
    }
 
    int value = buffer.get(r);
    r++;
    if (r == size) {
      r = 0;
    }
 
    count--;
 
    System.out.println("You read this message: " + value);
  }
 
  public void print() {
    System.out.println("This is our buffer now: " + buffer);
    System.out.println("w: " + w);
    System.out.println("r: " + r);
    System.out.println("count: " + count);
  }
 
}