package com.kovaitech.adani.solar.supervision.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kovaitech.adani.solar.supervision.bean.CheckListItem;
import com.kovaitech.adani.solar.supervision.bean.CheckupTicket;
import com.kovaitech.adani.solar.supervision.repository.CheckupTicketRepo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
public class CheckupTicketService {

    @Autowired
    private CheckupTicketRepo checkupTicketRepo;

    private ObjectMapper mapper = new ObjectMapper();

    public CheckupTicket getCheckupTicket(String id) {
        Optional<CheckupTicket> checkupTicket =  checkupTicketRepo.findById(id);

        if(!checkupTicket.isPresent()) {
            throw new RuntimeException("No ticket found with given ID : "+ id);
        }
        return checkupTicket.get();
    }

    public void addCheckListItem(List<CheckListItem> checkListItems, String checkupTicketId) {

        Optional<CheckupTicket> checkupTicket = checkupTicketRepo.findById(checkupTicketId);
        if(checkupTicket.isPresent()) {
            checkupTicket.get().setCheckListItems(checkListItems);
        }

        checkupTicketRepo.save(checkupTicket.get());

    }

    public List<CheckupTicket> getAll() {
        return checkupTicketRepo.findAll();
    }

    public String createOrUpdateTicket(CheckupTicket ticket) {
        ticket.setStatus("Pending");
        initializeCheckList(ticket);
       return checkupTicketRepo.save(ticket).getId();
    }

    private void initializeCheckList(CheckupTicket ticket) {
        List<CheckListItem> checkListItems = new ArrayList<>();
        try (InputStream stream = getClass().getResourceAsStream("static/CheckListItemData.json")) {
            if (stream == null) {
                throw new IOException("Stream is null");
            }
            String nameStr = IOUtils.toString(stream, Charset.defaultCharset());
            List<String> names = mapper.convertValue(nameStr, new TypeReference<List<String>>() {});
            int index = 1;

            for(String name : names) {
                checkListItems.add(new CheckListItem(index,name,
                        false,
                        null,
                        null, null,
                        false, null));
            }

        }catch (Exception e ) {
            log.error("Error while getting checklist items name", e);
        }
        ticket.setCheckListItems(checkListItems);
    }

    public void deleteTicket(String id) {
        checkupTicketRepo.deleteById(id);
    }

    public void deleteTickets(List<String> ids) {
        checkupTicketRepo.deleteAllById(ids);
    }


}
