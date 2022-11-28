package com.difficult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

public class C2477 {
	public long minimumFuelCost(int[][] roads, int seats) {
        HashMap<Integer,List<Integer>> e = new HashMap<>();
        int n = 0;
        for(int[] road : roads) {
        	int from = road[0];
        	int to = road[1];
        	n = Math.max(n, Math.max(from, to));
        	if(!e.containsKey(from)) {
        		e.put(from, new ArrayList<Integer>());
        	}
        	if(!e.containsKey(to)) {
        		e.put(to, new ArrayList<Integer>());
        	}
        	e.get(from).add(to);
        	e.get(to).add(from);
        }
        ++n;
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        long[] count = new long[n];
        boolean[] in = new boolean[n];
        in[0] = true;
        dfs(stack, e, in, count);
        
        long ret = 0;
        for(int i=1;i<n;i++) {
        	long car = count[i] / seats;
        	if(count[i] % seats !=0) {
        		++car;
        	}
        	ret += car;
        }
        
        return ret;
    }
	
	private void dfs(Stack<Integer> stack, HashMap<Integer,List<Integer>> e,
			boolean[] in, long[] count) {
		int fetch = stack.peek();
		int go = 1;
		if(e.containsKey(fetch)) {
			for(Integer link : e.get(fetch)) {
				if(!in[link]) {
					in[link] = true;
					stack.push(link);
					dfs(stack, e, in, count);
					go += count[link];
					stack.pop();
					in[link] = false;
				}
			}
		}
		count[fetch] = go;
	}
}
