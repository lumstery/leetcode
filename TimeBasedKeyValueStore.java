class TimeMap {
    private Map<String, List<Timestamped<String>>> map;

    public TimeMap() {
        map = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
      map.computeIfAbsent(key, k -> new ArrayList<>())
         .add(new Timestamped<>(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        List<Timestamped<String>> values = map.getOrDefault(key, Collections.emptyList());
        if(values.isEmpty()) return "";

        int start = 0;
        int end = values.size() - 1;
 

        while(start <= end) {
            int mid = start + (end - start ) / 2;
            
            if(values.get(mid).timestamp == timestamp){
                return values.get(mid).value;
            }else if(values.get(mid).timestamp > timestamp){
                end = mid - 1;
            }else { 
                start = mid + 1;
            }
        }

        if (end >= 0 && values.get(end).timestamp <= timestamp) {
            return values.get(end).value;
        }

        return "";
    }
    
    private static class Timestamped<T>{
        T value;
        int timestamp;

        public Timestamped(T value, int timestamp){
            this.value = value;
            this.timestamp = timestamp;
        }
    }
}

/**
   Approach: Use List<Timestamped>  as value container for every key
   where we hold pair of each value and corresponding timestamp

   as All the timestamps timestamp of set are strictly increasing
   we always add new values to the tail of list;

    then for retrieving of elements we use binary search and seek for element
    with given timestamp, if it could not be found we return closest value with highest
    timestamp using 'end' pointer (if timestamp of value[end] is <= to searchable timestamp)

  Time Complexity: set O(1), get O( n log n )
  Space Complexity: O(n)

 */
