import java.util.Arrays;

public class Fact {
//    0!=1
//    n!=n(n-1)!
    public static long fact(int n)
    {
        //1. you must have base case otherwise your recursion will never end
        if(n==0) return 1;//    0!=1
        else //    n!=n(n-1)!
        //2. You must call yourself to cause a recursion
        //3. You must decrease the size of the problem so the base case can be arrived(n-1)
            return n*fact(n-1);
    }

//    fact(5) return fact(4)*5
//    fact(4) return fact(4)*4
//    fact(3) return fact(2)*3
//    fact(2) return fact(1)*2
//    fact(1) return fact(0)*1
//    fact(0) return 1
//
//    1*1*2*3*4*5 = 5!
//

//    fib(0) = 0; base case
//    fib(1) = 1; base case
//    fib(index) = fib(index -1) + fib(index -2); index >=2

    public static long fib(int n)
    {
        if(n==0) return 0;//base case
        else if (n==1) return 1;//base case
        else return fib(n-1)+fib(n-2);
    }

    //Sum 1-n
    public static long sum(int n)
    {
        int rs=0;
        for(int i=1;i<=n;i++) rs+=i;
        return rs;
    }

    //Sum 1-n in RECURSION!
    public static long sum_r(int n)
    {
        //base case
        if(n==1) return 1;
        else return n+sum_r(n-1);
    }

    //Sum nVals in RECURSION!
    //base case: 0 when nVals.length==0
    //Sub array is the way to decrease the size of problem
    //You do not have to create a new array for each recursion call
    public static long sum_r(int[] nVals)
    {
        if(nVals.length==0) return 0;
        //if(nVals.length==1) return nVals[0]; //this also works
        return nVals[0]+sum_r(Arrays.copyOfRange(nVals,1,nVals.length-1));
    }

    public static long sum_r1(int[] nVals)//Help method
    {
        return sum_r1_int(nVals,0);
    }

    public static long sum_r1_int(int[] nVals,int nStart)
    {
        if(nStart==nVals.length-1) return nVals[nVals.length-1];
        return nVals[nStart]+sum_r1_int(nVals,nStart+1);
    }


    public static long sum(int[] nVals)
    {
        int rs = 0;
        for(int n:nVals)rs+=n;
        return rs;
    }

    public static boolean isPalindrome(String s)
    {
        for(int i=0,j=s.length()-1;i<j;i++,j--)
            if(s.charAt(i)!=s.charAt(j)) return false;
        return true;
    }

    public static boolean isPalindrome_r(String s)
    {
        if(s.length()<2) return true;
        if(s.charAt(0)!=s.charAt(s.length()-1)) return false;
        return isPalindrome_r(s.substring(1,s.length()-1));
    }

    public static boolean isPalindrome_r1(String s)
    {
        return isPalindrome_r1_int(s,0,s.length()-1);
    }

    public static boolean isPalindrome_r1_int(String s,int low,int high)
    {
        if(low > high) return true;
        if(s.charAt(low)!=s.charAt(high)) return false;
        return isPalindrome_r1_int(s,low+1,high-1);
    }


}
