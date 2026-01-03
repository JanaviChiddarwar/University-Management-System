package university.management.system;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Teacher_Details extends JFrame implements ActionListener
{

    Choice choice;

    JTable table;
    
    JButton search,print, update, add, cancel;

    Teacher_Details()
    {

        getContentPane().setBackground(new Color(192,164,252));
        setLayout(null);

        JLabel heading = new JLabel("Search by Employee ID");
        heading.setBounds(20,20,150,20);
        add(heading);

        choice = new Choice();
        choice.setBounds(180,20,150,20);
        add(choice);

        try
        {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
            while (resultSet.next())
            {
                choice.add(resultSet.getString("empId"));
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
            ResultSet resultSet = c.statement.executeQuery("select * from teacher");
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
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setPreferredWidth(130);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        JScrollPane js = new JScrollPane(table);
        js.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        js.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        js.setBounds(0, 100, 2000, 560);
        add(js);

        search = new JButton("Search");
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        add = new JButton("Add");
        add.setBounds(220,70,80,20);
        add.addActionListener(this);
        add(add);

        update = new JButton("Update");
        update.setBounds(320,70,80,20);
        update.addActionListener(this);
        add(update);

        cancel = new JButton("Cancel");
        cancel.setBounds(420,70,80,20);
        cancel.addActionListener(this);
        add(cancel);

        setLayout(null);
        setSize(2000,700);
        setLocation(100,100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == search){
            String q = "select * from teacher where empId = '"+choice.getSelectedItem()+"'";
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
                table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
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
        else if (e.getSource() == add)
        {
            setVisible(false);
            new Add_Faculty();
        }
        else if (e.getSource() == update)
        {
            setVisible(false);
            new Update_Student();
        }
        else
        {
            setVisible(false);
        }
    }

    public static void main(String[] args)
    {
        new Teacher_Details();
    }
}