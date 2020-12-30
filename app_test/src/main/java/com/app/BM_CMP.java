package com.app;

public class BM_CMP {
	
	private final int SIZE = 256;
	private int lastOccurrence[] = new int[SIZE];

	public static void main(String[] args) {
//		int a = 'a';
//		System.out.println(a);
//		System.out.println((int)("ab".charAt(0)));
	}


	private void buildIndex(String pattern) {
		
		int length = pattern.length();

		// 赋值数组每个元素为负一
		for (int i = 0; i < SIZE; i++)
			lastOccurrence[i] = -1;

		// 存储每个 ascii 码对应的位置
		for (int i = 0; i < length; i++)
			lastOccurrence[pattern.charAt(i)] = i; // 最后一个找到的为准
	}

	private int findLast(char aChar) {
		// 获取每个 ascii 码对应的位置
		return lastOccurrence[aChar];
	}
	
	public int find(String content, String pattern) {
		
		//validate, null or empty string is not allowed
		if (content == null || content.length() == 0)
			return -1;
		
		if (pattern == null || pattern.length() == 0)
			return -1;
		
		// search pattern
		if (content.length() < pattern.length())
			// impossible match
			return -1;

		// build last occurrence index
		buildIndex(pattern);
		
		// searching
		// 搜索字符总长度
		int start = pattern.length() - 1;
		// 内容总长度
		int end = content.length();
		int position, j;
		
		// search from left to right
		while (start < end) {

			// 搜索字符总长度
			position = start;

			// 搜索字符总长度
			for (j = pattern.length() - 1; j >= 0; j--) {
				// "HERE IS A SIMPLE EXAMPLE"
				//          "EXAMPLE"
				// if not match a character
				if (pattern.charAt(j) != content.charAt(position)) {
					
					// check the last occurrence index
					// 搜索在“搜索字符”中是否有存在的字符位置
					if (findLast(content.charAt(position)) != -1) {

						if (j - findLast(content.charAt(position)) > 0)
							// case 1
							start += j - findLast(content.charAt(position));
						else
							// case 2
							start += 1;
						
					} else {
						
						// case 3
						start += j + 1;
					}
					
					break;
				}
				
				if (j == 0) {
					
					// found pattern
					return position;
				}
				
				position--;
			}
		}
		
		// not found
		return -1;
	}
	
}