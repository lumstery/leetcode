class LRUCache {

    private class Node{
        int key;
        int value;
        Node prev;
        Node next;

        Node(int k, int v){
            this.key = k;
            this.value = v;
        }
        Node(){
            this(0,0);
        }
    }
    // max allowed capacity
    private int capacity;
    // current count of elements
    private int count;
    // will hold nodes by keys for eash access
    private Map<Integer, Node> map;
    // boundaries for doubly linked list
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.count = 0;
        map = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        
        if(node == null) {
            return -1;
        }
        
        // push node to top of list as it was queried
        moveToHead(node);

        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        
        if(node == null){
            node = new Node(key, value);
            map.put(key, node);
            insertToHead(node);
            ++count;
        }else{
            node.value = value;
            moveToHead(node);
        }

        // if we reach max capacity we need to drop least used
        if(count > capacity){
            // least used is always in the tail of list
            Node leastUsed = tail.prev;
            removeFromCurrentPosition(leastUsed);
            map.remove(leastUsed.key);
            --count;
        }
    }

    private void moveToHead(Node node){
        removeFromCurrentPosition(node);
        insertToHead(node);
    }

    private void insertToHead(Node node){
        Node after = head.next;
        head.next = node;
        node.prev = head;
        node.next = after;
        after.prev = node;
    }

    private void removeFromCurrentPosition(Node node){
        Node before = node.prev;
        Node after = node.next;

        before.next = after;
        after.prev = before;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 /*
   Approach: We use map for easy access to nodes by keys, and
   be able to update value for certain keys
   
   We use Doubly linked list for storing of frequency of usage 
   Whenever element added/update/retrieved -> we push it to head
   Thus when we reach max capacity, we know for certain that
   least used element is in the tail of the DLL

   Time Complexity: O(1)
   Space Complexity: O(n)
 */
