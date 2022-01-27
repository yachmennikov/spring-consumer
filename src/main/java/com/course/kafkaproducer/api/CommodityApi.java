package com.course.kafkaproducer.api;

import com.course.kafkaproducer.entity.Commodity;
import com.course.kafkaproducer.json.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/commodity/v1")
public class CommodityApi {

    @Autowired
    private CommodityService commodityService;

    @GetMapping("/all")
    public List<Commodity> generateCommodities() {
        return commodityService.getDummyCommodities();
    }
}
