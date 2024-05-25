package fees2;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.toedter.calendar.JDateChooser;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Array;
import java.util.Map;
import java.util.Map.*;
import static java.util.Map.entry;
import java.util.Set;
import java.util.TreeMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
        
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Anshika Computer
 */
public class Report extends javax.swing.JFrame {

    /**
     * Creates new form Report
     */
    DefaultTableModel model;
    public Report() {
        initComponents();
        updateCombobx();
       
    }
     public void clearRecord(){
        DefaultTableModel model=(DefaultTableModel)totaldata.getModel();
        model.setRowCount(1);
    }
     public void set_record(){
        
       SimpleDateFormat format=new SimpleDateFormat("YYYY-MM-dd");
          
       String cname=t_course.getSelectedItem().toString();  
       String to_date=format.format( t_to_date.getDate());
       String from_date=format.format(t_from_date.getDate());
       Integer amount_total=0;
       
      try{
           Class.forName("com.mysql.cj.jdbc.Driver");
       Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL"
               , "root","123");
        
       PreparedStatement psm=con.prepareStatement("select * from fees_details where date between ? and ? and courses=? ");
       
     
       psm.setString(1,from_date);
       psm.setString(2,to_date);
       psm.setString(3,cname);
         ResultSet rs=psm.executeQuery();
      
       
       while(rs.next()){
           String reciept_no=rs.getString("reciept_no");
           String roll_no=rs.getString("roll_no");
           String Student_name=rs.getString("student_name");
//           String Date=rs.getString("date");
           int amount=rs.getInt("total_amount");
           String remark=rs.getString("remark");
//           String mop=rs.getString("payment_mode");
           String Course=rs.getString("courses");
           Object[] obj={reciept_no,roll_no,Student_name,amount,remark,Course};
           amount_total=amount_total+amount;
           model =(DefaultTableModel) totaldata.getModel();
           model.addRow(obj);
       }
      tt_course.setText(cname);
      total.setText(amount_total.toString());
      }
      
