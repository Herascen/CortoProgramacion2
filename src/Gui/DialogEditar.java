
package Gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;


public class DialogEditar extends javax.swing.JDialog {

    int posicion;
    
    Document docnombre;
    Document docapellido;
    Document docgrado;
    
    boolean cumpleSize=false;
    boolean noNum=false;
    
    public DialogEditar(java.awt.Frame parent, boolean modal, int position) {
        super(parent, modal);
        this.posicion=position;
        setLocationRelativeTo(null);
        initComponents();
        lblSalir.addMouseListener(new manejaClicks());
        docnombre=txtnombre.getDocument();
        docapellido=txtapellido.getDocument();
        docgrado=txtgrado.getDocument();
        manageDocs docHandler=new manageDocs();
        docnombre.addDocumentListener(docHandler);
        docapellido.addDocumentListener(docHandler);
        docgrado.addDocumentListener(docHandler);
        
        ImageIcon imagencuadro=new ImageIcon("src/imagenes/noFondo.png");
        Icon iconoCuadro=new ImageIcon(imagencuadro.getImage().getScaledInstance(cuadro.getWidth(), cuadro.getHeight(), WIDTH));
        cuadro.setIcon(iconoCuadro);
        
        ImageIcon imagenSalir=new ImageIcon("src/imagenes/cerrar2.png");
        Icon iconoSalir=new ImageIcon(imagenSalir.getImage().getScaledInstance(lblSalir.getWidth(), lblSalir.getHeight(), Image.SCALE_DEFAULT));
        lblSalir.setIcon(iconoSalir);
        
        
        
        ImageIcon imaicono=new ImageIcon("src/imagenes/IconoGeneral.png");
        this.setIconImage(imaicono.getImage());
        
         //toma los datos del alumno buscado y los pone en los campos de texto para editarlos
       String name=registro.manejador.getArrayAlumno().get(posicion).getNombre();
       String apelli=registro.manejador.getArrayAlumno().get(posicion).getApellido();
       String grade=registro.manejador.getArrayAlumno().get(posicion).getGrado();
       txtnombre.setText(name);
       txtapellido.setText(apelli);
       txtgrado.setText(grade); }
    
    private class manejaClicks extends MouseAdapter{
    public void mouseClicked(MouseEvent e){
        System.exit(0);
    }
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtnombre = new javax.swing.JTextField();
        txtapellido = new javax.swing.JTextField();
        btnaceptar = new javax.swing.JButton();
        cuadro = new javax.swing.JLabel();
        txtgrado = new javax.swing.JTextField();
        lblAvisos = new javax.swing.JLabel();
        pnlFondo = new javax.swing.JPanel();
        lblSalir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtnombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(109, 100, 170, -1));

        txtapellido.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtapellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 130, 170, -1));

        btnaceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnaceptar.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        btnaceptar.setForeground(new java.awt.Color(102, 102, 102));
        btnaceptar.setText("Aceptar");
        btnaceptar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnaceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnaceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 80, -1));
        getContentPane().add(cuadro, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 220, 190));

        txtgrado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtgrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 160, 170, -1));

        lblAvisos.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        lblAvisos.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(lblAvisos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, 230, 10));

        pnlFondo.setBackground(new java.awt.Color(158, 223, 240));
        pnlFondo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlFondo.add(lblSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 20, 20));

        getContentPane().add(pnlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnaceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaceptarActionPerformed
     //edita los datos del alumno
        registro.manejador.getArrayAlumno().get(posicion).setNombre(txtnombre.getText());
       registro.manejador.getArrayAlumno().get(posicion).setApellido(txtapellido.getText());
       this.setVisible(false);


    }//GEN-LAST:event_btnaceptarActionPerformed

    public void validaDatos(){//valida los datos al igual que en la ventana principal
        String nums="\\d";
        Pattern pattnum=Pattern.compile(nums);
        Matcher matchNom=pattnum.matcher(txtnombre.getText());
        Matcher matchApp=pattnum.matcher(txtapellido.getText());
        Matcher matchGra=pattnum.matcher(txtgrado.getText());
        
         if(txtnombre.getText().length()>0){txtnombre.setBackground(new java.awt.Color(159, 225, 242));}else{txtnombre.setBackground(Color.white);}
        if(txtapellido.getText().length()>0){txtapellido.setBackground(new java.awt.Color(159,225,242));}else{txtapellido.setBackground(Color.white);}
        if(txtgrado.getText().length()>0){txtgrado.setBackground(new java.awt.Color(159,225,242));}else{txtgrado.setBackground(Color.white);}
        
        if(matchNom.find() || matchApp.find() || matchGra.find()){noNum=false; lblAvisos.setText("Los datos no pueden llevar números");}
        else if(!matchNom.find()&&!matchApp.find()&&!matchGra.find()){noNum=true; lblAvisos.setText("");}
        
         if(txtnombre.getText().length()==0 || txtapellido.getText().length()==0 || txtgrado.getText().length()==0){cumpleSize=false;}
         else if(txtnombre.getText().length()!=0 && txtapellido.getText().length()!=0 && txtgrado.getText().length()!=0){cumpleSize=true; }
        btnaceptar.setEnabled(cumpleSize);}
    
    private class manageDocs implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) { validaDatos();}

        @Override
        public void removeUpdate(DocumentEvent e) {validaDatos();}

        @Override
        public void changedUpdate(DocumentEvent e) { }}
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnaceptar;
    private javax.swing.JLabel cuadro;
    private javax.swing.JLabel lblAvisos;
    private javax.swing.JLabel lblSalir;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JTextField txtapellido;
    private javax.swing.JTextField txtgrado;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
