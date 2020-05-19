package proyecto2;
import java.io.*;

class AGF {
  
  static PrintStream so=System.out;
  /*
   * [01] -- Ecuaci�n diof�ntica X+Y=5
   *
*/
  public static double F01(double X1,double X2){
  	if (X1<=0&&X2<=0) return 10000000d;
  	if (X1<=0) return 5000000d;
  	if (X2<=0) return 5000000d;
//  	if (X1+X2-5>=-0.0001) return 2500000d;  
//  	if (X1+X2-5<=+0.0001) return 2500000d;  
 	if (X1+X2-5!=0) return 2500000d;  
  	return X1+X2-5;
  }//endF01

  public static double F02(double X){
  /*
   * (2)
   *
   *	Resolver la siguiente ecuaci�n c�bica:
   *
   *	Y=X^3+2.5X^2-2X+1
   *
   *	MINIMIZAR CON LAS RESTRICCIONES
   *
   *  X=-3.2180565 --> Y=0.0000000
   *  N=200
   *  E=4
   *  D=40
   *  Pc=1.000
   *  Pm=.005
   *  G=1000
   */
   double Y;
   Y=X*X*X+2.5*X*X-2*X+1;
   if (Y>0&&Y<=1) return Y;
   if (Y<1)     return 1000d;
   if (Y<10)    return 10000d;
   if (Y<100)   return 100000d;
   if (Y<1000)  return 1000000d;
   if (Y<10000) return 100000000d;
   return 10000000000d;
  }//endF02

  public static double F03(double X,double Y){
/*
 * (3)
 *
 *	Maximizar
 *
 *	N=200
 *	E=4
 *	D=40
 *	Pc=1
 *	Pm=.005
 *	G=5000
 *
 *	X=5
 *	Y=3
 *	F(X,Y)=0
 *
 */
 	double fx=-(X-5)*(X-5)-(Y-3)*(Y-3);
 	return fx;
  }//endF03

  public static double F04(double X,double Y){
/*
 * (4)
 *
 *	Minimizar
 *
 *	Z --> 0
 *	N=200
 *	E=4
 *	D=40
 *	Pc=1
 *	Pm=.005
 *	G=1000
 *
 *	(X,Y) = (1.198231,0.706163); Z=0.00000
 *
 *
 */
	double Z;
	Z=Math.pow(X,2)-2*X*Y+Math.pow(Y,3)+X+Y-2; 
	if (Z<0){
		if (Z>-100)
  			return 100000;
  		else
  			if (Z>-1000)
  				return 1000000;
  			else
  				if (Z>-10000)
  					return 100000000;
  				else
  					return 1000000000;
  				//endif
  			//endif
  		//endif
	}//endif
	return Z;
  }//endF04

  public static double F05(double X, double Y){
/*
 * (5)
 *
 */
//  2X+3Y-13=0
//   X-2Y+ 4=0
//  ----------
//  3X+ Y- 9=0
//  X=2; Y=3
/*
 *	USAR:
 *		  2 BITS ENTEROS
 *		 40 BITS DECIMALES
 *		200 INDIVIDUOS
 *		400 GENERACIONES
 *		Pc: 0.9
 *		Pm: 0.02
 */
 	double R1,R2;
	int C=0;
	R1=2*X+3*Y-13;
	R2=X-2*Y+4;
	if (R1>=0) C=C+1;
	if (R2>=0) C=C+1;
	if (C==2) return 3*X+Y-9;
	if (C==1) return 5000000d;
	return 10000000d;
  }//endF05

