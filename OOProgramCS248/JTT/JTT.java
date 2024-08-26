import java.awt.event.ActionListener;

// This is the Jerry Tac Toe Program 

// Author: Kristen Lee
// CS 248 1pm

import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class mom extends WindowAdapter
{
    public void windowClosing(WindowEvent e)
    {
        // we cant closing the window to quit the program 
        System.out.println("You have quit the game.");
        System.exit(0); // kills the program 
    }
}

class JTT extends JFrame implements ActionListener
{
    Color[]x;
    Color color;
    
    class gameBoard extends JPanel
    {
        public boolean homeScreen;
        public boolean newGame;
        public gameBoard() 
        { 
            homeScreen=true; 
            newGame=false;
            x=new Color[10];
            for(int i=1; i<=9;i++)
            {
                x[i]=Color.black;
            }
        }
        public void pressPlay() { homeScreen=false; }
        public void pressNew() 
        { 
            newGame=true; 
            x=new Color[10];
            for(int i=1; i<=9;i++)
            {
                x[i]=Color.black;
            }
        }

        //human color
        public void humanMorph(int i)
        {
            x[i]=Color.blue;
        }
        //computer color 
        public void compMorph(int i)
        {
            x[i]=Color.red;
        }
        //checking for a win
        public boolean checkWin(Color c)
        {
            if(x[1].equals(c) && x[2].equals(c) && x[3].equals(c)) { return true; }
            if(x[1].equals(c) && x[5].equals(c) && x[9].equals(c)) { return true; }
            if(x[1].equals(c) && x[4].equals(c) && x[8].equals(c)) { return true; }
            if(x[2].equals(c) && x[4].equals(c) && x[7].equals(c)) { return true; }
            if(x[2].equals(c) && x[6].equals(c) && x[9].equals(c)) { return true; }
            if(x[3].equals(c) && x[5].equals(c) && x[7].equals(c)) { return true; }
            if(x[3].equals(c) && x[6].equals(c) && x[8].equals(c)) { return true; }
            if(x[4].equals(c) && x[5].equals(c) && x[6].equals(c)) { return true; }
            if(x[7].equals(c) && x[8].equals(c) && x[9].equals(c)) { return true; }
            else return false;
        }
        public boolean checkTie(Color c, Color d)
        {
            if(checkWin(c)&& checkWin(d))
            {
                return true;
                
            }
            else return false;
        }

        //computer choosing move
        public int compMove(int i)
        {
            //right now set up to choose the first black circle; dumb moves
            for(int j=1; j<=9; j++)
            { 
                if(x[1].equals(Color.red) && x[2].equals(Color.red)) { if(x[3].equals(Color.black)) return 3; } // 123
                if(x[4].equals(Color.red) && x[5].equals(Color.red)) { if(x[6].equals(Color.black)) return 6; } // 456
                if(x[7].equals(Color.red) && x[8].equals(Color.red)) { if(x[9].equals(Color.black)) return 9; } // 789
                if(x[1].equals(Color.red) && x[5].equals(Color.red)) { if(x[9].equals(Color.black)) return 9; } // 159
                if(x[1].equals(Color.red) && x[4].equals(Color.red)) { if(x[8].equals(Color.black)) return 8; } // 148
                if(x[2].equals(Color.red) && x[4].equals(Color.red)) { if(x[7].equals(Color.black)) return 7; } // 247
                if(x[2].equals(Color.red) && x[6].equals(Color.red)) { if(x[9].equals(Color.black)) return 9; } // 269
                if(x[3].equals(Color.red) && x[5].equals(Color.red)) { if(x[7].equals(Color.black)) return 7; } // 357
                if(x[3].equals(Color.red) && x[6].equals(Color.red)) { if(x[8].equals(Color.black)) return 8; } // 368
                //blocking 
                if(x[1].equals(Color.blue) && x[4].equals(Color.blue)) { if(x[8].equals(Color.black)) return 8; } // 148
                if(x[1].equals(Color.blue) && x[8].equals(Color.blue)) { if(x[4].equals(Color.black)) return 4; } 
                if(x[8].equals(Color.blue) && x[4].equals(Color.blue)) { if(x[1].equals(Color.black)) return 1; }

                if(x[1].equals(Color.blue) && x[2].equals(Color.blue)) { if(x[3].equals(Color.black)) return 3; } // 123
                if(x[1].equals(Color.blue) && x[3].equals(Color.blue)) { if(x[2].equals(Color.black)) return 2; }
                if(x[2].equals(Color.blue) && x[3].equals(Color.blue)) { if(x[1].equals(Color.black)) return 1; }

                if(x[4].equals(Color.blue) && x[5].equals(Color.blue)) { if(x[6].equals(Color.black)) return 6; } // 456
                if(x[4].equals(Color.blue) && x[6].equals(Color.blue)) { if(x[5].equals(Color.black)) return 5; }
                if(x[5].equals(Color.blue) && x[6].equals(Color.blue)) { if(x[4].equals(Color.black)) return 4; }

                if(x[7].equals(Color.blue) && x[8].equals(Color.blue)) { if(x[9].equals(Color.black)) return 9; } // 789
                if(x[7].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[8].equals(Color.black)) return 8; }
                if(x[8].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[7].equals(Color.black)) return 7; }

