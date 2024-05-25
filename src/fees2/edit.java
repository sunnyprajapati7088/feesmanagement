package fees2;



import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import static java.lang.reflect.Array.get;
import static java.nio.file.Paths.get;
import javax.swing.JOptionPane;
import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import static javax.swing.UIManager.get;
import java.util.Date;
import java.text.SimpleDateFormat;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Anshika Computer
 */
public class edit extends javax.swing.JFrame {

    /**
     * Creates new form addfees
     * 
     */
   Date sdob;
    public edit() {
        initComponents();
         display_first_cash();
         int recieptt=getreceipt_no();
         receipt1.setText(Integer.toString(recieptt));
         editdata();
         updateCombobx();
        
    }
    public String updateData(){
        String status="";
        int  reciept_no=Integer.parseInt(receipt1.getText());
       String student_name=receivedfrom.getText();
       int roll_no=Integer.parseInt(rollno.getText());
       String payment_mode=modeofpayment.getSelectedItem().toString();
       String cheque_no=cheque.getText();
       String bank_name=bankname.getText();
       String dd_no=ddno.getText();
       String transaction_id=tno.getText();
       String course=courses.getSelectedItem().toString();
        sdob=dob.getDate();
//       float gstin
       float total_amt=Float.parseFloat(totalfees.getText());
       String gst_no=gst.getText();
        float in_ssgst=Float.parseFloat(in_sgst.getText());
         float in_ccgst=Float.parseFloat(in_cgst.getText());
       
       
    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
   
               String date1=format.format(sdob);
        float feess=Float.parseFloat(fees.getText());
        float ssgst=Float.parseFloat(sgst.getText());
        float ccgst=Float.parseFloat(cgst.getText());
        String remarkk=remark.getText();
        int year11=Integer.parseInt(year1.getText());
        
        int year22=Integer.parseInt(year2.getText());
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL",
                "root","123");
        PreparedStatement pst=con.prepareStatement("update fees_details set  student_name=?, "
                + "payment_mode=?,cheque_no=?,bank_name=?,"
                + "dd_no=?,courses=?,total_amount=?,date=?,amount=?,cgst=?,sgst=?,"
                + "remark=?,year1=?,year2=?,roll_no=?,transaction_id=? ,isgst=? ,icgst=? where reciept_no=?");
       
        pst.setString(1,student_name);
        pst.setString(2,payment_mode);
        pst.setString(3, cheque_no);
        pst.setString(4,bank_name);
        pst.setString(5,dd_no);
        pst.setString(6,course);
        pst.setFloat(7,total_amt);
        pst.setString(8, date1);
        pst.setFloat(9,feess);
         pst.setFloat(10,ssgst);
          pst.setFloat(11,ccgst);
        pst.setString(12, remarkk);
        pst.setInt(13,year11);
        pst.setInt(14,year22);
        pst.setInt(15, roll_no);
     
       pst.setString(16,transaction_id);
       pst.setFloat(17,in_ssgst);
       
