/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.security.auth.callback.ConfirmationCallback;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author oshan
 */
public class P2P extends javax.swing.JFrame {

  Connection conn=null;
  PreparedStatement pst,pst1,pst2,pst3=null;
  ResultSet rs,rs1,rs2,rs3=null;
    
    public P2P() {
        initComponents();
        conn=traderdb.connect();
        //buytableLoad();
        buytableLoad();
        selltableLoad();
        setAvg();
        deleteAllBtn.setVisible(false);
        //selectedRow();
    }

    public void buytableLoad(){
    
    
        try {
            
            String sql="SELECT * from buy";
            pst =conn.prepareStatement(sql);
            rs = pst.executeQuery();
            buytable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
        } catch (Exception e) {
            
            JOptionPane.showMessageDialog(null,e);
            
        }
        
    
    }
    
    public void selltableLoad(){
        
        
    
        try {
            
            String sql;
            
            
            sql="SELECT * from sell";
                  
            pst =conn.prepareStatement(sql);
            rs = pst.executeQuery();
            selltable.setModel(DbUtils.resultSetToTableModel(rs));
            
            
            
        } catch (SQLException e) {
            
            JOptionPane.showMessageDialog(null,e);
            
        }
        
    
    }

    public void insert(){
    
        SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        String date=d.format(datebox.getDate());
        String dealer=dealerbox.getText();
        String dd=usdtbox.getText();
        double USDT=Double.parseDouble(dd);
        double LKR=Double.parseDouble(lkrbox.getText());
        double rate=Double.parseDouble(ratebox.getText());
        String note=notebox.getText();
        
        try{
            
            //INSERT INTO buy(Date,DEaler,USDT,LKR,Rate,Note) VALUES('"+date+"','"+dealer+"','"+USDT"','"+LKR+"','"+rate+"','"+note+"')
            //String sql= "INSERT INTO buy(Date,DEaler,USDT,LKR,Rate,Note) VALUES('"+date+"','"+dealer+"','"+USDT"','"+LKR+"','"+rate+"','"+note+"')";
            String sql;
            //var x;
            int x=tabs.getSelectedIndex();
            //System.out.println(x);
            sql = switch (x) {
                case 0 -> " INSERT INTO buy(Date,DEaler,USDT,LKR,Rate,Note) VALUES ('"+date+"','"+dealer+"',"+USDT+","+LKR+","+rate+",'"+note+"')";
                case 1 -> " INSERT INTO sell(Date,DEaler,USDT,LKR,Rate,Note) VALUES ('"+date+"','"+dealer+"',"+USDT+","+LKR+","+rate+",'"+note+"')";
                default -> " ";
            };
            pst=conn.prepareStatement(sql);
            pst.execute();
        }
        catch(SQLException e) {
            
            JOptionPane.showMessageDialog(null,e);
        
    
    }
    }
    
