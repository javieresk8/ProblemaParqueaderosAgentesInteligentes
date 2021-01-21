/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenia;
import org.jgap.FitnessFunction;
import org.jgap.IChromosome;
/**
 *
 * @author Admin
 */
public class FuncionAptitud1 extends FitnessFunction {
    /*Optimiza la ecuacion de la distancia euclidiana desde el punto [0,0] al punto [x,y]*/
    /*sqrt(x^2 + y^2)*/
    private double fitness;
    public static double ajuste;
    private Parqueadero parking;
    public FuncionAptitud1(Parqueadero parking) {
        this.parking = parking;
        fitness = 0;
        
    }
    @Override
    protected double evaluate(IChromosome ic) {
        evaluar(ic);
        return fitness;
    }
    
    private void evaluar(IChromosome ind) {
        Integer individuo[] = new Integer[ind.size()];
        String valorX = "";
        String valorY = "";
        
        for(int i = 0; i < individuo.length;i++){
            individuo[i] = (Integer) ind.getGene(i).getAllele();         
        }
        
        for (int i=0; i < 4; i++){
            valorX += individuo[i].toString();
        }
        int valorXint = Integer.parseInt(valorX, 2);
        valorY += individuo[4].toString();
        valorY += individuo[5].toString();
        int valorYint = Integer.parseInt(valorY, 2);
        fitness = Math.sqrt(73) - calcularFuncion(valorXint, valorYint); //Raiz de 73 porque el mayor punto se consigue con[8,3]
        if (valorYint >= 9){
            fitness = 0;
        }
        //Validamos que se calcule con un valor disponible
        //necesitamos el mapa del parking para eso
        if (!this.parking.consultarPlaza(valorXint, valorYint)){
            fitness = 0;
        }
        //System.out.println("El vlaor que se calculo fue " + fitness + "con x =" + valorXint + " y = "+valorYint);
        
        
    }
    
    private double calcularFuncion(int x, int y){
        return  Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }
}
