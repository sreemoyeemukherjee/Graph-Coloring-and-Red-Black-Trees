/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

// class for each LinkedList Node
public class LinkedListNode {
    private int data; // stores data of node
    private LinkedListNode link;    // stores pointer to the next node

    // constructor
    public LinkedListNode(int data, LinkedListNode link) {
        this.data = data;
        this.link = link;
    }

    // generic getters
    public int getData() {
        return data;
    }
    public LinkedListNode getLink() {
        return link;
    }

    // generic setters
    public void setData(int data) {
        this.data = data;
    }
    public void setLink(LinkedListNode link) {
        this.link = link;
    }

    // method to add a node after the current last node
    public void addNodeAfter(int data)
    {
        link = new LinkedListNode(data, link);
    }
}
