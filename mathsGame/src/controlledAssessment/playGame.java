package controlledAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextField;

public class playGame extends sharedFunctionsAndVariables implements Runnable, KeyListener{ //extends imageloader to access its methods and values
//implements runnable to repaint the JPanel in a while loop. Keylistener allows the keys to have actions
	
	
	
	static JLabel finish = new JLabel();
	static JLabel displayscore = new JLabel();
	static JLabel click = new JLabel();
	static JButton returnmenu = new JButton();
	
	static Thread thread;//the thread allows us to run the program frame by frame
	static String FILENAME = "questions.txt";//directory for question sheet
	String scoresheet = "scores.txt";//directory for score sheet
	static int timeleft = 0;
	static boolean running = false;//if program is running
	static JTextField answerBox = new JTextField();
	
	static int score = 0;
	static JLabel times = new JLabel(""+timeleft);//to display the timeleft
	static JLabel points = new JLabel(""+score);//to display current score
	static int x = 180;
	static int y = 180;
	static JLabel question = new JLabel();
	
	static JLabel power = new JLabel();
	//int size = 0;
	static ArrayList <questionData>values = new ArrayList<questionData>();//this list holds the values, signs and answers for every question
	static int randomNumber;//questions are randomly chosen
	static boolean questionanswered = true;
	
	
	
	String modules = "";//the is string has the mode of the game so it can be recorded in the scoreboard
	
	static int stage = 1; //in the game there are 4 stages, the settings, then the maths game, then the end menu, and the animation
	
	
	
	
	JLabel finishhim = new JLabel("Finish Him!   (Press Space)");
	
	
	
