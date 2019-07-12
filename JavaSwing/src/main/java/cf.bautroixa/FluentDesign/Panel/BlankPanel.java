package FluentDesign.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement JPanel with some more function
 * @author Phạm Ngọc Duy
 */
public class BlankPanel extends JPanel {
    public BlankPanel() {
        super();
    }
    public void setMargin(int top, int right, int bottom, int left){
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }
    public int findItemIndex(Component component){
        int count = 0;
        for (Component subComponent : this.getComponents()){
            if (subComponent.equals(component)){
                return count;
            }
            count++;
        }
        return -1;
    }
    public void notifyItemChanged(){
        this.revalidate();
        this.repaint();
    }
}
