package izziv1;

interface Collection {
    static final String ERR_MSG_EMPTY = "izziv1.Collection is empty.";
    static final String ERR_MSG_FULL = "izziv1.Collection is full.";

    boolean isEmpty();
    boolean isFull();
    int size();
    String toString();
}
