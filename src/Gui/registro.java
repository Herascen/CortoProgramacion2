
package Gui;

import Logic.Alumno;
import Logic.ManejadorAlumnos;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.Document;
import javax.swing.*;


public class registro extends javax.swing.JFrame {
    //datos del usuario
    String nombre;
    String apellido;
    String grado;
    //instancia de la clase manejador en la que se encuentran los métodos para realizar operaciones sobre el array de alumnos
    public static ManejadorAlumnos manejador=new ManejadorAlumnos();
    final DefaultListModel model = new DefaultListModel();//para agregar o quitar elementos a la lista
    int conteoalumno=0;//sirve para la etiqueta lblconteo que nos pone cuantos alumnos hemos ingresado
    Document docNom, docApp, docgra;//documentos para cada uno de los txtDatos
    boolean cumpleSize=false;//para validar que el jtextfield no este vacio para ninguno de los datoss
    boolean normasDatos=false;//para validar que cumpla con el tamaño y que no lleve números
    boolean normasNum=false;//para validar que no lleve números
   public static int selected=0;//para saber que radiobutton se ha selecccionado
   ArrayList<String>thisArray;//array para su uso en los botones actualizar y ordenar
   boolean yaOrdeno;//para saber si ya ha utilizado el boton de ordenar y en consecuencia el boton actualizar tomará un comportamiento
   
   javax.swing.Timer temporizador=new javax.swing.Timer(3000, new ponAviso());//para poner avisos en la etiqueta por 3 segundos y luego se quiten
  
    public registro() {
        initComponents();
        btnAccion.setEnabled(false);//pone al botón para guardar datos como desactivado de inicio
        this.setLocationRelativeTo(null);
        setTitle("sistema de registro");
        btGroup.setSelected(rbtnAgregar.getModel(), rootPaneCheckingEnabled);
        clickCampos manejaCampos=new clickCampos();
        txtNombre.addMouseListener(manejaCampos);
        txtApellido.addMouseListener(manejaCampos);
        txtGrado.addMouseListener(manejaCampos);
        
        docNom=txtNombre.getDocument();//obtenemos los documentos de cada jtextfield
        docApp=txtApellido.getDocument();
        docgra=txtGrado.getDocument();
        
        manejadoc docHandler=new manejadoc();//les asignamos el manejador de eventos
        docNom.addDocumentListener(docHandler);
        docApp.addDocumentListener(docHandler);
        docgra.addDocumentListener(docHandler);
        
        
        ImageIcon salir=new ImageIcon("src/imagenes/cerrar2.png");
        Icon iconoSalir=new ImageIcon(salir.getImage().getScaledInstance(btnSalir.getWidth(), btnSalir.getHeight(), Image.SCALE_DEFAULT));
        btnSalir.setIcon(iconoSalir);
        this.repaint();
        btnSalir.addMouseListener(new manejaClick());
   
       
        ImageIcon imagecua=new ImageIcon("src/imagenes/estudiante2.png");
        Icon iconocua=new ImageIcon(imagecua.getImage().getScaledInstance(labelCuadro.getWidth(),labelCuadro.getHeight(), Image.SCALE_DEFAULT));
        labelCuadro.setIcon(iconocua);
        this.repaint();
        
        ImageIcon imageverti=new ImageIcon("src/imagenes/IconoGeneral.png");
        Icon iconoverti=new ImageIcon(imageverti.getImage().getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_DEFAULT));
        jLabel2.setIcon(iconoverti);
        this.repaint();
        
        ImageIcon imagealumnos=new ImageIcon(getClass().getResource("/imagenes/estudiantes2.png"));
        Icon iconoalumnos=new ImageIcon(imagealumnos.getImage().getScaledInstance(jLabel3.getWidth(), jLabel3.getHeight(), Image.SCALE_DEFAULT));
        jLabel3.setIcon(iconoalumnos);
        this.repaint();
      
