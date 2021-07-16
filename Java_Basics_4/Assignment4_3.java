package Java_Basics_4;

class ProdCons {
    int[] buffer;
    private int index;

    public ProdCons() {
        buffer = new int[20];
        index = 0;
    }

    // Produce
    public void produce() throws InterruptedException {
        while (true) {
            // could also synchronize on this but I like buffer more for understanding
            synchronized (buffer) {
                if (index < 20) {
                    buffer[index] = index;
                    System.out.println("Produced: " + buffer[index]);
                    index++;
                    // this is just so it's readable
                    Thread.sleep(200);
                }
            }
        }
    }

    // Consumer
    public void consume() throws InterruptedException {
        while (true) {
            synchronized (buffer) {
                if (index > 0) {
                    index--;
                    System.out.println("Consumed: " + buffer[index]);
                    buffer[index] = 0;
                    Thread.sleep(200);
                }
            }
        }
    }
}

public class Assignment4_3 {
    public static void main(String[] args) throws InterruptedException {

        ProdCons pc = new ProdCons();

        // producer
        Runnable prod = new Runnable() {
            @Override
            public void run() {

                try {
                    pc.produce();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        // consumer
        Runnable cons = new Runnable() {
            @Override
            public void run() {

                try {
                    pc.consume();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        new Thread(prod).start();
        new Thread(cons).start();

    }
}
