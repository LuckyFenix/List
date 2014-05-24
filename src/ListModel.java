import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class ListModel
{
    private Node rootNode;
    private int size;
    public DateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");

    public ListModel()
    {
        rootNode = new Node(null, dateFormat.format(new Date()), rootNode, rootNode);
        rootNode.setPreviousNode(rootNode);
        rootNode.setNextNode(rootNode);
        size = 0;
    }

    public Object[] getArray()
    {
        Object[] array = new Object[size];
        Node thisNode = rootNode;
        for (int i = 0; i < size; i++)
        {
            thisNode = thisNode.getNextNode();
            array[i] = thisNode.getData();
            System.out.println(array[i]);
        }
        System.out.println();
        return array;
    }

    public void addNode(Object data)
    {
        Node newNode = new Node(data, dateFormat.format(new Date()), rootNode.getPreviousNode(), rootNode);
        rootNode.getPreviousNode().setNextNode(newNode);
        rootNode.setPreviousNode(newNode);
        size++;
    }

    public void addNode(Object data, int index)
    {
        Node node = searchIndex(index);
        Node newNode = new Node(data, dateFormat.format(new Date()), node.getPreviousNode(), node);
        node.getPreviousNode().setNextNode(newNode);
        node.setPreviousNode(newNode);
        size++;
    }

    public void removeIndex(int index)
    {
        Node node = searchIndex(index);
        node.getPreviousNode().setNextNode(node.getNextNode());
        node.getNextNode().setPreviousNode(node.getPreviousNode());
        size--;
    }

    public Node searchIndex(int index)
    {
        Node node = rootNode;

        if (index < (size >> 1))
        {
            for (int i = 0; i <= index; i++)
                node = node.getNextNode();
        }
        else
        {
            for (int i = size; i > index; i--)
                node = node.getPreviousNode();
        }

        return node;
    }

    /*
    public int[] searchValue(Object data)
    {
        ArrayList<Node> array = new ArrayList<Node>();
        Node node = rootNode;

        while (true)
        {
            node = node.getNextNode();
            if (node.getData() == data)
            {
                array.add(node);
            }
        }

        int[] intArray = new int[array.size()];
        for (int i = size; i < array.size(); i++)
        {
            intArray[i] = Integer.parseInt((String) array.get(i).getData());
        }

        //return intArray;
    }
    */
}