       ImageIcon imaicono=new ImageIcon("src/imagenes/IconoGeneral.png");
       this.setIconImage(imaicono.getImage());
    }
   public static JLabel giveMeLbl(){
       return lblResultado;
   }
   private class clickCampos extends MouseAdapter{
       public void mouseClicked(MouseEvent e){
           if(e.getSource()==txtNombre){txtNombre.setText("");}
           else if(e.getSource()==txtApellido){txtApellido.setText("");}
           else if(e.getSource()==txtGrado){txtGrado.setText("");}
       }
   }
    public void validaDatos(){
       
        
        if(txtNombre.getText().length()>0){txtNombre.setBackground(new java.awt.Color(159,225,242));}//en caso de que el campo de texto no esté vacío lo pinta de color
        else{txtNombre.setBackground(Color.white);}//en caso de si estarlo lo pinta blanco
        
        if(txtApellido.getText().length()>0){txtApellido.setBackground(new java.awt.Color(159,225,242));}
        else{txtApellido.setBackground(Color.white);}
        
        if(txtGrado.getText().length()>0){txtGrado.setBackground(new java.awt.Color(159,225,242));}
        else{txtGrado.setBackground(Color.white);}
       
          //código para crear comparación entre los datos y los números para validar que estos no contengan números
        String numeros="\\d";
        Pattern patron=Pattern.compile(numeros);
        Matcher NomNum=patron.matcher(txtNombre.getText());
        Matcher AppNum=patron.matcher(txtApellido.getText());
        Matcher GradNum=patron.matcher(txtGrado.getText());
     
        //el método find nos dice si hay o no números
        if(NomNum.find() || AppNum.find() || GradNum.find()){ normasNum=false; lblResultado.setText("Los datos no pueden llevar números"); temporizador.start(); }//el temporizador desencadena un evento cada cierto tiempo
        //en este caso lo definimos para 3 segundos , cuando se llama al método start espera tres segundos para ejecutar la acción indicada en el actionlistener que le pasamos como parámetro
        
        else if(!NomNum.find() && !AppNum.find() && !GradNum.find()){normasNum=true; lblResultado.setText("");}
        
        if(txtNombre.getText().length()==0 || txtApellido.getText().length()==0 || txtGrado.getText().length()==0){cumpleSize=false;}
        else if(txtNombre.getText().length()!=0 && txtApellido.getText().length()!=0 && txtGrado.getText().length()!=0){cumpleSize=true;}
        
        if(normasNum && cumpleSize){normasDatos=true;}else if(!normasNum || !cumpleSize ){normasDatos=false;}//si no hay números y los campos de texto no están vacíos normasDatos es true
            
            btnAccion.setEnabled(normasDatos);}//dependiendo del valor de normasDatos se activará o desactivará el botón 
    
    private class ponAviso implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            lblResultado.setText("");
            temporizador.stop();}}
    //quita el aviso y detiene el temporizador
    
    
private class manejadoc implements DocumentListener{

        @Override
        public void insertUpdate(DocumentEvent e) {//maneja el evento al insertar en el campo de texto
         validaDatos();}

        @Override
        public void removeUpdate(DocumentEvent e) {//maneja el evento al borrar
        validaDatos();}

