extension SinglyLinkedList {
    
    func reverseRecursively() {
        func reverse(_ node: Node<T>?) -> Node<T>? {
            guard let head = node else { return node }
            if head.nextNode == nil { return head }
            
            let reversedHead = reverse(head.nextNode)
            head.nextNode?.nextNode = head
            head.nextNode = nil
            
            return reversedHead
        }
        
        firstNode = reverse(firstNode)
    }
    
}
