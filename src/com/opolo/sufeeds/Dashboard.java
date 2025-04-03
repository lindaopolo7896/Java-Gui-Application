//ELEANOR LINDA APIYO OPOLO
//ICS C
//189723
//1-11-2024

package com.opolo.sufeeds;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.*;

public class Dashboard extends JFrame implements ActionListener, MouseListener {

   private ImageIcon image = new ImageIcon("src/com/opolo/sufeeds/SU.png");
    private  JPanel panel1,panel2,panel3,menuPanel,panel5,homePanel,classPanel, commentPanel,settingPanel,logPanel,cardPanel1;
    private JPanel card1,headPanel,bodyPanel,btnPanel,card2,headPanel1,bodyPanel2,btnPanel3,cardPanel2,card3,headPanel2,bodyPanel3;
    private JPanel btnmenuPanel,copyPanel;
    private MatteBorder bottomBorder,rightBorder,topBorder;
    private ImageIcon menuIcon=new ImageIcon("src/com/opolo/sufeeds/menu.png");
    private JLabel menuLabel,label2,homeLabel,classLabel,commentLabel,settingLabel,logLabel,cardLabel1,cardLabel10,cardLabel11;
    private JLabel cardLabel2,cardLabel20,cardLabel21,copyLbl,noFeedLabel;
    private JLabel lblInstructor,lblClassName,noClassesLabel,noTopicsLabel,lblTopicName;
    private JTextField txtClassName ,txtClassInstructor,txtTopicName;
    private ImageIcon calendarIcon=new ImageIcon("src/com/opolo/sufeeds/calendar.png");
    private ImageIcon homeIcon=new ImageIcon("src/com/opolo/sufeeds/home.png");
    private ImageIcon classIcon=new ImageIcon("src/com/opolo/sufeeds/class.png");
    private ImageIcon commentIcon=new ImageIcon("src/com/opolo/sufeeds/comment.png");
    private ImageIcon settingIcon=new ImageIcon("src/com/opolo/sufeeds/setting.png");
    private ImageIcon logoutIcon=new ImageIcon("src/com/opolo/sufeeds/logout.png");
    private JButton btn1,btn2,btn3,btnSave,btnCancel,btnSaveTopic,btnSaveFeedBack,  btnCancelTopic ;
    private JDialog addClassDialog,addTopicDialog,addFeedBackDialog;
    private DefaultListModel<String> list,list2,list3;
    private JList<String> classList,topicList,feedback;
    private JScrollPane scrollPane,scrollPane2,scrollPane3;
    private JTextArea txtFeedBack;

    static final String URL="jdbc:mysql://localhost:3306/db_eleanor_opolo_189723";
    static final String USER="root";
    static final String PASSKEY = System.getenv("DB_PASSWORD");


    public Dashboard(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("SU Feeds-Student Dashboard");
        this.setSize(1000,650);
        this.setLayout(new BorderLayout());
        this.setIconImage(image.getImage());

        bottomBorder=BorderFactory.createMatteBorder(0,0,1,0,Color.BLACK);
        panel1=new JPanel();
        panel1.setPreferredSize(new Dimension(1000,50));
        panel1.setLayout(new BorderLayout(10,10));
        panel1.setBorder(bottomBorder);
        this.add(panel1,BorderLayout.NORTH);

        panel2=new JPanel();
        panel2.setLayout(new FlowLayout());
        panel2.setPreferredSize(new Dimension(200,40));
        panel1.add(panel2,BorderLayout.WEST);

        menuLabel=new JLabel(menuIcon);

        menuLabel.setText("SU Feeds");
        menuLabel.setFont(new Font("Times New Roman",Font.BOLD,25));
        menuLabel.setForeground(new Color(64,92,172));
        menuLabel.setIconTextGap(30);
        menuLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        menuLabel.addMouseListener(this);
        panel2.add(menuLabel);

        panel3=new JPanel();

        panel3.setPreferredSize(new Dimension(300,40));
        panel1.add(panel3,BorderLayout.EAST);

        label2=new JLabel("+");
        label2.setFont(new Font(null, Font.BOLD,40));
        label2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        label2.addMouseListener(this);

        label2.setIcon(calendarIcon);
        label2.setIconTextGap(30);
        panel3.add(label2);

        rightBorder=BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK);
        menuPanel=new JPanel();
        menuPanel.setBackground(new Color(64,92,172));
        menuPanel.setLayout(new GridLayout(10,1));
        menuPanel.setBorder(rightBorder);
        menuPanel.setPreferredSize(new Dimension(220,150));
        this.add(menuPanel,BorderLayout.WEST);