        @Override
        public void changedUpdate(DocumentEvent e) {}}

    private class manejaClick extends MouseAdapter{
        public void mouseClicked(MouseEvent e){
            System.exit(0);}}
  

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btGroup = new javax.swing.ButtonGroup();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        rbtnAgregar = new javax.swing.JRadioButton();
        rbtnBuscar = new javax.swing.JRadioButton();
        rbtnEditar = new javax.swing.JRadioButton();
        btnAccion = new javax.swing.JButton();
        scrlLista = new javax.swing.JScrollPane();
        lstEstudiantes = new javax.swing.JList<String>();
        labelCuadro = new javax.swing.JLabel();
        rbtnEliminar = new javax.swing.JRadioButton();
        pnlEntrada = new javax.swing.JPanel();
        btnActualizar = new javax.swing.JButton();
        btnOrdenar = new javax.swing.JButton();
        pnlLista = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        lblResultado = new javax.swing.JLabel();
        pnlFondo = new javax.swing.JPanel();
        lblConteo = new javax.swing.JLabel();
        txtGrado = new javax.swing.JTextField();
        btnSalir = new javax.swing.JLabel();
        pnlUp = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtNombre.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        txtNombre.setForeground(new java.awt.Color(102, 102, 102));
        txtNombre.setText("Nombre");
        txtNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 180, 120, -1));

        txtApellido.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        txtApellido.setForeground(new java.awt.Color(102, 102, 102));
        txtApellido.setText("Apellido");
        txtApellido.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        getContentPane().add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 210, 120, 20));

        rbtnAgregar.setBackground(new java.awt.Color(0, 204, 102));
        btGroup.add(rbtnAgregar);
        rbtnAgregar.setFont(new java.awt.Font("Arial Black", 1, 9)); // NOI18N
        rbtnAgregar.setForeground(new java.awt.Color(102, 102, 102));
        rbtnAgregar.setText("Guardar");
        rbtnAgregar.setContentAreaFilled(false);
        rbtnAgregar.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        rbtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 280, 80, -1));

        rbtnBuscar.setBackground(new java.awt.Color(0, 204, 102));
        btGroup.add(rbtnBuscar);
        rbtnBuscar.setFont(new java.awt.Font("Arial Black", 1, 9)); // NOI18N
        rbtnBuscar.setForeground(new java.awt.Color(102, 102, 102));
        rbtnBuscar.setText("Buscar");
        rbtnBuscar.setContentAreaFilled(false);
        rbtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 330, 70, 20));

        rbtnEditar.setBackground(new java.awt.Color(0, 204, 102));
        btGroup.add(rbtnEditar);
        rbtnEditar.setFont(new java.awt.Font("Arial Black", 1, 9)); // NOI18N
        rbtnEditar.setForeground(new java.awt.Color(102, 102, 102));
        rbtnEditar.setText("Editar");
        rbtnEditar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 153, 51)));
        rbtnEditar.setContentAreaFilled(false);
        rbtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnEditarActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 330, 80, 20));

        btnAccion.setBackground(new java.awt.Color(159, 225, 242));
        btnAccion.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        btnAccion.setForeground(new java.awt.Color(102, 102, 102));
        btnAccion.setText("Guardar");
        btnAccion.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnAccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAccionActionPerformed(evt);
            }
        });
        getContentPane().add(btnAccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 70, -1));

        scrlLista.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        lstEstudiantes.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        lstEstudiantes.setForeground(new java.awt.Color(102, 102, 102));
        scrlLista.setViewportView(lstEstudiantes);

        getContentPane().add(scrlLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 180, 280, 180));
        getContentPane().add(labelCuadro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 110, 60, 50));

        btGroup.add(rbtnEliminar);
        rbtnEliminar.setFont(new java.awt.Font("Arial Black", 1, 9)); // NOI18N
        rbtnEliminar.setForeground(new java.awt.Color(102, 102, 102));
        rbtnEliminar.setText("Eliminar");
        rbtnEliminar.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        rbtnEliminar.setBorderPainted(true);
        rbtnEliminar.setContentAreaFilled(false);
        rbtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtnEliminarActionPerformed(evt);
            }
        });
        getContentPane().add(rbtnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, -1, 20));

        pnlEntrada.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlEntrada.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(pnlEntrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 190, 300));

        btnActualizar.setBackground(new java.awt.Color(160, 226, 244));
        btnActualizar.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        btnActualizar.setForeground(new java.awt.Color(102, 102, 102));
        btnActualizar.setText("Actualizar");
        btnActualizar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        getContentPane().add(btnActualizar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 80, -1));

        btnOrdenar.setBackground(new java.awt.Color(159, 225, 242));
        btnOrdenar.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        btnOrdenar.setForeground(new java.awt.Color(102, 102, 102));
        btnOrdenar.setText("Ordenar");
        btnOrdenar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOrdenarActionPerformed(evt);
            }
        });
        getContentPane().add(btnOrdenar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 80, -1));

        pnlLista.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        javax.swing.GroupLayout pnlListaLayout = new javax.swing.GroupLayout(pnlLista);
        pnlLista.setLayout(pnlListaLayout);
        pnlListaLayout.setHorizontalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlListaLayout.createSequentialGroup()
                .addContainerGap(98, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(96, 96, 96))
        );
        pnlListaLayout.setVerticalGroup(
            pnlListaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlListaLayout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 214, Short.MAX_VALUE))
        );

        getContentPane().add(pnlLista, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 100, 300, 300));

        lblResultado.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        lblResultado.setForeground(new java.awt.Color(102, 102, 102));
        lblResultado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(lblResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 460, 20));

        pnlFondo.setBackground(new java.awt.Color(159, 225, 242));
        pnlFondo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlFondo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblConteo.setFont(new java.awt.Font("Arial Black", 2, 8)); // NOI18N
        pnlFondo.add(lblConteo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 180, 10));

        getContentPane().add(pnlFondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 670, 410));

        txtGrado.setFont(new java.awt.Font("Arial Black", 1, 10)); // NOI18N
        txtGrado.setForeground(new java.awt.Color(102, 102, 102));
        txtGrado.setText("Grado");
        txtGrado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        txtGrado.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txtGrado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 120, 20));
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 0, 20, 20));

        pnlUp.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pnlUp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        pnlUp.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 80, 60));

        getContentPane().add(pnlUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void rbtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnBuscarActionPerformed
        
        selected=1;//en caso de que el usuario de click al radiobutton buscar se creara una ventana jdialog en la que se pondrá el carnet
        DialogoCarnet dialog = new DialogoCarnet(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        
    }//GEN-LAST:event_rbtnBuscarActionPerformed

    private void rbtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnEditarActionPerformed
         selected=2;
        DialogoCarnet dialog = new DialogoCarnet(new javax.swing.JFrame(), true);
        dialog.setVisible(true);    }//GEN-LAST:event_rbtnEditarActionPerformed

    private void rbtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnEliminarActionPerformed
       selected=3;
        DialogoCarnet dialog = new DialogoCarnet(new javax.swing.JFrame(), true);
        dialog.setVisible(true); 
    }//GEN-LAST:event_rbtnEliminarActionPerformed

    private void rbtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtnAgregarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtnAgregarActionPerformed

    private void btnAccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAccionActionPerformed
        int numElementos=0;
        
        ArrayList<Alumno> arrayalumno;
        nombre=txtNombre.getText();
        apellido=txtApellido.getText();
        grado=txtGrado.getText();
        //guarda los alumnos 
        manejador.IngresarAlumno(nombre, apellido, grado);
        arrayalumno=manejador.getArrayAlumno();
        //guarda los alumnos en el model para el jlist
        for(int i=0+conteoalumno; i<arrayalumno.size(); i++){
            model.addElement(arrayalumno.get(i).getDatos());
            numElementos=model.size();
        }
        conteoalumno++;
        lstEstudiantes.setModel(model);//agrega los alumos guardados a la lista
        //a continuación vacía los campos de texto y pone el aviso de cuantos alumnos van
        txtNombre.setText("");
        txtApellido.setText("");
        txtGrado.setText("");
        lblConteo.setText(numElementos + " Alumnos en la lista");
        
    }//GEN-LAST:event_btnAccionActionPerformed

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
       if(yaOrdeno){//si ya habíamos ordenado entonces al actualizar el jlist ponemos los alumnos ordenados
         model.clear();
      lstEstudiantes.setModel(model);
      for(String a : thisArray){model.addElement(a);}        
      lstEstudiantes.setModel(model);
       
       }else{//si aun no habíamos ordenado pone los alumnos desordenados
       ArrayList<Alumno> arrayalumno;
       arrayalumno=manejador.getArrayAlumno();
       model.clear();
       for(Alumno a: arrayalumno){ model.addElement(a.getDatos());}
       lstEstudiantes.setModel(model);}
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void btnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOrdenarActionPerformed
        //ordena los alumnos usando el método ordenaralumnos que devuelve un arraylist de strings con los datos de los alumnos
        yaOrdeno=true;
        thisArray=manejador.OrdenarAlumnos(manejador.getArrayAlumno());
        model.clear();
        lstEstudiantes.setModel(model);
      for(String a : thisArray){ model.addElement(a);}        
    lstEstudiantes.setModel(model);

    }//GEN-LAST:event_btnOrdenarActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btGroup;
    private javax.swing.JButton btnAccion;
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnOrdenar;
    private javax.swing.JLabel btnSalir;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel labelCuadro;
    private javax.swing.JLabel lblConteo;
    private static javax.swing.JLabel lblResultado;
    private javax.swing.JList<String> lstEstudiantes;
    private javax.swing.JPanel pnlEntrada;
    private javax.swing.JPanel pnlFondo;
    private javax.swing.JPanel pnlLista;
    private javax.swing.JPanel pnlUp;
    private javax.swing.JRadioButton rbtnAgregar;
    private javax.swing.JRadioButton rbtnBuscar;
    private javax.swing.JRadioButton rbtnEditar;
    private javax.swing.JRadioButton rbtnEliminar;
    private javax.swing.JScrollPane scrlLista;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtGrado;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