	static BufferedImage l;
	
	
	
	
	static JPanel inGameInterface = new JPanel(){ //this jpanel is for the actual game
		// TODO Auto-generated method stub
		
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			inGameInterface.setBackground(new Color(155,85,215));
			
			g.drawImage(l, 0,0,800,800,null);
			
			times.setBounds(600, 10, 200, 200);
			times.setVisible(true);
			times.setText(""+timeleft);
			times.setFont(new Font("impact", Font.BOLD, 50));
			
			points.setBounds(300, 20, 100, 100);
			points.setVisible(true);
			points.setText(""+score);
			points.setForeground(new Color(255,255,0));
			points.setFont(new Font("impact", Font.BOLD, 40));
			
			//back.setBounds(10, 10, 35, 35);
    		////back.setBackground(new Color(187,20,200));
    		//back.setVisible(true);
			
			question.setFont(new Font("Ariel",Font.BOLD, 30));
			question.setBounds(180, 200, 400, 100);
			question.setVisible(true);
			question.setForeground(new Color(255,255,0));
			
			answerBox.requestFocus();
			answerBox.setBounds(180+200, 200, 120, 100);
			//answerBox.setVisible(true);
			answerBox.setFont(new Font("impact", Font.BOLD, 40));
			
			power.setBounds(x, y, 200, 100);
			power.setVisible(true);
			power.setFont(new Font("Ariel",Font.BOLD,30));
			power.setForeground(new Color(255,255,0));
		}
	};
	
	
	static JPanel endGameInterface = new JPanel(){ //this jpanel is for the game over menu
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			endGameInterface.setBackground(new Color(155,85,215));
			
			finish.setBounds(250,200,300,100);
			finish.setVisible(true);
			finish.setText("Level Complete");
			finish.setFont(new Font("Ariel",Font.BOLD,30));
			finish.setForeground(new Color(255,255,0));
			
			displayscore.setBounds(300,300, 200,200);
			displayscore.setVisible(true);
			displayscore.setFont(new Font("Ariel",Font.BOLD,30));

			displayscore.setForeground(new Color(255,255,0));
			displayscore.setText("You got; " + score);
			
			click.setBounds(100,400,600,100);
			click.setVisible(true);
			click.setText("Go to the main menu?");
			click.setFont(new Font("Ariel",Font.BOLD,20));
			click.setForeground(new Color(255,255,0));
			
			
			returnmenu.setBounds(500, 600, 200, 100);
			returnmenu.setVisible(true);
			returnmenu.setText("Go to main menu");
			returnmenu.setBackground(new Color(170,86,245));
			returnmenu.setFont(new Font("Ariel",Font.BOLD,16));
			
		}
		
	};
	public synchronized void start(){///when start method is activated, running is set to true
		score=0;
		if(playGame.running)return;
		playGame.running = true;
		playGame.thread = new Thread(this);//thread is introduced
		playGame.thread.start();//thread is started

		
	}
	
	@Override
	public void run() {
		running = true;
		int fps = 60;//60 frames a seccond is ideal
		double tps = 1000000000/fps;//a million nanoseconds is a second, but 60 frames a second
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer=0;
		int ticks = 0;
		long secondTimer = 0;
		while(running){
			now = System.nanoTime();
			delta+=(now-lastTime)/tps;//the computer takes time to read from one line to another by a few nanoseconds
			timer+= now - lastTime; //System.nano time is the time program started, now is the current time
			secondTimer+= now - lastTime;
			lastTime = now;
			if(delta>=1){//delta is equal to one after 60 frames
				ticks++;
				delta--;
			}
			if(timer >=1000000000/10 && stage == 2){//for animations, we need 60 frames a second
				ticks=0;
				timer=0;
				render();
			}
			if(secondTimer >=1000000000){
				timeleft--;
				secondTimer = 0;
			}
			
		}
	}
	
	private void render() {//this render method will process all of the instructions frame by frame
		
		if(stage == 2){//if in maths game
			
			//inGameInterface.removeAll();//remove all JPanel components, otherwise there will be duplicates of them
		
			times.setText(""+timeleft);//every second, the time displayed would go down by 1
			//the colour of the text changes depending on the time, eventually going to white
			times.setForeground(new Color(255,255-(int)Math.sqrt(timeleft*timeleft),255-(int)Math.sqrt(timeleft*timeleft)));
		
			answerBox.removeKeyListener(this);
			answerBox.addKeyListener(this);//adds key listener
			//answerBox.setFocusable(true);//The user does not have to click on the text box to type in it.
			//answerBox.setFocusTraversalKeysEnabled(false);
			
			inGameInterface.repaint();//repaints jpanel to update graphics
		}
		if((stage == 2 && (timeleft <= 0 || values.size() == 0))){//if timeleft is 0, change mode but also add player name and mode and score to text tile to record
			stage = 3;
			//thread.stop();
			f.remove(inGameInterface);
			f.add(endGameInterface);
			f.revalidate();
			f.repaint();
			recordscore();
			
			
		
		}
		if(stage == 3){
			//System.out.println("lhhblk");
			endGameInterface.add(finish);
			endGameInterface.add(displayscore);
			endGameInterface.add(click);
			endGameInterface.add(returnmenu);
			
			endGameInterface.revalidate();
			endGameInterface.repaint();
			
			//stop();
		}
		
		
	}
	private void recordscore() {
		// TODO Auto-generated method stub

		getmodules();
		String line;
    	try{
    		BufferedWriter bw = new BufferedWriter(new FileWriter(scoresheet,true));
    			bw.newLine();
    			if(score<0){
    				score=0;
    			}
    			bw.write(sharedFunctionsAndVariables.username+"-"+score+"-"+modules);
    			bw.close();
    	}
    	catch(Exception u){
    		u.printStackTrace();
    		
    	}
		
	}
	private static void indices() {//to display indices and fractions, we use jlabels to display them
		question.setText(""+values.get(randomNumber).a+" "+values.get(randomNumber).c+" "+values.get(randomNumber).b + " = ");//text is set to the first value + operator + second values + '=' sign
		if(values.get(randomNumber).a.contains("^")){//^ is the indices sign     if first values contains indices
			String[] d = values.get(randomNumber).a.split("\\^");//split 2^2(4) by '^' so we have an integer and an indices
			question.setText(""+d[0]+" "+values.get(randomNumber).c+" "+values.get(randomNumber).b + " = ");//the text is the number, then the operator, then the other number
			power.setText(""+d[1]);
			power.setFont(new Font("Ariel", Font.BOLD, 20));
			x = 200;//coordinates for indices
			y=180;
		}
		if(values.get(randomNumber).b.contains("^")){//if  second value contains indices
			String [] d = values.get(randomNumber).b.split("\\^");
			question.setText(""+values.get(randomNumber).a+" "+values.get(randomNumber).c+" "+d[0] + " = ");

			power.setFont(new Font("Ariel", Font.BOLD, 20));
			power.setText(""+d[1]);
			x=260;
			y=180;
		}
		if(values.get(randomNumber).a.contains("/")){//on fractions questions, both values have a '/' sign separating numerator from denomenator, and denomenator is the same
			String [] d = values.get(randomNumber).a.split("/");
			x = 420;
			y = 280;
			power.setFont(new Font("Ariel", Font.BOLD, 40));
			power.setText(""+d[1]);
			
		}
	}
	private void getmodules() {//on the scoreboard, the mode is displayed as a string 
		if(gameConfiguration.add == true){
			modules += "add ";
		}
		if(gameConfiguration.sub == true){
			modules += "sub ";
		}
		if(gameConfiguration.div == true){
			modules += "div ";
		}
		if(gameConfiguration.mul == true){
			modules += "mul ";
		}
	}
	
	
	
	public synchronized void stop(){
		if(!running)return;
		running = false;
		try{
			thread.join();
		}catch (InterruptedException e){//In case of exception of stopping a thread
			e.printStackTrace();
		}
		
	}
	static class questionData{//this class holds 4 values for a question,
		String a;//first number  e.g 5
		String b;//second number e.g 10
		String c;//operator e.g +
		String d;//answer e.g 15
		public questionData(String a, String b, String c, String d){
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
		}
		
	}
	public void keyPressed(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			//System.out.println(values.get(randomNumber).d);
			questionanswered = true;
			if(values.size()>0){
			if(answerBox.getText().equals(values.get(randomNumber).d)){
				score++;
			}else{   //wrong answer
				score--;
				System.out.println("****** "+values.get(randomNumber).d+" ********");
			}
			
			values.remove(randomNumber);
			}
			//System.out.println(values.size() + " " + mode);
			answerBox.setText(null);

			choosequestion();
		}
		
		
	
	}
	static void choosequestion(){
		if(questionanswered == true && values.size() > 0){//if question needs to be loaded and there is a question left
			
			Random random = new Random();//random generator creator
			randomNumber = random.nextInt(values.size());//integer is random number ranging from 0- whatever the size of the list of questions
			while(values.size() != 0 &&
					(
					(gameConfiguration.add == false && values.get(randomNumber).c.equals("+"))|| 
					(gameConfiguration.sub == false && values.get(randomNumber).c.equals("-"))||
					(gameConfiguration.mul == false && values.get(randomNumber).c.equals("*"))||
					(gameConfiguration.div == false && values.get(randomNumber).c.equals("/"))
					)
					){
					values.remove(randomNumber);
					//if the operator of the question does not correspond to the chosen mode e.g dividing when on adding questions only, pick another random question
					if(values.size() != 0){
					randomNumber = random.nextInt(values.size());
					System.out.println(values.get(randomNumber).c + " " + values.size() + " questions left");
					}
			}
			
			questionanswered = false;//a question has been set yet to be answered
			}
		if(values.size() > 0){
			power.setText("");//the indices text are removed as most questions do not have them
			indices();//sets the text of the indices
		}
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public static void createComponents() {
		// TODO Auto-generated method stub
		inGameInterface.add(question);//add the components
		inGameInterface.add(times);
		inGameInterface.add(answerBox);
		//inGameInterface.add(back);
		inGameInterface.add(points);
		inGameInterface.add(power);
	}
	
	
}