        homePanel=new JPanel();

        homePanel.setBackground(new Color(64,92,172));
        menuPanel.add(homePanel);

        homeLabel=new JLabel(homeIcon);
        homeLabel.setText("Home");
        homeLabel.setForeground(Color.white);
        homeLabel.setIconTextGap(50);
        homeLabel.setFont(new Font("Sans Serif",Font.BOLD,21));
        homeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeLabel.addMouseListener(this);
        homePanel.add(homeLabel);

        classPanel=new JPanel();
        classPanel.setBackground(new Color(64,92,172));
        menuPanel.add(classPanel);

        classLabel=new JLabel(classIcon);
        classLabel.setText("Classes");
        classLabel.setForeground(Color.white);
        classLabel.setIconTextGap(30);
        classLabel.setFont(new Font("Sans Serif",Font.BOLD,21));
        classLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        classLabel.addMouseListener(this);
        classPanel.add(classLabel);

        commentPanel=new JPanel();
        commentPanel.setBackground(new Color(64,92,172));
        menuPanel.add(commentPanel);

        commentLabel=new JLabel(commentIcon);
        commentLabel.setText("Comments");
        commentLabel.setForeground(Color.white);
        commentLabel.setIconTextGap(10);
        commentLabel.setFont(new Font("Sans Serif",Font.BOLD,21));
        commentLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        commentLabel.addMouseListener(this);
        commentPanel.add(commentLabel);

        settingPanel=new JPanel();
        settingPanel.setBackground(new Color(64,92,172));
        menuPanel.add(settingPanel);

        settingLabel=new JLabel(settingIcon);
        settingLabel.setText("Topics");
        settingLabel.setForeground(Color.white);
        settingLabel.setIconTextGap(35);
        settingLabel.setFont(new Font("Sans Serif",Font.BOLD,21));
        settingLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        settingLabel.addMouseListener(this);
        settingPanel.add(settingLabel);

        logPanel=new JPanel();
        logPanel.setBackground(new Color(64,92,172));
        menuPanel.add(logPanel);

        logLabel=new JLabel(logoutIcon);
        logLabel.setText("Log Out");
        logLabel.setForeground(Color.white);
        logLabel.setIconTextGap(35);
        logLabel.setFont(new Font("Sans Serif",Font.BOLD,21));
        logLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        logLabel.addMouseListener(this);
        logPanel.add(logLabel);




        panel5=new JPanel();

        panel5.setBounds(0, 0, 700, 400);
        this.add(panel5,BorderLayout.CENTER);



        cardPanel1=new JPanel();
        cardPanel1.setPreferredSize(new Dimension(700,200));
        panel5.add(cardPanel1);

        card1=new JPanel();
        card1.setPreferredSize(new Dimension(300,150));
        card1.setLayout(new BorderLayout());
        cardPanel1.add(card1);

        headPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,25,4));
        headPanel.setBackground(new Color(252, 251, 251, 228));
        cardLabel1=new JLabel("Add Classes");
        cardLabel1.setFont(new Font("Sans Serif",Font.BOLD,25));
        headPanel.add(cardLabel1);
        card1.add(headPanel,BorderLayout.NORTH);

        bodyPanel=new JPanel(new FlowLayout(FlowLayout.LEFT,25,4));
        bodyPanel.setBackground(new Color(252, 251, 251, 228));
        cardLabel10=new JLabel("Add and manage classes");
        cardLabel10.setFont(new Font("Sans Serif",Font.PLAIN,19));
        cardLabel11=new JLabel();
        cardLabel11.setText(" your attending this semester");
        cardLabel11.setFont(new Font("Sans Serif",Font.PLAIN,19));
        bodyPanel.add(cardLabel10);
        bodyPanel.add(cardLabel11);
        card1.add(bodyPanel,BorderLayout.CENTER);

        btnPanel=new JPanel(new FlowLayout( FlowLayout.CENTER,0,10));
        btnPanel.setBackground(new Color(252, 251, 251, 228));
        btn1=new JButton("Add Class");
        btn1.setFocusable(false);
        btn1.setForeground(Color.white);
        btn1.setBackground(new Color(72,164,76));
        btn1.setPreferredSize(new Dimension(250,30));
        btn1.addActionListener(this);
        btnPanel.add(btn1);
        card1.add(btnPanel,BorderLayout.SOUTH);


        card2=new JPanel();
        card2.setPreferredSize(new Dimension(300,150));
        card2.setLayout(new BorderLayout());
        cardPanel1.add(card2);

        headPanel1=new JPanel(new FlowLayout(FlowLayout.LEFT,25,4));
        headPanel1.setBackground(new Color(252, 251, 251, 228));
        cardLabel2=new JLabel("Add Topics/FeedBack");
        cardLabel2.setFont(new Font("Sans Serif",Font.BOLD,25));
        headPanel1.add(cardLabel2);
        card2.add(headPanel1,BorderLayout.NORTH);



        bodyPanel2=new JPanel(new FlowLayout(FlowLayout.LEFT,25,4));
        bodyPanel2.setBackground(new Color(252, 251, 251, 228));
        cardLabel20=new JLabel("Add topics & feedback");
        cardLabel20.setFont(new Font("Sans Serif",Font.PLAIN,19));
        cardLabel21=new JLabel();
        cardLabel21.setText(" for each class");
        cardLabel21.setFont(new Font("Sans Serif",Font.PLAIN,19));
        bodyPanel2.add(cardLabel20);
        bodyPanel2.add(cardLabel21);
        card2.add(bodyPanel2,BorderLayout.CENTER);


        btnPanel3=new JPanel(new FlowLayout( FlowLayout.CENTER,0,10));
        btnPanel3.setBackground(new Color(252, 251, 251, 228));
        btn2=new JButton("Add Topics");
        btn2.setFocusable(false);
        btn2.setForeground(Color.white);
        btn2.setBackground(new Color(72,164,76));
        btn2.setPreferredSize(new Dimension(250,30));
        btn2.addActionListener(this);
        btnPanel3.add(btn2);
        card2.add(btnPanel3,BorderLayout.SOUTH);

        cardPanel2=new JPanel();
        cardPanel2.setPreferredSize(new Dimension(700,200));
        panel5.add(cardPanel2);





        btnmenuPanel=new JPanel(new FlowLayout( FlowLayout.CENTER,0,10));




        copyPanel=new JPanel();
        topBorder=BorderFactory.createMatteBorder(0,0,0,1,Color.BLACK);
        copyPanel.setBorder(topBorder);
        this.add(copyPanel,BorderLayout.SOUTH);

        copyLbl=new JLabel("© 2024 SU Feeds. All Rights Reserved.");

        copyPanel.add(copyLbl,BorderLayout.CENTER);

        list=new DefaultListModel<>();
        classList=new JList<>(list);
        classList.setFont(new Font("Arial",Font.PLAIN,18));
        classList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollPane=new JScrollPane(classList);
        scrollPane.setPreferredSize(new Dimension(500,400));

        noClassesLabel = new JLabel("No available classes", SwingConstants.CENTER);
        noClassesLabel.setFont(new Font("Sans Serif", Font.ITALIC, 18));
        noClassesLabel.setForeground(Color.RED);


        list2=new DefaultListModel<>();
        topicList=new JList<>(list2);
        topicList.setFont(new Font("Arial",Font.PLAIN,18));
        topicList.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollPane2=new JScrollPane(topicList);
        scrollPane2.setPreferredSize(new Dimension(500,400));

        list3=new DefaultListModel<>();
        feedback=new JList<>(list3);
        feedback.setFont(new Font("Arial",Font.PLAIN,18));
        feedback.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        scrollPane3=new JScrollPane(feedback);
        scrollPane3.setPreferredSize(new Dimension(500,400));


        noTopicsLabel = new JLabel("No available topics", SwingConstants.CENTER);
        noTopicsLabel.setFont(new Font("Sans Serif", Font.ITALIC, 18));
        noTopicsLabel.setForeground(Color.RED);

        noFeedLabel = new JLabel("No available feedback", SwingConstants.CENTER);
        noFeedLabel.setFont(new Font("Sans Serif", Font.ITALIC, 18));
        noFeedLabel.setForeground(Color.RED);
        this.setVisible(true);

    }


    private void showAddClassDialog() {
        addClassDialog = new JDialog(this, "Add Class", true);
        addClassDialog.setSize(300, 200);
        addClassDialog.setLayout(new GridLayout(3, 2, 20, 20));

        lblInstructor = new JLabel("Class Instructor:");
        addClassDialog.add(lblInstructor);
        txtClassInstructor = new JTextField();
        txtClassInstructor.setFont(new Font(null,Font.PLAIN,18));
        addClassDialog.add(txtClassInstructor);



        lblClassName=new JLabel("Class Name");
        addClassDialog.add(lblClassName);
        txtClassName=new JTextField();
        txtClassName.setFont(new Font(null,Font.PLAIN,18));
        addClassDialog.add(txtClassName);

        btnSave = new JButton("Save");
        btnSave.setBackground(new Color(64,92,172));
        btnSave.setFocusable(false);
        btnSave.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSave.addActionListener(this);
        addClassDialog.add(btnSave);

        btnCancel = new JButton("Cancel");
        btnCancel.setBackground(new Color(64,92,172));
        btnCancel.setFocusable(false);
        btnCancel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancel.addActionListener(this);
        addClassDialog.add(btnCancel);

        addClassDialog.setLocationRelativeTo(this);
        addClassDialog.setVisible(true);
    }

    private  void saveClassesDbms(String instructor,String className){
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet resultSet=null;

        try{
            conn= DriverManager.getConnection(URL,USER,PASSKEY);
            String checkQuery="SELECT * FROM tbl_classes WHERE class_name=?";
            psmt=conn.prepareStatement(checkQuery);
            psmt.setString(1,className);
            resultSet= psmt.executeQuery();

            if(resultSet.next()){
                JOptionPane.showMessageDialog(this,"Class Already Exists","ERROR",JOptionPane.ERROR_MESSAGE);
            }

            String insertQuery="INSERT INTO tbl_classes(inst_name,class_name) VALUES(?,?)";
            psmt=conn.prepareStatement(insertQuery);
            psmt.setString(1,instructor);
            psmt.setString(2,className);

            int rowsInserted = psmt.executeUpdate();
            if(rowsInserted>0){
                JOptionPane.showMessageDialog(this,"Class Added Successfully😛","Info",JOptionPane.INFORMATION_MESSAGE);
                addClassDialog.dispose();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    private void loadClasses() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSKEY);
            String selectQuery = "SELECT inst_name, class_name FROM tbl_classes";
            psmt = conn.prepareStatement(selectQuery);
            resultSet = psmt.executeQuery();

            // Clear the list before loading new data
            list.clear();

            boolean hasClasses = false; //  check if classes exist
            while (resultSet.next()) {
                hasClasses = true; // if at least one class is found
                String classEntry = resultSet.getString("inst_name") + ": " + resultSet.getString("class_name");
                list.addElement(classEntry);
            }

            // If no classes were found, show the no classes label
            if (!hasClasses) {
                panel5.remove(scrollPane); // Remove the scroll pane if it exists
                panel5.add(noClassesLabel); // Add the no classes label
            } else {
                panel5.remove(noClassesLabel); // Remove the no classes label if it exists
                panel5.add(scrollPane); // Add the scroll pane with classes
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (psmt != null) psmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showAddTopicDialog() {
        addTopicDialog = new JDialog(this, "Add Topic", true);
        addTopicDialog.setSize(400, 500);
        addTopicDialog.setLayout(new GridLayout(5, 1, 20, 20));

        lblTopicName = new JLabel("Topic Name:");
        addTopicDialog.add(lblTopicName);
        txtTopicName = new JTextField();
        txtTopicName.setFont(new Font(null, Font.PLAIN, 18));
        addTopicDialog.add(txtTopicName);

        JLabel lblFeedBack=new JLabel("FeedBack:");
        addTopicDialog.add(lblFeedBack);
        txtFeedBack=new JTextArea();
        txtFeedBack.setLineWrap(true);
        txtFeedBack.setFont(new Font(null,Font.PLAIN,18));
        addTopicDialog.add(txtFeedBack);

         btnSaveTopic = new JButton("Save");
        btnSaveTopic.setBackground(new Color(64, 92, 172));
        btnSaveTopic.setFocusable(false);
        btnSaveTopic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnSaveTopic.addActionListener(this);
        addTopicDialog.add(btnSaveTopic);

        btnCancelTopic = new JButton("Cancel");
        btnCancelTopic.setBackground(new Color(64, 92, 172));
        btnCancelTopic.setFocusable(false);
        btnCancelTopic.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btnCancelTopic.addActionListener(e -> addTopicDialog.dispose());
        addTopicDialog.add(btnCancelTopic);

        addTopicDialog.setLocationRelativeTo(this);
        addTopicDialog.setVisible(true);
    }

    private void saveTopicToDb(String className, String topicName,String feedback) {
        Connection conn = null;
        PreparedStatement psmt = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSKEY);
            String insertQuery = "INSERT INTO tbl_topics(class_name, topic_name,feedback) VALUES(?, ?,?)";
            psmt = conn.prepareStatement(insertQuery);
            psmt.setString(1, className);
            psmt.setString(2, topicName);
            psmt.setString(3,feedback);

            int rowsInserted = psmt.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Topic and Feedback Added Successfully", "Info", JOptionPane.INFORMATION_MESSAGE);
                addTopicDialog.dispose();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (psmt != null) psmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    private void loadClassesForTopic() {
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet resultSet = null;

        try {
            conn = DriverManager.getConnection(URL, USER, PASSKEY);
            String selectQuery = "SELECT class_name FROM tbl_classes";
            psmt = conn.prepareStatement(selectQuery);
            resultSet = psmt.executeQuery();

            list.clear();

            while (resultSet.next()) {
                String className = resultSet.getString("class_name");
                list.addElement(className);
            }


            // Show the class selection dialog

            int option = JOptionPane.showConfirmDialog(this, scrollPane, "Select Class to Add Topic", JOptionPane.OK_CANCEL_OPTION);

            if (option == JOptionPane.OK_OPTION) {
                String selectedClass = classList.getSelectedValue();
                if (selectedClass != null) {
                    showAddTopicDialog();
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a class", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (psmt != null) psmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void loadTopics() {
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet resultSet=null;



        try{
            conn=DriverManager.getConnection(URL,USER,PASSKEY);
            String selectQuery="SELECT class_name,topic_name FROM tbl_topics ";
            psmt=conn.prepareStatement(selectQuery);

            resultSet=psmt.executeQuery();

            list2.clear();

            boolean hasTopics=false;
             while(resultSet.next()){
                 hasTopics=true;
                String topicEntry=resultSet.getString("class_name")+":"+resultSet.getString("topic_name");
                list2.addElement(topicEntry);

             }

             if(!hasTopics){
                 panel5.add(noTopicsLabel);
                 panel5.remove(scrollPane2);
             }else {

                 panel5.add(scrollPane2);
                 panel5.remove(noTopicsLabel);
             }

            panel5.revalidate();
            panel5.repaint();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (psmt != null) psmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void loadFeedback(){
        Connection conn=null;
        PreparedStatement psmt=null;
        ResultSet resultSet=null;

       try{
           conn=DriverManager.getConnection(URL,USER,PASSKEY);
           String selectQuery=("SELECT topic_name,feedback FROM tbl_topics");
           psmt=conn.prepareStatement(selectQuery);
           resultSet=psmt.executeQuery();

           list3.clear();

           boolean hasFeedback=false;
           while(resultSet.next()){
               hasFeedback=true;
               String feedEntry=resultSet.getString("topic_name")+":"+resultSet.getString("feedback");
               list3.addElement(feedEntry);

           }

           if(!hasFeedback){
               panel5.add(noFeedLabel);
               panel5.remove(scrollPane3);
           }else {

               panel5.add(scrollPane3);
               panel5.remove(noFeedLabel);
           }

           panel5.revalidate();
           panel5.repaint();




       } catch (SQLException e) {
           throw new RuntimeException(e);
       }finally {
           // Close resources
           try {
               if (resultSet != null) resultSet.close();
               if (psmt != null) psmt.close();
               if (conn != null) conn.close();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
    }




    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==btn1){
            showAddClassDialog();

        }

        if(e.getSource()==btnCancel){
            addClassDialog.dispose();
        }

        if (e.getSource()==btnSave) {

            String instructor=txtClassInstructor.getText();
            String className=txtClassName.getText();

            if(!instructor.isEmpty() && !className.isEmpty()){
                saveClassesDbms(instructor,className);
                list.addElement(instructor+":"+className);

                txtClassInstructor.setText("");
                txtClassName.setText("");


            }else {
                JOptionPane.showMessageDialog(this,"All fields are required","Error",JOptionPane.ERROR_MESSAGE);
            }
        }



        if (e.getSource() == btn2) {
            loadClassesForTopic(); // Load classes for topic addition
        }

        if (e.getSource() == btnSaveTopic) {
            String topicName = txtTopicName.getText();
            String selectedClass = classList.getSelectedValue(); // Get the selected class from the list
            String feedback=txtFeedBack.getText();
            if (!topicName.isEmpty() && selectedClass != null && !feedback.isEmpty()) {
                saveTopicToDb(selectedClass, topicName,feedback);
                txtTopicName.setText("");
                txtFeedBack.setText("");// Clear the text field
            } else {
                JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }




    }




    @Override
    public void mouseClicked(MouseEvent e) {
        JLabel sourceLabel = (JLabel) e.getSource();

        if (sourceLabel == homeLabel) {
            panel5.removeAll();
            panel5.add(cardPanel1);
            panel5.add(cardPanel2);
            panel5.revalidate();
            panel5.repaint();
        } else if (sourceLabel == classLabel) {
            panel5.removeAll();
            panel5.setLayout(new FlowLayout());
            JLabel panel5Lbl = new JLabel("Saved Classes");
            panel5Lbl.setForeground(new Color(72, 164, 76));
            panel5Lbl.setPreferredSize(new Dimension(450, 40));
            panel5Lbl.setFont(new Font("Sans Serif", Font.BOLD, 20));

            panel5.add(panel5Lbl);
            loadClasses(); // Load classes when the class label is clicked

            // Refresh the panel to show the updated content
            panel5.revalidate();
            panel5.repaint();
        }else if(sourceLabel==settingLabel){
            panel5.removeAll();
            panel5.setLayout(new FlowLayout());
            JLabel panel6Lbl = new JLabel("Saved Topics");
            panel6Lbl.setForeground(new Color(72, 164, 76));
            panel6Lbl.setPreferredSize(new Dimension(450, 40));
            panel6Lbl.setFont(new Font("Sans Serif", Font.BOLD, 20));
            panel5.add(panel6Lbl);

            loadTopics();

            panel5.revalidate();
            panel5.repaint();

        }else if(sourceLabel==commentLabel){
            panel5.removeAll();
            panel5.setLayout(new FlowLayout());
            JLabel panel7Lbl = new JLabel("Saved Feedback");
            panel7Lbl.setForeground(new Color(72, 164, 76));
            panel7Lbl.setPreferredSize(new Dimension(450, 40));
            panel7Lbl.setFont(new Font("Sans Serif", Font.BOLD, 20));
            panel5.add(panel7Lbl);


            loadFeedback();

            panel5.revalidate();
            panel5.repaint();
        }
        else if (sourceLabel == logLabel) {
            JOptionPane.showMessageDialog(this, "You are logged out", "Info", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
            new Login();
        } else if (sourceLabel == label2) {
            showAddClassDialog();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {




    }

    @Override
    public void mouseReleased(MouseEvent e) {







    }

    @Override
    public void mouseEntered(MouseEvent e) {
        label2.setForeground(new Color(72,164,76));
        homeLabel.setForeground(new Color(72,164,76));
        classLabel.setForeground(new Color(72,164,76));
        commentLabel.setForeground(new Color(72,164,76));
        settingLabel.setForeground(new Color(72,164,76));
        logLabel.setForeground(new Color(72,164,76));

    }

    @Override
    public void mouseExited(MouseEvent e) {
        homeLabel.setForeground(Color.WHITE);
        classLabel.setForeground(Color.WHITE);
        commentLabel.setForeground(Color.WHITE);
        settingLabel.setForeground(Color.WHITE);
        logLabel.setForeground(Color.WHITE);
        label2.setForeground(Color.BLACK);
    }
}