  public static double F06(double X,double Y,double Z){
 /*
 * (6)
 *
 */
 	/*
  	 *	SISTEMA DE ECUACIONES SIMULT�NEAS NO LINEALES (TRASCENDENTES)
  	 *
  	 *	SIN(X)-2X^2-Y   +3.158529=0
  	 *	COS(Y)+ Y^2-X+2Z-0.583853=0
  	 *	X^2   +Y^2 +Z^2 -6       =0
  	 *
  	 *	MINIMIZA
  	 *	  SIN(X)+COS(Y)-X^2+2Y^2+Z^2-X-Y+2Z-3.425324
  	 *	SUJETO A:
  	 *	SIN(X)-2X^2-Y   +3.158529>=0
  	 *	COS(Y)+ Y^2-X+2Z-0.583853>=0
  	 *	X^2   +Y^2 +Z^2 -6       >=0
  	 *
  	 *
  	 * X=+1; Y=-2; Z=-1
  	 * X=+1; Y=+2; Z=-1.25
  	 * X=+1; Y=+2; Z=-1.015625
  	 * X=+1; Y= 0; Z=-0.984375
  	 * N=200
  	 * E=4
  	 * D=40
  	 * V=3
  	 * Pc=1.0
  	 * Pm=.05
  	 * G=3000
  	 */
	double R1,R2,R3;
	int C=0;
  	R1=Math.sin(X)-2*X*X-Y+3.158529;
  	R2=Math.cos(Y)+Y*Y-X+2*Z-0.583853;
  	R3=X*X+Y*Y+Z*Z-6;
  	if (R1>=0) C=C+1;
  	if (R2>=0) C=C+1;
  	if (R3>=0) C=C+1;
  	// Si 3 condiciones satisfechas eval�a la funci�n
  	if (C==3) return Math.sin(X)+Math.cos(Y)-X*X+2*Y*Y+Z*Z-X-Y+2*Z-3.425324;
  	// Si 2 condiciones satisfechas penalty=15M-(15M/3)*2
  	if (C==2) return 5000000d;
  	// Si 1 condici�n satisfecha    penalty=15M-(15M/3)*1
  	if (C==1) return 10000000d;
  	// Si 0 condiciones satisfechas penalty=15M-(15M/3)*0
  	return 15000000d;
  }//endF06

  public static double F07(double X,double Y,double Z){
/*
 * (7)
 *
	SISTEMA DE ECUACIONES SIMULT�NEAS NO LINEALES
	ALGEBR�ICAS

  	X*X+3*Y-Z*Z*Z=-10;
  	X*X+Y*Y+Z*Z=6;
  	X*X*X-Y*Y+Z=2;
		
 	 * N=100
  	 * E=3
  	 * D=25
  	 * V=3
  	 * Pc=1.0
  	 * Pm=.05
  	 * G=5000 */
 	// X=+1; Y=-1; Z=+2
	double R1,R2,R3;
	int C=0;
  	R1=X*X+3*Y-Z*Z*Z+10;
  	R2=X*X+Y*Y+Z*Z-6;
  	R3=X*X*X-Y*Y+Z-2;
  	if (R1>=0) C=C+1;
  	if (R2>=0) C=C+1;
  	if (R3>=0) C=C+1;
  	if (C==3) return 2*X*X+X*X*X+3*Y+Z+Z*Z-Z*Z*Z+2;
  	if (C==2) return 5000000d;
  	if (C==1) return 10000000d;
  	return 15000000d;
  }//endF06


  public static double F08(double X){
  double Y;
/*
 * (8)
 *
*/
 /*
  * ECUACI�N CU�RTICA
  *	X^4-X^3+2.5x^2-2X-34.0625=0
   *  X=2.5
   *  N=50
   *  E=4
   *  D=30
   *  Pc=.9
   *  Pm=.005
   *  G=200
   */
  Y=Math.pow(X,4)-X*X*X+2.5*X*X-2*X-34.0625;
  if (Y<0){
  	if (Y>-100)
  		return 100000;
  	else
  		if (Y>-1000)
  			return 1000000;
  		else
  			if (Y>-10000)
  				return 100000000;
  			else
  				return 1000000000;
  			//endif
  		//endif
  	//endif
  }//endif
  return Y; 
  }//endF08

  public static double Rosenbrock(double X, double Y){
/*
 * (9)
 *
 * FUNCI�N DE ROSENBROCK
 * SUPUESTAMENTE MUY DIF�CIL
 *
 *	EL M�NIMO TE�RICO EST� EN (1,1)
 *
 *	N=200
 *	E=4
 *	D=50
 *	V=2
 *	Pc=1
 *	Pm=0.01
 *	G=500
 */
// min[f(X,Y)]=0 @ (1,1)
  	double X2=X*X;
  	return (1-X)*(1-X)+100*(Y-X2)*(Y-X2);
  }
  
