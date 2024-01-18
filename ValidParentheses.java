class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        for(int i=0; i < s.length() ; i++){
            Character c = s.charAt(i);
            switch(c){
                case '(':
                case '[':
                case '{':  
                stack.push(c);   
                break;
                
                case ')':
                    if (stack.isEmpty() || !stack.pop().equals('(')) return false;
                break;
                    
                case ']':
                     if (stack.isEmpty() || !stack.pop().equals('[')) return false;
                break;                    
                
                case '}':
                     if (stack.isEmpty() || !stack.pop().equals('{')) return false;
                break;                    
            }
            
        }
      
       return stack.isEmpty();
    }
}
/*
 Approcah : we iterate through string and when we have any opening brackets we just push them to stack, once we encounter any closing
brackets we pop character from top of the stack and check if it corresponds to enclosing brackets
 Time Complexity: O(n)
 Space Complexity: O(1)
*/
