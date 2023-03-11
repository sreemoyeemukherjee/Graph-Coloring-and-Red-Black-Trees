/**Name: Sreemoyee Mukherjee
 * Andrew ID: sreemoym
 * Course: Data Structures & Algorithms
 * Assignment Number: 3
 */
package ds;

// class for storing the dictionary as a Red Black Tree
public class RedBlackTree {
    private RedBlackNode sentinelNode, root;
    private int value = 0;

    private String name;
    public RedBlackTree() {
        // RedBlackNode sentinel node is used for all null children and parent (parent of root)
        sentinelNode = new RedBlackNode("", -1, RedBlackNode.BLACK, null, null, null);
        root = sentinelNode;
    }

    //Parameters:
    //value - is an integer to be inserted
    // Pre-condition: memory is available for insertion
    // below code implements the algorithm mentioned at:
    // https://www.andrew.cmu.edu/user/mm6/95-771/examples/DSARedBlackTreeProject/dist/javadoc/index.html
    public int insert(String name){
        RedBlackNode y = sentinelNode;
        RedBlackNode x = root;

        while (x != sentinelNode) {
            y = x;
            // Traverse the tree to the left or right depending on the key to be inserted
            if (name.compareTo(x.getName()) < 0) {
                x = x.getLc();
            }
            else if (name.compareTo(x.getName()) > 0) {
                x = x.getRc();
            }
            else {
                return x.getValue();
            }
        }

        // Insert new node
        RedBlackNode newNode = new RedBlackNode(name, value, RedBlackNode.BLACK, null, null, null);
        value++;
        newNode.setP(y);    // setting y as parent
        if (y == sentinelNode) {
            root = newNode;
        } else if (name.compareTo(y.getName()) < 0) {
            y.setLc(newNode);
        } else {
            y.setRc(newNode);
        }
        newNode.setLc(sentinelNode);
        newNode.setRc(sentinelNode);
        newNode.setColor(RedBlackNode.RED); // setting all new nodes to RED initially
        RBInsertFixup(newNode); // fixing the colors
        return  newNode.getValue();
    }

    // fixing colors of the Red Black Tree after a new node is inserted
    // below code implements the algorithm mentioned at:
    // https://www.andrew.cmu.edu/user/mm6/95-771/examples/DSARedBlackTreeProject/dist/javadoc/index.html
    private void RBInsertFixup(RedBlackNode z){
        while (z.getP().getColor() == RedBlackNode.RED){
            if(z.getP() == z.getP().getP().getLc()){
                RedBlackNode y = z.getP().getP().getRc();
                if(y.getColor() == RedBlackNode.RED){
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                }
                else {
                    if(z == z.getP().getRc()){
                        z = z.getP();
                        leftRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    rightRotate(z.getP().getP());
                }
            }
            else {
                RedBlackNode y = z.getP().getP().getLc();
                if(y.getColor() == RedBlackNode.RED){
                    z.getP().setColor(RedBlackNode.BLACK);
                    y.setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    z = z.getP().getP();
                }
                else {
                    if(z == z.getP().getLc()){
                        z = z.getP();
                        rightRotate(z);
                    }
                    z.getP().setColor(RedBlackNode.BLACK);
                    z.getP().getP().setColor(RedBlackNode.RED);
                    leftRotate(z.getP().getP());
                }
            }
        }
        root.setColor(RedBlackNode.BLACK);
    }

    /** pseudocode for left rotations
     pre: right[x] != nil[T]
     pre: root's parent is nill[T]*/
    // below code implements the algorithm mentioned at:
    // https://www.andrew.cmu.edu/user/mm6/95-771/examples/DSARedBlackTreeProject/dist/javadoc/index.html
    public void leftRotate(RedBlackNode x){
        RedBlackNode y = x.getRc();
        x.setRc(y.getLc());
        y.getLc().setP(x);
        y.setP(x.getP());
        if(x.getP() == sentinelNode){
            root = y;
        }
        else {
            if(x == x.getP().getLc()){
                x.getP().setLc(y);
            }
            else {
                x.getP().setRc(y);
            }
        }
        y.setLc(x);
        x.setP(y);
    }

    /** pseudocode for right rotation
     pre: left[x] != nil[T]
     pre: root's parent is nill[T]*/
    // below code implements the algorithm mentioned at:
    // https://www.andrew.cmu.edu/user/mm6/95-771/examples/DSARedBlackTreeProject/dist/javadoc/index.html
    public void rightRotate(RedBlackNode x){
        RedBlackNode y = x.getLc();
        x.setLc(y.getRc());
        y.getRc().setP(x);
        y.setP(x.getP());
        if(x.getP() == sentinelNode){
            root = y;
        }
        else {
            if(x == x.getP().getLc()){
                x.getP().setLc(y);
            }
            else {
                x.getP().setRc(y);
            }
        }
        y.setRc(x);
        x.setP(y);
    }

    // to get the numeric value for a particular course name
    public int getValue(String name){
        RedBlackNode copy = treeCopy(root);
        RedBlackNode node = searchName(copy, name);
        return node.getValue();
    }

    // to get the course name for a particular numeric value
    public String getName(int value){
        RedBlackNode copy = treeCopy(root);
        searchValue(copy, value);
        return this.name;
    }

    // searches in Red Black Tree for a particular course name
    public RedBlackNode searchName(RedBlackNode root, String name){
        // Base Cases: root is null or key is present at root
        if (root==sentinelNode || root.getName().equals(name))
            return root;

        // Key is smaller than root's key
        if (name.compareTo(root.getName()) < 0)
            return searchName(root.getLc(), name);

        // Key is greater than root's key
        return searchName(root.getRc(), name);
    }

    // searches in Red Black Tree for a particular course value or ID
    public void searchValue(RedBlackNode node, int value)
    {
        // Base Case: node is null
        if(node.getValue() == sentinelNode.getValue())
            return;
        // found the value, thus storing the course name
        if (value == node.getValue())
            this.name = node.getName();
        // if both children are sentinel nodes i.e. null then return
        if(node.getLc().getValue() == sentinelNode.getValue() && node.getRc().getValue() == sentinelNode.getValue()) {
            return;
        }

        /* first recur on left child */
        searchValue(node.getLc(), value);

        /* now recur on right child */
        searchValue(node.getRc(), value);
    }

    // Post condition: we get a copy of our root that we can afford to lose
    public RedBlackNode treeCopy(RedBlackNode root) {
        if (root == null)
        {
            return null;
        }
        RedBlackNode newNode = new RedBlackNode(root.getName(), root.getValue(), root.getColor());
        newNode.setLc(treeCopy(root.getLc()));
        newNode.setRc(treeCopy(root.getRc()));
        return newNode;
    }
}
