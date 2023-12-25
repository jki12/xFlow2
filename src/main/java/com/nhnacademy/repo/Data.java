package com.nhnacademy.repo;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Data { // 지사, 위치, 장치 식별 번호, 시간 정보, 센서, 타입, 값
    private String branch;
    private String location;
    private String devEui;
    private String sensor;
    private String type;
    private long timeMillsec;
    private double value;
}
