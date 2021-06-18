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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

import controlledAssessment.playGame.questionData;

public class gameConfiguration extends sharedFunctionsAndVariables{
	static JSlider timeslider = new JSlider(60,180,60); //our slider can customise the minimum value to the maximum
	static JButton start = new JButton("PLAY");
	//JButton start = new JButton("PLAY");
	static JButton back = new JButton();
	static JLabel whattodo = new JLabel("The slider at the top measures the time limit you"
			+ " have to answer the questions");
	static JLabel whattodo1 = new JLabel( " Use the buttons to select your types of questions | You must select at least one");
	static boolean add = false;//booleans check what type of questions are selected
	static boolean sub = false;
	static boolean mul = false;
	static boolean div = false;
	static JRadioButton addMode = new JRadioButton("Adding");//these tickboxes can be true or false
	static JRadioButton subMode = new JRadioButton("Subtraction");//good for making selections
	static JRadioButton divMode = new JRadioButton("Dividing");
	static JRadioButton mulMode = new JRadioButton("Multiplying");
	static void createComponents(){
		gameConfiguration.add(timeslider);//add jpanel components
		gameConfiguration.add(addMode);
		gameConfiguration.add(subMode);
		gameConfiguration.add(mulMode);
		gameConfiguration.add(divMode);
		gameConfiguration.add(start);
		gameConfiguration.add(back);
		gameConfiguration.add(whattodo);
		gameConfiguration.add(whattodo1);
		start.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(add == true ||sub == true ||mul == true ||div == true){//the game starts only if at least one buttons is selected
				playGame.timeleft = timeslider.getValue();//the time is based on the value of the slider
				if(playGame.timeleft==0){
					playGame.timeleft=60;
				}
				playGame.stage = 2;//mode 1 is the maths game
				
				f.remove(gameConfiguration);//removing the settings JPanel
				f.add(playGame.inGameInterface);//adding the game jpanel
				f.revalidate();//when changing jpanels, they must be revalidated to change heirachy
				f.repaint();//paint the jpanel
				//playGame.inGameInterface.requestFocus();
				readFile();//the text file with questions are read so the list of questions can be created 
				
				
				//start();//start the thread
				(new playGame()).start();
				
				playGame.choosequestion();
				try{
					playGame.l = load("pp.png");
				}catch(Exception k){
					
				}
				}
			}

			
			
		});
		
		addMode.addActionListener(new ActionListener(){//radio buttons can have action listeners too

			@Override
			public void actionPerformed(ActionEvent e) { //the radio button changes the values of the modes
				if(add == false){//if its negative, set it to positive
					add = true;
					System.out.println(add);
				}
				else if(add == true){
					add = false;
					System.out.println(add);
				}
			}
			
			
		});
		divMode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(div == false){
					div = true;
					System.out.println(div);
				}
				else if(div == true){
					div = false;
					System.out.println(div);
				}
			}
			
			
		});
		subMode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(sub == false){
					sub = true;
					System.out.println(sub);
				}
				else if(sub == true){
					sub = false;
					System.out.println(sub);
				}
			}
			
			
		});
		mulMode.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				if(mul == false){
					mul = true;
					System.out.println(mul);
				}
				else if(mul == true){
					mul = false;
					System.out.println(mul);
				}
			}
			
			
		});
		playGame.returnmenu.addActionListener(new ActionListener(){//return to menu

			@Override
			public void actionPerformed(ActionEvent e) {
				menuSelection m = new menuSelection();
				f.remove(playGame.endGameInterface);
				f.revalidate();
				f.repaint();
				menuSelection m1 = new menuSelection();
			}
		});
		

		back.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				menuSelection m = new menuSelection();
				f.remove(gameConfiguration);
				f.revalidate();
				f.repaint();
				menuSelection m2 = new menuSelection();
			}
		});
	}
	
	
	static JPanel gameConfiguration = new JPanel(){ //this jpanel is for the settings
		

		public void paintComponent(Graphics g){
			super.paintComponent(g);
			
			gameConfiguration.setBackground(new Color(155,85,215));
			
			timeslider.setBounds(200,100,400,60);//set the bounds
			timeslider.setVisible(true);
			
			timeslider.setPaintLabels(true);//ticks are labelled with numbers
			
			timeslider.setPaintTicks(true);//ticks are painted
			timeslider.setSnapToTicks(true);//values are set to ticks, not in between ticks
			timeslider.setMajorTickSpacing(60);//tick at every 60
			timeslider.setMinorTickSpacing(60);//every 60 has a label
			timeslider.setBackground(new Color(155,85,215));//colour of box behind
			timeslider.setForeground(new Color(255,255,0));//text colour
			
			whattodo.setVisible(true);
			whattodo.setBounds(60, 10, 900, 30);
			whattodo.setBackground(new Color(155,85,215));
			whattodo.setForeground(new Color(255,255,0));
			whattodo.setFont(new Font("Ariel",Font.BOLD,18));
			
			whattodo1.setVisible(true);
			whattodo1.setBounds(60, 200,  700, 200);
			whattodo1.setBackground(new Color(155,85,215));
			whattodo1.setForeground(new Color(255,255,0));
			whattodo1.setFont(new Font("Ariel",Font.BOLD,18));
			
			addMode.setVisible(true);
			addMode.setBounds(140, 400, 120, 40);
			addMode.setBackground(new Color(155,85,215));
			addMode.setForeground(new Color(255,255,0));
			addMode.setFont(new Font("Ariel",Font.BOLD,14));
			
			subMode.setVisible(true);
			subMode.setBounds(260, 400, 120, 40);
			subMode.setBackground(new Color(155,85,215));
			subMode.setForeground(new Color(255,255,0));
			subMode.setFont(new Font("Ariel",Font.BOLD,14));
			
			divMode.setVisible(true);
			divMode.setBounds(380, 400, 120, 40);
			divMode.setBackground(new Color(155,85,215));
			divMode.setForeground(new Color(255,255,0));
			divMode.setFont(new Font("Ariel",Font.BOLD,14));
			
			mulMode.setVisible(true);
			mulMode.setBounds(500, 400, 120, 40);
			mulMode.setBackground(new Color(155,85,215));
			mulMode.setForeground(new Color(255,255,0));
			mulMode.setFont(new Font("Ariel",Font.BOLD,14));
			
			start.setVisible(true);
			start.setBounds(350, 500, 100, 80);
			
			
    		back.setBounds(10, 10, 35, 35);
    		back.setBackground(new Color(187,20,200));
    		back.setVisible(true);
		
		}
		
	};
	
	static void readFile() {
		String line;
		
    	try{
    		BufferedReader br = new BufferedReader(new FileReader(playGame.FILENAME));
    		BufferedWriter bw = new BufferedWriter(new FileWriter(playGame.FILENAME,true));
    		
    		while((line = br.readLine()) != null){
        		String[] words = line.split("=");
        		playGame.values.add(new questionData(words[0],words[1],words[2],words[3]));//object of question created with values
        	}
    	}
    	catch(Exception u){
    		u.printStackTrace();
    		
    	}
		
	}
	
	
}