  public static double Proy2(double X, double Y){
/*
 * (10)
 *
 */
  	/*
  	 *		max(10X+11Y)
  	 *	subject to:
  	 *		3X+4Y<=9	<=		
  	 *		5X+2Y>=8	>=
  	 *		X-2Y<=1		<=
  	 *
  	 *	N=200
  	 *	E=5
  	 *	D=25
  	 *	V=2
  	 *	Pc=1
  	 *	Pm=0.05
  	 *	G=2000
  	 *	[1]		-- MAXIMIZA
  	 *	
  	 *	X=2; Y=0.75; Optimo = 28.25
  	 */
	double Penalty=-1000000000d/5d;
	int N=5;
	if (X>=0) N--;
	if (Y>=0) N--;
	if (3*X+4*Y<=9) N--;
	if (5*X+2*Y>=8) N--;
	if (X-2*Y<=1)   N--;
	if (N==0){
  		return 10*X+11*Y;}
  	else
  		return Penalty*(double)N;
  }

  public static double Proy1(double X, double Y){
/*
 * (11)
 *
 */
 	/*
  	 *		max(10X+11Y)
  	 *	subject to:
  	 *		3X+4Y<=9
  	 *		5X+2Y<=8
  	 *		X-2Y<=1
  	 *	N=200
  	 *	E=5
  	 *	D=25
  	 *	V=2
  	 *	Pc=1
  	 *	Pm=0.05
  	 *	G=2000
  	 *	[1]		-- MAXIMIZA
  	 *	
  	 *	X=1; Y=1.5; Optimo = 26.5
  	 */
	double Penalty=-1000000000d/5d;
	int N=5;
	if (X>=0) N--;
	if (Y>=0) N--;
	if (3*X+4*Y<=9) N--;
	if (5*X+2*Y<=8) N--;
	if (X-2*Y<=1)   N--;
	if (N==0){
  		return 10*X+11*Y;}
  	else
  		return Penalty*(double)N;
  }

  public static double EqsLin(double X, double Y, double Z){
/*
 * (12)
 *
 */
  	/*
  	 *	2X - 2Y +3Z = 5
  	 *	 X +  Y + Z =-2
  	 *   X - 2Y -5Z =-2
  	 *--------------------
  	 *
  	 * MINIMIZE{ 4X - 3Y - Z -1 }
  	 *	subject to
  	 *	2X - 2Y +3Z - 5 >= 0
  	 *	 X +  Y + Z + 2 >= 0
  	 *   X - 2Y -5Z + 2 >= 0
  	 *
  	 *	X=-1
  	 *	Y=-2
  	 *	Z=+1
  	 *
  	 *	N=250
  	 *	E=6
  	 *	D=25
  	 *	V=3
  	 *	Pc=0.9
  	 *	Pm=0.05
  	 *	G=750
  	 *	[0] - MINIMIZA
  	 *
  	 */
	double Penalty=+1000000000d/3d;
	int N=3;
	if (2*X-2*Y+3*Z-5>=0) N--;
	if (  X+  Y+  Z+2>=0) N--;
	if (  X-2*Y-5*Z+2>=0)   N--;
	if (N==0){
  		return 4*X-3*Y-Z-1;}
  	else
  		return Penalty*(double)N;
  }

  public static double EqsNoLin(double X, double Y, double Z){
/*
 * (13)
 *
 */
  	/*
  	 *	X^2 +  3Y - Z^3 + 10 = 0
  	 *	X^2 + Y^2 + Z^2 - 6  = 0
  	 *  X^3 - Y^2 + Z   - 2  = 0
  	 *--------------------
  	 *
  	 * MINIMIZE{ 2X^2 + X^3 +3Y + Z +Z^2 - Z^3 +2 }
  	 *	subject to
  	 *	X^2 +  3Y - Z^3 + 10 >= 0
  	 *	X^2 + Y^2 + Z^2 - 6  >= 0
  	 *  X^3 - Y^2 + Z   - 2  >= 0
  	 *
  	 *	X=-1
  	 *	Y=-1
  	 *	Z=+2
  	 *
  	 *	N=200
  	 *	E=2
  	 *	D=32
  	 *	Pc=0.9
  	 *	Pm=0.05
  	 *	G=250
  	 *	[0] -> MINIMIZE
  	 *
  	 */
	double Penalty=+1000000000d/3d;
	int N=3;
	if (X*X+3*Y-Z*Z*Z+10>=0) N--;
	if (X*X+Y*Y+Z*Z-6>=0)    N--;
	if (X*X*X-Y*Y+Z-2>=0)    N--;
	if (N==0){
  		return 2*X*X+X*X*X+3*Y+Z+Z*Z-Z*Z*Z+2;}
  	else
  		return Penalty*(double)N;
  }

