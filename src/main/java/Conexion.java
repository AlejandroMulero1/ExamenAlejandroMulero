import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Conexion {
    private SessionFactory sessionFactory;

    /**
     * Al crear un objeto de la clase conexión por defecto se ejecuta el setup
     */
    public Conexion(){
        setUp();
    }

    /**
     * Método que abre una sesión a partir de la sesión factory privada de la clase
     * @return
     */
    public Session abrirSesion(){
        return sessionFactory.openSession();
    }

    /**
     * Método que cierra la sessiónfactory
     */
    public void close(){
        sessionFactory.close();
    }

    /**
     * Método que establece la conexión con la base de datos a partir del hibernate.cfg.xml
     */
    private void setUp() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // por defecto: hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
            e.printStackTrace();
        }
    }
}
