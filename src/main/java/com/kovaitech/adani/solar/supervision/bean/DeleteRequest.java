package com.kovaitech.adani.solar.supervision.bean;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeleteRequest {

    private List<String> ids;
}