  public static double Transporte(int I){
/*
 * (14)
 *
	/*
	 * MINIMIZE
  	 */
  	double V[]=new double [16];
  	for (int i=0;i<16;i++)
  		V[I]=EGA20.Var[I][i];
  	//endFor
	double Penalty=+1000000000d/8d;
	int N=8;
	if (V[0]+V[1]+V[2]+V[3]<=20) 		  N--;
	if (V[4]+V[5]+V[6]+V[7]<=10) 		  N--;
	if (V[8]+V[9]+V[10]+V[11]<=10)    	  N--;
	if (V[12]+V[13]+V[14]+V[15]<=15)	  N--;
	if (V[0]+V[4]+V[8]+V[12]>=15)		  N--;
	if (V[1]+V[5]+V[9]+V[13]>=20)		  N--;
	if (V[2]+V[6]+V[10]+V[14]>=10)		  N--;
	if (V[3]+V[7]+V[11]+V[15]>=10)		  N--;
	if (N==0){
  		double R=80*V[0]+215*V[1]+100*V[2]+108*V[3]+102*V[4]+68*V[5]+15*V[6]+13*V[7];
  		       R=R+12*V[8]+19*V[9]+10*V[10]+6*V[11]+12*V[12]+5*V[13]+13*V[14]+18*V[15];
  		return R;}
  	else
  		return Penalty*(double)N;
  }

  public static double Sphere (double X0, double X1,double X2,double X3, double X4, double X5, double X6,double X7,double X8, double X9){
/*
 * (15)
 *
 *	Minimiza
 *
 *	N=200
 *	E=7
 *	D=25
 *	V=5
 *	
 */
  	/*
  	 *	f(X)=Sum [i=1,10] {X[i]^2}
  	 *
  	 *	-100 <= Xi <= 100
  	 *
  	 *	min(f(X) = f(0,0,...,0)=0
   	 */
   	double Penalty=100000000,Penalty_20=5000000;
   	int C=0;
	if (X0>=-100) C++;
	if (X0<=+100) C++;
	if (X1>=-100) C++;
	if (X1<=+100) C++;
	if (X2>=-100) C++;
	if (X2<=+100) C++;
	if (X3>=-100) C++;
	if (X3<=+100) C++;
	if (X4>=-100) C++;
	if (X4<=+100) C++;
	if (X5>=-100) C++;
	if (X5<=+100) C++;
	if (X6>=-100) C++;
	if (X6<=+100) C++;
	if (X7>=-100) C++;
	if (X7<=+100) C++;
	if (X8>=-100) C++;
	if (X8<=+100) C++;
	if (X9>=-100) C++;
	if (X9<=+100) C++;
	if (C!=20){
		return Penalty-C*Penalty_20;
	}else{
	   	return X0*X0+X1*X1+X2*X2+X3*X3+X4*X4+X5*X5+X6*X6+X7*X7+X8*X8+X9*X9;
   	}
  }
  /* (16)
   */
  	public static double F_X (double X){
  		return  0.0000001791+0.9999859173*X+0.0001802553*X*X-0.1674482693*X*X*X+0.0014301880*X*X*X*X+0.0073233570*X*X*X*X*X;
  	}
  	 /* (17)
   */
  	public static double F_XY (double X, double Y){
  		double T=-0.02443374*Y-0.069795*Y*Y+0.300879562*Y*Y*Y-0.219584368*Y*Y*Y*Y+1.086625946*X;
  			T=T-0.698222039*X*Y+3.002159176*X*Y*Y-4.921476587*X*Y*Y*Y+2.616521362*X*Y*Y*Y*Y;
  			T=T-0.255537557*X*X+2.151174951*X*X*Y-7.98362429*X*X*Y*Y+11.67745*X*X*X*Y*Y*Y;
			T=T-5.703943612*X*X*Y*Y*Y*Y+0.017778028*X*X*X-1.520621294*X*X*X*Y+5.388130861*X*X*X*Y*Y;
			T=T-7.507058744*X*X*X*Y*Y*Y+3.500786139*X*X*X*Y*Y*Y*Y;
		return T;
	}
/*
 *	(18)
 *
 * Funci�n para determinar los MarcadoresDeIntervalos del MiniBanco
 *
 *	Minimizar SUMA{i=1,N}[Elementos en el Intervalo [i-1,i]]-N x (Elementos en el Intervalo 1)
 *
 */
 public static double IntMB (int MarcadoresDeIntervalos, int Individuo, int G) throws Exception{
 	/*System.out.println("Generacion "+G);
 	System.out.println("Individuo  "+Individuo);*/
	BufferedReader MB;
 	try {
		MB=new BufferedReader(new InputStreamReader(new FileInputStream(new File("MiniBanco.txt"))));
	}//endTry
	catch (Exception e){
		System.out.println("************************************************");
		System.out.println("                 ERROR FATAL");
		System.out.println("  NO SE ENCUENTRA EL ARCHIVO \"MiniBanco.Txt\"");
		System.out.println("************************************************");
		return -1;
	}//endCatch
	/*for (int i=0;i<MarcadoresDeIntervalos;i++){
		System.out.println(EGA20.Var[Individuo][i]);
	}   System.out.println();*/
	//		ORDENA LOS MarcadoresDeIntervalos DE MENOR A MAYOR
	double MinVal;int MinNdx;
	for (int i=0;i<MarcadoresDeIntervalos-1;i++){
		MinVal=EGA20.Var[Individuo][i];
		MinNdx=i;
		for (int j=i+1;j<MarcadoresDeIntervalos;j++){
			if (EGA20.Var[Individuo][j]<MinVal){
				MinVal=EGA20.Var[Individuo][j];
				MinNdx=j;
			}//endif
		}//endFor
		if (MinNdx!=i){
			EGA20.Var[Individuo][MinNdx]=EGA20.Var[Individuo][i];
			EGA20.Var[Individuo][i]=MinVal;
		}//endIf
	}//endFor
	/*for (int i=0;i<MarcadoresDeIntervalos;i++){
		System.out.println(EGA20.Var[Individuo][i]);
	}*/
 	long ElementosEnElPrimerIntervalo=ElementosMenoresA(MB,EGA20.Var[Individuo][0]);
 	long Suma=ElementosEnElPrimerIntervalo;
 	/*System.out.println(Suma);
 	BufferedReader kb=new BufferedReader(new InputStreamReader(System.in));
 	kb.readLine();*/
 	for (int j=0;j<MarcadoresDeIntervalos;j++){
 		Suma=Suma+ElementosMenoresA(MB,EGA20.Var[Individuo][j]);
// 		System.out.println(Suma);
 	}//endFor
 	Suma=Suma+ElementosMenoresA(MB,+1);
 	double T=MarcadoresDeIntervalos+1;
 	double TT=T*ElementosEnElPrimerIntervalo;
// 	System.out.println("Suma  "+Suma+"; 1er Intervalo "+T+" veces: "+TT);
 	return Suma-TT;
 }//EndIntMB

