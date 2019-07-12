package Controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

import AppUtils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class MainController implements Initializable {

    private int value = 0;
    private String folderPath;
    private Scene scene;
    private ArrayList<String> fileNameStrings = new ArrayList<>(), replaceWhatList = new ArrayList<>(), replaceByList = new ArrayList<>();

    @FXML
    private Node main;

    @FXML
    private TextField inputFolderPath;

    @FXML
    private Button btnBrowse, btnStart, btnAddRule;

    @FXML
    private CheckBox cboxRemoveVNAlphabetSign, cboxReplaceSquareBracket, cboxReplaceSpace, cboxReplaceHyphen, cboxReplaceQuotation;

    @FXML
    private ListView listView;

    @FXML
    private VBox vboxRuleList;

    public void browseFolder() {
        if (folderPath == null || folderPath.equals("")) {
            System.out.println("Null Folder Path!");
            return;
        }
        File folder = new File(folderPath);
        if (folder == null || !folder.exists()) {
            System.out.println("Folder not exist");
            return;
        }
        fileNameStrings = FileUtils.listFilesForFolder(new File(folderPath), false);
        listView.getItems().addAll(fileNameStrings);
    }

    public void getSelected() {
        replaceWhatList.clear();
        replaceByList.clear();
        for (Node item : vboxRuleList.getChildren()) {
            if (item instanceof BorderPane) {
                Node center = ((BorderPane) item).getCenter();
                if (center instanceof GridPane) {
                    Node child = null;
                    String replaceWhat = "", replaceBy = "";
                    if ((child = ((GridPane) center).getChildren().get(0)) instanceof TextField) {
                        replaceWhat = ((TextField) child).getText();
                    }
                    if ((child = ((GridPane) center).getChildren().get(1)) instanceof TextField) {
                        replaceBy = ((TextField) child).getText();
                    }
                    if (replaceWhat.length() > 0 && replaceBy.length() > 0){
                        replaceWhatList.add(replaceWhat);
                        replaceByList.add(replaceBy);
                    }
                }
            }
        }
    }

    public void applyRule() {
        int fileNameStringLen = fileNameStrings.size();
        for (int i = 0; i < fileNameStringLen; i++) {
            String oldName = fileNameStrings.get(i), newName = oldName;
            if (cboxRemoveVNAlphabetSign.isSelected()) {
                try {
                    newName = FileUtils.normalizeCharacter(newName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (cboxReplaceSquareBracket.isSelected()) {
                replaceWhatList.addAll(Arrays.asList("[", "]"));
                replaceByList.addAll(Arrays.asList("(", ")"));
            }
            if (cboxReplaceSpace.isSelected()) {
                replaceWhatList.add(" ");
                replaceByList.add("_");
            }
            if (cboxReplaceHyphen.isSelected()) {
                replaceWhatList.addAll(Arrays.asList("-", "–"));
                replaceByList.addAll(Arrays.asList("_", "_"));
            }
            if (cboxReplaceQuotation.isSelected()) {
                replaceWhatList.addAll(Arrays.asList("'", "”", "“"));
                replaceByList.addAll(Arrays.asList("_", "_", "_"));
            }
            replaceWhatList.add("___");
            replaceByList.add("_");
            replaceWhatList.add("Đ");
            replaceByList.add("D");
            int len = replaceWhatList.size();
            for (int j = 0; j < len; j++) {
                newName = newName.replace(replaceWhatList.get(j), replaceByList.get(j));
            }
            if (FileUtils.rename(folderPath, oldName, newName)) {
                System.out.print(".");
            } else {
                System.out.print("x");
            }
            listView.getItems().set(i, newName);
        }
        try {
            browseFolder();
        } catch (Exception e){
            System.out.println(e);
        }
        System.out.println();
    }

    public BorderPane newRuleLayout() {
        BorderPane borderPane = new BorderPane();
        GridPane center = new GridPane();
        center.setHgap(5);
        // use parent to set margin its child
        BorderPane.setMargin(center, new Insets(0, 5, 0, 0));

        TextField inputReplaceWhat = new TextField();
        inputReplaceWhat.setPromptText("Thay thế kí tự");
        TextField inputReplaceBy = new TextField();
        inputReplaceBy.setPromptText("Bởi kí tự");
        center.add(inputReplaceWhat, 0, 0);
        center.add(inputReplaceBy, 1, 0);

        Button btnRemoveRule = new Button("Xóa");
        btnRemoveRule.setOnAction(event -> {
            int pos = vboxRuleList.getChildren().indexOf(borderPane);
            vboxRuleList.getChildren().remove(pos);
        });

        borderPane.setCenter(center);
        borderPane.setRight(btnRemoveRule);
        return borderPane;
    }

    public void messageWindow() throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Layout/dialog_layout.fxml"));
        Parent root = loader.load();
        Stage dialogWindow = new Stage();
        dialogWindow.setTitle("Dialog");
        dialogWindow.setScene(new Scene(root, 500, 150));

        // Set position of second window, related to primary window.
        dialogWindow.setX(main.getScene().getWindow().getX() + 200);
        dialogWindow.setY(main.getScene().getWindow().getY() + 100);

        dialogWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //<<Browse Button
        btnBrowse.setOnAction(event -> {
            DirectoryChooser folderChooser = new DirectoryChooser();
            folderChooser.setTitle("Open Resource File");
            File file = folderChooser.showDialog(btnBrowse.getScene().getWindow());
            if (file != null) {
                folderPath = file.getAbsolutePath();
                inputFolderPath.setText(folderPath);
                try {
                    browseFolder();
                } catch (Exception e){
                    System.out.println(e);
                }

            } else {
                try {
                    messageWindow();
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.out.println("ERROR: Folder not found");
            }
        });
        //Browse Button>>

        //<<Start button
        btnStart.setOnAction(event -> {
            getSelected();
            applyRule();
        });
        //Start button>>

        btnAddRule.setOnAction(event -> {
            vboxRuleList.getChildren().add(newRuleLayout());
        });

    }
}
