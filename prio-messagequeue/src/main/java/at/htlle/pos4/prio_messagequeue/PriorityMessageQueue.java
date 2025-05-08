package at.htlle.pos4.prio_messagequeue;

import java.util.LinkedList;

class PriorityMessageQueue {
    private final LinkedList<Message> queue = new LinkedList<>();
    private final int capacity;

    public PriorityMessageQueue(int capacity) {
        this.capacity = capacity;
    }

    /** Producer ruft diese Methode auf */
    public synchronized void sendMessage(Message msg) throws InterruptedException {
        // Warten, bis Platz frei wird
        while (queue.size() == capacity) {
            wait();
        }

        if (msg.isPriority()) {
            int idx = 0;
            for (Message m : queue) {      // zähle nur PRIO-Nachrichten am Anfang
                if (!m.isPriority()) break;
                idx++;
            }
            queue.add(idx, msg);            // hinter die letzte PRIO einfügen
        } else {
            queue.addLast(msg);            // normale Nachricht ganz ans Ende
        }
        notifyAll();                   // evtl. wartende Consumer/Producer wecken
    }

    /** Consumer ruft diese Methode auf */
    public synchronized Message receiveMessage() throws InterruptedException {
        // Warten, bis mindestens eine Nachricht verfügbar ist
        while (queue.isEmpty()) {
            wait();
        }
        Message msg = queue.removeFirst();
        notifyAll();                   // evtl. wartende Producer wecken
        return msg;
    }
}
