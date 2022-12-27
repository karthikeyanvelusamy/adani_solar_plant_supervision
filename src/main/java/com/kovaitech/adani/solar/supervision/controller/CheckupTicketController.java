package com.kovaitech.adani.solar.supervision.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kovaitech.adani.solar.supervision.bean.CheckListItem;
import com.kovaitech.adani.solar.supervision.bean.CheckupTicket;
import com.kovaitech.adani.solar.supervision.bean.DeleteRequest;
import com.kovaitech.adani.solar.supervision.service.CheckupTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/tickets")
public class CheckupTicketController {


    @Autowired
    private CheckupTicketService service;

    private ObjectMapper mapper = new ObjectMapper();


    @RequestMapping(value = "/upsert", method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> upsert(@RequestBody CheckupTicket checkupTicket) {
        try{
            checkupTicket.setStatus("Pending");
            Map<String, Object> response = new HashMap<>();
            response.put("id",service.createOrUpdateTicket(checkupTicket));
            return new ResponseEntity<String>(mapper.writeValueAsString(response),
        HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                     HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @RequestMapping(value = "/checklist-add/{id}", method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> checklistAdd(@PathVariable("id") String id, @RequestBody Map<String, CheckListItem> checkListItemMap) {
        try{
            service.addCheckListItem(checkListItemMap, id);
            Map<String, Object> response = new HashMap<>();
            response.put("status","Success: Updated Successfully");
            return new ResponseEntity<String>(mapper.writeValueAsString(response), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


    @RequestMapping(value = "/delete/{checkupTicketId}", method = RequestMethod.DELETE,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@PathVariable("checkTicketId") String checkupTicketId) {
        try{
            service.deleteTicket(checkupTicketId);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Successfully Deleted");
            return new ResponseEntity<String>(mapper.writeValueAsString(response),
                   HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> delete(@RequestBody DeleteRequest deleteRequest) {
        try{
            service.deleteTickets(deleteRequest.getIds());
            Map<String, Object> response = new HashMap<>();
            response.put("status", "Successfully Deleted");
            return new ResponseEntity<String>(mapper.writeValueAsString(response),
                    HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

    }
    @RequestMapping(value = "/get/{checkupTicketId}", method = RequestMethod.GET,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> get(@PathVariable("checkupTicketId") String checkupTicketId) {
        try{

            Map<String, Object> response = new HashMap<>();
            response.put("data",service.getCheckupTicket(checkupTicketId));
            return new ResponseEntity<String>(mapper
                    .writeValueAsString(response),
                   HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                   HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET,produces =  MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAll() {
        try{

            List<CheckupTicket> tickets = service.getAll();
            Map<String, List<CheckupTicket>> res = new HashMap<>();
            res.put("data",tickets);
            return new ResponseEntity<String>(mapper
                    .writeValueAsString(res),
                    HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }




}
