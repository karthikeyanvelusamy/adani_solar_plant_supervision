package com.kovaitech.adani.solar.supervision.service;

import com.kovaitech.adani.solar.supervision.bean.CheckupTicket;
import com.kovaitech.adani.solar.supervision.repository.CheckupTicketRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CheckupTicketService {

    @Autowired
    private CheckupTicketRepo checkupTicketRepo;


    public CheckupTicket getCheckupTicket(String id) {
        Optional<CheckupTicket> checkupTicket =  checkupTicketRepo.findById(id);

        if(!checkupTicket.isPresent()) {
            throw new RuntimeException("No ticket found with given ID : "+ id);
        }
        return checkupTicket.get();
    }

    public List<CheckupTicket> getAll() {
        return checkupTicketRepo.findAll();
    }

    public String createOrUpdateTicket(CheckupTicket ticket) {
       return checkupTicketRepo.save(ticket).getId();
    }


    public void deleteTicket(String id) {
        checkupTicketRepo.deleteById(id);
    }
 

}
