import java.util.ArrayList;
import java.util.HashMap;

class Node {

    private ArrayList<Node> children = new ArrayList<>(0);

    private String value;
    private String type;

    //private T value; 笑死，泛型根本不会用
    //另一个问题是父节点创建的时候，不知道children们的数据类型，此时传入的T不能确定（之后还能改吗？）

    public Node() {}

    public Node(ArrayList<Node>children, String value) {
        this.children = children;
        this.value = value;
    }


    public Node(String value, String type) {
        this.value = value;
        this.type = type;
    }
    public Node(String value) {
        this.value = value;
    }

    public boolean hasChild(){
        return (children.size() != 0);
    }

    public boolean isToken(){
        //判断是字母？？
        return false;
    }

    public int getLength(){
        return children.size();
    }

    public void show(){
        if (type != null) System.out.println(value + " " + type);

        //hasChild
        else{
            System.out.println(value);
            for (Node child : children) child.show();
        }
    }

    //getter and setter

    public ArrayList<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }

    public void addChild(Node child) {
        this.children.add(child);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}