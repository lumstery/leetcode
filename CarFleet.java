class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) {
            return 0; // No cars, no fleets
        }

        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(position[i], (double) (target - position[i]) / speed[i]);
        }

        Arrays.sort(cars, (a, b) -> Integer.compare(a.position, b.position));
        
        Stack<Double> stack = new Stack<>();
        for (int i = cars.length - 1; i >= 0; i--) {
            double currentTime = cars[i].time;
            
            if (!stack.isEmpty() && currentTime <= stack.peek()) {
                continue;
            } else {
                stack.push(currentTime);
            }
        }
        return stack.size();
    }

    static class Car {
        int position;
        double time;

        public Car(int position, double time) {
            this.position = position;
            this.time = time;
        }
    }
}
/*
 Approach: We sort cars by distances and then iterate backwards from the car that is closest to destination to the car that is furthest to destination.

Having distance to destination and speed we can easily calculate time required for arrival.

Then we use monotonic stack and iterate in reverse order
when stack is empty we simply put time of arrival to stack head
if the time of arrival of previous car is <= time of ariival of our stack.head() time 
we simply skip this car as we know that eventually this car will match with our "leader".

then once we find a car that has arrival time > than our leadin fleet at stack.head(),
we push new "time of arrival" to stack and now we store a new fleet at stack.head()

then we continue iterating with same approach where cars that have time of arrival <= than previous fleet, we "merge" this car to that fleet, othewise we register another fleet. 

Time complexity: O(n log(n)) mainly because of sorting time
Space complexity: O(n)

*/