      catch(Exception e){
          e.printStackTrace();
      }
    }
     
     void updateCombobx(){
        
        try{
             Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test?zeroDateTimeBehavior=CONVERT_TO_NULL","root","123");
        
         PreparedStatement pst=con.prepareStatement("select * from courses ");
        ResultSet rs=pst.executeQuery();
         
         while(rs.next()){
             t_course.addItem(rs.getString("course_name"));
         }

       
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }
     //export to excel
     public void exportToExcel(){
         XSSFWorkbook wb=new XSSFWorkbook();
         //XSSFWorkbook is the jar class which is add by externally into the library
         XSSFSheet  ws=wb.createSheet(); 
         //to store the data into the excel 
         TreeMap<String,Object[]>map=new TreeMap<String ,Object[]>();
        //TreeMap is use for key or value like k=9 and s=8
        //here k is key and 9 is the value
        DefaultTableModel model=( DefaultTableModel)totaldata.getModel();
        map.put("0", new Object[]{model.getColumnName(0),model.getColumnName(1),model.getColumnName(2),model.getColumnName(3),model.getColumnName(4),model.getColumnName(5)});
                //new object is as a temporary array which is called annonious array;
                // 0 means row of excel and new object is used for the keys ,means all value of the table
              for(int i=1;i<model.getRowCount();i++){
                  map.put(Integer.toString(i),new Object[]{model.getValueAt(i,0),model.getValueAt(i,1),model.getValueAt(i,2),model.getValueAt(i,3),model.getValueAt(i,4),model.getValueAt(i,5)});
              }
         Set<String>id=map.keySet();
         XSSFRow fRow;
         int rowId=0;
         for(String key:id){
             fRow=ws.createRow(rowId++);
             Object[] value=map.get(key);
             int cellId=0;
            for(Object object:value){
                XSSFCell cell= fRow.createCell(cellId++);
                cell.setCellValue(object.toString());
                
            }
             
         }
       try(FileOutputStream fos =new FileOutputStream(new File(path1.getText()))){
            
             wb.write(fos);
            
             JOptionPane.showMessageDialog(this, "file exported successfully"+path1.getText());
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        tt_course = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        tt_course1 = new javax.swing.JLabel();
        tt_course2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        button = new javax.swing.JButton();
        button1 = new javax.swing.JButton();
        path1 = new javax.swing.JTextField();
        button2 = new javax.swing.JButton();
        browse = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        totaldata = new javax.swing.JTable();
        t_from_date = new com.toedter.calendar.JDateChooser();
        t_to_date = new com.toedter.calendar.JDateChooser();
        t_course = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 490, 1040));

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));
        jPanel1.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jPanel1AncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("to");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 20, -1));

        jPanel2.setBackground(new java.awt.Color(0, 0, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Total Amount Collected:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 160, -1));

        tt_course.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tt_course.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(tt_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 90, 20));
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 80, -1));

        total.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 80, 20));

        tt_course1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tt_course1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(tt_course1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 90, 20));

        tt_course2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tt_course2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(tt_course2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 90, 20));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Select Course:");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 100, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 350, 70));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Select Date:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 100, -1));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Form:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 60, 50, -1));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Select Course:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 100, -1));

        button.setBackground(new java.awt.Color(0, 0, 102));
        button.setForeground(new java.awt.Color(255, 255, 255));
        button.setText("Export to Excel");
        button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActionPerformed(evt);
            }
        });
        jPanel1.add(button, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 160, 120, -1));

        button1.setBackground(new java.awt.Color(0, 0, 102));
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setText("Submit");
        button1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                button1MouseClicked(evt);
            }
        });
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        jPanel1.add(button1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        path1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                path1ActionPerformed(evt);
            }
        });
        jPanel1.add(path1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 210, -1));

        button2.setBackground(new java.awt.Color(0, 0, 102));
        button2.setForeground(new java.awt.Color(255, 255, 255));
        button2.setText("Print");
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });
        jPanel1.add(button2, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 120, -1, -1));

        browse.setBackground(new java.awt.Color(0, 0, 102));
        browse.setForeground(new java.awt.Color(255, 255, 255));
        browse.setText("Browse");
        browse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseActionPerformed(evt);
            }
        });
        jPanel1.add(browse, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 160, 100, -1));

        totaldata.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "reciept no", "roll no", "student name", "amount ", "remark", "Course"
            }
        ));
        jScrollPane1.setViewportView(totaldata);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 830, -1));
        jPanel1.add(t_from_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, -1, -1));
        jPanel1.add(t_to_date, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, -1, -1));

        t_course.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t_courseMouseClicked(evt);
            }
        });
        t_course.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t_courseActionPerformed(evt);
            }
        });
        jPanel1.add(t_course, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 190, 20));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, 1230, 1080));

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jPanel1AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jPanel1AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jPanel1AncestorAdded

    private void buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActionPerformed
        // TODO add your handling code here:
        exportToExcel();
    }//GEN-LAST:event_buttonActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    private void path1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_path1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_path1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button2ActionPerformed

    private void browseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseActionPerformed
        // TODO add your handling code here:
        JFileChooser fileChooser=new JFileChooser();
        //filechooser is use to choose the loction of file to save
        fileChooser.showOpenDialog(this);
        SimpleDateFormat dateformat=new SimpleDateFormat("YYYY-MM-dd");
        String date=dateformat.format(new Date());
        //new date is used to take current date
        
        try{
            File f=fileChooser.getSelectedFile();
            //file f is use to store the file 
            String path =f.getAbsolutePath();
            //get absolutePath is use to select the the path of file
           path=path+"_"+date+".xlsx";
           //these upper line isuse to stroe path with curent date and extention
           path1.setText(path);
            
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }//GEN-LAST:event_browseActionPerformed

    private void button1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_button1MouseClicked
        // TODO add your handling code here:
        clearRecord();
        set_record();
         
       
         
        
         
    }//GEN-LAST:event_button1MouseClicked

    private void t_courseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t_courseMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_t_courseMouseClicked

    private void t_courseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t_courseActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_t_courseActionPerformed

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        // TODO add your handling code here:
        search s=new search();
        s.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel4MouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        edit e=new edit();
        e.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        edit_course ed=new edit_course();
        ed.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        // TODO add your handling code here:
        Report r=new Report();
        r.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        // TODO add your handling code here:
        sign_up si=new sign_up();
        si.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel11MouseClicked

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
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Report.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Report().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Home2;
    private javax.swing.JPanel back;
    private javax.swing.JButton browse;
    private javax.swing.JButton button;
    private javax.swing.JButton button1;
    private javax.swing.JButton button2;
    private javax.swing.JPanel courselist;
    private javax.swing.JPanel editcourse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel logout;
    private javax.swing.JTextField path1;
    private javax.swing.JPanel searchreport;
    private javax.swing.JComboBox<String> t_course;
    private com.toedter.calendar.JDateChooser t_from_date;
    private com.toedter.calendar.JDateChooser t_to_date;
    private javax.swing.JLabel total;
    private javax.swing.JTable totaldata;
    private javax.swing.JLabel tt_course;
    private javax.swing.JLabel tt_course1;
    private javax.swing.JLabel tt_course2;
    private javax.swing.JPanel viewall;
    // End of variables declaration//GEN-END:variables
}
