public class Main {
    public static void main(String[] args) {
        // shared setup
        RingBuffer ringBuffer = new RingBuffer(3);
        Writer writer = new Writer("Writer-1", ringBuffer);
        Reader fastReader = new Reader("FastReader", ringBuffer);
        Reader slowReader = new Reader("SlowReader", ringBuffer);

        System.out.println("\n=== Case 1: No data to read yet ===");
        // No writes have happened yet, so readers should get null.
        System.out.println(fastReader.getName() + " read: " + fastReader.read());
        System.out.println(slowReader.getName() + " read: " + slowReader.read());

        System.out.println("\n=== Case 2: Normal write and read ===");
        // Writer fills and then exceeds capacity to force overwrite behavior.
        writer.write(10);
        writer.write(20);
        writer.write(30);

        // Fast reader consumes data early and misses nothing.
        System.out.println(fastReader.getName() + " read: " + fastReader.read()); // 10
        System.out.println(fastReader.getName() + " read: " + fastReader.read()); // 20

        writer.write(40); // Overwrites oldest when needed
        writer.write(50); // Buffer now keeps the latest 3 logical entries

        System.out.println(fastReader.getName() + " read: " + fastReader.read()); // 30
        System.out.println(fastReader.getName() + " read: " + fastReader.read()); // 40
        System.out.println(fastReader.getName() + " read: " + fastReader.read()); // 50

        System.out.println("\n=== Case 3: Slow reader misses overwritten data ===");
        // Slow reader starts late. First read returns null because old data is gone.
        System.out.println(slowReader.getName() + " read: " + slowReader.read()); // null
        // Following reads return currently available items.
        System.out.println(slowReader.getName() + " read: " + slowReader.read()); // 30
        System.out.println(slowReader.getName() + " read: " + slowReader.read()); // 40
        System.out.println(slowReader.getName() + " read: " + slowReader.read()); // 50

        System.out.println("\n=== Case 4: Unauthorized writer name (no permission) ===");
        // Direct write with a different writer name should be rejected.
        try {
            ringBuffer.write(999, "Writer-2");
            System.out.println("Unexpected: unauthorized write succeeded");
        } catch (IllegalStateException ex) {
            System.out.println("Expected failure: " + ex.getMessage());
        }

        System.out.println("\n=== Case 5: Creating second writer for same buffer ===");
        // Registering a second writer on the same buffer should fail.
        try {
            Writer secondWriter = new Writer("Writer-2", ringBuffer);
            secondWriter.write(777);
            System.out.println("Unexpected: second writer succeeded");
        } catch (IllegalStateException ex) {
            System.out.println("Expected failure: " + ex.getMessage());
        }

        System.out.println("\n=== End of demo ===");
    }
}
