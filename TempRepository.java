package com.cloud.compute;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;


public interface TempRepository extends JpaRepository<Temp, Long> {
     
	Collection<Temp> findByDate(String Date);

	List<Temp> deleteByDate(String date1);

	@Query(value = "SELECT * FROM temp_details td WHERE td.date BETWEEN  :Date1 AND DATE_ADD(:Date1, INTERVAL 4 DAY)", nativeQuery=true)
    public List<Temp> findForecast(@Param("Date1") String Date1);

	@Query(value ="SELECT * FROM temp_details WHERE Month(Date) = :month AND DAY(Date) = :day", nativeQuery=true)
	public Collection<Temp> findFutureForecast(@Param("month")int month,@Param("day")int day);

	@Query(value ="SELECT * FROM temp_details WHERE date LIKE '%:period%'", nativeQuery=true)
	public Collection<Temp> findFutureForecastFinal(@Param("period")String period);


	@Query(value = "SELECT * FROM temp_details", nativeQuery=true)
    public List<Temp> findHistory();

	
}

