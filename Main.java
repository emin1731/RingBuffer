public class Main {
    public static void main(String[] args) {
        RingBuffer ringBuffer = new RingBuffer(3);
        Writer writer = new Writer("Writer-1", ringBuffer);

        Reader fastReader = new Reader("FastReader", ringBuffer);
        Reader slowReader = new Reader("SlowReader", ringBuffer);

        writer.write(10);
        writer.write(20);
        writer.write(30);

        System.out.println(fastReader.getName() + " read: " + fastReader.read());
        System.out.println(fastReader.getName() + " read: " + fastReader.read());

        writer.write(40);
        writer.write(50);

        System.out.println(fastReader.getName() + " read: " + fastReader.read());
        System.out.println(fastReader.getName() + " read: " + fastReader.read());
        System.out.println(fastReader.getName() + " read: " + fastReader.read());

        System.out.println(slowReader.getName() + " read: " + slowReader.read());
        System.out.println(slowReader.getName() + " read: " + slowReader.read());
        System.out.println(slowReader.getName() + " read: " + slowReader.read());
        System.out.println(slowReader.getName() + " read: " + slowReader.read());
    }
}
