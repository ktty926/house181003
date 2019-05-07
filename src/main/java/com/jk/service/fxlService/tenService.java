package com.jk.service.fxlService;


import com.jk.model.Tenant;
import com.jk.model.Total;

import java.util.HashMap;

public interface tenService {

    HashMap<String, Object> findTen(Integer page, Integer rows, Tenant tenant);

    Total findParticulars(Integer tenId);
}
