/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;
import Logic.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.*;

/**
 *
 * @author hernandez
 */
public class DialogoCarnet extends javax.swing.JDialog {

   public static String eliminado;
   int condicion=0;
   Timer temporizador;
   Document docCarnet;
   boolean continuar=false;
    public DialogoCarnet(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        docCarnet=txtCarnet.getDocument();
        docCarnet.addDocumentListener(new docCarnetListener());
        btnSalir.addMouseListener(new manejaClicks());
        
       
        
        ImageIcon imagen=new ImageIcon("src/imagenes/IconoGeneralDialog.png");
        Icon iconon=new ImageIcon(imagen.getImage().getScaledInstance(lblLogo.getWidth(),lblLogo.getHeight(), Image.SCALE_DEFAULT));
        lblLogo.setIcon(iconon);
        
        ImageIcon imageSalir=new ImageIcon("src/imagenes/cerrar2.png");
        Icon iconSalir=new ImageIcon(imageSalir.getImage().getScaledInstance(btnSalir.getWidth(), btnSalir.getHeight(), Image.SCALE_DEFAULT));
        btnSalir.setIcon(iconSalir);
        this.repaint();
        
        ImageIcon imaicono=new ImageIcon("src/imagenes/IconoGeneral.png");
        this.setIconImage(imaicono.getImage());
        
    }
    
    public void validaCarnet(){//valida el carnet
        
       String carnett=txtCarnet.getText();
       
       if(carnett.length()>0){continuar=true;  txtCarnet.setBackground(new java.awt.Color(159,225,242));}else{continuar=false; txtCarnet.setBackground(Color.white);}
       btnAceptar.setEnabled(continuar);
    }
 private class docCarnetListener implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {
            validaCarnet();}

        @Override
        public void removeUpdate(DocumentEvent e) {
            validaCarnet();}

        @Override
        public void changedUpdate(DocumentEvent e) {}}
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblLogo = new javax.swing.JLabel();
        txtCarnet = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jlbcarnet = new javax.swing.JLabel();
        btnSalir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(lblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 70, 50));

        txtCarnet.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtCarnet, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 250, -1));

        btnAceptar.setBackground(new java.awt.Color(255, 255, 255));
        btnAceptar.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        btnAceptar.setForeground(new java.awt.Color(102, 102, 102));
        btnAceptar.setText("Aceptar");
        btnAceptar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 60, -1));

        jPanel1.setBackground(new java.awt.Color(158, 223, 240));
        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jlbcarnet.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        jlbcarnet.setForeground(new java.awt.Color(102, 102, 102));
        jlbcarnet.setText("Ingresa el carnet del alumno");
        jPanel1.add(jlbcarnet, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 100, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 400, 290));
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 20, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents
//el siguiente método desata el evento de acuerdo a que radiobutton presionamos buscar, eliminar o editar
    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
       //este temporizador ayuda a poner un mensaje en el frame principar en caso de que no se encuentre el alumn
        temporizador=new Timer(5000, new AvisoLabel());
       if(registro.selected==1){ String alumno=registro.manejador.BuscarAlumno(txtCarnet.getText(), 0);registro.giveMeLbl().setText(alumno); temporizador.start();}//buscar alumno
       
       else if(registro.selected==2){int alumno=registro.manejador.EditarAlumno(txtCarnet.getText());
       if(alumno!=1000){DialogEditar dialog = new DialogEditar(new javax.swing.JFrame(), true, alumno); dialog.setVisible(true); System.out.println("alumno si fue diferente a 1000");}
       else if(alumno==1000){registro.giveMeLbl().setText("Alumno no encontrado para editar");}}//para editar alumno en este caso se abre una tercera ventana con los datos en campos de texto para que los editemos
       
       else if(registro.selected==3){ eliminado=registro.manejador.EliminarAlumno(txtCarnet.getText());  registro.giveMeLbl().setText(eliminado);//para eliminar alumnotemporizador.start();
      }
       
       this.setVisible(false);//una vez hemos dado click al botón aceptar se realiza una de las acciones ateriores e inmediatamente se hace invisible la ventana
    }//GEN-LAST:event_btnAceptarActionPerformed

    private class manejaClicks extends MouseAdapter{
        public void mouseClicked(MouseEvent e){//maneja el click en la etiqueta correspondiente a cerrar
            System.exit(0);
        }
    }
    
    private class AvisoLabel implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            registro.giveMeLbl().setText("");
            temporizador.stop();
        }
    
}
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel jlbcarnet;
    private javax.swing.JLabel lblLogo;
    private javax.swing.JTextField txtCarnet;
    // End of variables declaration//GEN-END:variables
}
