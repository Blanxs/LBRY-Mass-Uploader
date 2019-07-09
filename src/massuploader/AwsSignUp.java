/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package massuploader;

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

/**
 *
 * @author John Blanton
 */
public class AwsSignUp {
   public static JFrame SignUpFrame=null;
   public static JFrame ConfirmationCodeFrame=null;
     
   public static String ProgramFilePath=System.getProperty("user.dir"); 

   public static EscFrameListener JbtEscFrame=null;
   public static SignUpListener JbtRunSignUpProgram=null;
   public static SignUpMenuItemListener JbtSignUpMenuItem=null;
   public static EscConfirmationFrameListener JbtEscConfirmationFrame=null;
   public static SubmitConfirmationCodeListener JbtSubmitConfirmationCode=null;
   
   public static JButton jbtEsc=null;
   public static JButton jbtSignUp=null;
   public static JButton jbtSubmitConfirmationCode=null;
   public static JButton jbtEscConfirmationCodeFrame=null;
   
   public static Dimension screenSize =null;   
   public static int screenRes; 
    
   public static GridLayout experimentLayout=null;
   public static Image StartUpImage=null;
    
   public static JMenuItem SignUpItem=null;
  
   public static JPanel SignUpPanel=null;
   public static JPanel ConfirmationCodePanel=null;
   
 public static JLabel UserNameLabel=null;
 public static JLabel PasswordLabel=null;
 public static JLabel ConfirmationCodeLabel=null;
 public static JLabel EmailLabel=null;
 public static JLabel NumberLabel=null;
 public static JTextField jtfUserName=null;
 public static JTextField jtfConfirmationCode=null;
 public static JTextField jtfPassword=null;
 public static JTextField jtfEmail=null;
 public static JTextField jtfNumber=null;   
 
public AwsSignUp(){
setTheLookAndFeel();

   JbtEscFrame =new EscFrameListener();
   JbtRunSignUpProgram=new SignUpListener();
   JbtSignUpMenuItem=new SignUpMenuItemListener();
   JbtEscConfirmationFrame=new EscConfirmationFrameListener();
   JbtSubmitConfirmationCode=new SubmitConfirmationCodeListener();
   
   jbtSignUp= new JButton("Sign Up.");
   jbtEsc= new JButton("Esc");
   jbtSubmitConfirmationCode=new JButton("Submit Confirmation Code");
   jbtEscConfirmationCodeFrame=new JButton("Esc");
   SignUpItem=new JMenuItem("Sign Up");
   SignUpItem.addActionListener(JbtSignUpMenuItem);
    
   jbtSubmitConfirmationCode.addActionListener(JbtSubmitConfirmationCode);
   jbtEscConfirmationCodeFrame.addActionListener(JbtEscConfirmationFrame);
  
   jbtSignUp.addActionListener(JbtRunSignUpProgram);
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
   SignUpFrame.setVisible(false);
    }}

public class EscConfirmationFrameListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
   ConfirmationCodeFrame.setVisible(false);
    }}

public class SubmitConfirmationCodeListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
        CognitoHelper.VerifyAccessCode(CognitoHelper.USER_NAME, jtfConfirmationCode.getText());
   ConfirmationCodeFrame.setVisible(false);
    }}

public static void TestPasswordWithRequirements(String Password){
 
}

