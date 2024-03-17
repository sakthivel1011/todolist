import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ToDoList extends JFrame {
    private ArrayList<String> tasks = new ArrayList<>();
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public ToDoList() {
        setTitle("To-Do List Application");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // FIX: Qualify EXIT_ON_CLOSE with JFrame
        setLocationRelativeTo(null); // Center the window
        initComponents();
    }

    private void initComponents() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        mainPanel.add(new JScrollPane(taskList), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JTextField taskTextField = new JTextField(20);
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskTextField.getText().trim();
                if (!task.isEmpty()) {
                    tasks.add(task);
                    listModel.addElement(task);
                    taskTextField.setText("");
                }
            }
        });

        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    tasks.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });

        buttonPanel.add(taskTextField);
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ToDoList().setVisible(true);
            }
        });
    }
}
