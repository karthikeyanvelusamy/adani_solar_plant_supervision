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
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CheckupTicketService {

    @Autowired
    private CheckupTicketRepo checkupTicketRepo;

    private ObjectMapper mapper = new ObjectMapper();

    public CheckupTicket getCheckupTicket(String id) {
        Optional<CheckupTicket> checkupTicket = checkupTicketRepo.findById(id);

        if (!checkupTicket.isPresent()) {
            throw new RuntimeException("No ticket found with given ID : " + id);
        }
        return checkupTicket.get();
    }

    public void addCheckListItem(List<CheckListItem> checkListItems, String checkupTicketId) {

        Optional<CheckupTicket> checkupTicket = checkupTicketRepo.findById(checkupTicketId);
        if (checkupTicket.isPresent()) {
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
        List<String> names = mapper.convertValue(getNames(), new TypeReference<List<String>>() {
        });
        int index = 1;
        for (String name : names) {
            checkListItems.add(new CheckListItem(index, name,
                    false,
                    null,
                    null, null,
                    false, null));
        }
        ticket.setCheckListItems(checkListItems);
    }

    public void deleteTicket(String id) {
        checkupTicketRepo.deleteById(id);
    }

    public void deleteTickets(List<String> ids) {
        checkupTicketRepo.deleteAllById(ids);
    }

    private String getNames() {
        return "[\"Check Oil Level in Conservator Tank\",\"Check Oil Level In Buchhloz Relay\",\"Check Prismatic Glass Oil Level\",\"Check Oil Level In Bushing\",\"Check Cooling Ckt (Fan/Pump)\",\"Check Oil Leakage\",\"Check Silica Color In Breather\",\"Check Oil Level In Breather Cup\",\"Check Breathing Status Of Transformer\",\"Check Winding And Oil Temperature\",\"Check Signs Of Hotspots Damage Distress\",\"Check Equipment Corrosion Dirt\",\"Check Water Ingress Into Marshalling Box\",\"Check Marshalling Box Illumination\",\"Check Abnormal Sound Indication\",\"Ensure About Proper Isolation\",\"Clean HV & LV Bushings, Seal of Bushings\",\"Clean LA & BPI Insulators\",\"Clean Marshalling Box\",\"Clean Bushing Oil Level Glass\",\"Clean Transformer Body & Cooling Fins\",\"Clean WTI/OTI/MOG Dial Gauge\",\"Tighten TB/Relay/Contactor of MB\",\"Tighten Prot. Equipment Cables and TB’s\",\"Tighten HV Primary Terminals\",\"Tighten LV Secondary Terminals\",\"Tighten Neutral and all Earthing\",\"Tighten LA & BPI Insulators Clamp\",\"Tighten Gantry Insulators and Jumpers\",\"Tighten all Control Wiring Terminals\",\"Tighten all Covers and Sealing\",\"Tighten Neutral Grounding\",\"Check Gasket for Whole Transformer\",\"Check Bus duct Bus Support Insulator\",\"Check HV & LV Bushings, Seal of Bushings\",\"Check CT & LA BPI Structure and Joints\",\"Check Selector Switch Push Buttons\",\"Check Aux Relays/Contactors/MCB's\",\"Check Indication & Illumination Circuit\",\"Check Closing & Tripping Circuit\",\"Check Door Lock & Proper Sealing\",\"IR Test of Power and Control Cable\",\"IR and PI Value of Transformer\",\"HV and LV Winding Resistance Measurement\",\"Transformer Turns and Voltage Ratio Measurement\",\"Magnetizing Current And Balance Measurement\",\"Simulation of Protection\",\"Remote/Local Tap Change Operation\",\"Oil BDV, Moisture, DGA Test\"]";
    }
}
