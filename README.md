Welcome to the fundanalysisutil!
-----------------------------------------------------------------------
Instructions to run fundanalysisutil

1) Clone the fundanalysisutil source code from github
2) Navigate to the folder fundanalysisutil
3) run the command prompt from 'mvn clean install'
    - mvn clean install will create a jar file in target and required jars in target\lib folder
4) run the jar with the following command
    - 'java -jar target\fundanalysisutil.jar -f c:\Temp\fund.csv -b c:\Temp\benchmark.csv -rf c:\Temp\FundReturnSeries.csv -rb c:\Temp\BenchReturnSeries.csv -of c:\Temp\outputFile.csv'
    - For help on command prompt please use the following command 'java -jar target\fundanalysisutil.jar --help'
5) For any changes to the properties please refer to outperformance.properties
6) For changing the outperformed, underperformed values, please use the following properties from the outperformance.properties, for example for a value 2 and -2
                    upperNLowerLimits=-2,2
                    -2=underperformed
                    2=outperformed


