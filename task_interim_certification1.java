class Node{
    class Entity{
        int key;
        int value;
    }
    class Basket{
        Node head;
        class Node{
            Entity entity;
            Node next;
        }

        public Integer find(int key){ // O(1)
            Node node = head;
            while(node != null){
                if(node.entity.key == key){
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean push(Entity entity){ // O(1)
            Node node = new Node();
            node.entity = entity;

            if(head == null){
                head = node;
            }else{
                if(head.entity.key == entity.key){
                    return false;
                }else {
                    Node cur = head;
                    while (cur.next != null) {
                        if (cur.next.entity.key == entity.key) {
                            return false;
                        }
                        cur = cur.next;
                    }
                    cur.next = node;
                }
            }
            return true;
        }

        public boolean remove(int key){ // O(1)
            if(head != null) {
                if (head.entity.key == key) {
                    head = head.next;
                    return true;
                } else {
                    Node cur = head;
                    while (cur.next != null) {
                        if (cur.next.entity.key == key) {
                            cur.next = cur.next.next;
                            return true;
                        }
                        cur = cur.next;
                    }
                }
            }
            return false;
        }
    }

    static final int INIT_SIZE = 16;

    Basket[] baskets;

    public HashMap(){
        this(INIT_SIZE);
    }

    public HashMap(int size){
        baskets = new Basket[size];
    }

    private int getIndex(int key){
        return key % baskets.length;
    }

    public Integer find(int key){ // O(1)
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.find(key);
        }
        return null;
    }

    public boolean push(int key, int value){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket == null){
            basket = new Basket();
            baskets[index] = basket;
        }
        Entity entity = new Entity();
        entity.key = key;
        entity.value = value;
        return basket.push(entity);
    }

    public boolean remove(int key){
        int index = getIndex(key);
        Basket basket = baskets[index];
        if(basket != null){
            return basket.remove(key);
        }
        return false;
    }
}

class Tree{
    Node root;
    class Node{
        int value;
        Node left;
        Node right;
    }

    public Node find(int value){
        return find(root, value);
    }

    private Node find(Node node, int value){
        if(node == null){
            return null;
        }
        if(node.value == value){
            return node;
        }
        if(node.value < value){
            return find(node.right, value);
        }else{
            return find(node.left, value);
        }
    }

    public void insert(int value){
        if(root == null){
            root = new Node();
            root.value = value;
        }else {
            insert(root, value);
            //root = balance(root);
        }

        //root.color = BLACK;
    }

    public void insert(Node node, int value){
        if(node.value != value){
            if(node.value < value){
                if(node.right == null){
                    node.right = new Node();
                    node.right.value = value;
                    //node.right.color = RED;
                }else{
                    insert(node.right, value);
                }
            }else{
                if(node.left == null){
                    node.left = new Node();
                    node.left.value = value;
                    //node.left.color = RED;
                }else{
                    insert(node.left, value);
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//
//        map.push(1, 2);
//        map.push(3, 4);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));
//
//        map.remove(3);
//        map.push(2, 5);
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(2));

        Tree tree = new Tree();

        for(int i=1; i<=5; i++){
            tree.insert(i);
        }

    }
}

class RedBlackTree <M extends Comparable<M>> {
    Node root;
    private enum colorNode {
        RED, BLACK
    }
    class Node{
        Node left;
        Node right;
        M data;
        colorNode color;

        Node(M data) {
            this.data = data;
            left = null;
            right = null;
            color = ColorNode.RED           
        }
        @Override
        public String toString() {
            return "Node{" +
                    " data=" + data +
                    ", color=" + color +
                    ",left=" + left +
                    ", right=" + right +
                    '}';
        }
    }
  // Функция для поворота узла против часовой стрелки.
  node rotateLeft(node myNode) {
    private Node rotateLeft (Node myNode){
        Node child = myNode.right;
        Node childLeft = child.left;
        child.left = myNode;
        myNode.right = childLeft;
        return child;
    }
  // Функция для поворота узла по часовой стрелке.
  node rotateRight(node myNode) {
    private Node rotateRight (Node myNode){
        Node child = myNode.left;
        Node childRight = child.right;
        child.right = myNode;
        myNode.left = childRight;
        return child;
    }
  // Функция для проверки того, является ли узел красного цвета или нет.
  boolean isRed(node myNode) {
    return myNode != null && myNode.color.equals(ColorNode.RED);
  }
 // Функция для изменения цвета двух узлы.
 private void swapColors (Node node1, Node node2){
    ColorNode temp = node1.color;
    node1.color = node2.color;
    node2.color = temp;
}
// вставка в левостороннее Красно-черное дерево.
public boolean insert (M data){
    Node node;
    if (root != null) {
        node = insert(root, data);
        if (node == null) {
            return false;
        }
    } else {
        node = new Node(data);
    }
    root = node;
    root.color = ColorNode.BLACK;
    return true;
}
private Node insert (Node myNode, M data){
    if (myNode == null) {
        return new Node(data);
    }
    if (myNode.data.compareTo(data) > 0) {
        myNode.left = insert(myNode.left, data);
    } else if (myNode.data.compareTo(data) < 0) {
        myNode.right = insert(myNode.right, data);
    } else
        return null;
    return balanced(myNode);
}
// Правый дочерний элемент красный, а левый дочерний элемент черный или не существует.
private Node balanced (Node myNode){
    if (isRed(myNode.right) && !isRed(myNode.left)) {
        myNode = rotateLeft(myNode);
        swapColors(myNode, myNode.left);
    }
// Левый ребенок, а также левый внук выделены красным цветом
    if (isRed(myNode.left) && isRed(myNode.left.left)) {
        myNode = rotateRight(myNode);
        swapColors(myNode, myNode.right);
    }
// когда и левый, и правый дочерние элементы окрашены в красный цвет.
    if (isRed(myNode.left) && isRed(myNode.right)) {
        myNode.color = ColorNode.RED;
        myNode.left.color = ColorNode.BLACK;
        myNode.right.color = ColorNode.BLACK;
    }
    return myNode;
}

    public void inorder () {
    inorder(root);
}
  // Обход по порядку
    private void inorder (Node node){
    if (node != null) {
        inorder(node.left);
        System.out.print(node.data + " " + node.color + "|");
        inorder(node.right);
    }
}
}
