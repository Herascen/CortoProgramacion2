
package cortoprogramacion2;

import java.util.Calendar;


public class Alumno {
    private String Nombre;
    private String Apellido;
    private String Carnet;
    public Alumno(String nom, String apell){
        Nombre=nom;
        Apellido=apell;
        Carnet=GeneradorCarnet();
    }
    public void setNombre(String nombre){
        Nombre=nombre;
    }
    public void setApellido(String apellido){
        Apellido=apellido;
    }
    public String getNombre(){//este método se creó pensando en su utilización a la hora de editar, el usuario ingresa un carnet y una nueva ventana aparece con dos textfields y tendremos que poner el nombre en el primer textfield
        
        
        return Nombre;
    }
    public String getApellido(){//método creado con el mismo fin del método getNombre
        return Apellido;
    }
    public String getDatos(){
        return Nombre + " " + Apellido + " " + Carnet;
    }
    public String getCarnet(){//el siguiente método genera un carnet con las iniciales de del nombre y el apelldio seguido por los minutos que marca el sistema en ese momento mas dos números aleatorios...
        
        
        return Carnet;
    }
    
    public String GeneradorCarnet(){
        String codigo="";
        String iniciales="" + Nombre.charAt(0)+Apellido.charAt(0);//obtención de las iniciales
        
        Calendar calendario=Calendar.getInstance();//obtención de los minutos
        int minutos=calendario.get(Calendar.MINUTE);
        String minutes="" + minutos;
        if(minutos<10){if(minutos==0){minutes="00";}else{minutes="0" + minutos;}}
        
        String numero="";//obtención de dos números aleatorios
        double num=Math.random()*100;
        int num2=(int)num;
        if(num<10){numero="0" + num2;}
        else{numero="" + num2;}
        
        
       codigo=iniciales + minutes + numero;
        
        
        
        return codigo;
    }
    
}
