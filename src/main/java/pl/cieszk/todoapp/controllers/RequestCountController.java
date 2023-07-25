package pl.cieszk.todoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.cieszk.todoapp.utils.RequestCounterFilter;

@RestController
@RequestMapping("/api/utils")
public class RequestCountController {
    private final RequestCounterFilter requestCounterFilter;

    @Autowired
    public RequestCountController(RequestCounterFilter requestCounterFilter) {
        this.requestCounterFilter = requestCounterFilter;
    }

    @GetMapping("/request-count")
    public ResponseEntity<Integer> getRequestCount() {
        return ResponseEntity.ok(requestCounterFilter.getRequestCount());
    }
}
