using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Swipe.AppModel
{
    public class FileName
    {
        public FileName()
        {
        }

        public FileName(int index, string oldName, string newName)
        {
            Index = index;
            OldName = oldName;
            NewName = newName;
        }

        public int Index { get; set; }
        public string OldName { get; set; }
        public string NewName { get; set; }
    }
    class FileUtils
    {
        public static string[] GetFileNames(string[] files)
        {
            string[] _files = files;
            for (int i = 0; i < files.Length; i++)
            {
                _files[i] = Path.GetFileName(files[i]);
            }
            return _files;
        }
        public static string normalizeCharacter(string value)
        {
            return string.Concat(value.Normalize(NormalizationForm.FormD).Where( c => CharUnicodeInfo.GetUnicodeCategory(c) != UnicodeCategory.NonSpacingMark));
            //return value.Normalize(NormalizationForm.FormD);
        }

        public static bool rename(string folderPath, string oldName, string newName)
        {
            File.Move(folderPath + "\\" + oldName, folderPath + "\\" + newName);
            return false;
        }
    }
}