       pst.setFloat(18,in_ccgst);
       
       
         pst.setInt(19,reciept_no);
      int rowcout=  pst.executeUpdate();
      if(rowcout==1){
          status="Success";
          
      }
      else{
          status="failed";
      }
        
        
        
        
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
   
   
       return status;
       
    }
    void updateCombobx(){
        
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","123");
        
         PreparedStatement pst=con.prepareStatement("select * from courses ");
        ResultSet rs=pst.executeQuery();
         
         while(rs.next()){
             courses.addItem(rs.getString("course_name"));
         }

       
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    public void display_first_cash(){
        DD.setVisible(false);
        check.setVisible(false);
        cheque.setVisible(false);
        ddno.setVisible(false);
        bankname.setVisible(false);
          bank_name.setVisible(false);
          tno.setVisible(false );
            ltno.setVisible(false);
        
    }
    public boolean validation(){
     if(receivedfrom.getText().equals("")){
         JOptionPane.showMessageDialog(this,"fill received from box");
         return false;
     }
     if(rollno.getText().equals("")){
         JOptionPane.showMessageDialog(this,"fillthe roll no box");
         return false;
         
     }
     if(year1.getText().equals("") && year2.getText().equals("")){
         JOptionPane.showMessageDialog(this, "enter the years of session");
         return false;
     }
     if(dob.getDate()==null){
         JOptionPane.showMessageDialog(this,"please choose Date");
         return false;
     }
    
     if(fees.getText().equals("") || fees.getText().matches("[0-9]")==true){
        
     
         JOptionPane.showMessageDialog(this,"enter the valid amount");
         return false;
     }
     if(modeofpayment.getSelectedItem().toString().equalsIgnoreCase("cheque")){
         if(cheque.getText().equals("")){
               JOptionPane.showMessageDialog(this,"enter the valid cheque no");
         return false;
         }
         
     }
       if(modeofpayment.getSelectedItem().toString().equalsIgnoreCase("DD")){
         if(ddno.getText().equals("")){
               JOptionPane.showMessageDialog(this,"enter the valid DD no");
         return false;
         }
         if(modeofpayment.getSelectedItem().toString().equalsIgnoreCase("card")){
           
                  if(bankname.getText().equals("")){
         JOptionPane.showMessageDialog(this,"enter the bank name");
         return false;
     
             }
         }
         
     }
     return true;
    }
    public int getreceipt_no(){
        int reciept=0;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","123");
            String sql;
            sql = "select max(reciept_no) from fees_details";
        PreparedStatement stmt=con.prepareStatement(sql);  
        ResultSet rs=stmt.executeQuery();
        if(rs.next()==true){
           reciept=rs.getInt(1);
        
            
        }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        return reciept+1;
        
    }
    
    public void editdata(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","123");
        // to be access last record of the database
        PreparedStatement smpt=con.prepareStatement("select * from fees_details order by reciept_no desc limit 1");
        ResultSet rs=smpt.executeQuery();
        rs.next();
        receipt1.setText(rs.getString("reciept_no"));
        modeofpayment.setSelectedItem(rs.getString("payment_mode"));
        dob.setDate(rs.getDate("date"));
        rollno.setText(rs.getString("roll_no"));
        receivedfrom.setText(rs.getString("student_name"));
        cheque.setText(rs.getString("cheque_no"));
        ddno.setText(rs.getString("dd_no"));
        bankname.setText(rs.getString("bank_name"));
        year1.setText(rs.getString("year1"));
           year2.setText(rs.getString("year2"));
           courses.setSelectedItem(rs.getString("courses"));
           remark.setText(rs.getString("remark"));
           fees.setText(rs.getString("amount"));
        sgst.setText(rs.getString("sgst"));
         cgst.setText(rs.getString("cgst"));
         totalfees.setText(rs.getString("total_amount"));
         tno.setText(rs.getString("transaction_id"));
        in_sgst.setText(rs.getString("isgst"));
        in_cgst.setText(rs.getString("icgst"));
        
         
        
        
        
        
        }
        catch(Exception e){
            e.printStackTrace();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        Home2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        searchreport = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        editcourse = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        courselist = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        viewall = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        back = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        logout = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        DD = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        bank_name = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        check = new javax.swing.JLabel();
        receipt1 = new javax.swing.JTextField();
        bankname = new javax.swing.JTextField();
        dob = new com.toedter.calendar.JDateChooser();
        cheque = new javax.swing.JTextField();
        ddno = new javax.swing.JTextField();
        modeofpayment = new javax.swing.JComboBox<>();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        received = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        receivedfrom = new javax.swing.JTextField();
        year1 = new javax.swing.JTextField();
        year2 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        fees = new javax.swing.JTextField();
        totalfees = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        sgst = new javax.swing.JTextField();
        cgst = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        remark = new javax.swing.JTextArea();
        coursename = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        courses = new javax.swing.JComboBox<>();
        in_sgst = new javax.swing.JTextField();
        in_cgst = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        rollno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        gst = new javax.swing.JLabel();
        ltno = new javax.swing.JLabel();
        ltno1 = new javax.swing.JLabel();
        tno = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(0, 0, 102));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Home2.setBackground(new java.awt.Color(0, 0, 102));
        Home2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        Home2.setVerifyInputWhenFocusTarget(false);
        Home2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                Home2MouseEntered(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        jLabel3.setText("      Home ");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Home2Layout = new javax.swing.GroupLayout(Home2);
        Home2.setLayout(Home2Layout);
        Home2Layout.setHorizontalGroup(
            Home2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        Home2Layout.setVerticalGroup(
            Home2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Home2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.add(Home2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 70, -1, -1));

        searchreport.setBackground(new java.awt.Color(0, 0, 102));
        searchreport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        searchreport.setVerifyInputWhenFocusTarget(false);
        searchreport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                searchreportMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                searchreportMouseExited(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/search2.png"))); // NOI18N
        jLabel4.setText("   Search report");
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout searchreportLayout = new javax.swing.GroupLayout(searchreport);
        searchreport.setLayout(searchreportLayout);
        searchreportLayout.setHorizontalGroup(
            searchreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchreportLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        searchreportLayout.setVerticalGroup(
            searchreportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 56, Short.MAX_VALUE)
        );

        jPanel3.add(searchreport, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 142, 330, -1));

        editcourse.setBackground(new java.awt.Color(0, 0, 102));
        editcourse.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        editcourse.setVerifyInputWhenFocusTarget(false);
        editcourse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                editcourseMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                editcourseMouseExited(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/edit2.png"))); // NOI18N
        jLabel2.setText("  Edit course");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout editcourseLayout = new javax.swing.GroupLayout(editcourse);
        editcourse.setLayout(editcourseLayout);
        editcourseLayout.setHorizontalGroup(
            editcourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editcourseLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        editcourseLayout.setVerticalGroup(
            editcourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editcourseLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.add(editcourse, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 214, 330, -1));

        courselist.setBackground(new java.awt.Color(0, 0, 102));
        courselist.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        courselist.setVerifyInputWhenFocusTarget(false);
        courselist.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                courselistMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                courselistMouseExited(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/list_1.png"))); // NOI18N
        jLabel5.setText(" Course list");
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout courselistLayout = new javax.swing.GroupLayout(courselist);
        courselist.setLayout(courselistLayout);
        courselistLayout.setHorizontalGroup(
            courselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, courselistLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        courselistLayout.setVerticalGroup(
            courselistLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(courselistLayout.createSequentialGroup()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 2, Short.MAX_VALUE))
        );

        jPanel3.add(courselist, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 292, 330, -1));

        viewall.setBackground(new java.awt.Color(0, 0, 102));
        viewall.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        viewall.setVerifyInputWhenFocusTarget(false);
        viewall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                viewallMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                viewallMouseExited(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view all record.png"))); // NOI18N
        jLabel6.setText("View all record");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout viewallLayout = new javax.swing.GroupLayout(viewall);
        viewall.setLayout(viewallLayout);
        viewallLayout.setHorizontalGroup(
            viewallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, viewallLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        viewallLayout.setVerticalGroup(
            viewallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel3.add(viewall, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 364, 330, -1));

        back.setBackground(new java.awt.Color(0, 0, 102));
        back.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        back.setVerifyInputWhenFocusTarget(false);
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                backMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                backMouseExited(evt);
            }
        });
        back.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back1.png"))); // NOI18N
        jLabel8.setText("        Back");
        back.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(52, 2, 249, 54));

        jPanel3.add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 434, 330, -1));

        logout.setBackground(new java.awt.Color(0, 0, 102));
        logout.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.white, java.awt.Color.white, null, null));
        logout.setVerifyInputWhenFocusTarget(false);
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logoutMouseExited(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/logout.png"))); // NOI18N
        jLabel11.setText("      Logout ");
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout logoutLayout = new javax.swing.GroupLayout(logout);
        logout.setLayout(logoutLayout);
        logoutLayout.setHorizontalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logoutLayout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        logoutLayout.setVerticalGroup(
            logoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logoutLayout.createSequentialGroup()
                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 48, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel3.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 504, -1, -1));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 1040));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 1040));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        DD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        DD.setText("DD no:-");
        jPanel2.add(DD, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 80, -1));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Mode of Payment:-");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 120, -1));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Date:-");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 40, 60, -1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel2.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 390, -1));

        bank_name.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bank_name.setText("Bank Name:-");
        jPanel2.add(bank_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        jLabel28.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel28.setText("Reciept no:-");
        jPanel2.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 80, -1));

        check.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        check.setText("Check no:-");
        jPanel2.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, 80, -1));

        receipt1.setText("01");
        receipt1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipt1ActionPerformed(evt);
            }
        });
        jPanel2.add(receipt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 30, 60, -1));

        bankname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banknameActionPerformed(evt);
            }
        });
        jPanel2.add(bankname, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, 170, -1));
        jPanel2.add(dob, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 40, 170, -1));

        cheque.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chequeActionPerformed(evt);
            }
        });
        jPanel2.add(cheque, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 170, -1));
        jPanel2.add(ddno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 180, -1));

        modeofpayment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "DD", "Cheque", "card ", "cash", "UPI", " " }));
        modeofpayment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modeofpaymentActionPerformed(evt);
            }
        });
        jPanel2.add(modeofpayment, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        jPanel5.setBackground(new java.awt.Color(0, 0, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Following payment in the college of office the the year");
        jPanel5.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 350, -1));

        received.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        received.setText("Received From:-");
        jPanel5.add(received, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 120, -1));

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Course:-");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, 120, -1));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel21.setText("Heads");
        jPanel5.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 120, -1));

        jLabel23.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel23.setText("S.No:-");
        jPanel5.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 120, -1));

        jLabel26.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel26.setText("Amount(Rs)");
        jPanel5.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 100, 120, -1));

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Receiver Signature:-");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 150, -1));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel19.setText("Remark:-");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 120, -1));
        jPanel5.add(receivedfrom, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 210, -1));

        year1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                year1ActionPerformed(evt);
            }
        });
        jPanel5.add(year1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 60, -1));
        jPanel5.add(year2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 40, 60, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText("to");
        jPanel5.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 40, 20, -1));

        fees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                feesActionPerformed(evt);
            }
        });
        jPanel5.add(fees, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 130, 90, -1));

        totalfees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalfeesActionPerformed(evt);
            }
        });
        jPanel5.add(totalfees, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 250, 90, -1));
        jPanel5.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 240, 160, 10));
        jPanel5.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 1000, 10));
        jPanel5.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1000, 10));
        jPanel5.add(sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 170, 90, -1));

        cgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cgstActionPerformed(evt);
            }
        });
        jPanel5.add(cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 210, 90, -1));

        remark.setColumns(20);
        remark.setRows(5);
        jScrollPane1.setViewportView(remark);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 270, 60));

        coursename.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursenameActionPerformed(evt);
            }
        });
        jPanel5.add(coursename, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 270, -1));

        jButton1.setText("Print");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 350, 100, 30));

        courses.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "java", "python", "c++", "PHP" }));
        courses.setSelectedIndex(2);
        courses.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                coursesMouseClicked(evt);
            }
        });
        courses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                coursesActionPerformed(evt);
            }
        });
        jPanel5.add(courses, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 190, 20));

        in_sgst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                in_sgstActionPerformed(evt);
            }
        });
        jPanel5.add(in_sgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 170, 60, -1));
        jPanel5.add(in_cgst, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 200, 60, -1));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("enter the % of cgst");
        jPanel5.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 200, 150, 20));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel14.setText("enter the % of sgst");
        jPanel5.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 170, 150, 20));

        jPanel2.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 1000, 570));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Roll.No:-");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 60, -1));
        jPanel2.add(rollno, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 170, -1));

        jLabel1.setText("G.S.T No:-");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 76, -1, 20));

        gst.setText("GLGPP0491F");
        jPanel2.add(gst, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 80, 170, -1));

        ltno.setText("Transaction Id");
        jPanel2.add(ltno, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        ltno1.setText("Transaction Id");
        jPanel2.add(ltno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));
        jPanel2.add(tno, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 180, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 1310, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void receipt1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipt1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receipt1ActionPerformed

    private void modeofpaymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modeofpaymentActionPerformed
        // TODO add your handling code here:
        if(modeofpayment.getSelectedIndex()== 0){
            DD.setVisible(true);
            ddno.setVisible(true);
            bankname.setVisible(true);
            bank_name.setVisible(true);
            check.setVisible(false);
            cheque.setVisible(false);
            tno.setVisible(false );
            ltno.setVisible(false);
            }
        if(modeofpayment.getSelectedIndex()==1){
            DD.setVisible(false);
            ddno.setVisible(false);
            bankname.setVisible(true);
            bank_name.setVisible(true);
            check.setVisible(true);
            cheque.setVisible(true);
            tno.setVisible(false );
            ltno.setVisible(false);
            
        }
        if(modeofpayment.getSelectedIndex()==3){
            DD.setVisible(false);
            ddno.setVisible(false);
            bankname.setVisible(false);
            bank_name.setVisible(false);
            check.setVisible(false);
            cheque.setVisible(false);
            tno.setVisible(false );
            ltno.setVisible(false);
            
        }
          if(modeofpayment.getSelectedIndex()==2){
            DD.setVisible(false);
            ddno.setVisible(false);
            bankname.setVisible(true);
            bank_name.setVisible(true);
            check.setVisible(false);
            cheque.setVisible(false);
            tno.setVisible(false );
            ltno.setVisible(false);
            
        }
           if(modeofpayment.getSelectedIndex()==4){
              DD.setVisible(false);
            ddno.setVisible(false);
            bankname.setVisible(true);
            bank_name.setVisible(true);
            check.setVisible(false);
            cheque.setVisible(false);
            tno.setVisible(true);
            ltno.setVisible(true);
          }
        
    }//GEN-LAST:event_modeofpaymentActionPerformed

    private void year1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_year1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_year1ActionPerformed

    private void totalfeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalfeesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalfeesActionPerformed

    private void cgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cgstActionPerformed

    private void feesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_feesActionPerformed
        // TODO add your handling code here:
        Float amt=Float.parseFloat(fees.getText());
         Float i_in_sgst=Float.parseFloat(in_sgst.getText());
        Float i_in_cgst=Float.parseFloat(in_cgst.getText());
        Float ccgst=(float)(amt*i_in_cgst*0.01);
        Float ssgst=(float)(amt*i_in_sgst*0.01);
        Float ttotal=ccgst+ssgst+amt;
        cgst.setText(Float.toString(ccgst));
        sgst.setText(Float.toString(ssgst));
        totalfees.setText(Float.toString(ttotal));
        
    }//GEN-LAST:event_feesActionPerformed

    private void coursesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursesActionPerformed
        // TODO add your handling code here:
        coursename.setText(courses.getSelectedItem().toString());
    }//GEN-LAST:event_coursesActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
       
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        // TODO add your handling code here:
      if(validation()==true){
          String result=updateData();
          if(result.equals("Success")){
              JOptionPane.showMessageDialog(this,"update is completed");
              printreciept p=new printreciept();
              p.setVisible(true);
              this.dispose();
          }
          else{
              JOptionPane.showMessageDialog(this,"update is not completed");
          }
      }
      
    }//GEN-LAST:event_jButton1MouseClicked

    private void banknameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banknameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banknameActionPerformed

    private void coursenameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_coursenameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_coursenameActionPerformed

    private void coursesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_coursesMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_coursesMouseClicked

    private void chequeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chequeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chequeActionPerformed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        home home=new home();
        home.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void Home2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Home2MouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        Home2.setBackground(clr);
    }//GEN-LAST:event_Home2MouseEntered

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        search s=new search();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void searchreportMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchreportMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        searchreport.setBackground(clr);
    }//GEN-LAST:event_searchreportMouseEntered

    private void searchreportMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchreportMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,0,102);
        searchreport.setBackground(clr);
    }//GEN-LAST:event_searchreportMouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        edit e=new edit();
        e.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel2MouseClicked

    private void editcourseMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editcourseMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        editcourse.setBackground(clr);
    }//GEN-LAST:event_editcourseMouseEntered

    private void editcourseMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_editcourseMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,0,102);
        editcourse.setBackground(clr);
    }//GEN-LAST:event_editcourseMouseExited

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        edit_course ed=new edit_course();
        ed.setVisible(true);
        this.dispose();

    }//GEN-LAST:event_jLabel5MouseClicked

    private void courselistMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courselistMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        courselist.setBackground(clr);
    }//GEN-LAST:event_courselistMouseEntered

    private void courselistMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_courselistMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,0,102);
        courselist.setBackground(clr);
    }//GEN-LAST:event_courselistMouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Report r=new Report();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void viewallMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewallMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        viewall.setBackground(clr);
    }//GEN-LAST:event_viewallMouseEntered

    private void viewallMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_viewallMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,0,102);
        viewall.setBackground(clr);
    }//GEN-LAST:event_viewallMouseExited

    private void backMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        back.setBackground(clr);
    }//GEN-LAST:event_backMouseEntered

    private void backMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseExited
        Color clr=new Color(0,0,102);
        back.setBackground(clr);
        // TODO add your handling code here:
    }//GEN-LAST:event_backMouseExited

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        sign_up si=new sign_up();
        si.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

    private void logoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseEntered
        // TODO add your handling code here:
        Color clr=new Color(0,0,255);
        logout.setBackground(clr);
    }//GEN-LAST:event_logoutMouseEntered

    private void logoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseExited
        // TODO add your handling code here:
        Color clr=new Color(0,0,102);
        logout.setBackground(clr);
    }//GEN-LAST:event_logoutMouseExited

    private void in_sgstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_in_sgstActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_in_sgstActionPerformed

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
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(addfees.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new addfees().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DD;
    private javax.swing.JPanel Home2;
    private javax.swing.JPanel back;
    private javax.swing.JLabel bank_name;
    private javax.swing.JTextField bankname;
    private javax.swing.JTextField cgst;
    private javax.swing.JLabel check;
    private javax.swing.JTextField cheque;
    private javax.swing.JPanel courselist;
    private javax.swing.JTextField coursename;
    private javax.swing.JComboBox<String> courses;
    private javax.swing.JTextField ddno;
    private com.toedter.calendar.JDateChooser dob;
    private javax.swing.JPanel editcourse;
    private javax.swing.JTextField fees;
    private javax.swing.JLabel gst;
    private javax.swing.JTextField in_cgst;
    private javax.swing.JTextField in_sgst;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel logout;
    private javax.swing.JLabel ltno;
    private javax.swing.JLabel ltno1;
    private javax.swing.JComboBox<String> modeofpayment;
    private javax.swing.JTextField receipt1;
    private javax.swing.JLabel received;
    private javax.swing.JTextField receivedfrom;
    private javax.swing.JTextArea remark;
    private javax.swing.JTextField rollno;
    private javax.swing.JPanel searchreport;
    private javax.swing.JTextField sgst;
    private javax.swing.JTextField tno;
    private javax.swing.JTextField totalfees;
    private javax.swing.JPanel viewall;
    private javax.swing.JTextField year1;
    private javax.swing.JTextField year2;
    // End of variables declaration//GEN-END:variables
}
