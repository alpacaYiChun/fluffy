package com.difficult;

public class C2478 {
	public static void main(String[] args) {
		C2478 t = new C2478();
		t.beautifulPartitions("33212958", 3, 1);
		t.beautifulPartitions("23542185131", 3, 2);
		t.beautifulPartitions("23542185131", 3, 3);
	}
	private static final long BIG = 1000000007;
	public int beautifulPartitions(String s, int k, int minLength) {
		char[] ar = s.toCharArray();
		int n = ar.length;
		long[] count = new long[n];
		if(!prime(ar[n-1])) {
			for(int i=0;i<=n-minLength;i++) {
				if(prime(ar[i])) {
					count[i] = 1;
				}
			}
		}
		/*
		for(int i=0;i<n;i++) {
			System.out.print(count[i] + " ");
		}
		System.out.println();
		*/
		// dp until k segs
		for(int j=2;j<=k;j++) {
			long sum = 0;
			for(int i=minLength;i<n;i++) {
				if(prime(ar[i]) && !prime(ar[i-1])) {
					sum += count[i];
					sum %= BIG;
				}
			}
			for(int i=0;i<=n-minLength;i++) {
				if(!prime(ar[i])) {
					count[i] = 0;
				} else {
					count[i] = sum;
				}
				// for next round
				long toReduce = 0;
				if(i+minLength<n && prime(ar[i+minLength]) && !prime(ar[i+minLength-1])) {
					toReduce = count[i+minLength];
				}
				sum = (sum + BIG - toReduce) % BIG;
			}
			/*
			for(int i=0;i<n;i++) {
				System.out.print(count[i] + " ");
			}
			System.out.println();
			*/
		}
		
		return (int)count[0];
    }
	
	private boolean prime(char c) {
		switch(c) {
			case '2':
			case '3':
			case '5':
			case '7': return true;
			default: return false;
		}
	}
}
