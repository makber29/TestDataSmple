package data;

import java.util.Map;

public class TestDataWrapper {

    private Map<String, Object> data;

    protected TestDataWrapper(Map<String, Object> data) {
        this.data = data;
    }

    public Object getInputValueByKey(String key) {
        return data.get(key);
    }

    protected static TestDataWrapper fromMap(Map<String, Object> dataMap) {
        return new TestDataWrapper(dataMap);
    }
}
