package com.ubots.relationshipCenter.config;

import com.ubots.relationshipCenter.entities.Attendant;
import com.ubots.relationshipCenter.entities.Called;
import com.ubots.relationshipCenter.entities.enums.CalledStatus;
import com.ubots.relationshipCenter.entities.enums.CalledType;
import com.ubots.relationshipCenter.repositories.AttendantRepository;
import com.ubots.relationshipCenter.repositories.CalledRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private AttendantRepository attendantRepository;

    @Autowired
    private CalledRepository calledRepository;

    @Override
    public void run(String... args) throws Exception {

        Attendant att1 =  new Attendant(null, "Atendente 1 - Cartões", CalledType.CARDS);
        Attendant att2 =  new Attendant(null, "Atendente 2 - Empréstimos", CalledType.LOANS);
        Attendant att3 =  new Attendant(null, "Atendente 3 - Outros Assuntos", CalledType.OTHERS);

        attendantRepository.saveAll((Arrays.asList(att1, att2, att3)));

        Called cal1 = new Called(null, Instant.parse("2023-09-19T14:53:07Z"), att1, CalledType.CARDS, "Teste 1 - CARDS", "Descrição teste 1 - CARDS", CalledStatus.IN_PROGRESS);
        Called cal2 = new Called(null, Instant.parse("2023-09-19T15:53:07Z"), att1, CalledType.CARDS, "Teste 2 - CARDS", "Descrição teste 2 - CARDS", CalledStatus.IN_PROGRESS);
        Called cal3 = new Called(null, Instant.parse("2023-09-19T14:53:07Z"), att1, CalledType.CARDS, "Teste 3 - CARDS", "Descrição teste 3 - CARDS", CalledStatus.IN_PROGRESS);
        Called cal4 = new Called(null, Instant.parse("2023-09-19T15:53:07Z"), att2, CalledType.LOANS, "Teste 1 - LOANS", "Descrição teste 1 - LOANS", CalledStatus.IN_PROGRESS);
        Called cal5 = new Called(null, Instant.parse("2023-09-19T14:53:07Z"), att2, CalledType.LOANS, "Teste 2 - LOANS", "Descrição teste 2 - LOANS", CalledStatus.IN_PROGRESS);
        Called cal6 = new Called(null, Instant.parse("2023-09-19T14:53:07Z"), att3, CalledType.OTHERS, "Teste 1 - OTHERS", "Descrição teste 1 - OTHERS", CalledStatus.IN_PROGRESS);
        Called cal7 = new Called(null, Instant.parse("2023-09-19T15:53:07Z"), att3, CalledType.OTHERS, "Teste 2 - OTHERS", "Descrição teste 2 - OTHERS", CalledStatus.IN_PROGRESS);
        Called cal8 = new Called(null, Instant.parse("2023-09-19T15:53:07Z"), null, CalledType.CARDS, "Teste 4 - CARDS", "Descrição teste 4 - CARDS", CalledStatus.CREATED);

        calledRepository.saveAll(Arrays.asList(cal1, cal2, cal3, cal4, cal5, cal6, cal7, cal8));

    }
}
