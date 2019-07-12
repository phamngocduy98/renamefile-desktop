package FluentDesign.ListView;

import FluentDesign.Panel.BorderPanel;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement render for item of JList
 * @author Phạm Ngọc Duy
 */
public abstract class ListItemRender<T> extends BorderPanel implements ListCellRenderer<T> {

    public ListItemRender() {
        setMargin(5, 5, 5, 5);
    }

    public abstract void bind(T itemObject);
    public abstract void selectedStyle(Color background);
    public abstract void notSelectedStyle(Color background);

    @Override
    public Component getListCellRendererComponent(JList<? extends T> list, T itemObject, int index, boolean isSelected, boolean cellHasFocus) {
        bind(itemObject);

        if (isSelected) {
            selectedStyle(list.getSelectionBackground());
            setBackground(list.getSelectionBackground());
        } else {
            notSelectedStyle(list.getBackground());
            setBackground(list.getBackground());
        }
        return this;
    }
}