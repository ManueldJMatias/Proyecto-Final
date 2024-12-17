import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.util.Arrays;

public class Main
{
    public static void main (String args[]){
        String archivoEntrada = "Lugares.csv";
        Vector<LugarTuristico>LugarT=new Vector<>();
        ArbolDos arbol= new ArbolDos();
        ArbolAVL costos=new ArbolAVL();
        ColaDinamica cola= new ColaDinamica();
        Scanner scanner=new Scanner(System.in);
        Grafo grafo = new Grafo();
        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada))) {
            String linea;

            String encabezado = br.readLine();
            System.out.println("Encabezado: " + encabezado);

            while ((linea = br.readLine()) != null) {

                String[] datos = linea.split(",");

                String nombres = datos[0];
                int horas=Integer.parseInt(datos[4]);
                int costo=Integer.parseInt(datos[5]);
                String tipo = datos[3]; 
                nombres = nombres.replaceAll("\\s+", "").toLowerCase();
                LugarT.add(new LugarTuristico(nombres, datos[1], datos[2], datos[3],horas,costo));
                grafo.agregarNodo(nombres + " (" + tipo + ")"); // Agregar cada lugar como nodo del grafo con su tipo
            }
            for (LugarTuristico Lugar : LugarT) {
                arbol.insertar(Lugar);
                costos.insertar(Lugar);
                System.out.println(Lugar.toString());
                System.out.println("--------------------------------------------------------------------------------------------------------");

            }
            while (true) {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1 - Hacer una cola de destinos");
                System.out.println("2 - Buscar por nombre");
                System.out.println("3 - eliminar en la cola ");
                System.out.println("4 - muestra la cola de lugares");
                System.out.println("5 - Agregar un Destino");
                System.out.println("6 - Eliminar un Destino");
                System.out.println("7 - recorrer InOrden por costo");
                System.out.println("8 - Ordena por QuickSort los costos");
                System.out.println("10.-Agrega aristas");
                System.out.println("9 - Imprime el grafo");
                System.out.println("0 - Salir");
                System.out.print("Opción: ");

                int opcion = scanner.nextInt();
                scanner.nextLine(); 

                switch (opcion) {
                    case 0:
                        System.out.println("Saliendo...");
                        return;

                    case 1:
                        System.out.println("HACIENDO COLA: ");
                        System.out.print("Digite cuántos lugares desea agregar: ");
                        int numero = scanner.nextInt();
                        scanner.nextLine();
                        for (int i = 0; i < numero; i++) {
                            System.out.print("Digite el nombre del lugar turístico: ");
                            String nombre = scanner.nextLine();
                            LugarTuristico lugar = arbol.buscarNombre(nombre);

                            if (lugar != null) {
                                System.out.println("Lugar turístico agregado: " + lugar.getNombre());
                                cola.agregar(lugar);
                            } else {
                                System.out.println("No se encontró el lugar turístico con el nombre: " + nombre);
                            }
                        }

                        break;

                    case 2:
                        System.out.println("BUSQUEDA POR NOMBRE: ");
                        System.out.print("Digite el nombre a buscar: ");
                        String busqueda=scanner.nextLine();
                        LugarTuristico enc = arbol.buscarNombre(busqueda);
                         if (enc != null) {
                                System.out.println("Lugar turístico agregado: " + enc.toString());
                            } else {
                                System.out.println("No se encontró el lugar turístico con el nombre: " + enc);
                            }
                        break;

                    case 3:
                        System.out.println("Eliminar en cola ");
                        try
                        {
                            cola.eliminar();
                        }
                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }

                        break;

                    case 4:
                        cola.imprimir();
                        break;

                        
                    case 5:
                        System.out.println("Agregar Destino: ");
                        System.out.println("Escriba el nombre: ");
                        String nom = scanner.nextLine();
                        String nombres = nom.replaceAll("\\s+", "").toLowerCase();
                        System.out.println("Escriba el estado: ");
                        String estado = scanner.nextLine();
                        System.out.println("Escriba el clima: ");
                        String clima = scanner.nextLine();
                        System.out.println("Escriba el tipo: ");
                        String tipo = scanner.nextLine();
                        System.out.println("Escriba las horas de recorrido: ");
                        int horas = scanner.nextInt();
                        System.out.println("Escriba el costo: ");
                        int costo = scanner.nextInt();
                        LugarTuristico nuevo = new LugarTuristico(nom,estado,clima,tipo,horas,costo);
                        LugarT.add(nuevo);
                        costos.insertar(nuevo);
                        arbol.insertar(nuevo);
                        grafo.agregarNodo(nombres + " (" + tipo + ")");
                        try (FileWriter fw = new FileWriter(archivoEntrada, true)) {
                            fw.write(nuevo.getNombre() + "," + nuevo.getEstado() + "," + nuevo.getClima() + "," + nuevo.getTipo() + nuevo.getHrsRecorrido() + ","+nuevo.getCosto() + ","  +"\n");
                        } catch (IOException e) {
                            System.out.println("Error " + e.getMessage());
                        }

                        System.out.println("Lugar agregado exitosamente:\n" + nuevo);
                        
                        break;


                    case 6:
                            System.out.print("Dijite el nombre a eliminar: ");
                        String eliminar=scanner.nextLine();
                        LugarTuristico e = arbol.buscarNombre(eliminar);
                         if (e != null) {
                             arbol.eliminar(e);
                             costos.eliminar(e);
                             LugarT.remove(e);
                                System.out.println("Lugar turístico Eliminado: " + e.getNombre());
                            } else {
                                System.out.println("No se encontró el lugar turístico con el nombre: " + e);
                            }
                        break;
                    case 7:
                        costos.recorrerInOrden();

                        break;
                         case 8:
                             quicksort q=new quicksort();
                        System.out.println("Ordenando los costos por QuickSort...");
                        int[] costosArray = new int[LugarT.size()];
                        for (int i = 0; i < LugarT.size(); i++) {
                            costosArray[i] = LugarT.get(i).getCosto();
                        }
                        q.quickSort(costosArray);
                        System.out.println("Costos ordenados: " + Arrays.toString(costosArray));
                        break;
                        case 9:
                        System.out.println("Mostrando grafo: ");
                        System.out.println(grafo.toString());                            
                            
                            
                            break;
                            case 10:
                                System.out.println("AGREGAR ARISTA EN EL GRAFO");
                        System.out.print("Ingrese el nodo origen mas su tipo: ");
                        String origen = scanner.nextLine();
                        System.out.print("Ingrese el nodo destino mas su tipo: ");
                        String destino = scanner.nextLine();
                        if (grafo.agregarArista(origen, destino)) {
                            System.out.println("Arista agregada exitosamente entre " + origen + " y " + destino);
                        } else {
                            System.out.println("No se pudo agregar la arista entre " + origen + " y " + destino);
                        }
                        break;

                    default:
                        System.out.println("Opción no válida. Por favor, ingrese un número entre 0 y 6.");
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}

