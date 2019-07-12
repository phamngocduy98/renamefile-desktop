package FluentDesign.Button;


import java.awt.event.ActionEvent;

/**
 * This class implement Android button onClick Style
 * @author Phạm Ngọc Duy
 */
@FunctionalInterface
public interface OnClickListener {
    void onClick(ActionEvent actionEvent);
}