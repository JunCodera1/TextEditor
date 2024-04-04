package Model;

import java.awt.Component;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TreepadModel {
    
    public TreepadModel() {
    }

    public void open(JTextArea textArea, Component parentComponent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int return_value = chooser.showOpenDialog(parentComponent); 
        if (return_value == JFileChooser.APPROVE_OPTION) {
            File file_folder = chooser.getSelectedFile();
            open_file_folder(file_folder, textArea);
        }
    }
    
    public void open_file_folder(File file_folder, JTextArea textArea) {
        if(file_folder.isDirectory()) {
            File[] files = file_folder.listFiles();
            if(files != null) {
                for(File file : files) {
                    open_file_folder(file, textArea);
                }
            }
        }
        else {
            try(BufferedReader reader = new BufferedReader(new FileReader(file_folder))) {
                StringBuilder idea = new StringBuilder();
                String line;
                while((line = reader.readLine())!= null){
                    idea.append(line).append("\n");
                }
                textArea.append(idea.toString() + "\n\n");
                
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void save(JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Text Files", "txt");
        fileChooser.setFileFilter(filter);
        
        int returnValue = fileChooser.showSaveDialog(null);
        if(returnValue == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                writer.write(textArea.getText());
            }catch(IOException e) {
                e.printStackTrace();
            }
        }
        
    }
}
