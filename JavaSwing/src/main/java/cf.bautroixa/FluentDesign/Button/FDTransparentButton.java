package FluentDesign.Button;

import FluentDesign.Button.FDButton;

import java.awt.*;

public class FDTransparentButton extends FDButton {

    private final Color backgroundColor = new Color(255, 255, 255);
    private final Color hoverBorderColor = new Color(255, 255, 255);
    private final Color textColor = Color.BLACK;
    public FDTransparentButton(String text) {
        super(text);
        setTextColor(textColor);
        setBackgroundColor(backgroundColor);
        setHoverBorderColor(hoverBorderColor);
    }
}