                if(x[1].equals(Color.blue) && x[5].equals(Color.blue)) { if(x[9].equals(Color.black)) return 9; } // 159
                if(x[1].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[5].equals(Color.black)) return 5; }
                if(x[5].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[1].equals(Color.black)) return 1; }

                if(x[2].equals(Color.blue) && x[4].equals(Color.blue)) { if(x[7].equals(Color.black)) return 7; } // 247
                if(x[2].equals(Color.blue) && x[7].equals(Color.blue)) { if(x[4].equals(Color.black)) return 4; }
                if(x[4].equals(Color.blue) && x[7].equals(Color.blue)) { if(x[2].equals(Color.black)) return 2; }

                if(x[2].equals(Color.blue) && x[6].equals(Color.blue)) { if(x[9].equals(Color.black)) return 9; } // 269
                if(x[2].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[6].equals(Color.black)) return 6; }
                if(x[6].equals(Color.blue) && x[9].equals(Color.blue)) { if(x[2].equals(Color.black)) return 2; }

                if(x[3].equals(Color.blue) && x[5].equals(Color.blue)) { if(x[7].equals(Color.black)) return 7; } // 357
                if(x[3].equals(Color.blue) && x[7].equals(Color.blue)) { if(x[5].equals(Color.black)) return 5; }
                if(x[5].equals(Color.blue) && x[7].equals(Color.blue)) { if(x[3].equals(Color.black)) return 3; }

                if(x[3].equals(Color.blue) && x[6].equals(Color.blue)) { if(x[8].equals(Color.black)) return 8; } // 368
                if(x[3].equals(Color.blue) && x[8].equals(Color.blue)) { if(x[6].equals(Color.black)) return 6; }
                if(x[6].equals(Color.blue) && x[8].equals(Color.blue)) { if(x[3].equals(Color.black)) return 3; }

                if(x[j].equals(Color.black))
                {
                   return j;
                }
            }
            return 0;
        }

        //PAINTING!!!
        public void paintComponent(Graphics g)
        {
            g.setColor(Color.pink);
            g.fillRect(0,0, 12000,800);
            if(!homeScreen)
            {
                //lines 
                g.setColor(Color.black);
                g.drawLine(200, 150, 1000, 150); //line 1 to 3
                //g.drawLine(650, 250, 1050, 250); //line 2 to 3
                g.drawLine(400, 350, 800, 350); //line 4 to 6
                //g.drawLine(650, 450, 850, 450); //line 5 to 6
                g.drawLine(200, 550, 1000, 550); //line 7 to 9
                //g.drawLine(650, 650, 1050, 650); //line 8 to 9
                g.drawLine(200, 150, 600, 550); //line 1 to 8
                g.drawLine(600, 150, 1000, 550); //line 2 to 9
                g.drawLine(600, 150, 200, 550); //line 2 to 7
                g.drawLine(1000, 150, 600, 550); //line 3 to 8
                g.drawLine(200, 150, 1000, 550); //line 1 to 5
                g.drawLine(1000, 150, 200, 550); //line 3 to 7

                //circles
                // bottom row
                g.setColor(x[7]);
                g.fillOval(150,500,100,100); //7
                g.setColor(x[8]);
                g.fillOval(550,500,100,100); //8
                g.setColor(x[9]);
                g.fillOval(950,500,100,100); //9
                //middle row
                g.setColor(x[4]);
                g.fillOval(350,300,100,100); //4
                g.setColor(x[5]);
                g.fillOval(550,300,100,100); //5
                g.setColor(x[6]);
                g.fillOval(750,300,100,100); //6
                //top row
                g.setColor(x[1]);
                g.fillOval(150,100,100,100); //1
                g.setColor(x[2]);
                g.fillOval(550,100,100,100); //2
                g.setColor(x[3]);
                g.fillOval(950,100,100,100); //3

                // write numbers
                g.setFont(new Font("Arial", Font.BOLD, 20));
                g.setColor(Color.white);
                g.drawString("1", 200, 150);
                g.drawString("2", 600, 150);
                g.drawString("3", 1000, 150);
                g.drawString("4", 400, 350);
                g.drawString("5", 600, 350);
                g.drawString("6", 800, 350);
                g.drawString("7", 200, 550);
                g.drawString("8", 600, 550);
                g.drawString("9", 1000, 550);
            }
        }
    }

    gameBoard artist; 
    JButton playButton, newGame;
    JButton morphMove, player1, player2;
    JTextArea tf;
    public boolean gameOver=false;

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==playButton)
        {
            artist.pressPlay();
            artist.repaint(); //force a screen refresh
        }
        //int j;
        if(e.getSource()==morphMove)
        {
            
            if(!gameOver)
            {
                int i=Integer.parseInt(tf.getText());
                artist.humanMorph(i);
                artist.repaint();
                //check if user wins 
                if(artist.checkWin(Color.blue)) 
                { 
                    System.out.println("Human win!"); 
                    gameOver=true;
                    return;
                }

                // have the computer pick a counter move
                //int j;
                artist.compMorph(artist.compMove(i));
                artist.repaint();
                /*for(int j=1; j<=9; j++)
                {
                    if(x[j].equals(Color.black))
                    {
                        artist.compMorph(j);
                        artist.repaint();
                        break;
                    }
                }*/

                //check for computer win 
                if(artist.checkWin(Color.red)) 
                { 
                    System.out.println("Computer win!"); 
                    gameOver=true;

                }
                //check for draw 
                if(artist.checkTie(Color.blue, Color.red))
                { 
                    System.out.println("Draw!"); 
                    gameOver=true;
                }
            }
        }

        if(e.getSource()==player2)
        {
            if(!gameOver)
            {
                System.out.println("The computer moves first.");
                for(int j=1; j<=9; j++)
                {
                    if(x[j].equals(Color.black))
                    {
                        artist.compMorph(j);
                        artist.repaint();
                        break;
                    }
                }
            }
        }
        if(e.getSource()==newGame)
        {
            gameOver=false;
            artist.pressNew();
            artist.repaint();
        }
    }

    public JTT()
    {
        setTitle("Jerry Tac Toe");
        addWindowListener(new mom());
        setSize(1300,1000);
        
        Container T=getContentPane();
        T.setLayout(new BorderLayout()); // set layout manager

        JPanel upperPanel=new JPanel();
        upperPanel.setLayout(new BorderLayout());
        T.add(upperPanel, "North");
        JPanel lowerPanel=new JPanel();
        lowerPanel.setLayout(new BorderLayout());
        T.add(lowerPanel, "South");
        JPanel sidePanel=new JPanel();
        sidePanel.setLayout(new BorderLayout());
        T.add(sidePanel, "West");

        artist=new gameBoard();
        T.add(artist,"Center");

        playButton= new JButton("Play");
        playButton.addActionListener(this);
        upperPanel.add(playButton, "West");

        newGame= new JButton("New Game");
        newGame.addActionListener(this);
        sidePanel.add(newGame, "North");
        player2= new JButton("Let Computer Play First");
        player2.addActionListener(this);
        sidePanel.add(player2, "South");
    
        morphMove=new JButton("Make a move");
        morphMove.addActionListener(this);
        lowerPanel.add(morphMove, "West");
       
        tf=new JTextArea("Press play to begin. \nIf you would like to move first, then in the text box below type the # you would like to move to. \nAfter you type your number press 'Make Move' button. \nIf you want the computer to play first then press 'Let Computer Play First'\nIf you want to restart press 'New Game' \nIf you want to quit the game, just exit out of this window.");
        upperPanel.add(tf, "Center"); 
        tf=new JTextArea("0");
        lowerPanel.add(tf, "Center"); 
        setVisible(true);
    }
    public static void main(String [] args)
    {
        JTT tac=new JTT();
    }
}