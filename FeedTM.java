
/*
 *	Alimenta una Máquina de Turing
 */
import java.io.*;

class FeedTM {
	static RandomAccessFile Datos;
	static BufferedReader TF;
	static BufferedReader Kbr;

	public static void main(String[] args) throws Exception {
		Kbr = new BufferedReader(new InputStreamReader(System.in));
		String sResp;
		int i;
		boolean ask = false;
		while (true) {
			if (ask) { // No preguntes la primera vez
				System.out.println("\nDesea leer otro archivo?");
				System.out.println("\"S\" para continuar; otro para terminar...)");
				sResp = Kbr.readLine().toUpperCase();
				if (!sResp.equals("S"))
					break;
				// endIf
			} else {
				ask = true; // Pregunta de la segunda en adelante
			} // endif
			System.out.println("Deme el nombre del archivo de datos que quiere leer:");
			String FName = Kbr.readLine().toUpperCase();
			try {
				Datos = new RandomAccessFile(new File(FName), "r");
			} // endTry
			catch (Exception e1) {
				System.out.println("No se encontro \"" + FName + "\"");
				continue;
			} // endCatch
			String Cinta = "", Car;
			while (true) {
				System.out.println("Los datos estan en binario-ASCII? (S/N)");
				sResp = Kbr.readLine().toUpperCase();
				if (sResp.equals("S") || sResp.equals("N"))
					break;
				// endIf
			} // endWhile
			if (sResp.equals("N")) {
				System.out.println("Deme el nombre del archivo de salida de imagen numerica:");
				String FTarget = Kbr.readLine().toUpperCase();
				PrintStream Fps = new PrintStream(new FileOutputStream(new File(FTarget)));
				int BytesEnDatos = 0;
				/*
				 * Averigua el tamaño del archivo en bytes
				 */
				while (true) {
					Datos.seek(BytesEnDatos);
					try {
						Datos.readByte();
					} catch (Exception e) {
						break;
					}
					BytesEnDatos++;
				} // endWhile
				Datos.close();
				System.out.println("Se leyeron " + BytesEnDatos + " datos\n");
				/*
				 * Convierte en binario-ASCII
				 */
				int Y, T;
				Datos = new RandomAccessFile(new File(FName), "r");
				for (i = 0; i < BytesEnDatos; i++) {
					Datos.seek(i);
					Y = Datos.readByte();
					T = Y; // T <-- Número original
					Car = "";
					for (int j = 0; j < 8; j++) {
						if (Y % 2 == 0)
							Car = "0" + Car;
						else
							Car = "1" + Car;
						Y = Y / 2;
					} // endFor
					Cinta = Cinta + Car;
					Fps.printf("%4.0f  ", (float) T, Car); // Escribe cada uno de los bytes leidos
					Fps.println();
				} // endFor
				Fps.close();
			} else {
				Datos.close();
				BufferedReader FCinta;
				FCinta = new BufferedReader(new InputStreamReader(new FileInputStream(new File(FName))));
				Cinta = FCinta.readLine();
				FCinta.close();
			} // endIf
			PrintStream Tape = new PrintStream(new FileOutputStream(new File("Tape.txt")));
			Tape.println(Cinta); // La imagen binaria esta en "Tape.txt"
			Tape.close();
			System.out.println("La imagen binaria-ASCII se halla en \"Tape.txt\"");
			int LongCinta = Cinta.length();
			Datos.close();
			String TTFN = "";
			String TT = "";
			int MTLen;
			while (true) {
				while (true) {
					System.out.println("Deme el nombre del archivo con la Maquina de Turing:");
					TTFN = Kbr.readLine().toUpperCase();
					try {
						TF = new BufferedReader(new InputStreamReader(new FileInputStream(new File(TTFN))));
						System.out.println();
						break;
					} // endTry
					catch (Exception e1) {
						System.out.println("No se encontro \"" + TTFN + "\"");
						continue;
					} // endCatch
				} // endWhile
				/*
				*
				*/
				boolean Forever = false, FF;
				TT = TF.readLine();
				MTLen = TT.length();
				System.out.println(MTLen + " bytes leidos del mapa de la MT");
				int iCar = 0;
				for (i = 0; i < MTLen; i++) {
					Car = TT.substring(i, i + 1);
					try {
						iCar = Integer.parseInt(Car);
						FF = false;
					} catch (Exception e) {
						FF = true;
					}
					if ((iCar != 0 && iCar != 1) || FF) {
						System.out.println("Error en el formato de la Maquina de Turing");
						System.out.println("Deben ser solamente \"0\" o \"1\"");
						Forever = true;
						break; // Exit For
					} // endIf
				} // endFor
				if (Forever)
					continue;
				// endIf
				if (MTLen % 16 != 0) {
					System.out.println("La longitud de la Maquina de Turing debe ser multiplo de 16");
					continue;
				} // endIf
				break; // Exit While
			} // endwhile
			int NumStates = MTLen / 16;
			int ix16, x0_I, x1_I, Estado;
			String x0_M, x1_M;
			System.out.println("Hay " + NumStates + " estados en la Maquina de Turing");
			System.out.println(" EA | O | M | SE || O | M | SE |");
			System.out.println(" -------------------------------");
			for (i = 0; i < NumStates; i++) {
				System.out.printf("%4.0f|", (float) i);
				ix16 = i * 16;
				x0_I = Integer.parseInt(TT.substring(ix16, ix16 + 1));
				x0_M = TT.substring(ix16 + 1, ix16 + 2);
				if (x0_M.equals("0"))
					x0_M = " R |";
				else
					x0_M = " L |";
				System.out.printf("%3.0f|" + x0_M, (float) x0_I);
				Estado = 0;
				for (int j = ix16 + 2; j < ix16 + 8; j++) {
					Estado = Estado * 2;
					if (TT.substring(j, j + 1).equals("1"))
						Estado++;
					// endif
				} // endFor
				if (Estado == 63)
					System.out.print("   H||");
				else
					System.out.printf("%4.0f||", (float) Estado);
				// endif
				x1_I = Integer.parseInt(TT.substring(ix16 + 8, ix16 + 9));
				x1_M = TT.substring(ix16 + 9, ix16 + 10);
				if (x1_M.equals("0"))
					x1_M = " R |";
				else
					x1_M = " L |";
				System.out.printf("%3.0f|" + x1_M, (float) x1_I);
				Estado = 0;
				for (int j = ix16 + 10; j < ix16 + 16; j++) {
					Estado = Estado * 2;
					if (TT.substring(j, j + 1).equals("1"))
						Estado++;
					// endif
				} // endFor
				if (Estado == 63) {
					System.out.print("   H|\n");
				} else {
					System.out.printf("%4.0f|\n", (float) Estado);
				} // endif
			} // endFor
			/*
			 * NUMERO DE TRANSICIONES
			 */
			int N = 0;
			while (true) {
				System.out.println("Deme el numero maximo de transiciones de la Maquina de Turing: ");
				sResp = Kbr.readLine();
				try {
					N = Integer.parseInt(sResp);
					break;
				} // endTry
				catch (Exception e) {
					System.out.println("Error de formato");
					continue;
				} // endCatch
			} // endWhile
			/*
			 * LONGITUD DESEADA DE LA CINTA
			 */
			int M = 0;
			while (true) {
				while (true) {
					System.out.println("Deme el tamano deseado de cinta: ");
					sResp = Kbr.readLine();
					try {
						M = Integer.parseInt(sResp);
						break;
					} // endTry
					catch (Exception e1) {
						System.out.println("Error de formato ");
						continue;
					} // endCatch
				} // endWhile
				if (M < LongCinta) {
					System.out.println("El tamano especificado es menor que los datos ");
					continue;
				} // endIf
				break;
			} // endWhile
			System.out.println("La cinta se rellena con 0 s a izq.y derecha...");
			int DifTamCinta = M - LongCinta;
			if (DifTamCinta % 2 != 0)
				DifTamCinta++; // Numero par
			DifTamCinta = DifTamCinta / 2;
			for (i = 0; i < DifTamCinta; i++)
				Cinta = "0" + Cinta; // 0s del lado izquierdo
			for (i = 0; i < DifTamCinta; i++)
				Cinta = Cinta + "0"; // 0s del lado derecho
			/*
			 * POSICION DE LA CABEZA
			 */
			int P = 0;
			while (true) {
				while (true) {
					System.out.println("Deme la posicion de la cabeza en la cinta: ");
					System.out.println("\t(Entre \"0\" y \"" + M + "\")");
					sResp = Kbr.readLine();
					try {
						P = Integer.parseInt(sResp);
						break;
					} // endTry
					catch (Exception e1) {
						System.out.println("Error de formato ");
						continue;
					} // endCatch
				} // endFor
				if (P < 0 || P >= M) {
					System.out.println("Cabeza en posicion erronea ");
					continue;
				} // endIf
				break; // Exit While
			} // endWhile
			String NuevaCinta = UTM.newTape(TT, Cinta, N, P);
			PrintStream PProc = new PrintStream(new FileOutputStream(new File("Procesada.doc")));
			PProc.println(NuevaCinta);
			PProc.close();
			System.out.println("\nNueva cinta esta en \"Procesada.doc\"");
		} // endWhile
	} // endMain
} // endClass
