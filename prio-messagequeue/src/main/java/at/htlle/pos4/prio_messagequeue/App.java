package at.htlle.pos4.prio_messagequeue;

public class App {

    public static void main(String[] args) {
        int capacity = 5;                     // maximale Queue-Größe
        PriorityMessageQueue queue = new PriorityMessageQueue(capacity);

        // Zwei Producer & zwei Consumer starten
        new Producer("Producer-1", queue).start();
        new Producer("Producer-2", queue).start();
        new Consumer("Consumer-1", queue).start();
        new Consumer("Consumer-2", queue).start();
    }
}
