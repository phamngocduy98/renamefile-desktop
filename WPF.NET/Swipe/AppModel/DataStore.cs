using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml.Serialization;

namespace Swipe.AppModel
{
    class DataStore
    {
        private List<FileName> files = new List<FileName>();
        public void SetFileList(string[] files)
        {
            this.files.Clear();
            int index = 0;
            foreach (string file in files)
            {
                index++;
                this.files.Add(new FileName() { Index = index, OldName = file, NewName = "" });
            }
        }
        public List<FileName> getFileList()
        {
            return this.files;
        }


    }
    [Serializable]
    [XmlRoot(ElementName = "Config")]
    public class ConfigStore
    {
        [XmlElement("FolderPath")]
        public string folderPath;
        [XmlArrayItem("ReplaceWhatItem")]
        public List<string> ReplaceWhat;
        [XmlArrayItem("ReplaceByItem")]
        public List<string> ReplaceBy;

        public ConfigStore()
        {
            ReplaceWhat = new List<string>();
            ReplaceBy = new List<string>();
        }

        public void SetCustomReplaceWhat(List<string> customReplaceWhat)
        {
            ReplaceWhat.Clear();
            ReplaceWhat.AddRange(customReplaceWhat);
        }
        public void SetCustomReplaceBy(List<string> customReplaceBy)
        {
            ReplaceBy.Clear();
            ReplaceBy.AddRange(customReplaceBy);
        }
    }
}
