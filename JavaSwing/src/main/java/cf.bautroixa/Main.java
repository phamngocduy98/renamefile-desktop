import App.FileName;
import App.FileNameItemRender;
import App.FileUtils;
import FluentDesign.*;
import FluentDesign.Basic.FDCheckbox;
import FluentDesign.Basic.FDTextInput;
import FluentDesign.Basic.FDTextLabel;
import FluentDesign.Button.FDButton;
import FluentDesign.Button.FDPriButton;
import FluentDesign.Button.OnClickListener;
import FluentDesign.Frame.AppFrame;
import FluentDesign.Frame.FDDialog;
import FluentDesign.ListView.ListViewBuilder;
import FluentDesign.Panel.BorderPanel;
import FluentDesign.Panel.FDScrollPane;
import FluentDesign.Panel.GridPanel;
import FluentDesign.Panel.LinearPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;


class MainApp {
    //VIEW
    FDViewStore store = new FDViewStore();
    AppFrame appFrame;
    BorderPanel tab1, tab2, tab3;
    JList fileListView;

    //DATA
    String folderPath;
    ArrayList<FileName> fileNames = new ArrayList<>();
    ArrayList<String> replace = new ArrayList<>(), replaceWith = new ArrayList<>();
    boolean removeVietnameseAlphabetSign, replaceSquareBracket, replaceSpace, replaceHyphen, replaceQuotation;

    public MainApp() {
    }

