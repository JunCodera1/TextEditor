package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;

import Model.TreepadModel;
import View.TreepadView;

public class TreepadController implements ActionListener {
    TreepadModel model;
    TreepadView view;

    public TreepadController(TreepadView view) {
        this.view = view;
    }

    public void setModel(TreepadModel model) {
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Open":
                model.open(view.getTextArea(), view);
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Save":
                model.save(view.getTextArea());
                break;
        }
    }
}
