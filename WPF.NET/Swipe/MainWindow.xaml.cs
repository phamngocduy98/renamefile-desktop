using System.Windows;
using System.Windows.Controls;
using System.IO;
using Swipe.AppModel;
using System.Collections.Generic;
using System.ComponentModel;
using System.Windows.Data;
using System.Collections.ObjectModel;

namespace Swipe
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        private AppModel.DataStore data;
        private AppModel.ConfigStore config;
        List<string> replaceWhat = new List<string>();
        List<string> replaceBy = new List<string>();
        private string CONFIG_INPUT_FILE_NAME = "Rule.RenameTool.conf";
        public MainWindow()
        {
            data = new AppModel.DataStore();
            config = new AppModel.ConfigStore();
            InitializeComponent();
            loadRule();
        }

        public void loadRule()
        {
            AppModel.ConfigStore newConfig = FileUtils.DeSerializeObject<AppModel.ConfigStore>(CONFIG_INPUT_FILE_NAME);
            if (newConfig != null)
            {
                config = newConfig;
                gridRule.Children.Clear();
                int ruleLen = config.ReplaceWhat.Count;
                for (int i = 0; i < ruleLen; i++)
                {
                    addRule(config.ReplaceWhat[i], config.ReplaceBy[i]);
                }
            }
        }

        public void saveRule()
        {
            List<string> customReplaceWhat = new List<string>();
            List<string> customReplaceBy = new List<string>();
            int rowCount = gridRule.Children.Count / 3;
            for (int i = 0; i < rowCount; i++)
            {
                string rpWhat = ((TextBox)gridRule.Children[3 * i]).Text;
                customReplaceWhat.Add(rpWhat);
                string rpBy = ((TextBox)gridRule.Children[3 * i + 1]).Text;
                customReplaceBy.Add(rpBy);
            }
            config.SetCustomReplaceWhat(customReplaceWhat);
            config.SetCustomReplaceBy(customReplaceBy);
            FileUtils.SerializeObject<ConfigStore>(config, CONFIG_INPUT_FILE_NAME);
        }

        public void addRule(string replaceWhatTxt, string replaceByTxt)
        {
            TextBox txtReplaceWhat = new TextBox() { Text = replaceWhatTxt };
            TextBox txtReplaceBy = new TextBox() { Text = replaceByTxt };
            Button btnRemoveRule = new Button() { Content = "Xóa" };
            btnRemoveRule.Click += BtnRemoveRule_Click;

            int row = gridRule.Children.Count / 3;
            //System.Console.WriteLine("row=" + row);
            gridRule.RowDefinitions.Add(new RowDefinition() { Height = GridLength.Auto });

            gridRule.Children.Add(txtReplaceWhat);
            Grid.SetColumn(txtReplaceWhat, 0);
            Grid.SetRow(txtReplaceWhat, row);

            gridRule.Children.Add(txtReplaceBy);
            Grid.SetColumn(txtReplaceBy, 1);
            Grid.SetRow(txtReplaceBy, row);

            gridRule.Children.Add(btnRemoveRule);
            Grid.SetColumn(btnRemoveRule, 2);
            Grid.SetRow(btnRemoveRule, row);
        }

        public void applyRule(bool previewOnly)
        {
            saveRule();
            replaceWhat.Clear();
            replaceBy.Clear();
            int rowCount = gridRule.Children.Count / 3;
            for (int i = 0; i < rowCount; i++)
            {
                string rpWhat = ((TextBox)gridRule.Children[3 * i]).Text;
                replaceWhat.Add(rpWhat);
                string rpBy = ((TextBox)gridRule.Children[3 * i + 1]).Text;
                replaceBy.Add(rpBy);
                //System.Console.WriteLine("Replace " + rpWhat + " by " + rpBy);
            }
            List<FileName> files = data.getFileList();
            foreach (FileName fileNameString in files)
            {
                string newName = fileNameString.OldName;
                if (cboxRemoveVNCSign.IsChecked == true)
                {
                    newName = FileUtils.normalizeCharacter(newName);
                    replaceWhat.AddRange(new List<string> { "đ", "Đ" });
                    replaceBy.AddRange(new List<string> { "d", "D" });
                }
                if (cboxBracket.IsChecked == true)
                {
                    replaceWhat.AddRange(new List<string> { "[", "]" });
                    replaceBy.AddRange(new List<string> { "(", ")" });
                }
                if (cboxSpace.IsChecked == true)
                {
                    replaceWhat.Add(" ");
                    replaceBy.Add("_");
                }
                if (cboxHyphen.IsChecked == true)
                {
                    replaceWhat.AddRange(new List<string> { "-", "–" });
                    replaceBy.AddRange(new List<string> { "_", "_" });
                }
                if (cboxQuotation.IsChecked == true)
                {
                    replaceWhat.AddRange(new List<string> { "'", "”", "“" });
                    replaceBy.AddRange(new List<string> { "_", "_", "_" });
                }
                if (cboxSpecialCharacter.IsChecked == true)
                {
                    replaceWhat.AddRange(new List<string> { "Ⓡ", "®", "@" });
                    replaceBy.AddRange(new List<string> { "", "", "" });
                }
                int len = replaceWhat.Count;
                for (int i = 0; i < len; i++)
                {
                    //System.Console.WriteLine("Rename " + newName + " by pattern" + replaceWhat[i] + " by" + replaceBy[i]);
                    newName = newName.Replace(replaceWhat[i], replaceBy[i]);
                    //System.Console.WriteLine("res=" + newName);
                }
                string finalName = newName;
                while (cboxRemoveDuplicateSpace.IsChecked == true)
                {
                    newName = newName.Replace("___", "_");
                    newName = newName.Replace("__", "_");
                    if (newName == finalName)
                    {
                        break;
                    }
                    else
                    {
                        finalName = newName;
                    }
                }
                if (cboxRemoveBeginSpace.IsChecked == true && newName[0] == '_')
                {
                    newName = newName.Remove(0, 1);
                }
                fileNameString.NewName = newName;
                if (!previewOnly && FileUtils.rename(config.folderPath, fileNameString.OldName, newName))
                {
                    //System.Console.WriteLine("Rename to " + newName + ": Success");
                }
            }
            //refresh listview
            listView.Items.Refresh();
            if (!previewOnly)
            {
                System.Windows.Forms.MessageBox.Show("Đã đổi tên thành công", "Cảnh báo nguy hiểm!!!");
            }
        }

        private void browse_folder(string folderPath)
        {
            string[] files = Directory.GetFiles(folderPath);
            if (files.Length == 0)
            {
                System.Windows.Forms.MessageBox.Show("Thư mục rỗng", "Cảnh báo");
                return;
            }
            files = FileUtils.GetFileNames(files);
            inputFolder.Text = folderPath;
            data.SetFileList(files);
            config.folderPath = folderPath;
            listView.ItemsSource = data.getFileList();
            listView.Items.Refresh();
            //System.Console.WriteLine(data.getFileList().Count);
        }

        private void BtnBrowse_Click(object sender, RoutedEventArgs e)
        {
            var fbd = new System.Windows.Forms.FolderBrowserDialog();
            if (config.folderPath != null && config.folderPath.Length > 0)
            {
                fbd.SelectedPath = config.folderPath;
            }

            System.Windows.Forms.DialogResult result = fbd.ShowDialog();
            string folderPath = fbd.SelectedPath;
            if (result.ToString() == "OK" && !string.IsNullOrWhiteSpace(folderPath))
            {
                browse_folder(folderPath);
            }
            //else
            //{
            //    System.Windows.Forms.MessageBox.Show("No folder selected", "Warning");
            //}
        }

        private void BtnPreview_Click(object sender, RoutedEventArgs e)
        {
            browse_folder(config.folderPath);
            applyRule(true);
        }

        private void BtnStart_Click(object sender, RoutedEventArgs e)
        {
            browse_folder(config.folderPath);
            applyRule(false);
        }

        private void BtnAddRule_Click(object sender, RoutedEventArgs e)
        {
            addRule("Thay thế kí tự ...", "... bởi kí tự");
        }

        private void BtnRemoveRule_Click(object sender, RoutedEventArgs e)
        {
            // each ROW contains 3 children

            int index = gridRule.Children.IndexOf((Button)sender);
            // remove all child of ROW
            for (int i = 0; i < 3; i++)
            {
                gridRule.Children.RemoveAt(index - 2);
            }
            // update row value of other row (prevent 2 row in the same position)
            int len = gridRule.Children.Count;
            for (int i = 0; i < len; i++)
            {
                Grid.SetRow(gridRule.Children[i], i / 3);
            }
            int row = index / 3;
            gridRule.RowDefinitions.RemoveAt(row);
        }
    }
}
