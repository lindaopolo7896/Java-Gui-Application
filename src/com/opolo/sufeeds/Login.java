//ELEANOR LINDA APIYO OPOLO
//ICS C
//189723
//1-11-2024

package com.opolo.sufeeds;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Login extends JFrame implements MouseListener, ActionListener {
    private JPanel panel1, panel, panel4;
    private JLabel headLbl, userLbl, passwordLbl, lbl6,copyLbl;
    private JTextField userField;
   private JPasswordField passField;
    private JButton btn;
    ImageIcon image = new ImageIcon("src/com/opolo/sufeeds/SU.png");

    static final String URL="jdbc:mysql://localhost:3306/db_eleanor_opolo_189723";
    static final String USER="root";
   static final String PASSKEY = System.getenv("DB_PASSWORD");

    public Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SU FEEDS-LOGIN");
        this.setSize(400, 600);
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(64, 92, 172));
        this.setLocationRelativeTo(null);
        this.setIconImage(image.getImage());


        panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(40, 50, 300, 400);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        this.add(panel);


        headLbl = new JLabel("LOGIN TO SU FEEDS");
        headLbl.setFont(new Font("Times New Roman", Font.BOLD, 25));
        headLbl.setBounds(30, 0, 300, 100);
        panel.add(headLbl);


        panel1 = new JPanel();
        panel1.setBounds(20, 70, 250, 300);
        panel1.setLayout(new GridLayout(8, 1));
        panel.add(panel1);


        userLbl = new JLabel("Username or Email");
        userLbl.setFont(new Font(null, Font.BOLD, 18));
        panel1.add(userLbl);

        userField = new JTextField();

        panel1.add(userField);

        passwordLbl = new JLabel("Password");
        passwordLbl.setFont(new Font(null, Font.BOLD, 18));
        panel1.add(passwordLbl);

        passField = new JPasswordField();
        panel1.add(passField);

        panel4 = new JPanel();
        panel4.setLayout(new FlowLayout());
        panel1.add(panel4);

        btn = new JButton("Login");
        btn.setPreferredSize(new Dimension(150, 30));
        btn.setBackground(new Color(22, 161, 94, 255));
        btn.setFocusable(false);
        btn.addActionListener(this);
        panel4.add(btn);


        lbl6 = new JLabel("Don't Have an Account?Register");
        lbl6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        lbl6.addMouseListener(this);
        panel1.add(lbl6);

        copyLbl=new JLabel("© 2024 SU Feeds. All Rights Reserved.");
        copyLbl.setBounds(80,530,300,30);
        copyLbl.setForeground(Color.white);
        this.add(copyLbl,BorderLayout.SOUTH);

        this.setVisible(true);

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        this.dispose();
        Registration registration = new Registration();


    }

    @Override
    public void mousePressed(MouseEvent e) {
        lbl6.setForeground(new Color(64, 92, 172));
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = userField.getText();
        String password = new String(passField.getPassword());

        if (login(input, password)) {
            JOptionPane.showMessageDialog(null, "Login Successful!");
            this.dispose();
            new Dashboard();
        } else {
            JOptionPane.showMessageDialog(null, "Invalid credentials.");
        }
    }

    public boolean login(String input, String password) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSKEY)) {
            String sql = "SELECT * FROM tbl_user_details WHERE (std_name = ? OR std_email = ?) AND std_pass = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, input); // Set username/email
                stmt.setString(2, input); // Set username/email
                stmt.setString(3, password); // Set password

                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next(); // If a record is found, login is successful
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
