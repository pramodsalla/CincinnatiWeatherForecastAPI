package com.cloud.compute;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;

@RestController
@Transactional
@RequestMapping("/weather/")
public class AppController {

	private final TempRepository tempRepository;
	private final Temp2Repository temp2Repository;

	public AppController(TempRepository tempRepository, Temp2Repository temp2Repository) {
		super();
		this.tempRepository = tempRepository;
		this.temp2Repository = temp2Repository;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/historical/")

	public List<Temp> samplesMM() {
		
		return this.tempRepository.findHistory();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/historical/{Date1}")
	public String samplesM(@PathVariable String Date1) {
		System.out.println("Date : " + Date1);
		if (this.tempRepository.findByDate(Date1).isEmpty()) {
			throw new DateNotFoundException(Date1);
		}

		Collection<Temp> sample = this.tempRepository.findByDate(Date1);

		String data = "";
		for (Temp tmp : sample) {
			data = "{\"DATE\": \"" + tmp.getDate() + "\",\"TMAX\": " + tmp.gettMax() + ",\"TMIN\": " + tmp.gettMin()
					+ "}";
		}

		return data;

	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/historical/{Date1}")
	public List<Temp> delete(@PathVariable String Date1) {

		return this.tempRepository.deleteByDate(Date1);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/forecast/{Date1}")
	public Collection<Temp> foreCast(@PathVariable String Date1) throws ParseException {
		System.out.println("Date : " + Date1);

		// returns the directly from data base
		Collection<Temp> weekdata = this.tempRepository.findForecast(Date1);
		System.out.println("forecast size" + weekdata.size());
		List<Temp> tWeekdata = new ArrayList<Temp>();

		

		// future date
		String tempDate = null;
		if (weekdata.size() == 0) {
						DateTime dt = new DateTime(Integer.parseInt(Date1.substring(0, 4)), Integer.parseInt(Date1.substring(4, 6)),
					Integer.parseInt(Date1.substring(6)), 0, 0, 0, 0);
			for (int i = 1; i <= 5; i++) {

				
				tWeekdata.addAll(this.tempRepository.findFutureForecast(dt.getMonthOfYear(), dt.getDayOfMonth()));

				dt = dt.plusDays(1);

				
				tempDate = Date1;
				Date1 = dt.toString().substring(0, 10).replace("-", "");

				int AvgTmax = 0;
				int AvgTmin = 0;
				int tmpAvgTmax = 0;
				int tmpAvgTmin = 0;

				for (Temp t : tWeekdata) {
					System.out.println("inside for each Tmax" + t.gettMax());
					tmpAvgTmax = tmpAvgTmax + t.gettMax();
				}
				AvgTmax = tmpAvgTmax / tWeekdata.size();

				for (Temp t : tWeekdata) {
					tmpAvgTmin = tmpAvgTmin + t.gettMin();
				}
				AvgTmin = tmpAvgTmin / tWeekdata.size();

				Temp t = new Temp(tempDate, AvgTmax, AvgTmin);

				weekdata.add(t);

			}

		}

		
		return weekdata;
	}

	@RequestMapping(value = "/historical/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String post(@RequestBody Temp temp, Model model) {
		this.tempRepository.save(temp);

		Collection<Temp2> sample = this.temp2Repository.findByDate(temp.getDate());

		String data = "";
		for (Temp2 tmp : sample) {
			data = "{\"DATE\": \"" + tmp.getDate() + "\"}";
		}

		return data;

	}

}
