package com.monster.gdxgame;

public class Monster {
	int Xdif;
	int Ydif;
	int Mm;
	int nextx;
	int nexty;
	public int[] wallcheckloop(int Mx, int My, int Px, int Py, int[] wallx, int[] wally, int[]Mdir, int nextx, int nexty, boolean[] Mboo) {
		int Mi=0;
		System.out.println(nextx);
		System.out.println(nexty);
		if ((Mdir[0]>=Mdir[1]) && (Mdir[0]>=Mdir[2]) && (Mdir[0]>=Mdir[3])) {
			nextx=Mx-2;
			Mm=Mdir[0];
			Mi=0;
			System.out.println(nextx);
		}
		else if ((Mdir[1]>=Mdir[0]) && (Mdir[1]>=Mdir[2]) && (Mdir[1]>=Mdir[3])) {
			nextx=Mx+2;
			Mm=Mdir[1];
			Mi=1;
			System.out.println(nextx);
		}
		else if ((Mdir[2]>=Mdir[1]) && (Mdir[2]>=Mdir[0]) && (Mdir[2]>=Mdir[3])) {
			nexty=My+2;
			Mm=Mdir[2];
			Mi=2;
			System.out.println(nexty);
		}
		else if ((Mdir[3]>=Mdir[1]) && (Mdir[3]>=Mdir[2]) && (Mdir[3]>=Mdir[0])) {
			nexty=My-2;
			Mm=Mdir[3];
			Mi=3;
			System.out.println(nexty);
		}
		System.out.println("before 1st loop");
		for (int i=0;i<(wallx.length);i++) {
			//System.out.println("for 1.1");
			if ((nextx==wallx[i]) && (nexty==wally[i])) {
				System.out.println(wallx[i]);
				System.out.println(nextx);
				System.out.println(wally[i]);
				System.out.println(nexty);
				 for (int j=0; j<(Mdir.length);j++) {
					 //System.out.println("for 1.2");
					 if (Mdir[j]==Mm) {
						 //System.out.println("if 1.2");
						 Mboo[j]=false;
					 }
				 }
			}
			else if ((nextx!=wallx[i]) && (nexty!=wally[i])){
				Mboo[Mi]=true;
			}
		}
		return Mdir;
	}
	public int[] movement (int Mx, int My, int Px, int Py, int[] wallx, int[] wally) {
		boolean[] Mboo;
		Mboo=new boolean[4];
		for (int i=0;i==4;i++) {
			Mboo[i]=false;
		}
		int[] Mdir;
		Mdir=new int[4];
		Mdir[0]=Mx-Px;
		Mdir[1]=Px-Mx;
		Mdir[2]=Py-My;
		Mdir[3]=My-Py;
		nextx=Mx;
		nexty=My;
		Mdir=wallcheckloop(Mx, My, Px, Py, wallx, wally, Mdir, nextx, nexty, Mboo);
		Mdir=wallcheckloop(Mx, My, Px, Py, wallx, wally, Mdir, nextx, nexty, Mboo);
		Mdir=wallcheckloop(Mx, My, Px, Py, wallx, wally, Mdir, nextx, nexty, Mboo);
		if ((Mdir[0]>=Mdir[1]) && (Mdir[0]>=Mdir[2]) && (Mdir[0]>=Mdir[3])) {
			Mx--;
		}
		else if ((Mdir[1]>=Mdir[0]) && (Mdir[1]>=Mdir[2]) && (Mdir[1]>=Mdir[3])) {
			Mx++;
		}
		else if ((Mdir[2]>=Mdir[1]) && (Mdir[2]>=Mdir[0]) && (Mdir[2]>=Mdir[3])) {
			My++;
		}
		else if ((Mdir[3]>=Mdir[1]) && (Mdir[3]>=Mdir[2]) && (Mdir[3]>=Mdir[0])) {
			My--;
		}
		int[] M;
		M=new int[2];
		M[0]=Mx;
		M[1]=My;
		return M;
	} 
}
