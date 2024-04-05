// TreepadController.java
package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

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
        Map<String, Runnable> actions = new HashMap<>();
        // Open
        actions.put("Open", () -> model.open(view.getTextArea(), view, view.getTree()));
        
        //Exit
        actions.put("Exit", () -> System.exit(0));
        
        //Save
        actions.put("Save", () -> model.save(view.getTextArea()));

        String command = e.getActionCommand();
        Runnable action = actions.get(command);
        if (action != null) {
            action.run();
        }
    }

}
