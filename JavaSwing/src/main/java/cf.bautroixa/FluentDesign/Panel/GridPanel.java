package FluentDesign.Panel;

import java.awt.*;

/**
 * This class implement GridLayout
 * @author Phạm Ngọc Duy
 */
public class GridPanel extends BlankPanel {
    public GridPanel(int rows, int cols,int  colSpace, int rowSpace) {
        super();
        setLayout(new GridLayout(rows, cols, colSpace, rowSpace));
    }
}
