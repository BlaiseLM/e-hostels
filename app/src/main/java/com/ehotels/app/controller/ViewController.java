package com.ehotels.app.controller;

import com.ehotels.app.dao.ViewDAO;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/views")
public class ViewController {

    private final ViewDAO viewDAO;

    public ViewController(ViewDAO viewDAO) {
        this.viewDAO = viewDAO;
    }

    @GetMapping("/chain")
    public ResponseEntity<List<Map<String, Object>>> getChainView() {
        return ResponseEntity.ok(viewDAO.getFullChainView());
    }

    @GetMapping("/hotel")
    public ResponseEntity<List<Map<String, Object>>> getHotelView() {
        return ResponseEntity.ok(viewDAO.getFullHotelView());
    }
}