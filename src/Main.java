import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Main {
    public static void main(String [] args){
        /*
        * Hay que recordar que Sobreescribir equals() y hashCode() funciona nos ayuda a evaular la igualdad
        * de los objetos y es mayormente utilizado para Value Objects (Objetos que representan DATOS)
        *
        * Por ejemplo:
        * class Persona{
        *   PersonaId id;
        * }
        *
        * class PersonaId{
        *   Long valor;
        * }
        *
        * */

        //Creamos una Fruta de referencia. Objeto piña.
        Fruta piña = new Fruta("Amarilla", false);

        // Creamos una piña diferente.
        Fruta piñaConLentes =  new Fruta("Amarilla", true);
        // Creamos una piña sin lentes, lo cual es igual al objeto piña.
        Fruta piñaSinLentes = new Fruta("Amarilla", false);

        // Evaluamos la igualdad de los Objectos
        System.out.println("¿El objeto piña es igual que el objeto piñaConLentes? " + piña.equals(piñaConLentes));
        System.out.println("¿El objeto piña es igual que el objeto piñaSinLentes? " + piña.equals(piñaSinLentes));
        System.out.println("El objeto piña es una: " + piña.toString());

        // Para validar la implementación de la sobreescritura de equals() y hashCode(), ponemos los valores
        // en una colección que no acepte duplicados.
        Map<Fruta, Integer> piñas = new HashMap<>();

        piñas.put(piña, 1);
        piñas.put(piñaSinLentes, 2);
        piñas.put(piñaConLentes, 3);

        System.out.println();

        // Imprimimos las frutas agregadas a la Colección.
        System.out.println("Frutas en la Colección: ");
        for (Map.Entry<Fruta, Integer> pinia :
                piñas.entrySet()) {
            System.out.println(pinia.toString());
        }
    }
}

class Fruta{

    String color;
    boolean lentes;

    public Fruta(String color, boolean lentes){
        this.color = color;
        this.lentes = lentes;
    }

    // Sobreescribimos el método equals para poder comparar si los objetos son iguales.
    @Override
    public boolean equals(Object obj){
        // si es el mismo objeto, entonces sí son iguales.
        if(this == obj){
            return true;
        }

        // Si el objeto que estamos comparando es igual a nulo
        // Ó, el objeto que estamos comparando diferente a la clase Fruta
        // entonces sabemos que no son iguales.
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }

        // Si el objeto que estamos comparando es una instancia de la clase Fruta, seguimos evaluando.
        if(obj instanceof Fruta){
            // Hacemos una conversión de la clase Object a la clase Fruta.
            Fruta objFruta = (Fruta)obj;

            // Si los valores de las propiedades son iguales, entonces decimos que sí son efectivamente el mismo objeto.
            return objFruta.color.equals(this.color)
                    && objFruta.lentes == this.lentes;
        }

        // En cualquier caso donde no se cumplan los escenarios anteriores, decimos que los objetos no son iguales.
        return false;
    }

    // Si el método equals es sobreescrito, entonces el método hashCode también tiene que ser sobreescrito para
    // el buen funcionamiento de las colecciones que utilizan Hash como identificador - Map, HashMap, HashTable, etc.
    @Override
    public int hashCode(){
        return Objects.hash(this.color, this.lentes);
    }

    // Sobreescribimos el método toString() para que no solo se imprima el hash.
    @Override
    public String toString(){
        return "Clase Fruta {color: "+ this.color +", lentes: " + this.lentes + "}";
    }
}