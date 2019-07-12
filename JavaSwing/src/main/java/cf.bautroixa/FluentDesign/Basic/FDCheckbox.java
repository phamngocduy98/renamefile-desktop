package FluentDesign.Basic;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FDCheckbox extends JCheckBox {
    public FDCheckbox() {
        super();
        init();
    }

    public FDCheckbox(String s) {
        super(s);
        init();
    }

    public void init() {
        // icon
        URL appIconURL = ClassLoader.getSystemResource("cf/bautroixa/checkbox/icon.png");
        if (appIconURL != null)
            setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        // pressed
        appIconURL = ClassLoader.getSystemResource("cf/bautroixa/checkbox/pressedIcon.png");
        if (appIconURL != null)
            setPressedIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        // selected
        appIconURL = ClassLoader.getSystemResource("cf/bautroixa/checkbox/selectedIcon.png");
        if (appIconURL != null)
            setSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        // hover
        appIconURL = ClassLoader.getSystemResource("cf/bautroixa/checkbox/rollOverIcon.png");
        if (appIconURL != null)
            setRolloverIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        //hover selected
        appIconURL = ClassLoader.getSystemResource("cf/bautroixa/checkbox/rollOverSelectionIcon.png");
        if (appIconURL != null)
            setRolloverSelectedIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
    }
}
