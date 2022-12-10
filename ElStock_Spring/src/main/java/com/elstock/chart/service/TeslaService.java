package com.elstock.chart.service;

import com.elstock.chart.dto.Price;
import com.elstock.chart.entity.Tesla;
import com.elstock.chart.repository.TeslaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class TeslaService {

    private final TeslaRepository teslaRepository;


//    public void test() throws IOException, ParseException {
//
//
//        JSONParser parser = new JSONParser(); // 형변환(파싱) 할 수 있는 생성자 생성
//
//        // 테슬라 api 가져오기
//        Reader reader = new FileReader("C:/graph_api/start.json"); //json파일 읽어오는 놈
//
//        JSONObject jsonObject = (JSONObject) parser.parse(reader); //parser로 json파일을 파싱하고 JSONObject로 형변환, 이유 : 원형태가 jsonobject이기도 하고, 파일형태를 그대로 사용할 수 없어서.
//
//        JSONArray prices = (JSONArray) jsonObject.get("prices"); //가격을 가져와서 jsonarray형태로 형변환, 이유 : 원래 prices가 jsonArray형태인데 jsonObject형태로 가져왔기 때문에 다시 어레이형태로 바꿔야한다.
//
//        List<Tesla> listPrice = new ArrayList<>();
//
//        for (Object e : prices) {
//            //     Price newPrice = new Price();
//            JSONObject price = (JSONObject) e; //prices를 for문 돌리면서 e 변수에다가 값을 계속 담고 다시 JSONObject형태로 형변환. 어레이형태에서 꺼내면 다시 오브젝트 형태로 바뀌어야함.
//
//            try {
//                Tesla newTesla = Tesla.builder()
//                        .adjclose(Math.round(Double.parseDouble(price.get("adjclose").toString()) * 10) / 10.0)
//                        .close(Math.round(Double.parseDouble(price.get("close").toString()) * 10) / 10.0)
//                        .date(Long.parseLong(price.get("date").toString()))
//                        .high(Math.round(Double.parseDouble(price.get("high").toString()) * 10) / 10.0)
//                        .volume(Long.parseLong(price.get("volume").toString()))
//                        .low(Math.round(Double.parseDouble(price.get("low").toString()) * 10) / 10.0)
//                        .open(Math.round(Double.parseDouble(price.get("open").toString()) * 10) / 10.0)
//                        .build();
//                System.out.println(newTesla.toString());
//
//                listPrice.add(newTesla);
//            } catch (Exception exception) {
//                System.out.println("널떳음");
//                //e.printStackTrace();
//            }
//
//            teslaRepository.saveAll(listPrice);
//
//        }
//
//    }

    public List<Price> getTesla() {
        List<Tesla> tesla = teslaRepository.findAll();

        List<Price> priceList = new ArrayList<>();

        for(Tesla bean : tesla){
            Price price = Price.builder()
                    .close(bean.getClose())
                    .open(bean.getOpen())
                    .high(bean.getHigh())
                    .low(bean.getLow())
                    .date(bean.getDate())
                    .adjclose(bean.getAdjclose())
                    .volume(bean.getVolume())
                    .build();
            priceList.add(price);
        }
        return priceList;
    }

}
