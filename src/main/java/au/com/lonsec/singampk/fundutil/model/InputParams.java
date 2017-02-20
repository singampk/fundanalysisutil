package au.com.lonsec.singampk.fundutil.model;

/**
 * This object is to hold the input parameters from
 * the command prompt
 *
 * Created by ksingamp on 18/02/2017.
 */
public class InputParams {

    private String fundCSVPath = "";
    private String benchmarkCSVPath = "";
    private String returnFundSeriesPath = "";
    private String returnBenchmarkSeriesPath = "";
    private String outputFilePath = "";

    public String getFundCSVPath() {
        return fundCSVPath;
    }

    public void setFundCSVPath(String fundCSVPath) {
        this.fundCSVPath = fundCSVPath;
    }

    public String getBenchmarkCSVPath() {
        return benchmarkCSVPath;
    }

    public void setBenchmarkCSVPath(String benchmarkCSVPath) {
        this.benchmarkCSVPath = benchmarkCSVPath;
    }

    public String getReturnFundSeriesPath() {
        return returnFundSeriesPath;
    }

    public void setReturnFundSeriesPath(String returnFundSeriesPath) {
        this.returnFundSeriesPath = returnFundSeriesPath;
    }

    public String getReturnBenchmarkSeriesPath() {
        return returnBenchmarkSeriesPath;
    }

    public void setReturnBenchmarkSeriesPath(String returnBenchmarkSeriesPath) {
        this.returnBenchmarkSeriesPath = returnBenchmarkSeriesPath;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}
