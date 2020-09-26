package com.hackerank.medium;

/**
 * We define a magic square to be an  matrix of distinct positive integers from  to  where the sum of any row, column, or diagonal of length  is always equal to the same number: the magic constant.

You will be given a  matrix  of integers in the inclusive range . We can convert any digit  to any other digit  in the range  at cost of . Given , convert it into a magic square at minimal cost. Print this cost on a new line.

Note: The resulting magic square must contain distinct integers in the inclusive range .

Example

$s = [[5, 3, 4], [1, 5, 8], [6, 4, 2]]

The matrix looks like this:

5 3 4
1 5 8
6 4 2
We can convert it to the following magic square:

8 3 4
1 5 9
6 7 2
 * @author Rakesh
 *
 */
public class MagicSquare {

	static int[][] constructMagic(int s[][], int type, int i, int j, int currValue, int n, int max){
		if(currValue == max) {
			// This returns the final array
			return s;
		} else {
			if(currValue == 1) {
				s[i][j] = currValue++;
				if(i == 0 || i == n-1) {
					s[n-i-1][j] = max;
				} else if(j==0 || j==n-1) {
					s[i][n-j-1] = max;
				}
			} else {
				int prevI = i;
				int prevJ = j;
				if(type == 10 || type ==40) {
					if(i-1 < 0 ) {
						i = n-1;
					} else {
						i = i-1;
					}
					if(j+1 == n) {
						j = 0;
					} else {
						j = j+1;
					}
				} 
				else if(type == 11) {
					if(i-1 < 0 ) {
						i = n-1;
					} else {
						i = i-1;
					}
					if(j-1 < 0) {
						j = n-1;
					} else {
						j = j-1;
					}
				}
				else if(type ==20 || type ==30 ) {
					if(j-1 < 0 ) {
						j = n-1;
					} else {
						j = j-1;
					}
					if(i+1 == n) {
						i = 0;
					} else {
						i = i+1;
					}
				}
				else if(type ==31 ) {
					if(j-1 < 0 ) {
						j = n-1;
					} else {
						j = j-1;
					}
					if(i-1 < 0) {
						i = n-1;
					} else {
						i = i-1;
					}
				} else if (type == 21 || type == 41) {
					if(i+1 == n) {
						i = 0;
					} else {
						i = i+1;
					}
					if(j+1 == n) {
						j = 0;
					} else {
						j = j+1;
					}
				}
				if(s[i][j]==0)
					s[i][j] = currValue++;
				else {
					//System.out.println(i+":"+j);
					if(type ==10 || type ==11) {
						i = prevI + 1;
						j = prevJ;
					} 
					else if(type ==20 || type ==21) {
						i = prevI-1;
						j = prevJ;
					}
					else if( type ==30 || type ==31) {
						i = prevI;
						j = prevJ + 1;
					} else if(type ==40 || type == 41) {
						i = prevI;
						j = prevJ - 1;
					}
					s[i][j] = currValue++;

				}
				//print(s,n);
			}
			return constructMagic(s, type, i, j, currValue, n, max);
		}
	}

	static void print(int s[][], int n) {
		for(int i = 0;i<n;i++) {
			for(int j=0;j<n;j++) {
				System.out.print(s[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("###########");
	}
	// Complete the formingMagicSquare function below.
	static int formingMagicSquare(int[][] s) {
		int n = 3;
		int min = (n*n)*(n*n+1)/2;
		int tempMin = 0;
		for(int count=0;count<n;count++) {
			for(int count1=0;count1<n;count1++) {
				int allcombs[][] = new int[n][n];
				if(((count==0 || count == n-1) && (count1==n/2)) || ((count1==0 || count1 == n-1) && (count==n/2))) {
					//System.out.print(count+"~~"+count1);
					//System.out.println(count+"----"+count1);
					if(count ==0 ) {
						//System.out.print("-"+1);
						allcombs = new int[n][n]; 
						allcombs = constructMagic(allcombs, 10, count,count1, 1, n, n*n); // i-1, j+1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("1:"+min + " : "+tempMin);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 11, count,count1, 1, n, n*n);
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("2:"+min+ " : "+tempMin);
					} else if(count == n-1) { 
						//System.out.print("-"+2);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 20, count,count1, 1, n, n*n); // i+1, j-1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("3:"+min+ " : "+tempMin);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 21, count,count1, 1, n, n*n); // i+1, j-1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("4:"+min+ " : "+tempMin);
					} else if(count1 == 0) {
						//System.out.print("-"+3);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 30, count,count1, 1, n, n*n); // i+1, j-1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("5:"+min+ " : "+tempMin);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 31, count,count1, 1, n, n*n); // i+1, j-1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("6:"+min+ " : "+tempMin);
					} else if (count1 == n-1) {
						//System.out.print("-"+4);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 40, count,count1, 1, n, n*n); // i-1, j+1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("7:"+min+ " : "+tempMin);
						allcombs = new int[n][n];
						allcombs = constructMagic(allcombs, 41, count,count1, 1, n, n*n); // i-1, j+1
						min = (tempMin = cost(s,allcombs,n)) <min ? tempMin : min;
						//System.out.println("8:"+min+ " : "+tempMin);
					}
				}
			}
		}
		return min;
	}
	
	static int cost(int p[][], int q[][], int n) {
		int sum = 0;
		for(int count=0;count<n;count++) {
			for(int count1=0;count1<n;count1++) {
				if(q[count][count1] != p[count][count1]) {
					sum = sum + Math.abs(q[count][count1] - p[count][count1]);
				}
			}
		}
		return sum;
	}

	static boolean isMagicSquare(int [][]s) {
		int colSum[] = new int[3];
		int rowSum[]  =new int[3];
		int diagSum[] = new int[2];
		int n =3;
		int maxSum = ((n*n)*(n*n+1)/2);
		for(int count = 0;count < n;count++ ) {
			for(int count1 = 0; count1<n; count1++) {
				rowSum[count] = rowSum[count] + s[count][count1];
				colSum[count1] = colSum[count1] + s[count][count1];
				if(count==count1) {
					diagSum[0] = diagSum[0] + s[count][count1];
					if(n%2 == 1 && (count ==  n/2) && (count1 == n/2)) {
						diagSum[1] = diagSum[1] + s[count][count1];
					}
				} else if(count == (n-count1-1)){
					diagSum[1] = diagSum[1] + s[count][count1];
				}
				maxSum = maxSum - s[count][count1];
			}
			if(count > 0 && rowSum[count] != rowSum[count-1]) {
				return false;
			}
		}
		if(maxSum != 0 || diagSum[1] != diagSum[0]) {
			return false;
		}
		for(int count = 1;count<3;count++) {
			if(colSum[count]!=colSum[count-1]) {
				return false;
			}
		}

		return true;
	}
	public static void main(String ...args) {
		System.out.println(formingMagicSquare(new int[][] {
			{ 4, 8, 2},
			{ 4, 5, 7},
			{ 6, 1, 6}
		}));
	}
}
