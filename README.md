Welcome to the fundanalysisutil!
-----------------------------------------------------------------------
Instructions to run fundanalysisutil

*  Clone the fundanalysisutil source code from github
*  Navigate to the folder fundanalysisutil
*  run the command prompt from 'mvn clean install'
    - mvn clean install will create a jar file in target and required jars in target\lib folder
*  run the jar with the following command
    - 'java -jar target\fundanalysisutil.jar -f c:\Temp\fund.csv -b c:\Temp\benchmark.csv -rf c:\Temp\FundReturnSeries.csv -rb c:\Temp\BenchReturnSeries.csv -of c:\Temp\outputFile.csv'
    - For help on command prompt please use the following command 'java -jar target\fundanalysisutil.jar --help'
*  For any changes to the properties please refer to outperformance.properties
*  For changing the outperformed, underperformed values, please use the following properties from the outperformance.properties, for example for a value 2 and -2
                    upperNLowerLimits=-2,2
                    -2=underperformed
                    2=outperformed


