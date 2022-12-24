package com.kovaitech.adani.solar.supervision.repository;

import com.kovaitech.adani.solar.supervision.bean.CheckupTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CheckupTicketRepo extends MongoRepository<CheckupTicket, String> {

}
