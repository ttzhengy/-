package 栈;

// 只能执行无括号的，数字为个位数的四则运算
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        // 一个数栈，一个符号栈
        CalculatorLinkedListStack numStack = new CalculatorLinkedListStack(10);
        CalculatorLinkedListStack operStack = new CalculatorLinkedListStack(10);
        // 用于扫描
        int index = 0;
        char ch = ' ';
        int num1 = 0;
        int num2 = 0;
        while (true){
            // 依次扫描
            ch = expression.substring(index,index+1).charAt(0);
            if (operStack.isOper(ch)){  // 判断是否操作符
                if (operStack.isEmpty()){   // 若栈为空，可直接压栈
                    operStack.push(ch);
                }else {     // 否则需要比较操作符优先级
                    if (operStack.priority(ch) <= operStack.priorityPeek()) {   // 栈中操作符优先级高
                        num1 = numStack.pull();
                        num2 = numStack.pull();
                        numStack.push(operStack.cal(num1,num2,operStack.pull()));
                        operStack.push(ch);
                    }else {     // 当前操作符优先级高
                        num1 = numStack.pull();
                        num2 = numStack.pull();
                        numStack.push(operStack.cal(num1,num2,ch));
                    }
                }
            }else {     // 数，入数栈
                numStack.push(ch-48);
            }
            if (++index >= expression.length()){
                break;
            }
        }
        // 扫描结束，处理剩余栈中数据
        CalculatorLinkedListStack numStack2 = new CalculatorLinkedListStack(10);
        CalculatorLinkedListStack operStack2 = new CalculatorLinkedListStack(10);
        // 将两个栈颠倒，防止计算顺序错误
        while (!numStack.isEmpty()){
            numStack2.push(numStack.pull());
        }
        while (!operStack.isEmpty()){
            operStack2.push(operStack.pull());
        }
        // 符号栈为空，即数字栈最后一个数为结果
        while (true){
            if (operStack2.isEmpty()){
                break;
            }else {
                num2 = numStack2.pull();
                num1 = numStack2.pull();
                numStack2.push(operStack2.cal(num1,num2,operStack2.pull()));
            }
        }
        System.out.printf("表达式结果为%d",numStack2.pull());
    }
}

class CalculatorLinkedListStack{
    private int maxSize;
    private int[] stack;
    private int top = -1;

    public CalculatorLinkedListStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    public void push(int value){
        if (isFull()){
            System.out.println("栈已满");
        }else {
            stack[++top] = value;
        }
    }

    public int pull(){
        if (isEmpty()){
            throw new RuntimeException("栈已空");
        }else {
            return stack[top--];
        }
    }

    public void list(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0; i--) {
            System.out.printf("stack[%d]=%s\n",i,stack[i]);
        }
    }

    public boolean isFull(){
        return top == maxSize - 1;
    }

    public boolean isEmpty(){
        return top == -1;
    }

    public int priority(int oper){
        if (oper == '*' || oper == '/'){
            return 1;
        }else if (oper == '+' || oper == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    public int priorityPeek(){
        int oper = pull();
        int priority;
        if (oper == '*' || oper == '/'){
            priority = 1;
        }else if (oper == '+' || oper == '-'){
            priority = 0;
        }else {
            priority = -1;
        }
        push(oper);
        return priority;
    }

    public boolean isOper(char oper){
        return oper == '+' || oper == '-' || oper == '*' || oper == '/';
    }

    // 其中num1为先弹出来的数，num2位后弹出来的数，做减法除法时注意
    public int cal(int num1, int num2, int oper){
        int res=0;
        switch(oper){
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2-num1;
                break;
            case '*':
                res = num1*num2;
                break;
            case '/':
                res = num2/num1;
                break;
            default:
                break;
        }
        return res;
    }
}
