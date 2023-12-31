//-----------------------------------------------------
// Title: Stack
//-----------------------------------------------------
public class Stack <T> {

    private Node first = null;

    public boolean isEmpty() {
        return first == null;
    }

    private int size = 0;

    public void push(int data) {
        Node oldFirst = first;
        first = new Node();
        first.setData(data);
        first.setNext(oldFirst);
        size++;
    }

    public T pop() {
        T data = (T) first.getData();
        first = first.getNext();
        size--;
        return data;
    }

    public T get (int index) {
        if (index > size) {return null;}

        Node n = first;
        for(int i = 0; i<index; i++) {
            n = n.getNext();
        }
        return (T) n.getData();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
