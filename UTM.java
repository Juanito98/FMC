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
    private static int haltingState = 63;
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
            int State = 0;
            for (int j = iState + 2; j < iState + 8; j++) {
                State = State * 2;
                if (TT.substring(j, j + 1).equals("1"))
                    State++;
            }
            adj[i][0] = new Transition(State, bitTW, Mv);
            // leer segunda parte, donde se lee un 1.
            bitTW = Integer.parseInt(TT.substring(iState + 8, iState + 9));
            Mv = Integer.parseInt(TT.substring(iState + 9, iState + 10));
            State = 0;
            for (int j = iState + 10; j < iState + 16; j++) {
                State = State * 2;
                if (TT.substring(j, j + 1).equals("1"))
                    State++;
            }
            adj[i][1] = new Transition(State, bitTW, Mv);
        }
    }

    // TODO: Imprimir toda la informacion necesaria
    // Checar case.out para saber qué tenemos que imprimir
    static String newTape(String TT, String Cinta, int N, int P) {
        makeStateGraph(TT);
        // Para no alterar cinta, creemos una copia
        StringBuilder currentTape = new StringBuilder(Cinta);
        int currentPosition = P;
        int currentState = 0;
        for (int i = 0; i < N && currentState != haltingState; ++i) {
            int biteInState = currentTape.charAt(currentPosition) - '0';
            Transition t = adj[currentState][biteInState];
            currentTape.setCharAt(currentPosition, (char) (t.bitToWrite + '0'));
            currentState = t.nextState;
            currentPosition += (t.move == 0 ? 1 : -1);
        }

        return currentTape.toString();
    }
}