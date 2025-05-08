package at.htlle.pos4.prio_messagequeue;

import java.util.Random;

/** Producer-Thread */
class Producer extends Thread {
    private final PriorityMessageQueue queue;
    private final Random rnd = new Random();
    private       int counter = 1;

    public Producer(String name, PriorityMessageQueue queue) {
        super(name);
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                boolean prio  = rnd.nextBoolean();          // zufällig Priorität
                Message msg   = new Message(prio, getName() + "-Msg " + counter++);
                queue.sendMessage(msg);
                System.out.printf("%-10s ▶ send : %s%n", getName(), msg);

                Thread.sleep(500 + rnd.nextInt(1500));      // 0,5–2 s Pause
            }
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
    }
}
