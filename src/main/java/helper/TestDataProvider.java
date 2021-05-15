package helper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Method;

public class TestDataProvider {
    private static Logger logger = LogManager.getLogger(TestDataProvider.class);
    private static String testDataFile = "testData.json";
    private static JsonObject testData;

    static {
        try {
            String testDataPath = TestDataProvider.class.getClassLoader().getResource(testDataFile).getPath();
            testData = JsonParser.parseReader(new FileReader(testDataPath)).getAsJsonObject();
        } catch (Exception e) {
            logger.info("Load json file error. -- " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Load test data list
     * @param method The method that called the data provider method
     * @return test data list
     */
    @DataProvider(name = "testData" )
    public static Object[][] getDataArray(Method method) {
        JsonArray jsonArray = testData.getAsJsonArray(method.getName());
        Object[][] testDataList = new Object[jsonArray.size()][1];
        for (int i = 0; i < jsonArray.size(); i++) {
            testDataList[i][0] = jsonArray.get(i);
        }
        logger.info("load the test data for method: " + method.getName()
                + ". Total test cases are: " + jsonArray.size());
        return testDataList;
    }


}
