public class Main {
    public static void main(String[] args) {
        double x1, x2, x3, x4, x5, x6, sum, hsum, hx1, hx2, hx3, hx4, hx5, hx6;
     hx1= 0;
     hx2=0;
     hx3=0;
     hx4=0;
     hx5=0;
     hx6=0;

        hsum = 0;
        for(x1 = 0; x1 <10; x1 += 0.1) {
            for(x2 = 0; x2 <10; x2 += 0.1) {
                for(x3 = 0; x3 <10; x3 += 0.1) {
                    for(x4 = 0; x4 <5; x4 += 0.1) {
                        for(x5 = 0; x5 <45; x5 += 0.1) {
                            for(x6 = 0; x6 <5; x6 += 0.1) {

                                if(x1 + x2 <= 10 && x3+x4 <= 10 && x5+x6 <= 10 && x1+2*x4 <= 10 && x2+2*x5<=10 && x3+2*x6 <=10) {
                                    hx1 = x1;
                                    hx2 = x2;
                                    hx3 = x3;
                                    hx4 = x4;
                                    hx5 = x5;
                                    hx6 = x6;
                                    sum = 1*x1 + 2*x2 + 3*x3 + 4*x4 + 5*x5 + 6*x6;
                                    if(sum > hsum) {
                                        hsum = sum;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        System.out.println("Sum: " + hsum + "\nx1: " + hx1 + "\nx2: " + hx2 + "\nx3: " + hx3 + "\nhx4: " + hx4 + "\nhx5: " + hx5 + "\nhx6: " +hx6);
    }
}
