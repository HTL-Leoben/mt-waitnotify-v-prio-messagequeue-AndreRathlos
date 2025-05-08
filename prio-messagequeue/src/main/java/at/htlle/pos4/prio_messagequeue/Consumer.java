package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

class Consumer extends Thread {
    private final PriorityMessageQueue queue;
    private final Random rnd = new Random();

    public Consumer(String name, PriorityMessageQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message msg = queue.receiveMessage();
                System.out.printf("%-10s ◀ recv : %s%n", getName(), msg);

                Thread.sleep(500 + rnd.nextInt(1500));      // 0,5–2 s Pause
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
