public class Regular extends Savings implements Compound_Interest{

    public Regular(String cusNumber, String cusName, double iniDeposit, int noofYears, String typeSaving) {
        super(cusNumber, cusName, iniDeposit, noofYears, typeSaving);
    }

    @Override
    public double generateTable() {
    double interest = iniDeposit * 10;
    iniDeposit+= interest;
return iniDeposit;
    }
}
