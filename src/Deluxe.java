public class Deluxe extends Savings implements Compound_Interest {

    public Deluxe(String cusNumber, String cusName, double iniDeposit, int noofYears, String typeSaving) {
        super(cusNumber, cusName, iniDeposit, noofYears, typeSaving);
    }
// create function
    public double generateTable() {
        double interest = iniDeposit * 15;
        iniDeposit+= interest;
    return iniDeposit;
    }
}
