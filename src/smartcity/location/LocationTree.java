package smartcity.location;



public class LocationTree {


    class Node {

        String name;

        Node left, right;

        Node(String name) { this.name = name; }

    }



    private Node root;



    public void insert(String name) {

        root = insertRec(root, name);

    }



    private Node insertRec(Node root, String name) {

        if (root == null) return new Node(name);

        if (name.compareToIgnoreCase(root.name) < 0)

            root.left = insertRec(root.left, name);

        else if (name.compareToIgnoreCase(root.name) > 0)

            root.right = insertRec(root.right, name);

        return root;

    }



    public void remove(String name) {

        root = removeRec(root, name);

    }



    private Node removeRec(Node root, String name) {

        if (root == null) return null;

        if (name.compareToIgnoreCase(root.name) < 0)

            root.left = removeRec(root.left, name);

        else if (name.compareToIgnoreCase(root.name) > 0)

            root.right = removeRec(root.right, name);

        else {

            if (root.left == null && root.right == null)

                return null;

            else if (root.left == null)

                return root.right;

            else if (root.right == null)

                return root.left;

            root.name = minValue(root.right);

            root.right = removeRec(root.right, root.name);

        }

        return root;

    }



    private String minValue(Node root) {

        String minv = root.name;

        while (root.left != null) {

            minv = root.left.name;

            root = root.left;

        }

        return minv;

    }



    public void display() {

        if (root == null) {

            System.out.println("⚠️ No locations to display!");

            return;

        }

        inorder(root);

    }



    private void inorder(Node node) {

        if (node != null) {

            inorder(node.left);

            System.out.println(node.name);

            inorder(node.right);

        }

    }

}