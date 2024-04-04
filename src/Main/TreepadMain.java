package Main;

import Controller.TreepadController;
import Model.TreepadModel;
import View.TreepadView;

public class TreepadMain {
    public static void main(String[] args) {
        TreepadView view = new TreepadView();
        TreepadModel model = new TreepadModel();
        TreepadController controller = new TreepadController(view, model);

        controller.setModel(model);
        view.setVisible(true);
    }
}
