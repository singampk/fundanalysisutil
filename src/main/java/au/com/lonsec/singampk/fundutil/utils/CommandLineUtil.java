package au.com.lonsec.singampk.fundutil.utils;

import au.com.lonsec.singampk.fundutil.model.InputParams;
import org.apache.log4j.Logger;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

/**
 * This class is used to optimize hte experience
 * for the end user to use the command prompt
 * util for the util
 *
 * Created by ksingamp on 18/02/2017.
 */
public class CommandLineUtil {
    private static final Logger log = Logger.getLogger(CommandLineUtil.class.getName());
    private String[] args = null;
    private Options options = new Options();

    public CommandLineUtil(String[] args) {

        this.args = args;

        options.addOption("h", "help", false, "Please use the following syntax to execute the jar: java -jar fundanalysisuti.jar with the options");
        options.addOption("f", "var", true, "Please enter absolute path for fund.csv file followed by -f, eg: -f c:/temp/fund.csv");
        options.addOption("b", "var", true, "Please enter absolute path for benchmark.csv file followed by -b, eg: -b c:/temp/benchmark.csv");
        options.addOption("rf", "var", true, "Please enter absolute path for returnFundSeries.csv file followed by -rf, eg: -rf c:/temp/returnFundSeries.csv");
        options.addOption("rb", "var", true, "Please enter absolute path for returnBenchmarkSeries.csv file followed by -rb, eg: -rb c:/temp/returnBenchmarkSeries.csv");
        options.addOption("of", "var", true, "Please enter absolute path for monthlyOutperformace.csv file followed by -of, eg: -of c:/temp/monthlyOutperformance.csv");
    }

    /**
     * To parse the input parameters
     * @return InputParams Object
     */
    public InputParams parse() {
        CommandLineParser parser = new BasicParser();
        InputParams inputParams = new InputParams();
        CommandLine cmd = null;
        try {
            cmd = parser.parse(options, args);

            if (cmd.hasOption("h"))
                help();

            if (cmd.hasOption("f")) {
                log.info("fund.csv path :  " + cmd.getOptionValue("f"));
                inputParams.setFundCSVPath(cmd.getOptionValue("f"));
            } else {
                log.error( "Missing fund csv path, please enter fund csv path, e.g., : -f c:/temp/fund.csv");
                help();
            }
            if (cmd.hasOption("b")) {
                log.info("benchmark.csv path :  " + cmd.getOptionValue("b"));
                inputParams.setBenchmarkCSVPath(cmd.getOptionValue("b"));
            } else {
                log.error( "Missing benchmark csv path, please enter benchmark csv path, e.g., : -b c:/temp/benchmark.csv");
                help();
            }
            if (cmd.hasOption("rf")) {
                log.info("returnFundSeries.csv path :  " + cmd.getOptionValue("rf"));
                inputParams.setReturnFundSeriesPath(cmd.getOptionValue("rf"));
            } else {
                log.error( "Missing returnFundSeries csv path, please enter returnFundSeries csv path, e.g., : -rf c:/temp/returnFundSeries.csv");
                help();
            }
            if (cmd.hasOption("rb")) {
                log.info("returnBenchmarkSeries.csv path :  " + cmd.getOptionValue("rb"));
                inputParams.setReturnBenchmarkSeriesPath(cmd.getOptionValue("rb"));
            } else {
                log.error( "Missing returnBenchmarkSeries csv path, please enter returnBenchmarkSeries csv path, e.g., : -rb c:/temp/returnBenchmarkSeries.csv");
                help();
            }
            if (cmd.hasOption("of")) {
                log.info("monthlyOutPerformance.csv path :  " + cmd.getOptionValue("of"));
                inputParams.setOutputFilePath(cmd.getOptionValue("of"));
            }


        } catch (ParseException e) {
            log.error( "Failed to parse command line properties", e);
            help();
        }
        return inputParams;
    }

    /**
     * Help text
     */
    private void help() {
        HelpFormatter formater = new HelpFormatter();
        formater.printHelp("This is command line help to use fundanalysisutil jar", options);
        System.exit(0);
    }
}

