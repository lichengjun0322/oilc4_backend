package upc.csp1_0.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;
/**
 * @description:RSA加密秘钥
 */
@Service
@Transactional
public class RSAEncryption {
    private int N;
    private int L;
    private int E;
    private int D;
    /*素数判断*/
    private boolean suShuJudge(int n){
        int i = 2;
        boolean flag = true;
        for(;i < n;i++){
            if(n % i == 0){
                flag = false;
                break;
            }
        }
        if(flag == true){
            return true;
        }else {
            return false;
        }
    }
    private int gcd(int a,int b){
        int n = 0;
        while (b != 0){
            n = a % b;
            a = b;
            b = n;
        }
        return a;
    }
    /*生产素数*/
    private int suShu(){
        Random r = new Random();
        int r2 = r.nextInt(10)+40;
        for(;;){
            if(suShuJudge(r2) == true){
                break;
            }else {
                r2 = r2 + 1;
            }
        }
        return r2;
    }
    /*计算N和L*/
    private int[] calculateNL(){
        int NL[] = new int[2];
        int p = suShu();
        int q = suShu();
        /*System.out.println(p);
        System.out.println(q);*/
        NL[0] = p * q;
        int p1 = p - 1;
        int q1 = q - 1;
        NL[1] = p1 * q1 / gcd(p1,q1);
        return NL;
    }
    /*计算E*/
    private int calculateE(int l){
        int eList[] = new int[10];
        int i = 2;
        int num = 0;
        //int NL[] = calculateNL();
        for(;;) {
            if (gcd(i, l) == 1) {
                eList[num] = i;
                num = num + 1;
            } else {
                i = i + 1;
            }
            if(num == 10){
                break;
            }
        }
        Random r = new Random();
        int r2 = r.nextInt(9);
        return eList[r2];
    }
    /*计算D*/
    private int calculateD(int l,int e){
        /*int[] NL = calculateNL();
        int l = NL[1];
        int e = calculateE();*/
        int d = 1;
        int rsd = 0;
        for(;;){
            if((e*d)%l == 1){
                rsd = d;
                break;
            }else {
                d = d + 1;
            }
        }
        return rsd;
    }
    /*RSA*/
    public int[] rsa(){
        int n = 0;
        int l = 0;
        int e = 0;
        int d = 0;
        int NL[] = calculateNL();
        int[] rsList = new int[4];
        n = NL[0];
        l = NL[1];
        e = calculateE(l);
        d = calculateD(l,e);
        N = n;
        L = l;
        E = e;
        D = d;
       /* test*/
        rsList[0] = n;
        rsList[1] = l;
        rsList[2] = e;
        rsList[3] = d;
        return rsList;
    }
    public int enRSACode(int ip){
        rsa();
        System.out.println(N);
        System.out.println(L);
        System.out.println(E);
        System.out.println(D);
        //int mingwen = 865;
        int code = (int)Math.pow(ip,E)%N;
        System.out.println(code);
        return code;
    }
    public int deRSACode(){

        return 0;
    }
}
