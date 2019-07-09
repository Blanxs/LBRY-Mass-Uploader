/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massuploader;

/**
 *
 * @author John Blanton
 */

import com.jtattoo.plaf.smart.SmartLookAndFeel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Properties;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class AwsSignIn {
     public static  JFrame SignInFrame=null;

     
    public static String ProgramFilePath=System.getProperty("user.dir"); 

   public static EscFrameListener JbtEscFrame=null;
   public static SignInListener JbtRunSignInProgram=null;
   public static SignInMenuItemListener JbtSignInMenuItem=null;
   
   public static JButton jbtEsc=null;
   public static JButton jbtSignIn=null;
   public static JButton jbtSignUp=null;
   
   public static Dimension screenSize =null;   
   public static int screenRes; 
    
   public static GridLayout experimentLayout=null;
   public static Image StartUpImage=null;
    
   public static JMenuItem SignInItem=null;
   
   public static JPanel SignInPanel=null;
   
 public static JLabel UserNameLabel=null;
 public static JLabel PasswordLabel=null;
 public static JLabel SignUpLabel=new JLabel("If you dont have an account,click here to sign up.");
 public static JTextField jtfUserName=null;
 public static JTextField jtfPassword=null;
   
 
public AwsSignIn(){
setTheLookAndFeel();

   JbtEscFrame =new EscFrameListener();
   JbtRunSignInProgram=new SignInListener();
   JbtSignInMenuItem=new SignInMenuItemListener();
   
   jbtSignIn= new JButton("Sign In.");
   jbtSignUp= new JButton("Sign Up.");
   jbtEsc= new JButton("Esc");
   SignInItem=new JMenuItem("Sign In");
   SignInItem.addActionListener(JbtSignInMenuItem);
   SignUpLabel=new JLabel("If you dont have an account,click here to sign up.");
   
   jbtSignIn.addActionListener(JbtRunSignInProgram);
   jbtSignUp.addActionListener(AwsSignUp.JbtSignUpMenuItem);
   jbtEsc.addActionListener(JbtEscFrame);
   screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
}

public static void setTheLookAndFeel(){
        try {
            // setup the look and feel properties
            Properties props = new Properties();
            
            props.put("logoString", "my company"); 
            props.put("licenseKey", "INSERT YOUR LICENSE KEY HERE");
            
            props.put("selectionBackgroundColor", "180 240 197"); 
            props.put("menuSelectionBackgroundColor", "180 240 197"); 
            
            props.put("controlColor", "218 254 230");
            props.put("controlColorLight", "218 254 230");
            props.put("controlColorDark", "180 240 197"); 

            props.put("buttonColor", "218 230 254");
            props.put("buttonColorLight", "255 255 255");
            props.put("buttonColorDark", "244 242 232");

            props.put("rolloverColor", "218 254 230"); 
            props.put("rolloverColorLight", "218 254 230"); 
            props.put("rolloverColorDark", "180 240 197"); 

            props.put("windowTitleForegroundColor", "0 0 0");
            props.put("windowTitleBackgroundColor", "180 240 197"); 
            props.put("windowTitleColorLight", "218 254 230"); 
            props.put("windowTitleColorDark", "180 240 197"); 
            props.put("windowBorderColor", "218 254 230");
            
            // set your theme
            SmartLookAndFeel.setCurrentTheme(props);
            //HiFiLookAndFeel.setCurrentTheme(props);
            // select the Look and Feel
            UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel"); 
            //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }   
}  

public class EscFrameListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
   SignInFrame.setVisible(false);
    }}

public class SignInListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){  
//    String result = CognitoHelper.ValidateUser(jtfUserName.getText(), jtfPassword.getText());
//    CognitoHelper.TEST(result);
    if(CognitoHelper.SignInUser(jtfUserName.getText(), jtfPassword.getText())){
      SignInFrame.setVisible(false);  
    }
  //CognitoHelper.SignUpUser(jtfUserName.getText(), jtfPassword.getText(), jtfEmail.getText(), jtfNumber.getText());
    }}

public class SignInMenuItemListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    experimentLayout = new GridLayout(0,2);  
    File srcFolder=new File(ProgramFilePath+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
      JbtEscFrame =new EscFrameListener();
   JbtRunSignInProgram=new SignInListener();
   jbtSignIn= new JButton("Sign In");
   jbtSignUp= new JButton("Sign Up");
   jbtEsc= new JButton("Esc");
   SignInItem=new JMenuItem("Sign In");
   SignInItem.addActionListener(JbtSignInMenuItem);
   jbtSignIn.addActionListener(JbtRunSignInProgram);
   jbtSignUp.addActionListener(AwsSignUp.JbtSignUpMenuItem);
   jbtEsc.addActionListener(JbtEscFrame);
   screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
    SignInFrame = new JFrame("Sign In");
    SignInFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    SignInFrame.setSize((int)(screenSize.width*.35), (int)(screenSize.height*.25));
    SignInFrame.setResizable(false);
    SignInFrame.setLocationRelativeTo(null);
    SignInFrame.setIconImage(StartUpImage);
    SignInPanel=new JPanel();
    SignInPanel.setLayout(experimentLayout);
      
    jtfUserName=new JTextField("",50);
    jtfPassword=new JTextField("",50);  
   
    UserNameLabel=new JLabel("Username");
    PasswordLabel=new JLabel("Password");
    SignUpLabel=new JLabel("If you dont have an account,click here to sign up.");
    
    SignInPanel.add(SignUpLabel);
    SignInPanel.add(jbtSignUp);
    SignInPanel.add(UserNameLabel);
    SignInPanel.add(jtfUserName);
    SignInPanel.add(PasswordLabel);
    SignInPanel.add(jtfPassword);
    SignInPanel.add(jbtSignIn);
    SignInPanel.add(jbtEsc);
    
    SignInFrame.add(SignInPanel);

    SignInFrame.setVisible(true);

    }}   
}
