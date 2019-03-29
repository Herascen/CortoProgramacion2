
package cortoprogramacion2;
import java.util.ArrayList;
//esta clase creará las instancias de alumno y les asignará los datos correspondientes antes de agregarlos al arraylist, también editará el arraylist para eliminar, buscar y editar alumnos
//la clase será instanciada en los manejadores de eventos de los componentes gui
public class ManejadorAlumnos {
   private final ArrayList<Alumno> ArrayAlumno;
    public ManejadorAlumnos(){
        this.ArrayAlumno = new ArrayList<>();
        
    }
  public ArrayList<Alumno> getArrayAlumno(){//devuelve el arraylist de alumnos para su uso en el método ordenar alumnos
      return ArrayAlumno;
  }  
  public void IngresarAlumno(String nombre, String apellido){//asignará los datos a un alumno al llamar al método constructor y luego lo agregará al array de alumnos
      ArrayAlumno.add(new Alumno(nombre, apellido));
  }
  public String  BuscarAlumno(String carnet,  int posicion){//devolverá los datos del alumno la posición será la posición inicial
      if(posicion==ArrayAlumno.size() || ArrayAlumno.get(posicion).getCarnet().equals(carnet)){
          if(ArrayAlumno.get(posicion).getCarnet().equals(carnet)){
          return ArrayAlumno.get(posicion).getDatos();}
          else{return "Alumno no encontrado";}
      }
      else{return BuscarAlumno(carnet, posicion+1);}
      
  }
  public void EliminarAlumno(String carnet){
      for(int i=0; i<ArrayAlumno.size(); i++){
          if(ArrayAlumno.get(i).getCarnet().equals(carnet)){   ArrayAlumno.remove(i);}
          
      }
      
  }
  public Alumno EditarAlumno(String carnet){//si se encontró el estudiante lo devolverá, en caso de no encontrarlo; el estudiante será igual a null
      //el usuario de este método deberá condiderar lo que se hará dependiendo del resultado
      Alumno estudent=null;
     
      for(int i=0; i<ArrayAlumno.size(); i++){
          if(ArrayAlumno.get(i).getCarnet()==carnet){estudent=ArrayAlumno.get(i);}
          
      }
      return estudent;
  }
public ArrayList<String> OrdenarAlumnos(ArrayList<Alumno> elarray){//recibe el mismo arraylist de el objeto y devuelve un array ordenado alfabéticamente de los datos de los alumnos del array
    ArrayList<String>datos=new ArrayList<>();
    for(int i=0; i<elarray.size(); i++){
        datos.add(elarray.get(i).getDatos());}
  return datos;
}
 
}

