package com.bol.kalah.controller;

import com.bol.kalah.exception.KalahException;
import com.bol.kalah.exception.UnsupportedKalahOperationException;
import com.bol.kalah.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class KalahController {

    @Autowired
    private GameService gameService;

    @GetMapping("/")
    public @ResponseBody
    ModelAndView getBoard() {
        Map<String, Object> model = new HashMap<>();
        model.put("state", gameService.getCurrentState().name());
        try {
            model.put("board", gameService.getBoard());
        } catch (UnsupportedKalahOperationException e) {
            model.put("board", null);
        }
        try {
            model.put("winner", gameService.declareWinner());
        } catch (UnsupportedKalahOperationException e) {
            model.put("winner", null);
        }

        return new ModelAndView("home", model);
    }

    @PostMapping("/new")
    public @ResponseBody
    ResponseEntity newGame() {
        try {
            gameService.startNewGame();
            return ResponseEntity.ok().build();
        } catch (UnsupportedKalahOperationException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/play/{pit}")
    public @ResponseBody
    ResponseEntity play(@PathVariable("pit") int pit) {
        try {
            gameService.play(pit);
            return ResponseEntity.ok().build();
        } catch (KalahException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
