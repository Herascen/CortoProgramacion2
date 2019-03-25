
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
      
  }
  public void BuscarAlumno(String carnet){//devolverá los datos del alumno 
      
  }
  public void EliminarAlumno(String carnet){
      
  }
  public void EditarAlumno(String carnet){
      
  }
}

