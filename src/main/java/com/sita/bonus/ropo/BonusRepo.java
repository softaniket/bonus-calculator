package com.sita.bonus.ropo;

import com.sita.bonus.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BonusRepo extends JpaRepository<Bonus, String> {
}
