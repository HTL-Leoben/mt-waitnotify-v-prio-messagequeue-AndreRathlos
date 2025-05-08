package at.htlle.pos4.prio_messagequeue;

class Message {
    private final boolean isPriority;
    private final String content;

    public Message(boolean isPriority, String content) {
        this.isPriority = isPriority;
        this.content    = content;
    }
    public boolean isPriority() { return isPriority; }
    public String  getContent() { return content;   }

    @Override
    public String toString() {
        return (isPriority ? "[PRIO] " : "[----] ") + content;
    }
}
