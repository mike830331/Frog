package com.yuccheng.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.yuccheng.model.SortNumber;

@Service
public class JavaStreamSortService extends dataService {
	public static List<SortNumber> getData() throws IOException {
		List<SortNumber> data = readData();
		data = data.stream().sorted(Comparator.comparingInt(SortNumber::getUser_number)).collect(Collectors.toList());
		return data;
	}

}
