import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;

public class FileOpen {
    private FileDialog filedialog_open;
    private String fileopen = null;
    private String filename = null;             //存放打开地址，文件名
    private File file1;                         //字节流对象
    private FileReader file_reader;             //字符流对象
    private FileWriter file_writer;
    private BufferedReader in;                  //文件行读取
    private StringBuffer text = new StringBuffer();
    NotePad haffman = null;

    public void openFile(NotePad hf)
    {
        haffman = hf;
        filedialog_open = new FileDialog(haffman.getMainFrame(),"打开文件对话框",FileDialog.LOAD);
        //为文件对话框添加监听
        filedialog_open.addWindowListener(new WindowListener()
        {
            @Override
            public void windowOpened(WindowEvent e) {
            }

            @Override
            public void windowClosing(WindowEvent e) {
                filedialog_open.setVisible(false);
            }

            @Override
            public void windowClosed(WindowEvent e) {
            }

            @Override
            public void windowIconified(WindowEvent e) {
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
            }

            @Override
            public void windowActivated(WindowEvent e) {
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public void open()
    {
        String s = "";
        filedialog_open.setVisible(true);
        fileopen = filedialog_open.getDirectory();
        //返回文件对话框中显示的文件所属的目录
        filename = filedialog_open.getFile();
        /*
        返回当前文件对话框显示的文件名的字符串表示
        如果不存在就返回NULL 判断打开的文件是否存在
         */
        if(filename != null)
        {
            try{
                file1 = new File(fileopen,filename);
                file_reader = new FileReader(file1);
                in = new BufferedReader(file_reader);
                while ((s = in.readLine()) != null)
                    text.append(s +"\n");
                in.close();
                file_reader.close();
            }
            catch (IOException e2)
            {
                System.out.println("无法打开文件！");
            }
        }
    }

    public void save(TextArea textArea,NotePad hf)
    {
        char[] charbuf = textArea.getText().toCharArray();
        try
        {
            if ((fileopen != null)  && (filename != null))
            {
                file1 = new File(fileopen,filename);
                file_writer = new FileWriter(file1);
                file_writer.write(charbuf);
                file_writer.close();
                System.exit(0);
            }
            else {
                saveAs(textArea, hf);
            }
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    public void saveAs(TextArea textArea,NotePad hf){
        try{
            haffman = hf;
            char[] charbuf = textArea.getText().toCharArray();
            FileDialog f = new FileDialog(haffman.getMainFrame(),"另存为",FileDialog.LOAD);
            f.setVisible(true);
            filename = f.getDirectory() + f.getFile();
            file_writer = new FileWriter(filename + ".txt");
            file_writer.write(charbuf);
            file_writer.close();
            System.exit(0);
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }
    }

    public String getText() {
        return getText();
    }
}
