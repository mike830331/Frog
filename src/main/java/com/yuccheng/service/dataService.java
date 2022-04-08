package com.yuccheng.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuccheng.model.SortNumber;
import com.yuccheng.repo.SortNumberRepository;

@Service
public class dataService {

	public static List<SortNumber> readData() throws IOException {
		System.out.println("Hello World");
		String filePath = "Writesheet.csv";
		File file = new File(filePath);
		List<SortNumber> input = new ArrayList<SortNumber>();
		try {
			if (file.isFile() && file.exists()) {
				InputStreamReader read = new InputStreamReader(new FileInputStream(file));
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					String[] token = lineTxt.split(",");
					SortNumber model = new SortNumber();
					model.setUser_name(token[0]);
					model.setUser_number(Integer.valueOf(token[1]));
					input.add(model);
				}

			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // consider encoding
		return input;
	}

}
