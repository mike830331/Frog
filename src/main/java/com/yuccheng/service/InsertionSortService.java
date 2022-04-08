package com.yuccheng.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yuccheng.model.SortNumber;

@Service
public class InsertionSortService extends dataService {
	public static List<SortNumber> getData() throws IOException {
		List<SortNumber> data = readData();
		int len = data.size();
		return insertionSort(data, len);
	}

	public static List<SortNumber> insertionSort(List<SortNumber> data, int len) {
		if (len == 1) // passes are done
		{
			return data;
		}
		insertionSort(data, len - 1);

		SortNumber last = data.get(len - 1); // last number of the List
		int j = len - 2; // correct index of last number of the List

		while (j >= 0 && data.get(j).getUser_number() > last.getUser_number()) // find the correct index of the last element
		{
			data.set(j + 1, data.get(j)); // shift section of sorted elements upwards by one element if correct index
											// isn't found
			j--;
		}
		data.set(j + 1, last);
		return data;
	}
}
