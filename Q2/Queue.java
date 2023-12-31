//-----------------------------------------------------
// Title: Queue
//-----------------------------------------------------
public class Queue <T>{

    private Node first, last;

    public boolean isEmpty()
    { return first == null; }

    public void enqueue(T item)
    {
        Node oldlast = last;
        last = new Node();
        last.setData(item);
        last.setNext(null);
        if (isEmpty()) first = last;
        else oldlast.setNext(last);
    }
    public T dequeue()
    {
        T item = (T) first.getData(); //
        first = first.getNext();
        if (isEmpty()) last = null;
        return item;
    }

}
