package FluentDesign.Frame;

import FluentDesign.Basic.FDTextLabel;
import FluentDesign.Button.FDButton;
import FluentDesign.Button.FDPriButton;
import FluentDesign.Button.OnClickListener;
import FluentDesign.Panel.GridPanel;
import FluentDesign.Panel.LinearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;

/**
 * This class implement Dialog Frame
 * @author Phạm Ngọc Duy
 */
public class FDDialog extends AppFrame {
    // title
    private LinearPanel dialogTitlePanel;
    private FDTextLabel dialogTitle;
    // action
    private GridPanel dialogActionPanel;
    FDButton okBtn, cancelBtn;
    public FDDialog(String s) throws HeadlessException {
        super(s);
        setSize(500, 250);

        // title panel
        dialogTitlePanel = new LinearPanel(BoxLayout.X_AXIS);
        dialogTitle = new FDTextLabel.Builder("FDDialog").size(28).margin(0,0,10,0).build();
        dialogTitlePanel.add(dialogTitle);
        dialogTitlePanel.add(Box.createVerticalGlue());

        // action panel
        dialogActionPanel = new GridPanel(1,2, 10, 0);
        okBtn = new FDPriButton("OK");
        cancelBtn = new FDButton("Cancel");
        dialogActionPanel.add(okBtn);
        dialogActionPanel.add(cancelBtn);
    }

    public JLabel getDialogTitle() {
        return dialogTitle;
    }

    public JPanel getDialogTitlePanel() {
        return dialogTitlePanel;
    }

    public JPanel getDialogActionPanel() {
        return dialogActionPanel;
    }

    public FDButton getOkBtn() {
        return okBtn;
    }

    public FDButton getCancelBtn() {
        return cancelBtn;
    }

    public static class Builder extends AppFrameBuilder<FDDialog.Builder, FDDialog>{
        public Builder() {
            super();
            this.margin(20, 20, 20, 20);
            this.topPanel(appFrame.getDialogTitlePanel());
            this.bottomPanel(appFrame.getDialogActionPanel());
            appFrame.setResizable(false);

            // no title bar
//            appFrame.setUndecorated(true);
        }
        public FDDialog.Builder contentPanel(JPanel panel){
            this.centerPanel(panel);
            return getBuilderInstance();
        }

        public FDDialog.Builder setOKOnClickListener(OnClickListener onClickListener){
            appFrame.getOkBtn().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(ActionEvent actionEvent) {
                    onClickListener.onClick(actionEvent);
                    // close the frame
                    appFrame.dispatchEvent(new WindowEvent(appFrame, WindowEvent.WINDOW_CLOSING));
                }
            });
            return getBuilderInstance();
        }
        public FDDialog.Builder setCancelOnClickListener(OnClickListener onClickListener){
            appFrame.getCancelBtn().setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(ActionEvent actionEvent) {
                    onClickListener.onClick(actionEvent);
                    // close the frame
                    appFrame.dispatchEvent(new WindowEvent(appFrame, WindowEvent.WINDOW_CLOSING));
                }
            });
            return getBuilderInstance();
        }

        @Override
        public void newAppFrame() {
            appFrame = new FDDialog("FDDialog");
        }

        @Override
        public FDDialog.Builder title(String title) {
            appFrame.getDialogTitle().setText(title);
            appFrame.setTitle(title);
            return this;
        }

        @Override
        public Builder getBuilderInstance() {
            return this;
        }
    }
}
