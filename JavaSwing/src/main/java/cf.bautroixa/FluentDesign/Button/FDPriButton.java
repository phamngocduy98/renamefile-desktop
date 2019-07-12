package FluentDesign.Button;

import java.awt.Color;

/**
 * This class implement primary button
 * @author Phạm Ngọc Duy
 */
public class FDPriButton extends FDButton {
    private final Color backgroundColor = new Color(0, 120, 215); // 0 120 215 // 0 90 158
    private final Color hoverBorderColor = new Color(102, 174, 231);
    private final Color textColor = Color.WHITE;
    public FDPriButton(String text) {
        super(text);
        setTextColor(textColor);
        setBackgroundColor(backgroundColor);
        setHoverBorderColor(hoverBorderColor);
    }
}
