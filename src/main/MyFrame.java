/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Cofa
 */
public class MyFrame extends javax.swing.JFrame {

    DefaultTableModel model;

    List<Employees> emplyeeList = null;

    public MyFrame() {
        initComponents();
        getEmployeesList();
    }

    public void insertEmployee() {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employees emp = new Employees();

            emp.setName(jTextField_name.getText());
            emp.setAddress(jTextField_address.getText());
            emp.setAge(Integer.parseInt(jTextField_age.getText()));
            emp.setSalary(Double.parseDouble(jTextField_salary.getText()));

            session.persist(emp);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();

        }
    }

    public void deleteEmployee() {
        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employees emp = new Employees();

            emp.setEmployee_id(Integer.parseInt(jTextField_id.getText()));

            session.delete(emp);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();

        }
    }

    public void updateEmployee() {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Employees emp = new Employees();

            emp.setEmployee_id(Integer.parseInt(jTextField_id.getText()));
            emp.setName(jTextField_name.getText());
            emp.setAddress(jTextField_address.getText());
            emp.setAge(Integer.parseInt(jTextField_age.getText()));
            emp.setSalary(Double.parseDouble(jTextField_salary.getText()));

            session.update(emp);

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();

        }
    }

    //ovim sam nasao listu i napunio redove
    public List<Employees> getEmployeesList() {

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        String hql = "from Employees";
        Query query = session.createQuery(hql);

        try {
            tx = session.beginTransaction();

            emplyeeList = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();

        }
        model = (DefaultTableModel) jTable1.getModel();

        for (Employees zaposlenik : emplyeeList) {

            Object[] row = new Object[5];

            row[0] = zaposlenik.getEmployee_id();
            row[1] = zaposlenik.getName();
            row[2] = zaposlenik.getAddress();
            row[3] = zaposlenik.getAge();
            row[4] = zaposlenik.getSalary();

            model.addRow(row);

        }
        return emplyeeList;
    }

    // refresh table
    public void refreshTable() {

        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        getEmployeesList();

    }

    public void filterEmployee() {

        model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        Session session = HibernateUtil.createSessionFactory().openSession();
        Transaction tx = null;

        //definisem upit
        String hql = "FROM Employees AS e WHERE e.name= :name or e.address= :address or e.age= :age or e.salary= :salary";
        Query query = session.createQuery(hql);

        //postavljam parametre u upit
        query.setParameter("name", jTextField_name.getText());

        query.setParameter("address", jTextField_address.getText());

        if (jTextField_age.getText().equals("")) {
            query.setParameter("age", 0);
        } else {
            query.setParameter("age", Integer.parseInt(jTextField_age.getText()));
        }

        if (jTextField_salary.getText().equals("")) {
            query.setParameter("salary", 0.1);
        } else {
            query.setParameter("salary", Double.parseDouble(jTextField_salary.getText()));
        }

        try {
            tx = session.beginTransaction();

            emplyeeList = query.list();

            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println(e);
        } finally {
            HibernateUtil.close();

        }
        model = (DefaultTableModel) jTable1.getModel();

        for (Employees zaposlenik : emplyeeList) {

            Object[] row = new Object[5];

            row[0] = zaposlenik.getEmployee_id();
            row[1] = zaposlenik.getName();
            row[2] = zaposlenik.getAddress();
            row[3] = zaposlenik.getAge();
            row[4] = zaposlenik.getSalary();

            model.addRow(row);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        filter = new javax.swing.JButton();
        showall = new javax.swing.JButton();
        add = new javax.swing.JButton();
        update = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        jTextField_id = new javax.swing.JTextField();
        jTextField_name = new javax.swing.JTextField();
        jTextField_address = new javax.swing.JTextField();
        jTextField_age = new javax.swing.JTextField();
        jTextField_salary = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Employee_id", "Name", "Address", "Age", "Salary"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        filter.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        filter.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cofa\\Downloads\\iconfinder_Search_icon_2541673 (2).png")); // NOI18N
        filter.setText("Filter Employee");
        filter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        filter.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        filter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                filterActionPerformed(evt);
            }
        });

        showall.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        showall.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cofa\\Downloads\\icons8-show-property-32.png")); // NOI18N
        showall.setText("Reset Filter");
        showall.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        showall.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showallActionPerformed(evt);
            }
        });

        add.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        add.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cofa\\Downloads\\iconfinder_flat-style-circle-add_1312548 (2).png")); // NOI18N
        add.setText("Add Employee");
        add.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        add.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        update.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        update.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cofa\\Downloads\\icons8-restart-32.png")); // NOI18N
        update.setText("Update Data");
        update.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        delete.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N
        delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\Cofa\\Downloads\\iconfinder_error_1646012 (2).png")); // NOI18N
        delete.setText("Delete");
        delete.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        jTextField_id.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jTextField_name.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jTextField_address.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jTextField_age.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jTextField_salary.setFont(new java.awt.Font("Calibri", 1, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel2.setText("Name:");

        jLabel3.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel3.setText("Address:");

        jLabel4.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel4.setText("Age:");

        jLabel5.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jLabel5.setText("Salary:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(45, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(76, 76, 76)
                        .addComponent(jTextField_address))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(94, 94, 94)
                        .addComponent(jTextField_name))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(125, 125, 125)
                        .addComponent(jTextField_id))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(filter, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(67, 67, 67)
                        .addComponent(showall, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(93, 93, 93)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_age)
                            .addComponent(jTextField_salary))))
                .addGap(78, 78, 78)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(add)
                        .addGap(96, 96, 96)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(102, 102, 102)
                        .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_name, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_age, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField_salary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(delete)
                    .addComponent(add)
                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(showall)
                    .addComponent(filter))
                .addGap(40, 40, 40))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jTextField_address, jTextField_age, jTextField_id, jTextField_name, jTextField_salary});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void filterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_filterActionPerformed
        filterEmployee();
    }//GEN-LAST:event_filterActionPerformed

    private void showallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showallActionPerformed
        refreshTable();
    }//GEN-LAST:event_showallActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        insertEmployee();
        refreshTable();
    }//GEN-LAST:event_addActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        updateEmployee();
        refreshTable();
    }//GEN-LAST:event_updateActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        deleteEmployee();
        refreshTable();
    }//GEN-LAST:event_deleteActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MyFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MyFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton delete;
    private javax.swing.JButton filter;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField_address;
    private javax.swing.JTextField jTextField_age;
    private javax.swing.JTextField jTextField_id;
    private javax.swing.JTextField jTextField_name;
    private javax.swing.JTextField jTextField_salary;
    private javax.swing.JButton showall;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
