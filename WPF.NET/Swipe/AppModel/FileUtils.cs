using System;
using System.Collections.Generic;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Xml;
using System.Xml.Serialization;

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
        public static void SerializeObject<T>(T serializableObject, string fileName)
        {
            if (serializableObject == null) { return; }

            try
            {
                XmlDocument xmlDocument = new XmlDocument();
                XmlSerializer serializer = new XmlSerializer(serializableObject.GetType());
                using (MemoryStream stream = new MemoryStream())
                {
                    serializer.Serialize(stream, serializableObject);
                    stream.Position = 0;
                    xmlDocument.Load(stream);
                    xmlDocument.Save(fileName);
                }
            }
            catch (Exception ex)
            {
                //    //Log exception here
            }
        }
        public static T DeSerializeObject<T>(string fileName)
        {
            if (string.IsNullOrEmpty(fileName)) { return default(T); }

            T objectOut = default(T);

            try
            {
                XmlDocument xmlDocument = new XmlDocument();
                xmlDocument.Load(fileName);
                string xmlString = xmlDocument.OuterXml;

                using (StringReader read = new StringReader(xmlString))
                {
                    Type outType = typeof(T);

                    XmlSerializer serializer = new XmlSerializer(outType);
                    using (XmlReader reader = new XmlTextReader(read))
                    {
                        objectOut = (T)serializer.Deserialize(reader);
                    }
                }
            }
            catch (Exception ex)
            {
                //Log exception here
            }

            return objectOut;
        }
    }
}
