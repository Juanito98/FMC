/**
 * UTM
 */
public class UTM {

    public static class Transition {
        int nextState;
        int bitToWrite;
        int move; // 0 -> Right, 1 --> Left

        public Transition(int nextState, int bitToWrite, int move) {
            this.nextState = nextState;
            this.bitToWrite = bitToWrite;
            this.move = move;
        }
    }
    
    private static int statesNumber;
    private static int haltingState = 63; // ? TODO: Corregir si me equivoco
    private static Transition[][] adj;

    // TODO: Impplementar
    private static void makeStateGraph(String TT) {
        // Conseguir statesNumber
        statesNumber = TT.length() / 16; // TODO: no estoy seguro, tal vez
        adj = new Transition[statesNumber][2];
        // Añadir todas las transiciones
        // Por ejemplo, si estoy sobre un 0 en el estado 3, inicializo
        adj[3][0] = new Transition(0, 0, 0);
        // O si veo un 1 y estoy en el estado 3, puedo hacer
        adj[3][1] = new Transition(3, 0, 1);
        // La idea es más o menos leer cada ¿8 bits?
        for (int i = 0; i < statesNumber; ++i) {
            for (int bitInState = 0; bitInState <= 1; ++bitInState) {
                adj[i][bitInState] = new Transition(0,0,0);
            }
        }
    }

    // TODO: Implementar todos la informacion necesaria
    // Checar case.out para saber qué tenemos que imprimir
    static String newTape(String TT, String Cinta, int N, int P) {
        // Para no alterar cinta, creemos una copia
        StringBuilder currentTape = new StringBuilder(Cinta);
        int currentPosition = P;

        makeStateGraph(TT);
        int currentState = 0;
        for (int i = 0; i < N || currentState == haltingState; ++i) {
            int biteInState = currentTape.charAt(currentPosition) - '0';
            Transition t = adj[currentState][biteInState];
            currentTape.setCharAt(currentPosition, (char) (t.bitToWrite + '0'));
            currentState = t.nextState;
            currentPosition += (t.move == 0 ? 1 : -1);
        }
        return currentTape.toString();
    }
}