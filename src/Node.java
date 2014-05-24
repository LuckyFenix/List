
public class Node
{
    private Object data;
    private String date;
    private Node previousNode;
    private Node nextNode;

    public Node(Object data, String date, Node previousNode, Node nextNode)
    {
        this.data = data;
        this.date = date;
        this.previousNode = previousNode;
        this.nextNode = nextNode;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public Node getPreviousNode()
    {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode)
    {
        this.previousNode = previousNode;
    }

    public Node getNextNode()
    {
        return nextNode;
    }

    public void setNextNode(Node nextNode)
    {
        this.nextNode = nextNode;
    }
}
