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
    private static String TuringMachine;
    private static int statesNumber;
    private static final int HALTING_STATE = 63;

    // Algunas variables de salida que serán de utilidad
    private static boolean stateReached[];
    private static int statesReached;
    private static int productive;
    private static int MaxPosition;
    private static int MinPosition;

    // adj[Estado][BitInPosition] --> qué transición hacer si estas en Estado
    // estás parado sobre un 0 o 1 (BitInPosition)
    // Ej: adj[0][1] quiere decir que estoy en estado 0 y estoy parado sobre un 1
    private static Transition[][] adj;

    private static void makeStateGraph(String TT) {
        // Conseguir statesNumber
        TuringMachine = TT;
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
        int currentPosition = P;
        int currentState = 0;
        productive = 0;
        MaxPosition = P;
        MinPosition = P;
        // Para no alterar cinta, creemos una copia
        StringBuilder currentTape = new StringBuilder(Cinta);


        for (int i = 0; i < N && currentState != HALTING_STATE; ++i) {
            // Si es un estado que no habíamos visitado
            // lo contamos y visitamos
            if (!stateReached[currentState]) {
                stateReached[currentState] = true;
                statesReached++;
            }
            // Actualizamos la posición mínima y máxima
            // que ha tocado la cabeza
            if (currentPosition > MaxPosition) {
                MaxPosition = currentPosition;
            }
            if (currentPosition < MinPosition) {
                MinPosition = currentPosition;
            }
            if (currentPosition < 0 || currentPosition >= currentTape.length()) {
                break;
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
        if (!stateReached[currentState]) {
            stateReached[currentState] = true;
            statesReached++;
        }

        // Tomaremos el tamaño de la cinta para leer caracter por caracter y saber
        // cuantos 1 tiene la cinta final
        char ch;
        for (int i = 0; i < currentTape.length(); i++) {
            ch = currentTape.charAt(i);
            if (ch == '1') {
                productive++;
            }
        }

        return currentTape.toString();
    }

    static int getStatesReached() {
        return statesReached;
    }
    static int getProductiveness() {
        return productive;
    }
    static int getMaxPosition() {
        return MaxPosition;
    }
    static int getMinPosition() {
        return MinPosition;
    }

    // Esta función imprime la tabla de estados comprimida;
    // esto es, solo los estados que se visitaron
    // En caso que algun estado visitado tenga una arista que 
    // apunte a un estado que no visitamos, en vez de eso dirige
    // a HALT.
    static void print() {
		int ix16, x0_I, x1_I, Estado;
		String x0_M, x1_M;
		System.out.println("Hay " + statesReached + " estados en la Maquina de Turing");
		System.out.println(" EA | O | M | SE || O | M | SE |");
		System.out.println(" -------------------------------");
		for (int i = 0; i < statesNumber; i++) {
            if (!stateReached[i]) {
                // No lo imprimimos si no fue visitado
                continue;
            }
			System.out.printf("%4.0f|", (float) i);
            ix16 = i * 16;

			x0_I = Integer.parseInt(TuringMachine.substring(ix16, ix16 + 1));
			x0_M = TuringMachine.substring(ix16 + 1, ix16 + 2);
			if (x0_M.equals("0"))
				x0_M = " R |";
			else
				x0_M = " L |";
			System.out.printf("%3.0f|" + x0_M, (float) x0_I);
			Estado = 0;
			for (int j = ix16 + 2; j < ix16 + 8; j++) {
				Estado = Estado * 2;
				if (TuringMachine.substring(j, j + 1).equals("1"))
					Estado++;
				// endif
            } // endFor
            // Notemos que si el estado al que se dirige nunca lo visitamos
            // entonces mejor dirigimos la arista a HALT
			if (Estado == 63 || !stateReached[Estado])
				System.out.print("   H||");
			else
				System.out.printf("%4.0f||", (float) Estado);
            
            // endif
			x1_I = Integer.parseInt(TuringMachine.substring(ix16 + 8, ix16 + 9));
			x1_M = TuringMachine.substring(ix16 + 9, ix16 + 10);
			if (x1_M.equals("0"))
				x1_M = " R |";
			else
				x1_M = " L |";
			System.out.printf("%3.0f|" + x1_M, (float) x1_I);
			Estado = 0;
			for (int j = ix16 + 10; j < ix16 + 16; j++) {
				Estado = Estado * 2;
				if (TuringMachine.substring(j, j + 1).equals("1"))
					Estado++;
				// endif
            } // endFor
            // Notemos que si el estado al que se dirige nunca lo visitamos
            // entonces mejor dirigimos la arista a HALT
			if (Estado == 63 || !stateReached[Estado]) {
				System.out.print("   H|\n");
			} else {
				System.out.printf("%4.0f|\n", (float) Estado);
            } // endif
        } // endFor
    }
}