import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.*;

public class NotePad extends JFrame{
    private JFrame MainFrame = new JFrame("notepad");

    //工具栏组件
    private MenuBar menuBar = new MenuBar();
    private TextArea editArea = new TextArea(30,120);
    private boolean ifSave = false;
    Clipboard clipboard = new Clipboard("");

    //主菜单对象
    Menu file = new Menu("文件");
    //子菜单对象
    MenuItem newItem = new MenuItem("新建");
    MenuItem openItem = new MenuItem("打开");
    MenuItem saveItem = new MenuItem("保存");
    MenuItem saveAsItem = new MenuItem("另存为");
    //MenuItem pageSetItem = new MenuItem("页面设置");
    //MenuItem printItem = new MenuItem("打印");
    MenuItem exitItem = new MenuItem("退出");

    Menu edit = new Menu("编辑");
    MenuItem undoItem = new MenuItem("撤销");
    MenuItem cutItem = new MenuItem("剪切");
    MenuItem copyItem = new MenuItem("复制");
    MenuItem pasteItem = new MenuItem("粘贴");
    //MenuItem deleteItem = new MenuItem("删除");
    MenuItem lookItem = new MenuItem("查找");
    //MenuItem lookNextItem = new MenuItem("查找下一个");
    MenuItem replaceItem = new MenuItem("替换");
    //MenuItem toItem = new MenuItem("转到");
    MenuItem selectAllItem = new MenuItem("全选");

    Menu format = new Menu("格式");
    //MenuItem autoLineItem = new MenuItem("自动换行");
    MenuItem fontItem = new MenuItem("字体");

    //Menu check = new Menu("查看");
    //MenuItem statusItem = new MenuItem("状态栏");

    Menu help = new Menu("帮助");
    //MenuItem checkHelpItem = new MenuItem("查看帮助");
    MenuItem aboutItem = new MenuItem("关于记事本");




    public void init(){
        ActionListener menuFileListener = new ActionListener()
        {
            FileOpen fileOpen = new FileOpen();
            NotePad notePadJFrame = new NotePad();

//            public void about0(){
//                JDialog about = new JDialog();
//                about.setTitle("关于记事本");
//                about.setSize(100,100);
//                JLabel jl = new JLabel("练习之作");
//                about.add(jl);
//                about.setVisible(false);
//            }


            @Override
            public void actionPerformed(ActionEvent e)
            {
                //定义监听
                String cmd = e.getActionCommand();
                //退出监听
                if (cmd.equals("退出"))
                {
                    System.exit(0);
                }
                //新建文件监听
                else if (cmd.equals("新建"))
                {
                    if (ifSave == false){
                        editArea.setText("");
                    }
                }
                //打开文件监听
                else if (cmd.equals("打开"))
                {
                    fileOpen.openFile(notePadJFrame);
                    fileOpen.open();
                    editArea.setText(fileOpen.getText());
                }
                //保存文件监听
                else if (cmd.equals("保存"))
                {
                    fileOpen.save(getEditArea(),notePadJFrame);
                }
                //另存为文件监听
                else if (cmd.equals("另存为"))
                {
                    fileOpen.saveAs(getEditArea(),notePadJFrame);
                }
                //剪切文件监听
                else if (cmd.equals("")){
                    String text = editArea.getSelectedText();
                    StringSelection selection = new StringSelection(text);
                    clipboard.setContents(selection,null);
                    if (!"".equals(editArea.getText()))
                    {
                        editArea.setText("");
                        System.out.print("dagg");
                    }
                }
                //复制文件监听
                else if (cmd.equals("")){
                    String text = editArea.getSelectedText();
                    StringSelection selection = new StringSelection(text);
                    clipboard.setContents(selection,null);
                }
                //粘贴文件监听
                else if (cmd.equals("粘贴"))
                {
                    String text = null;
                    Transferable contents;
                    contents = clipboard.getContents(this);
                    editArea.replaceText(text,editArea.getSelectionStart(),editArea.getSelectionEnd());
                    StringSelection selection = new StringSelection("");
                    clipboard.setContents(selection,null);
                }
                //全选文件监听
                else if (cmd.equals("全选"))
                {
                    editArea.selectAll();
                }
                else if(cmd.equals("关于记事本"))
                {

                }

            }


        };

        //文件添加组件
        exitItem.addActionListener(menuFileListener);
        newItem.addActionListener(menuFileListener);
        openItem.addActionListener(menuFileListener);
        saveAsItem.addActionListener(menuFileListener);
        saveItem.addActionListener(menuFileListener);
        file.add(newItem);
        file.add(openItem);
        file.add(saveItem);
        file.add(saveAsItem);
        file.add(new MenuItem("-"));
        //file.add(pageSetItem);
        //file.add(printItem);
        file.add(new MenuItem("-"));
        file.add(exitItem);

        //编辑添加组件
        cutItem.addActionListener(menuFileListener);
        copyItem.addActionListener(menuFileListener);
        pasteItem.addActionListener(menuFileListener);
        selectAllItem.addActionListener(menuFileListener);
        edit.add(undoItem);
        edit.add(new MenuItem("-"));
        edit.add(cutItem);
        edit.add(copyItem);
        edit.add(pasteItem);
        //edit.add(deleteItem);
        edit.add(new MenuItem("-"));
        edit.add(lookItem);
        //edit.add(lookNextItem);
        edit.add(replaceItem);
        //edit.add(toItem);
        edit.add(new MenuItem("-"));
        edit.add(selectAllItem);

        //格式添加组件
        //format.add(autoLineItem);
        format.add(new MenuItem("-"));
        format.add(fontItem);

        //查看添加组件
        //check.add(statusItem);

        //帮助添加组件
        //help.add(checkHelpItem);
        help.add(new MenuItem("-"));
        help.add(aboutItem);

        //工具条添加组件
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(help);
        //menuBar.add(check);


        //工具条添加到主界面
        MainFrame.setMenuBar(menuBar);

        MainFrame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        });
        MainFrame.add(editArea);
        MainFrame.pack();
        MainFrame.setVisible(true);
    }


    public JFrame getMainFrame() {
        return MainFrame;
    }

    public void setMainFrame(JFrame mainFrame) {
        MainFrame = mainFrame;
    }

    public TextArea getEditArea() {
        return editArea;
    }

    public void setEditArea(TextArea editArea) {
        this.editArea = editArea;
    }

    public static void main(String[] args) {
        new NotePad().init();
    }

}
