package models;

public class Profit {
    private String profitId;
    private String date;
    private double totalProfit;

    public Profit() {
        this.profitId = "";
        this.date = "";
        this.totalProfit = 0.0;
    }
    public Profit(String profitId, String date, double totalProfit) {
        this.profitId = profitId;
        this.date = date;
        this.totalProfit = totalProfit;
    }

    public String getProfitId() {
        return profitId;
    }

    public String getDate() {
        return date;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setProfitId(String profitId) {
        this.profitId = profitId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }   

    public String toStringProfit() {
        String str=profitId + "," + date + "," + totalProfit+"\n";
        return str;
    }

    public Profit formProfit(String str) {
        String[] data = str.split(",");
        return new Profit(data[0], data[1], Double.parseDouble(data[2]));
    }   
}
