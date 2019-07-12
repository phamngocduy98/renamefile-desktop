package FluentDesign.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement Main App Frame
 * @author Phạm Ngọc Duy
 */
public class AppFrame extends JFrame {
    public AppFrame() throws HeadlessException {
        init();
    }

    public AppFrame(String s) throws HeadlessException {
        super(s);
        init();
    }
    public void init(){
        setMinimumSize(new Dimension(200,200));
        setSize(new Dimension(500, 500));
        setLayout(new BorderLayout());
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

    public static class Builder extends AppFrameBuilder<AppFrame.Builder, AppFrame> {
        public Builder() {
            super();
        }

        @Override
        public void newAppFrame() {
            appFrame = new AppFrame("App");
        }

        @Override
        public Builder getBuilderInstance() {
            return this;
        }
    }
}
