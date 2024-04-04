// TreepadView.java
package View;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import Controller.TreepadController;
import Model.TreepadModel;

public class TreepadView extends JFrame {

    JMenu menu;
    JMenuItem open, save, exit;
    JMenuBar menuBar;
    JTextArea textArea;
    private JTree tree;
    JScrollPane scrollText, scrollTree;
    DefaultMutableTreeNode node;
    TreepadController controller;

    public TreepadView() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Treepad Editor");
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());

        node = new DefaultMutableTreeNode("Root");
        setTree(new JTree(node));
        getTree().setPreferredSize(new Dimension(200, getHeight()));

        textArea = new JTextArea();
        scrollText = new JScrollPane(textArea);

        scrollTree = new JScrollPane(getTree());

        menuBar = new JMenuBar();
        open = new JMenuItem("Open");
        save = new JMenuItem("Save");
        exit = new JMenuItem("Exit");
        menu = new JMenu("Option");

        menu.add(open);
        menu.add(save);
        menu.add(exit);
        menuBar.add(menu);

        // Add menu bar to the frame
        this.setJMenuBar(menuBar);

        // Add components to the frame
        this.add(scrollText, BorderLayout.CENTER);
        this.add(scrollTree, BorderLayout.WEST);

        // Create controller and attach action listeners
        TreepadModel model = new TreepadModel();
        controller = new TreepadController(this, model);
        open.addActionListener(controller);
        save.addActionListener(controller);
        exit.addActionListener(controller);
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public JTree getTree() {
        return tree;
    }

    public void setTree(JTree tree) {
        this.tree = tree;
    }
}
