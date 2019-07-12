package FluentDesign.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * This class implement a secondary button
 * @author Phạm Ngọc Duy
 */
public class FDButton extends JButton {

    private final Color textColor = Color.BLACK;
    private final Color backgroundColor = new Color(204, 204, 204);
    private final Color hoverBorderColor = new Color(122, 122, 122);

    public FDButton(String text) {
        super(text);
        setBorderPainted(false);
        setFocusPainted(false);
        setBackground(backgroundColor);
        setForeground(textColor);
        setHoverBorderColor(hoverBorderColor);

        setFont(new Font("Segoe UI", Font.PLAIN, 15));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                setBorderPainted(true);
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                setBorderPainted(false);
            }
        });
    }

    public void setHoverBorderColor(Color borderColor) {
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(borderColor, 2),
                BorderFactory.createEmptyBorder(5, 30, 5, 30)));
    }

    public void setBackgroundColor(Color backgroundColor) {
        setBackground(backgroundColor);
    }

    public void setTextColor(Color textColor) {
        setForeground(textColor);
    }

    public void setOnClickListener(OnClickListener onClickListener){
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                onClickListener.onClick(actionEvent);
            }
        });
    }

}
