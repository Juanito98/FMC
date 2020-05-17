package proyecto2;
import java.io.*;
/*
 *	USO:
		String=N2bS.N2S((float)number,digits,decimals,ClearLeftmostZeros);
*/
class N2bS {

  public static String N2S (float Num, int NumDigits, int NumDecs, int ClearZeros){
    PrintStream p=System.out;
   	NumDigits++;
   	if (Num==0){
		String R="";
		if (ClearZeros!=0) R=R+"0.";
		else {for (int i=0; i<NumDigits-NumDecs-1; i++) R=R+"0";R=R+".";}
		for (int i=0; i<NumDecs; i++) R=R+"0";
		return R;
   	}//endIf
	if (ClearZeros!=0&&ClearZeros!=1){
		p.println("****Invalid Flag (0-->\"0\"; 1-->\" \")****");
		p.println("****NULL string will be returned****");
		return "";
	}//endif
	if (NumDigits<=1){
		p.println("****  Invalid number of digits  ****");
		p.println("****NULL string will be returned****");
		return "";
	}//endif
	if (NumDigits<NumDecs+1){
		p.println("�The number of decimals cannot be larger than the string�s length!");
		p.println("                 (+1 for the decimal point)\n");
		p.println("****NULL string will be returned****");
		return "";
	}//endif
	if (NumDecs==0){
		if ((int)Num==0){
			String Ceros="";
			for (int i=0;i<NumDigits;i++){
				Ceros=Ceros+"0";
			}return Ceros;
		}//endIf
	}else{
		if (Num==0){
			String C="0.";
			for (int i=0;i<NumDecs;i++)
				C=C+"0";
			//endfor
			return C;
		}//endif
		NumDigits++;				// Compensa el punto decimal
	}//endif
	String N2C [] = new String [10];
	N2C[0]="0";
	N2C[1]="1";
	N2C[2]="2";
	N2C[3]="3";
	N2C[4]="4";
	N2C[5]="5";
	N2C[6]="6";
	N2C[7]="7";
	N2C[8]="8";
	N2C[9]="9";
	String sign="";
	if (Num<0) {sign="-";Num=-Num;}
  	String Cadena="",C="",C_i;
  	int Residuo;
	int iNum=(int)Num;
	int NumEnts=NumDigits-NumDecs-1;
	for (int i=0;i<NumEnts;i++){
		Residuo=iNum%10;
		C=N2C[Residuo]+C;
		iNum=iNum/10;
	}//endFor
	if (ClearZeros==1){
		boolean DigitsOff=true;
		for (int i=0;i<NumEnts;i++){
			C_i=C.substring(i,i+1);
			if (C_i.equals("0")&&DigitsOff)
				Cadena=Cadena+" ";
			else{
				if (DigitsOff) DigitsOff=false;
				Cadena=Cadena+C_i;
			}//endif
	  	}//endfor
	}else
		Cadena=C;
	//endif
  	if (NumDecs!=0){
		Cadena=Cadena+".";
		int Digito;
		float Dig10;
		iNum=(int)Num;
		float Decimal=Num-(float)iNum;
  		for (int i=0;i<NumDecs;i++){
			Dig10=Decimal*10;
			Digito=(int)Dig10;
			Cadena=Cadena+N2C[Digito];
 			Decimal=Dig10-(float)Digito;
		}//endFor
	}//endif
	Cadena=sign+ltrim(Cadena);
  	return Cadena;
  }//endNum2String
  
  public static String ltrim (String Cadena){
  	String C="",C_i;
  	for (int i=0;i<Cadena.length();i++){
  		C_i=Cadena.substring(i,i+1);
		if (!C_i.equals(" ")) C=C+C_i;
	}//endfor
	return C;
  }//endltrim
	
 public static void main(String[] args) throws Exception {
  	String Test;
  	float Num;
	Num=0f;
	Test=N2S(Num,14,7,0);
	System.out.printf( "Numero=%15.7f ",Num);
	System.out.println("Cadena="+Test);
	
	for (int i=0;i<20;i++){
		Num=(float)i*3.1416f-50;
		Test=N2S(Num,14,7,1);
		System.out.printf( "Numero=%15.7f ",Num);
		System.out.println("Cadena="+Test);
	}//endFor
  }//endMain
 } //endClass