  public static long ElementosMenoresA(BufferedReader MB, double Xi) throws Exception{
	long Elementos=0;
	String sNum;double Num;
	while (true){
		try {sNum=MB.readLine();
		     if (sNum==null) return Elementos;
		     Num=Double.parseDouble(sNum);
		}//endTry
		catch (Exception e) {System.out.println("Formato invalido en archivo de datos");return 0;}
		if (Num>=Xi)
			return Elementos;
		else
			Elementos++;
	}//endWhile
 }//endElementosMenoresA
 /*
  *	19
  *
  *	Funci�n extra�da de la Intr. a AGs de la WEB
  *
  *		MAXIMIZE
  *	f(x1, x2, x3) = 21.5 + x1 sin (4 pi x1) + x2 sin (20 pi x2) + x3 
  * x1 in [-3.0,12.1], x2 in [4.1,5.8], and x3 in [0.0,1.0].
  *
  *	N=200
  *	E=4
  *	D=32
  *	V=3
  *	Pc=1
  *	Pm=0.05
  *	G=100
  *	[1] -> MAXIMIZE
  *
  *	X=11.625
  *	Y=5.725
  *	Z=1
  *	F(X,Y,Z)=39.85
  * 
 */
 public static double FromWeb(double X, double Y, double Z){
	double Penalty=-1000000000d/6;
	int N=6;
	if (X>=-3) 	 N--;
	if (X<=12.1) N--;
	if (Y>=4.1)  N--;
	if (Y<=5.8)	 N--;
	if (Z>=0)	 N--;
	if (Z<=1)	 N--;
	if (N==0){	
  		return 21.5+X*Math.sin(4*Math.PI*X)+Y*Math.sin(20*Math.PI*Y)+Z;}
  	else
  		return Penalty*(double)N;
  }//endFromWeb
 /*
  *	20
  *
  *	ROYAL ROAD
  *
  *		MAXIMIZE
  *	11111111........................................................
  *........11111111................................................
  *................11111111........................................
  *........................11111111................................
  *................................11111111........................
  *........................................11111111................
  *................................................11111111........
  *........................................................11111111
 */
 public static double RR1(String Ind){
 	int Fitness=64;
	for (int i=0;i<64;i=i+8){
		int k=0;
		for (int j=0;j<8;j++){
			if (Ind.substring(i+j,i+j+1).equals("1")) k++;
		}//endFor
		if (k!=8){
			Fitness=Fitness-8;
		}//endIf
	}//endFor
	return (double)Fitness;
  }//endFromWeb
 
