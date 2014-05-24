import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame
{
    private JPanel globalPanel;
    private DefaultListModel<Object> defaultListModel;
    private JList<Object> list;
    private ListModel listModel;
    private JLabel label = new JLabel("Список пуст");

    public Main()
    {
        setSize(500, 500);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        globalPanel = new JPanel(new BorderLayout());
        globalPanel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                super.mouseClicked(e);
                if (e.getClickCount() == 2)
                {
                    list.clearSelection();
                }
            }
        });
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        defaultListModel = new DefaultListModel<>();
        list = new JList<>(defaultListModel);
        list.setAutoscrolls(true);

        listModel = new ListModel();

        JButton addNewNodeBtn = new JButton("Добавить элемент");
        addNewNodeBtn.addActionListener(e ->
        {
            Object data = JOptionPane.showInputDialog(getThis(),
                    "Введите новое значение:");
            if (data != null && !data.toString().trim().equals(""))
            {
                int index = list.getSelectedIndex();
                if (index == -1)
                {
                    listModel.addNode(data);
                    initList();
                } else
                {
                    listModel.addNode(data, index);
                    initList();
                }
            }
        });
        JButton removeNode = new JButton("Удалить элемент");
        removeNode.addActionListener(e ->
        {
            if (list.getSelectedIndex() != -1)
            {
                listModel.removeIndex(list.getSelectedIndex());
            }
            initList();
        });
        JButton searchNode = new JButton("Найти элементы");
        searchNode.addActionListener(e ->
        {
            Object data = JOptionPane.showInputDialog(getThis(),
                    "Введите элемент, который хотите найти:");
            if (data != null && !data.toString().trim().equals(""))
            {
                int[] array = listModel.searchValue(data);
                list.setSelectedIndices(array);
            }
        });
        btnPanel.add(addNewNodeBtn);
        btnPanel.add(removeNode);
        btnPanel.add(searchNode);

        globalPanel.add(label, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(globalPanel);
        scrollPane.setPreferredSize(new Dimension(500, 500));

        add(scrollPane, BorderLayout.CENTER);
        add(btnPanel, BorderLayout.EAST);
    }

    private void initList()
    {
        Object[] array = listModel.getArray();
        if (array.length != 0)
        {
            globalPanel.removeAll();
            globalPanel.add(list, BorderLayout.NORTH);
            defaultListModel.removeAllElements();
            for (Object anArray : array)
            {
                defaultListModel.addElement(anArray);
            }
        } else
        {
            globalPanel.removeAll();
            globalPanel.add(label, BorderLayout.NORTH);
        }
        globalPanel.updateUI();
    }

    public Main getThis()
    {
        return this;
    }

    public static void main(String[] args)
    {
        Main main = new Main();
        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
