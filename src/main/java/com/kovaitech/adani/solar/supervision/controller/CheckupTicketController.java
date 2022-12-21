package com.kovaitech.adani.solar.supervision.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kovaitech.adani.solar.supervision.bean.CheckupTicket;
import com.kovaitech.adani.solar.supervision.service.CheckupTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/tickets")
public class CheckupTicketController {


    @Autowired
    private CheckupTicketService service;


    @RequestMapping(value = "/upsert", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> upsert(@RequestBody CheckupTicket checkupTicket) {
        try{
            return new ResponseEntity<String>(service.createOrUpdateTicket(checkupTicket),
        HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                     HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


    @RequestMapping(value = "/delete/{checkupTicketId}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<String> delete(@PathParam("checkTicketId") String checkupTicketId) {
        try{
            service.deleteTicket(checkupTicketId);
            return new ResponseEntity<String>("Successfully Deleted",
                   HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR) ;
        }

    }

    @RequestMapping(value = "/get/{checkupTicketId}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> get(@PathParam("checkTicketId") String checkupTicketId) {
        try{

            return new ResponseEntity<String>(new ObjectMapper()
                    .writeValueAsString(service.getCheckupTicket(checkupTicketId)),
                   HttpStatus.OK);

        }catch (Exception e) {
            return new ResponseEntity<String>( "Error : "+ e.getLocalizedMessage(),
                   HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }


}