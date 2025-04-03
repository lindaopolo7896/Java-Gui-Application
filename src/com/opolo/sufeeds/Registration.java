//ELEANOR LINDA APIYO OPOLO
//ICS C
//189723
//1-11-2024

package com.opolo.sufeeds;

import javax.swing.*;
import java.awt.*;



import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Registration extends JFrame implements MouseListener, ActionListener {
    private JPanel panel,panel1,panel2,panel3,panel4;
    private JLabel headLbl,userLbl,emailLbl,yearLbl,passLbl,accountLbl,conPassLbl,copyLbl;
    private JTextField txtUsername,txtEmail;
    private JPasswordField txtConPass,txtPassword;
    private JRadioButton radBtn1,radBtn2,radBtn3,radBtn4,radBtn5,radBtn6;
    private JCheckBox chkBox;
    private JButton btn;
    public ImageIcon image = new ImageIcon("src/com/opolo/sufeeds/SU.png");


    static final String URL="jdbc:mysql://localhost:3306/db_eleanor_opolo_189723";
    static final String USER="root";
    static final String PASSKEY = System.getenv("DB_PASSWORD");


    public Registration(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SU FEEDS - REGISTRATION");
        this.setLayout(null);
        this.setSize(500,650);
        this.getContentPane().setBackground(new Color(64,92,172));
        this.setLocationRelativeTo(null);
        this.setIconImage(image.getImage());


        panel=new JPanel();
        panel.setLayout(null);
        panel.setBounds(40,20,400,550);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2,true));
        this.add(panel);



        headLbl=new JLabel("SU FEEDS REGISTRATION");
        headLbl.setFont(new Font("Times New Roman",Font.BOLD,25));
        headLbl.setBounds(30,0,400,100);
        panel.add(headLbl);


        panel1=new JPanel();
        panel1.setBounds(50,70,280,450);
        panel1.setLayout(new GridLayout(15,1));
        panel.add(panel1);


        userLbl=new JLabel("Username");
        userLbl.setFont(new Font(null,Font.BOLD,18));
        panel1.add(userLbl);

        txtUsername=new JTextField();
        panel1.add(txtUsername);

        emailLbl=new JLabel("Email");
        emailLbl.setFont(new Font(null,Font.BOLD,18));
        panel1.add(emailLbl);

        txtEmail=new JTextField();
        panel1.add(txtEmail);

        yearLbl=new JLabel("Year of Study");
        yearLbl.setFont(new Font(null,Font.BOLD,18));
        panel1.add(yearLbl);


        panel2=new JPanel();
        panel2.setLayout(new FlowLayout());
        panel1.add(panel2);

        radBtn1=new JRadioButton("Year 1");
        panel2.add(radBtn1);
        radBtn2=new JRadioButton("Year 2");
        panel2.add(radBtn2);
        radBtn3=new JRadioButton("Year 3");
        panel2.add(radBtn3);


        panel3=new JPanel();
        panel3.setLayout(new FlowLayout(FlowLayout.CENTER,14,0));

        radBtn4=new JRadioButton("Year 4");
        panel3.add(radBtn4);
        radBtn5=new JRadioButton("Year 5");
        panel3.add(radBtn5);
        radBtn6=new JRadioButton("N/A");
        panel3.add(radBtn6);
        panel1.add(panel3);


        passLbl=new JLabel("Create Password");
        passLbl.setFont(new Font(null,Font.BOLD,18));
        panel1.add(passLbl);

        txtPassword=new JPasswordField();
        panel1.add(txtPassword);


        conPassLbl=new JLabel("Reenter Password");
        conPassLbl.setFont(new Font(null,Font.BOLD,18));
        panel1.add(conPassLbl);

        txtConPass=new JPasswordField();
        panel1.add(txtConPass);

        chkBox=new JCheckBox("I agree to terms and conditions");
        panel1.add(chkBox);


        panel4=new JPanel();
        panel4.setLayout(new FlowLayout());
        panel1.add(panel4);

        btn=new JButton("Register");
        btn.setPreferredSize(new Dimension(150,30));
        btn.setBackground(new Color  (22,161,94,255));
        btn.setFocusable(false);
        btn.addActionListener(this);
        panel4.add(btn);


        accountLbl=new JLabel("Have an Account?Login");
        accountLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        accountLbl.addMouseListener(this);
        panel1.add(accountLbl);

        copyLbl=new JLabel("© 2024 SU Feeds. All Rights Reserved.");
        copyLbl.setBounds(120,580,700,30);
        copyLbl.setForeground(Color.white);
        this.add(copyLbl,BorderLayout.SOUTH);


        this.setVisible(true);
    }





    @Override
    public void mouseClicked(MouseEvent e) {
        this.dispose();
        Login login=new Login();
    }

    @Override
    public void mousePressed(MouseEvent e) {

        accountLbl.setForeground(new Color(64,92,172));
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
        if(e.getSource()==btn){
            String username = txtUsername.getText();
            String email = txtEmail.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String confirmPassword = String.valueOf(txtConPass.getPassword());


            String yearOfStudy = null;
            if (radBtn1.isSelected()) yearOfStudy = "Year 1";
            else if (radBtn2.isSelected()) yearOfStudy = "Year 2";
            else if (radBtn3.isSelected()) yearOfStudy = "Year 3";
            else if (radBtn4.isSelected()) yearOfStudy = "Year 4";
            else if (radBtn5.isSelected()) yearOfStudy = "Year 5";
            else if (radBtn6.isSelected()) yearOfStudy = "N/A";


            boolean agreedTerms = chkBox.isSelected();

            // Validate input
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }


            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(password.length()<8) {
                JOptionPane.showMessageDialog(this, "Passwords must be 8 characters and above", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!agreedTerms) {
                JOptionPane.showMessageDialog(this, "You must agree to the terms and conditions!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Register user
            saveUserToDatabase(username, email, password,yearOfStudy,agreedTerms);

        }

    }

    private void saveUserToDatabase(String username, String email, String password, String yearOfStudy, boolean agreedTerms) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // Establish database connection
            conn = DriverManager.getConnection(URL, USER, PASSKEY);

            //checks if user already exists
            String checkQuery = "SELECT * FROM tbl_user_details WHERE std_email = ? OR std_name = ?";
            preparedStatement = conn.prepareStatement(checkQuery);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(this, "A user with this email or username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String insertQuery = "INSERT INTO tbl_user_details (std_name, std_email,year_of_study, std_pass,agreed_terms) VALUES (?, ?, ?, ?, ?)";

            preparedStatement = conn.prepareStatement(insertQuery);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, yearOfStudy);
            preparedStatement.setString(4,password);
            preparedStatement.setBoolean(5, agreedTerms);

            int rowsInserted = preparedStatement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Login();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }





}
