package bst;

public class BST<Key extends Comparable<Key>, Value> {

    Node root;

    class Node {

        Key key;
        Value value;
        Node left, right;
        int n;

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    public Node put(Node r, Key key, Value value) {
        if (r == null) {
            return new Node(key, value, 1);
        }

        int cmp = key.compareTo(r.key);
        if (cmp < 0) {
            r.left = put(r.left, key, value);
        } else if (cmp > 0) {
            r.right = put(r.right, key, value);
        } else {
            r.value = value;
        }
        r.n = 1 + size(r.left) + size(r.right);

        return r;
    }

    public Value get(Key key) {
        return get(root, key);
    }

    public Value get(Node r, Key key) {
        if (key == null) {
            throw new NoSuchFieldError("no element");
        } else if (r == null) {
            return null;
        }

        int cmp = key.compareTo(r.key);
        if (cmp < 0) {
            return get(r.left, key);
        } else if (cmp > 0) {
            return get(r.right, key);
        } else {
            return r.value;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node r) {
        if (r == null) {
            return 0;
        } else {
            return r.n;
        }
    }

    public static void main(String[] args) {
        BST<String, Integer> t = new BST<>();
        t.put("g", 7);
        t.put("a", 10);
        t.put("d", 20);
        t.put("f", 5);
        t.put("e", 7);
        t.put("w", 5);

        t.inorder();
        System.out.println("");
        t.preorder();
        System.out.println("");
        t.postorder();
        System.out.println("");
        System.out.println(t.get("d"));
        System.out.println(t.levelOrder());
    }

    public void inorder() {
        inorder(root);
    }

    public void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public void preorder() {
        preorder(root);
    }

    public void preorder(Node root) {
        if (root != null) {
            System.out.print(root.key + " ");
            inorder(root.left);
            inorder(root.right);
        }
    }

    public void postorder() {
        postorder(root);
    }

    public void postorder(Node root) {
        if (root != null) {
            inorder(root.left);
            inorder(root.right);
            System.out.print(root.key + " ");
        }
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> Q = new Queue<>();

        Q.enqueue(root);

        while (!Q.isEmpty()) {
            Node temp = Q.dequeue();
            if (temp == null) {
                continue;
            }
            keys.enqueue(temp.key);
            Q.enqueue(temp.left);
            Q.enqueue(temp.right);
        }

        return keys;
    }

}
