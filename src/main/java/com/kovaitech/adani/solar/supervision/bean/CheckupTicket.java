package com.kovaitech.adani.solar.supervision.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document(collection = "checkup_tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckupTicket {

    @Id
    private String id;
    private String plantName;
    private String type;
    private String startDate;
    private String endDate;
    private String equipmentNo;
    private String initiatedBy;
    private String startTime;
    private String finishedBy;
    private String endTime;
    private String weatherCondition;
    private String status;
    private Map<String, CheckListItem> checkListData;
    private int totalManPowerUsed;
    private int pwtNo;

}
