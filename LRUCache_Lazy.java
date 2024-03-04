class LRUCache {
    private LinkedHashMap<Integer, Integer> map;
    private final int max_capacity;

    public LRUCache(int capacity) {
        this.max_capacity = capacity;
        this.map = new LinkedHashMap<Integer, Integer>(capacity, 0.75f, true){
          protected boolean removeEldestEntry(Map.Entry eldest){
              return size() > max_capacity;
          }
        };  
    }
    
    public int get(int key) {
        return map.getOrDefault(key, -1);
    }
    
    public void put(int key, int value) {
        map.put(key, value);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

 /*
   Approach: Lazy solution with using of LinkedHashMap
   In the constructor, the third boolean parameter specifies the ordering mode. If we set it to true, it will be in access order. (https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#LinkedHashMap-int-float-boolean-)
By overriding removeEldestEntry in this way, we do not need to take care of it ourselves. It will automatically remove the least recent one when the size of map exceeds the specified capacity.(https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html#removeEldestEntry-java.util.Map.Entry-)
 
 Time Complexity: O(1)
 Space Complexity: O(n)
 */
