package FluentDesign;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class implement Look and feel
 *
 * @author Phạm Ngọc Duy
 */
public class FDLookAndFeel {
    public FDLookAndFeel() {

    }

    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

    public static void applyLookAndFeel() {
        // font:
        setUIFont(new javax.swing.plaf.FontUIResource("Segoe UI", Font.PLAIN, 15));

        //theme
        UIManager.put("MenuItem.background", new Color(242, 242, 242));
        UIManager.put("MenuItem.font", new Font("Segoe UI", Font.PLAIN, 15));
        UIManager.put("MenuItem.border", new EmptyBorder(5, 10, 5, 10));

        UIManager.put("Menu.background", new Color(242, 242, 242));
        UIManager.put("Menu.font", new Font("Segoe UI", Font.PLAIN, 15));
        UIManager.put("Menu.border", new EmptyBorder(5, 10, 5, 10));

        UIManager.put("Menu.background", new Color(242, 242, 242));
        UIManager.put("Menu.font", new Font("Segoe UI", Font.PLAIN, 15));
        UIManager.put("Menu.border", new EmptyBorder(5, 10, 5, 10));


        UIManager.put("TabbedPane.font", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("TabbedPane.tabInsets", new Insets(10, 10, 10, 10));
        UIManager.put("TabbedPane.selectedForeground", new Color(0, 0, 0));
        UIManager.put("TabbedPane.foreground", new Color(98, 98, 98));

        UIManager.put("ComboBox.background", new Color(255, 255, 255));
        UIManager.put("ComboBox.font", new Font("Segoe UI", Font.PLAIN, 18));
        UIManager.put("ComboBox.border", BorderFactory.createLineBorder(new Color(122, 122, 122), 2));

//        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    }
}
