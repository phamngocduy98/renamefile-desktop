package FluentDesign.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement BoxLayout
 * @author Phạm Ngọc Duy
 */
public class LinearPanel extends BlankPanel {
    public LinearPanel(int boxLayoutAxis) {
        super();
        setLayout(new BoxLayout(this, boxLayoutAxis));
    }
    public void rightAlign(){
        setAlignmentX(Component.RIGHT_ALIGNMENT);
    }
    public void leftAlign(){
        setAlignmentX(Component.LEFT_ALIGNMENT);
    }
    public void topAlign(){
        setAlignmentY(Component.TOP_ALIGNMENT);
    }
    public void bottomAlign(){
        setAlignmentY(Component.BOTTOM_ALIGNMENT);
    }
    public void centerAlign(){
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
    }
    public void addSpace(int xSize, int ySize){
        add(Box.createRigidArea(new Dimension(xSize, ySize)));
    }
    public void addVerticalExpandingSpace(){
        add(Box.createVerticalGlue());
    }
    public void addHorizontalExpandingSpace(){
        add(Box.createHorizontalGlue());
    }


}
