package FluentDesign.Panel;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement Main App Panel
 * @author Phạm Ngọc Duy
 */
public class BorderPanel extends JPanel {
    private int colSpace = 5;
    private int rowSpace = 5;
    public BorderPanel() {
        setLayout(new BorderLayout(colSpace,rowSpace));
    }

    public void setMargin(int top, int right, int bottom, int left){
        setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
    }
    public void setTopComponent(Component component) {
        add(component, BorderLayout.NORTH);
    }

    public void setLeftComponent(Component component) {
        add(component, BorderLayout.WEST);
    }

    public void setRightComponent(Component component) {
        add(component, BorderLayout.EAST);
    }

    public void setBottomComponent(Component component) {
        add(component, BorderLayout.SOUTH);
    }

    public void setCenterComponent(Component component) {
        add(component, BorderLayout.CENTER);
    }



    public Component getTopComponent() {
        return ((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.NORTH);
    }

    public Component getLeftComponent() {
        return ((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.WEST);
    }
    public Component getRightComponent() {
        return ((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.EAST);
    }
    public Component getBottomComponent() {
        return ((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.SOUTH);
    }
    public Component getCenterComponent() {
        return ((BorderLayout)this.getLayout()).getLayoutComponent(BorderLayout.CENTER);
    }



    public static class Builder{
        BorderPanel borderPanel;

        public Builder() {
            this.borderPanel = new BorderPanel();
        }

        public Builder margin(int top, int right, int bottom, int left){
            borderPanel.setMargin(top, right, bottom, left);
            return this;
        }

        public Builder topComponent(Component component) {
            borderPanel.add(component, BorderLayout.NORTH);
            return this;
        }

        public Builder leftComponent(Component component) {
            borderPanel.add(component, BorderLayout.WEST);
            return this;
        }

        public Builder rightComponent(Component component) {
            borderPanel.add(component, BorderLayout.EAST);
            return this;
        }

        public Builder bottomComponent(Component component) {
            borderPanel.add(component, BorderLayout.SOUTH);
            return this;
        }

        public Builder centerComponent(Component component) {
            borderPanel.add(component, BorderLayout.CENTER);
            return this;
        }
        public BorderPanel build(){
            return borderPanel;
        }
    }
}
