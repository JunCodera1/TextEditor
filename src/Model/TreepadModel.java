package Model;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

public class TreepadModel {
    private Map<String, DefaultMutableTreeNode> openedFolders; 

    public TreepadModel() {
        openedFolders = new HashMap<>();
    }

    public void open(JTextArea textArea, Component parentComponent, JTree tree) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int return_value = chooser.showOpenDialog(parentComponent);
        if (return_value == JFileChooser.APPROVE_OPTION) {
            File file_folder = chooser.getSelectedFile();
            if (file_folder.isDirectory()) {
                open_file_folder(file_folder, textArea, tree);
            }
        }

        // Add mouse listener to the tree
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    handleTreeMouseClick(e, textArea, tree);
                }
            }
        });
    }
    public void save(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);

        int returnValue = fileChooser.showSaveDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(textArea.getText());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void open_file_folder(File file_folder, JTextArea textArea, JTree tree) {
        String path = file_folder.getAbsolutePath();
        DefaultMutableTreeNode node = openedFolders.get(path);
        if (node != null) {
            expandNode(node, tree);
        } else {
            DefaultMutableTreeNode root = new DefaultMutableTreeNode(file_folder.getName());
            openedFolders.put(path, root);
            fillTree(file_folder, root);
            DefaultTreeModel treeModel = (DefaultTreeModel) tree.getModel();
            treeModel.setRoot(root);
            expandNode(root, tree);
        }

        ((DefaultTreeModel) tree.getModel()).reload();
    }

    public void handleTreeMouseClick(MouseEvent e, JTextArea textArea, JTree tree) {
        int selRow = tree.getRowForLocation(e.getX(), e.getY());
        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
        if (selRow != -1) {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) selPath.getLastPathComponent();
            if (selectedNode.isLeaf()) { 
                File selectedFile = new File(selectedNode.toString());
                if (selectedFile.isFile()) { 
                    System.out.println("Opening file: " + selectedFile.getAbsolutePath()); // Ghi log
                    openSelectedFile(selectedFile, textArea);
                }
            }
        }
    }



    private void openSelectedFile(File file, JTextArea textArea) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder idea = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                idea.append(line).append("\n");
            }
            textArea.append(idea.toString()); // Sử dụng append để thêm nội dung mới vào JTextArea
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



   

    public void fillTree(File directory, DefaultMutableTreeNode parent) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                DefaultMutableTreeNode node = new DefaultMutableTreeNode(file.getName());
                parent.add(node);
                if (file.isDirectory()) {
                    fillTree(file, node);
                }
            }
        }
    }

    private void expandNode(DefaultMutableTreeNode node, JTree tree) {
        TreeNode[] path = node.getPath();
        TreePath treePath = new TreePath(path);
        tree.expandPath(treePath);
    }
}