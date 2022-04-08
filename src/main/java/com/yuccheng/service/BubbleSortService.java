package com.yuccheng.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yuccheng.model.SortNumber;

@Service
public class BubbleSortService extends dataService {

	public static List<SortNumber> getData() throws IOException {
		List<SortNumber> data = readData();
		int len = data.size();
		return bubbleSort(data, len);
	}

	public static List<SortNumber> bubbleSort(List<SortNumber> data, int len) {
		if (len == 1) // passes are done
		{
			return data;
		}
		for (int i = 0; i < len - 1; i++) // iteration through unsorted elements
		{
			if (data.get(i).getUser_number() > data.get(i + 1).getUser_number()) // check if the elements are in order
			{ // if not, swap them
				SortNumber tempModel =new SortNumber();
				tempModel.setUser_name(data.get(i).getUser_name());
				tempModel.setUser_number(data.get(i).getUser_number());

				data.set(i, data.get(i + 1));
				data.set(i + 1, tempModel);
			}
		}
		bubbleSort(data, len - 1);
		return data;
	}


}
