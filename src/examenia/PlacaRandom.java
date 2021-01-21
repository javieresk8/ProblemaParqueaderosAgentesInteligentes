
package examenia;

import java.io.Serializable;
import java.util.Random;

/**
 *
 * @author Admin
 */
public class PlacaRandom implements Serializable{
    
    String placa;
    
    public PlacaRandom(){
        this.placa = generarPlaca();
    }

    public String getPlaca() {
        return placa;
    }
    
    public String generarPlaca(){
        String pl= "";
        Random r = new Random(System.currentTimeMillis());
        /*Generamos 3 letras mayusculas aleatorias*/
        for (int i = 0; i < 3; i++){
            /*genera numero entre 65 y 90*/
            int num = r.nextInt(26) + 65;
            char letra = (char)num;
            pl += letra;  
        }
        for (int i = 0; i < 4; i++){
            /*genera numero entre 65 y 90*/
            int num = r.nextInt(10);
            pl += String.valueOf(num);  
        }
        
        
        return pl;
    }
    
}
