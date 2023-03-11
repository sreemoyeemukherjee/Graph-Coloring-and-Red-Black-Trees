/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

// POJO class to store node of a Red Black Tree
public class RedBlackNode {
    public static final int BLACK = 0;
    public static final int RED = 1;
    private String name;
    private int value;
    private int color;
    private RedBlackNode p;
    private RedBlackNode lc;
    private RedBlackNode rc;

    public RedBlackNode(String name, int value, int color)
    {
        this.name = name;
        this.value = value;
        this.color = color;
        this.p = null;
        this.lc = null;
        this.rc = null;

    }
    public RedBlackNode(String name, int value, int color, RedBlackNode p, RedBlackNode lc, RedBlackNode rc){
        this.name = name;
        this.value = value;
        this.color = color;
        this.p = p;
        this.lc = lc;
        this.rc = rc;
    }

    // generic getters
    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }

    public int getColor() {
        return color;
    }

    public RedBlackNode getP() {
        return p;
    }

    public RedBlackNode getLc() {
        return lc;
    }

    public RedBlackNode getRc() {
        return rc;
    }

    // generic setters
    public void setName(String name) {
        this.name = name;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setP(RedBlackNode p) {
        this.p = p;
    }

    public void setLc(RedBlackNode lc) {
        this.lc = lc;
    }

    public void setRc(RedBlackNode rc) {
        this.rc = rc;
    }
}
