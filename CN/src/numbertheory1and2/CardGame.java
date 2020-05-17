package numbertheory1and2;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.*;

public class CardGame {
    static boolean isPrime(int n)
    {
        if(n==2)
            return true;
        if(n%2==0)
            return false;
        int sqrt=(int)Math.sqrt(n)+1;

        for (int i = 3; i < sqrt; i+=2) {
            if(n%i==0)
                return false;
        }
        return true;
    }
    static List<Integer> primes(int n)
    {
        List<Integer> primes=new ArrayList<>();
        boolean[] arr=new boolean[n+1];
        int sqrt=(int)Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            for (int j = i*i; j <= n; j+=i) {
                arr[j]=true;
            }
        }

        for (int i = 2; i <= n; i++) {
            if(!arr[i])
                primes.add(i);
        }

        return primes;
    }
    static Map<Integer,Integer> getPrimeFactors1(int k,List<Integer> primes,Map<Integer,Integer> pF)
    {
        Map<Integer,Integer> primeFactors=new HashMap<>();
        if(primes.contains(k))
        {
            primeFactors.put(k,1);
            pF.put(k,0);
            return primeFactors;
        }
        for (int p:primes)
        {
            int co=0;
            while(k%p==0)
            {
                k=k/p;
                co++;
            }
            if(co==0)
                continue;
            primeFactors.put(p,co);
            pF.put(p,0);
            if(k==1)
                break;
        }
        return primeFactors;
    }
    static Map<Integer,Integer> getPrimeFactors(int k,Map<Integer,Integer> pF)
    {
        Map<Integer,Integer> primeFactors=new HashMap<>();
        int co=0;
        while (k % 2 == 0) {
            co++;
            k /= 2;
        }
        if(co>0) {
            pF.put(2, 0);
            primeFactors.put(2, co);
        }
        co=0;


        for (int i = 3; i <= Math.sqrt(k); i += 2) {
            while (k % i == 0) {
                co++;
                k /= i;
            }
            primeFactors.put(i,co);
            pF.put(i,0);
            co=0;
        }


        if (k > 2) {
            primeFactors.put(k, 1);
            pF.put(k, 0);
            return primeFactors;
        }
        return primeFactors;
    }
    static void totalPrimeFactors(Map<Integer,Integer> pF,int k,boolean add)
    {
        Set<Integer> keys=pF.keySet();
        for (int p:keys)
        {
            int co=0;
            while(k%p==0)
            {
                k=k/p;
                co++;
            }
            if(add)
                pF.put(p,pF.get(p)+co);
            else
                pF.put(p,pF.get(p)-co);
            if(k==1)
                break;
        }
    }
    static boolean check(Map<Integer,Integer> pFK,Map<Integer,Integer> pF){
        Set<Integer> keys=pF.keySet();
        for (int p:keys)
        {
                if(pF.get(p)<pFK.get(p))
                    return false;
        }
        return true;
    }
    public static void main(String[] args)throws Exception {
        Reader reader=new Reader();
        int n=reader.nextInt();
        int k=reader.nextInt();
        //List<Integer> primes=primes(31623);
        Map<Integer,Integer> iPrimes=new HashMap<>();
        Map<Integer,Integer> kPrimes=getPrimeFactors(k,iPrimes);
        int[] arr=new int[n];
        for (int i = 0; i < n; i++) {
            arr[i]=reader.nextInt();
        }
        long total=0;
        boolean fin=false,jAdd=true;
        for (int j=0,i = 0; i < n;) {
            if(!fin && jAdd)
                totalPrimeFactors(iPrimes,arr[j],true);

            if(check(kPrimes,iPrimes))
            {
                total += n-j;
                totalPrimeFactors(iPrimes,arr[i],false);
                i++;
                jAdd=false;
            }
            else if(fin)
            {
                totalPrimeFactors(iPrimes,arr[i],false);
                i++;
            }
            else {
                jAdd=true;
                j++;
            }
            if(!fin && j==n)
            {
                fin=true;
                j=n-1;
            }
        }
        System.out.print(total);
    }
    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }


        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }
}
