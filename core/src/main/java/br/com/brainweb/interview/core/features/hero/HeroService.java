package br.com.brainweb.interview.core.features.hero;

import br.com.brainweb.interview.core.features.powerstats.PowerStatsService;
import br.com.brainweb.interview.model.Hero;
import br.com.brainweb.interview.model.PowerStats;
import br.com.brainweb.interview.model.dto.HeroWithStatsDto;
import br.com.brainweb.interview.model.request.CreateHeroRequest;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class HeroService {

	@Autowired
    private final HeroRepository heroRepository;
    @Autowired
    private final PowerStatsService powerStatsService;

    @Transactional
    public Long create(CreateHeroRequest createHeroRequest) {
        PowerStats powerStats = new PowerStats(createHeroRequest);
        Long powerStatsId = powerStatsService.create(powerStats);
        return heroRepository.create(new Hero(createHeroRequest, powerStatsId));
    }

    @Transactional
    public HeroWithStatsDto findById(Long id){
        return heroRepository.findById(id);
    }

    public HeroWithStatsDto findByName(String name) {
        return heroRepository.findByName(name);
    }
}