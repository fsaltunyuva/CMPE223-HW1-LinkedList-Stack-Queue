//-----------------------------------------------------
// Title: Single Linked List Implementation
//-----------------------------------------------------
public class SLinkedList  {
    private Node head;
    private int size; //current numbers of the elements

    public void addFirst(Node newNode) { //Time complexity O(1)
        newNode.setNext(head);
        head = newNode;
        size++;
    }

    public void addLast(Node newNode) { //Time complexity O(n) (For-Loop)
        Node curr = head;
        for(int i = 0; i<size-1; i++) {
            curr = curr.getNext();
        }
        curr.setNext(newNode);
        size++;
    }

    public Node get(int index) {
        if (index >= size){
            return null;
        }
            Node curr = head;
        for(int i = 0; i<index; i++) {
            curr = curr.getNext();
        }
        return curr;
    }

    public void removeFirst() {
        Node n = head;
        head.setNext(null);
        head = head.getNext();
        size--;
    }


    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
