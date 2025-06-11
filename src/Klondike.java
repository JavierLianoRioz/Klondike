
public class Klondike {

    private boolean jugando = true;
    private Menu menu = new Menu();

    private Baraja baraja;
    private Descarte descarte;
    private static final int CANTIDAD_PALOS = 4;
    private Palo[] palos = new Palo[CANTIDAD_PALOS];
    private static final int CANTIDAD_COLUMNAS = 7;
    private Columna[] columnas = new Columna[CANTIDAD_COLUMNAS];

    public Klondike() {
        baraja = new Baraja();
        descarte = new Descarte();

        for (Palo palo : palos) palo = new Palo();

        for (int index = 0; index < columnas.length; index++) columnas[index] = new Columna(index + 1);
    }

    public void iniciar() {
        do {
            menu.mostrarOpciones();
            mostrarTapete();
            seleccionarOpcion(menu.preguntarOpcion());
        } while (estaJugando());
    }

    private boolean estaJugando() {
        return jugando;
    }

    private void seleccionarOpcion(int indice) {
        switch (indice) {
            case 1 -> baraja.moverA(descarte);
            case 2 -> descarte.moverA(elegirPalo("A"));
            case 3 -> descarte.moverA(elegirColumna("A"));
            case 4 -> elegirPalo("De").moverA(elegirColumna("A"));
            case 5 -> elegirColumna("De").moverA(elegirPalo("A"));
            case 6 -> elegirColumna("De").moverA(elegirColumna("A"));
            case 7 -> elegirColumna("De").voltear();
            case 8 -> descarte.moverA(baraja);
            case 9 -> jugando = false;
        }
    }

    private Columna elegirColumna(String prefijo) {
        int eleccion = escogerOpcion("¿"+ prefijo, CANTIDAD_COLUMNAS, "que columna?");
        return columnas[eleccion - 1];
    }

    private int escogerOpcion(String prefijo, int maximo, String mensaje) {
        Console console = new Console();
        return console.readInt(prefijo + " " + mensaje + "[1-" + maximo + "]");
    }

    private Palo elegirPalo(String prefijo) {
        int eleccion = escogerOpcion("¿"+ prefijo, CANTIDAD_COLUMNAS, "que palo?");
        return palos[eleccion - 1];
    }

    private void mostrarTapete() {
        saltoDeLinea();

        System.out.print("Baraja: "); 
        baraja.mostrar(); 
        saltoDeLinea();

        saltoDeLinea();
        
        System.out.print("Descarte: "); 
        descarte.mostrar();
        saltoDeLinea();

        saltoDeLinea();

        for (int index = 0; index < columnas.length; index++) {
            System.out.print((index+1)+"º Palo:");
            columnas[index].mostar();
            saltoDeLinea();
        }

        saltoDeLinea();
        
        for (int index = 0; index < palos.length; index++) {
            System.out.print((index+1)+"º Palo:");
            palos[index].mostar();
            saltoDeLinea();
        }

        saltoDeLinea();
    }

    private void saltoDeLinea() {
        System.out.println();
    }


}
