package university.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.ResultSet;
import javax.swing.*;

public class Student_Leave extends JFrame implements ActionListener
{

    Choice choiceRollNo, choTime;

    JTextField selDate;
    
    JButton submit, cancel;

    Student_Leave()
    {

        getContentPane().setBackground(new Color(210,232,252));

        JLabel heading = new JLabel("Apply Leave (Student)");
        heading.setBounds(40,50,300,30);
        heading.setFont(new Font("Tahoma", Font.BOLD,20));
        add(heading);

        JLabel RollNoSE = new JLabel("Search by Roll Number");
        RollNoSE.setBounds(60,100,200,20);
        RollNoSE.setFont(new Font("Tahoma", Font.PLAIN,18));
        add(RollNoSE);

        choiceRollNo = new Choice();
        choiceRollNo.setBounds(60,130,200,20);
        add(choiceRollNo);

        try
        {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while (resultSet.next())
            {
                choiceRollNo.add(resultSet.getString("rollno"));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60,180,200,20);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN,18));
        add(lbldate);

        selDate = new JTextField("dd/MM/yyyy");
        selDate.setBounds(60, 210, 200, 25);
        selDate.setForeground(new Color(105, 105, 105));
        add(selDate);

        selDate.addFocusListener(new FocusAdapter()
        {
            public void focusGained(FocusEvent e)
            {
                if (selDate.getText().equals("dd/MM/yyyy"))
                {
                    selDate.setText("");
                }
            }

            public void focusLost(FocusEvent e)
            {
                if (selDate.getText().isEmpty())
                {
                    selDate.setText("dd/MM/yyyy");
                }
            }
        });

        JLabel time = new JLabel("Time Duration");
        time.setBounds(60,260,200,20);
        time.setFont(new Font("Tahoma", Font.PLAIN,18));
        add(time);

        choTime = new Choice();
        choTime.setBounds(60,290,200,20);
        choTime.add("Full Day");
        choTime.add("Half Day");
        add(choTime);

        submit = new JButton("Submit");
        submit.setBounds(60,350,100,25);
        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        submit.addActionListener(this);
        add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(200,350,100,25);
        cancel.setBackground(Color.black);
        cancel.setForeground(Color.white);
        cancel.addActionListener(this);
        add(cancel);

        setSize(500,550);
        setLocation(550,100);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == submit){
            String rollno = choiceRollNo.getSelectedItem();
            String date = selDate.getText();
            String time = choTime.getSelectedItem();

            String Q = "insert into studentleave values('"+rollno+"','"+date+"','"+time+"')";
            try
            {
                Conn c = new Conn();
                c.statement.executeUpdate(Q);
                JOptionPane.showMessageDialog(null, "Leave Confirmed");
                setVisible(false);
            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args)
    {
        new Student_Leave();
    }
}