package com.kovaitech.adani.solar.supervision.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "checkup_tickets")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckupTicket {

    @Id
    private String id;
    private String plantName;
    private String equipmentNo;
    private String initiatedBy;
    private String startTime;
    private String finishedBy;
    private String endTime;
    private String weatherCondition;
    private int totalManPowerUsed;
    private int pwtNo;

}
