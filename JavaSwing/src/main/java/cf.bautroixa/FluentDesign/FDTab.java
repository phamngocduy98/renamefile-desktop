package FluentDesign;

import sun.swing.SwingUtilities2;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.text.View;
import java.awt.*;

/**
 * This class implement TabLayout
 * @author Phạm Ngọc Duy
 */
public class FDTab extends JTabbedPane {
    public FDTab() {
        super();
        setUI(new BasicTabbedPaneUI() {

            private final Insets borderInsets = new Insets(0, 0, 0, 0);
            private final Color borderColor = new Color(34, 91, 171);
            private final Color activatedTextColor = new Color(1,1,1);
            private final Color textColor = new Color(98,98,98);

            @Override
            protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            }

            @Override
            protected Insets getContentBorderInsets(int tabPlacement) {
                return borderInsets;
            }

            @Override
            protected void paintTabBorder(Graphics graphics, int tabPlacement, int tabIndex, int x, int y, int width, int height, boolean isSelected) {
                Graphics2D g2 = (Graphics2D) graphics;
                g2.setColor(borderColor);
                g2.setStroke(new BasicStroke(3));
                if (isSelected) {
                    g2.drawLine(x + 13, y + height-3, x + width - 13, y + height-3);
                }
            }

            @Override
            protected void paintTabBackground(Graphics graphics, int i, int i1, int i2, int i3, int i4, int i5, boolean b) {
            }

            @Override
            protected void paintFocusIndicator(Graphics graphics, int i, Rectangle[] rectangles, int i1, Rectangle rectangle, Rectangle rectangle1, boolean b) {
            }
            @Override
            protected int getTabLabelShiftY(int var1, int var2, boolean var3) {
                return 0;
            }

            @Override
            protected int getTabLabelShiftX(int i, int i1, boolean b) {
                return 0;
            }

            @Override
            protected void paintText(Graphics var1, int var2, Font var3, FontMetrics var4, int var5, String var6, Rectangle var7, boolean var8) {
                var1.setFont(var3);
                View var9 = this.getTextViewForTab(var5);
                if (var9 != null) {
                    var9.paint(var1, var7);
                } else {
                    int var10 = this.tabPane.getDisplayedMnemonicIndexAt(var5);
                    if (this.tabPane.isEnabled() && this.tabPane.isEnabledAt(var5)) {
                        Color var11 = this.tabPane.getForegroundAt(var5);
                        if (var8) {
                            Color var12 = UIManager.getColor("TabbedPane.selectedForeground");
                            if (var12 != null) {
                                var11 = var12;
                            }
                        }

                        var1.setColor(var11);
                        SwingUtilities2.drawStringUnderlineCharAt(this.tabPane, var1, var6, var10, var7.x, var7.y + var4.getAscent());
                    } else {
                        var1.setColor(this.tabPane.getBackgroundAt(var5).brighter());
                        SwingUtilities2.drawStringUnderlineCharAt(this.tabPane, var1, var6, var10, var7.x, var7.y + var4.getAscent());
                        var1.setColor(this.tabPane.getBackgroundAt(var5).darker());
                        SwingUtilities2.drawStringUnderlineCharAt(this.tabPane, var1, var6, var10, var7.x - 1, var7.y + var4.getAscent() - 1);
                    }
                }

            }

        });
    }
}
