   M S B A M L   `   `   `  ���� \  XRename Tool (WPF) - Pham Ngoc Duy, Version=1.0.0.0, Culture=neutral, PublicKeyToken=nullclr-namespace:SwipeSwipe      Swipe.MainWindow   \ XPresentationFramework, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35R NWindowsBase, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35W SPresentationCore, Version=4.0.0.0, Culture=neutral, PublicKeyToken=31bf3856ad364e35D 9http://schemas.microsoft.com/winfx/2006/xaml/presentation    R NSystem.Xaml, Version=4.0.0.0, Culture=neutral, PublicKeyToken=b77a5c561934e089:x,http://schemas.microsoft.com/winfx/2006/xaml     @d2http://schemas.microsoft.com/expression/blend/2008     Jmc;http://schemas.openxmlformats.org/markup-compatibility/2006     %localclr-namespace:Swipe       � Title$+  %Rename Tool v19.09.16 - Pham Ngoc Duy��$	��500��$	��500��.���� -    ��Name$ 
tabControl��.@��� $��Trang chủ��.��]� .+�� $	��Top=� � 	Resources  �� 
TargetType �� Property �� Value��%_   '��       '�� )     �� " ��.���  � ��$ 5,5,10,5q��� " ��.���  � ��$ 5q� � ColumnDefinitions �� $	��14* ��� $	��53* ��� $
��Auto � � RowDefinitions �� �� .+��� -    ��Name$ 	btnBrowse��	 �� 	IsDefault	 . $��Duyệt��$��75��$��2��$��4.6,5,5.2,4.8q��� -   
 ��Name$
 inputFolder��$ ��Chọn một thư mục...��$��0��$��5,5,84.2,4.8q�$��3���� -   $ 
btnPreview��$��Xem trước��$��1��$��0��$��2��$��5,5.2,5.4,4.6q��� -   $ btnStart��$��Đổi tên file��$��1��$��2��$��4.6,5.2,5.2,4.6q��� -    ��Name$ listView��$��Bottom=� �� View �� .P��� $	��STT�� �� Width$ 30�� �� DisplayMemberBinding �� *Index+�� $��Tên hiện tại��$	 220�� �� *	OldName+�� $��
Tên mới��$	 220�� �� *	NewName+�� $��Cấu hình��.��]� ��. .+�� $	��Top=�$��5q� �� $��* ��� $��* � �� $��* ��� $��* ��� $��* ��� $��* ��� $��* � ��%3   '��       �� " ��.���  � ��$ 5,5,10,5q�.+��� -    ��Name$ cboxRemoveVNCSign��$��Bỏ dấu tiếng việt�� d� 	IsChecked$
 TrueY��� -   $ cboxBracket��$��Thay [] bằng ()��$
 TrueY�$��0��$��1���� -	   $ 	cboxSpace��$��Thay space bằng _��$
 TrueY�$��1��$��0���� -
   $ 
cboxHyphen��$��Thay - bằng _��$
 TrueY�$��1��$��1���� -   $ cboxQuotation��$��Thay ' bằng _��$
 TrueY�$��2��$��0���� -   $ cboxSpecialCharacter��$��Xóa kí hiệu @ Ⓡ��$
 TrueY�$��2��$��1���� -   $ cboxRemoveDuplicateSpace��$%��Xóa các dấu __ trùng lặp��$
 TrueY�$��3��$��0���� -   $ cboxRemoveBeginSpace��$��Xóa __ ở đầu tên��$
 TrueY�$��3��$��1���� -   $ btnLoadRole��$��	Load Rule��$��4��$��0���� -   $ btnSaveRole��$��	Save Rule��$��4��$��1���� -   $ 
btnAddRule��$��Thêm luật thay thế��$��Bottom=��� $��Bottom=�.��� -    �Name$ gridRule��$��10,0,0,0q� ��%d   '��       '�� (     �� " ��.���  � ��$ 0,3,5,3q��� " ��.���  � ��$ 0,3,5,3q� �� $��* ��� $��* ��� $
��Auto �