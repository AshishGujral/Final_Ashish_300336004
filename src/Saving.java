import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import net.miginfocom.swing.*;
/*
 * Created by JFormDesigner on Fri Aug 13 16:03:09 PDT 2021
 */



/**
 * @author unknown
 */
public class Saving extends JFrame {
    // create array list of saving class
    public static ArrayList<Savings> list1= new ArrayList<Savings>();

    Connection1 con = new Connection1();
    Connection conObj = con.connect();
    public Saving() throws SQLException, ClassNotFoundException {
        initComponents();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        // create object of form class
        // git hub link
        Saving form1 = new Saving();

        // call update table and set visible to true
        form1.updateTable();
        form1.setVisible(true);
    }
    public void updateTable() throws SQLException {

        String quer1 = "Select * from savingstable";
        PreparedStatement query = conObj.prepareStatement(quer1);

        ResultSet rs = query.executeQuery();

        ResultSetMetaData Res = rs.getMetaData();
        // count the column in table
        int c = Res.getColumnCount();
        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        df.setRowCount(0);
        rs.last();
        int z = rs.getRow();
        rs.beforeFirst();

        String[][] array = new String[0][];
        if(z>0) {
            array= new String[z][5];
        }
        // System.out.println(rs.next());
        int j=0;
        while(rs.next()) {
            array[j][0] = rs.getString("custno");
            //  System.out.println(rs.getString("catcode"));
            array[j][1] = rs.getString("custname");
            array[j][2] = rs.getString("cdep");
            array[j][3] = rs.getString("nyears");
            array[j][4] = rs.getString("savtype");
            ++j;

        }

        String[] cols = {"Number", "Name","Deposit","Years","Type of Savings"};

        DefaultTableModel model = new DefaultTableModel(array,cols);
        table1.setModel(model);

        table1.setDefaultEditor(Object.class, null);
    }

    private void btnAddActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        String cusNumber = txtNo.getText();
        String cusName = txtName.getText();
        Double iniDeposit = Double.parseDouble(txtDeposit.getText());
        int years = Integer.parseInt(txtYear.getText());

        String typeSaving = comboBox.getSelectedItem().toString();
        list1.add(new Savings(cusNumber,cusName,iniDeposit,years,typeSaving));

        String query1= "Select * from savingstable where custno =?";
        PreparedStatement query= conObj.prepareStatement(query1);

        query.setString(1,cusNumber);
        ResultSet rs = query.executeQuery();

        if(rs.isBeforeFirst()){
            JOptionPane.showMessageDialog(null," The record is Existing");
            return;
        }
        String query2 =  "Insert into savingstable values (?,?,?,?,?)";
        query= conObj.prepareStatement(query2);
        query.setString(1,cusNumber);
        query.setString(2,cusName);
        query.setString(3, String.valueOf(iniDeposit));
        query.setString(4, String.valueOf(years));
        query.setString(5,typeSaving);
        query.executeUpdate();

