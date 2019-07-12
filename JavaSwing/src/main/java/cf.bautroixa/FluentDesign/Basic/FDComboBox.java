package FluentDesign.Basic;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class FDComboBox extends JComboBox {

    private final Color borderColor = new Color(122, 122, 122);
    public FDComboBox(Object[] objects) {
        super(objects);
//        setUI(new BasicComboBoxUI(){
//
//            @Override
//            protected JButton createArrowButton() {
//                FDTransparentButton button = new FDTransparentButton("More");
//                Color color = new Color(255,255,255);
//                BasicArrowButton var1 = new BasicArrowButton(5, color, color, borderColor, color);
//                return var1;
//            }
//        });
    }
}
