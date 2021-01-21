
package examenia;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
/**
 *
 * @author Javier Erazo
 */
public class Contenedor {
    AgentController agenteController;
    AgentContainer mainContainer;
    Object[] contenedor = new Object[1];
    
    public void inicializarContenedor(){
        jade.core.Runtime runtime = jade.core.Runtime.instance();
        runtime.setCloseVM(true);
        System.out.println("Runtime ha sido creado");
        Profile profile = new ProfileImpl(null, 1099, null);
        System.out.println("Perfil por defecto creado");
        mainContainer = runtime.createMainContainer(profile);
        System.out.println(String.format("Contenedor creado %s", profile.toString()));
        inicializarAgentes();
        
    }
    
    private void inicializarAgentes(){
        try {
            
            mainContainer.createNewAgent("Ag3", Agente3.class.getName(), null).start();
            Parqueadero parking = new Parqueadero();
            mainContainer.createNewAgent("Ag2", Agente2.class.getName(), new Object[]{parking}).start();
            PlacaRandom placa = new PlacaRandom();
            mainContainer.createNewAgent("Ag1", Agente1.class.getName(), new Object[]{placa}).start();
  
        } catch (Exception e) {
        }
        
    }

    public static void main(String[] args) {
        new Contenedor().inicializarContenedor();
    }
    
}
