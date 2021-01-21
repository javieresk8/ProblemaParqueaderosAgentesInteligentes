/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenia;

import org.jgap.IChromosome;

/**
 *
 * @author Admin
 */
public class Mostrar {
    Integer [] individuo;
      /*1 y 2 pasan 4, la 3 pasa 2 */
    /*Se le pasa un entero que indica la cantidad de bits asignados para la parte entera */
    public String mostrarIndividuo(IChromosome ind){
        individuo = new Integer[ind.size()];
        String valorX = "";
        String valorY = "";
        
        for(int i = 0; i < individuo.length;i++){
            individuo[i] = (Integer) ind.getGene(i).getAllele(); 

        }
        
        for (int i=0; i < 4; i++){
            valorX += individuo[i].toString();

        }
        int valorXint = Integer.parseInt(valorX, 2);
        valorY += individuo[4].toString() ;
        valorY += individuo[5].toString();

        int valorYint = Integer.parseInt(valorY, 2);
        System.out.println(valorXint + ","+ valorYint); 
        return valorXint + ","+ valorYint;
    }
    
    public void mostrarTodosIndividuos(IChromosome[] ind){
        for (IChromosome cromosoma: ind){
            mostrarIndividuo(cromosoma);
        }
        
    }
    
    

}
