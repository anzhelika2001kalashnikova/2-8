package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    private JButton resultButton;
    private JPanel GUIWindow;
    private JButton createButton;
    private JTextField textList;
    private JTextField textResult;
    private JButton clearButton;
    private JButton vieButton;
    LinkedList<Double> linkedList = new LinkedList<>();

    public GUI() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 115, dimension.height / 2 - 175, 230, 350);
        setContentPane(GUIWindow);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        createButton.addActionListener(e -> {
            try {
                linkedList.addLast(Double.valueOf(textList.getText()));
            } catch (NumberFormatException e1) {
                textList.setText(e1.getMessage());
                textList.setText("Введите пожалуйста число");
            }
            textList.setText("");
            textResult.setText(linkedList.getListToString());
        });
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               linkedList.insert(linkedList.getIndexByNode(linkedList.maxNode()) + 1, (-1)*linkedList.getValueByIndex(linkedList.getIndexByNode(linkedList.maxNode())));
                textResult.setText(linkedList.getListToString());
            }

        });
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                linkedList.clear();
                textResult.setText(linkedList.getListToString());
            }
        });
        vieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textResult.setText(linkedList.maxNodeToString(linkedList.maxNode()));
            }
        });
    }

}

