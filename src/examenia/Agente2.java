
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
public class Agente2 extends Agent {
    private Contenedor contenedor;
    public static Parqueadero parking;
    @Override
    protected void setup() {
        super.setup();
        parking = (Parqueadero)getArguments()[0];
        System.out.println("Soy Agente 2 he nacido, el parking es: \n" + parking.toString());
        addBehaviour(new Comportamiento());
    }
    
    
    @Override
    protected void takeDown() {
        
        System.out.println("Ya no hay parqueaderos, Agente 2 muerto");
    }

    
    class Comportamiento extends CyclicBehaviour{

        @Override
        public void action() {
            try {
                ACLMessage acl = blockingReceive();
                String msj = acl.getContent();
                Parqueadero parqueadero = (Parqueadero)getArguments()[0];
                System.out.println("Soy el Agente 2 he recibido el mensaje: " + msj);
                /*Consulta si hay plazas disponibles*/
                if(parqueadero.revisarPlazasDisponibles()){
                    /*Ejecutamos AlgGen para buscar la plaza mas cercana */
                    System.out.println("Procedo a calcular el parqueadero mas cercano...");
                    Prueba1 algGen = new Prueba1(parqueadero);
                    String resultado = algGen.empezar();
                    System.out.println("Soy el agente 2 y he recibido el resultado:" + resultado);
                    String [] coordenadas = resultado.split(",");


                    String respuesta = "El parqueadero esta en el bloque " + coordenadas[0] + ", aparcamiento #" + coordenadas[1];
                    new EnviarMensaje().enviarMensajeString(ACLMessage.REQUEST, "Ag1", getAgent(), respuesta, "Ag2-Ag1");
                    /*Procede a marcarlo como ocupado*/
                    int x = Integer.parseInt(coordenadas[0]);
                    int y = Integer.parseInt(coordenadas[1]);
                    Agente2.parking.marcarOcupado(x, y);
                    System.out.println("Marque como ocupado a:" + resultado);
                    System.out.println(Agente2.parking.toString());
                    blockingReceive();
                } else {
                    /*Este caso ya no hay plazas disponibles*/
                    new EnviarMensaje().enviarMensajeString(ACLMessage.REQUEST, "Ag1", getAgent(), "NOHAY", "Ag2-Ag1");
                    doDelete();
                }
                
                
            } catch (Exception e) {
            }
            
            
            
        }
        
    }
    
    
}


