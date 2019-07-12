package App;

import FluentDesign.Basic.FDTextLabel;
import FluentDesign.ListView.ListItemRender;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class FileNameItemRender extends ListItemRender<FileName> {

    private JLabel avatarIcon;
    private FDTextLabel titleTxt;
    private FDTextLabel contentTxt;

    public FileNameItemRender() {
        super();
        URL appIconURL = ClassLoader.getSystemResource("cf/bautroixa/.unknown.png");
        avatarIcon = new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        titleTxt = new FDTextLabel.Builder("").color(new Color(34, 91, 171)).build();
        contentTxt = new FDTextLabel.Builder("").color(new Color(255, 3, 0)).build();

        // content panel
        JPanel contentPanel = new JPanel(new GridLayout(0, 1));
        contentPanel.add(titleTxt);
        contentPanel.add(contentTxt);

        // appPanel use border layout
        setLeftComponent(avatarIcon);
        setCenterComponent(contentPanel);
    }

    @Override
    public void bind(FileName fileName) {
        URL appIconURL = ClassLoader.getSystemResource("cf/bautroixa/"+fileName.getExtension()+".png");
        if (appIconURL != null){
            avatarIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        } else {
            appIconURL = ClassLoader.getSystemResource("cf/bautroixa/.unknown.png");
            avatarIcon.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(appIconURL)));
        }

        titleTxt.setText(fileName.getOldName());
        contentTxt.setText(fileName.getNewName());

        // set Opaque to change background color of JLabel
        avatarIcon.setOpaque(true);
        titleTxt.setOpaque(true);
        contentTxt.setOpaque(true);
    }

    @Override
    public void selectedStyle(Color background) {
        avatarIcon.setBackground(background);
        titleTxt.setBackground(background);
        contentTxt.setBackground(background);
    }

    @Override
    public void notSelectedStyle(Color background) {
        avatarIcon.setBackground(background);
        titleTxt.setBackground(background);
        contentTxt.setBackground(background);
    }

}
