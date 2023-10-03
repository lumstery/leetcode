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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
     
        return dummyHead.next;
    }
}
/*
Approach: Iteration through two linked lists using pointers and filling of resulting list
Time complexity: O(n)
Space complexity: O (n)

We have to iterate through two linked lists simultaneously and calculate a sum of the values of nodes

if the sum of values exceeds 9, we should send "carry" to the next iteration for example

 \
  1  9  3
  3  2  1 
 /
  4

will  result into 1st resulting node storing
4 without carrying anything ;

  \
1  9  3
3  2  1 
  /
4 1

will result in 11 , thus 11 % 10 = 1 which we store as a result of the 2nd node, and 11/10 = 1 we store as a carry value that will be added to the 3rd node;
so 3rd node value = 3 +1 + 1 (carry) 

     \
1  9  3
3  2  1 
     /
4  1  5

there are two caveats here:
1) l1,l2 might have different lengths, and one of them can finish before another, thus we have to use zeros for the one that finished earlier
2) once we complete iterating through lists, if there is anything in "carry" then we need to apped additional node with "carry" as value

*/
