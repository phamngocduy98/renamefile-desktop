package FluentDesign;

import java.util.HashMap;

public class FDViewStore {
    HashMap<String, Object> hashMap;

    public FDViewStore() {
        hashMap = new HashMap<String, Object>();
    }

    public <T> void store(String id, T view){
        hashMap.put(id, view);
    }
    public Object get(String id){
        return hashMap.get(id);
    }
}
