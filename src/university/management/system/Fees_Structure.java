package university.management.system;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Fees_Structure extends JFrame implements ActionListener
{

    JButton back;

    Fees_Structure()
    {

        setTitle("Fees Structure");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Fees Structure");
        heading.setBounds(400,10,400,30);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        add(heading);

        back = new JButton("Back");
        back.setBounds(850, 15, 100, 30);
        back.addActionListener(this);
        add(back);

        JTable table = new JTable();

        try
        {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from fee");
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

        JScrollPane js = new JScrollPane(table);
        js.setBounds(0,60,1000,700);
        add(js);

        setSize(1000,700);
        setLocation(250,50);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == back) {
            dispose();   // closes Fees_Structure window
        }
    }
    public static void main(String[] args)
    {
        new Fees_Structure();
    }
}