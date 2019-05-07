package com.jk.service.fxlService.serviceImpl;

import com.jk.mapper.fxlMapper;
import com.jk.model.Tenant;
import com.jk.model.Total;
import com.jk.service.fxlService.tenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.SimpleFormatter;

@Service
public class tenServiceImpl implements tenService {
    @Autowired
    private fxlMapper fxlMapper;

    @Override
    public HashMap<String, Object> findTen(Integer page, Integer rows, Tenant tenant) {
        HashMap<String, Object> hashMap = new HashMap<>();
        int count = fxlMapper.count(tenant.getTenName(),tenant.getTenSite());
        int start = (page - 1) * rows;
        List<Tenant> list = fxlMapper.findTen(start,rows,tenant.getTenName(),tenant.getTenSite());
        hashMap.put("total",count);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public Total findParticulars(Integer tenId) {
        Total total = fxlMapper.findParticulars(tenId);
        Date htStartDate = total.getHtStartDate();
        Date htEndDate = total.getHtEndDate();
        Calendar  from  =  Calendar.getInstance();
        from.setTime(htStartDate);
        Calendar  to  =  Calendar.getInstance();
        to.setTime(htEndDate);
        int fromYear = from.get(Calendar.YEAR);
        int fromMonth = from.get(Calendar.MONTH);
        int fromDay = from.get(Calendar.DAY_OF_MONTH);
        int toYear = to.get(Calendar.YEAR);
        int toMonth = to.get(Calendar.MONTH);
        int toDay = to.get(Calendar.DAY_OF_MONTH);
        int year = toYear  -  fromYear;
        int month = toMonth  - fromMonth;
        int day = toDay  - fromDay;
        total.setHtYear(year);
        total.setHtMouth(month);
        total.setHtDay(day);
        return total;
    }
}
