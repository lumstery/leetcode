class Twitter {
    private static int timestamp = 0;
    private Map<Integer, User> userMap;


	public Twitter() {
		userMap = new HashMap<Integer, User>();
	}
    
    public void postTweet(int userId, int tweetId) {
       userMap.computeIfAbsent(userId, k -> new User(userId));
       userMap.get(userId).post(tweetId);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> newsFeed = new LinkedList<>();
        
        if(!userMap.containsKey(userId))   return newsFeed;
       
       Set<Integer> users = userMap.get(userId).followed;
        PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
       

        for(int user: users){
			Tweet t = userMap.get(user).head;
			if(t != null){
				q.add(t);
			}
		}

        int n=0;
		while(!q.isEmpty() && n<10){
		  Tweet t = q.poll();
		  newsFeed.add(t.id);
		  n++;
		  if(t.next != null)
			q.add(t.next);
		}

        return newsFeed;
    }
    
    public void follow(int followerId, int followeeId) {
        userMap.computeIfAbsent(followerId, (k) -> new User(followerId));
        userMap.computeIfAbsent(followeeId, (k) -> new User(followeeId));
        userMap.get(followerId).follow(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if(!userMap.containsKey(followerId) || followerId==followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }

    private static class User{
        int id;
        Set<Integer> followed;
        Tweet head;

		public User(int id){
			this.id=id;
			followed = new HashSet<>();
			follow(id); // first follow itself
			head = null;
		}
        
		public void follow(int id){
			followed.add(id);
		}

		public void unfollow(int id){
			followed.remove(id);
		}

    // everytime user post a new tweet, add it to the head of tweet list.
		public void post(int id){
			Tweet t = new Tweet(id);
			t.next=head;
			head=t;
		}
    }

    private static class Tweet{
        int id;
        int time;
        Tweet next;

        public Tweet(int id){
            this.id = id;
            this.time = timestamp++;
            this.next = null;
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
 /*
 Approach: We have userMap, where store User objects
 Each User object stores the relations of whoom current user follows
 and relation to most recent Tweet that is stored in singly-linked-list 

 The getNewsFeed method in the provided Twitter class is responsible for generating the news feed for a given user. Here's an explanation of how the news feed is formed:

Input Validation:

If the userId provided is not in the userMap, it means that the user doesn't exist, so an empty list is returned.
Initialization:

Initialize an empty list called newsFeed to store the final news feed.
Obtain the set of users that the given userId follows.
Priority Queue Initialization:

Initialize a priority queue q to store the tweets from the users the current user follows. The priority queue is sorted based on the timestamp of the tweets in descending order (from newest to oldest).
Each element in the priority queue is a Tweet object.
Populating the Priority Queue:

Iterate over each user that the current user follows.
For each followed user, retrieve the latest tweet (which is at the head of the tweet list).
If the tweet exists, add it to the priority queue.
Generating the News Feed:

Start a loop to retrieve tweets from the priority queue.
Continue looping until either the queue becomes empty or 10 tweets have been retrieved.
Pop a tweet from the priority queue.
Add the ID of the popped tweet to the newsFeed list.
Increment the counter n to keep track of the number of tweets added to the news feed.
If the popped tweet has a next tweet (i.e., an older tweet), add it back to the priority queue.
Exit the loop once either the queue becomes empty or 10 tweets have been added to the news feed.
Return the News Feed:

Return the newsFeed list containing the IDs of the tweets in the user's news feed, with the newest tweets appearing first.

Time Complexity:

Populating the priority queue: O(k * log(k)), where k is the number of users the current user follows.
Generating the news feed: O(n * log(k)), where n is the number of tweets in the news feed (limited to 10).
Overall time complexity: O((k + n) * log(k)).
Space Complexity:

User map: O(number of users).
Priority queue: O(k), where k is the number of users the current user follows.
News feed list: O(n), where n is the number of tweets in the news feed (limited to 10).
Overall space complexity: O(number of users + k + n).

 */
