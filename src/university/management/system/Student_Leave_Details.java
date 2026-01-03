package university.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Student_Leave_Details extends JFrame implements ActionListener {

    Choice choiceEmpID;

    JTable table;

    JButton search ,cancel,print;

    Student_Leave_Details()
    {

        getContentPane().setBackground(new Color(250,172,206));

        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20,20,150,20);
        add(heading);

        choiceEmpID = new Choice();
        choiceEmpID.setBounds(180,20,150,20);
        add(choiceEmpID);

        try
        {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from student");
            while (resultSet.next())
            {
                choiceEmpID.add(resultSet.getString("rollno"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        table = new JTable();
        try
        {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from studentleave");
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++)
            {
                model.addColumn(metaData.getColumnName(i));
            }
            while(resultSet.next())
            {
                Object[] row = new Object[columnCount];
                for(int i = 1; i <= columnCount; i++)
                {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
            table.setModel(model);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,900,600);
        add(scrollPane);


        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        cancel = new JButton("Cancel");
        cancel.setBounds(220,70,80,20);
        cancel.addActionListener(this);
        add(cancel);

        setSize(900,700);
        setLocation(300,100);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == search){
            String q = "select * from studentleave where rollno = '"+choiceEmpID.getSelectedItem()+"'";
            try
            {
                Conn c = new Conn();
                ResultSet resultSet = c.statement.executeQuery(q);
                DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            for(int i = 1; i <= columnCount; i++)
            {
                model.addColumn(metaData.getColumnName(i));
            }
            while(resultSet.next())
            {
                Object[] row = new Object[columnCount];
                for(int i = 1; i <= columnCount; i++)
                {
                    row[i - 1] = resultSet.getObject(i);
                }
                model.addRow(row);
            }
            table.setModel(model);
            }
            catch (Exception E)
            {
                E.printStackTrace();
            }
        }
        else if (e.getSource() == print)
        {
            try
            {
                table.print();
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
        new Student_Leave_Details();
    }
}