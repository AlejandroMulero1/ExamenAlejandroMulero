import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {

        Conexion conexion=new Conexion();
        Session session=conexion.abrirSesion();
        session.close();
        conexion.close();
    }



}
