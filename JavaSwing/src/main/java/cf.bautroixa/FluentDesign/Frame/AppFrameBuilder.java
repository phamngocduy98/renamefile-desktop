package FluentDesign.Frame;

import javax.swing.*;
import java.awt.*;

/**
 * This class implement Builder for AppFrame
 * @author Phạm Ngọc Duy
 */
public abstract class AppFrameBuilder<T extends AppFrameBuilder<T, F>, F extends AppFrame> {
    protected F appFrame;

    public AppFrameBuilder() {
        newAppFrame();
        centerScreen();
    }

    public abstract void newAppFrame();
    public abstract T getBuilderInstance();

    public T centerScreen() {
        // JFrame center the screen:
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        appFrame.setLocation(dim.width / 2 - appFrame.getSize().width / 2, dim.height / 2 - appFrame.getSize().height / 2);
        return getBuilderInstance();
    }

    public T exitOnClose(){
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return getBuilderInstance();
    }

    public T margin(int top, int right, int bottom, int left){
        appFrame.getRootPane().setBorder(BorderFactory.createEmptyBorder(top, left, bottom, right));
        return getBuilderInstance();
    }

    public T title(String title) {
        appFrame.setTitle(title);
        return getBuilderInstance();
    }

    public T icon(Image iconImage) {
        appFrame.setIconImage(iconImage);
        return getBuilderInstance();
    }

    public T size(int xLen, int yLen) {
        appFrame.setSize(xLen, yLen);
        return getBuilderInstance();
    }

    public T topPanel(Component panel) {
        appFrame.add(panel, BorderLayout.NORTH);
        return getBuilderInstance();
    }

    public T leftPanel(Component panel) {
        appFrame.add(panel, BorderLayout.WEST);
        return getBuilderInstance();
    }

    public T rightPanel(Component panel) {
        appFrame.add(panel, BorderLayout.EAST);
        return getBuilderInstance();
    }

    public T bottomPanel(Component panel) {
        appFrame.add(panel, BorderLayout.SOUTH);
        return getBuilderInstance();
    }

    public T centerPanel(Component panel) {
        appFrame.add(panel, BorderLayout.CENTER);
        return getBuilderInstance();
    }

    public T menu(JMenuBar menuBar) {
        appFrame.setJMenuBar(menuBar);
        return getBuilderInstance();
    }

    public F build() {
        appFrame.setVisible(true);
        return appFrame;
    }

}