    public void selectedRowbuy(){
        
        
        int r=buytable.getSelectedRow();
        
        try {
            
            DefaultTableModel model=(DefaultTableModel)buytable.getModel();
            datebox.setDate((Date) model.getValueAt(r, 1));
        } catch (Exception ex) {
            Logger.getLogger(P2P.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        
        String dealer=buytable.getValueAt(r,2).toString();
        double USDT=Double.parseDouble( buytable.getValueAt(r, 3).toString());
        double LKR= Double.parseDouble(buytable.getValueAt( r,4).toString());
        double rate= Double.parseDouble(buytable.getValueAt( r,5).toString());
        String note = buytable.getValueAt(r,6).toString();
        String id=buytable.getValueAt(r, 0).toString();
        
        idlabel.setText(id);
        notebox.setText(note);
        dealerbox.setText(dealer);
        usdtbox.setText(String.valueOf(USDT));
        lkrbox.setText(String.valueOf(LKR));
        ratebox.setText(String.valueOf(rate));
        
        
    
    }
    
    public void selectedRowsell(){
        
       int r=selltable.getSelectedRow();
        
        try {
            
            DefaultTableModel model=(DefaultTableModel)selltable.getModel();
            datebox.setDate((Date) model.getValueAt(r, 1));
        } catch (Exception ex) {
            Logger.getLogger(P2P.class.getName()).log(Level.SEVERE, null, ex);
            //JOptionPane.showMessageDialog(null, ex);
        }
        
        
        String dealer=selltable.getValueAt(r,2).toString();
        double USDT=Double.parseDouble( selltable.getValueAt(r, 3).toString());
        double LKR= Double.parseDouble(selltable.getValueAt( r,4).toString());
        double rate= Double.parseDouble(selltable.getValueAt( r,5).toString());
        String note = selltable.getValueAt(r,6).toString();
         String id=selltable.getValueAt(r, 0).toString();
        
        idlabel.setText(id);
        
        notebox.setText(note);
        dealerbox.setText(dealer);
        usdtbox.setText(String.valueOf(USDT));
        lkrbox.setText(String.valueOf(LKR));
        ratebox.setText(String.valueOf(rate));
        
        
        
    
    }
     
    public void clear(){
     
        datebox.setDate(null);
        notebox.setText("");
        dealerbox.setText("");
        usdtbox.setText(String.valueOf(""));
        lkrbox.setText(String.valueOf(""));
        ratebox.setText(String.valueOf(""));
     }
    
    public void update(){
    
         SimpleDateFormat d=new SimpleDateFormat("yyyy-MM-dd");
        String date=d.format(datebox.getDate());
        String dealer=dealerbox.getText();
        String dd=usdtbox.getText();
        double USDT=Double.parseDouble(dd);
        double LKR=Double.parseDouble(lkrbox.getText());
        double rate=Double.parseDouble(ratebox.getText());
        String note=notebox.getText();
        String id=idlabel.getText();
        try{
            
            
            String sql;
           
            int x=tabs.getSelectedIndex();
         
            sql = switch (x) {
                case 0 -> " UPDATE buy SET Date ='"+date+"' , Dealer ='"+dealer+"'  ,USDT='"+USDT+"',LKR='"+LKR+"',Rate='"+rate+"',Note='"+note+"' WHERE id='"+id+"' ";
                case 1 -> " UPDATE sell SET Date ='"+date+"' , Dealer ='"+dealer+"'  ,USDT='"+USDT+"',LKR='"+LKR+"',Rate='"+rate+"',Note='"+note+"' WHERE id='"+id+"' ";
                default -> " ";
            };
            pst=conn.prepareStatement(sql);
            pst.execute();
              JOptionPane.showMessageDialog(this, "Updated.!", "Update record", JOptionPane.WARNING_MESSAGE);
        }
        catch(SQLException e) {
            
            JOptionPane.showMessageDialog(this, "Execute Update  Failed ", "Failed", HEIGHT);
        
    
    }
        catch(NullPointerException e){
        
                JOptionPane.showMessageDialog(this, "Fill Required fiedls", "Update Failed", ERROR);
        
        }
    
    }
    
    public void delete(){
    
        int i=tabs.getSelectedIndex();
        System.out.println("tab="+i);
        String k;
        if(i==0){
        
            k="buy";
            
            
            
        }
        else{
            k="sell";
            
        }
        String id=idlabel.getText();
        
        
        
        String sql="DELETE FROM "+k+" WHERE id='"+id+"'";
        
        try {
            
            conn.prepareStatement(sql).execute();
            JOptionPane.showMessageDialog(null,"Deleted.!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    
    }
    
    public void deleteAll(){
        
        
       int r= JOptionPane.showConfirmDialog(this, "Are you sure", "Delete All", ConfirmationCallback.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
       
   
        if(r==0){
        
    int i=tabs.getSelectedIndex();
        System.out.println("tab="+i);
        String k;
        if(i==0){
        
            k="buy";
            
            
            
        }
        else{
            k="sell";
            
        }
        
        
        
        
        String sql="DELETE FROM "+k;
        
        try {
            
            conn.prepareStatement(sql).execute();
            JOptionPane.showMessageDialog(null,"Deleted.!");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        }
    }
    
    public void setAvg(){
    
       
       
        Double buyLKRSum,sellLKRSum,buyUSDTSum,sellUSDTSum;
        String buy,sell;
        
        String sqlavgbuy="SELECT SUM(LKR)/SUM(USDT) FROM buy";
        String sqlavgsell="SELECT SUM(LKR)/SUM(USDT) FROM sell";
         String sqlbuyLKRSum="SELECT SUM(LKR) FROM buy";
        String sqlsellLKRSum="SELECT SUM(LKR) FROM sell";
        
        try {
            pst=conn.prepareStatement(sqlavgbuy);
            rs= pst.executeQuery();
            
            pst1=conn.prepareStatement(sqlavgsell);
            rs1= pst1.executeQuery();
            
            pst2=conn.prepareStatement(sqlbuyLKRSum);
            rs2= pst2.executeQuery();
            
            pst3=conn.prepareStatement(sqlsellLKRSum);
            rs3= pst3.executeQuery();
            
            while(rs.next()){
            
                Double buy1=rs.getDouble(1);
                System.out.println(buy1);
                buy=buy1.toString();
                 avgBuy.setText("BUY = "+buy);
            }
            
            while(rs1.next()){
            
                Double sell1=rs1.getDouble(1);
                System.out.println(sell1);
                sell=sell1.toString();
                 avgSell.setText("SELL = "+sell);
            }
            while(rs2.next() && rs3.next()){
            
                buyLKRSum=rs2.getDouble(1);
                sellLKRSum=rs3.getDouble(1);
                
                Double prof=sellLKRSum-buyLKRSum;
                
                System.out.println(prof);
                String profit=prof.toString();
                plLabel.setText(profit);
            }
            
            
            
            
          
        } catch (SQLException e) {
            
             JOptionPane.showMessageDialog(this, e, "Error", HIDE_ON_CLOSE);
        }
        
        
        
       
    
    }
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        main = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        idlabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        exitBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        insertBtn = new javax.swing.JButton();
        deleteBtn = new javax.swing.JButton();
        datebox = new com.toedter.calendar.JDateChooser();
        notebox = new javax.swing.JTextField();
        ratebox = new javax.swing.JTextField();
        lkrbox = new javax.swing.JTextField();
        usdtbox = new javax.swing.JTextField();
        dealerbox = new javax.swing.JTextField();
        searchBox = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        deleteAllBtn = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        tabs = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        buytable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        selltable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        avgBuy = new javax.swing.JLabel();
        avgSell = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        plLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        main.setBackground(new java.awt.Color(51, 51, 51));
        main.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(153, 153, 0));
        jPanel1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("ID : ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 30, -1));
        jPanel1.add(idlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 50, 20));

        jLabel2.setText("Note");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, -1, -1));

        jLabel3.setText("Date");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        jLabel4.setText("Dealer ID");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, -1));

        jLabel5.setText("USDT");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, -1, -1));

        jLabel6.setText("LKR");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, -1));

        jLabel7.setText("Rate");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, -1));

        exitBtn.setText("Exit");
        exitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitBtnActionPerformed(evt);
            }
        });
        jPanel1.add(exitBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 470, -1, -1));

        updateBtn.setText("Update");
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 390, -1, -1));

        insertBtn.setText("Insert");
        insertBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertBtnActionPerformed(evt);
            }
        });
        jPanel1.add(insertBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, -1, -1));

        deleteBtn.setText("Delete");
        deleteBtn.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                deleteBtnMouseMoved(evt);
            }
        });
        deleteBtn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                deleteBtnFocusGained(evt);
            }
        });
        deleteBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteBtnMouseExited(evt);
            }
        });
        deleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 390, -1, -1));

        datebox.setDateFormatString("yyyy MM dd");
        jPanel1.add(datebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 60, 210, -1));

        notebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noteboxActionPerformed(evt);
            }
        });
        jPanel1.add(notebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 250, 210, -1));

        ratebox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rateboxActionPerformed(evt);
            }
        });
        jPanel1.add(ratebox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 210, -1));

        lkrbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lkrboxActionPerformed(evt);
            }
        });
        jPanel1.add(lkrbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 210, -1));

        usdtbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usdtboxActionPerformed(evt);
            }
        });
        jPanel1.add(usdtbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 210, -1));
        jPanel1.add(dealerbox, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, 210, -1));

        searchBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchBoxKeyReleased(evt);
            }
        });
        jPanel1.add(searchBox, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 330, 290, -1));

        jLabel8.setText("Search");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 300, -1, -1));

        deleteAllBtn.setText("Delete All");
        deleteAllBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                deleteAllBtnMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                deleteAllBtnMouseExited(evt);
            }
        });
        deleteAllBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllBtnActionPerformed(evt);
            }
        });
        jPanel1.add(deleteAllBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 410, 90, 30));

        main.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 350, 540));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabs.setBackground(new java.awt.Color(0, 153, 153));

        jPanel3.setBackground(new java.awt.Color(51, 204, 0));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buytable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        buytable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                buytableMouseClicked(evt);
            }
        });
        buytable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buytableKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(buytable);

        jPanel3.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 270));

        tabs.addTab("BUY", jPanel3);

        jPanel4.setBackground(new java.awt.Color(153, 0, 51));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        selltable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        selltable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selltableMouseClicked(evt);
            }
        });
        selltable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                selltableKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(selltable);

        jPanel4.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 270));

        tabs.addTab("SELL", jPanel4);

        jPanel2.add(tabs, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 680, 310));

        jPanel5.setBackground(new java.awt.Color(0, 102, 102));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        avgBuy.setText("BUY");
        jPanel5.add(avgBuy, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 60, 100, -1));

        avgSell.setText("SELL");
        jPanel5.add(avgSell, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, 90, -1));

        jLabel9.setText("Average prices");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 90, -1));

        jLabel10.setText("Profit/Loss");
        jPanel5.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 70, -1, -1));

        plLabel.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));
        jPanel5.add(plLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 120, 40));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 680, 190));

        main.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 0, 690, 510));

        getContentPane().add(main, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 510));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitBtnActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitBtnActionPerformed

    private void noteboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noteboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_noteboxActionPerformed

    private void rateboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rateboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rateboxActionPerformed

    private void lkrboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lkrboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lkrboxActionPerformed

    private void usdtboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usdtboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usdtboxActionPerformed

    private void insertBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertBtnActionPerformed
        // TODO add your handling code here:
        insert();
        buytableLoad();
        selltableLoad();
       clear();
       setAvg();
    }//GEN-LAST:event_insertBtnActionPerformed

    private void selltableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selltableMouseClicked
        // TODO add your handling code here:
         selectedRowsell();
    }//GEN-LAST:event_selltableMouseClicked

    private void selltableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_selltableKeyReleased
        // TODO add your handling code here:
        selectedRowsell();
    }//GEN-LAST:event_selltableKeyReleased

    private void buytableKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buytableKeyReleased
        // TODO add your handling code here:
        selectedRowbuy();
    }//GEN-LAST:event_buytableKeyReleased

    private void buytableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buytableMouseClicked
        // TODO add your handling code here:
         selectedRowbuy();
    }//GEN-LAST:event_buytableMouseClicked

    private void searchBoxKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchBoxKeyReleased
        // TODO add your handling code here:
        
        String s=searchBox.getText();
        String sql="SELECT * from buy";
        
      int[] selectedRows = buytable.getSelectedRows();
        System.out.println(selectedRows[0]);
      
        
        
    }//GEN-LAST:event_searchBoxKeyReleased

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        // TODO add your handling code here:
        update();
        buytableLoad();
        selltableLoad();
        setAvg();
        clear();
    }//GEN-LAST:event_updateBtnActionPerformed

    private void deleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBtnActionPerformed
        // TODO add your handling code here:
        delete();
        buytableLoad();
        selltableLoad();
        setAvg();
        clear();
    }//GEN-LAST:event_deleteBtnActionPerformed

    private void deleteBtnFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_deleteBtnFocusGained
        // TODO add your handling code here:
        //System.out.println("focusing btn delete");
    }//GEN-LAST:event_deleteBtnFocusGained

    private void deleteBtnMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseMoved
        // TODO add your handling code here:
        //System.out.println("focusing btn delete");
       
    }//GEN-LAST:event_deleteBtnMouseMoved

    private void deleteBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseEntered
        // TODO add your handling code here:
         deleteAllBtn.setVisible(true);
    }//GEN-LAST:event_deleteBtnMouseEntered

    private void deleteBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteBtnMouseExited
        // TODO add your handling code here:
         deleteAllBtn.setVisible(false);
    }//GEN-LAST:event_deleteBtnMouseExited

    private void deleteAllBtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteAllBtnMouseEntered
        // TODO add your handling code here:
         deleteAllBtn.setVisible(true);
    }//GEN-LAST:event_deleteAllBtnMouseEntered

    private void deleteAllBtnMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteAllBtnMouseExited
        // TODO add your handling code here:
                 deleteAllBtn.setVisible(false);

    }//GEN-LAST:event_deleteAllBtnMouseExited

    private void deleteAllBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllBtnActionPerformed
        // TODO add your handling code here:
        deleteAll();
         buytableLoad();
        selltableLoad();
        setAvg();
        clear();
    }//GEN-LAST:event_deleteAllBtnActionPerformed

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
            java.util.logging.Logger.getLogger(P2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(P2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(P2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(P2P.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new P2P().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avgBuy;
    private javax.swing.JLabel avgSell;
    private javax.swing.JTable buytable;
    private com.toedter.calendar.JDateChooser datebox;
    private javax.swing.JTextField dealerbox;
    private javax.swing.JButton deleteAllBtn;
    private javax.swing.JButton deleteBtn;
    private javax.swing.JButton exitBtn;
    private javax.swing.JLabel idlabel;
    private javax.swing.JButton insertBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField lkrbox;
    private javax.swing.JPanel main;
    private javax.swing.JTextField notebox;
    private javax.swing.JLabel plLabel;
    private javax.swing.JTextField ratebox;
    private javax.swing.JTextField searchBox;
    private javax.swing.JTable selltable;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JButton updateBtn;
    private javax.swing.JTextField usdtbox;
    // End of variables declaration//GEN-END:variables

    
}
