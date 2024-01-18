class Solution {
        private static enum Operand {
            PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/");

            String sign;

            Operand(String sign) {
                this.sign = sign;
            }

            public static Operand fromValue(String sign) {
                return Arrays.stream(values())
                        .filter(val -> val.sign.equals(sign))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("Invalid operand: " + sign));
            }
        }
        public int evalRPN(String[] tokens) {
            Set<String> operands = Set.of("+", "-", "*", "/");
            Stack<Integer> stack = new Stack<>();

            for (String token : tokens) {
                if (!operands.contains(token)) {
                    Integer number = Integer.valueOf(token);
                    stack.push(number);
                } else {
                    Integer rightNumber = stack.pop();
                    Integer leftNumber = stack.pop();
                    Operand operand = Operand.fromValue(token);

                    switch (operand) {
                        case PLUS:
                            stack.push(leftNumber + rightNumber);
                            break;
                        case MINUS:
                            stack.push(leftNumber - rightNumber);
                            break;
                        case DIVIDE:
                            stack.push(leftNumber / rightNumber);
                            break;
                        case MULTIPLY:
                            stack.push(leftNumber * rightNumber);
                            break;
                    }
                }
            }
            return stack.pop();
        }
}
/*
 Approach: We iterate through tokens and if we have integer we push it to stack othewise if we have operand we are poping 2 numbers and apply operation to them, once we have a result we push it back to stack so that it can be used for the next operation
  Time Complexity: O(n)
  Space Complexity: O(n)
*/
