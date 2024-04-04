package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

public class TreepadView extends JFrame {
        
        JMenu menu;
        JMenuItem open, save, exit;
        JMenuBar menuBar;
        JTextArea textArea;
        JTree tree;
        JScrollPane scrollText, scrollTree;
        DefaultMutableTreeNode node;
        
        public TreepadView() {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.setTitle("Treepad Editor");
            this.setSize(800, 600); 
            this.setLayout(new BorderLayout());

            node = new DefaultMutableTreeNode("Root");
            tree = new JTree(node);
            tree.setPreferredSize(new Dimension(200, getHeight()));

            textArea = new JTextArea();
            scrollText = new JScrollPane(textArea);

            scrollTree = new JScrollPane(tree);

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
        }

        public JTextArea getTextArea() {
            return textArea;
        }

        public void setTextArea(JTextArea textArea) {
            this.textArea = textArea;
        }
}
