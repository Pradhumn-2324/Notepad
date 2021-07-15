/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepadeditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author ASUS
 */
public class NotepadEditor implements ActionListener
{
    JFrame jf;
    JMenuBar jm;
    JMenuItem neww,open,save;
    JTextArea ta;
    File f;
    public NotepadEditor() 
    {
        try
        {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e1)
        {
        e1.printStackTrace();
        }
        JFrame jf=new JFrame("PRADHUMN'S NOTEPAD");
        jf.setSize(700,600);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
       
        jm=new JMenuBar();
        
        JMenu file=new JMenu("File");
        
        
        neww=new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);
       
        open=new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        
        save=new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        
        jm.add(file);
        
        JMenu edit=new JMenu("Edit");
        jm.add(edit);
        
        jf.setJMenuBar(jm);

        ta=new JTextArea();
        
        JScrollPane sp=new JScrollPane(ta);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jf.add(sp);
        
        jf.setVisible(true);
    }
   
    public static void main(String[] args) 
    {
    new NotepadEditor();
    
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
    String s=e.getActionCommand();
    
        if(e.getSource()==neww)
        {
          ta.setText("");
        }
         if(e.getSource()==open)
        {
          
          JFileChooser fc=new JFileChooser();
          int result=fc.showOpenDialog(jf);
          if(result==0)
          {
          ta.setText("");
          f=fc.getSelectedFile();
          jf.setTitle(f.getName());
          try(FileInputStream fis=new FileInputStream(f))
          {
          int i;
          while((i=fis.read())!=-1)
          {
          ta.append(String.valueOf((char)i));
          }
          }
          catch(IOException ee)
          {
          ee.printStackTrace();
          }
        }
        }
         if(e.getSource()==save)
         {
         
          JFileChooser fc=new JFileChooser();
          
          
          int result=fc.showSaveDialog(jf);
          if(result==JFileChooser.APPROVE_OPTION)
          {
          String text=ta.getText();
          f=(fc.getSelectedFile());
          jf.setTitle(f.getName());
          try(FileOutputStream fos=new FileOutputStream(f))
          {
          byte[] b=text.getBytes();
          fos.write(b);
          }
          catch(IOException e2)
          {
          e2.printStackTrace();
          }
           
         }
         }
       
    }
}