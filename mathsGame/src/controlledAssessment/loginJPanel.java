package controlledAssessment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class loginJPanel extends sharedFunctionsAndVariables{
	
	static String password; //the variable of password
    static boolean valid = true; // a boolean to check if username has already been used
    
    static boolean repeat = false;
    
    static final String FILENAME = "users.txt";
    static boolean regis = false; //boolean check for registering 
    static JLabel us= new JLabel("Username; "); 
    static JLabel pas= new JLabel("Password; ");
    static JButton jb = new JButton();
    static JTextField text= new JTextField();
    static JButton login = new JButton("Log in");
    static JPasswordField moreText= new JPasswordField();
    static JButton register = new JButton();
    public static void addloginJPanel(){
    	f.add(loginJPanel);
    	loginJPanel.revalidate();
    	//loginJPanel.repaint();
    	
        
    	
		System.out.println("painted "+ regis + " "+valid);
		
		jb.addActionListener(new ActionListener(){//our jb button set the text to the original

			@Override
			public void actionPerformed(ActionEvent e) {
				us.setText("Username; ");
				pas.setText("Password; ");
				regis = false;//the user is now on the login, not signing up
				login.setText("Log in");
				loginJPanel.remove(jb);
				f.repaint();
			}
		});
        
        register.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent p){
        		regis = true;
        		us.setText("Enter Username; ");
        		pas.setText("Enter Password");
        		login.setText("Sign up");//change text
        		loginJPanel.add(jb);
            }
        		
        	
        });
        login.addActionListener(new ActionListener(){ //add a command to enter button
        	
        	public void actionPerformed(ActionEvent l){
        		if(!text.getText().contains("-") && !moreText.getText().contains("-")){
        			sharedFunctionsAndVariables.username = text.getText();//the username is the text in the other text field
    				password = moreText.getText();//the password is the text in the text field

    				checkUserCredentials(regis);//this method will read and write the text file
        		}
				
				if(regis == true){
					us.setText("Username; ");
					pas.setText("Password; ");
	        		regis = false;//the user is now on the login, not signing up
	        		login.setText("Log in");
	        		loginJPanel.remove(jb);
	        		f.repaint();
				}
		    	
            }

			public void checkUserCredentials(boolean regis) {

				String line;//this is a string variable for each line of text
		    	try{
		    		BufferedReader br = new BufferedReader(new FileReader(FILENAME));//buffered reader reads characters from an input stream, 
		    		//the file reader reads from a text file
		    		BufferedWriter bw = new BufferedWriter(new FileWriter(FILENAME,true));
		    		//writes character to output stream 
		    		while((line = br.readLine()) != null){ //every line in the bufferedreader is read
		        		String[] words = line.split("-"); //in our file, the username and password is separated by a hyphen
		        		if(regis == true && words[0].equals(sharedFunctionsAndVariables.username)){//if the user is creating an account, the username must be unique
		        			valid = false;//so if a username is equal to the username, the username already exists
		        		}
		        		if(words[0].equals(sharedFunctionsAndVariables.username) && words[1].equals(password) && regis == false){
		        			//if the username is equals to one of the usernames in the file, and the password entered is equal to the password,
		        			//and if the user is logging in, not registering
		        			//loginJPanel.setVisible(false);
		        			System.out.println("loginToMenu");
		        			f.remove(loginJPanel);
		        			f.revalidate();
		        			f.repaint();
		        			menuSelection m = new menuSelection();//the menuSelection class is created
		        			
		        		}
		        	}
		    		if(regis == true && valid == true){//if the user is registering and if the username is not taken, create account
		    			bw.newLine();//new line for text file
		    			bw.write(sharedFunctionsAndVariables.username+"-"+password);//write down the username 
		    			bw.close();//closing buffered writer as output stream is no longer used
		    		}else{
		    			valid = true;
		    		}
		    	}
		    	catch(Exception u){ //using buffered readers and writers can cause errors with loading streams and files
		    		u.printStackTrace();//so it is important to use try/catch statements
		    		
		    	}
				
			}
        	
        });
		loginJPanel.setBackground(new Color(155,85,215));//set background of our JPanel to purple
        
    	loginJPanel.add(pas);
        loginJPanel.add(login);
        loginJPanel.add(register);
        loginJPanel.add(text);
        loginJPanel.add(moreText);
        loginJPanel.add(us);//Here, adding all of our components instead of in the JPanel.
        
        
    }
    static JPanel loginJPanel = new JPanel(){ //this jpanel will allow us paint graphics
    	
		@Override
     public void paintComponent( Graphics g){//this paint component method enables repainting of the JPanel
    	 super.paintComponent(g);//this enables repainting of the JPanel2
    	 
    	 
    	 try {//As there can be an IO stream error, try/catch must be used
			g.drawImage(load("ufo.png"),600-64, 50, 128*2, 128*2,null); //the first parameter is the image to load,
			
			g.drawImage(load("logo1.png"), 400-1332/4, 50, 1332/2, 102/2, null);
			// the other numbers are the coordinates, and sizes
		} catch (IOException e1) {
			 //TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	 //here, we set all of the bounds for all of our JLabel, Text fields and buttons
    	 	us.setBounds(160, (appHeight/5),140, appHeight/16);
     		us.setVisible(true);
     		us.setForeground(new Color(255,255,0));
     		us.setFont(new Font("Ariel", Font.BOLD, 14));
     		
     		pas.setFont(new Font("Ariel", Font.BOLD, 14));
        	pas.setBounds(160,(appHeight/5)+100, 140, appHeight/16);
        	pas.setVisible(true);
        	pas.setForeground(new Color(255,255,0));
            
        	//text.setVisible(true);
        	text.setFont(new Font("Ariel", Font.BOLD, 20));
        	text.setBackground(new Color(20,140,200));
        	text.setBounds(300, (appHeight/5), 200,appHeight/16);
            
        	moreText.setBounds(300, (appHeight/5)+100, 200, appHeight/16);
        	moreText.setVisible(true);
        	moreText.setFont(new Font("Ariel", Font.BOLD, 20));
        	moreText.setBackground(new Color(20,140,200));
            
            
        	jb.setBounds(0,0,100,30);
        	jb.setVisible(true);
        	jb.setText("Back");
        	
            
        	register.setText("Dont have an account? Sign up."); //The register button creates a sign up inteface
    		register.setBounds((int) (appWidth/2-appWidth/6), (appHeight/3)+200+150, appWidth/3, appHeight/12);
            register.setFont(new Font("Ariel", Font.BOLD, 14));//give it a nice big font
            register.setVisible(true);
            
            login.setFont(new Font("Ariel", Font.BOLD, 14));
            login.setVisible(true);
            login.setBounds(300, 500, 200,40);
       }
		
    };
    
	
}
