package controlledAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mainApp extends sharedFunctionsAndVariables{ //by extending the imageloader class, its variables can be accessed
    
    
    
    //This is important as the enter button could be used either for logging in or signing up
    //The two functions are very different
    
    BufferedImage image;//this stores an image which will be loaded later
    
    
    public static void startApp() { // as this is the method of the mainApp class, it is run when the object of it is created
    	f = new JFrame();
    	//loginJPanel.loginJPanel = new JPanel();
    	
    	//f.setLayout(new BorderLayout());
    	f.setTitle("Maths Simualtor 3000");//title of our JFrame set, which appears at the top of the windows
        f.setResizable(false);//resizing the window will cause graphical issues
        f.setSize(appWidth, appHeight);//the sizes of the window is set
        f.setVisible(true);//Our JFrame will not be invisible
        
        //as it can use a paintComponent method which enabled easy updating of graphics
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//When the window closes, the program no longer runs.
        
        
        
        //Adding a JPanel will enable graphical display on our program
        loginJPanel.addloginJPanel();
        menuJPanel.addComponents();
        gameConfiguration.createComponents();
        playGame.createComponents();
        
    }

    
    

    public static void main(String[] args) { // this is the part of the program run when started
    	System.out.println("start");
    	startApp(); //create new object of the main app class
    }
    
    
}