        JOptionPane.showMessageDialog(null,"One record Added");
        updateTable();
    }

    private void table1MouseClicked(MouseEvent e) {
        // TODO add your code here
        DefaultTableModel df = (DefaultTableModel) table1.getModel();

        int index1 = table1.getSelectedRow();

        txtNo.setText((df.getValueAt(index1,0).toString()).toString());
        txtName.setText(df.getValueAt(index1,1).toString());
        txtDeposit.setText(df.getValueAt(index1,2).toString());
        txtYear.setText(df.getValueAt(index1,3).toString());
        comboBox.setSelectedItem(df.getValueAt(index1,4).toString());
        DefaultTableModel df1 =(DefaultTableModel) table2.getModel();

       /* String number= (df.getValueAt(index1,0).toString());
        String name=(df.getValueAt(index1,1).toString());
        double des= Double.parseDouble(df.getValueAt(index1,2).toString());
        int y = Integer.parseInt(df.getValueAt(index1,1).toString());
        String combo=(df.getValueAt(index1,1).toString());
        df1.setRowCount(0);
        String[] cols = {"Year", "Starting","Interest","Ending Value"};
        int year =Integer.valueOf(df.getValueAt(index1,3).toString());
        String [][] array= new String[0][];
        array=new String[year-1][];
        String d = df.getValueAt(index1,2).toString();
        double deposit = Double.parseDouble(d);
        if(df.getValueAt(index1,4).toString().equals("Savings-Deluxe")){
            Deluxe de=new Deluxe(number,name,des,y,combo);
            for(int i=0;i<year;i++){
                int sum=i+1;
                double interest = 0;
                array[i][0] = String.valueOf(sum);
                array[i][1] =String.valueOf(deposit+interest);
                interest = de.generateTable();
                array[i][2]=String.valueOf(interest);
                array[i][3]=String.valueOf(deposit+interest);

            }
        }
        if(df.getValueAt(index1,4).toString().equals("Savings-Regular")){
            Regular re=new Regular(number,name,des,y,combo);
            for(int i=0;i<year;i++){
                int sum=i+1;
                double interest = 0;
                array[i][0] = String.valueOf(sum);
                array[i][1] =String.valueOf(deposit+interest);
                interest = re.generateTable();
                array[i][2]=String.valueOf(interest);
                array[i][3]=String.valueOf(deposit+interest);

            }
        }*/
      //  DefaultTableModel model = new DefaultTableModel(array,cols);
        //table2.setModel(model);
        //table2.setDefaultEditor(Object.class, null);

    }

    private void btnEditActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        DefaultTableModel df = (DefaultTableModel) table1.getModel();
        int index1 = table1.getSelectedRow();
        String cusNumber = txtNo.getText();
        String cusName = txtName.getText();
        Double iniDeposit = Double.parseDouble(txtDeposit.getText());
        int years = Integer.parseInt(txtYear.getText());

        String typeSaving = comboBox.getSelectedItem().toString();
        String oldvalue=df.getValueAt(index1,0).toString();
        PreparedStatement query;
        query = conObj.prepareStatement("Update savingstable set custno=?, custname=?,cdep=?, nyears=?,savtype=? where custname = ?");
        query.setString(1, cusNumber);
        query.setString(2, cusName);
        query.setString(3, String.valueOf(iniDeposit));
        query.setString(4, String.valueOf(years));
        query.setString(5, typeSaving);
        query.executeUpdate();
        JOptionPane.showMessageDialog(null, "One record edited ");

        updateTable();

    }

    private void btnDeleteActionPerformed(ActionEvent e) throws SQLException {
        // TODO add your code here
        String cusNumber = txtNo.getText();
        String cusName = txtName.getText();
        Double iniDeposit = Double.parseDouble(txtDeposit.getText());
        int years = Integer.parseInt(txtYear.getText());

        String typeSaving = comboBox.getSelectedItem().toString();
        PreparedStatement query;
        query = conObj.prepareStatement("Delete from savingstable where custname = ?");
        query.setString(1, cusNumber);

        query.executeUpdate();

        JOptionPane.showMessageDialog(null, "One record deleted ");
        updateTable();
    }

    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - unknown
        label1 = new JLabel();
        txtNo = new JTextField();
        label2 = new JLabel();
        txtName = new JTextField();
        label3 = new JLabel();
        txtDeposit = new JTextField();
        label4 = new JLabel();
        txtYear = new JTextField();
        label5 = new JLabel();
        comboBox = new JComboBox<>();
        scrollPane1 = new JScrollPane();
        table1 = new JTable();
        scrollPane2 = new JScrollPane();
        table2 = new JTable();
        btnAdd = new JButton();
        btnEdit = new JButton();
        btnDelete = new JButton();

        //======== this ========
        var contentPane = getContentPane();
        contentPane.setLayout(new MigLayout(
            "hidemode 3",
            // columns
            "[fill]" +
            "[fill]" +
            "[fill]",
            // rows
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]" +
            "[]"));

        //---- label1 ----
        label1.setText("Enter the Customer Number");
        contentPane.add(label1, "cell 0 1");

        //---- txtNo ----
        txtNo.setColumns(28);
        contentPane.add(txtNo, "cell 2 1");

        //---- label2 ----
        label2.setText("Enter the Customer Name");
        contentPane.add(label2, "cell 0 2");
        contentPane.add(txtName, "cell 2 2");

        //---- label3 ----
        label3.setText("Enter the Initial Deposit");
        contentPane.add(label3, "cell 0 3");
        contentPane.add(txtDeposit, "cell 2 3");

        //---- label4 ----
        label4.setText("Enter number of Years");
        contentPane.add(label4, "cell 0 4");
        contentPane.add(txtYear, "cell 2 4");

        //---- label5 ----
        label5.setText("Choose the type of savings");
        contentPane.add(label5, "cell 0 5");

        //---- comboBox ----
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] {
            "Savings-Deluxe",
            "Savings-Regular"
        }));
        contentPane.add(comboBox, "cell 2 5");

        //======== scrollPane1 ========
        {

            //---- table1 ----
            table1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    table1MouseClicked(e);
                }
            });
            scrollPane1.setViewportView(table1);
        }
        contentPane.add(scrollPane1, "cell 0 6");

        //======== scrollPane2 ========
        {
            scrollPane2.setViewportView(table2);
        }
        contentPane.add(scrollPane2, "cell 2 6");

        //---- btnAdd ----
        btnAdd.setText("Add");
        btnAdd.addActionListener(e -> {
            try {
                btnAddActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(btnAdd, "cell 0 7");

        //---- btnEdit ----
        btnEdit.setText("Edit");
        btnEdit.addActionListener(e -> {
            try {
                btnEditActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(btnEdit, "cell 0 7");

        //---- btnDelete ----
        btnDelete.setText("Delete");
        btnDelete.addActionListener(e -> {
            try {
                btnDeleteActionPerformed(e);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
        contentPane.add(btnDelete, "cell 0 7");
        pack();
        setLocationRelativeTo(getOwner());
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - unknown
    private JLabel label1;
    private JTextField txtNo;
    private JLabel label2;
    private JTextField txtName;
    private JLabel label3;
    private JTextField txtDeposit;
    private JLabel label4;
    private JTextField txtYear;
    private JLabel label5;
    private JComboBox<String> comboBox;
    private JScrollPane scrollPane1;
    private JTable table1;
    private JScrollPane scrollPane2;
    private JTable table2;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDelete;
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
