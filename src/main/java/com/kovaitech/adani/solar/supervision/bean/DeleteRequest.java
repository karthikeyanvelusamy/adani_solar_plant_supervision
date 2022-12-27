package com.kovaitech.adani.solar.supervision.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class DeleteRequest {

    private List<String> ids;
}