 /*
  *	21
  *
  *	DECEPTIVE
  *
  *		MAXIMIZE
  *	f(111)=1.0
  *	f(011)=f(101)=f(110)=0.5
  * f(001)=f(010)=f(100)=0.95
  * f(000)=0.2
  */
 public static double Deceptive(String Ind){
 	double Fitness=0;
	if (Ind.equals("111")) Fitness=1;
	if (Ind.equals("011")||Ind.equals("101")||Ind.equals("110")) Fitness=0.5;
	if (Ind.equals("000")) Fitness=0.2;
	else Fitness=0.95;
	System.out.println("Indic "+Ind+" Fitness= "+Fitness);
	return Fitness;
  }//endFromWeb
  
 /*
  *	22
  *
  *	
  *		MAXIMIZE
  *	
  *	f(X,Y)=6X+9Y
  * 
  * 2X+3<=12
  * 4X+Y<=8
  * X>=0
  * Y>=0
  *
  *	N=200
  *	E=4
  *	D=40
  *	V=2
  *	Pc=1
  *	Pm=0.05
  *	G=500
  *	[1] --> MAXIMIZE
  *
  *	X=1.125
  *	Y=3.250
  *	F(X,Y)=36
  *
  */
  public static double F6V019V02 (double X, double Y){
  	int s=0;
  	if (2*X+3*Y<=12) s++;
  	if (4*X+Y<=8) s++;
  	if (X>=0) s++;
  	if (Y>=0) s++;
  	if (s==4)
  		return 6*X+9*Y;
  	//endIf
  	return -1000000000+s*250000000;
  }//endF6v019v02
  
 /*
  *	23
  *
  *	
  *		MAXIMIZE
  *	
  *	Deceptive function
  * 
  * Los datos de la funci�n deben encontrarse en <Decptive.txt>
  * En caso de error, la funci�n regresa <-1000>
  *
  *	El archivo debe contener dos columnas:
  *		a) La cadena a probar
  *		b) Tab
  *		c) El fitness
  * 
  */
  public static double DecepFromFile (String X) throws Exception {
  	//  public static int DecepFitness[];
	//  public static String DecepString[];
	for (int i=0;i<EGA20.n;i++){
		if ((EGA20.DecepString[i]).equals(X))
			return EGA20.DecepFitness[i];
		//endIf
	}//endFor
	return -1000;
  }//endDecepFromFile
 /*
  *	24
  *
  *	
  *		MINIMIZE
  *	
  *	De Jong's No 1
  * 
  */
  public static double DeJong1 (double X, double Y, double Z) {
  	int s=0;
  	double numConst=6;
  	double penalty=+1000000000/numConst;
  	//System.out.println("X->"+X+"; Y-> "+Y);
  	if (X>=-5.12) s++;
  	if (X< +5.12) s++;
  	if (Y>=-5.12) s++;
  	if (Y< +5.12) s++;
  	if (Z>=-5.12) s++;
  	if (Z< +5.12) s++;
  	if (s==numConst)
  		return X*X+Y*Y+Z*Z;
  	//endIf
  	return +1000000000-s*penalty;
  }//end DJ1

 /*
  *	25
  *
  *	
  *		MINIMIZE
  *	
  *	Weighted Sphere
  * 
  */
  public static double WSphere (double X, double Y, double Z) {
  	int s=0;
  	double numConst=6;
  	double penalty=+1000000000/numConst;
  	if (X>=-5.12) s++;
  	if (X< +5.12) s++;
  	if (Y>=-5.12) s++;
  	if (Y< +5.12) s++;
  	if (Z>=-5.12) s++;
  	if (Z< +5.12) s++;
  	if (s==numConst)
  		return 1*X*X+2*Y*Y+3*Z*Z;
  	//endIf
  	return +1000000000-s*penalty;
  }//end DJ1
 
