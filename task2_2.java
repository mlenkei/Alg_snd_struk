// Узел связанного списка
class Node
{
    int data;
    Node next;
 
    Node(int data, Node next)
    {
        this.data = data;
        this.next = next;
    }
}
 
class Main
{
    // Вспомогательная функция для печати заданного связанного списка
    public static void printList(Node head)
    {
        Node ptr = head;
        while (ptr != null)
        {
            System.out.print(ptr.data + " —> ");
            ptr = ptr.next;
        }
 
        System.out.println("null");
    }
 
    // Переворачивает заданный связанный список, изменяя его поля `.next` и
    // его голова.
    public static Node reverse(Node head)
    {
        Node previous = null;
        Node current = head;
 
        // обход списка
        while (current != null)
        {
            // сложно: обратите внимание на следующий узел
            Node next = current.next;
 
            current.next = previous;    // фиксируем текущий узел
 
            previous = current;
            current = next;
        }
 
        // фиксируем голову, чтобы она указывала на новый фронт
        return previous;
    }
 
    public static void main(String[] args)
    {
        // ключи ввода
        int[] keys = { 1, 2, 3, 4, 5, 6 };
 
        Node head = null;
        for (int i = keys.length - 1; i >= 0; i--) {
            head = new Node(keys[i], head);
        }
 
        head = reverse(head);
        printList(head);
    }
}
