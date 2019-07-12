package FluentDesign.Basic;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement TextView
 * @author Phạm Ngọc Duy
 */
public class FDTextLabel extends JLabel {
    private String fontFamily = "Segoe UI";
    private int fontStyle = Font.PLAIN;
    private int fontSize = 15;
    public FDTextLabel(String s) {
        super(s);
        setFont(new Font("Segoe UI", Font.PLAIN, 15));
    }
    public void setTextSize(int fontSize){
        this.fontSize = fontSize;
        setFont(new Font(fontFamily, fontSize, fontSize));
    }

    public static class Builder {
        private FDTextLabel fdTextLabel;

        public Builder(String text) {
            this.fdTextLabel = new FDTextLabel(text);
        }

        public Builder text(String text){
            fdTextLabel.setText(text);
            return this;
        }

        public Builder size(int textSize){
            fdTextLabel.setTextSize(textSize);
            return this;
        }
        public Builder color(Color color){
            fdTextLabel.setForeground(color);
            return this;
        }
        public Builder margin(int top, int right, int bottom, int left){
            fdTextLabel.setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
            return this;
        }
        public FDTextLabel build(){
            return fdTextLabel;
        }
    }
}

