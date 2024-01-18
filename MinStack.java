class MinStack {
    private Node head;

    public MinStack() {
        
    }
    
    public void push(int val) {
        if(head == null) 
        head = new Node(val ,val , null);
            else
        head  = new Node(val, Math.min(val, head.min), head);
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
    
    private static class Node {
        int val;
        Node next;
        int min;
        
        public Node(int val, int min, Node next){
         this.val = val;
         this.min = min;
         this.next  = next;   
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
   
   Approach: To implement stack we are introducing Node class and creating uni-directional linked list, we always store reference to our head and in the head node we always store min value, which is updated when any new value is pushed to stack. To remove value head.next becomes head and it will alway contain min value for all the nodes below. 
   Time complexity: O(1)
   Space complexity: O(N)

 */
