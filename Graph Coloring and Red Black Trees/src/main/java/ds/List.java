/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

// class for creating a list
public class List {

    private LinkedListNode head;
    private LinkedListNode current;

    private LinkedListNode tail;
    public List()
    {
        head = new LinkedListNode(-1, null);    // head initially points to a dummy node
        tail = null;
        current = head; // current is initially same as head
    }

    // Adds a node at the end of current list
    public void add(int data){
        LinkedListNode cur = new LinkedListNode(data, null);
        if(tail == null && head.getLink() == null){
            head.setLink(cur);
            tail = cur;
            current.setLink(cur);
        }
        else {
            tail.addNodeAfter(data);
            tail = tail.getLink();
        }
    }

    public LinkedListNode next(){
        return current.getLink();
    }
}
