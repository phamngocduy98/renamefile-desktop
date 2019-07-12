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
        List<string> replaceWhat = new List<string>();
        List<string> replaceBy = new List<string>();
        public MainWindow()
        {
            data = new AppModel.DataStore();
            InitializeComponent();
        }

        public void applyRule(bool previewOnly)
        {
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
                }
                if (cboxBracket.IsChecked == true)
                {
                    replaceWhat.AddRange(new List<string> { "[" ,"]" });
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
                replaceWhat.AddRange(new List<string> { "___", "đ", "Đ" });
                replaceBy.AddRange(new List<string> { "_", "d", "D" });
                int len = replaceWhat.Count;
                for (int i = 0; i < len; i++)
                {
                    newName = newName.Replace(replaceWhat[i], replaceBy[i]);
                }
                fileNameString.NewName = newName;
                if (!previewOnly && FileUtils.rename(data.folderPath, fileNameString.OldName, newName))
                {
                    //System.Console.WriteLine("Rename to " + newName + ": Success");
                }
            }
            //refresh listview
            listView.Items.Refresh();
        }

        private void browse_folder(string folderPath)
        {
            string[] files = Directory.GetFiles(folderPath);
            if (files.Length == 0)
            {
                System.Windows.Forms.MessageBox.Show("This folder is empty", "Warning");
                return;
            }
            files = FileUtils.GetFileNames(files);
            inputFolder.Text = folderPath;
            data.SetFileList(files);
            data.folderPath = folderPath;
            listView.ItemsSource = data.getFileList();
            listView.Items.Refresh();
            //System.Console.WriteLine(data.getFileList().Count);
        }

        private void BtnBrowse_Click(object sender, RoutedEventArgs e)
        {
            var fbd = new System.Windows.Forms.FolderBrowserDialog();

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
            browse_folder(data.folderPath);
            applyRule(true);
        }

        private void BtnStart_Click(object sender, RoutedEventArgs e)
        {
            browse_folder(data.folderPath);
            applyRule(false);
        }

        private void BtnAddRule_Click(object sender, RoutedEventArgs e)
        {
            TextBox txtReplaceWhat = new TextBox() { Text = "Thay thế kí tự..." };
            TextBox txtReplaceBy = new TextBox() { Text = "...bởi kí tự"  };
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
                Grid.SetRow(gridRule.Children[i], i/3);
            }
            int row = index / 3;
            gridRule.RowDefinitions.RemoveAt(row);
        }
    }
}
