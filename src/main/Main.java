
package main;

import javax.swing.JFrame;


public class Main {

    public static void main(String[] args) {
        MyFrame frame = new MyFrame();
        
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        Employees emp = new Employees();
    }
    
}