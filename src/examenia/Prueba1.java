/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenia;
import org.jgap.Chromosome;
import org.jgap.Configuration;
import org.jgap.FitnessFunction;
import org.jgap.Gene;
import org.jgap.Genotype;
import org.jgap.IChromosome;
import org.jgap.InvalidConfigurationException;
import org.jgap.impl.DefaultConfiguration;
import org.jgap.impl.IntegerGene;
/**
 *
 * @author Admin
 */
public class Prueba1 {
    Parqueadero parking;
    public Prueba1(Parqueadero parking){
            this.parking = parking;
    }
    public String empezar(){
        String resultado = null;
        try {
            Configuration configuracion = new DefaultConfiguration();
            FitnessFunction funcion = new FuncionAptitud1(parking);
            configuracion.setFitnessFunction(funcion);
            /*Se utiliza 4 bits para x(maximo 15 ), 2 bits para y (maximo 3)*/
            Gene[] genes = new Gene[6];
            for(int i = 0; i < genes.length; i++){
                genes[i] = new IntegerGene(configuracion, 0, 1);
            }
            Chromosome cromosomaNumero = new Chromosome(configuracion, genes);
            configuracion.setSampleChromosome(cromosomaNumero);
            configuracion.setPopulationSize(5);
            Genotype poblacion = Genotype.randomInitialGenotype(configuracion);
            System.out.println("Poblacion iniciada");
            Mostrar show = new Mostrar();
            
            for(int m=0; m < 10; m++){
                System.out.println("-------------------Inicio generacion-------------------");
                System.out.println("Iteracion #" + m);
                show.mostrarTodosIndividuos(poblacion.getChromosomes());
               // System.out.println("llegue");
                poblacion.evolve(12);
                IChromosome mejorIndividuo = poblacion.getFittestChromosome();
                show.mostrarIndividuo(mejorIndividuo);
                //valorY = mejorIndividuo.getFitnessValue();
                System.out.println("El valor de aptitud obtenido es: " + mejorIndividuo.getFitnessValue());
                System.out.println("-----------------------------------Fin Generacion---------------------------");
                System.out.println("Mejor individuo: ");
                resultado = show.mostrarIndividuo(poblacion.getFittestChromosome()); 
               // System.out.println("he terminado");
                
            }
        } catch (InvalidConfigurationException e) {
            System.err.println("No se pudo ejecutar el AG...");
        }
        return resultado;
    }
}
