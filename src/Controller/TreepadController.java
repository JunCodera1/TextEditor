// TreepadController.java
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import javax.swing.JTree;
import Model.TreepadModel;
import View.TreepadView;

public class TreepadController implements ActionListener {
    TreepadModel model;
    TreepadView view;

    public TreepadController(TreepadView view, TreepadModel model) {
        this.view = view;
        this.model = model;
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Open":
                model.open(view.getTextArea(), view, view.getTree());
                break;
            case "Exit":
                System.exit(0);
                break;
            case "Save":
                model.save(view.getTextArea());
                break;
        }
    }

	public void setModel(TreepadModel model2) {
		// TODO Auto-generated method stub
		
	}
}
