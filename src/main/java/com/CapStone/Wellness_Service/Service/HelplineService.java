package com.CapStone.Wellness_Service.Service;

import com.CapStone.Wellness_Service.Entity.Helpline;
import com.CapStone.Wellness_Service.Repository.HelplineRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HelplineService {
    private final HelplineRepository helplineRepository;

    public HelplineService(HelplineRepository helplineRepository) {
        this.helplineRepository = helplineRepository;
    }
    public List<Helpline> getAllHelpline(){
        return helplineRepository.findAll();
    }


}
