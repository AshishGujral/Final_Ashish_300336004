public class Savings {

    private String cusNumber;
    private String cusName;
    protected double iniDeposit;
    protected int noofYears;
    private String typeSaving;

    public Savings(String cusNumber, String cusName, double iniDeposit, int noofYears, String typeSaving) {
        this.cusNumber = cusNumber;
        this.cusName = cusName;
        this.iniDeposit = iniDeposit;
        this.noofYears = noofYears;
        this.typeSaving = typeSaving;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public double getIniDeposit() {
        return iniDeposit;
    }

    public void setIniDeposit(double iniDeposit) {
        this.iniDeposit = iniDeposit;
    }

    public int getNoofYears() {
        return noofYears;
    }

    public void setNoofYears(int noofYears) {
        this.noofYears = noofYears;
    }

    public String getTypeSaving() {
        return typeSaving;
    }

    public void setTypeSaving(String typeSaving) {
        this.typeSaving = typeSaving;
    }
}