public class SignUpListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){
    jtfConfirmationCode=new JTextField("",50);
    ConfirmationCodeLabel=new JLabel("Enter Confirmation Code.");
    ConfirmationCodePanel=new JPanel();
    jbtSubmitConfirmationCode=new JButton("Submit Confirmation Code");
    jbtEscConfirmationCodeFrame=new JButton("Esc");
    JbtEscConfirmationFrame=new EscConfirmationFrameListener();
    JbtSubmitConfirmationCode=new SubmitConfirmationCodeListener();
    jbtSubmitConfirmationCode.addActionListener(JbtSubmitConfirmationCode);
    jbtEscConfirmationCodeFrame.addActionListener(JbtEscConfirmationFrame);  
    experimentLayout = new GridLayout(0,2);  
    File srcFolder=new File(ProgramFilePath+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
    screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
    screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
    ConfirmationCodeFrame = new JFrame("Enter Confirmation Code");
    ConfirmationCodeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    ConfirmationCodeFrame.setSize((int)(screenSize.width*.35), (int)(screenSize.height*.15));
    ConfirmationCodeFrame.setResizable(false);
    ConfirmationCodeFrame.setLocationRelativeTo(null);
    ConfirmationCodeFrame.setIconImage(StartUpImage);
    ConfirmationCodePanel.add(ConfirmationCodeLabel);
    ConfirmationCodePanel.add(jtfConfirmationCode);
    ConfirmationCodePanel.add(jbtSubmitConfirmationCode);
    ConfirmationCodePanel.add(jbtEscConfirmationCodeFrame);
    ConfirmationCodeFrame.add(ConfirmationCodePanel);
    ConfirmationCodeFrame.setVisible(true);
    SignUpFrame.setVisible(false);
    CognitoHelper.USER_NAME=jtfUserName.getText();
  CognitoHelper.SignUpUser(jtfUserName.getText(), jtfPassword.getText(), jtfEmail.getText(), "+"+jtfNumber.getText());
    }}

public class SignUpMenuItemListener  implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e){

    experimentLayout = new GridLayout(0,2);  
    File srcFolder=new File(ProgramFilePath+File.separator+"src");
    StartUpImage=(new ImageIcon(srcFolder.getPath()+"\\splash.png")).getImage();
      JbtEscFrame =new EscFrameListener();
   JbtRunSignUpProgram=new SignUpListener();
   jbtSignUp= new JButton("Sign Up.");
   jbtEsc= new JButton("Esc");
   SignUpItem=new JMenuItem("Sign Up");
   SignUpItem.addActionListener(JbtSignUpMenuItem);
   jbtSignUp.addActionListener(JbtRunSignUpProgram);
   jbtEsc.addActionListener(JbtEscFrame);
   screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
   screenRes = Toolkit.getDefaultToolkit().getScreenResolution();
    SignUpFrame = new JFrame("Sign Up");
    SignUpFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    SignUpFrame.setSize((int)(screenSize.width*.35), (int)(screenSize.height*.25));
    SignUpFrame.setResizable(false);
    SignUpFrame.setLocationRelativeTo(null);
    SignUpFrame.setIconImage(StartUpImage);
    SignUpPanel=new JPanel();
    SignUpPanel.setLayout(experimentLayout);
      
    jtfUserName=new JTextField("",50);
    jtfPassword=new JTextField("",50);  
    jtfEmail=new JTextField("",50); 
    jtfNumber=new JTextField("",50); 
    
    UserNameLabel=new JLabel("Username. ");
    PasswordLabel=new JLabel("Password. ");
    PasswordLabel.setToolTipText("Be at least 6 characters, have an uppercase,a lowercase,a special character and a number.");
    EmailLabel=new JLabel("Email. ");
    NumberLabel=new JLabel("Phone Number. ");
    
    SignUpPanel.add(UserNameLabel);
    SignUpPanel.add(jtfUserName);
    SignUpPanel.add(PasswordLabel);
    SignUpPanel.add(jtfPassword);
    SignUpPanel.add(EmailLabel);
    SignUpPanel.add(jtfEmail);
    SignUpPanel.add(NumberLabel);
    SignUpPanel.add(jtfNumber);
    SignUpPanel.add(jbtSignUp);
    SignUpPanel.add(jbtEsc);
    
    SignUpFrame.add(SignUpPanel);
    AwsSignIn.SignInFrame.setVisible(false);
    SignUpFrame.setVisible(true);

    }}
}
