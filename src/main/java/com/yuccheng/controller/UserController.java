package com.yuccheng.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yuccheng.model.SortNumber;
import com.yuccheng.model.User;
import com.yuccheng.repo.SortNumberRepository;
import com.yuccheng.repo.UserRepository;
import com.yuccheng.service.BubbleSortService;
import com.yuccheng.service.InsertionSortService;
import com.yuccheng.service.JavaStreamSortService;
import com.yuccheng.service.dataService;

@Controller
public class UserController {

	@Autowired
	UserRepository urepo;

	@Autowired
	SortNumberRepository sortrepo;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/login")
	public String getLogin() {
		return "login";
	}

	@GetMapping("/dummy")
	public String dummy() {
		return "dummy";
	}

	@GetMapping("/main")
	public String main() throws InterruptedException {
		//////////////
		SortNumber model = new SortNumber();
		model.setUser_name("Mike");
		model.setUser_number(1);
		sortrepo.save(model);
		List<SortNumber> a = sortrepo.findByUsername("Mike");
		System.out.println(a);

		//////////////
		List<SortNumber> dataList = new ArrayList<SortNumber>();
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
//		ScheduledFuture<?> future = 
		long oneDay = 24 * 60 * 60 * 1000;
		long iniDelay = getTime("16:50:00") - System.currentTimeMillis();
		iniDelay = iniDelay > 0 ? iniDelay : oneDay + iniDelay;
		boolean save = false;
		executor.scheduleAtFixedRate(
//		executor.scheduleWithFixedDelay(

				new TimerTask() {
					@Override
					public void run() {
						try {
							List<SortNumber> JavaStreamSortdataList = JavaStreamSortService.getData();
							List<SortNumber> BubbleSortSortdataList = BubbleSortService.getData();
							List<SortNumber> InsertionSortdataList = InsertionSortService.getData();
							dataList.forEach(e -> sortrepo.save(e));
							List<SortNumber> a = sortrepo.findAll();
							a.forEach(System.out::println);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				}, iniDelay, oneDay, TimeUnit.MILLISECONDS);
		executor.awaitTermination(iniDelay + 60, TimeUnit.SECONDS);
//		executor.awaitTermination(15, TimeUnit.SECONDS);
		////////
		System.out.println(executor.isTerminated());
		if (!executor.isTerminated()) {
			executor.shutdownNow();
			save = true;
		}
		if (save) {
			for (SortNumber data : dataList) {
				sortrepo.save(data);
			}

		}
		return "main";
	}

	private long getTime(String time) {
		// TODO Auto-generated method stub
		try {
			DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
			DateFormat dayFormat = new SimpleDateFormat("yy-MM-dd");
			Date curDate = dateFormat.parse(dayFormat.format(new Date()) + " " + time);
			return curDate.getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	@PostMapping("/login")
	public String login_user(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, ModelMap modelMap) {

		User auser = urepo.findByUsernamePassword(username, password);

		if (auser != null) {
			String uname = auser.getUser_email();
			String upass = auser.getUser_pass();

			if (username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(upass)) {
				session.setAttribute("username", username);
				return "redirect:/main";
			} else {
				modelMap.put("error", "Invalid Account");
				return "login";
			}
		} else {
			modelMap.put("error", "Invalid Account");
			return "login";
		}

	}

	@GetMapping(value = "/logout")
	public String logout_user(HttpSession session) {
		session.removeAttribute("username");
		session.invalidate();
		return "redirect:/login";
	}

}
