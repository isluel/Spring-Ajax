package com.spring.ajax_Test;

import java.io.Writer;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rometools.rome.feed.rss.Category;
import com.rometools.rome.feed.rss.Channel;
import com.rometools.rome.feed.rss.Description;
import com.rometools.rome.feed.rss.Item;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;


/**
 * Handles requests for the application home page.
 */

@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("/ajax_test")
	public String ajax() {
		
		return "ajax_test";
	}
	
	
	@RequestMapping(value = "/ajax_submit", method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> submit(HttpServletRequest request) {
		
		System.out.println(request.getParameter("id"));
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("data", "getget!");
		//String str = "";
		return map;
	}
	
	@RequestMapping("/CrossDomain")
	public String corss() {
		return "/CrossDomain";
	}
	
	@RequestMapping("/CrossDomain_T")
	public String corss_T() {
		return "/CrossDomain_T";
	}
	
	@RequestMapping("/jsonp_Test")
	public void Jsonp_Test(HttpServletRequest request, HttpServletResponse response, Map<String, Object> map) throws Exception {
		map.put("data1", "value1");
        map.put("data2", "value2");
        map.put("data3", "value3");
        
		String callback = request.getParameter("callback");
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(map);
		Writer out = response.getWriter();
		out.append(callback).append("(").append(json).append(")");
	}

	
	@RequestMapping("/rss")
	public ModelAndView rss() {
		List<SampleContent> items = new ArrayList<SampleContent>();
		
		SampleContent content  = new SampleContent();
		content.setTitle("Spring MVC Tutorial 1");
		content.setUrl("http://www.kma.go.kr/weather/forecast/mid-term_01.jsp");
		content.setSummary("Tutorial 1 summary ...");
		content.setCreatedDate(new Date());
		items.add(content);
		
		SampleContent content2  = new SampleContent();
		content2.setTitle("Spring MVC Tutorial 2");
		content2.setUrl("http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108");
		content2.setSummary("Tutorial 2 summary ...");
		content2.setCreatedDate(new Date());
		items.add(content2);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("rssViewer");
		mav.addObject("feedContent", items);
		//model.addAttribute("feedContent", items);
		return mav;
		//return "rssViewer";
	}
	
	@RequestMapping("/rssT")
	public @ResponseBody Map<String, Object> rssT() {
		String rssUrl = "http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId=108";
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			URL feedUrl = new URL(rssUrl);
		    SyndFeedInput input = new SyndFeedInput();
		    SyndFeed feed = input.build(new XmlReader(feedUrl));
		    
		    System.out.println("RSS title: " + feed.getTitle());
		    System.out.println("RSS author: " + feed.getAuthor());
	
		    List entries = feed.getEntries();
		   
		    for (int i = 0; i < entries.size(); i++) {
		        SyndEntry entry = (SyndEntry) entries.get(i);
		        System.out.println("--- Entry " + i);
		        System.out.println(entry.getTitle());
		        System.out.println(entry.getAuthor());
		        System.out.println(entry.getDescription().getValue());
		        System.out.println(entry.getLink());
		        map.put(i+"", entry.getDescription().getValue());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
