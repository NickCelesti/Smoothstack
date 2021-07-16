package Java_Basics_4;

public class Assignment4_2 {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";

        System.out.println("Start");
        Runnable t1 = new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (a) {
                        Thread.sleep(100);
                        synchronized (b) {
                            System.out.println("Thread 1 is running");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable t2 = new Runnable() {

            @Override
            public void run() {
                try {
                    synchronized (b) {
                        Thread.sleep(100);
                        synchronized (a) {
                            System.out.println("Thread 2 is running");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(t1).start();
        new Thread(t2).start();
        // thread 1 is waiting on thread 2 to free up b and vice versa with thread 2
        System.out.println("End");
    }
}
