package com.kovaitech.adani.solar.supervision.bean;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CheckListItem {


    private int index;
    private String name;
    private boolean status;
    private String observationAction;
    private String beforePhoto;
    private String afterPhoto;
    private boolean isMaterialConsumed;
    private String consumedMaterials;

}
