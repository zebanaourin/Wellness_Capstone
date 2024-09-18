package com.CapStone.Wellness_Service.Controller;

import com.CapStone.Wellness_Service.Entity.Helpline;
import com.CapStone.Wellness_Service.Service.HelplineService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/helpline")
public class HelplineController {
    private final HelplineService helplineService;


    public HelplineController(HelplineService helplineService) {
        this.helplineService = helplineService;
    }

    @GetMapping
    public List<Helpline> getAllHelplines(){
        return helplineService.getAllHelpline();
    }
}
