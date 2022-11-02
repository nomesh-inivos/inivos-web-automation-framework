package utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * Retry the failed test cases again for 'retryCount' number of times.
 */
public class RetryAnalyzer implements IRetryAnalyzer {
  int count = 0;
  int retryCount = 1; //number of retry efforts

    @Override
    public boolean retry(ITestResult iTestResult) {
        while(count < retryCount){
            count ++;
            return true;
        }
        return false;
    }
}
