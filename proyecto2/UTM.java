package proyecto2;

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

    // Guardamos el numero de estados de la TM
    private static int statesNumber;
    private static final int HALTING_STATE = 63;

    // Algunas variables de salida que serán de utilidad
    private static boolean stateReached[];
    private static int statesReached;

    // adj[Estado][BitInPosition] --> qué transición hacer si estas en Estado
    // estás parado sobre un 0 o 1 (BitInPosition)
    // Ej: adj[0][1] quiere decir que estoy en estado 0 y estoy parado sobre un 1
    private static Transition[][] adj;

    private static void makeStateGraph(String TT) {
        // Conseguir statesNumber
        statesNumber = TT.length() / 16;
        adj = new Transition[statesNumber][2];
        // Añadir todas las transiciones
        for (int i = 0; i < statesNumber; ++i) {
            int iState = i * 16; // Dependiendo del estado vamos a estar en el n-esimo bit.
            // leer primera parte, donde se lee un 0.
            int bitTW = Integer.parseInt(TT.substring(iState, iState + 1));
            int Mv = Integer.parseInt(TT.substring(iState + 1, iState + 2));
            // Para obtener el estado, tenemos que convertir de binario a decimal
            int State = 0;
            for (int j = iState + 2; j < iState + 8; j++) {
                State <<= 1;
                if (TT.substring(j, j + 1).equals("1"))
                    State++;
            }
            adj[i][0] = new Transition(State, bitTW, Mv);
            // leer segunda parte, donde se lee un 1.
            bitTW = Integer.parseInt(TT.substring(iState + 8, iState + 9));
            Mv = Integer.parseInt(TT.substring(iState + 9, iState + 10));
            State = 0;
            for (int j = iState + 10; j < iState + 16; j++) {
                State <<= 1;
                if (TT.substring(j, j + 1).equals("1"))
                    State++;
            }
            adj[i][1] = new Transition(State, bitTW, Mv);
        }
    }

    static String newTape(String TT, String Cinta, int N, int P) {
        // Init params
        makeStateGraph(TT);
        stateReached = new boolean[statesNumber];
        statesReached = 0;
        
        // Para no alterar cinta, creemos una copia
        StringBuilder currentTape = new StringBuilder(Cinta);
        int currentPosition = P;
        int currentState = 0;

        for (int i = 0; i < N && currentState != HALTING_STATE; ++i) {
            if (!stateReached[currentState]) {
                stateReached[currentState] = true;
                statesReached++;
            }
            // Obtenemos el bit sobre el que estamos en la cinta
            int biteInState = currentTape.charAt(currentPosition) - '0';
            // Ya que tenemos estado y bit sobre el que estamos,
            // podemos obtener la transición por hacer
            Transition t = adj[currentState][biteInState];
            // Escribimos el bit correspondiente
            currentTape.setCharAt(currentPosition, (char) (t.bitToWrite + '0'));

            // Nos movemos derecha o izquierda
            currentPosition += (t.move == 0 ? 1 : -1);
            // Cambiamos de estado
            currentState = t.nextState;
        }

        return currentTape.toString();
    }

    static int getStatesReached() {
        return statesReached;
    }
}