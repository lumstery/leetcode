/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length == 0)
         return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>(){
            @Override
            public int compare(ListNode o1,ListNode o2){
                if (o1.val<o2.val)
                    return -1;
                else if (o1.val==o2.val)
                    return 0;
                else 
                    return 1;
            }
        });

        ListNode result = new ListNode(0);
        ListNode tail = result;
        

        for(ListNode node: lists){
            if(node!=null){
                queue.add(node);
            }
        }

        while(!queue.isEmpty()){
            tail.next = queue.poll();
            tail = tail.next;

            if(tail.next != null){
                queue.add(tail.next);
            }
        }

        return result.next;
    }
}
/*
 Approach: Min Heap , Priority Queue is used to choosse min element among heads of lists.
 Firstly we put all heads to the queue and then until queue is not
 empty we poll min element and append it to result list, and if 
 current element has next node, we add it to queue to mainaing graduall iteration through all lists

 Time Complexity: 
 Inserting an element into a priority queue is O(log k), where k is the number of lists, because the maximum number of elements in the queue is k at any time (assuming the comparator's complexity is O(1)).

Each of the n nodes is inserted into and removed from the priority queue exactly once, leading to a total time complexity of O(n log k).

The space complexity is O(k) due to the priority queue, as it contains at most one node from each of the k lists at any time.
 Space Complexity; O(k)
 
*/
