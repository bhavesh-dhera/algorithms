import java.util.*;
class SegmentTree{
	static int[] st;
	static int n;
	public static void main(String args[]){
		Scanner sc=new Scanner(System.in);
		n=sc.nextInt();
		int[] a=new int[n];
		for(int i=0;i<n;i++)
			a[i]=sc.nextInt();
		constructTree(n,a);
		int q=sc.nextInt();
		while(q-->0){
			int qs=sc.nextInt();
			int qe=sc.nextInt();
			System.out.println(getSum(qs,qe));
		}
		update(a,0,7);
		System.out.println(getSum(0,1));
	}
	public static void constructTree(int n,int[] arr){
		int h=(int)(Math.ceil(Math.log(n)/Math.log(2)));
		int size=2*(int)Math.pow(2,h)-1;
		st=new int[size];
		constructTreeUtil(arr,0,n-1,0);
	}
	public static int constructTreeUtil(int[] arr, int ss,int se, int si){
		if(ss==se){
			st[si]=arr[ss];
			return st[si];
		}
		int mid=ss+(se-ss)/2;
		st[si]=constructTreeUtil(arr,ss,mid,2*si+1)+constructTreeUtil(arr,mid+1,se,2*si+2);
		return st[si];
	}
	public static int getSum(int qs, int qe){
		return getSumUtil(0,n-1,qs,qe,0);
	}
	public static int getSumUtil(int ss,int se,int qs,int qe,int si){
		if(qs<=ss && qe>=se)
			return st[si];
		if(se<qs || ss>qe)
			return 0;
		int mid=ss+(se-ss)/2;
		return getSumUtil(ss,mid,qs,qe,2*si+1)+
			getSumUtil(mid+1,se,qs,qe,2*si+2);
	}
	public static void update(int[] arr,int i,int newval ){
		int diff=newval-arr[i];
		arr[i]=newval;
		updateUtil(0,arr.length-1,i,diff,0);
	}
	public static void updateUtil(int ss, int se, int i, int diff, int si){
		if(i<ss||i>se)
			return;
		st[si]+=diff;
		if(se!=ss){
			int mid=ss+(se-ss)/2;
			updateUtil(ss,mid,i,diff,2*si+1);
			updateUtil(mid+1,se,i,diff,2*si+2);

		}
	}

}