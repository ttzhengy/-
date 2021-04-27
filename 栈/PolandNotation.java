package 栈;

import java.util.*;

public class PolandNotation {
    public static void main(String[] args) {
        // String expresion = "-1+((2+3)*4)-5";
        String expresion = "-1+(4*4)-(7+5/5)";
        List<String> list = toInfixExpression(expresion);
        List<String> SuffixList = parseSuffixExpressionLisf(list);
        System.out.println(SuffixList);

        // String suffixExpression = "30 4 + 5 * 6 -";
        // String suffixExpression = "-4 5 * 8 - 60 + 8 2 / +";
        // String suffixExpression = "-4 -5 *";
        //
        // List<String> rpnList = getListString(suffixExpression);
        int res = calculate(SuffixList);
        System.out.println(res);
    }

    public static List<String> getListString(String suffixExpression){
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        Collections.addAll(list,split);
        return list;
    }

    public static int calculate(List<String> L){
        Stack<String> stack = new Stack<>();
        for (String item:L) {
            // if (item.matches("\\d+")){  //匹配多位数,但是不能匹配负数和小数
            if (item.matches("-?[0-9]+(\\.[0-9]+)?")){  //匹配多位数,但是不能匹配小数
                stack.push(item);
            }else {
                int num2 = Integer.parseInt(stack.pop());
                int num1;
                // 当首个数字是负数时
                if (!stack.isEmpty()) {
                    num1 = Integer.parseInt(stack.pop());
                }else {
                    num1=0;
                }
                int res = 0;
                if (item.equals("+")){
                    res = num1 + num2;
                }else if (item.equals("-")){
                    res = num1 - num2;
                }else if (item.equals("*")){
                    res = num1 * num2;
                }else if (item.equals("/")){
                    res = num1 / num2;
                }else {
                    throw new RuntimeException("运算符错误");
                }
                stack.push(""+res);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static List<String> toInfixExpression (String ex){
        List<String> list = new ArrayList<>();
        int index = 0;
        String str = "";
        char c ;
        while (index<ex.length()){  // 扫描字符串，转为list
            if (!Character.isDigit(c=ex.charAt(index))){    // 若char不是数字，直接添加到list
                list.add(""+c);
                index++;
            }else {
                // 如果是数字，先把存放多位数的str清空
                str = "";
                // 当未到字符串尾，且当前扫描的字符是数字，则将字符合并到字符串str
                while (index<ex.length() && Character.isDigit(c=ex.charAt(index))){
                    str+=c;
                    index++;    // 只有当当前位是数字是才自加，否则进入下一个循环，添加字符
                }
                list.add(str);
            }
        }
        return list;
    }

    public static List<String> parseSuffixExpressionLisf(List<String> infixEx){
        Stack<String> s1 = new Stack<>();
        // Stack<String> s2 = new Stack<>();
        List<String> s2 = new ArrayList<>();
        for (String ele:infixEx) {
            if (ele.matches("[0-9]+")){
                s2.add(ele);
            }else if (ele.equals("(")){
                s1.push(ele);
            }else if (ele.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            }else{
                // 当s1不为空，且s1顶不为左括号时
                // 若ele优先级小于等于s1栈顶，将s1栈顶弹出至s2，并继续比较ele与新栈顶
                // 否则压栈
                while (s1.size() != 0 && !s1.peek().equals("(") && Operation.getValue(ele)<=Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                s1.push(ele);
            }
        }

        // 将s1剩余的压入s2
        while (!s1.isEmpty()){
            s2.add(s1.pop());
        }

        // 此处s2不是栈，因此不需要逆序输出
        return s2;
    }
}

class Operation{
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    public static int getValue(String oper){
        int result = 0;
        switch(oper){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                break;
        }
        return result;
    }
}
