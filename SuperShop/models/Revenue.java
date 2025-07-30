package models;

public class Revenue    {
    private String revenueId;
    private String date;
    private double totalRevenue;

    public Revenue() {
        this.revenueId = "";
        this.date = "";
        this.totalRevenue = 0.0;
    }
    public Revenue(String revenueId, String date, double totalRevenue) {
        this.revenueId = revenueId;
        this.date = date;
        this.totalRevenue = totalRevenue;

    }

    public String getRevenueId() {
        return revenueId;
    }

    public String getDate() {
        return date;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setRevenueId(String revenueId) {
        this.revenueId = revenueId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public String toStringRevenue() {
        String str=revenueId + "," + date + "," + totalRevenue+"\n";
        return str;
    }

    public Revenue formRevenue(String str) {
        String[] data = str.split(",");
        return new Revenue(data[0], data[1], Double.parseDouble(data[2]));
    }
}
