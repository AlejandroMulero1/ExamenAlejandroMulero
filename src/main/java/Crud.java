
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Objects;


public class Crud {

    /**
     * Método que guarda un objeto en la base de datos, a través de una sesión pasada por parámetro, se inicia una transacción
     * y si no se produce ninguna excepción en el guardado hace una commit, al utilizar el saveOrUpdate este método
     * también puede ser utilizado para actualizar
     * @param conexion objeto de la clase Conexión el cual se utilizara para insertar en la base de datos
     * @param obj obj a guardar o actualizar en la base de datos
     */
    public static void saveOrUpdate(Conexion conexion, Object obj) {
        Session session=conexion.abrirSesion();
        //Inicio la transaccion
        Transaction transaction = session.beginTransaction();
        try {
            //Inserto en la BD
            session.saveOrUpdate(obj);

            //Commit si no genera errores
            transaction.commit();

        } catch (Exception e) {
            //Rollback si aparece algun error
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    /**
     * Método que lee un objeto de la base de datos, a través de una sesión pasada por parámetro, se inicia una transacción
     * y si no se produce ninguna excepción en la lectura hace una commit
     * @param conexion objeto de la clase Conexión el cual se utilizara para insertar en la base de datos
     * @param claseEntity Clase a la que pertenece el objeto que se va a leer de la base de datos
     * @param id PK del objeto a leer de la base de datos
     * @return Object
     */
    public static Object read(Conexion conexion, Class claseEntity , int id) {
        Session session=conexion.abrirSesion();
        Object objectDB=null;
        // start a transaction
        Transaction transaction = session.beginTransaction();
        try{
             objectDB = session.load(claseEntity, id);
            // commit transaction
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            e.printStackTrace();
        }
        finally {
            session.close();
        }
        return objectDB ;
    }

    /**
     * Metodo que borra un objeto de la base de datos, a través de una sesión pasada por parámetro, y un
     * load con la id, se inicia una transacción y si no se produce ninguna excepción en el borrado hace una commit
     * @param conexion objeto de la clase Conexión el cual se utilizara para insertar en la base de datos
     * @param claseEntity Clase a la que pertenece el objeto que se va a leer de la base de datos
     * @param id PK del objeto a leer de la base de datos
     */
    public static void delete(Conexion conexion, Class claseEntity, int id) {
        Session session=conexion.abrirSesion();
        Transaction transaction = session.beginTransaction();
        try {
            Object obj = session.load(claseEntity, id);
            session.delete(obj);
            System.out.println("Se ha borrado la fila");
            transaction.commit();
        }catch (Exception e){
            transaction.rollback();
            System.err.println("No se ha encontrado ninguna persona con esa id, vuelva a intentarlo mas tarde");
        }
        finally {
            session.close();
        }
    }
}
