
package examenia;

/**
 *
 * @author Admin
 */
public class Parqueadero {
    
    int filas;
    int columnas;
    /*Cada elemento del arreglo es una casilla del parking,
    0 = Bloque de via transitable, no se puede parquear
    -1 = Estacionamiento ocupado
    1 = Estacionamiento disponible*/
    
    /*El parqueadero se divide en 3 sectores de 6 parqueaderos cada uno, 
    tiene rutas de acceso a cada uno de ellos*/
    /*  00000
        01110  //Bloque 1
        01110
        00000
        01110  //Bloque 2
        01110
        00000
        01110 //Bloque 3
        01110
        00000*/
    int[][] mapa;
    public Parqueadero(){
        this.filas = 10;
        this.columnas = 5;
        mapa = new int[filas][columnas];
        
        marcarPlazas();
        mapa[8][3]= -1;
        
    }
    public boolean consultarPlaza(int x, int y){
//        System.out.println("Estoy comparando");
        //Validaciones para no tomar valores no disponibles
        if (x <=8  && x>0 && x != 3 && x != 6){
            if(this.mapa[x][y] ==0){
                return false;
            } else if(this.mapa[x][y] == 1) {
                return true;
            }
        }
           
                
        return false;
    }
    public void marcarPlazas(){
        for(int i = 1; i < 9; i++){
            for(int j = 1; j < 4; j++){
                if (i == 3 || i == 6){
                    continue;
                }
                this.mapa[i][j] = 1; 
            }
            
        }
        
    }
    
    public boolean revisarPlazasDisponibles(){
        
        for (int i = 1; i <= 9; i++ ){
            for (int j = 1; j <= 3; j++){
                if (this.mapa[i][j] == 1)
                    return true;
            }
            
        }
        return false;
    }
    
    public void marcarOcupado(int i, int j){
        this.mapa[i][j] = -1;
    }

    @Override
    public String toString() {
        String str = "";
        
        for (int i = 0; i < filas; i++ ){
            for (int j = 0; j < columnas; j++){
                str += this.mapa[i][j];
            }
            str += "\n";
        }
        
        return str;
    }
    
    
    
}
