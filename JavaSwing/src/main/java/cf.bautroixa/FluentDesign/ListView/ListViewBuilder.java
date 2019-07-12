package FluentDesign.ListView;


import FluentDesign.Panel.BorderPanel;
import FluentDesign.Panel.FDScrollPane;

import javax.swing.*;
import java.util.ArrayList;

/**
 * This class implement List View Builder
 * @author Phạm Ngọc Duy
 */
public class ListViewBuilder<T> extends JPanel {
    DefaultListModel<T> dataModel;
    JList<T> list = null;

    public ListViewBuilder() {
    }

    public ListViewBuilder(ArrayList<T> objects) {
        super();
        // new dataModel
        dataModel = getNewModelFromList(objects);
        // make JList for dataModel
        list = new JList<T>(dataModel);
    }
    private DefaultListModel getNewModelFromList(ArrayList<T> objects){
        DefaultListModel newDataModel = new DefaultListModel<>();
        for (T object : objects){
            newDataModel.addElement(object);
        }
        return newDataModel;
    }
    public DefaultListModel getModel(){
        return dataModel;
    }

    public ListViewBuilder setItemRender(ListItemRender listItemRender){
        list.setCellRenderer(listItemRender);
        return this;
    }
    public ListViewBuilder setOrientation(int jListOrientation){
        list.setLayoutOrientation(jListOrientation);
        list.setVisibleRowCount(-1);
        return this;
    }
    public JPanel buildwithPanel(){
        BorderPanel panel = new BorderPanel();
        FDScrollPane scrollPane = new FDScrollPane(list);
//        scrollPane.setPreferredSize(new Dimension(250,80));
        panel.setCenterComponent(scrollPane);
        return panel;
    }
    public JList<T> build(){
        return list;
    }
}
