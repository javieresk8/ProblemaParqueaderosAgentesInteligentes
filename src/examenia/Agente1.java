
package examenia;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Javier Erazo
 */
public class Agente1 extends Agent {
    private Contenedor contenedor;
    @Override
    protected void setup() {
        super.setup(); //To change body of generated methods, choose Tools | Templates.
        addBehaviour(new Comportamiento());
    }
    
    
    @Override
    protected void takeDown() {
        
        System.out.println("Voy a morir Agente1, ya no hay parqueaderos");
    }

    
    class Comportamiento extends CyclicBehaviour{

        @Override
        public void action() {
            PlacaRandom placa = (PlacaRandom)getArguments()[0];
            //
            System.out.println("Naci con la placa: " + placa.getPlaca());
            
            new EnviarMensaje().enviarMensajeString(ACLMessage.REQUEST, "Ag2", getAgent(), "Busca", "Ag1-Ag2");
           // contenedor = (Contenedor)getArguments()[0];
//           Parqueadero park = new Parqueadero();
//            System.out.println(park.toString());
            ACLMessage acl = blockingReceive();
            String msjag2 = acl.getContent();
            System.out.println("He recibido respuesta de Ag2, me dice que:" + msjag2);
            
            if(msjag2.equalsIgnoreCase("NOHAY")){
                /*Caso donde ya no hay parqueaderos disponibles*/
                new EnviarMensaje().enviarMensajeString(ACLMessage.REQUEST, "Ag3", getAgent(), msjag2, "Ag1-Ag3");
                doDelete();
                
            } else {
                String msjAg3 = "Para la Placa " + placa.getPlaca() +" " + msjag2;
                new EnviarMensaje().enviarMensajeString(ACLMessage.INFORM, "Ag3", getAgent(), msjAg3, "Ag1-Ag3");
                System.err.println("Agente1 me pongo a esperar 2 segundos");
                doWait(2000);
                placa.regenerarPlaca();
                //System.err.println("Agente1 me pongo a esperar 5 segundos");
                //block(5000);
               // wait(5000);
                    //restart();
                    //blockingReceive();
            }
            
            
            
        }
        
    }
    
    
}


