package kl.socialnetwork.web.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kl.socialnetwork.domain.models.serviceModels.LoggerServiceModel;
import kl.socialnetwork.domain.models.viewModels.logger.LoggerViewModel;
import kl.socialnetwork.services.LoggerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/logs")
public class LoggerController {
    private final LoggerService loggerService;
    private final ModelMapper modelMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public LoggerController(LoggerService loggerService, ModelMapper modelMapper, ObjectMapper objectMapper) {
        this.loggerService = loggerService;
        this.modelMapper = modelMapper;
        this.objectMapper = objectMapper;
    }

    @GetMapping(value = "/all")
    public List<LoggerViewModel> allLogs() {
        List<LoggerServiceModel> allLogs = this.loggerService.getAllLogs();

        return allLogs.stream()
                .map(this::parseDate)
                .collect(Collectors.toList());
    }


    @GetMapping(value = "/findByUserName/{username}")
    public List<LoggerViewModel> getLogsByUsername(@PathVariable String username) {
        return this.loggerService
                .getLogsByUsername(username)
                .stream()
                .map(this::parseDate)
                .collect(Collectors.toList());
    }


    private LoggerViewModel parseDate(LoggerServiceModel x) {
        LoggerViewModel loggerViewModel = new LoggerViewModel();
        this.modelMapper.map(x, loggerViewModel);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss");
        String formattedDateTime = x.getTime().format(formatter);
        loggerViewModel.setTime(formattedDateTime);
        return loggerViewModel;
    }


}
