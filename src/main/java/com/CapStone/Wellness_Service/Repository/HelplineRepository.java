package com.CapStone.Wellness_Service.Repository;

import com.CapStone.Wellness_Service.Entity.Helpline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelplineRepository extends JpaRepository<Helpline, Long> {
}
