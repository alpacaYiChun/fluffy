package com.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C2488 {
	public int countSubarrays(int[] nums, int k) {
		int n = nums.length;

		int ret = 0;
		
		int center = -1;
		for(int i=0;i<n;i++) {
			if(nums[i] == k) {
				center = i;
				break;
			}
		}
		
		++center;
		
		int[] greaterThan = new int[n+1];
		int[] smallerThan = new int[n+1];
		for(int i=1;i<=n;i++) {
			greaterThan[i] = greaterThan[i-1];
			smallerThan[i] = smallerThan[i-1];
			if(nums[i-1]>k) {
				greaterThan[i]++;
			} else if(nums[i-1]<k){
				smallerThan[i]++;
			}
		}
		
		HashMap<Integer, List<Integer>> diff = new HashMap<Integer, List<Integer>>();
		for(int i=0;i<=n;i++) {
			int mask = greaterThan[i] - smallerThan[i];
			if(!diff.containsKey(mask)) {
				diff.put(mask, new ArrayList<Integer>());
			}
			diff.get(mask).add(i);
		}
		
		for(Map.Entry<Integer, List<Integer>> e : diff.entrySet()) {
			int left = 0;
			int right = 0;
			for(Integer val : e.getValue()) {
				if(val<center) {
					++left;
				} else if(val >= center) {
					++right;
				}
			}
			ret += left * right;
			
			right = 0;
			int beyond = e.getKey() + 1;
			if(diff.containsKey(beyond)) {
				for(Integer p : diff.get(beyond)) {
					if(p >= center) {
						++right;
					}
				}
			}
			ret += left * right;
		}
		
		return ret;
    }
}
