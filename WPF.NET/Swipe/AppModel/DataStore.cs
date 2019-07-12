using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Swipe.AppModel
{
    class DataStore
    {
        public string folderPath { get; set; }
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
}
