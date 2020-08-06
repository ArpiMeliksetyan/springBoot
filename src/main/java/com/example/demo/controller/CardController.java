package com.example.demo.controller;


import com.example.demo.model.Card;
import com.example.demo.model.exceptions.DuplicateDataException;
import com.example.demo.repository.CardRepository;
import com.example.demo.service.CardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/cards")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private CardService cardService;


    @GetMapping
    public ResponseEntity getAll() {
        return ResponseEntity.ok(cardService.findAll());
//        List<Card> cards = cardService.findAll();
//        return ResponseEntity.status(200).body(cards);

    }


    @PostMapping
    public ResponseEntity add(@RequestBody Card card, @RequestHeader("OriginModule") String accept) throws DuplicateDataException {
        log.info("Requested module is : {}", accept);
        cardService.add(card);
        return ResponseEntity.ok(card);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable int id /*@RequestHeader("Accept-Encoding") String acceptEncoding*/) {
        log.info("Received request for getting card by id :{}", id);
        Optional<Card> optional = cardService.getById(id);
//        System.out.println(acceptEncoding);
        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        }

        return ResponseEntity.status(302).header(HttpHeaders.LOCATION, "/").build();

//        return ResponseEntity.ok(cardService.getById(id));
    }

    @GetMapping("/by-number/{number}")
    public ResponseEntity getByName(@PathVariable String number) {
        Card card = cardService.getByNumber(number);
        return ResponseEntity.ok(card);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable int id, HttpServletRequest request) {
        log.info("Delete request for Card");
        if (log.isDebugEnabled()) {
            log.debug("Delete request for Card with id {} from ip {}", id, request.getRemoteAddr());
        }
        cardService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@RequestBody Card card, @PathVariable int id) throws DuplicateDataException {
        card.setId(id);
        cardService.update(card);
        return ResponseEntity.ok("Your card is updated");
    }

}
