
package examenia;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.StringTokenizer;
/**
 *
 * @author Javier Erazo
 */
public class Agente3 extends Agent {
    
    @Override
    protected void setup() {
        super.setup();
        
        System.out.println("Soy Agente 3 he nacido");
        addBehaviour(new Comportamiento());
    }
    
    
    @Override
    protected void takeDown() {
        
        System.out.println("Voy a morir Agente3, no hay parqueaderos");
    }

    
    class Comportamiento extends CyclicBehaviour{

        @Override
        public void action() {
            try {
                ACLMessage acl = blockingReceive();
                String msj = acl.getContent();
                if(msj.equalsIgnoreCase("NOHAY")){
                    /*Caso donde ya no hay parqueaderos disponibles*/
                    doDelete();
                
                } else {
                    System.out.println("Soy el Agente 3 he recibido el mensaje: \n\n\n" + 
                        "========================BIENVENIDO==============================\n"+msj);
                    //blockingReceive();
                }
                
               
                
            } catch (Exception e) {
            }
            
            
            
        }
        
    }
    
    
}