 /*
  *	25
  *
  *	
  *		MINIMIZE
  *	
  *	Rotated Hyper-Ellipsoid
  * 
  */
  public static double RotHE (double X, double Y, double Z) {
  	int s=0;
  	double numConst=6;
  	double penalty=+1000000000/numConst;
  	if (X>=-65.536) s++;
  	if (X< +65.536) s++;
  	if (Y>=-65.536) s++;
  	if (Y< +65.536) s++;
  	if (Z>=-65.536) s++;
  	if (Z< +65.536) s++;
  	if (s==numConst){
  		return 1*X*X+2*Y*Y+3*Z*Z;
  	}
  	//endIf
  	return +1000000000-s*penalty;
  }//end DJ1
 /*
  *	27
  *
  *	
  *		MINIMIZE
  *	
  *	Support Vector Machine
  * 
  */
  public static double SVM(double a, double b) {
  	int w=0;
  	double penalty=+1000000000/EGA20.numVects;
	for (int i=0;i<EGA20.numVects;i++){
		if ((a*EGA20.Vects[i][0]-EGA20.Vects[i][1]+b>0)&&(EGA20.Clase[i]==1)||(a*EGA20.Vects[i][0]-EGA20.Vects[i][1]+b<0)&&(EGA20.Clase[i]==0))
			w++;
		//endIf
	}//endFor
	if (w==0){
		System.out.println("Eureka!");
		return 0.1;
	}else
		return (double)(w*penalty);
	//endIf
  }//end SVM
  /*
   * 28
   *
   * Minimize:
   *
   * 5X^5-2X^3+4X^2-2X+1
   *
   *  N=250
   *  E=1
   *  D=43
   *  V=1
   *  Pc=1
   *  Pm=.01
   *  G=2000
   *
   *  X=-1.2054480
   *  F(X)=0.0000000
   *
   */
   public static double quintic (double X){
   	double X_5, X_3, X_2;
   	X_2=X*X;
   	X_3=X_2*X;
   	X_5=X_3*X_2;
   	double F=5*X_5-2*X_3+4*X_2-2*X+1;
   	if (F>=0) return F;
   	if (F<-1000000) return 1000000000;
   	if (F<-10000)   return 10000000;
   	if (F<-100)     return 1000000;
   	return 10000;
   }

  /*
   * 29
   *
   * Minimize:
   *
   * 5X^5-2X+1
   *
   *  N=300
   *  E=1
   *  D=43
   *  Pc=1
   *  Pm=.01
   *  G=3000
   *
   *  X=-0.8891190
   *  F(X)=0.0000000
   *
   */
   public static double quintic2 (double X){
   	double F=5*X*X*X*X*X-2*X+1;
   	if (F>=0) return F;
   	if (F<-1000000) return 1000000000;
   	if (F<-10000)   return 10000000;
   	if (F<-100)     return 1000000;
   	return 10000;
   }
  /*
   * 30
   *
   * Maximize:
   *
   * -(X-5)^2-(Y-3)^2
   *
   *  N=300
   *  E=3
   *  D=10
   *  G=3000
   *
   *  X=5
   *  Y=3
   *  F(X)=0.00
   *
   */
   public static double DelLibroAGs (double X, double Y) throws Exception{
   	  double A=-((X-5)*(X-5));
   	  double B=-((Y-3)*(Y-3));
   	  double F=Math.abs(A+B);
   	  if (F>10d)					return 100000000f;
   	  if (F>1d)						return 10000000f;
   	  if (F>0.1d)					return 1000000f;
   	  if (F>0.01d)					return 100000f;
   	  if (F>0.001d)					return 10000f;
   	  if (F>0.0001d)				return 1000f;
   	  if (F>0.00001d)				return 100f;
   	  if (F>0.000001d)				return 10f;
      return F;
   }
   
