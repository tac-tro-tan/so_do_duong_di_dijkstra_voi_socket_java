package thongdiepclientserver;

import java.io.*;
import java.util.Scanner;

public class thuattoan {
	public static void main(String[] args) { 
		//nhap dinh bat dau va ket thuc
		System.out.print("Nhap a=");
        Scanner t = new Scanner(System.in);
        int d = t.nextInt();
        System.out.print("Nhap b=");
        int l = t.nextInt();
        t.close();
        
        
		Pair dPair= name3(d,l);
		int Dai[]= dPair.getDai();
		int P[]=dPair.getp();
		int a= dPair.geta();
		int b=dPair.getb();
		int i=dPair.geti();
		int sum=dPair.getsum();
		
  	  		System.out.println("xet dinh "+(b+1)+":");
    	  
  	  		if (Dai[b] > 0 && Dai[b] < sum) {
  	  			System.out.println("quang duong ngan nhat tu dinh "+(a + 1)+" den dinh "+ (b + 1)+
  	  					" co do dai la "+ Dai[b]+"");   
  	  			
  	  			String s="";
  	  			while (i != a) {
  	  				s=s+(i+1)+">--";    	        	
  	  				i = P[i];
  	  			}
  	  			s=s+(a+1);   	
  	  		StringBuffer reverse = new StringBuffer(s);
  	  		reverse.reverse().toString();
  	  		System.out.println(reverse);
    	       
  	  		} else {
  	  			System.out.println("khong co duong di tu dinh "+ (a + 1)+" den dinh "+ (b + 1));
  	  		}    	    
  	  		System.out.println("-----------------------------------");    	     
		//}                  
           
    }
	public static int[] doc_file(String tenfile) {
		File text = new File(tenfile);
		Scanner scanner = null;
		try {
			scanner = new Scanner(text);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//x chua cac gia tri cua g[][]
		int x[]= new int[1000];       
		int count=0;
		while (scanner.hasNextInt()) 
		{
    	 int c = scanner.nextInt();
    	 x[count]=c;
    	 count++; 
		}
		return x;
	}
	public static  Pair name3(int d, int l) {
		 int a=d;// dinh bat dau
		 int b=l;//dinh ket thuc
		 int i=0;
		 int j=0;
		 int sum=0;//gia tri lon vo cung
		 int n=0;// so dinh
		 //doc file sao chep tu file goc
		 int x[]= new int[1000];
		 x=doc_file("folder_s\\45.txt");

//		File text = new File("folder_s\\45.txt");
//		Scanner scanner = null;
//		try {
//			scanner = new Scanner(text);
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		//x chua cac gia tri cua g[][]
//		int x[]= new int[1000];       
//		int count=0;
//		while (scanner.hasNextInt()) 
//		{
//    	 int c = scanner.nextInt();
//    	 count++; 
//    	 switch (count) 
//    	 {
//          	case 1:
//            	n = c; 
//            	break;
//           /* case 2:
//            	a = c;          
//            	break; */
//            default:
//            	x[count]=c;            			
//            	break;
//            }
//    	                                
//		}
//       append(area, "file: "+p4+"\n");
//       BufferedReader in;
//       try {
//    	   in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
//    	   String str;
//    	   while ((str = in.readLine()) != null) {
//   		   append(area, str+"\n");
//    	   }
//    	   in.close();
//       } catch (UnsupportedEncodingException |FileNotFoundException e) {
//    	   // TODO Auto-generated catch block
//    	   e.printStackTrace();
//       } catch (IOException e) {
//    	   // TODO Auto-generated catch block
//    	   e.printStackTrace();
//       }		
		 n=x[0];
		int G[][]= new int[n][n];
		// gan gia tri x vao g[][] va tinh sum
		for (i = 0; i < n; i++)
			for (j = 0; j < n; j++) 
			{
				G[i][j]=x[1+i*n+j];
				sum += G[i][j]; 
			}
		//gan gia tri sum vao cac canh ko ton tai
		for (i = 0; i < n; i++) 
		{
			for (j = 0; j < n; j++) 
			{
				if (i != j && G[i][j] == 0)
					G[i][j] = sum;
			}         
		}
		a--; 
		b--; 
		int S[]= new int[n];
		int Dai[]=new int [n];
		int P[]=new int [n];
		//for (b = 0;  b< n; b++) => tìm đường đến mọi đỉnh
		//{
  	  		for ( i = 0; i < n; i++) {
  	  			Dai[i] = sum;                   // khoi tao do dai tu a toi moi dinh la vo cung
  	  			S[i] = 0;                       // danh sach cac diem da xet
  	  			P[i] = a;                       // dat diem bat dau cua moi diem la a
  	  		}        	      	  
  	  		Dai[a] = 0;                         // do dai tu a -> a la 0   	      	  
  	  		while (S[b] == 0) 
  	  		{                 // trong khi diem cuoi chua duoc xet
  	  			for (i = 0; i < n; i++)          // tim 1 vi tri ma khong phai la vo cung
  	  				if (!(S[i]>0) && Dai[i] < sum)
  	  					break;
 
    	        // i >=n tuc la duyet het cac dinh ma khong the tim thay dinh b -> thoat
  	  			if (i >= n) {break;
  	  			}
  	  			for (j = 0; j < n; j++) {    // tim diem co vi tri ma do dai la min
  	  				if (!(S[j]>0) && Dai[i] > Dai[j]) {	  					
  	  					i = j;
  	  				}
  	  			}  	  			
  	  			S[i] = 1;                       // cho i vao danh sach xet roi
    	  
  	  			for (j = 0; j < n; j++) {    // tinh lai do dai cua cac diem chua xet
  	  				if (!(S[j]>0) && Dai[i] + G[i][j] < Dai[j]) {
  	  					Dai[j] = Dai[i] + G[i][j];      // thay doi Dai
  	  					P[j] = i;                       // danh dau diem truoc no 	  				
  	  				}
  	  			}  	  			
  	  		}
  	  		thuattoan thuattoan= new thuattoan();
			return thuattoan.new Pair(Dai, P,a,b,i,sum);			
	}

	 final class Pair {
        public final  int[] Dai;
        public final  int[] p;
        public final  int a;
        public final  int b;
        public final  int i;
        public final  int sum;

        public Pair( int[] Dai,  int[] p, int a, int b, int i, int sum) {
            this.Dai = Dai;
            this.p = p;
            this.a = a;
            this.b = b;
            this.i = i;
            this.sum = sum;
            
        }
        
        public int[] getDai() {
            return Dai;
        }
     
        public int[] getp() {
            return p;
        }
        
        public int geta() {
            return a;
        }
        
        public int getb() {
            return b;
        }
        
        public int geti() {
            return i;
        }
        
        public int getsum() {
            return sum;
        }        
	}
}