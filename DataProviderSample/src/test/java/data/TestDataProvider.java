package data;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class TestDataProvider{
    private static final String TEST_DATA_DIRECTORY = "src/main/resources/test-suites/"; // Directory containing all JSON files

    @DataProvider(name = "testData")
    public static Object[][] provideTestData(Method method) throws IOException {
        String className = method.getDeclaringClass().getName();
        String methodName = method.getName();

        File dataDir = new File(TEST_DATA_DIRECTORY);
        File[] files = dataDir.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null || files.length == 0) return new Object[0][];

        for (File file : files) {
            JsonObject testDataJson = loadTestDataJson(file);
            if (testDataJson == null || !testDataJson.has(className)) continue;

            JsonObject classData = testDataJson.getAsJsonObject(className).getAsJsonObject("testMethods");
            if (!classData.has(methodName)) continue;

            JsonObject methodData = classData.getAsJsonObject(methodName);
            JsonObject dataSet = methodData.getAsJsonObject("dataSet");

            List<Object[]> dataList = new ArrayList<>();
            for (Map.Entry<String, JsonElement> entry : dataSet.entrySet()) {
                JsonObject data = entry.getValue().getAsJsonObject();
                Map<String, Object> dataMap = new Gson().fromJson(data, Map.class);
                dataList.add(new Object[]{TestDataWrapper.fromMap(dataMap)});
            }

            return dataList.toArray(new Object[0][0]);
        }

        return new Object[0][];
    }


    // Method to load a specific JSON file
    private static JsonObject loadTestDataJson(File file) throws IOException {
        FileReader reader = new FileReader(file);
        return new Gson().fromJson(reader, JsonObject.class);
    }
}




//public final class TestDataProvider{
//    private static final String TEST_DATA_DIRECTORY = "src/main/resources/test-suite-resources/"; // Directory containing all JSON files
//
//    // This method dynamically loads the appropriate JSON file based on the test class and method
//    @DataProvider(name = "testData")
//    public static Object[][] provideTestData(Method method) throws IOException {
//        String className = method.getDeclaringClass().getName();
//        String methodName = method.getName();
//
//        // Load all JSON files from the testData directory
//        File dataDir = new File(TEST_DATA_DIRECTORY);
//        File[] files = dataDir.listFiles((dir, name) -> name.endsWith(".json"));
//
//        assert files != null;
//        for (File file : files) {
//            JsonObject testDataJson = loadTestDataJson(file);
//            if (testDataJson != null && testDataJson.has(className)) {
//                JsonObject classData = testDataJson.getAsJsonObject(className).getAsJsonObject("testMethods");
//                if (classData != null && classData.has(methodName)) {
//                    JsonObject methodData = classData.getAsJsonObject(methodName);
//                    Map<String, Object> data = new Gson().fromJson(methodData.getAsJsonObject("data"), Map.class);
//                    return new Object[][]{{TestDataWrapper.fromMap(data)}};
////                }
//                } else {
//                    throw new RuntimeException("The test-method: '" + className + "." + methodName +
//                            "' does not have any input data associated with it!");
//                }
//            }
//        }
//        return new Object[0][];
//    }
//
//    // Method to load a specific JSON file
//    private static JsonObject loadTestDataJson(File file) throws IOException {
//        FileReader reader = new FileReader(file);
//        return new Gson().fromJson(reader, JsonObject.class);
//    }
//}
