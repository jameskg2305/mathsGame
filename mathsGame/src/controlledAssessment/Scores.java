package controlledAssessment;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Scores extends sharedFunctionsAndVariables{
	ArrayList<stats> jlabeldata = new ArrayList<stats>();//list of stats objects to store scores
	
	
	ArrayList<JLabel> jlabelscores = new ArrayList<JLabel>();//list of jlabels to store scores
	ArrayList<JLabel> jlabelmodes = new ArrayList<JLabel>();//list of jlabels to store modes
	ArrayList<JLabel> jlabelnames = new ArrayList<JLabel>();//these store usernames
	
	static final String FILENAME = "scores.txt";
	public Scores(){
		f.add(scoresJPanel);
		scoresJPanel.revalidate();
		readfile();
		
		
	}
	
	
	private void readfile() {
		String line;
    	try{
    		BufferedReader br = new BufferedReader(new FileReader(FILENAME));//create buffered reader stream
    		
    		while((line = br.readLine()) != null){//for everyline in the buffered reader stream
        		String[] words = line.split("-");
        		
        		//Before, I had only 1 list of jlabels which did not work as the lines of text not aligned/tidy
        		jlabelscores.add(new JLabel());//add a new jlabel to list of jlables
            	jlabelmodes.add(new JLabel());
            	jlabelnames.add(new JLabel());
            	
        		jlabeldata.add(new stats(words[0],Integer.parseInt(words[1]),words[2]));//create a new object of the stats class to store record
        	}
    	}
    	catch(Exception u){//prevents any form of exceptions
    		u.printStackTrace();
    		
    	}
		
	}


	JPanel scoresJPanel = new JPanel(){//jpanel used to display our interface
		public void paintComponent(Graphics g){//this method is run every time the jpanel is 'repainted'
			
			super.paintComponent(g);
			
			scoresJPanel.setBackground(new Color(155,85,215));//set background colour
			
			JLabel scores = new JLabel("SCORES");
			scores.setVisible(true);
			scores.setBounds(355, 34, 90, 20);
			scores.setBackground(Color.YELLOW);
			scores.setForeground(new Color(255,255,0));
			scoresJPanel.add(scores);
    		
    		JButton back = new JButton();
    		back.setBounds(10, 10, 35, 35);
    		back.setBackground(new Color(187,20,200));
    		back.setVisible(true);
    		back.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					f.remove(scoresJPanel);
					f.revalidate();
					f.repaint();
					menuSelection ms = new menuSelection();
					
				}
    			
    		});
    		scoresJPanel.add(back);
    		
    		JButton sortbyLowest = new JButton("Lowest");
    		sortbyLowest.setBounds(580, 10, 120, 20);
    		sortbyLowest.setBackground(new Color(187,20,200));
    		sortbyLowest.setVisible(true);
    		sortbyLowest.setForeground(new Color(255,255,0));
    		sortbyLowest.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					displayScores("Lowest");
				}
    			
    		});
    		
    		scoresJPanel.add(sortbyLowest);
    		
    		JButton sortbyHighest = new JButton("Highest");
    		sortbyHighest.setBounds(460, 10, 120, 20);
    		sortbyHighest.setBackground(new Color(187,20,200));
    		sortbyHighest.setVisible(true);
    		sortbyHighest.setForeground(new Color(255,255,0));
    		sortbyHighest.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					displayScores("Highest");
				}
    			
    		});
    		scoresJPanel.add(sortbyHighest);
    		
        	int counter = 0;
        	for(JLabel jls : jlabelscores){
        		jls.setVisible(true);
        		System.out.println(counter);
        		String score = Integer.toString(jlabeldata.get(counter).scores);
        		jls.setText(score);
        		jls.setBounds(450, 100+(counter * 22), 90, 20);
        		jls.setBackground(Color.YELLOW);
        		jls.setForeground(new Color(255,255,0));
        		scoresJPanel.add(jls);
        		counter++;
        	}
        	counter = 0;
        	for(JLabel jls : jlabelnames){
        		jls.setBounds(50, 100+(counter * 22), 90, 20);
        		jls.setBackground(Color.YELLOW);
        		jls.setText(jlabeldata.get(counter).name);
        		jls.setVisible(true);
        		jls.setForeground(new Color(255,255,0));
        		scoresJPanel.add(jls);
        		counter++;
        	}
        	counter = 0;
        	for(JLabel jls : jlabelmodes){
        		jls.setVisible(true);
        		jls.setBounds(600, 100+(counter * 22), 200, 20);
        		jls.setBackground(Color.YELLOW);
        		jls.setText(jlabeldata.get(counter).mode);
        		jls.setForeground(new Color(255,255,0));
        		scoresJPanel.add(jls);
        		
        		
        		counter++;
        	}
    		counter = 0;
        	//sf.repaint();
			//scores.setBounds(350, 100, 100, 100);
    		System.out.println("ff");
		}
		
		
	};
	public void displayScores(String s){
		if(s.equals("Lowest")){
			for(int i = 0; i<jlabeldata.size() * jlabeldata.size();i++){//in bubblesort, there worst case complexity is n squared 
			for(int y =0; y<jlabeldata.size()-1;y++){
				//System.out.println(scoresforjlabels.get(y));
				if(jlabeldata.get(y).scores > jlabeldata.get(y+1).scores){
					int temp = jlabeldata.get(y+1).scores;
					jlabeldata.get(y+1).scores = jlabeldata.get(y).scores;
					jlabeldata.get(y).scores = temp;
					
					String temp1 = jlabeldata.get(y+1).name;
					jlabeldata.get(y+1).name = jlabeldata.get(y).name;
					jlabeldata.get(y).name = temp1;
					
					temp1 = jlabeldata.get(y+1).mode;
					jlabeldata.get(y+1).mode = jlabeldata.get(y).mode;
					jlabeldata.get(y).mode = temp1;
					
					scoresJPanel.removeAll(); // without this, there will be many clones of buttons.
					f.repaint();
				}
			}
			}
		}
		if(s.equals("Highest")){
			for(int i = 0; i<jlabeldata.size() * jlabeldata.size();i++){
			for(int y =0; y<jlabeldata.size()-1;y++){
				//System.out.println(scoresforjlabels.get(y));
				if(jlabeldata.get(y).scores < jlabeldata.get(y+1).scores){
					int temp = jlabeldata.get(y+1).scores;
					jlabeldata.get(y+1).scores = jlabeldata.get(y).scores;
					jlabeldata.get(y).scores = temp;
					
					String temp1 = jlabeldata.get(y+1).name;
					jlabeldata.get(y+1).name = jlabeldata.get(y).name;
					jlabeldata.get(y).name = temp1;
					
					temp1 = jlabeldata.get(y+1).mode;
					jlabeldata.get(y+1).mode = jlabeldata.get(y).mode;
					jlabeldata.get(y).mode = temp1;
					
					scoresJPanel.removeAll();
					f.repaint();
				}
			}
		}
		}
	}
}

class stats{//this class stores information about a score
	String name;
	int scores;
	String mode;
	public stats(String name, int scores, String mode){
		this.name = name;
		this.scores = scores;
		this.mode = mode;
	}
}


























