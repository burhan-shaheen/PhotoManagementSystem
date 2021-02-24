package photomanagementapplication;

class BSTNode<T> {
    public String key;
    public LinkedList<String> data;
    public BSTNode<T> left, right;

    public BSTNode(String key, LinkedList<String> data) {
        this.key = key;
        this.data = data;
        left = right = null;
    }
    
}

public class BST<T> {
    private String deleteData;
    private BSTNode<T> root, current;

    public BST() {
        current = root = null;
    }

    public void clear() {
        current = root = null;
    }

    public boolean empty() {
        return root == null;
    }

    public boolean full() {
        return false;
    }

    public LinkedList<String> retrieve() {
        return current.data;
    }

    public boolean findKey(String k) {

        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareTo(p.key) == 0) {
                return true;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return false;
    }
    public void findKeyAndInsertData(String k, String val) {
        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareTo(p.key) == 0) {
                p.data.insert(val);
                break;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
    }
    public LinkedList<String> findAndReturnData(String k) {

        BSTNode<T> p = root;
        while (p != null) {
            current = p;
            if (k.compareTo(p.key) == 0) {
                return p.data;
            } else if (k.compareTo(p.key) < 0) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        return null;
    }

    public boolean insert(String k, LinkedList<String> val) {
        if (root == null) {
            current = root = new BSTNode<T>(k, val);
            return true;
        }

        BSTNode<T> p = current;
        if (findKey(k)) {
            current = p;
            return false;
        }

        BSTNode<T> tmp = new BSTNode<T>(k, val);
        if (k.compareTo(current.key) < 0) {
            current.left = tmp;
        } else {
            current.right = tmp;
        }
        current = tmp;
        return true;
    }

    public boolean removeKey(String k) {

        // Search for k
        String k1 = k;
        BSTNode<T> p = root;
        BSTNode<T> q = null; // Parent of p
        while (p != null) {

            if (k1.compareTo(p.key) < 0) {
                q =p;
                p = p.left;
            } else if (k1.compareTo(p.key) > 0) {
                q = p;
                p = p.right;
            } else { // Found the key

                // Check the three cases
                if ((p.left != null) && (p.right != null)) { // Case 3: two
                    // children

                    // Search for the min in the right subtree
                    BSTNode<T> min = p.right;
                    q = p;
                    while (min.left != null) {
                        q = min;
                        min = min.left;
                    }
                    p.key = min.key;
                    p.data = min.data;
                    k1 = min.key;
                    p = min;
                    // Now fall back to either case 1 or 2
                }

                // The subtree rooted at p will change here
                if (p.left != null) { // One child
                    p = p.left;
                } else { // One or no children
                    p = p.right;
                }

                if (q == null) { // No parent for p, root must change
                    root = p;
                } else {
                    if (k1.compareTo(q.key) < 0) {
                        q.left = p;
                    } else {
                        q.right = p;
                    }
                }
                current = root;
                return true;

            }
        }

        return false; // Not found
    }
    public void deleteFromBST(String str){
        deleteData = str;
        inOrder(root);
    }
    private void inOrder(BSTNode node) { 
        if (node == null) 
        { return; } 
        inOrder(node.left); 
        node.data.deleteNode(deleteData);
        inOrder(node.right); 
    }

}