   public static double CyC2015 (double X, double Y, double Z){
 	int C=0;
	if (X>=0)		  C++;
	if (Y>=0)		  C++;
	if (Z>=0)		  C++;
 	if (2*X+Y+Z<=180)   C++;
 	if (X+3*Y+2*Z<=300) C++;
 	if (2*X+Y+2*Z<=240) C++;
	if (C==0) return -60000000;
	if (C==1) return -50000000;
	if (C==2) return -40000000;
	if (C==3) return -30000000;
	if (C==4) return -20000000;
	if (C==5) return -10000000;
    	return 6*X+5*Y+4*Z;
   }//endCyC2015
   /*
    *	Esta rutina busca maximizar la ganancia experada en BlackJack cuando la
    * apuesta m�nima es 50 y la m�xima es 100
    * Usa las probabilidades calculadas de la simulaci�n de 10,000 manos con
    * la estrategia cl�sica
    */
   public static double BJ (double b0, double b1, double b2, double b3, double b4, double b5, double b6){
      int condits=9;
      double penalty=10000000/condits;
  	double P[]=new double [7], b[]=new double [7];
  	P[0]=0.2062;P[1]=0.109016667;P[2]=0.0596;P[3]=0.031533333;
	P[4]=0.017133333;P[5]=0.008416667;P[6]=0.005016667;
	b[0]=b0;b[1]=b1;b[2]=b2;b[3]=b3;b[4]=b4;b[5]=b5;b[6]=b6;
 	int C=0;
	if (b0>=50)	  C++;
	if (b1>=b0)	  C++;
	if (b2>=b1)	  C++;
 	if (b3>=b2)   C++;
 	if (b4>=b3)	  C++;
 	if (b5>=b4)	  C++;
 	if (b6>=b5)	  C++;
 	if (b6<=1000) C++;
 	if (b0+b1+b2+b3+b4+b5+b6<=2500) C++;
 	if(C!=condits) return -10000000+C*penalty;
	double Expected=P[0]*b[0];
	for (int i=1;i<7;i++){
		Expected=Expected+P[i]*b[i];
	}//endif
    	return Expected;
   }//endCyC2015

	/*
	 * 33. Kolmogorov
	 * 
	 * Esta función busca maximizar las coincidencias del archivo kolmogorv.txt con
	 * una cinta inicial llena de ceros En específico, maximiza F = coincidencias +
	 * ((64-numero de estados alcanzados) / 64)
	 */
	public static double Kolmogorov(String TT, String CintaObj, int Tam, int N, boolean imprimeInfo) {
		// Creamos nuestra cinta inicial llena de ceros
		String ceros = new String(new char[Tam]).replace('\0', '0');
		// Decimos que la posición inicial sea la mitad de la cinta
		int P = Tam / 2;
		// Obtenemos la cinta final que da la UTM
		String NuevaCinta = UTM.newTape(TT, ceros, N, P);

		double fitness = 0;
		// Vamos a iterar sobre las posibles posiciones iniciales
		// para comparar la cinta objetivo con la nueva cinta
		// y elegiremos aquel índice que me da el mejor match
		int minPosibleIni = Math.max(0, P - CintaObj.length());
		int maxPosibleIni = Math.min(Tam - CintaObj.length(), P + CintaObj.length());
		int bestIdx = P;
		for (int i = minPosibleIni; i <= maxPosibleIni; ++i) {
			// Definimos el fitness máximo como la longitud de la cinta objetivo
			double fit = CintaObj.length();
			for (int j = 0; j < CintaObj.length(); ++j) {
				if (NuevaCinta.charAt(i + j) != CintaObj.charAt(j)) {
					// Si en algun punto difieren, le restamos 1 al fitness
					fit--;
				}
			}
			// Si empezar en la i-ésima posicion tiene más coincidencias
			// actualizamos el fitness
			if (fit >= fitness) {
				fitness = fit;
				bestIdx = i;
			}
		}

		// En caso de que se requiera, imprimimos la información
		// del desempeño de la máquina de turing
		if (imprimeInfo) {
			UTM.print();
			so.println("Cadena Objetivo: " + CintaObj);
			so.println("Cadena Encontrada: " + NuevaCinta.substring(bestIdx, bestIdx + CintaObj.length()));
			so.println("Coinciden en " + (int) fitness + "/" + CintaObj.length() + " posiciones");
		}

		// Una vez obtenido las coincidencias de la cinta objetivo con la de
		// turing, entonces le restamos 1/64 por cada estado que visitó la UTM
		double statesReached = UTM.getStatesReached();
		fitness -= (statesReached - 1) / 64.0;

		return fitness;
	}// endKolmogorov

} //endClass
