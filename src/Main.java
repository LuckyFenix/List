import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        setSize(800, 800);
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

        defaultListModel = new DefaultListModel<Object>();
        list = new JList<Object>(defaultListModel);

        listModel = new ListModel();

        JButton addNewNodeBtn = new JButton("Добавить элемент");
        addNewNodeBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object data = JOptionPane.showInputDialog(getThis(),
                        "Введите новое значение:");
                if (data != null)
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
            }
        });
        JButton removeNode = new JButton("Удалить элемент");
        removeNode.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (list.getSelectedIndex() != -1)
                {
                    listModel.removeIndex(list.getSelectedIndex());
                }
                initList();
            }
        });
        JButton searchNode = new JButton("Найти элементы");
        /*
        searchNode.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Object data = JOptionPane.showInputDialog(getThis(),
                        "Введите новое значение:");
                if (data != null)
                {
                    list.setSelectedIndices(listModel.searchValue(data));
                }
            }
        });
        */
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
        System.out.println("хуйня");
        if (array.length != 0)
        {
            globalPanel.removeAll();
            globalPanel.add(list, BorderLayout.NORTH);
            defaultListModel.removeAllElements();
            for (int i = 0; i < array.length; i++)
            {
                defaultListModel.addElement(array[i]);
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
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setVisible(true);
    }
}
