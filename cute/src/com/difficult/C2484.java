package com.difficult;

public class C2484 {
	public static void main(String[] args) {
		System.out.println(new C2484().countPalindromes("0423054"));
	}
	
	private static final long BIG = 1000000007;
	
	public int countPalindromes(String s) {
		char[] ar = s.toCharArray();
		int n = ar.length;
		
		if (n<5) {
			return 0;
		}
		
		long[][] leftOne = new long[n][10];
		long[][] rightOne = new long[n][10];
		long[][] leftTwo = new long[n][100];
		long[][] rightTwo = new long[n][100];
		
		leftOne[0][ar[0]-'0']++;
		rightOne[n-1][ar[n-1]-'0']++;
		
		for(int i=1;i<n;i++) {
			for(int j=0;j<10;j++) {
				leftOne[i][j] = leftOne[i-1][j];
			}
			leftOne[i][ar[i]-'0']++;
		}
		for(int i=n-2;i>=0;i--) {
			for(int j=0;j<10;j++) {
				rightOne[i][j] = rightOne[i+1][j];
			}
			rightOne[i][ar[i]-'0']++;
		}
		
		leftTwo[1][(ar[0]-'0')*10+(ar[1]-'0')] = 1;
		rightTwo[n-2][(ar[n-1]-'0')*10+(ar[n-2]-'0')] = 1;
		
		for(int i=2;i<n;i++) {
			for(int j=0;j<100;j++) {
				leftTwo[i][j] = leftTwo[i-1][j];
			}
			for(int k=0;k<10;k++) {
				int mask = k*10 + ar[i] - '0';
				leftTwo[i][mask]+=leftOne[i-1][k];
			}
		}
		for(int i=n-3;i>=0;i--) {
			for(int j=0;j<100;j++) {
				rightTwo[i][j] = rightTwo[i+1][j];
			}
			for(int k=0;k<10;k++) {
				int mask = k*10+(ar[i] - '0');
				rightTwo[i][mask] += rightOne[i+1][k];
			}
		}
		
		long all = 0;
		for(int i=2;i<n-2;i++) {
			for(int k=0;k<100;k++) {
				all = (all + leftTwo[i-1][k] * rightTwo[i+1][k]) % BIG;
			}
		}
		
		/*
		for(int j=2;j<n-2;j++) {
			for(int i=0;i<100;i++) {
				System.out.println(j+":"+i+":"+leftTwo[j-1][i]+":"+rightTwo[j+1][i]);
			}
			System.out.println();
		}
		*/
		
		return (int)all;
    }
}
