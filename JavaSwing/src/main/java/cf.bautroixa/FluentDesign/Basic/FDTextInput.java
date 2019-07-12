/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FluentDesign.Basic;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.BorderFactory;
import javax.swing.JTextField;

/**
 * This class implement TextInput
 * @author Phạm Ngọc Duy
 */
public class FDTextInput extends JTextField {

    private String defaultText;
    public FDTextInput() {
        init();
    }
    

    public FDTextInput(String text) {
        super(text);
        defaultText = text;
        init();
    }

    public FDTextInput(String text, int columns) {
        super(text, columns);
        defaultText = text;
        init();
    }

    public String getDefaultText(){
        return defaultText;
    }
    
    public void setBorderColor(Color color){
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(color, 2),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    }
    private void init() {
        setBorderColor(new Color(122, 122, 122));
        setFont(new Font("Segoe UI", Font.PLAIN, 16));
        addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent focusEvent) {
                setBorderColor(new Color(34, 91, 171));
                FDTextInput thisText = (FDTextInput)focusEvent.getComponent();
                if (thisText.getText().equals(thisText.getDefaultText())){
                    thisText.setText("");
                }
            }

            @Override
            public void focusLost(FocusEvent focusEvent) {
                setBorderColor(new Color(122, 122, 122));
                FDTextInput thisText = (FDTextInput)focusEvent.getComponent();
                if (thisText.getText().equals("")){
                    thisText.setText(thisText.getDefaultText());
                }
            }
        });
    }
}
