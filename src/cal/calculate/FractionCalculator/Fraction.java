package cal.calculate.FractionCalculator;

/**
 * Created by mostafa on 10/1/2017.
 */
public class Fraction {

    private int numerator;
    private int denominator;


    public Fraction(int numerator,int denominator){

        if(denominator == 0 ){
             throw new IllegalArgumentException();
        }
        if( (denominator < 0) || (denominator < 0 && numerator < 0) ){
            denominator = denominator * -1;
            numerator = numerator * -1;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(Integer i){
        this(i,1);

    }

    public Fraction(){
         this(0,1);

    }


    public int getNumerator(){
        return numerator;
    }
    public int getDenominator(){
        return denominator;
    }
    public String toString(){
        if(getDenominator() == 1)
           return Integer.toString(getNumerator());
        else
            return getNumerator()+ "/" + getDenominator();
    }

    public double toDouble(){
        return ((double)getNumerator()/getDenominator());

    }
    public Fraction add(Fraction other){
//ex: (3/4 + 1/2) must first unify denomonator by multipling
        int thisNum  = this.getNumerator() * other.getDenominator();
        int otherNum = other.getNumerator() * this.getDenominator();
        int unfiedDenominator = this.getDenominator() * other.getDenominator();



            int num = thisNum + otherNum;

          return new Fraction(num,unfiedDenominator);
    }

    public Fraction subtract(Fraction other){

        //ex: (3/4 - 1/2) must first unify denomonator by multipling
        int thisNum  = this.getNumerator() * other.getDenominator();
        int otherNum = other.getNumerator() * this.getDenominator();
        int unfiedDenominator = this.getDenominator() * other.getDenominator();

        int num = thisNum - otherNum;

        return new Fraction(num,unfiedDenominator);
    }



    public Fraction multiply(Fraction other){
        int numerator = getNumerator() * other.getNumerator();
        int denominator = getDenominator() * other.getDenominator();

        return new Fraction(numerator,denominator);
    }

    public Fraction divide(Fraction other){

        if (other.getNumerator() == 0 || other.getDenominator() ==0){
            throw new IllegalArgumentException();
        }
        //ex: (3/4 / 1/2) is equal to (3/4 * 2/1)
        int numerator  = getNumerator() * other.getDenominator();
        int denominator = getDenominator() * other.getNumerator();

        return new Fraction(numerator,denominator);

    }


    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Fraction){
            if(Math.abs(toDouble()) == Math.abs(((Fraction) obj).toDouble())){
                return true;
            }
            return false;
        }
        return false;
    }

    public void toLowestTerms(){
        int gcd = gcd(getNumerator(),getDenominator());
        this.numerator = getNumerator()/gcd;
        this.denominator = getDenominator()/gcd;

    }

    public static int gcd(int num,int den){

        int counter = 1;
        if(num < den){
            counter = num;
        }
        else{
            counter = den;
        }

        int GCD = 1;
        while(counter > 0) {
            if (num % counter == 0 && den % counter == 0) {
                GCD = counter;
                break;
            } else {
                counter--;
            }
        }
        return GCD;
    }

}
