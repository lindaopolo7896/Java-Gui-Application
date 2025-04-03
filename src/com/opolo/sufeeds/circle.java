package com.opolo.sufeeds;

import javax.swing.*;
import java.awt.*;

public class circle extends JComponent {


    public void paint(Graphics2D g){
        super.paint(g);
       Graphics2D g2D=(Graphics2D) g;

        g2D.drawOval(0,0,100,100);

    }
}
