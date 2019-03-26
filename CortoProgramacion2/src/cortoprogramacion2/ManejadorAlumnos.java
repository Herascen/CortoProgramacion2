
package cortoprogramacion2;
import java.util.ArrayList;
//esta clase creará las instancias de alumno y les asignará los datos correspondientes antes de agregarlos al arraylist, también editará el arraylist para eliminar, buscar y editar alumnos
//la clase será instanciada en los manejadores de eventos de los componentes gui
public class ManejadorAlumnos {
   private final ArrayList<Alumno> ArrayAlumno;
    public ManejadorAlumnos(){
        this.ArrayAlumno = new ArrayList<>();
        
    }
  public ArrayList<Alumno> getArrayAlumno(){//devuelve el arraylist de alumnos para su uso en el JList
      return ArrayAlumno;
  }  
  public void IngresarAlumno(String nombre, String apellido){//asignará los datos a un alumno al llamar al método constructor y luego lo agregará al array de alumnos
      ArrayAlumno.add(new Alumno(nombre, apellido));
  }
  public String  BuscarAlumno(String carnet,  int posicion){//devolverá los datos del alumno 
      if(posicion==ArrayAlumno.size() || ArrayAlumno.get(posicion).equals(carnet)){
          if(ArrayAlumno.get(posicion).equals(carnet)){
          return ArrayAlumno.get(posicion).getDatos();}
          else{return "Alumno no encontrado";}
      }
      else{return BuscarAlumno(carnet, posicion+1);}
      
  }
  public void EliminarAlumno(String carnet){
      
  }
  public void EditarAlumno(String carnet){
      
  }
}

