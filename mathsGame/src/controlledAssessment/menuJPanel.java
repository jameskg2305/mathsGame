package controlledAssessment;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JPanel;

public class menuJPanel extends sharedFunctionsAndVariables{
	static JButton jb = new JButton();
	static JButton jbscores = new JButton();
	static JButton jbexit = new JButton();
	static JButton jblogout = new JButton();
	static JPanel jp = new JPanel(){
		public void paintComponent( Graphics g){
	    	 super.paintComponent(g);
	    	 
	    	 jp.setBackground(new Color(155,85,215));//set colour of background
	    	 //load image from imageLoader
	    	 try {
	 			g.drawImage(load("proj.png"),450,100,128*2,128*2,null);
	 		} catch (IOException e1) {
	 			// TODO Auto-generated catch block
	 			e1.printStackTrace();
	 		}
	    	 try {
	    		 g.drawImage(load("logo1.png"), 400-1332/4, 50, 1332/2, 102/2, null);
			} catch (IOException e1) {
				 //TODO Auto-generated catch block
				e1.printStackTrace();
			}
	        
	    	//add the buttons
	        jb.setText("Play");
	        jb.setBounds(350,100, 100, 100);
	        jb.setBackground(new Color(187,20,200));
	        jb.setVisible(true);
	        jb.setFont(new Font("Ariel", Font.BOLD, 18));
	        jb.setForeground(new Color(255,255,0));
	        
	        jbscores.setText("Scores");
	        jbscores.setBounds(350,220, 100, 100);
	        jbscores.setBackground(new Color(187,20,200));
	        jbscores.setVisible(true);
	        jbscores.setFont(new Font("Ariel", Font.BOLD, 18));
	        jbscores.setForeground(new Color(255,255,0));
	        
	        jbexit.setText("Exit");
	        jbexit.setBounds(350,460, 100, 100);
	        jbexit.setFont(new Font("Ariel", Font.BOLD, 18));
	        jbexit.setBackground(new Color(187,20,200));
	        jbexit.setVisible(true);
	        jbexit.setForeground(new Color(255,255,0));
	        
	        jblogout.setText("Log out");
	        jblogout.setBounds(350,340, 100, 100);
	        jblogout.setFont(new Font("Ariel", Font.BOLD, 18));
	        jblogout.setBackground(new Color(187,20,200));
	        jblogout.setVisible(true);
	        jblogout.setForeground(new Color(255,255,0));
	        
	        
	        
		}
		
	};
	
	static void addComponents(){
		
		jb.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				f.remove(menuJPanel.jp);
				f.revalidate();
				f.repaint();
				
				f.add(gameConfiguration.gameConfiguration);
				f.revalidate();
				f.repaint();
				//f.add();
				//loginJPanel.loginJPanel.revalidate();
			}
        	
        });

		jblogout.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.remove(menuJPanel.jp);
				f.revalidate();
				f.repaint();
				f.add(loginJPanel.loginJPanel);
				f.revalidate();
			}
        	
        });

        jbscores.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.remove(menuJPanel.jp);
				f.revalidate();
				f.repaint();
				Scores ss = new Scores();
				
			}
        	
        });

        jbexit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				f.dispose();
			}
        	
        });
		jp.add(jblogout);
        jp.add(jb);
        jp.add(jbexit);
        jp.add(jbscores);
	}
}
