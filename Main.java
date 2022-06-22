import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        //假设已经有一棵树，实现加减乘除 if while begin define

        HashMap<String, String> env = new HashMap<>();
        Scanner in = new Scanner(System.in);

       // String s = in.nextLine();
        String s1 = "(* (+ 2 3) (/ 4 2))";
        Node tree = retrieve(s1);

        tree.show();

        tree = evaluate(tree, env);
        tree.show();
    }

    /*
    考虑数据类型的运算到底应该怎么写？？
    1、所有的value都定义为string类型，在创建节点的时候通过正则表达式加入type，运算时根据type将string转化为对应类型运算
    2、在创建节点的时候根据正则表达式判断类型，传入参数<T>。问题在于父节点的类型如何定义？定义为object？计算之后又该如何进行转化？
        创建一个新的父节点替换之？新的父节点的<T>又该如何得到呢？
     */

    //问题：除号和只能输出浮点数
    static Node evaluate(Node root, HashMap<String, String> env) {
        if (!root.hasChild()) {
            if (root.isToken()) root.setValue(env.get(root.getValue()));
            return root;
        } else {
            Node child1 = root.getChildren().get(0);
            Node child2 = root.getChildren().get(1);
            Node child3 = root.getChildren().get(2);

            double num1 = 0;
            double num2 = 0;
            if ((evaluate(child2, env).getType().equals("num"))) num1 = Double.parseDouble(evaluate(child2, env).getValue());
            if ((evaluate(child3, env).getType().equals("num"))) num2 = Double.parseDouble(evaluate(child3, env).getValue());


            switch (evaluate(child1, env).getValue()) {
                case "+":
                    root.setValue(String.valueOf(num1 + num2));
                    root.setType("num");
                    root.setChildren(new ArrayList<>(0));
                    break;

                case "-":
                    root.setValue(String.valueOf(num1 - num2));
                    root.setType("num");
                    root.setChildren(new ArrayList<>(0));
                    break;

                case "*":
                    root.setValue(String.valueOf(num1 * num2));
                    root.setType("num");
                    root.setChildren(new ArrayList<>(0));
                    break;

                case "/":
                    root.setValue(String.valueOf(num1 / num2));
                    root.setType("num");
                    root.setChildren(new ArrayList<>(0));
                    break;

                case "%":
                    root.setValue(String.valueOf(num1 % num2));
                    root.setType("num");
                    root.setChildren(new ArrayList<>(0));
                    break;

//                case "&":
//                    return (Boolean)evaluate(root.getChildren(), env) & (Boolean)evaluate(root.getChildren(), env);
//
//                case "|":
//                    return (Boolean)evaluate(root.getChildren(), env) | (Boolean)evaluate(root.getChildren(), env);

//                case "if":
//                    if ((Boolean) evaluate(root.getChildren(),env)) return evaluate(root.getChildren(), env);
//                    else return evaluate(root.getChildren(), env);

    //如果表达式里有多个符号怎么办？
//                case "while":
//                    Object value = null;
//                    while ((Boolean) evaluate(root.getChildren(),env)){
//                        value = evaluate(root.getChildren(),env);
//                    }
//                    return value;

                case "define":
                    env.put(child2.getValue(), child3.getValue());
                    break;

                case "set":
                    env.put(child2.getValue(), child3.getValue());
                    break;

   // 语句怎么解析？
//                case "begin":
//                    evaluate(root.getChildren(), env);
//                    evaluate(root.getChildren(), env);
//                    return evaluate(root.getChildren(), env);
            }
        }
        return root;
    }

    public static Node retrieve(String exp) {

        Node root = new Node("und");

        Pattern numPattern = Pattern.compile("[+-]?\\d+(\\.\\d+)?"); //判断数字

        //操作符的这个正则怎么写？
        Pattern operatorPattern = Pattern.compile("^begin$ | ^if$ | while | define | set | \\+ " +
                " - | \\* | % | & | \\| $");

        for (int i = 1; i < exp.length(); i++) {
            if (exp.charAt(i) == ')') return root;
            else if (exp.charAt(i) == '(') {
                root.addChild(retrieve(exp.substring(i)));

                //跳到右括号
                int j = i + 1;
                int right = 0, left = 0;

                for (; j < exp.length(); j++) {
                    if (exp.charAt(j) == '(') right++;
                    else if (exp.charAt(j) == ')') {
                        if (right == left) break;
                        else left++;
                    }
                }
                i = j ;
            } else if (exp.charAt(i) == ' ') continue;

                //读取数据
            else {
                int j = i;
                for (; j < exp.length(); j++) {
                    if (exp.charAt(j) == ' ' || exp.charAt(j) == ')') break;
                }
                String term = exp.substring(i, j);
                Node child = new Node(term);

                //判断type
                if (numPattern.matcher(term).matches()) child.setType("num");
                    //else if (operatorPattern.matcher(term).matches()) child.setType("opera");
                else if (term.equals("+") || term.equals("-") || term.equals("*") || term.equals("\\") || term.equals("%")
                        || term.equals("&") || term.equals("|") || term.equals("if") || term.equals("while")
                        || term.equals("begin") || term.equals("set") || term.equals("define"))
                    child.setType("opera");
                else child.setType("token");

                root.addChild(child);

                //跳到停止处
                i = j - 1;
            }
        }

        return root;
    }
}