    public BorderPanel tab1() {
        //TAB 1:
        BorderPanel tab1Panel = new BorderPanel();
        tab1Panel.setMargin(15, 15, 15, 15);

        //<<Choose file path
        FDTextInput folderLocationTextInput = new FDTextInput("Chọn đường dẫn...");
        FDButton folderChooseButton = new FDButton("Duyệt...");
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        folderChooseButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(ActionEvent actionEvent) {
                int res = jFileChooser.showOpenDialog(null);
                if (res == JFileChooser.APPROVE_OPTION) {
                    java.io.File file = jFileChooser.getSelectedFile();
                    String thisFolderPath = file.getAbsolutePath();
                    folderPath = thisFolderPath;
                    folderLocationTextInput.setText(thisFolderPath);
                    updateListView();
                } else {
                    new FDDialog.Builder().title("Thông báo")
                            .contentPanel(new BorderPanel.Builder().centerComponent(new FDTextLabel("Bạn chưa chọn file nào")).build())
                            .setOKOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(ActionEvent actionEvent) {
                                    return;
                                }
                            })
                            .setCancelOnClickListener(new OnClickListener() {
                                @Override
                                public void onClick(ActionEvent actionEvent) {
                                    return;
                                }
                            })
                            .build();
                }
            }
        });
        //Choose file path>>

        //<<Start button
        FDPriButton processButton = new FDPriButton("Bắt đầu");
        processButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(ActionEvent actionEvent) {
                getSelectedRule();
                applyRule();
            }
        });
        //Start button>>

        tab1Panel.setCenterComponent(folderLocationTextInput);
        tab1Panel.setRightComponent(folderChooseButton);
        tab1Panel.setBottomComponent(processButton);
        return tab1Panel;
    }

    public BorderPanel tab2() {
        BorderPanel tab2Panel = new BorderPanel();
        tab2Panel.setMargin(15, 15, 15, 15);

        //<<topPanel
        GridPanel optionsPanel = new GridPanel(0, 2, 5, 5);

        FDCheckbox removeVietnameseAlphabetSign = new FDCheckbox("Xóa dấu chữ cái tiếng việt");
        removeVietnameseAlphabetSign.setSelected(true);
        store.store("removeVietnameseAlphabetSign", removeVietnameseAlphabetSign);
        FDCheckbox replaceSquareBracket = new FDCheckbox("Đổi [] thành ()");
        replaceSquareBracket.setSelected(true);
        store.store("replaceSquareBracket", replaceSquareBracket);
        FDCheckbox replaceSpace = new FDCheckbox("Đổi dấu cách thành _");
        replaceSpace.setSelected(true);
        store.store("replaceSpace", replaceSpace);
        FDCheckbox replaceHyphen = new FDCheckbox("Đổi - thành _");
        replaceHyphen.setSelected(true);
        store.store("replaceHyphen", replaceHyphen);
        FDCheckbox replaceQuotation = new FDCheckbox("Đổi ' thành _");
        replaceQuotation.setSelected(true);
        store.store("replaceQuotation", replaceQuotation);


        optionsPanel.add(removeVietnameseAlphabetSign);
        optionsPanel.add(replaceSquareBracket);
        optionsPanel.add(replaceSpace);
        optionsPanel.add(replaceHyphen);
        optionsPanel.add(replaceQuotation);
        //topPanel>>

        //<<centerPanel
        LinearPanel rulePanel = new LinearPanel(BoxLayout.Y_AXIS);
        //centerPanel>>

        //<<centerPanel
        FDButton addRuleButton = new FDButton("Thêm luật");
        addRuleButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(ActionEvent actionEvent) {
                BorderPanel ruleItemPanel = new BorderPanel();
                ruleItemPanel.setMargin(5, 5, 5, 5);
                //<<Item
                GridPanel ruleItemSubPanel = new GridPanel(0, 2, 5, 5);
                ruleItemSubPanel.add(new FDTextInput("Thay thế kí tự..."));
                ruleItemSubPanel.add(new FDTextInput("...Bởi kí tự"));
                FDButton deleteButton = new FDButton("Xóa");
                deleteButton.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(ActionEvent actionEvent) {
                        int pos = rulePanel.findItemIndex(ruleItemPanel);
                        rulePanel.remove(pos);
                        rulePanel.notifyItemChanged();
                    }
                });

                ruleItemPanel.setCenterComponent(ruleItemSubPanel);
                ruleItemPanel.setRightComponent(deleteButton);
                //Item>>
                rulePanel.add(ruleItemPanel);
                rulePanel.notifyItemChanged();
            }
        });
        //BottomPanel>>

        tab2Panel.setTopComponent(optionsPanel);
        tab2Panel.setCenterComponent(rulePanel);
        tab2Panel.setBottomComponent(addRuleButton);
        return tab2Panel;
    }

    public void updateListView() {
        if (folderPath == null || folderPath.equals("")) {
            System.out.println("Null Folder Path!");
            return;
        }
        File folder = new File(folderPath);
        if (folder == null || !folder.exists()) {
            System.out.println("Folder not exist");
            return;
        }
        ArrayList<String> fileNameStrings = FileUtils.listFilesForFolder(folder, false);

        fileNames.clear();
        for (String fileNameString : fileNameStrings) {
            fileNames.add(new FileName(fileNameString));
        }
        // lazy solution, make new ListViewBuilder may increase cost
        fileListView.setModel(new ListViewBuilder<FileName>(fileNames).getModel());
    }

    public void initListView() {
        ArrayList<FileName> fileNames = new ArrayList<>();
        fileListView = new ListViewBuilder<FileName>(fileNames).setItemRender(new FileNameItemRender()).build();
    }

    public void getSelectedRule() {
        removeVietnameseAlphabetSign = ((JCheckBox)store.get("removeVietnameseAlphabetSign")).isSelected();
        replaceSquareBracket = ((JCheckBox)store.get("replaceSquareBracket")).isSelected();
        replaceSpace = ((JCheckBox)store.get("replaceSpace")).isSelected();
        replaceHyphen = ((JCheckBox)store.get("replaceHyphen")).isSelected();
        replaceQuotation = ((JCheckBox)store.get("replaceQuotation")).isSelected();

        replace.clear();
        replaceWith.clear();
        // get view this way is not recommend, too complex
        LinearPanel centerPanel = (LinearPanel) tab2.getCenterComponent();
        for (Component ruleItemPanel : centerPanel.getComponents()) {
            if (ruleItemPanel instanceof BorderPanel) {
                GridPanel ruleItemSubPanel = (GridPanel) ((BorderPanel) ruleItemPanel).getCenterComponent();
                String replaceString = ((FDTextInput) ruleItemSubPanel.getComponent(0)).getText();
                String replaceWithString = ((FDTextInput) ruleItemSubPanel.getComponent(1)).getText();
                replace.add(replaceString);
                replaceWith.add(replaceWithString);
            }
        }
    }

    public void applyRule() {
        for (FileName fileNameString : fileNames) {
            String newName = fileNameString.getOldName();
            if (removeVietnameseAlphabetSign) {
                try {
                    newName = FileUtils.normalizeCharacter(newName);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            if (replaceSquareBracket) {
                replace.addAll(Arrays.asList("[", "]"));
                replaceWith.addAll(Arrays.asList("(", ")"));
            }
            if (replaceSpace) {
                replace.add(" ");
                replaceWith.add("_");
            }
            if (replaceHyphen) {
                replace.addAll(Arrays.asList("-", "–"));
                replaceWith.addAll(Arrays.asList("_", "_"));
            }
            if (replaceQuotation) {
                replace.addAll(Arrays.asList("'", "”", "“"));
                replaceWith.addAll(Arrays.asList("_", "_", "_"));
            }
            replace.add("___");
            replaceWith.add("_");
            int len = replace.size();
            for (int i = 0; i < len; i++) {
                newName = newName.replace(replace.get(i), replaceWith.get(i));
            }
            fileNameString.setNewName(newName);
            if (FileUtils.rename(folderPath, fileNameString.getOldName(), newName)){
                System.out.println("Rename to "+newName+": Success");
            }

        }
        // lazy solution, make new ListViewBuilder may increase cost
        fileListView.setModel(new ListViewBuilder<FileName>(fileNames).getModel());
    }

    public FDTab tabView() {
        FDTab tabbedPane = new FDTab();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        tab1 = tab1();
        tab2 = tab2();

        tabbedPane.addTab("Trang chủ", tab1);
        tabbedPane.addTab("Cấu hình", tab2);
        return tabbedPane;
    }

    public JMenuBar menuBar() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("FileName");
        file.add(new JMenuItem("New"));
        file.add(new JMenuItem("Open"));
        file.add(new JMenuItem("Close"));

        JMenu edit = new JMenu("Edit");
        file.add(new JMenuItem("Replace"));

        menubar.add(file);
        menubar.add(edit);
        return menubar;
    }

    public AppFrame build() {
        initListView();
        java.net.URL appIconURL = ClassLoader.getSystemResource("cf/bautroixa/duy.png");
        appFrame = new AppFrame.Builder()
                .title("Rename Tool - Pham Ngoc Duy")
                .icon(Toolkit.getDefaultToolkit().createImage(appIconURL))
//                .menu(menuBar())
                .topPanel(tabView())
                .centerPanel(new FDScrollPane(fileListView))
                .exitOnClose()
                .build();
        return appFrame;
    }
}

public class Main {

    public static void main(String[] args) {
        System.out.println("App Starting...");
        FDLookAndFeel.applyLookAndFeel();

        MainApp mainApp = new MainApp();
        mainApp.build();
    }
}
