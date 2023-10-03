class Solution {
        public double findMedianSortedArrays(int[] nums1, int[] nums2) {
            if (nums1 == null || nums2 == null) {
                return 0;
            }

            // we should make search on the smaller array for better performance
            // complexity will be O(log(nums1.length)) so we need to use smaller
            if (nums1.length > nums2.length) {
                return findMedianSortedArrays(nums2, nums1);
            }

            int smallerLength = nums1.length;
            int biggerLength = nums2.length;

            int searchStartIndex = 0; // index of start of search area for binary search
            int searchEndIndex = smallerLength; // index of end of search area for binary search

            // were going to do binary search to find such partitionNums1 that will eventually satisfy all our conditions
            // depending on the need we will adjust search area to left or rigth of search space in nums1 
            // we are going to search only in nums1
            while (searchStartIndex <= searchEndIndex) {
                // partition position in the middle of search area in array Nums1
                int partitionNums1 = (searchStartIndex + searchEndIndex) / 2;
                // partition position in the bigger array Nums2
                // to find proper partition position in Nums2 we need to place pointer in mirror-like position relatively to partitionNums1
                // if partitionNums1 moves right , then partitionNums2 should be moved left and vice versa
                //
                // (nums1.length + nums2.length + 1 / 2 ) would give us a median position of virtually merged combined array
                // +1 is used to cover odd/even cases
                // nums1 [1,2,3,4,5] , length  = 5
                // nums2 [1,2,3,4,5,6,7], length = 7
                // virtual [1,1,2,2,3,3,4,4,5,5,6,7],  5+7+1 / 2 = 6 -> this is virtual median position in combined array
                //                     ^
                // so to build first half of our supposed virtual array we would need to take partitionNums1 elements from nums1
                // + ( (nums1.length + nums2.length + 1 / 2 ) - partitionNums1 )
                // because if we take 2 elements from nums1, we would need to take 4 elements from nums2, to compensate, so sum will be 6;
                //
                // if we take [1,2] from nums1 , then we need to take [1,2,3,4] from nums2
                // thus
                //     |
                // [1,2,3,4,5]
                // [1,2,3,4,5,6,7]
                //         |
                int partitionNums2 = ((smallerLength + biggerLength + 1) / 2) - partitionNums1;

                // points to max value in nums1 array placed to the left from partition position partitionNums1
                int maxLeftNums1 = (partitionNums1 == 0) ? Integer.MIN_VALUE : nums1[partitionNums1 - 1];
                // points to min value in nums1 array placed to the right from partition position partitionNums1
                int minRightNum1 = (partitionNums1 == smallerLength) ? Integer.MAX_VALUE : nums1[partitionNums1];

                // points to max value in nums2 array placed to the left from partition position partitionNums2
                int maxLeftNums2 = (partitionNums2 == 0) ? Integer.MIN_VALUE : nums2[partitionNums2 - 1];
                // points to min value in nums2 array placed to the right from partition position partitionNums2
                int minRightNums2 = (partitionNums2 == biggerLength) ? Integer.MAX_VALUE : nums2[partitionNums2];

                /*
                partitionNums1 = 2
                       |
                   [1,2,3,4,5]
                 [1,2,3,4,5,6,7]
                         |
                   partitionNums2 = 4

                 here
                   maxLeftNums1 = 2, minRightNums1 = 3  because partition position is in between numbers 2 and 3 (indices 1 & 2 )
                   maxLeftNums2 = 4, minRightNums2 = 5  because partition position is in between numbers 4 and 5 (indices 3 & 4 )

                 if we visually align both arrays in the way that partition positions are aligned, we will see
                 all numbers to the left of | that would combine first half of virtual array, and all numbers to the right of | that would combine
                 second half of virtual array

                     partitionNums1 = 2
                         |
                     [1,2,3,4,5]
                 [1,2,3,4,5,6,7]
                         |
                   partitionNums2 = 4

                 in order to identify whether position where we decided to cut our arrays nums1 and nums2 is proper
                 we need to make sure that it satisfied a condition for median

               maxLeftNums1
                        \  |
                     [1,|2,3|,4,5]
                          \
                 [1,2,3,|4,5|,6,7]
                          | \
                            minRightNums2

                 maxLeftNums1 should be less than or  equal to minRightNums2

                           minRightNums1
                          | /
                     [1,|2,3|,4,5]
                          /
                 [1,2,3,|4,5|,6,7]
                        / |
                   maxLeftNums2

                 maxLeftNums2 should be less than or  equal to minRightNum1

                 only after doing such cross comparison we can judge that our partition point is perfectly placed,
                 i.e. all values on the left side, are smaller than all values on the right side

                 */


                if (maxLeftNums1 <= minRightNums2 && maxLeftNums2 <= minRightNum1) {

                    // if the size of combined array is even, then we need to calculate median as
                    // sum ( max(maxLeftNums1, maxLeftNums2) +  min(minRightNum1, minRightNums2) ) / 2
                    if ((smallerLength + biggerLength) % 2 == 0) {
/*
                           |
                       [1,2,3,4,5]
                     [1,2,3,4,5]
                           |
                     here 3 wins on the left side (max)  and 3 wins on the right side (min) so 3+3 / 2 = 3
 */
                        return
                                (double) (Math.max(maxLeftNums1, maxLeftNums2) + Math.min(minRightNum1, minRightNums2))
                                        / 2;
                    } else {
                        // if the size of combined array is odd, then we just need to take max among maxLeftNums1 and maxLeftNums2
                        // as bigger of  those numbers would eventually be a median number
                        // smaller would be at last position if the first half of combined array
/*
                             |
                     [1,2,3,4,5]
                         [1,2,7,8,9,10]
                             |
                    here it's 4 as it's > 2 
                    in combined array it would look like
                    [1,1,2,2,3,4,5,7,8,9,10]
                               ^
                    so we have 5 numbers to the left and 5 numbers to the right of 4, it's our median                    
 */
                        return Math.max(maxLeftNums1, maxLeftNums2);
                    }

                } else if (maxLeftNums1 > minRightNums2) {
                    /*
                    here if we are sure that our partitionNums1 in nums1 could not let us find such
                    maxLeftNums1 that woulb be smaller than minRightNums2, it means we have to search for another
                    partition position to the left of scanned area, so searchEnd index moves to the left of our partition position that did not suit
                     */
                    searchEndIndex = partitionNums1 - 1;
                } else {
                    /*
                    here if we are sure that our partitionNums1 in nums1 could not let us find such
                    maxLeftNums2 that would be smaller than minRightNum1, it means we have to search for another
                    partition position to the right of scanned area, so searchStartIndex  moves to the right of our  partition position that did not suit
                     */
                    searchStartIndex = partitionNums1 + 1;
                }
            }

            return 0.0;
        }
}
